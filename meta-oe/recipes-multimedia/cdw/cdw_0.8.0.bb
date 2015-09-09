SUMMARY = "Front-end for tools used for burning data CD/DVD"
HOMEPAGE = "http://cdw.sourceforge.net/"

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=c32b7a02c48c4d666b342f98c38a746c"

SRC_URI = "${SOURCEFORGE_MIRROR}/${BPN}/${BPN}-${PV}.tar.gz"
SRC_URI[md5sum] = "60a8d094343b50b436a013737261e92c"
SRC_URI[sha256sum] = "8f5bd7ed41bc07a84c5aa693bed3c38420e60ba6a3b5ae6ff8e41d78a4665fd2"

inherit autotools pkgconfig

DEPENDS = "libcdio ncurses libburn"
RDEPENDS_${PN} = "cdrkit"
