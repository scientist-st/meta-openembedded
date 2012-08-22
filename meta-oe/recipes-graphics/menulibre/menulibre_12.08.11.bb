DESCRIPTION = "An advanced menu editor"
HOMEPAGE = "https://launchpad.net/menulibre"
SECTION = "x11/graphics"
LICENSE = "GPLv3"
LIC_FILES_CHKSUM = "file://COPYING;md5=d32239bcb673463ab874e80d47fae504"

DEPENDS = "python python-distutils-extra-native"
inherit allarch distutils

S = "${WORKDIR}/${PN}"

SRC_URI = "https://launchpad.net/${PN}/trunk/${PV}/+download/${PN}_${PV}.tar.gz"
SRC_URI[md5sum] = "4338d1800a883ab990fa171886062cc8"
SRC_URI[sha256sum] = "e78b56bb8430f440a2a3051a538c5fa7c547ed3efafdd064e95109e1b8cd71d4"

FILES_${PN} += "${datadir}/applications ${datadir}/menulibre ${datadir}/share"

RDEPENDS_${PN} += "python-textutils python-pygobject"
