#!/bin/bash -ex
export FIRST_RUN='true'
vagrant up --no-provision
vagrant ssh -c 'sudo apt-get update'
vagrant ssh -c 'sudo DEBIAN_FRONTEND=noninteractive apt-get --with-new-pkgs upgrade -y'
vagrant ssh -c 'sudo DEBIAN_FRONTEND=noninteractive apt-get install -y build-essential linux-headers-generic'
vagrant halt
export FIRST_RUN='false'
vagrant up
vagrant reload
