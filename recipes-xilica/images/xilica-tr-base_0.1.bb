DESCRIPTION = "Base image for Xilica Testarossa Product line"

SUMMARY = "\
Modeled after core-image-full-cmdline, using only what is needed. \
  - poky/meta/recipes-extended/images/core-image-full-cmdline.bb \
  - poky/meta/recipes-extended/packagegroups/packagegroup-core-full-cmdline.bb \
"

# from poky
require recipes-extended/images/core-image-full-cmdline.bb
# from meta-openembedded/meta-oe
#require recipes-core/packagegroups/packagegroup-meta-oe.bb

# Add custom field to /etc/buildinfo
# https://docs.yoctoproject.org/5.0.5/ref-manual/classes.html#image-buildinfo
inherit image-buildinfo
# passed into BB env via BB_ENV_PASSTHROUGH_ADDITIONS
IMAGE_BUILDINFO_VARS:append = " XILICA_TR_BUILDSTAMP"

#IMAGE_FEATURES:append = ""

IMAGE_INSTALL:append = "\
    packagegroup-tr-base \
    os-release \
    lsb-release \
    "

#nfs-installer \
#packagegroup-meta-oe-multimedia \
