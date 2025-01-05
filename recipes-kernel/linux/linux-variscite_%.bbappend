# Kernel recipe modifications required for Xilica Testarossa
#
#   - Local file vars (FILESEXTRAPATHS,SRC_URI) are unnecessary bc
#     we now use a fork of the kernel source instead of patches.
#   - SRCREV = "${AUTOREV}" is broken, due to an unresolved git bug:
#     - see https://github.com/openembedded/bitbake/pull/16#issuecomment-2557304521

KERNEL_SRC = "git://x-token-auth@bitbucket.org/xilica/linux-imx;protocol=https"
SRCBRANCH = "xilica-tr-scarthgap"
SRCREV = "3780c36bb2f2cc7f2b2b28f5ce2827ad1132d4d9"
LINUX_VERSION_EXTENSION:append = "xilica+git${SRCPV}"
