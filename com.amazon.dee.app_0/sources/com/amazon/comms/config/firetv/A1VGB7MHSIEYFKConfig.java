package com.amazon.comms.config.firetv;

import android.content.Context;
import com.amazon.comms.config.util.DeviceConfigConstants;
import com.amazon.comms.config.util.LedControllerType;
import com.amazon.comms.device.AvsDeviceFacade;
import com.amazon.comms.device.FireTVDeviceFacade;
/* loaded from: classes11.dex */
public class A1VGB7MHSIEYFKConfig extends FireTVConfig {
    private static final String WEBRTC_FIELD_TRIALS = "WebRTC-AMLOGICH264/Enabled-FpsAdjust/WebRTC-Rfc5389StunRetransmissions/Enabled/VideoFrameEmit/Enabled/WebRTC-Audio-SendSideBwe/Enabled/";
    private static A1VGB7MHSIEYFKConfig a1VGB7MHSIEYFKConfig = new A1VGB7MHSIEYFKConfig();

    public static A1VGB7MHSIEYFKConfig getDeviceConfigInstance() {
        return a1VGB7MHSIEYFKConfig;
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
