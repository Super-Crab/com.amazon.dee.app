package com.amazon.photos.uploader;

import androidx.work.WorkManager;
import com.amazon.clouddrive.android.core.interfaces.Metrics;
import com.amazon.photos.uploader.blockers.BackoffBlockerEvaluator;
import com.amazon.photos.uploader.blockers.PauseResumeState;
import com.amazon.photos.uploader.internal.InternalEvaluator;
import com.amazon.photos.uploader.internal.NotificationUpdatesNotifier;
import com.amazon.photos.uploader.internal.UploadRequestUpdatesNotifier;
import com.amazon.photos.uploader.internal.UploadSummaryTracker;
import com.amazon.photos.uploader.internal.dao.UploadRequestDao;
import com.amazon.photos.uploader.internal.utils.SystemUtil;
import com.amazon.photos.uploader.log.UploadLogger;
import dagger.MembersInjector;
import javax.inject.Provider;
/* loaded from: classes13.dex */
public final class UploaderOperations_MembersInjector implements MembersInjector<UploaderOperations> {
    private final Provider<UploadRequestDao> p0Provider;
    private final Provider<BackoffBlockerEvaluator> p0Provider10;
    private final Provider<SystemUtil> p0Provider11;
    private final Provider<NotificationUpdatesNotifier> p0Provider12;
    private final Provider<Uploader> p0Provider13;
    private final Provider<QueueManager> p0Provider14;
    private final Provider<UploadSummaryTracker> p0Provider15;
    private final Provider<InternalEvaluator> p0Provider2;
    private final Provider<UploadFrameworkContext> p0Provider3;
    private final Provider<UploadRequestUpdatesNotifier> p0Provider4;
    private final Provider<Metrics> p0Provider5;
    private final Provider<UploadLogger> p0Provider6;
    private final Provider<PauseResumeState> p0Provider7;
    private final Provider<SchedulingCallback> p0Provider8;
    private final Provider<WorkManager> p0Provider9;

    public UploaderOperations_MembersInjector(Provider<UploadRequestDao> provider, Provider<InternalEvaluator> provider2, Provider<UploadFrameworkContext> provider3, Provider<UploadRequestUpdatesNotifier> provider4, Provider<Metrics> provider5, Provider<UploadLogger> provider6, Provider<PauseResumeState> provider7, Provider<SchedulingCallback> provider8, Provider<WorkManager> provider9, Provider<BackoffBlockerEvaluator> provider10, Provider<SystemUtil> provider11, Provider<NotificationUpdatesNotifier> provider12, Provider<Uploader> provider13, Provider<QueueManager> provider14, Provider<UploadSummaryTracker> provider15) {
        this.p0Provider = provider;
        this.p0Provider2 = provider2;
        this.p0Provider3 = provider3;
        this.p0Provider4 = provider4;
        this.p0Provider5 = provider5;
        this.p0Provider6 = provider6;
        this.p0Provider7 = provider7;
        this.p0Provider8 = provider8;
        this.p0Provider9 = provider9;
        this.p0Provider10 = provider10;
        this.p0Provider11 = provider11;
        this.p0Provider12 = provider12;
        this.p0Provider13 = provider13;
        this.p0Provider14 = provider14;
        this.p0Provider15 = provider15;
    }

    public static MembersInjector<UploaderOperations> create(Provider<UploadRequestDao> provider, Provider<InternalEvaluator> provider2, Provider<UploadFrameworkContext> provider3, Provider<UploadRequestUpdatesNotifier> provider4, Provider<Metrics> provider5, Provider<UploadLogger> provider6, Provider<PauseResumeState> provider7, Provider<SchedulingCallback> provider8, Provider<WorkManager> provider9, Provider<BackoffBlockerEvaluator> provider10, Provider<SystemUtil> provider11, Provider<NotificationUpdatesNotifier> provider12, Provider<Uploader> provider13, Provider<QueueManager> provider14, Provider<UploadSummaryTracker> provider15) {
        return new UploaderOperations_MembersInjector(provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8, provider9, provider10, provider11, provider12, provider13, provider14, provider15);
    }

    @Override // dagger.MembersInjector
    public void injectMembers(UploaderOperations uploaderOperations) {
        uploaderOperations.setRequestDao$AndroidPhotosUploader_release(this.p0Provider.mo10268get());
        uploaderOperations.setInternalEvaluator$AndroidPhotosUploader_release(this.p0Provider2.mo10268get());
        uploaderOperations.setUploadFrameworkContext$AndroidPhotosUploader_release(this.p0Provider3.mo10268get());
        uploaderOperations.setUploadRequestUpdatesNotifier$AndroidPhotosUploader_release(this.p0Provider4.mo10268get());
        uploaderOperations.setMetrics$AndroidPhotosUploader_release(this.p0Provider5.mo10268get());
        uploaderOperations.setLogger$AndroidPhotosUploader_release(this.p0Provider6.mo10268get());
        uploaderOperations.setPauseResumeState$AndroidPhotosUploader_release(this.p0Provider7.mo10268get());
        uploaderOperations.setSchedulingCallback$AndroidPhotosUploader_release(this.p0Provider8.mo10268get());
        uploaderOperations.setWorkManager$AndroidPhotosUploader_release(this.p0Provider9.mo10268get());
        uploaderOperations.setBackoffBlockerEvaluator$AndroidPhotosUploader_release(this.p0Provider10.mo10268get());
        uploaderOperations.setSystemUtil$AndroidPhotosUploader_release(this.p0Provider11.mo10268get());
        uploaderOperations.setNotificationUpdatesNotifier$AndroidPhotosUploader_release(this.p0Provider12.mo10268get());
        uploaderOperations.setUploader$AndroidPhotosUploader_release(this.p0Provider13.mo10268get());
        uploaderOperations.setQueueManager$AndroidPhotosUploader_release(this.p0Provider14.mo10268get());
        uploaderOperations.setUploadSummaryTracker$AndroidPhotosUploader_release(this.p0Provider15.mo10268get());
    }
}
