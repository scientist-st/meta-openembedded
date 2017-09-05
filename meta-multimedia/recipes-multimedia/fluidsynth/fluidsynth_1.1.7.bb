SUMMARY = "Fluidsynth is a software synthesizer"
HOMEPAGE = "http://www.fluidsynth.org/"
SECTION = "libs/multimedia"
LICENSE = "LGPL-2.1"
LIC_FILES_CHKSUM = "file://LICENSE;md5=fc178bcd425090939a8b634d1d6a9594"

DEPENDS = "alsa-lib ncurses glib-2.0"

SRC_URI = "git://github.com/FluidSynth/fluidsynth.git"
SRCREV = "e3232e377e7a913c3b0b74a76c9cbaac50b14cdc"
S = "${WORKDIR}/git"

inherit cmake pkgconfig lib_package

PACKAGECONFIG ??= "${@bb.utils.filter('DISTRO_FEATURES', 'pulseaudio', d)}"
PACKAGECONFIG[sndfile] = "-Denable-libsndfile-support=ON,-Denable-libsndfile-support=OFF,libsndfile1"
PACKAGECONFIG[jack] = "-Denable-jack-support=ON,-Denable-jack-support=OFF,jack"
PACKAGECONFIG[pulseaudio] = "-Denable-pulseaudio=ON,--Denable-pulseaudio=OFF,pulseaudio"
PACKAGECONFIG[portaudio] = "-Denable-portaudio=ON,-Denable-portaudio=OFF,portaudio-v19"
