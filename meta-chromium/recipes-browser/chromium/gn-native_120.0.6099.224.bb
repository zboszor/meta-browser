# gn-native contains the GN binary used to configure Chromium.
# It is not released separately, and each Chromium release is only expected to
# work with the GN version provided with it.

require chromium.inc

inherit native

S = "${WORKDIR}/chromium-${PV}"

# bootstrap.py --no_clean hardcodes the build location to out_bootstrap.
# Omitting --no_clean causes the script to create a temporary directory with a
# random name outside the build directory, so we choose the lesser of the two
# evils.
B = "${S}/out_bootstrap"

DEPENDS = "clang-native ninja-native"

do_configure[noexec] = "1"
do_compile[noexec] = "1"

do_install() {
	install -d ${D}${bindir}
	install -m 0755 ${S}/buildtools/linux64/gn ${D}${bindir}/gn
}

INSANE_SKIP:${PN} += "already-stripped"
