package com.amazon.comms.config.echo.headless;

import com.amazon.comms.calling.service.AudioConfig;
import com.amazon.comms.calling.service.DynamicAcousticParams;
import com.amazon.comms.config.base.HeadlessConfig;
import com.amazon.comms.config.util.DeviceConfigConstants;
/* loaded from: classes11.dex */
public class HeadlessFos5FastMixerCommonConfig extends HeadlessConfig {
    private static final int RENDER_TRACK_BUFFER_SIZE_BYTES = 1536;
    private static HeadlessFos5FastMixerCommonConfig commonFastMixerConfig = new HeadlessFos5FastMixerCommonConfig();
    private static final DynamicAcousticParams.ConfigPath DEFAULT_DYNAMIC_ACOUSTIC_PARAMS_CONFIG_PATH = DynamicAcousticParams.ConfigPath.LIBASP;

    public static HeadlessFos5FastMixerCommonConfig getDeviceConfigInstance() {
        return commonFastMixerConfig;
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
}
