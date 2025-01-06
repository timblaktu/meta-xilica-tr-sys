#
# Good example of a packagegroup: meta/recipes-core/packagegroups/packagegroup-base.bb
# Also this: https://docs.yoctoproject.org/5.0.5/dev-manual/customizing-images.html#customizing-images-using-custom-package-groups
#
SUMMARY = "Recipe for customizing package installation for the xilica-tr-base image."
PR = "r1"

#
# packages which content depend on MACHINE_FEATURES need to be MACHINE_ARCH
#
PACKAGE_ARCH = "${MACHINE_ARCH}"

#
# sets appropriate default values and automatically adds -dev, -dbg, and -ptest 
# complementary packages for each package specified in the PACKAGES statement
#
inherit packagegroup

#
# For each package you specify in PACKAGES, you can use RDEPENDS and RRECOMMENDS 
# entries to provide a list of packages the parent task package should contain.
#
PACKAGES = "\
    packagegroup-tr-base \
    "

#
# This packagegroup contains everything that xilica-tr-base image needs on top 
# of the other packagegroups included in IMAGE_INSTALL/CORE_IMAGE_EXTRA_INSTALL.
#
RDEPENDS:packagegroup-tr-base = "\
    vim-tiny \
    tmux \
    docker-moby \
    python3-docker-compose \
    alsa-utils \
    curl \
    dosfstools \
    fio \
    i2c-tools \
    imx-kobs \
    os-release \
    lsb-release \
    ldd \
    memtester \
    mtd-utils \
    mtd-utils-ubifs \
    "
RRECOMMENDS:packagegroup-tr-base = "\
    "
