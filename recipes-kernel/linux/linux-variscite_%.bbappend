KERNEL_SRC = "git://x-token-auth@bitbucket.org/xilica/linux-imx;protocol=https"
SRCBRANCH = "xilica-tr-scarthgap"
# SRCREV = "${AUTOREV}" is broken, see https://github.com/openembedded/bitbake/pull/16#issuecomment-2557304521
SRCREV = "30b42820cd05ede7cce425386e676598d5c43235"
# PV = "${LINUX_VERSION}+git${SRCPV}"

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

