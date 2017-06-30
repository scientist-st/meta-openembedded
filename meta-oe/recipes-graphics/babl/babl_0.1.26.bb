SUMMARY = "Babl is a dynamic, any to any, pixel format conversion library"
LICENSE = "LGPL-3.0"
LIC_FILES_CHKSUM = "file://COPYING;md5=6a6a8e020838b23406c81b19c1d46df6"

inherit gnomebase

SRC_URI = "http://ftp.gimp.org/pub/${BPN}/0.1/${BP}.tar.bz2"
SRC_URI[md5sum] = "c5dd31d249379a75dec224a152bbf8cb"
SRC_URI[sha256sum] = "fd80e165f1534c64457a8cce7a8aa90559ab28ecd32beb9e3948c5b8cd373d34"

FILES_${PN} += "${libdir}/babl-*/*.so"
FILES_${PN}-dev += "${libdir}/babl-*/*.la"
FILES_${PN}-dbg += "${libdir}/babl-*/.debug/"
