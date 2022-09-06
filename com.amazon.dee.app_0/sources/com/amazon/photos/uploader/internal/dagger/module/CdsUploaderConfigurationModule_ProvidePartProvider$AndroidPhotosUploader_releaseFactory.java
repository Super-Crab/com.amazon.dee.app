package com.amazon.photos.uploader.internal.dagger.module;

import com.amazon.photos.uploader.cds.PartProvider;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes13.dex */
public final class CdsUploaderConfigurationModule_ProvidePartProvider$AndroidPhotosUploader_releaseFactory implements Factory<PartProvider> {
    private final CdsUploaderConfigurationModule module;

    public CdsUploaderConfigurationModule_ProvidePartProvider$AndroidPhotosUploader_releaseFactory(CdsUploaderConfigurationModule cdsUploaderConfigurationModule) {
        this.module = cdsUploaderConfigurationModule;
    }

    public static CdsUploaderConfigurationModule_ProvidePartProvider$AndroidPhotosUploader_releaseFactory create(CdsUploaderConfigurationModule cdsUploaderConfigurationModule) {
        return new CdsUploaderConfigurationModule_ProvidePartProvider$AndroidPhotosUploader_releaseFactory(cdsUploaderConfigurationModule);
    }

    public static PartProvider providePartProvider$AndroidPhotosUploader_release(CdsUploaderConfigurationModule cdsUploaderConfigurationModule) {
        return (PartProvider) Preconditions.checkNotNull(cdsUploaderConfigurationModule.providePartProvider$AndroidPhotosUploader_release(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public PartProvider mo10268get() {
        return providePartProvider$AndroidPhotosUploader_release(this.module);
    }
}
