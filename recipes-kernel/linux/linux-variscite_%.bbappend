KERNEL_SRC = "git://x-token-auth@bitbucket.org/xilica/linux-imx;protocol=https"
SRCBRANCH = "xilica-tr-scarthgap"
SRCREV = "${AUTOREV}"
PV = "${LINUX_VERSION}+git${SRCPV}"

# Local file settings are no longer needed since we are using our
# own fork to manage changes instead of patches.
#
# FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"
# SRC_URI += " \
#     file://devtool-fragment.cfg \
#     file://0001-added-ak4558-codec.patch \
#     file://0002-xtr-dts-patch-for-var-mickledore.patch \
#     file://0002-xtr-dts-patch-for-var-scarthgap.patch \
# "

