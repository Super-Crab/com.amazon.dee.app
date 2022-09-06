package com.amazon.photos.uploader.internal.dagger.module;

import com.amazon.photos.uploader.QueueManager;
import com.amazon.photos.uploader.internal.UploadSummaryNotifier;
import com.amazon.photos.uploader.internal.UploadSummaryTracker;
import com.amazon.photos.uploader.internal.dao.UploadRequestDao;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes13.dex */
public final class ConfigurationModule_ProvideUploadSummaryTracker$AndroidPhotosUploader_releaseFactory implements Factory<UploadSummaryTracker> {
    private final ConfigurationModule module;
    private final Provider<QueueManager> queueManagerProvider;
    private final Provider<UploadRequestDao> requestDaoProvider;
    private final Provider<UploadSummaryNotifier> summaryNotifierProvider;

    public ConfigurationModule_ProvideUploadSummaryTracker$AndroidPhotosUploader_releaseFactory(ConfigurationModule configurationModule, Provider<UploadRequestDao> provider, Provider<UploadSummaryNotifier> provider2, Provider<QueueManager> provider3) {
        this.module = configurationModule;
        this.requestDaoProvider = provider;
        this.summaryNotifierProvider = provider2;
        this.queueManagerProvider = provider3;
    }

    public static ConfigurationModule_ProvideUploadSummaryTracker$AndroidPhotosUploader_releaseFactory create(ConfigurationModule configurationModule, Provider<UploadRequestDao> provider, Provider<UploadSummaryNotifier> provider2, Provider<QueueManager> provider3) {
        return new ConfigurationModule_ProvideUploadSummaryTracker$AndroidPhotosUploader_releaseFactory(configurationModule, provider, provider2, provider3);
    }

    public static UploadSummaryTracker provideUploadSummaryTracker$AndroidPhotosUploader_release(ConfigurationModule configurationModule, UploadRequestDao uploadRequestDao, UploadSummaryNotifier uploadSummaryNotifier, QueueManager queueManager) {
        return (UploadSummaryTracker) Preconditions.checkNotNull(configurationModule.provideUploadSummaryTracker$AndroidPhotosUploader_release(uploadRequestDao, uploadSummaryNotifier, queueManager), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public UploadSummaryTracker mo10268get() {
        return provideUploadSummaryTracker$AndroidPhotosUploader_release(this.module, this.requestDaoProvider.mo10268get(), this.summaryNotifierProvider.mo10268get(), this.queueManagerProvider.mo10268get());
    }
}
