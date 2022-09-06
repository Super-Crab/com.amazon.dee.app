package com.amazon.comms.config.tablets;
/* loaded from: classes11.dex */
public class HDTabletConfig extends TabletConfig {
    private static final String WEBRTC_FIELD_TRIALS = "WebRTC-MTKH264/Enabled/WebRTC-MTKH264-LowLatency/Enabled/WebRTC-Rfc5389StunRetransmissions/Enabled/VideoFrameEmit/Enabled/WebRTC-Audio-SendSideBwe/Enabled/";
    private static HDTabletConfig hDTabletConfig = new HDTabletConfig();

    public static HDTabletConfig getDeviceConfigInstance() {
        return hDTabletConfig;
    }

    @Override // com.amazon.comms.config.tablets.TabletConfig, com.amazon.comms.config.DeviceConfig
    public int getVideoFps() {
        return 30;
    }

    @Override // com.amazon.comms.config.tablets.TabletConfig, com.amazon.comms.config.DeviceConfig
    public int getVideoHeight() {
        return 720;
    }

    @Override // com.amazon.comms.config.tablets.TabletConfig, com.amazon.comms.config.DeviceConfig
    public int getVideoMaxBitrate() {
        return 2000;
    }

    @Override // com.amazon.comms.config.tablets.TabletConfig, com.amazon.comms.config.DeviceConfig
    public int getVideoStartBitrate() {
        return 900;
    }

    @Override // com.amazon.comms.config.tablets.TabletConfig, com.amazon.comms.config.DeviceConfig
    public int getVideoWidth() {
        return 1280;
    }

    @Override // com.amazon.comms.config.tablets.TabletConfig, com.amazon.comms.config.DeviceConfig
    public String getWebRTCFieldTrials() {
        return WEBRTC_FIELD_TRIALS;
    }
}
