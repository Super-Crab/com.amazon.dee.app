package com.amazon.photos.uploader.internal.dagger.module;

import com.amazon.clouddrive.android.core.interfaces.Metrics;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes13.dex */
public final class CdsUploaderConfigurationModule_ProvideMetrics$AndroidPhotosUploader_releaseFactory implements Factory<Metrics> {
    private final CdsUploaderConfigurationModule module;

    public CdsUploaderConfigurationModule_ProvideMetrics$AndroidPhotosUploader_releaseFactory(CdsUploaderConfigurationModule cdsUploaderConfigurationModule) {
        this.module = cdsUploaderConfigurationModule;
    }

    public static CdsUploaderConfigurationModule_ProvideMetrics$AndroidPhotosUploader_releaseFactory create(CdsUploaderConfigurationModule cdsUploaderConfigurationModule) {
        return new CdsUploaderConfigurationModule_ProvideMetrics$AndroidPhotosUploader_releaseFactory(cdsUploaderConfigurationModule);
    }

    public static Metrics provideMetrics$AndroidPhotosUploader_release(CdsUploaderConfigurationModule cdsUploaderConfigurationModule) {
        return (Metrics) Preconditions.checkNotNull(cdsUploaderConfigurationModule.provideMetrics$AndroidPhotosUploader_release(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Metrics mo10268get() {
        return provideMetrics$AndroidPhotosUploader_release(this.module);
    }
}
