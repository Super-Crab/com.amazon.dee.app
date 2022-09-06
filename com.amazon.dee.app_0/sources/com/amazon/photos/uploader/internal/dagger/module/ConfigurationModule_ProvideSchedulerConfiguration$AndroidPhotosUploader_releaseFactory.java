package com.amazon.photos.uploader.internal.dagger.module;

import com.amazon.photos.uploader.internal.SchedulerConfiguration;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes13.dex */
public final class ConfigurationModule_ProvideSchedulerConfiguration$AndroidPhotosUploader_releaseFactory implements Factory<SchedulerConfiguration> {
    private final ConfigurationModule module;

    public ConfigurationModule_ProvideSchedulerConfiguration$AndroidPhotosUploader_releaseFactory(ConfigurationModule configurationModule) {
        this.module = configurationModule;
    }

    public static ConfigurationModule_ProvideSchedulerConfiguration$AndroidPhotosUploader_releaseFactory create(ConfigurationModule configurationModule) {
        return new ConfigurationModule_ProvideSchedulerConfiguration$AndroidPhotosUploader_releaseFactory(configurationModule);
    }

    public static SchedulerConfiguration provideSchedulerConfiguration$AndroidPhotosUploader_release(ConfigurationModule configurationModule) {
        return (SchedulerConfiguration) Preconditions.checkNotNull(configurationModule.provideSchedulerConfiguration$AndroidPhotosUploader_release(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public SchedulerConfiguration mo10268get() {
        return provideSchedulerConfiguration$AndroidPhotosUploader_release(this.module);
    }
}
