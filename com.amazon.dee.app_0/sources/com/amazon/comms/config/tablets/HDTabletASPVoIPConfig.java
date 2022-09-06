package com.amazon.comms.config.tablets;

import com.amazon.comms.calling.service.DynamicAcousticParams;
import com.amazon.comms.calling.service.PlatformVoIPSelection;
import com.amazon.comms.config.util.DeviceConfigConstants;
/* loaded from: classes11.dex */
public class HDTabletASPVoIPConfig extends HDTabletConfig {
    private static HDTabletASPVoIPConfig tabletVoIPConfig = new HDTabletASPVoIPConfig();

    public static HDTabletASPVoIPConfig getDeviceConfigInstance() {
        return tabletVoIPConfig;
    }

    @Override // com.amazon.comms.config.DeviceConfig
    public DynamicAcousticParams.ConfigPath getDynamicAcousticParamsConfigPath() {
        return DeviceConfigConstants.DYNAMIC_ACOUSTIC_PARAMS_CONFIG_PATH_LIBASP;
    }

    @Override // com.amazon.comms.config.DeviceConfig
    public PlatformVoIPSelection getPlatformVoIPSelection() {
        return new PlatformVoIPSelection(true, false);
    }
}
