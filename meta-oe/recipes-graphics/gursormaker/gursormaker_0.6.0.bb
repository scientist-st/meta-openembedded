DESCRIPTION = "Gursor Maker is a graphical user interface that allows manipulating cursor themes for X11"
HOMEPAGE = "http://gursormaker.sourceforge.net"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=ea57b4c0b954c4ee11e2feedd61fa5a2"

SRC_URI = "http://sourceforge.net/projects/${BPN}/files/${BPN}/${BPN}-${PV}/${BPN}-${PV}.tar.bz2"
SRC_URI[md5sum] = "52d2ead2a3b9872fe79ab28164b59f1d"
SRC_URI[sha256sum] = "c4deb96ecca48b1907d98feb86a8377a59deedf1aba7b16ad4d73d6a2b314ae4"

inherit distutils

RDEPENDS_${PN} = "xcursorgen python python-numpy python-pygtk python-lxml"
