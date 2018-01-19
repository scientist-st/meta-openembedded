DESCRIPTION = "An advanced menu editor"
HOMEPAGE = "https://launchpad.net/menulibre"
SECTION = "x11/graphics"
LICENSE = "GPLv3"
LIC_FILES_CHKSUM = "file://COPYING;md5=d32239bcb673463ab874e80d47fae504"

DEPENDS = "python3-distutils-extra-native intltool-native"

inherit distutils3 gtk-icon-cache

SRC_URI = " \
    https://launchpad.net/menulibre/2.1/${PV}/+download/${PN}-${PV}.tar.gz \
"
SRC_URI[md5sum] = "0e30f24f544f0929621046d17874ecf0"
SRC_URI[sha256sum] = "36a6350019e45fbd1219c19a9afce29281e806993d4911b45b371dac50064284"

do_install_append() {
    sed -i 's:${D}::g' ${D}${datadir}/applications/menulibre.desktop
}

FILES_${PN} += " \
    ${datadir}/applications \
    ${datadir}/menulibre \
    ${datadir}/icons \
"

RDEPENDS_${PN} += " \
    gtk+3 \
    python3-pygobject \
    gnome-menus3 \
    python3-unixadmin \
    python3-psutil \
"
