SUMMARY = "WiFi configuration for temp monitoring system"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

inherit systemd

SRC_URI = "file://wpa_supplicant-wlan0.conf \
    file://wpa_supplicant@wlan0.service"

S = "${WORKDIR}"

SYSTEMD_SERVICE:${PN} = "wpa_supplicant@wlan0.service"
SYSTEMD_AUTO_ENABLE = "enable"

do_install() {
    install -d ${D}${sysconfdir}/wpa_suplicant
    install -m 0600 ${WORKDIR}/wpa_supplicant-wlan0.conf ${D}${sysconfdir}/wpa_suplicant/wpa_supplicant-wlan0.conf

    install -d ${D}${systemd_system_unidir}
    install -m 0644 ${WORKDIR}/wpa_supplicant@wlan0.service ${D}${systemd_system_unidir}/
}

FILES:${PN} = "${sysconfdir}/wpa_supplicant/wpa_supplicant-wlan0.conf \
${systemd_system_unidir}/wpa_supplicant@wlan0.service"
