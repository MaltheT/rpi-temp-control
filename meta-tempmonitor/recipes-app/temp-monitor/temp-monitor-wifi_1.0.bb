SUMMARY = "WiFi configuration for temp monitoring system"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

inherit systemd

SRC_URI = "file://wpa_supplicant-wlan0.conf \
           file://wpa_supplicant@wlan0.service"

S = "${WORKDIR}"

SYSTEMD_SERVICE:${PN} = "wpa_supplicant@wlan0.service"
SYSTEMD_AUTO_ENABLE = "enable"

RDEPENDS:${PN} += "dhcpcd wpa-supplicant"

do_install() {
    install -d ${D}${sysconfdir}/wpa_supplicant
    install -m 0600 ${WORKDIR}/wpa_supplicant-wlan0.conf ${D}${sysconfdir}/wpa_supplicant/wpa_supplicant-wlan0.conf

    install -d ${D}${systemd_system_unitdir}
    install -m 0644 ${WORKDIR}/wpa_supplicant@wlan0.service ${D}${systemd_system_unitdir}/
}

FILES:${PN} = "${sysconfdir}/wpa_supplicant/wpa_supplicant-wlan0.conf \
               ${systemd_system_unitdir}/wpa_supplicant@wlan0.service"
