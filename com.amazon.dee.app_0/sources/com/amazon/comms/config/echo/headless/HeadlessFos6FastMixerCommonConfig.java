package com.amazon.comms.config.echo.headless;

import com.amazon.comms.calling.service.AudioConfig;
import com.amazon.comms.calling.service.DynamicAcousticParams;
import com.amazon.comms.config.base.HeadlessConfig;
import com.amazon.comms.config.util.DeviceConfigConstants;
/* loaded from: classes11.dex */
public class HeadlessFos6FastMixerCommonConfig extends HeadlessConfig {
    private static final int RENDER_TRACK_BUFFER_SIZE_BYTES = 3072;
    private static HeadlessFos6FastMixerCommonConfig commonFastMixerConfig = new HeadlessFos6FastMixerCommonConfig();

    public static HeadlessFos6FastMixerCommonConfig getDeviceConfigInstance() {
        return commonFastMixerConfig;
    }

    @Override // com.amazon.comms.config.DeviceConfig
    public int getAudioRenderBufferSizeBytes() {
        return RENDER_TRACK_BUFFER_SIZE_BYTES;
    }

    @Override // com.amazon.comms.config.DeviceConfig
    public DynamicAcousticParams.ConfigPath getDynamicAcousticParamsConfigPath() {
        return DeviceConfigConstants.DYNAMIC_ACOUSTIC_PARAMS_CONFIG_PATH_LIBASP;
    }

    @Override // com.amazon.comms.config.DeviceConfig
    public AudioConfig.FastRenderPath getFastAudioRenderPath() {
        return DeviceConfigConstants.FAST_RENDER_PATH_AUDIOTRACK;
    }
}
