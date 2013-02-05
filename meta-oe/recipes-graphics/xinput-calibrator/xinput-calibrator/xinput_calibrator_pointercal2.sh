#!/bin/sh
###############################################################################
#
# Script to make the changes permanent. For each device a single configuration
# is stored in /etc/X11/xorg.conf.d/
#
# The first parameter is either
#   - the the device name in sysfs e.g 'event0' (when called from systemd) - or
#   - 'xdg-autostart' when called from /etc/xdg/autostart/*.desktop - or
#   - empty when called for manual recalibration
#
# initial version by Andreas Müller <schnitzeltony@googlemail.com>, 2013-02-05
#
###############################################################################


# Uncomment the following line to reduce log output
#debug='false &&'

# xserver might not be up yet or has yet not detected touchsreen device yet.
# To work around we try several times. If xserver+device are not up within
# <max_try_systemd>seconds something is wrong
max_try_systemd=30

# xserver might not have detected devices yet. To work around wait for
# <max_delay_autostart>seconds
max_delay_autostart=10


BINARY='xinput_calibrator'
CALFILE=
TOUCHNAME=
CMDOPTION=


set_device_vars() {
	DEVICEID=`echo $TOUCHNAME | tr -s ' -/\\|\t\n\r' '_'`
	CALFILE="/etc/X11/xorg.conf.d/99-$DEVICEID.conf"
	CMDOPTION="--output-filename $CALFILE"
}


extract_next_dev() {
	DEVICENO="$((DEVICENO+1))"
	ENDPOS="$((DEVICENO*2))"
	TOUCHNAME=`echo $DEVICELIST | cut -s -d'"' -f$ENDPOS`
}


if [ x$1 != x ]; then
	# called by systemd for a specific device - no recalibration
	if [ x$1 != xxdg-autostart ]; then
		$debug echo "----- $0 started by systemd for device $1 -----"

		# TBD?: unique ID for multiple touchscreens of same type
		# TBD?: DISPLAY != default
		export DISPLAY=:0
		TOUCHNAME=`cat /sys/class/input/$1/device/name`
		$debug echo "Device: '$TOUCHNAME'"
		set_device_vars
		$debug echo "Checking for stored calibration in $CALFILE"
		if grep "\"$TOUCHNAME\"" $CALFILE ; then
			echo "Using calibration data stored in $CALFILE"
			exit 0
		fi
	# called by: /etc/xdg/autostart/*.desktop check all devices - no recalibration
	else
		$debug echo "----- $0 started by autostart ----- "
		$debug echo `date -u` ": delay ${max_delay_autostart}s ..."
		sleep $max_delay_autostart

		DEVICELIST=`$BINARY --list`
		$debug $BINARY --list
		# no devices?
		if [ "x`echo $DEVICELIST | grep Device`" = "x" ]; then
			exit 0
		fi
		DEVICECOUNT=`$BINARY --list | wc -l`
		# for multiple devices the first uncalibrated is calibrated (TBD?)
		DEVICENO=0
		while [ "$DEVICENO" -ne "$DEVICECOUNT" ]; do
			extract_next_dev
			set_device_vars
			$debug echo "Checking for stored calibration in $CALFILE"
			if grep "\"$TOUCHNAME\"" $CALFILE ; then
				$debug echo "Using calibration data stored in $CALFILE"
				TOUCHNAME=
			else
				$debug echo "No calibration data found for '$TOUCHNAME'"
				break
			fi
		done
		if [ "x$TOUCHNAME" = "x" ]; then
			echo 'All devices calibrated - exiting.'
			exit 0
		fi
	fi
# called manual: check all available devices - force recalibration
else
	echo "----- $0 started manually -----"
	echo "auto detect touchscreen devices..."
	# we are in a terminal so max output below is OK
	debug=
	DEVICELIST=`$BINARY --list`
	$BINARY --list
	# no devices?
	if [ "x`echo $DEVICELIST | grep Device`" = "x" ]; then
		exit 0
	fi
	DEVICECOUNT=`$BINARY --list | wc -l`
	DEVICENO=0
	# one device
	if [ "$DEVICECOUNT" -eq "1" ]; then
		extract_next_dev
	# multiple devices
	else
		echo 'Multiple devices were found. Select the one to calibrate:'
		while [ "$DEVICENO" -ne "$DEVICECOUNT" ]; do
			extract_next_dev
			echo -n "Calibrate '$TOUCHNAME' <y>? "
			read YESNO
			if [ "x$YESNO" = "xy" ]; then
				break
			else
				TOUCHNAME=
			fi
		done
		if [ "x$TOUCHNAME" = "x" ]; then
			echo 'No device selected - exiting without calibration.'
			exit 0
		fi
	fi
	set_device_vars
fi


# wait for xserver to calibrate
i=1
while [ "$i" -ne "$max_try_systemd" ]; do
	$debug echo `date -u` ": starting calibration"
	$BINARY --device "$TOUCHNAME" --no-timeout --output-type xorg.conf.d $CMDOPTION
	if [ $? = 0 ]; then
		exit 0
	fi
	sleep 1
	i="$((i+1))"
done

# when reaching here something went wrong
echo "Could not calibrate '$TOUCHNAME'"
exit 1
