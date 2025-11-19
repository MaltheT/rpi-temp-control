SUMMARY = "Raspberry Pi Temperature Control System Image"
LICENSE = "MIT"

LICENSE_FLAGS_ACCEPTED += "synaptics-killswitch"

inherit core-image

IMAGE_FEATURES += "ssh-server-dropbear splash"

IMAGE_INSTALL:append = " \
    kernel-modules \
    linux-firmware-rpidistro-bcm43455 \
    wpa-supplicant \
    iw \
    i2c-tools \
    nano \
    htop \
    dhcpcd \
    temp-monitor-wifi \
    temp-monitor \
    "

EXTRA_USERS_PARAMS = "usermod -P root root;"

hostname:pn-base-files = "tempmonitor"
