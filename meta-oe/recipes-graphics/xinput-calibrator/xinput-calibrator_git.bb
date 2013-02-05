DESCRIPTION = "A generic touchscreen calibration program for X.Org"
HOMEPAGE = "http://www.freedesktop.org/wiki/Software/xinput_calibrator"
LICENSE = "MIT-X"
LIC_FILES_CHKSUM = "file://src/calibrator.cpp;endline=22;md5=1bcba08f67cdb56f34021557898e4b5a"
DEPENDS = "virtual/libx11 libxi"

PV = "0.7.5+git${SRCPV}"
PR = "r4"

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
    file://99-xf86-xinput-calibrator.rules \
    file://xinput-calibrator@.service \
    file://xinput_calibrator_pointercal2.sh \
"

S = "${WORKDIR}/git"

# force native X11 ui as we don't have gtk+ in DEPENDS
EXTRA_OECONF += "--with-gui=x11"

do_install_append() {
	install -d ${D}${bindir}
	install -m 0755 ${WORKDIR}/xinput_calibrator_pointercal2.sh ${D}${bindir}
	install -d ${D}/${sysconfdir}/X11/xorg.conf.d

	# use our script to make calibration permanent (sufficiont rights assumed)
	sed -i 	-e 's,^Exec=.*,Exec=${base_bindir}/sh -c '\''${bindir}/xinput_calibrator_pointercal2.sh; cat'\'',' \
		${D}${datadir}/applications/xinput_calibrator.desktop

	# udev/systemd start?
	if ${@base_contains('DISTRO_FEATURES','systemd','true','false',d)}; then
		install -d ${D}${systemd_unitdir}/system
		install -m 0644 ${WORKDIR}/xinput-calibrator@.service ${D}${systemd_unitdir}/system
		install -d ${D}${base_libdir}/udev/rules.d
		install -m 0644 ${WORKDIR}/99-xf86-xinput-calibrator.rules ${D}${base_libdir}/udev/rules.d
	# ${sysconfdir}/xdg/autostart?
	else
	        install -d ${D}${sysconfdir}/xdg/autostart
	        install -m 0644 ${D}${datadir}/applications/xinput_calibrator.desktop ${D}${sysconfdir}/xdg/autostart
		# use our script to make calibration permanent (sufficiont rights assumed) / not in Terminal
		# /var/log is not accessible for all users so use user's home
	        sed -i 	-e 's,^Exec=.*,Exec=${base_bindir}/sh -c '\''${bindir}/xinput_calibrator_pointercal2.sh xdg-autostart 1>>$HOME/xinput-calibration.log 2>\&1'\'',' \
			-e 's,^Terminal=true,Terminal=false,' \
			${D}${sysconfdir}/xdg/autostart/xinput_calibrator.desktop
	fi
}

FILES_${PN} += "${sysconfdir}/xdg/autostart ${systemd_unitdir}/system"
RDEPENDS_${PN} = "xterm"
