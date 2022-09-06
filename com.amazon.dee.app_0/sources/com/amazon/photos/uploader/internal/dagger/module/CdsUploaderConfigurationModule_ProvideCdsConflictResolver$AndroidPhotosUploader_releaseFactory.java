package com.amazon.photos.uploader.internal.dagger.module;

import com.amazon.photos.uploader.cds.error.CdsConflictResolver;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes13.dex */
public final class CdsUploaderConfigurationModule_ProvideCdsConflictResolver$AndroidPhotosUploader_releaseFactory implements Factory<CdsConflictResolver> {
    private final CdsUploaderConfigurationModule module;

    public CdsUploaderConfigurationModule_ProvideCdsConflictResolver$AndroidPhotosUploader_releaseFactory(CdsUploaderConfigurationModule cdsUploaderConfigurationModule) {
        this.module = cdsUploaderConfigurationModule;
    }

    public static CdsUploaderConfigurationModule_ProvideCdsConflictResolver$AndroidPhotosUploader_releaseFactory create(CdsUploaderConfigurationModule cdsUploaderConfigurationModule) {
        return new CdsUploaderConfigurationModule_ProvideCdsConflictResolver$AndroidPhotosUploader_releaseFactory(cdsUploaderConfigurationModule);
    }

    public static CdsConflictResolver provideCdsConflictResolver$AndroidPhotosUploader_release(CdsUploaderConfigurationModule cdsUploaderConfigurationModule) {
        return (CdsConflictResolver) Preconditions.checkNotNull(cdsUploaderConfigurationModule.provideCdsConflictResolver$AndroidPhotosUploader_release(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public CdsConflictResolver mo10268get() {
        return provideCdsConflictResolver$AndroidPhotosUploader_release(this.module);
    }
}
