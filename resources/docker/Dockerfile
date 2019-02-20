FROM ubuntu:18.04
MAINTAINER Robby <robby@k-state.edu>
ENV SIREUM_HOME=/opt/sireum \
    JAVA_HOME=/opt/sireum/bin/java \
    SCALA_HOME=/opt/sireum/bin/scala \
    PATH=/opt/sireum/bin:/opt/sireum/bin/java/bin:/opt/sireum/bin/mill:/opt/sireum/bin/scala/bin:$PATH
RUN dpkg --add-architecture i386 && \
    apt-get update && apt-get install -y locales && \
    locale-gen en_US en_US.UTF-8 && \
    dpkg-reconfigure locales && \
    apt-get install -y wget git zip unzip libgomp1 xz-utils build-essential automake cmake aspcud curl python \
                       bc libc6-dev libgmp-dev libsodium-dev libc6-dev-i386 libgmp-dev:i386 libsodium-dev:i386 nano zlib1g-dev gcc && \
    rm -rf /var/lib/apt/lists/* && \
    cd /opt && \
    git clone --recursive -b master https://github.com/sireum/kekinian.git sireum && \
    $SIREUM_HOME/bin/test.sh && \
    sireum
