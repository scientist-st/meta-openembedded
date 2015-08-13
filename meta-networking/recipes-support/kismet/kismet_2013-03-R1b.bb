SUMMARY = "Kismet is a wireless detector, sniffer, and intrusion detector"
LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://kismet_server.cc;beginline=1;endline=17;md5=f9601a6e93556bbcd97c972500aa1e75"

SRC_URI = "https://www.kismetwireless.net/code/${BPN}-${PV}.tar.xz"
SRC_URI[md5sum] = "596acdd2940887dd05a498ea27475eea"
SRC_URI[sha256sum] = "636d4d7ef8c67ae6ee8d8e1635f5115700eecb9fa4c208afaee30238db527c2c"

inherit autotools-brokensep pkgconfig

DEPENDS = "libpcap libpcre libnl ncurses"

# TODO group kismet
