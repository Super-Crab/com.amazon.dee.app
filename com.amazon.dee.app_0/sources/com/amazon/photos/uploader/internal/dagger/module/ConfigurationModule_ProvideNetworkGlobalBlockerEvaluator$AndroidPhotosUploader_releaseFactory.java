package com.amazon.photos.uploader.internal.dagger.module;

import com.amazon.photos.uploader.SchedulingCallback;
import com.amazon.photos.uploader.blockers.NetworkGlobalBlockerEvaluator;
import com.amazon.photos.uploader.blockers.NetworkMonitor;
import com.amazon.photos.uploader.internal.utils.SystemUtil;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes13.dex */
public final class ConfigurationModule_ProvideNetworkGlobalBlockerEvaluator$AndroidPhotosUploader_releaseFactory implements Factory<NetworkGlobalBlockerEvaluator> {
    private final ConfigurationModule module;
    private final Provider<NetworkMonitor> networkMonitorProvider;
    private final Provider<SchedulingCallback> schedulingCallbackProvider;
    private final Provider<SystemUtil> systemUtilProvider;

    public ConfigurationModule_ProvideNetworkGlobalBlockerEvaluator$AndroidPhotosUploader_releaseFactory(ConfigurationModule configurationModule, Provider<SystemUtil> provider, Provider<SchedulingCallback> provider2, Provider<NetworkMonitor> provider3) {
        this.module = configurationModule;
        this.systemUtilProvider = provider;
        this.schedulingCallbackProvider = provider2;
        this.networkMonitorProvider = provider3;
    }

    public static ConfigurationModule_ProvideNetworkGlobalBlockerEvaluator$AndroidPhotosUploader_releaseFactory create(ConfigurationModule configurationModule, Provider<SystemUtil> provider, Provider<SchedulingCallback> provider2, Provider<NetworkMonitor> provider3) {
        return new ConfigurationModule_ProvideNetworkGlobalBlockerEvaluator$AndroidPhotosUploader_releaseFactory(configurationModule, provider, provider2, provider3);
    }

    public static NetworkGlobalBlockerEvaluator provideNetworkGlobalBlockerEvaluator$AndroidPhotosUploader_release(ConfigurationModule configurationModule, SystemUtil systemUtil, SchedulingCallback schedulingCallback, NetworkMonitor networkMonitor) {
        return (NetworkGlobalBlockerEvaluator) Preconditions.checkNotNull(configurationModule.provideNetworkGlobalBlockerEvaluator$AndroidPhotosUploader_release(systemUtil, schedulingCallback, networkMonitor), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public NetworkGlobalBlockerEvaluator mo10268get() {
        return provideNetworkGlobalBlockerEvaluator$AndroidPhotosUploader_release(this.module, this.systemUtilProvider.mo10268get(), this.schedulingCallbackProvider.mo10268get(), this.networkMonitorProvider.mo10268get());
    }
}
