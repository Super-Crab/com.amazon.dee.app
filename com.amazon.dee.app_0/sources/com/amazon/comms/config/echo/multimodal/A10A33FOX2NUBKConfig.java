package com.amazon.comms.config.echo.multimodal;

import android.content.Context;
import com.amazon.comms.calling.service.AudioConfig;
import com.amazon.comms.calling.service.DynamicAcousticParams;
import com.amazon.comms.calling.service.VideoConstraints;
import com.amazon.comms.config.base.MultiModalConfig;
import com.amazon.comms.config.util.DeviceConfigConstants;
import com.amazon.comms.config.util.SimJobScheduler;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;
/* loaded from: classes11.dex */
public class A10A33FOX2NUBKConfig extends MultiModalConfig {
    private static final int RENDER_TRACK_BUFFER_SIZE_BYTES = 1536;
    private static final String WEBRTC_FIELD_TRIALS = "WebRTC-MTKH264/Enabled/WebRTC-MTKH264-LowLatency/Enabled/WebRTC-AVSync-AudioDelayEstimateMs/Enabled-128/WebRTC-Rfc5389StunRetransmissions/Enabled/WebRTC-Audio-SendSideBwe/Enabled/";
    private static A10A33FOX2NUBKConfig a10A33FOX2NUBKConfig;
    private static final Map<Integer, VideoConstraints> supportedVideoConstraints;

    static {
        TreeMap treeMap = new TreeMap();
        GeneratedOutlineSupport1.outline140(240, 180, 15, treeMap, 2700);
        GeneratedOutlineSupport1.outline140(DeviceConfigConstants.VIDEO_WIDTH_320, 240, 15, treeMap, 4800);
        GeneratedOutlineSupport1.outline140(480, 360, 24, treeMap, 16560);
        GeneratedOutlineSupport1.outline140(640, 480, 24, treeMap, 28800);
        supportedVideoConstraints = Collections.unmodifiableMap(treeMap);
        a10A33FOX2NUBKConfig = new A10A33FOX2NUBKConfig();
    }

    public static A10A33FOX2NUBKConfig getDeviceConfigInstance() {
        return a10A33FOX2NUBKConfig;
    }

    @Override // com.amazon.comms.config.DeviceConfig
    public int getAudioRenderBufferSizeBytes() {
        return 1536;
    }

    @Override // com.amazon.comms.config.DeviceConfig
    public DynamicAcousticParams.ConfigPath getDynamicAcousticParamsConfigPath() {
        return DeviceConfigConstants.DYNAMIC_ACOUSTIC_PARAMS_CONFIG_PATH_LIBASP;
    }

    @Override // com.amazon.comms.config.DeviceConfig
    public AudioConfig.FastRenderPath getFastAudioRenderPath() {
        return DeviceConfigConstants.FAST_RENDER_PATH_OPENSLES;
    }

    @Override // com.amazon.comms.config.DeviceConfig
    public SimJobScheduler getJobScheduler(Context context) {
        try {
            return (SimJobScheduler) Class.forName(DeviceConfigConstants.DEVICE_JOBSCHEDULER_IMPL_MULTIMODALTACHYONARM).newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override // com.amazon.comms.config.base.MultiModalConfig, com.amazon.comms.config.DeviceConfig
    public String getLocalSurfaceViewShape() {
        return "circle";
    }

    @Override // com.amazon.comms.config.DeviceConfig
    public int getNonH264VideoFps() {
        return 24;
    }

    @Override // com.amazon.comms.config.DeviceConfig
    public int getNonH264VideoHeight() {
        return 240;
    }

    @Override // com.amazon.comms.config.DeviceConfig
    public int getNonH264VideoWidth() {
        return DeviceConfigConstants.VIDEO_WIDTH_320;
    }

    @Override // com.amazon.comms.config.base.MultiModalConfig, com.amazon.comms.config.DeviceConfig
    public Map<Integer, VideoConstraints> getSupportedResolutions() {
        return supportedVideoConstraints;
    }

    @Override // com.amazon.comms.config.DeviceConfig
    public int getVideoFps() {
        return 24;
    }

    @Override // com.amazon.comms.config.DeviceConfig
    public int getVideoHeight() {
        return 480;
    }

    @Override // com.amazon.comms.config.DeviceConfig
    public int getVideoMaxBitrate() {
        return 800;
    }

    @Override // com.amazon.comms.config.DeviceConfig
    public int getVideoStartBitrate() {
        return 800;
    }

    @Override // com.amazon.comms.config.DeviceConfig
    public int getVideoWidth() {
        return 640;
    }

    @Override // com.amazon.comms.config.DeviceConfig
    public String getWebRTCFieldTrials() {
        return WEBRTC_FIELD_TRIALS;
    }

    @Override // com.amazon.comms.config.DeviceConfig
    public boolean ignoreCameraEvictionError() {
        return false;
    }

    @Override // com.amazon.comms.config.DeviceConfig
    public boolean preferCamera1API() {
        return true;
    }

    @Override // com.amazon.comms.config.DeviceConfig
    public boolean simulateFirstFrameReceived() {
        return true;
    }

    @Override // com.amazon.comms.config.DeviceConfig
    public boolean updateCameraHalFramerateAllowed() {
        return true;
    }
}
