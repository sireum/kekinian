#!/bin/bash
# Usage: .ci/watchdog.sh <idle-minutes> <command> [<arg> ...]
#
# Runs the command, passing its output through, and stops it if it prints nothing for
# <idle-minutes>.  Before stopping it, every Java process's threads are dumped into the
# log, which is the point: CI's Linux amd64 job intermittently freezes while proyek is
# loading the project, and GitHub's own six-hour limit kills it without leaving any
# evidence of what the threads were waiting on.  The longest silence in a passing run
# is under ten minutes, so the limit can sit well clear of it.
#
# Exits with the command's status, or 124 (timeout(1)'s) when the watchdog stopped it.
set -u

idle_minutes=$1
shift
home=$(cd -P "$(dirname "$0")/.." && pwd -P)
log=$(mktemp)

# its own session and process group, so that it and everything it started can be found
# and stopped together
setsid "$@" > "$log" 2>&1 &
pid=$!
tail -n +1 -f --pid="$pid" "$log" &
tail_pid=$!

dump_threads() {
  local jcmd=""
  for j in "$home/bin/linux/java/bin/jcmd" "$home/bin/linux/arm/java/bin/jcmd"; do
    if [ -x "$j" ]; then
      jcmd=$j
      break
    fi
  done
  echo ""
  echo "=== processes ==="
  ps -o pid,ppid,etime,args --forest --sid "$pid" | cut -c1-300
  for jpid in $(pgrep -s "$pid" java); do
    echo ""
    echo "=== threads of $jpid: $(tr '\0' ' ' < "/proc/$jpid/cmdline" | cut -c1-300) ==="
    if [ -n "$jcmd" ]; then
      "$jcmd" "$jpid" Thread.print -l 2>&1 || kill -QUIT "$jpid"
    else
      # the JVM prints the dump on its own stdout, which is the log being tailed
      kill -QUIT "$jpid"
    fi
  done
}

while kill -0 "$pid" 2>/dev/null; do
  sleep 30
  if [ $(( $(date +%s) - $(stat -c %Y "$log") )) -ge $(( idle_minutes * 60 )) ]; then
    echo ""
    echo "::error::watchdog: no output for ${idle_minutes} minutes; dumping Java threads, then stopping $*"
    dump_threads
    sleep 5  # let a SIGQUIT dump reach the log
    kill -TERM -- "-$pid" 2>/dev/null
    sleep 10
    kill -KILL -- "-$pid" 2>/dev/null
    wait "$tail_pid" 2>/dev/null
    rm -f "$log"
    exit 124
  fi
done

wait "$pid"
status=$?
wait "$tail_pid" 2>/dev/null
rm -f "$log"
exit "$status"
