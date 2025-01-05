# Kernel recipe modifications required for Xilica Testarossa
#
#   - Local file vars (FILESEXTRAPATHS,SRC_URI) are unnecessary bc
#     we now use a fork of the kernel source instead of patches.
#   - SRCREV = "${AUTOREV}" is broken, due to an unresolved git bug:
#     - see https://github.com/openembedded/bitbake/pull/16#issuecomment-2557304521

KERNEL_SRC = "git://x-token-auth@bitbucket.org/xilica/linux-imx;protocol=https"
SRCBRANCH = "xilica-tr-scarthgap"
SRCREV = "a2f517efcb4208aba266dd06828cc60eaeba0454"
LINUX_VERSION_EXTENSION:append = "xilica+git${SRCPV}"
