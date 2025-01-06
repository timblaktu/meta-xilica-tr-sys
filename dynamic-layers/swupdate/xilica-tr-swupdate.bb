DESCRIPTION = "SWUpdate-able base image for Xilica Testarossa Product line"
SUMMARY = "This image adds swupdate, provided by meta-variscite-sdk-imx, on top of the xilica-tr-base."
LICENSE = "MIT"

require "recipes-xilica/images/xilica-tr-base.bb"

CORE_IMAGE_EXTRA_INSTALL += " \
	swupdate \
	swupdate-www \
	kernel-image \
	kernel-devicetree \
"

QBSP_IMAGE_CONTENT = ""
IMAGE_FSTYPES = "tar.zst"
