package com.amazon.photos.uploader.internal.dagger.module;

import com.amazon.photos.uploader.internal.workers.CoroutineWorkerUtil;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes13.dex */
public final class CdsUploaderConfigurationModule_ProvidesCoroutineWorkerUtil$AndroidPhotosUploader_releaseFactory implements Factory<CoroutineWorkerUtil> {
    private final CdsUploaderConfigurationModule module;

    public CdsUploaderConfigurationModule_ProvidesCoroutineWorkerUtil$AndroidPhotosUploader_releaseFactory(CdsUploaderConfigurationModule cdsUploaderConfigurationModule) {
        this.module = cdsUploaderConfigurationModule;
    }

    public static CdsUploaderConfigurationModule_ProvidesCoroutineWorkerUtil$AndroidPhotosUploader_releaseFactory create(CdsUploaderConfigurationModule cdsUploaderConfigurationModule) {
        return new CdsUploaderConfigurationModule_ProvidesCoroutineWorkerUtil$AndroidPhotosUploader_releaseFactory(cdsUploaderConfigurationModule);
    }

    public static CoroutineWorkerUtil providesCoroutineWorkerUtil$AndroidPhotosUploader_release(CdsUploaderConfigurationModule cdsUploaderConfigurationModule) {
        return (CoroutineWorkerUtil) Preconditions.checkNotNull(cdsUploaderConfigurationModule.providesCoroutineWorkerUtil$AndroidPhotosUploader_release(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public CoroutineWorkerUtil mo10268get() {
        return providesCoroutineWorkerUtil$AndroidPhotosUploader_release(this.module);
    }
}
