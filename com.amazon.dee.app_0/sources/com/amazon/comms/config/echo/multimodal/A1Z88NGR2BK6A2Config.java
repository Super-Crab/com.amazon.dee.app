package com.amazon.comms.config.echo.multimodal;

import android.content.Context;
import com.amazon.comms.calling.service.AudioConfig;
import com.amazon.comms.calling.service.DynamicAcousticParams;
import com.amazon.comms.calling.service.VideoConstraints;
import com.amazon.comms.config.base.MultiModalConfig;
import com.amazon.comms.config.util.DeviceConfigConstants;
import com.amazon.comms.config.util.SimJobScheduler;
/* loaded from: classes11.dex */
public class A1Z88NGR2BK6A2Config extends MultiModalConfig {
    private static final int RENDER_TRACK_BUFFER_SIZE_BYTES = 4608;
    private static final String WEBRTC_FIELD_TRIALS = "WebRTC-MTKH264/Enabled/WebRTC-MTKH264-LowLatency/Enabled/WebRTC-AVSync-AudioDelayEstimateMs/Enabled-160/WebRTC-Rfc5389StunRetransmissions/Enabled/WebRTC-Audio-SendSideBwe/Enabled/";
    private static A1Z88NGR2BK6A2Config a1Z88NGR2BK6A2Config = new A1Z88NGR2BK6A2Config();

    public static A1Z88NGR2BK6A2Config getDeviceConfigInstance() {
        return a1Z88NGR2BK6A2Config;
    }

    @Override // com.amazon.comms.config.DeviceConfig
    public int getAudioRenderBufferSizeBytes() {
        return 4608;
    }

    @Override // com.amazon.comms.config.DeviceConfig
    public DynamicAcousticParams.ConfigPath getDynamicAcousticParamsConfigPath() {
        return DeviceConfigConstants.DYNAMIC_ACOUSTIC_PARAMS_CONFIG_PATH_LIBASP;
    }

    @Override // com.amazon.comms.config.DeviceConfig
    public AudioConfig.FastRenderPath getFastAudioRenderPath() {
        return DeviceConfigConstants.FAST_RENDER_PATH_AUDIOTRACK;
    }

    @Override // com.amazon.comms.config.DeviceConfig
    public SimJobScheduler getJobScheduler(Context context) {
        try {
            return (SimJobScheduler) Class.forName(DeviceConfigConstants.DEVICE_JOBSCHEDULER_IMPL_MULTIMODALTACHYONARM).newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override // com.amazon.comms.config.DeviceConfig
    public VideoConstraints getMaxVideoConstraintsToRequestFromRemote() {
        return new VideoConstraints(1280, 800, 30);
    }

    @Override // com.amazon.comms.config.DeviceConfig
    public int getNonH264VideoFps() {
        return 24;
    }

    @Override // com.amazon.comms.config.DeviceConfig
    public int getNonH264VideoHeight() {
        return 180;
    }

    @Override // com.amazon.comms.config.DeviceConfig
    public int getNonH264VideoWidth() {
        return DeviceConfigConstants.VIDEO_WIDTH_320;
    }

    @Override // com.amazon.comms.config.DeviceConfig
    public int getVideoFps() {
        return 30;
    }

    @Override // com.amazon.comms.config.DeviceConfig
    public int getVideoHeight() {
        return 720;
    }

    @Override // com.amazon.comms.config.DeviceConfig
    public int getVideoMaxBitrate() {
        return 2000;
    }

    @Override // com.amazon.comms.config.DeviceConfig
    public int getVideoStartBitrate() {
        return 900;
    }

    @Override // com.amazon.comms.config.DeviceConfig
    public int getVideoWidth() {
        return 1280;
    }

    @Override // com.amazon.comms.config.DeviceConfig
    public String getWebRTCFieldTrials() {
        return WEBRTC_FIELD_TRIALS;
    }

    @Override // com.amazon.comms.config.DeviceConfig
    public int getWebRTCStatsPollingFrequency() {
        return 5000;
    }

    @Override // com.amazon.comms.config.DeviceConfig
    public boolean ignoreCameraEvictionError() {
        return true;
    }

    @Override // com.amazon.comms.config.DeviceConfig
    public boolean isCameraMetricsReportingEnabled() {
        return true;
    }

    @Override // com.amazon.comms.config.DeviceConfig
    public boolean isCameraShutterPresent() {
        return true;
    }

    @Override // com.amazon.comms.config.DeviceConfig
    public boolean isRealTimeTextCapable() {
        return true;
    }

    @Override // com.amazon.comms.config.DeviceConfig
    public boolean preferCamera1API() {
        return true;
    }

    @Override // com.amazon.comms.config.DeviceConfig
    public boolean provideCallingServiceHalParameter() {
        return true;
    }

    @Override // com.amazon.comms.config.DeviceConfig
    public boolean simulateFirstFrameReceived() {
        return true;
    }

    @Override // com.amazon.comms.config.DeviceConfig
    public boolean supportsHints() {
        return true;
    }

    @Override // com.amazon.comms.config.DeviceConfig
    public boolean supportsInsights(Context context) {
        return true;
    }

    @Override // com.amazon.comms.config.DeviceConfig
    public boolean updateCameraHalFramerateAllowed() {
        return true;
    }
}
