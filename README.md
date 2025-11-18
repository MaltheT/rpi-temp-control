

# how to build yocto image

1) clone yocto, poky, and meta-raspberrypi

2) edit local.conf to include:

LICENSE_FLAGS_ACCEPTED += "synaptics-killswitch"

Find the line MACHINE ??= "qemux86-64"
Change it to: MACHINE ?= "raspberrypi5"

3) create a symlik to ~/your/yocto/ with the meta-tempmonitor in this git

TODO: repeate these steps to double check and flesh out!

TODO: add section about autoconfigure wifi


TO add auto wifi create file:
/meta-tempmonitor/recipes-app/temp-monitor/files/wpa_supplicant-wlan0.conf'

add the following content and replace the wifi ssid and passord:

```
```
```
```
ctrl_interface=/var/run/wpa_supplicant
ctrl_interface_group=0
update_config=1

```
network={
    ssid="your-wifi-ssid"
    psk="your-wifi-password"
    key_mgmt=WPA-PSK
  }
```
