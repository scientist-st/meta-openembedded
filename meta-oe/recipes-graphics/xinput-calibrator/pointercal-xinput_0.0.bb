DESCRIPTION = "Touchscreen calibration data from xinput-calibrator"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

PR = "r5"

SRC_URI = "file://pointercal.xinput"
S = "${WORKDIR}"

do_install() {
	# Only install file if it has a contents
	if [ -s ${S}/pointercal.xinput ]; then
                install -d ${D}/${sysconfdir}/X11/xorg.conf.d
	        install -m 0644 ${S}/pointercal.xinput ${D}/${sysconfdir}/X11/xorg.conf.d/99-pointercal.conf
	fi
}

PACKAGE_ARCH = "${MACHINE_ARCH}"
CONFFILES_${PN} = "${sysconfdir}/X11/xorg.conf.d/99-pointercal.conf"
