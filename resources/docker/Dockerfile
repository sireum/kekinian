FROM ubuntu:20.04
MAINTAINER Robby <robby@k-state.edu>
ENV DEBIAN_FRONTEND=noninteractive
RUN apt-get update && \
    apt-get upgrade -y && \
    apt-get install -y git libgomp1 xz-utils build-essential automake cmake aspcud curl m4 \
                       locales bc libc6-dev libgmp-dev libsodium-dev nano software-properties-common zlib1g-dev \
                       nodejs python3-pip python3-sphinx python3-pygments python3-wheel && \
    apt-get autoremove -y && \
    pip3 install pygments-style-github sphinx_bootstrap_theme && \
    locale-gen en_US en_US.UTF-8 && \
    dpkg-reconfigure locales && \
    rm -rf /var/lib/apt/lists/*
