# Welcome
In this project I experiment with the Yocto Project to build custom Linux and services for my raspberrypi5. In this project simple C service blinks the red PWR led of the raspberrypi5 based on the current temperature of the CPU.

## Learning outcomes

* ✅How to build custom Linux for target Raspberry Pi 5 architecture.
* ✅How to autoconfigure wifi connection and ssh server for testing.
* ✅How to deploy C based service using systemd service manager.
* ✅How to decouple Yocto build pipeline and C source code.

## Next steps

* 🪲Investigate better debugging techniques. Currently the C software is debugged using `printf` after deployment to the raspberrypi5.
* 💻Better local development. Implementing locally on my laptop and cross-compiling with Yocto for the raspberrypi5 was difficult due to differences in the hardware and software on the platforms.

### Will this work on your machine?

Probably not, but for my future self here are the steps I took to build the project with Yocto:

1) clone yocto, poky, and meta-raspberrypi

2) edit bblayers.conf:

```
BBLAYERS ?= " \
  /home/<user>/yocto/poky/meta \
  /home/<user>/yocto/poky/meta-poky \
  /home/<user>/yocto/poky/meta-yocto-bsp \
  /home/<user>/yocto/meta-raspberrypi \
  /home/<user>/yocto/meta-tempmonitor \
  /home/<user>/yocto/meta-openembedded/meta-oe \
  "
```

2) edit local.conf to include:

Find the line MACHINE ??= "qemux86-64"
Change it to: MACHINE ?= "raspberrypi5"

Add this to the file:

```
LICENSE_FLAGS_ACCEPTED += "synaptics-killswitch"
DISTRO_FEATURES:append = " systemd"
DISTRO_FEATURES_BACKFILL_CONSIDERED += "sysvinit"
VIRTUAL-RUNTIME_init_manager = "systemd"
VIRTUAL-RUNTIME_initscripts = "systemd-compat-units"
INIT_MANAGER = "systemd"
```

3) add auto wifi create file:
`/meta-tempmonitor/recipes-app/temp-monitor/files/wpa_supplicant-wlan0.conf`

add the following content and replace the wifi ssid and password:
```
ctrl_interface=/var/run/wpa_supplicant
ctrl_interface_group=0
update_config=1

network={
    ssid="your-wifi-ssid"
    psk="your-wifi-password"
    key_mgmt=WPA-PSK
  }
```

4) copy the `meta-tempmonitor/` folder to `/home/<user>/yocto` and build it: 

NOTE: my original idea was to make it a symlink but that didn't work well.

```
cd /home/<user>/yocto/poky

```
```
source oe-init-build-env
```

```
bitbake-layers add-layer /path/to/meta-tempmonitor
```

To confirm:

```
bitbake-layers show
```

```
bitbake temp-monitor-image
```

5) once that is done flash the image to the MicroSD card:

```
cd /home/<user>/yocto/poky/build/tmp/deploy/images/raspberrypi5
```

```
ls *.wic.bz2
```

Note the file

```
bunzip2 -f temp-monitor-image-raspberrypi5.rootfs-<timestamp>.wic.bz2
```

Find the MicroSD and flash:


```
lsblk
```

```
sudo dd if=temp-monitor-image-raspberrypi5.rootfs-<timestamp>.wic of=/dev/<device> bs=4M status=progress conv=fsync
```

Now plug in the MicroSD to the raspberrypi5 and after a while the red PWR led should start blinking (done via the C code), which indicates that it is working.
