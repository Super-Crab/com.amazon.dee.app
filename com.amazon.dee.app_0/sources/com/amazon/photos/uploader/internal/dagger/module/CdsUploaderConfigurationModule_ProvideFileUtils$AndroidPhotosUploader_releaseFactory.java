package com.amazon.photos.uploader.internal.dagger.module;

import com.amazon.photos.uploader.internal.utils.FileUtils;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes13.dex */
public final class CdsUploaderConfigurationModule_ProvideFileUtils$AndroidPhotosUploader_releaseFactory implements Factory<FileUtils> {
    private final CdsUploaderConfigurationModule module;

    public CdsUploaderConfigurationModule_ProvideFileUtils$AndroidPhotosUploader_releaseFactory(CdsUploaderConfigurationModule cdsUploaderConfigurationModule) {
        this.module = cdsUploaderConfigurationModule;
    }

    public static CdsUploaderConfigurationModule_ProvideFileUtils$AndroidPhotosUploader_releaseFactory create(CdsUploaderConfigurationModule cdsUploaderConfigurationModule) {
        return new CdsUploaderConfigurationModule_ProvideFileUtils$AndroidPhotosUploader_releaseFactory(cdsUploaderConfigurationModule);
    }

    public static FileUtils provideFileUtils$AndroidPhotosUploader_release(CdsUploaderConfigurationModule cdsUploaderConfigurationModule) {
        return (FileUtils) Preconditions.checkNotNull(cdsUploaderConfigurationModule.provideFileUtils$AndroidPhotosUploader_release(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public FileUtils mo10268get() {
        return provideFileUtils$AndroidPhotosUploader_release(this.module);
    }
}
