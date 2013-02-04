DESCRIPTION = "A generic touchscreen calibration program for X.Org"
HOMEPAGE = "http://www.freedesktop.org/wiki/Software/xinput_calibrator"
LICENSE = "MIT-X"
LIC_FILES_CHKSUM = "file://src/calibrator.cpp;endline=22;md5=1bcba08f67cdb56f34021557898e4b5a"
DEPENDS = "virtual/libx11 libxi"

PV = "0.7.5+git${SRCPV}"
PR = "r2"

inherit autotools

SRCREV = "e02de96acbe2fa5a57b0bda6821a053b1b123fd2"
SRC_URI = " \
    git://github.com/tias/xinput_calibrator.git;protocol=git \
    file://0001-Prevent-timeout-exit-when-no-timeout-is-used.patch \
    file://0001-Add-parameter-output-filename-and-store-in-output_fi.patch \
    file://0002-CalibratorEvdev-do-write-calibration-results-if-outp.patch \
    file://0003-CalibratorXorgPrint-do-write-calibration-results-if-.patch \
    file://0004-CalibratorUsbtouchscreen-overridde-default-file-name.patch \
    file://0005-Make-all-Calibrator-destructors-virtual-to-fix-warni.patch \
    file://0006-Set-up-buffer-size-for-max-line-length-only-once-in-.patch \
"

S = "${WORKDIR}/git"

# force native X11 ui as we don't have gtk+ in DEPENDS
EXTRA_OECONF += "--with-gui=x11"

do_install_append() {
        install -d ${D}${bindir}
        install -m 0755 scripts/xinput_calibrator_pointercal.sh ${D}${bindir}/xinput_calibrator_once.sh

        install -d ${D}${sysconfdir}/xdg/autostart
        sed -i -e 's,^Exec=.*,Exec=${bindir}/xinput_calibrator_once.sh,' scripts/xinput_calibrator.desktop
        install -m 0644 scripts/xinput_calibrator.desktop ${D}${sysconfdir}/xdg/autostart
}

FILES_${PN} += "${sysconfdir}/xdg/autostart"
RDEPENDS_${PN} = "xinput xterm"
RRECOMMENDS_${PN} = "pointercal-xinput"
