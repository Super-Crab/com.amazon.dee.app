package com.amazon.photos.uploader.internal.dagger.module;

import com.amazon.clouddrive.android.core.interfaces.Metrics;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes13.dex */
public final class ConfigurationModule_ProvideMetrics$AndroidPhotosUploader_releaseFactory implements Factory<Metrics> {
    private final ConfigurationModule module;

    public ConfigurationModule_ProvideMetrics$AndroidPhotosUploader_releaseFactory(ConfigurationModule configurationModule) {
        this.module = configurationModule;
    }

    public static ConfigurationModule_ProvideMetrics$AndroidPhotosUploader_releaseFactory create(ConfigurationModule configurationModule) {
        return new ConfigurationModule_ProvideMetrics$AndroidPhotosUploader_releaseFactory(configurationModule);
    }

    public static Metrics provideMetrics$AndroidPhotosUploader_release(ConfigurationModule configurationModule) {
        return (Metrics) Preconditions.checkNotNull(configurationModule.provideMetrics$AndroidPhotosUploader_release(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Metrics mo10268get() {
        return provideMetrics$AndroidPhotosUploader_release(this.module);
    }
}
