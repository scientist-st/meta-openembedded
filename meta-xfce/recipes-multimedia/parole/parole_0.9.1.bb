DESCRIPTION = "Parole is a modern simple media player based on the GStreamer framework"
HOMEPAGE = "http://goodies.xfce.org/projects/applications/parole"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=59530bdf33659b29e73d4adb9f9f6552"

inherit xfce-app gtk-doc mime

DEPENDS += " \
    glib-2.0 \
    dbus-glib \
    gtk+3 \
    \
    xfce4-dev-tools-native \
    libxfce4util \
    libxfce4ui \
    xfconf \
    \
    gstreamer1.0-plugins-base \
    taglib \
"

SRC_URI[md5sum] = "85d76320726cda93e29e0ea59141c34c"
SRC_URI[sha256sum] = "a596b0add40294258dcd87b73317273e5060c76c201a0a33e8e78a514a6b4baf"

RRECOMMENDS_${PN} += " \
    gstreamer1.0-plugins-base-meta \
    gstreamer1.0-plugins-good-meta \
    gstreamer1.0-plugins-bad-meta \
    gstreamer1.0-plugins-ugly-meta \
"

EXTRA_OECONF = "--disable-gtk-doc"

PACKAGECONFIG ??= "notify"
PACKAGECONFIG[clutter] = "--enable-clutter, --disable-clutter, clutter"
PACKAGECONFIG[notify] = "--enable-notify-plugin, --disable-notify-plugin, libnotify"

FILES_${PN} += " \
    ${datadir}/appdata \
    ${libdir}/parole-0/*.so \
"
FILES_${PN}-dbg += "${libdir}/parole-0/.debug"
FILES_${PN}-dev += "${libdir}/parole-0/*.la"
