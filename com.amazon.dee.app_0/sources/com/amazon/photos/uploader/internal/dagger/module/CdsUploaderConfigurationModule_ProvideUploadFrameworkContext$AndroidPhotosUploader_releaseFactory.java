package com.amazon.photos.uploader.internal.dagger.module;

import com.amazon.photos.uploader.UploadFrameworkContext;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes13.dex */
public final class CdsUploaderConfigurationModule_ProvideUploadFrameworkContext$AndroidPhotosUploader_releaseFactory implements Factory<UploadFrameworkContext> {
    private final CdsUploaderConfigurationModule module;

    public CdsUploaderConfigurationModule_ProvideUploadFrameworkContext$AndroidPhotosUploader_releaseFactory(CdsUploaderConfigurationModule cdsUploaderConfigurationModule) {
        this.module = cdsUploaderConfigurationModule;
    }

    public static CdsUploaderConfigurationModule_ProvideUploadFrameworkContext$AndroidPhotosUploader_releaseFactory create(CdsUploaderConfigurationModule cdsUploaderConfigurationModule) {
        return new CdsUploaderConfigurationModule_ProvideUploadFrameworkContext$AndroidPhotosUploader_releaseFactory(cdsUploaderConfigurationModule);
    }

    public static UploadFrameworkContext provideUploadFrameworkContext$AndroidPhotosUploader_release(CdsUploaderConfigurationModule cdsUploaderConfigurationModule) {
        return (UploadFrameworkContext) Preconditions.checkNotNull(cdsUploaderConfigurationModule.provideUploadFrameworkContext$AndroidPhotosUploader_release(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public UploadFrameworkContext mo10268get() {
        return provideUploadFrameworkContext$AndroidPhotosUploader_release(this.module);
    }
}
