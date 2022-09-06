package com.amazon.comms.config.firetv;

import android.content.Context;
import com.amazon.comms.config.util.DeviceConfigConstants;
import com.amazon.comms.config.util.LedControllerType;
import com.amazon.comms.device.AvsDeviceFacade;
import com.amazon.comms.device.FireTVDeviceFacade;
/* loaded from: classes11.dex */
public class A2JKHJ0PX4J3L3Config extends FireTVConfig {
    private static final String WEBRTC_FIELD_TRIALS = "WebRTC-AMLOGICH264/Enabled-FpsAdjust/WebRTC-Rfc5389StunRetransmissions/Enabled/VideoFrameEmit/Enabled/WebRTC-Audio-SendSideBwe/Enabled/";
    private static A2JKHJ0PX4J3L3Config a2JKHJ0PX4J3L3Config = new A2JKHJ0PX4J3L3Config();

    public static A2JKHJ0PX4J3L3Config getDeviceConfigInstance() {
        return a2JKHJ0PX4J3L3Config;
    }

    @Override // com.amazon.comms.config.firetv.FireTVConfig, com.amazon.comms.config.base.MultiModalConfig, com.amazon.comms.config.DeviceConfig
    public AvsDeviceFacade getAvsDeviceFacade(Context context) {
        return new FireTVDeviceFacade(context, true, true, false);
    }

    @Override // com.amazon.comms.config.firetv.FireTVConfig, com.amazon.comms.config.DeviceConfig
    public LedControllerType getLedControllerType() {
        return DeviceConfigConstants.LED_CONTROLLER_TYPE_LED_CONTROLLER_SERVICE;
    }

    @Override // com.amazon.comms.config.firetv.FireTVConfig, com.amazon.comms.config.DeviceConfig
    public String getWebRTCFieldTrials() {
        return WEBRTC_FIELD_TRIALS;
    }

    @Override // com.amazon.comms.config.firetv.FireTVConfig, com.amazon.comms.config.DeviceConfig
    public boolean isForceReceiveOnlyVideo() {
        return false;
    }
}
