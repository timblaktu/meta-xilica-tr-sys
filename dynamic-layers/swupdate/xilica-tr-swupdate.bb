# Derived from https://github.com/varigit/meta-variscite-sdk-imx/blob/scarthgap_6.6.23-2.0.0_var01/dynamic-layers/swupdate/var-image-swupdate.bb

DESCRIPTION = "SWUpdate-able base image for Xilica Testarossa Product line"
SUMMARY = "This image adds swupdate, provided by meta-variscite-sdk-imx, on top of the xilica-tr-base."
LICENSE = "MIT"

require "recipes-xilica/images/xilica-tr-base_0.1.bb"

CORE_IMAGE_EXTRA_INSTALL += " \
	swupdate \
	swupdate-www \
	kernel-image \
	kernel-devicetree \
"

QBSP_IMAGE_CONTENT = ""

# Due to the SWUpdate image will not fit the default NAND size.
# Removing default ubi creation for this image
IMAGE_FSTYPES:remove = "multiubi"
