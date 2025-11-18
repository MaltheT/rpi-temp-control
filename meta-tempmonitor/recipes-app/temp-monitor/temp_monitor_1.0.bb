SUMMARY = "Temperature monitoring and LED control application"
DESCRIPTION = "Reads CPU temperature and controls ACT LED based on threshold"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

# Inherit cmake and systemd
inherit cmake systemd

SRC_URI = "file:///home/mtb/Projects/rpi-temp-control/temp-monitor \
           file://temp-monitor.service"

# Source directory (where CMakeLists.txt is)
S = "${WORKDIR}/temp-monitor"

# Systemd configuration
SYSTEMD_SERVICE:${PN} = "temp-monitor.service"
SYSTEMD_AUTO_ENABLE = "enable"

# Install the binary and service
do_install() {
    install -d ${D}${bindir}
    install -m 0755 ${B}/temp-monitor ${D}${bindir}/temp-monitor
    
    install -d ${D}${systemd_system_unitdir}
    install -m 0644 ${WORKDIR}/temp-monitor.service ${D}${systemd_system_unitdir}/
}

# What files this recipe provides
FILES:${PN} = "${bindir}/temp-monitor \
               ${systemd_system_unitdir}/temp-monitor.service"
