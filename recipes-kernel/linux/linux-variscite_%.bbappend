SUMMARY = "Kernel modifications required for Xilica Testarossa based on var-som-imx8mp"
DESCRIPTION = "Customizes the Linux kernel for Xilica Testarossa product line, based on var-som-imx8mp. \
The kernel is based on forks provided by the product's SoM and SoC vendors: Variscite and NXP."
HOMEPAGE = "https://bitbucket.org/xilica/linux-imx"

#   - Local file vars (FILESEXTRAPATHS,SRC_URI) are unnecessary bc
#     we now use a fork of the kernel source instead of patches.
#   - SRCREV = "${AUTOREV}" is broken, due to an unresolved git ls-remote/auth issue:
#     - see https://github.com/openembedded/bitbake/pull/16#issuecomment-2557304521

# Public Fork for variscite support reproducing issues
KERNEL_SRC = "git://github.com/timblaktu/linux-imx;protocol=https"
#KERNEL_SRC = "git://x-token-auth@bitbucket.org/xilica/linux-imx;protocol=https"

SRCBRANCH = "xilica-tr-scarthgap"
SRC_URI = "${KERNEL_SRC};branch=${SRCBRANCH}"
SRCREV = "a2f517efcb4208aba266dd06828cc60eaeba0454"

LINUX_VERSION_EXTENSION = "-xilica+git${SRCPV}"
