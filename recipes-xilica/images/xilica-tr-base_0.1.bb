DESCRIPTION = "Base image for Xilica Testarossa Product line"
SUMMARY = "Xilica Testarossa base builds on top of a headless 'full-cmdline' base image with docker CE support.
  - provided by meta-variscite-sdk-imx layer"

require dynamic-layers/virtualization/recipes-extended/images/var-image-docker-minimal.bb

# Add custom field to /etc/buildinfo
#   - https://docs.yoctoproject.org/5.0.5/ref-manual/classes.html#image-buildinfo
#   - XILICA_TR_BUILDSTAMP was passed to BB env via BB_ENV_PASSTHROUGH_ADDITIONS
#
inherit image-buildinfo
IMAGE_BUILDINFO_VARS:append = " XILICA_TR_BUILDSTAMP"

#IMAGE_FEATURES:append = ""

IMAGE_INSTALL:append = "\
    packagegroup-tr-base \
    os-release \
    lsb-release \
"
