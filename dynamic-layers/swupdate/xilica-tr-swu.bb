DESCRIPTION = "Compound SWUpdate image (.swu) using xilica-tr-swupdate"
SUMMARY = "This recipe creates a swu archive containing xilica-tr-swupdate and friends"
LICENSE = "MIT"
SECTION = ""

# Note: sw-description is mandatory
SRC_URI = " \
	file://sw-description \
	file://update.sh \
"

inherit swupdate

# Set to primary image packaged in .swu file
VAR_SWUPDATE_TARGET_IMAGE ??= "xilica-tr-swupdate"
# Set to primary image format/extension (i.e. .tar.gz, .tar.zst, etc.)
VAR_SWUPDATE_TARGET_IMAGE_FSTYPE = ".tar.zst"

# Expression to resolve packaged filename based
# on how swupdate packages images in order to update sw-description file
VAR_SWUPDATE_TARGET_IMAGE_FILE = "${VAR_SWUPDATE_TARGET_IMAGE}-${MACHINE}.rootfs${VAR_SWUPDATE_TARGET_IMAGE_FSTYPE}"

# List of Yocto images that contains a root filesystem
# it will be ensured they are built before creating swupdate image
IMAGE_DEPENDS = "${VAR_SWUPDATE_TARGET_IMAGE}"

# List of images that will be part of the compound image
# the list can have any binaries - images must be in the DEPLOY directory
SWUPDATE_IMAGES = " \
	${VAR_SWUPDATE_TARGET_IMAGE_FILE} \
"

# Images can have multiple formats - define which image must be
# taken to be put in the compound image
# This anonymous python function is equivalent to:
# SWUPDATE_IMAGES_FSTYPES["${VAR_SWUPDATE_TARGET_IMAGE}"] = "${VAR_SWUPDATE_TARGET_IMAGE_FSTYPE}"
# however it must be a python function as Bitbake does not expand variables as flag names.
python() {
    d.setVarFlag("SWUPDATE_IMAGES_FSTYPES",
        "VAR_SWUPDATE_TARGET_IMAGE", d.getVar("VAR_SWUPDATE_TARGET_IMAGE_FSTYPE"))
}
