DESCRIPTION = "python-distutils extension integrating gettext support, themed icons and scrollkeeper based documentation"
HOMEPAGE = "https://launchpad.net/python-distutils-extra"
SECTION = "devel/python"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://LICENSE;md5=4325afd396febcb659c36b49533135d4"

inherit setuptools

DEPENDS += "python-distribute-native"

DISTUTILS_INSTALL_ARGS += "--install-lib=${libdir}/${PYTHON_DIR}/site-packages"

SRC_URI = "https://launchpad.net/${BPN}/trunk/${PV}/+download/${BPN}-${PV}.tar.gz"
SRC_URI[md5sum] = "8e42b67947b80e27557b0fa66996b13e"
SRC_URI[sha256sum] = "94f882aeff4c3227490a012c46a5b445494ef496a539ccf554f8ad5ea92cfcb2"

do_install_append() {
	rm -rf ${D}/${libdir}/${PYTHON_DIR}/site-packages/*.egg-info
}

RDEPENDS_${PN} = "\
  python-distutils \
"

BBCLASSEXTEND = "native"
