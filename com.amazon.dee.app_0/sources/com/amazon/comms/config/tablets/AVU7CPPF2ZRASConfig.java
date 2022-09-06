package com.amazon.comms.config.tablets;
/* loaded from: classes11.dex */
public class AVU7CPPF2ZRASConfig extends HDTabletASPVoIPConfig {
    private static AVU7CPPF2ZRASConfig aVU7CPPF2ZRASConfig = new AVU7CPPF2ZRASConfig();

    public static AVU7CPPF2ZRASConfig getDeviceConfigInstance() {
        return aVU7CPPF2ZRASConfig;
    }

    @Override // com.amazon.comms.config.DeviceConfig
    public boolean provideCallingServiceHalParameter() {
        return true;
    }
}
