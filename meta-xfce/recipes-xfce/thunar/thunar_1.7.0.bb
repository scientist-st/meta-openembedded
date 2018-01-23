SUMMARY = "File manager for the Xfce Desktop Environment"
SECTION = "x11"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=94d55d512a9ba36caa9b7df079bae19f"
DEPENDS = "exo glib-2.0 gdk-pixbuf libxfce4util libxfce4ui xfconf libsm dbus-glib startup-notification libnotify xfce4-panel udev"

inherit xfce distro_features_check gobject-introspection

REQUIRED_DISTRO_FEATURES = "x11"

SRC_URI = "http://archive.xfce.org/src/xfce/${BPN}/${@'${PV}'[0:3]}/Thunar-${PV}.tar.bz2"
SRC_URI[md5sum] = "60f0cb9928859aa94028d06f82dd9e1c"
SRC_URI[sha256sum] = "36ecbe6d5a406a02f186118c8192b3587121172d9eebb1cb2df6a3bc06272400"

S = "${WORKDIR}/Thunar-${PV}/"

PACKAGECONFIG ??= ""
PACKAGECONFIG[pcre] = "--enable-pcre,--disable-pcre,libpcre"

FILES_${PN} += " \
    ${libdir}/thunarx-3/* \
    ${libdir}/xfce4/panel/plugins/* \
    ${libdir}/Thunar/[Tt]hunar* \
    ${datadir}/appdata \
    ${datadir}/dbus-1 \
    ${datadir}/polkit-1 \
    ${datadir}/Thunar \
    ${datadir}/xfce4/panel/plugins/* \
"

FILES_${PN}-dbg += "${libdir}/thunarx-2/.debug/ \
                    ${libdir}/xfce4/panel/plugins/.debug/ \
                    ${libdir}/Thunar/.debug/"

RRECOMMENDS_${PN} = "gvfs gvfsd-trash"
