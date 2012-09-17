DESCRIPTION = "Parole is a modern simple media player based on the GStreamer framework"
HOMEPAGE = "http://goodies.xfce.org/projects/applications/parole"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=59530bdf33659b29e73d4adb9f9f6552"

inherit xfce-app

DEPENDS += "dbus-glib glib-2.0 gst-meta-base gtk+ libxfce4ui libxfce4util libnotify"

SRC_URI[md5sum] = "918914fcdede178a027cd1ed70954c30"
SRC_URI[sha256sum] = "7ea01168beffda972c8a7392d8e7934edc6be9e19ad8655df7697ca699d0746c"

FILES_${PN} += "${libdir}/parole-0/*.so"
FILES_${PN}-dbg += "${libdir}/parole-0/.debug"
FILES_${PN}-dev += "${libdir}/parole-0/*.la"

RRECOMMENDS_${PN} = "gst-ffmpeg gst-meta-video gst-meta-audio"
