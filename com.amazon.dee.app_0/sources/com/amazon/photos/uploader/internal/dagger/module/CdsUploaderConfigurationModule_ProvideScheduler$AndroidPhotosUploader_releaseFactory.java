package com.amazon.photos.uploader.internal.dagger.module;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import io.reactivex.rxjava3.core.Scheduler;
/* loaded from: classes13.dex */
public final class CdsUploaderConfigurationModule_ProvideScheduler$AndroidPhotosUploader_releaseFactory implements Factory<Scheduler> {
    private final CdsUploaderConfigurationModule module;

    public CdsUploaderConfigurationModule_ProvideScheduler$AndroidPhotosUploader_releaseFactory(CdsUploaderConfigurationModule cdsUploaderConfigurationModule) {
        this.module = cdsUploaderConfigurationModule;
    }

    public static CdsUploaderConfigurationModule_ProvideScheduler$AndroidPhotosUploader_releaseFactory create(CdsUploaderConfigurationModule cdsUploaderConfigurationModule) {
        return new CdsUploaderConfigurationModule_ProvideScheduler$AndroidPhotosUploader_releaseFactory(cdsUploaderConfigurationModule);
    }

    public static Scheduler provideScheduler$AndroidPhotosUploader_release(CdsUploaderConfigurationModule cdsUploaderConfigurationModule) {
        return (Scheduler) Preconditions.checkNotNull(cdsUploaderConfigurationModule.provideScheduler$AndroidPhotosUploader_release(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Scheduler mo10268get() {
        return provideScheduler$AndroidPhotosUploader_release(this.module);
    }
}
