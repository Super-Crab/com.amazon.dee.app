package com.amazon.comms.config.firetv;

import com.amazon.comms.calling.service.DynamicAcousticParams;
import com.amazon.comms.config.util.DeviceConfigConstants;
/* loaded from: classes11.dex */
public class A8MCGN45KMHDHConfig extends FireTVEditionsConfig {
    private static A8MCGN45KMHDHConfig a8MCGN45KMHDHConfig = new A8MCGN45KMHDHConfig();

    public static A8MCGN45KMHDHConfig getDeviceConfigInstance() {
        return a8MCGN45KMHDHConfig;
    }

    @Override // com.amazon.comms.config.firetv.FireTVConfig, com.amazon.comms.config.DeviceConfig
    public DynamicAcousticParams.ConfigPath getDynamicAcousticParamsConfigPath() {
        return DeviceConfigConstants.DYNAMIC_ACOUSTIC_PARAMS_CONFIG_PATH_LIBASP;
    }
}
