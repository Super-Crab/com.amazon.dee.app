package com.amazon.comms.config.firetv;

import com.amazon.comms.calling.service.DynamicAcousticParams;
import com.amazon.comms.config.util.DeviceConfigConstants;
/* loaded from: classes11.dex */
public class A31DTMEEVDDOIVConfig extends FireTVConfig {
    private static final String WEBRTC_FIELD_TRIALS = "WebRTC-MTKH264/Enabled-NoAdjust/WebRTC-MTKH264/Enabled/WebRTC-MTKH264-LowLatency/Enabled/WebRTC-Rfc5389StunRetransmissions/Enabled/WebRTC-Audio-SendSideBwe/Enabled/";
    private static A31DTMEEVDDOIVConfig a31DTMEEVDDOIVConfig = new A31DTMEEVDDOIVConfig();

    public static A31DTMEEVDDOIVConfig getDeviceConfigInstance() {
        return a31DTMEEVDDOIVConfig;
    }

    @Override // com.amazon.comms.config.firetv.FireTVConfig, com.amazon.comms.config.DeviceConfig
    public DynamicAcousticParams.ConfigPath getDynamicAcousticParamsConfigPath() {
        return DeviceConfigConstants.DYNAMIC_ACOUSTIC_PARAMS_CONFIG_PATH_NONE;
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
