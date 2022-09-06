package com.amazon.photos.uploader;

import com.amazon.photos.uploader.internal.DestroyableDatabaseWrapper;
import com.amazon.photos.uploader.internal.InternalEvaluator;
import com.amazon.photos.uploader.internal.UploadRequestUpdatesNotifier;
import com.amazon.photos.uploader.internal.UploadSummaryNotifier;
import com.amazon.photos.uploader.log.UploadLogger;
import dagger.MembersInjector;
import javax.inject.Provider;
/* loaded from: classes13.dex */
public final class UploadManager_MembersInjector implements MembersInjector<UploadManager> {
    private final Provider<UploadFrameworkContext> p0Provider;
    private final Provider<UploadRequestUpdatesNotifier> p0Provider2;
    private final Provider<QueueManager> p0Provider3;
    private final Provider<UploadSummaryNotifier> p0Provider4;
    private final Provider<UploadLogger> p0Provider5;
    private final Provider<Uploader> p0Provider6;
    private final Provider<InternalEvaluator> p0Provider7;
    private final Provider<DestroyableDatabaseWrapper> p0Provider8;

    public UploadManager_MembersInjector(Provider<UploadFrameworkContext> provider, Provider<UploadRequestUpdatesNotifier> provider2, Provider<QueueManager> provider3, Provider<UploadSummaryNotifier> provider4, Provider<UploadLogger> provider5, Provider<Uploader> provider6, Provider<InternalEvaluator> provider7, Provider<DestroyableDatabaseWrapper> provider8) {
        this.p0Provider = provider;
        this.p0Provider2 = provider2;
        this.p0Provider3 = provider3;
        this.p0Provider4 = provider4;
        this.p0Provider5 = provider5;
        this.p0Provider6 = provider6;
        this.p0Provider7 = provider7;
        this.p0Provider8 = provider8;
    }

    public static MembersInjector<UploadManager> create(Provider<UploadFrameworkContext> provider, Provider<UploadRequestUpdatesNotifier> provider2, Provider<QueueManager> provider3, Provider<UploadSummaryNotifier> provider4, Provider<UploadLogger> provider5, Provider<Uploader> provider6, Provider<InternalEvaluator> provider7, Provider<DestroyableDatabaseWrapper> provider8) {
        return new UploadManager_MembersInjector(provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8);
    }

    @Override // dagger.MembersInjector
    public void injectMembers(UploadManager uploadManager) {
        uploadManager.setUploadFrameworkContext$AndroidPhotosUploader_release(this.p0Provider.mo10268get());
        uploadManager.setUploadRequestUpdatesNotifier$AndroidPhotosUploader_release(this.p0Provider2.mo10268get());
        uploadManager.setQueueManagerInternal$AndroidPhotosUploader_release(this.p0Provider3.mo10268get());
        uploadManager.setUploadSummaryNotifier$AndroidPhotosUploader_release(this.p0Provider4.mo10268get());
        uploadManager.setLogger$AndroidPhotosUploader_release(this.p0Provider5.mo10268get());
        uploadManager.setUploader$AndroidPhotosUploader_release(this.p0Provider6.mo10268get());
        uploadManager.setInternalEvaluator$AndroidPhotosUploader_release(this.p0Provider7.mo10268get());
        uploadManager.setDatabaseWrapper$AndroidPhotosUploader_release(this.p0Provider8.mo10268get());
    }
}
