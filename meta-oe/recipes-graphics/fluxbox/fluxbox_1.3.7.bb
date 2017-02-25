SUMMARY = "Fluxbox is a windowmanager for X"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://COPYING;md5=e90c7c0bee6fc368be0ba779e0eac053"

inherit autotools gettext pkgconfig distro_features_check

SRC_URI = " \
    http://sourceforge.net/projects/${BPN}/files/${BPN}/${PV}/${BPN}-${PV}.tar.xz \
    file://fluxbox-applications.desktop \
    file://fluxbox-xsessions.desktop \
"
SRC_URI[md5sum] = "b44afd10ee1e64624c23115aa51dcd55"
SRC_URI[sha256sum] = "fc8c75fe94c54ed5a5dd3fd4a752109f8949d6df67a48e5b11a261403c382ec0"

REQUIRED_DISTRO_FEATURES = "x11"

DEPENDS += " \
    virtual/libx11 \
    libxft \
    fribidi \
    libxrender \
    libxpm \
    libxext \
    libxrandr \
    libxinerama \
    fontconfig \
"

do_configure_append() {
    mkdir -p ${B}/src
}

do_install_append() {
    install -d ${D}${datadir}/xsessions
    install -m 0644 ${WORKDIR}/fluxbox-xsessions.desktop ${D}${datadir}/xsessions/fluxbox.desktop

    install -d ${D}${datadir}/applications
    install -m 0644 ${WORKDIR}/fluxbox-applications.desktop ${D}${datadir}/applications/fluxbox.desktop
}

FILES_${PN} += "${datadir}/xsessions"

RDEPENDS_${PN} += " \
    xmodmap \
"
