package com.amazon.photos.uploader.internal.dagger.module;

import com.amazon.photos.uploader.cds.multipart.InitializeMultiPartRequestBuilder;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes13.dex */
public final class CdsUploaderConfigurationModule_ProvideInitializeMultiPartRequestBuilder$AndroidPhotosUploader_releaseFactory implements Factory<InitializeMultiPartRequestBuilder> {
    private final CdsUploaderConfigurationModule module;

    public CdsUploaderConfigurationModule_ProvideInitializeMultiPartRequestBuilder$AndroidPhotosUploader_releaseFactory(CdsUploaderConfigurationModule cdsUploaderConfigurationModule) {
        this.module = cdsUploaderConfigurationModule;
    }

    public static CdsUploaderConfigurationModule_ProvideInitializeMultiPartRequestBuilder$AndroidPhotosUploader_releaseFactory create(CdsUploaderConfigurationModule cdsUploaderConfigurationModule) {
        return new CdsUploaderConfigurationModule_ProvideInitializeMultiPartRequestBuilder$AndroidPhotosUploader_releaseFactory(cdsUploaderConfigurationModule);
    }

    public static InitializeMultiPartRequestBuilder provideInitializeMultiPartRequestBuilder$AndroidPhotosUploader_release(CdsUploaderConfigurationModule cdsUploaderConfigurationModule) {
        return (InitializeMultiPartRequestBuilder) Preconditions.checkNotNull(cdsUploaderConfigurationModule.provideInitializeMultiPartRequestBuilder$AndroidPhotosUploader_release(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public InitializeMultiPartRequestBuilder mo10268get() {
        return provideInitializeMultiPartRequestBuilder$AndroidPhotosUploader_release(this.module);
    }
}
