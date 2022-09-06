package com.amazon.comms.config.firetv;

import android.content.Context;
import com.amazon.comms.config.util.DeviceConfigConstants;
import com.amazon.comms.config.util.LedControllerType;
import com.amazon.comms.device.AvsDeviceFacade;
import com.amazon.comms.device.FireTVDeviceFacade;
/* loaded from: classes11.dex */
public class A3HF4YRA2L7XGCConfig extends FireTVConfig {
    private static A3HF4YRA2L7XGCConfig a3HF4YRA2L7XGCConfig = new A3HF4YRA2L7XGCConfig();

    public static A3HF4YRA2L7XGCConfig getDeviceConfigInstance() {
        return a3HF4YRA2L7XGCConfig;
    }

    @Override // com.amazon.comms.config.firetv.FireTVConfig, com.amazon.comms.config.base.MultiModalConfig, com.amazon.comms.config.DeviceConfig
    public AvsDeviceFacade getAvsDeviceFacade(Context context) {
        return new FireTVDeviceFacade(context, true, true, false);
    }

    @Override // com.amazon.comms.config.firetv.FireTVConfig, com.amazon.comms.config.DeviceConfig
    public LedControllerType getLedControllerType() {
        return DeviceConfigConstants.LED_CONTROLLER_TYPE_LED_CONTROLLER_SERVICE;
    }
}
