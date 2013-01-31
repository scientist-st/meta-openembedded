# look for files in the layer first
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

inherit systemd

PRINC := "${@int(PRINC) + 2}"

SYSTEMD_PACKAGES = "dhcp-server-systemd dhcp-relay-systemd dhcp-client-systemd"
SYSTEMD_SERVICE_dhcp-server-systemd = "dhcpd.service"
SYSTEMD_SERVICE_dhcp-relay-systemd = "dhcrelay.service"
SYSTEMD_SERVICE_dhcp-client-systemd = "dhclient.service"
SYSTEMD_AUTO_ENABLE_dhcp-client-systemd = "disable"

SRC_URI += "file://dhcpd.service \
            file://dhclient.service \
            file://dhcrelay.service \
           "
