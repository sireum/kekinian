set FIRST_RUN=true
vagrant up --no-provision
vagrant ssh -c 'sudo apt update'
vagrant ssh -c 'sudo DEBIAN_FRONTEND=noninteractive apt-get --with-new-pkgs upgrade -y'
vagrant ssh -c 'sudo DEBIAN_FRONTEND=noninteractive apt-get install -y build-essential linux-headers-generic'
vagrant halt
set FIRST_RUN=false
vagrant up || exit /b
vagrant reload
