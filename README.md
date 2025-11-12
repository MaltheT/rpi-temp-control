

# how to build yocto image

1) clone yocto, poky, and meta-raspberrypi

2) edit local.conf to include:

LICENSE_FLAGS_ACCEPTED += "synaptics-killswitch"

Find the line MACHINE ??= "qemux86-64"
Change it to: MACHINE ?= "raspberrypi5"

3) create a symlik to ~/your/yocto/ with the meta-tempmonitor in this git

TODO: repeate these steps to double check and flesh out!

TODO: add section about autoconfigure wifi
