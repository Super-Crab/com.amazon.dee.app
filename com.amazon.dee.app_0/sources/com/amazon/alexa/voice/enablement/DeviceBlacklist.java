package com.amazon.alexa.voice.enablement;

import java.util.Set;
@FunctionalInterface
/* loaded from: classes11.dex */
interface DeviceBlacklist {
    Set<BlacklistableDevice> getBlacklistedDevices();
}
