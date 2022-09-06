package com.amazon.photos.uploader.internal.dagger.module;

import com.amazon.photos.uploader.internal.UploadRequestUpdatesNotifier;
import com.amazon.photos.uploader.internal.UploadSummaryTracker;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes13.dex */
public final class ConfigurationModule_ProvideUploadRequestUpdatesNotifier$AndroidPhotosUploader_releaseFactory implements Factory<UploadRequestUpdatesNotifier> {
    private final ConfigurationModule module;
    private final Provider<UploadSummaryTracker> uploadSummaryTrackerProvider;

    public ConfigurationModule_ProvideUploadRequestUpdatesNotifier$AndroidPhotosUploader_releaseFactory(ConfigurationModule configurationModule, Provider<UploadSummaryTracker> provider) {
        this.module = configurationModule;
        this.uploadSummaryTrackerProvider = provider;
    }

    public static ConfigurationModule_ProvideUploadRequestUpdatesNotifier$AndroidPhotosUploader_releaseFactory create(ConfigurationModule configurationModule, Provider<UploadSummaryTracker> provider) {
        return new ConfigurationModule_ProvideUploadRequestUpdatesNotifier$AndroidPhotosUploader_releaseFactory(configurationModule, provider);
    }

    public static UploadRequestUpdatesNotifier provideUploadRequestUpdatesNotifier$AndroidPhotosUploader_release(ConfigurationModule configurationModule, UploadSummaryTracker uploadSummaryTracker) {
        return (UploadRequestUpdatesNotifier) Preconditions.checkNotNull(configurationModule.provideUploadRequestUpdatesNotifier$AndroidPhotosUploader_release(uploadSummaryTracker), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public UploadRequestUpdatesNotifier mo10268get() {
        return provideUploadRequestUpdatesNotifier$AndroidPhotosUploader_release(this.module, this.uploadSummaryTrackerProvider.mo10268get());
    }
}
