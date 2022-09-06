package com.amazon.comms.config.echo.headless;
/* loaded from: classes11.dex */
public class A32DOYMUN6DTXAConfig extends HeadlessFos6FastMixerCommonConfig {
    private static A32DOYMUN6DTXAConfig a32DOYMUN6DTXAConfig = new A32DOYMUN6DTXAConfig();

    public static A32DOYMUN6DTXAConfig getDeviceConfigInstance() {
        return a32DOYMUN6DTXAConfig;
    }

    @Override // com.amazon.comms.config.DeviceConfig
    public boolean supportsReducedRegistrationTraffic() {
        return true;
    }
}
