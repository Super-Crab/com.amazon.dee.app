package com.amazon.photos.uploader.internal.dagger.module;

import com.amazon.photos.uploader.blockers.NetworkMonitor;
import com.amazon.photos.uploader.internal.UploadSummaryNotifier;
import com.amazon.photos.uploader.internal.device.BatteryReceiverRegisterHelper;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes13.dex */
public final class ConfigurationModule_ProvideUploadSummaryNotifier$AndroidPhotosUploader_releaseFactory implements Factory<UploadSummaryNotifier> {
    private final Provider<BatteryReceiverRegisterHelper> batteryReceiverRegisterHelperProvider;
    private final ConfigurationModule module;
    private final Provider<NetworkMonitor> networkMonitorProvider;

    public ConfigurationModule_ProvideUploadSummaryNotifier$AndroidPhotosUploader_releaseFactory(ConfigurationModule configurationModule, Provider<BatteryReceiverRegisterHelper> provider, Provider<NetworkMonitor> provider2) {
        this.module = configurationModule;
        this.batteryReceiverRegisterHelperProvider = provider;
        this.networkMonitorProvider = provider2;
    }

    public static ConfigurationModule_ProvideUploadSummaryNotifier$AndroidPhotosUploader_releaseFactory create(ConfigurationModule configurationModule, Provider<BatteryReceiverRegisterHelper> provider, Provider<NetworkMonitor> provider2) {
        return new ConfigurationModule_ProvideUploadSummaryNotifier$AndroidPhotosUploader_releaseFactory(configurationModule, provider, provider2);
    }

    public static UploadSummaryNotifier provideUploadSummaryNotifier$AndroidPhotosUploader_release(ConfigurationModule configurationModule, BatteryReceiverRegisterHelper batteryReceiverRegisterHelper, NetworkMonitor networkMonitor) {
        return (UploadSummaryNotifier) Preconditions.checkNotNull(configurationModule.provideUploadSummaryNotifier$AndroidPhotosUploader_release(batteryReceiverRegisterHelper, networkMonitor), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public UploadSummaryNotifier mo10268get() {
        return provideUploadSummaryNotifier$AndroidPhotosUploader_release(this.module, this.batteryReceiverRegisterHelperProvider.mo10268get(), this.networkMonitorProvider.mo10268get());
    }
}
