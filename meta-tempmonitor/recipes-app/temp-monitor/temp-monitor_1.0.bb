SUMMARY = "Temperature monitoring and LED control application"
DESCRIPTION = "Reads CPU temperature and controls ACT LED based on threshold"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

inherit cmake systemd

SRC_URI = "file://temp-monitor.service \
           file://temp-monitor"
S = "${WORKDIR}/temp-monitor"


SYSTEMD_SERVICE:${PN} = "temp-monitor.service"
SYSTEMD_AUTO_ENABLE = "enable"

do_configure() {
    cmake ${S} -B ${B} -DCMAKE_INSTALL_PREFIX=${D}${prefix}
}

do_compile() {
    cmake --build ${B} -- -j$(nproc)
}

do_install() {
    cmake --install ${B} --prefix=${D}${prefix}

    install -d ${D}${systemd_system_unitdir}
    install -m 0644 ${WORKDIR}/temp-monitor.service ${D}${systemd_system_unitdir}/
}

FILES:${PN} = "${bindir}/temp-monitor \
               ${systemd_system_unitdir}/temp-monitor.service"
