FROM sireum/ci:latest
MAINTAINER Robby <robby@k-state.edu>
ENV SIREUM_HOME=/opt/sireum \
    JAVA_HOME=/opt/sireum/bin/java \
    SCALA_HOME=/opt/sireum/bin/scala \
    PATH=/opt/sireum/bin:/opt/sireum/bin/java/bin:/opt/sireum/bin/scala/bin:$PATH
RUN cd /opt && \
    git clone --recursive -b master https://github.com/sireum/kekinian.git sireum && \
    $SIREUM_HOME/bin/build.cmd bin test && \
    rm -fR ~/Downloads/sireum && \
    sireum
