package com.amazon.photos.uploader.internal.dagger.module;

import androidx.work.WorkManager;
import com.amazon.photos.uploader.SchedulingCallback;
import com.amazon.photos.uploader.UploadFrameworkContext;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes13.dex */
public final class ConfigurationModule_ProvidesSchedulingCallback$AndroidPhotosUploader_releaseFactory implements Factory<SchedulingCallback> {
    private final ConfigurationModule module;
    private final Provider<UploadFrameworkContext> uploadFrameworkContextProvider;
    private final Provider<WorkManager> workManagerProvider;

    public ConfigurationModule_ProvidesSchedulingCallback$AndroidPhotosUploader_releaseFactory(ConfigurationModule configurationModule, Provider<UploadFrameworkContext> provider, Provider<WorkManager> provider2) {
        this.module = configurationModule;
        this.uploadFrameworkContextProvider = provider;
        this.workManagerProvider = provider2;
    }

    public static ConfigurationModule_ProvidesSchedulingCallback$AndroidPhotosUploader_releaseFactory create(ConfigurationModule configurationModule, Provider<UploadFrameworkContext> provider, Provider<WorkManager> provider2) {
        return new ConfigurationModule_ProvidesSchedulingCallback$AndroidPhotosUploader_releaseFactory(configurationModule, provider, provider2);
    }

    public static SchedulingCallback providesSchedulingCallback$AndroidPhotosUploader_release(ConfigurationModule configurationModule, UploadFrameworkContext uploadFrameworkContext, WorkManager workManager) {
        return (SchedulingCallback) Preconditions.checkNotNull(configurationModule.providesSchedulingCallback$AndroidPhotosUploader_release(uploadFrameworkContext, workManager), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public SchedulingCallback mo10268get() {
        return providesSchedulingCallback$AndroidPhotosUploader_release(this.module, this.uploadFrameworkContextProvider.mo10268get(), this.workManagerProvider.mo10268get());
    }
}
