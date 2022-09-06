package com.amazon.photos.uploader.internal.dagger.module;

import com.amazon.photos.uploader.SchedulingCallback;
import com.amazon.photos.uploader.internal.device.BatteryReceiverRegisterHelper;
import com.amazon.photos.uploader.internal.device.BatteryState;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes13.dex */
public final class ConfigurationModule_ProvidesBatteryBlockerMonitor$AndroidPhotosUploader_releaseFactory implements Factory<BatteryReceiverRegisterHelper> {
    private final Provider<BatteryState> batteryStateProvider;
    private final ConfigurationModule module;
    private final Provider<SchedulingCallback> schedulingCallbackProvider;

    public ConfigurationModule_ProvidesBatteryBlockerMonitor$AndroidPhotosUploader_releaseFactory(ConfigurationModule configurationModule, Provider<BatteryState> provider, Provider<SchedulingCallback> provider2) {
        this.module = configurationModule;
        this.batteryStateProvider = provider;
        this.schedulingCallbackProvider = provider2;
    }

    public static ConfigurationModule_ProvidesBatteryBlockerMonitor$AndroidPhotosUploader_releaseFactory create(ConfigurationModule configurationModule, Provider<BatteryState> provider, Provider<SchedulingCallback> provider2) {
        return new ConfigurationModule_ProvidesBatteryBlockerMonitor$AndroidPhotosUploader_releaseFactory(configurationModule, provider, provider2);
    }

    public static BatteryReceiverRegisterHelper providesBatteryBlockerMonitor$AndroidPhotosUploader_release(ConfigurationModule configurationModule, BatteryState batteryState, SchedulingCallback schedulingCallback) {
        return (BatteryReceiverRegisterHelper) Preconditions.checkNotNull(configurationModule.providesBatteryBlockerMonitor$AndroidPhotosUploader_release(batteryState, schedulingCallback), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public BatteryReceiverRegisterHelper mo10268get() {
        return providesBatteryBlockerMonitor$AndroidPhotosUploader_release(this.module, this.batteryStateProvider.mo10268get(), this.schedulingCallbackProvider.mo10268get());
    }
}
