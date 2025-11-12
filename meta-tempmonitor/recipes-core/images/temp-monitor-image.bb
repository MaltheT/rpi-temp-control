SUMMARY = "Raspberry Pi Temperature Monitoring System"
LICENSE = "MIT"

inherit core-image

IMAGE_FEATURES += "ssh-server-dropbear splash"

IMAGE_INTSALL:append = " \
kernel-modules \
linux-firmware-rpidistro-bcm43455 \
wpa-supplicant \
iw \
i2c-tools \
nano \
htop \
temp-monitor-wifi \
"

EXTRA_USERS_PARAMS = "usermod -P root root;"

hostname:pn-base-files = "tempmonitor"
