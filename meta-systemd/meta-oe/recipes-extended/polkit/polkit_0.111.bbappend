# systemd is not yet a distro feature it came in when systemd moved to oe-core
# so we need to override PACKAGECONFIG
PACKAGECONFIG = "systemd pam"
