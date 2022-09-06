package com.amazon.comms.config.firetv;

import android.content.Context;
import com.amazon.comms.device.AvsDeviceFacade;
import com.amazon.comms.device.FireTVDeviceFacade;
/* loaded from: classes11.dex */
public class FireTVEditionsConfig extends FireTVConfig {
    private static final String WEBRTC_FIELD_TRIALS = "WebRTC-MSH264/Enabled/WebRTC-MSH264-LowLatency/Enabled/WebRTC-Rfc5389StunRetransmissions/Enabled/VideoFrameEmit/Enabled/WebRTC-Audio-SendSideBwe/Enabled/";
    private static FireTVEditionsConfig fireTVEditionsConfig = new FireTVEditionsConfig();

    public static FireTVEditionsConfig getDeviceConfigInstance() {
        return fireTVEditionsConfig;
    }

    @Override // com.amazon.comms.config.firetv.FireTVConfig, com.amazon.comms.config.base.MultiModalConfig, com.amazon.comms.config.DeviceConfig
    public AvsDeviceFacade getAvsDeviceFacade(Context context) {
        return new FireTVDeviceFacade(context, false, false, true);
    }

    @Override // com.amazon.comms.config.firetv.FireTVConfig, com.amazon.comms.config.DeviceConfig
    public int getVideoFps() {
        return 35;
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
