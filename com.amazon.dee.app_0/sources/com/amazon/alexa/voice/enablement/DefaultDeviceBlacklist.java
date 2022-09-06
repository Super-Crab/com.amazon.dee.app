package com.amazon.alexa.voice.enablement;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
/* loaded from: classes11.dex */
final class DefaultDeviceBlacklist implements DeviceBlacklist {
    @Override // com.amazon.alexa.voice.enablement.DeviceBlacklist
    public Set<BlacklistableDevice> getBlacklistedDevices() {
        return new HashSet(Arrays.asList(BlacklistableDevice.instance("OUKITEL", "C3"), BlacklistableDevice.instance("Micromax", "Micromax C1"), BlacklistableDevice.instance("LENOVO", "Lenovo YT3-850M")));
    }
}
