package com.amazon.photos.uploader.internal.dagger.module;

import com.amazon.photos.uploader.internal.contentsignature.ContentSignatureProvider;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes13.dex */
public final class ConfigurationModule_ProvideContentSignatureProvider$AndroidPhotosUploader_releaseFactory implements Factory<ContentSignatureProvider> {
    private final ConfigurationModule module;

    public ConfigurationModule_ProvideContentSignatureProvider$AndroidPhotosUploader_releaseFactory(ConfigurationModule configurationModule) {
        this.module = configurationModule;
    }

    public static ConfigurationModule_ProvideContentSignatureProvider$AndroidPhotosUploader_releaseFactory create(ConfigurationModule configurationModule) {
        return new ConfigurationModule_ProvideContentSignatureProvider$AndroidPhotosUploader_releaseFactory(configurationModule);
    }

    public static ContentSignatureProvider provideContentSignatureProvider$AndroidPhotosUploader_release(ConfigurationModule configurationModule) {
        return (ContentSignatureProvider) Preconditions.checkNotNull(configurationModule.provideContentSignatureProvider$AndroidPhotosUploader_release(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public ContentSignatureProvider mo10268get() {
        return provideContentSignatureProvider$AndroidPhotosUploader_release(this.module);
    }
}
