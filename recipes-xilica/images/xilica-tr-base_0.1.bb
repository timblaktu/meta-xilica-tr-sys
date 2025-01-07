DESCRIPTION = "Base image for Xilica Testarossa Product line"
SUMMARY = "Xilica Testarossa base image."
LICENSE = "MIT"

require recipes-extended/images/core-image-full-cmdline.bb

inherit core-image features_check image-buildinfo

REQUIRED_DISTRO_FEATURES += " alsa nfs systemd usbgadget usbhost virtualization zeroconf"

# Add custom field to /etc/buildinfo
#   - https://docs.yoctoproject.org/5.0.5/ref-manual/classes.html#image-buildinfo
#   - XILICA_TR_BUILDSTAMP was passed to BB env via BB_ENV_PASSTHROUGH_ADDITIONS
#
IMAGE_BUILDINFO_VARS:append = " XILICA_TR_BUILDSTAMP"

# Image Features
#   https://docs.yoctoproject.org/5.0.5/ref-manual/features.html#image-features
IMAGE_FEATURES += " \
    hwcodecs \
    debug-tweaks \
    nfs-client \
    serial-autologin-root \
    tools-debug \
    tools-testapps \
"

CORE_IMAGE_EXTRA_INSTALL += " \
	packagegroup-imx-tools-audio \
	${@bb.utils.contains('DISTRO_FEATURES', 'systemd', 'systemd-analyze', '', d)} \
"

IMAGE_INSTALL:append = "\
    packagegroup-tr-base \
"
