package com.amazon.photos.uploader.internal.dagger.module;

import com.amazon.photos.uploader.UploadFrameworkContext;
import com.amazon.photos.uploader.blockers.NetworkMonitor;
import com.amazon.photos.uploader.internal.utils.SystemUtil;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes13.dex */
public final class ConfigurationModule_ProvidesNetworkBlockerMonitor$AndroidPhotosUploader_releaseFactory implements Factory<NetworkMonitor> {
    private final ConfigurationModule module;
    private final Provider<SystemUtil> systemUtilProvider;
    private final Provider<UploadFrameworkContext> uploadFrameworkContextProvider;

    public ConfigurationModule_ProvidesNetworkBlockerMonitor$AndroidPhotosUploader_releaseFactory(ConfigurationModule configurationModule, Provider<UploadFrameworkContext> provider, Provider<SystemUtil> provider2) {
        this.module = configurationModule;
        this.uploadFrameworkContextProvider = provider;
        this.systemUtilProvider = provider2;
    }

    public static ConfigurationModule_ProvidesNetworkBlockerMonitor$AndroidPhotosUploader_releaseFactory create(ConfigurationModule configurationModule, Provider<UploadFrameworkContext> provider, Provider<SystemUtil> provider2) {
        return new ConfigurationModule_ProvidesNetworkBlockerMonitor$AndroidPhotosUploader_releaseFactory(configurationModule, provider, provider2);
    }

    public static NetworkMonitor providesNetworkBlockerMonitor$AndroidPhotosUploader_release(ConfigurationModule configurationModule, UploadFrameworkContext uploadFrameworkContext, SystemUtil systemUtil) {
        return (NetworkMonitor) Preconditions.checkNotNull(configurationModule.providesNetworkBlockerMonitor$AndroidPhotosUploader_release(uploadFrameworkContext, systemUtil), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public NetworkMonitor mo10268get() {
        return providesNetworkBlockerMonitor$AndroidPhotosUploader_release(this.module, this.uploadFrameworkContextProvider.mo10268get(), this.systemUtilProvider.mo10268get());
    }
}
