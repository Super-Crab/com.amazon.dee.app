package com.amazon.photos.uploader.internal.dagger.module;

import com.amazon.photos.uploader.dao.BlockerDao;
import com.amazon.photos.uploader.internal.UploadSummaryTracker;
import com.amazon.photos.uploader.internal.livedata.GlobalBlockerLiveDataProvider;
import com.amazon.photos.uploader.internal.livedata.QueueBlockerLiveDataProvider;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes13.dex */
public final class DatabaseModule_ProvideBlockerDao$AndroidPhotosUploader_releaseFactory implements Factory<BlockerDao> {
    private final Provider<GlobalBlockerLiveDataProvider> globalBlockerLiveDataProvider;
    private final DatabaseModule module;
    private final Provider<QueueBlockerLiveDataProvider> queueBlockerLiveDataProvider;
    private final Provider<UploadSummaryTracker> uploadSummaryTrackerProvider;

    public DatabaseModule_ProvideBlockerDao$AndroidPhotosUploader_releaseFactory(DatabaseModule databaseModule, Provider<GlobalBlockerLiveDataProvider> provider, Provider<QueueBlockerLiveDataProvider> provider2, Provider<UploadSummaryTracker> provider3) {
        this.module = databaseModule;
        this.globalBlockerLiveDataProvider = provider;
        this.queueBlockerLiveDataProvider = provider2;
        this.uploadSummaryTrackerProvider = provider3;
    }

    public static DatabaseModule_ProvideBlockerDao$AndroidPhotosUploader_releaseFactory create(DatabaseModule databaseModule, Provider<GlobalBlockerLiveDataProvider> provider, Provider<QueueBlockerLiveDataProvider> provider2, Provider<UploadSummaryTracker> provider3) {
        return new DatabaseModule_ProvideBlockerDao$AndroidPhotosUploader_releaseFactory(databaseModule, provider, provider2, provider3);
    }

    public static BlockerDao provideBlockerDao$AndroidPhotosUploader_release(DatabaseModule databaseModule, GlobalBlockerLiveDataProvider globalBlockerLiveDataProvider, QueueBlockerLiveDataProvider queueBlockerLiveDataProvider, UploadSummaryTracker uploadSummaryTracker) {
        return (BlockerDao) Preconditions.checkNotNull(databaseModule.provideBlockerDao$AndroidPhotosUploader_release(globalBlockerLiveDataProvider, queueBlockerLiveDataProvider, uploadSummaryTracker), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public BlockerDao mo10268get() {
        return provideBlockerDao$AndroidPhotosUploader_release(this.module, this.globalBlockerLiveDataProvider.mo10268get(), this.queueBlockerLiveDataProvider.mo10268get(), this.uploadSummaryTrackerProvider.mo10268get());
    }
}
