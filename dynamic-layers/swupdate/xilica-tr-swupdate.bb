DESCRIPTION = "SWUpdate-able base image for Xilica Testarossa Product line"
SUMMARY = "This image adds swupdate (provided by meta-variscite-sdk-imx) on top of the xilica-tr-base.
  - https://github.com/sbabic/swupdate
  - https://variwiki.com/index.php?title=SWUpdate_Guide&release=mx8mp-yocto-scarthgap-6.6.23_2.0.0-v1.1
  - example recipe camem from meta-variscite-sdk-imx/dynamic-layers/swupdate/var-image-swupdate.bb
"
# TODO: choose and specify LICENSE

require "recipes-xilica/images/xilica-tr-base.bb"

CORE_IMAGE_EXTRA_INSTALL += " \
	swupdate \
	swupdate-www \
	kernel-image \
	kernel-devicetree \
"

QBSP_IMAGE_CONTENT = ""
IMAGE_FSTYPES = "tar.zst"
