DESCRIPTION = "A fast and lightweight IDE"
HOMEPAGE = "http://plugins.geany.org/"
LICENSE = "GPLv2"
LICENSE_${PN}-devhelp = "GPLv3"
LICENSE_${PN}-geanydoc = "GPLv3"
LICENSE_${PN}-geanygendoc = "GPLv3"
LICENSE_${PN}-geanyprj = "GPLv3"
LICENSE_${PN}-webhelper = "GPLv3"
LIC_FILES_CHKSUM = " \
	file://addons/COPYING;md5=4325afd396febcb659c36b49533135d4 \
	file://codenav/COPYING;md5=751419260aa954499f7abaabaa882bbe \
	file://debugger/COPYING;md5=4325afd396febcb659c36b49533135d4 \
	file://devhelp/COPYING;md5=d32239bcb673463ab874e80d47fae504 \
	file://geanydoc/COPYING;md5=d32239bcb673463ab874e80d47fae504 \
	file://geanyextrasel/COPYING;md5=c107cf754550e65755c42985a5d4e9c9 \
	file://geanygendoc/COPYING;md5=d32239bcb673463ab874e80d47fae504 \
	file://geanyinsertnum/COPYING;md5=c107cf754550e65755c42985a5d4e9c9 \
	file://geanylatex/COPYING;md5=c107cf754550e65755c42985a5d4e9c9 \
	file://geanylipsum/COPYING;md5=4325afd396febcb659c36b49533135d4 \
	file://geanylua/COPYING;md5=4325afd396febcb659c36b49533135d4 \
	file://geanymacro/COPYING;md5=c107cf754550e65755c42985a5d4e9c9 \
	file://geanyminiscript/COPYING;md5=4325afd396febcb659c36b49533135d4 \
	file://geanynumberedbookmarks/COPYING;md5=c107cf754550e65755c42985a5d4e9c9 \
	file://geanyprj/COPYING;md5=d32239bcb673463ab874e80d47fae504 \
	file://geanysendmail/COPYING;md5=c107cf754550e65755c42985a5d4e9c9 \
	file://geanyvc/COPYING;md5=c107cf754550e65755c42985a5d4e9c9 \
	file://geniuspaste/COPYING;md5=bfc203269f8862ebfc1198cdc809a95a \
	file://multiterm/COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263 \
	file://shiftcolumn/COPYING;md5=751419260aa954499f7abaabaa882bbe \
	file://spellcheck/COPYING;md5=4325afd396febcb659c36b49533135d4 \
	file://tableconvert/COPYING;md5=6753686878d090a1f3f9445661d3dfbc \
	file://updatechecker/COPYING;md5=4325afd396febcb659c36b49533135d4 \
	file://webhelper/COPYING;md5=d32239bcb673463ab874e80d47fae504 \
	file://xmlsnippets/COPYING;md5=4325afd396febcb659c36b49533135d4 \
"
DEPENDS = "geany lua"

inherit autotools pkgconfig

SRC_URI = "http://plugins.geany.org/${PN}/${PN}-${PV}.tar.bz2"
SRC_URI[md5sum] = "91f32224ab7a1eb8d05bf6cde5743427"
SRC_URI[sha256sum] = "5c13ac1e173284462248ed2faae389a72364c5aa1538de628d2aaa7d286e244b"

FILES_${PN}-dbg += "${libdir}/geany/.debug ${libdir}/${PN}/geanylua/.debug"
FILES_${PN}-dev += "${libdir}/geany/*.la ${libdir}/${PN}/*/*.la"

PLUGINS += "${PN}-addons"
FILES_${PN}-addons = "${libdir}/geany/addons.so"
RDEPENDS_${PN}-addons = "${PN}"

PLUGINS += "${PN}-codenav"
FILES_${PN}-codenav = "${libdir}/geany/codenav.so"
RDEPENDS_${PN}-codenav = "${PN}"

PLUGINS += "${PN}-debugger"
FILES_${PN}-debugger = "${libdir}/geany/debugger.so ${datadir}/${PN}/debugger"
RDEPENDS_${PN}-debugger = "${PN}"

PLUGINS += "${PN}-devhelp"
FILES_${PN}-devhelp = "${libdir}/geany/devhelp.so ${datadir}/${PN}/devhelp"
RDEPENDS_${PN}-devhelp = "${PN}"

PLUGINS += "${PN}-geanydoc"
FILES_${PN}-geanydoc = "${libdir}/geany/geanydoc.so"
RDEPENDS_${PN}-geanydoc = "${PN}"

PLUGINS += "${PN}-geanyextrasel"
FILES_${PN}-geanyextrasel = "${libdir}/geany/geanyextrasel.so"
RDEPENDS_${PN}-geanyextrasel = "${PN}"

#PLUGINS += "${PN}-geanygendoc"
#FILES_${PN}-geanygendoc = "${libdir}/geany/geanygendoc.so"
#RDEPENDS_${PN}-geanygendoc = "${PN}"

PLUGINS += "${PN}-geanyinsertnum"
FILES_${PN}-geanyinsertnum = "${libdir}/geany/geanyinsertnum.so"
RDEPENDS_${PN}-geanyinsertnum = "${PN}"

PLUGINS += "${PN}-geanylatex"
FILES_${PN}-geanylatex = "${libdir}/geany/geanylatex.so"
RDEPENDS_${PN}-geanylatex = "${PN}"

PLUGINS += "${PN}-geanylipsum"
FILES_${PN}-geanylipsum = "${libdir}/geany/geanylipsum.so"
RDEPENDS_${PN}-geanylipsum = "${PN}"

PLUGINS += "${PN}-geanylua"
FILES_${PN}-geanylua = "${libdir}/geany/geanylua.so ${libdir}/${PN}/geanylua/*.so ${datadir}/${PN}/geanylua"
RDEPENDS_${PN}-geanylua = "${PN}"

PLUGINS += "${PN}-geanymacro"
FILES_${PN}-geanymacro = "${libdir}/geany/geanymacro.so"
RDEPENDS_${PN}-geanymacro = "${PN}"

PLUGINS += "${PN}-geanyminiscript"
FILES_${PN}-geanyminiscript = "${libdir}/geany/geanyminiscript.so"
RDEPENDS_${PN}-geanyminiscript = "${PN}"

PLUGINS += "${PN}-geanynumberedbookmarks"
FILES_${PN}-geanynumberedbookmarks = "${libdir}/geany/geanynumberedbookmarks.so"
RDEPENDS_${PN}-geanynumberedbookmarks = "${PN}"

PLUGINS += "${PN}-geanyprj"
FILES_${PN}-geanyprj = "${libdir}/geany/geanyprj.so"
RDEPENDS_${PN}-geanyprj = "${PN}"

PLUGINS += "${PN}-geanysendmail"
FILES_${PN}-geanysendmail = "${libdir}/geany/geanysendmail.so"
RDEPENDS_${PN}-geanysendmail = "${PN}"

PLUGINS += "${PN}-geanyvc"
FILES_${PN}-geanyvc = "${libdir}/geany/geanyvc.so"
RDEPENDS_${PN}-geanyvc = "${PN}"

PLUGINS += "${PN}-geniuspaste"
FILES_${PN}-geniuspaste = "${libdir}/geany/geniuspaste.so"
RDEPENDS_${PN}-geniuspaste = "${PN}"

PLUGINS += "${PN}-multiterm"
FILES_${PN}-multiterm = "${libdir}/geany/multiterm.so"
RDEPENDS_${PN}-multiterm = "${PN}"

PLUGINS += "${PN}-pretty-printer"
FILES_${PN}-pretty-printer = "${libdir}/geany/pretty-printer.so"
RDEPENDS_${PN}-pretty-printer = "${PN}"

PLUGINS += "${PN}-shiftcolumn"
FILES_${PN}-shiftcolumn = "${libdir}/geany/shiftcolumn.so"
RDEPENDS_${PN}-shiftcolumn = "${PN}"

PLUGINS += "${PN}-spellcheck"
FILES_${PN}-spellcheck = "${libdir}/geany/spellcheck.so"
RDEPENDS_${PN}-spellcheck = "${PN}"

PLUGINS += "${PN}-tableconvert"
FILES_${PN}-tableconvert = "${libdir}/geany/tableconvert.so"
RDEPENDS_${PN}-tableconvert = "${PN}"

PLUGINS += "${PN}-treebrowser"
FILES_${PN}-treebrowser = "${libdir}/geany/treebrowser.so"
RDEPENDS_${PN}-treebrowser = "${PN}"

PLUGINS += "${PN}-updatechecker"
FILES_${PN}-updatechecker = "${libdir}/geany/updatechecker.so"
RDEPENDS_${PN}-updatechecker = "${PN}"

PLUGINS += "${PN}-webhelper"
FILES_${PN}-webhelper = "${libdir}/geany/webhelper.so"
RDEPENDS_${PN}-webhelper = "${PN}"

PLUGINS += "${PN}-xmlsnippets"
FILES_${PN}-xmlsnippets = "${libdir}/geany/xmlsnippets.so"
RDEPENDS_${PN}-xmlsnippets = "${PN}"

PACKAGES =+ "${PLUGINS}"
RDEPENDS_${PN} = "${PLUGINS}"
ALLOW_EMPTY_${PN} = "1"
