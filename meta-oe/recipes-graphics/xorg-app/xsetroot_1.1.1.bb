require recipes-graphics/xorg-app/xorg-app-common.inc

SUMMARY = "Classic X utility to set your root window background to a given pattern or color"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://COPYING;md5=6ea29dbee22324787c061f039e0529de"

DEPENDS += "xbitmaps libxcursor"

SRC_URI[md5sum] = "7211b31ec70631829ebae9460999aa0b"
SRC_URI[sha256sum] = "ba215daaa78c415fce11b9e58c365d03bb602eaa5ea916578d76861a468cc3d9"
