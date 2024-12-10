FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

SRC_URI += " file://devtool-fragment.cfg file://0001-added-ak4558-codec.patch"
# SRC_URI:append = "file://0002-xtr-dts-patch-for-var-mickledore.patch"
SRC_URI += " file://0002-xtr-dts-patch-for-var-scarthgap.patch"

