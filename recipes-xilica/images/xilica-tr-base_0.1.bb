DESCRIPTION = "Base image for Xilica Testarossa Product line"
SUMMARY = "Xilica Testarossa base builds on top of a headless 'full-cmdline' base image with docker CE support."
LICENSE = "MIT"

# provided by meta-variscite-sdk-imx layer
require recipes-extended/images/core-image-full-cmdline.bb
# require dynamic-layers/virtualization/recipes-extended/images/var-image-docker-minimal.bb

# Add custom field to /etc/buildinfo
#   - https://docs.yoctoproject.org/5.0.5/ref-manual/classes.html#image-buildinfo
#   - XILICA_TR_BUILDSTAMP was passed to BB env via BB_ENV_PASSTHROUGH_ADDITIONS
#
inherit image-buildinfo
IMAGE_BUILDINFO_VARS:append = " XILICA_TR_BUILDSTAMP"

inherit core-image

# Image Features
#   https://docs.yoctoproject.org/5.0.5/ref-manual/features.html#image-features
IMAGE_FEATURES += " \
    ssh-server-openssh \
    hwcodecs \
    debug-tweaks \
    nfs-client \
    perf \
    serial-autologin-root \
    tools-debug \
    tools-testapps \
"

CORE_IMAGE_EXTRA_INSTALL += " \
	packagegroup-imx-tools-audio \
	packagegroup-fsl-tools-testapps \
	packagegroup-fsl-tools-benchmark \
	packagegroup-fsl-gstreamer1.0 \
	packagegroup-fsl-gstreamer1.0-full \
	${@bb.utils.contains('DISTRO_FEATURES', 'systemd', 'systemd-analyze', '', d)} \
"

IMAGE_INSTALL:append = "\
    packagegroup-tr-base \
    os-release \
    lsb-release \
"
