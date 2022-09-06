package com.amazon.photos.uploader.internal.dagger.module;

import com.amazon.photos.uploader.internal.contentsignature.ContentSignatureProvider;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes13.dex */
public final class CdsUploaderConfigurationModule_ProvideContentSignatureProvider$AndroidPhotosUploader_releaseFactory implements Factory<ContentSignatureProvider> {
    private final CdsUploaderConfigurationModule module;

    public CdsUploaderConfigurationModule_ProvideContentSignatureProvider$AndroidPhotosUploader_releaseFactory(CdsUploaderConfigurationModule cdsUploaderConfigurationModule) {
        this.module = cdsUploaderConfigurationModule;
    }

    public static CdsUploaderConfigurationModule_ProvideContentSignatureProvider$AndroidPhotosUploader_releaseFactory create(CdsUploaderConfigurationModule cdsUploaderConfigurationModule) {
        return new CdsUploaderConfigurationModule_ProvideContentSignatureProvider$AndroidPhotosUploader_releaseFactory(cdsUploaderConfigurationModule);
    }

    public static ContentSignatureProvider provideContentSignatureProvider$AndroidPhotosUploader_release(CdsUploaderConfigurationModule cdsUploaderConfigurationModule) {
        return (ContentSignatureProvider) Preconditions.checkNotNull(cdsUploaderConfigurationModule.provideContentSignatureProvider$AndroidPhotosUploader_release(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public ContentSignatureProvider mo10268get() {
        return provideContentSignatureProvider$AndroidPhotosUploader_release(this.module);
    }
}
