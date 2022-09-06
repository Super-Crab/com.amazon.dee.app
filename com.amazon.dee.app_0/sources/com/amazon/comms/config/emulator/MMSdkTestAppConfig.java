package com.amazon.comms.config.emulator;

import android.content.Context;
import android.os.Build;
import com.amazon.comms.config.base.MultiModalConfig;
import com.amazon.comms.config.util.DeviceConfigConstants;
import com.amazon.comms.config.util.SimJobScheduler;
import com.amazon.comms.policy.CallingServiceRuntimePolicy;
/* loaded from: classes11.dex */
public class MMSdkTestAppConfig extends MultiModalConfig {
    private static MMSdkTestAppConfig mMSdkTestAppConfig = new MMSdkTestAppConfig();

    public static MMSdkTestAppConfig getDeviceConfigInstance() {
        return mMSdkTestAppConfig;
    }

    @Override // com.amazon.comms.config.DeviceConfig
    public boolean acceptPresenceBroadcast() {
        return false;
    }

    @Override // com.amazon.comms.config.DeviceConfig
    public boolean forceCamera2API() {
        return true;
    }

    @Override // com.amazon.comms.config.DeviceConfig
    public String getBuildVersion() {
        return Build.VERSION.INCREMENTAL;
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
    public synchronized CallingServiceRuntimePolicy getRuntimePolicy(Context context) {
        try {
            if (this.runtimePolicy == null) {
                this.runtimePolicy = (CallingServiceRuntimePolicy) Class.forName(DeviceConfigConstants.SLIMFAST_RUNTIME_POLICY).getDeclaredConstructor(Context.class).newInstance(context);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return this.runtimePolicy;
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
        return DeviceConfigConstants.VIDEO_BITRATE_600_KBPS;
    }

    @Override // com.amazon.comms.config.DeviceConfig
    public int getVideoWidth() {
        return 1280;
    }

    @Override // com.amazon.comms.config.DeviceConfig
    public boolean ignoreCameraEvictionError() {
        return true;
    }

    @Override // com.amazon.comms.config.DeviceConfig
    public boolean preferCamera1API() {
        return false;
    }

    @Override // com.amazon.comms.config.DeviceConfig
    public boolean shouldEnableAMZNBAudioFilter() {
        return false;
    }

    @Override // com.amazon.comms.config.DeviceConfig
    public boolean shouldPersistSimDeviceContext() {
        return false;
    }

    @Override // com.amazon.comms.config.DeviceConfig
    public boolean simulateFirstFrameReceived() {
        return true;
    }

    @Override // com.amazon.comms.config.base.MultiModalConfig, com.amazon.comms.config.DeviceConfig
    public boolean supportsCallReconnection() {
        return false;
    }

    @Override // com.amazon.comms.config.DeviceConfig
    public boolean supportsHints() {
        return true;
    }
}
