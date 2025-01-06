# Test Automation for Xilica Testarossa images and devices
#
# This module adds support to testimage.bbclass for deploying images
# and running tests on a Xilica Testarossa device.
#

import os
import bb
import time
import subprocess
import sys
import pexpect

import oeqa.controllers.controllerimage

class XilicaTestarossaTarget(oeqa.controllers.controllerimage.ControllerImageHardwareTarget):
    '''
    '''
    dtbs = { 'imx8mp-var-som-1.x-symphony.dtb' }

    @classmethod
    def get_extra_files(self):
        return list(self.dtbs.keys())

    def __init__(self, d):
        '''
        '''
        super(XilicaTestarossaTarget, self).__init__(d)

        self.image_fstype = self.get_image_fstype(d)
        self.deploy_cmds = [
                'mkdir -p /mnt/testrootfs',
                'mount -L testrootfs /mnt/testrootfs',
                'rm -rf /mnt/testrootfs/*',
                'tar xvf ~/test-rootfs.%s -C /mnt/testrootfs' % self.image_fstype,
                '[ -e /mnt/testrootfs/boot/uImage ] || [ -L /mnt/testrootfs/boot/uImage ] || cp ~/test-kernel /mnt/testrootfs/boot/uImage',
                ]

        for _, dtbfn in self.dtbs.iteritems():
            # Kernel and dtb files may not be in the image, so copy them if not
            self.deploy_cmds.append('[ -e /mnt/testrootfs/boot/{0} ] || cp ~/{0} /mnt/testrootfs/boot/'.format(dtbfn))

        if not self.serialcontrol_cmd:
            bb.fatal("This TEST_TARGET needs a TEST_SERIALCONTROL_CMD defined in local.conf.")

    def _deploy(self):
        '''
        '''
        self.controller.run("umount /boot; umount /mnt/testrootfs;")
        self.controller.ignore_status = False
        # Kernel and dtb files may not be in the image, so copy them just in case
        self.controller.copy_to(self.kernel, "~/test-kernel")
        kernelpath = os.path.dirname(self.kernel)
        for dtborig, dtbfn in self.dtbs.iteritems():
            dtbfile = os.path.join(kernelpath, dtborig)
            if os.path.exists(dtbfile):
                self.controller.copy_to(dtbfile, "~/%s" % dtbfn)
        self.controller.copy_to(self.rootfs, "~/test-rootfs.%s" % self.image_fstype)
        for cmd in self.deploy_cmds:
            self.controller.run(cmd)

    def _start(self, params=None):
        '''
        '''
        self.power_cycle(self.controller)
        try:
            serialconn = pexpect.spawn(self.serialcontrol_cmd, env=self.origenv, logfile=sys.stdout)
            # Could wait for "U-Boot" here
            serialconn.expect("NAND:")
            serialconn.expect("MMC:")
            serialconn.sendline("a")
            serialconn.expect("U-Boot#")
            serialconn.sendline("setenv bootpart 0:3")
            serialconn.expect("U-Boot#")
            serialconn.sendline("setenv mmcroot /dev/mmcblk0p3 ro")
            serialconn.expect("U-Boot#")
            serialconn.sendline("boot")
            serialconn.expect("login:", timeout=120)
            serialconn.close()
        except pexpect.ExceptionPexpect as e:
            bb.fatal('Serial interaction failed: %s' % str(e))

    def _wait_until_booted(self):
        '''
        '''
        try:
            serialconn = pexpect.spawn(self.serialcontrol_cmd, env=self.origenv, logfile=sys.stdout)
            serialconn.expect("login:", timeout=120)
            serialconn.close()
        except pexpect.ExceptionPexpect as e:
            bb.fatal('Serial interaction failed: %s' % str(e))
