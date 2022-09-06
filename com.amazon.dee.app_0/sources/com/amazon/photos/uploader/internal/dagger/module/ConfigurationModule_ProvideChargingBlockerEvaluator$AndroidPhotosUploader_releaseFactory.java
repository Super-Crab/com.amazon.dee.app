package com.amazon.photos.uploader.internal.dagger.module;

import com.amazon.photos.uploader.SchedulingCallback;
import com.amazon.photos.uploader.internal.device.BatteryState;
import com.amazon.photos.uploader.internal.device.ChargingBlockerEvaluator;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes13.dex */
public final class ConfigurationModule_ProvideChargingBlockerEvaluator$AndroidPhotosUploader_releaseFactory implements Factory<ChargingBlockerEvaluator> {
    private final Provider<BatteryState> batteryStateProvider;
    private final ConfigurationModule module;
    private final Provider<SchedulingCallback> schedulingCallbackProvider;

    public ConfigurationModule_ProvideChargingBlockerEvaluator$AndroidPhotosUploader_releaseFactory(ConfigurationModule configurationModule, Provider<BatteryState> provider, Provider<SchedulingCallback> provider2) {
        this.module = configurationModule;
        this.batteryStateProvider = provider;
        this.schedulingCallbackProvider = provider2;
    }

    public static ConfigurationModule_ProvideChargingBlockerEvaluator$AndroidPhotosUploader_releaseFactory create(ConfigurationModule configurationModule, Provider<BatteryState> provider, Provider<SchedulingCallback> provider2) {
        return new ConfigurationModule_ProvideChargingBlockerEvaluator$AndroidPhotosUploader_releaseFactory(configurationModule, provider, provider2);
    }

    public static ChargingBlockerEvaluator provideChargingBlockerEvaluator$AndroidPhotosUploader_release(ConfigurationModule configurationModule, BatteryState batteryState, SchedulingCallback schedulingCallback) {
        return (ChargingBlockerEvaluator) Preconditions.checkNotNull(configurationModule.provideChargingBlockerEvaluator$AndroidPhotosUploader_release(batteryState, schedulingCallback), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public ChargingBlockerEvaluator mo10268get() {
        return provideChargingBlockerEvaluator$AndroidPhotosUploader_release(this.module, this.batteryStateProvider.mo10268get(), this.schedulingCallbackProvider.mo10268get());
    }
}
