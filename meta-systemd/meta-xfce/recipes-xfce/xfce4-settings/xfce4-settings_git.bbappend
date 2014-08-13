PACKAGECONFIG = " \
    datetime-setter \
    ${@base_contains('DISTRO_FEATURES','alsa','sound-setter', base_contains('DISTRO_FEATURES','pulseaudio','sound-setter','',d),d)} \
"
