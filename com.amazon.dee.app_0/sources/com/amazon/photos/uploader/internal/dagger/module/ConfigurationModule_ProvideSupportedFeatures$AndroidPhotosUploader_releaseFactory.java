package com.amazon.photos.uploader.internal.dagger.module;

import com.amazon.photos.uploader.Feature;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import java.util.Set;
/* loaded from: classes13.dex */
public final class ConfigurationModule_ProvideSupportedFeatures$AndroidPhotosUploader_releaseFactory implements Factory<Set<Feature>> {
    private final ConfigurationModule module;

    public ConfigurationModule_ProvideSupportedFeatures$AndroidPhotosUploader_releaseFactory(ConfigurationModule configurationModule) {
        this.module = configurationModule;
    }

    public static ConfigurationModule_ProvideSupportedFeatures$AndroidPhotosUploader_releaseFactory create(ConfigurationModule configurationModule) {
        return new ConfigurationModule_ProvideSupportedFeatures$AndroidPhotosUploader_releaseFactory(configurationModule);
    }

    public static Set<Feature> provideSupportedFeatures$AndroidPhotosUploader_release(ConfigurationModule configurationModule) {
        return (Set) Preconditions.checkNotNull(configurationModule.provideSupportedFeatures$AndroidPhotosUploader_release(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get  reason: collision with other method in class */
    public Set<Feature> mo10268get() {
        return provideSupportedFeatures$AndroidPhotosUploader_release(this.module);
    }
}
