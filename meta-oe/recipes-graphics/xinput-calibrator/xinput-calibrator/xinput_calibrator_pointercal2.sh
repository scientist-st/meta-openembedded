#!/bin/sh
# Script to make the changes permanent. For each device a single configuration
# is stored in /etc/X11/xorg.conf.d. The first parameter is the the device name
# in sysfs e.g 'event0'. When called without param (for manual recalibration)
# it auto detects the touchscreens found.
#
# initial version by Andreas Müller <schnitzeltony@googlemail.com>, 2013-01-22
BINARY='xinput_calibrator'
CALFILE=
TOUCHNAME=
CMDOPTION=

export DISPLAY=:0

set_device_vars() {
	DEVICEID=`echo $TOUCHNAME | tr -s ' -/\\|\t\n\r' '_'`
	CALFILE="/etc/X11/xorg.conf.d/99-$DEVICEID.conf"
	CMDOPTION="--output-filename $CALFILE"
}

# called for a specific device - no recalibration
if [ x$1 != x ]; then
	echo "----- $0 started for $1 -----"

	# TBD?: unique ID for multiple touchscreens of same type
	TOUCHNAME=`cat /sys/class/input/$1/device/name`
	echo "Device: '$TOUCHNAME'"
	set_device_vars
	echo "Checking for stored calibration in $CALFILE"
	if grep "\"$TOUCHNAME\"" $CALFILE ; then
		echo "Using calibration data stored in $CALFILE"
		exit 0
	fi
# check all available devices - force recalibration
else
	echo "$0 started"
	echo "auto detecting touchscreen device..."
	DEVICELIST=`$BINARY --list`
	$BINARY --list
	# no devices?
	if [ "x`echo $DEVICELIST | grep Device`" = "x" ]; then
		exit 0
	fi
	DEVICECOUNT=`$BINARY --list | wc -l`
	# one device
	if [ "$DEVICECOUNT" -eq "1" ]; then
		TOUCHNAME=`echo $DEVICELIST | cut -s -d'"' -f2`

	# multiple devices
	else
		echo 'Multiple devices were found. Select the one to calibrate:'
		DEVICENO=0
		while [ "$DEVICENO" -ne "$DEVICECOUNT" ]; do
			DEVICENO="$((DEVICENO+1))"
			ENDPOS="$((DEVICENO*2))"
			TOUCHNAME=`echo $DEVICELIST | cut -s -d'"' -f$ENDPOS`
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


# under many conditions the xserver is not yet up or has not detected
# touchsreen device yet. To work around we try several times. If
# xserver+device are not up within 50s something goes wrong
max=50
i=1
while [ "$i" -ne "$max" ]; do
	echo `date -u` ": starting calibration"
	$BINARY --device "$TOUCHNAME" --no-timeout --output-type xorg.conf.d $CMDOPTION
	if [ $? = 0 ]; then
		echo "$0 finished with success"
		exit 0
	fi
	sleep 1
	i="$((i+1))"
done

# when reaching here something went wrong
echo "$0 finished without success"
exit 1
