DESCRIPTION = "Plugin for Xfce4 adapted from Gnome2 XPenguins applet"
SECTION = "x11/application"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"

inherit xfce-panel-plugin

PV = "4.10.1"
SRC_URI = "git://${HOME}/git-projects/xfce/xfce4-xpenguins-plugin;protocol=file;branch=master"
SRCREV = "${AUTOREV}"

#PV = "4.10.1+gitr${SRCPV}"

#SRC_URI = "git://gitorious.org/xfce/xfce4-xpenguins-plugin.git;protocol=git;branch=master"
#SRCREV = "25eebf13d03db45e178fbb7898fd56011e73b91b"
S = "${WORKDIR}/git"

FILES_${PN} += "${datadir}/xfce4/xpenguins/themes"
