package com.amazon.photos.uploader.internal.workers;

import androidx.work.WorkManager;
import com.amazon.clouddrive.android.core.interfaces.Metrics;
import com.amazon.photos.uploader.QueueManager;
import com.amazon.photos.uploader.SchedulingCallback;
import com.amazon.photos.uploader.internal.InternalEvaluator;
import com.amazon.photos.uploader.internal.NotificationUpdatesNotifier;
import com.amazon.photos.uploader.internal.UploadRequestUpdatesNotifier;
import com.amazon.photos.uploader.internal.UploadSummaryTracker;
import com.amazon.photos.uploader.internal.UploaderTransactionRunner;
import com.amazon.photos.uploader.internal.dao.UploadRequestDao;
import com.amazon.photos.uploader.internal.utils.PersistentLogger;
import dagger.MembersInjector;
import javax.inject.Provider;
/* loaded from: classes13.dex */
public final class BlockerEvaluatorWorker_MembersInjector implements MembersInjector<BlockerEvaluatorWorker> {
    private final Provider<QueueManager> p0Provider;
    private final Provider<UploaderTransactionRunner> p0Provider10;
    private final Provider<NotificationUpdatesNotifier> p0Provider11;
    private final Provider<WorkManager> p0Provider2;
    private final Provider<UploadRequestDao> p0Provider3;
    private final Provider<Metrics> p0Provider4;
    private final Provider<PersistentLogger> p0Provider5;
    private final Provider<InternalEvaluator> p0Provider6;
    private final Provider<UploadSummaryTracker> p0Provider7;
    private final Provider<UploadRequestUpdatesNotifier> p0Provider8;
    private final Provider<SchedulingCallback> p0Provider9;

    public BlockerEvaluatorWorker_MembersInjector(Provider<QueueManager> provider, Provider<WorkManager> provider2, Provider<UploadRequestDao> provider3, Provider<Metrics> provider4, Provider<PersistentLogger> provider5, Provider<InternalEvaluator> provider6, Provider<UploadSummaryTracker> provider7, Provider<UploadRequestUpdatesNotifier> provider8, Provider<SchedulingCallback> provider9, Provider<UploaderTransactionRunner> provider10, Provider<NotificationUpdatesNotifier> provider11) {
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
    }

    public static MembersInjector<BlockerEvaluatorWorker> create(Provider<QueueManager> provider, Provider<WorkManager> provider2, Provider<UploadRequestDao> provider3, Provider<Metrics> provider4, Provider<PersistentLogger> provider5, Provider<InternalEvaluator> provider6, Provider<UploadSummaryTracker> provider7, Provider<UploadRequestUpdatesNotifier> provider8, Provider<SchedulingCallback> provider9, Provider<UploaderTransactionRunner> provider10, Provider<NotificationUpdatesNotifier> provider11) {
        return new BlockerEvaluatorWorker_MembersInjector(provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8, provider9, provider10, provider11);
    }

    public static void injectSetInternalEvaluator(BlockerEvaluatorWorker blockerEvaluatorWorker, InternalEvaluator internalEvaluator) {
        blockerEvaluatorWorker.setInternalEvaluator(internalEvaluator);
    }

    public static void injectSetLogger(BlockerEvaluatorWorker blockerEvaluatorWorker, PersistentLogger persistentLogger) {
        blockerEvaluatorWorker.setLogger(persistentLogger);
    }

    public static void injectSetMetrics(BlockerEvaluatorWorker blockerEvaluatorWorker, Metrics metrics) {
        blockerEvaluatorWorker.setMetrics(metrics);
    }

    public static void injectSetQueueManager(BlockerEvaluatorWorker blockerEvaluatorWorker, QueueManager queueManager) {
        blockerEvaluatorWorker.setQueueManager(queueManager);
    }

    public static void injectSetRequestDao(BlockerEvaluatorWorker blockerEvaluatorWorker, UploadRequestDao uploadRequestDao) {
        blockerEvaluatorWorker.setRequestDao(uploadRequestDao);
    }

    public static void injectSetSchedulingCallback(BlockerEvaluatorWorker blockerEvaluatorWorker, SchedulingCallback schedulingCallback) {
        blockerEvaluatorWorker.setSchedulingCallback(schedulingCallback);
    }

    public static void injectSetTransactionRunner(BlockerEvaluatorWorker blockerEvaluatorWorker, UploaderTransactionRunner uploaderTransactionRunner) {
        blockerEvaluatorWorker.setTransactionRunner(uploaderTransactionRunner);
    }

    public static void injectSetUploadRequestUpdatesNotifier(BlockerEvaluatorWorker blockerEvaluatorWorker, UploadRequestUpdatesNotifier uploadRequestUpdatesNotifier) {
        blockerEvaluatorWorker.setUploadRequestUpdatesNotifier(uploadRequestUpdatesNotifier);
    }

    public static void injectSetUploadSummaryTracker(BlockerEvaluatorWorker blockerEvaluatorWorker, UploadSummaryTracker uploadSummaryTracker) {
        blockerEvaluatorWorker.setUploadSummaryTracker(uploadSummaryTracker);
    }

    public static void injectSetWorkManager(BlockerEvaluatorWorker blockerEvaluatorWorker, WorkManager workManager) {
        blockerEvaluatorWorker.setWorkManager(workManager);
    }

    @Override // dagger.MembersInjector
    public void injectMembers(BlockerEvaluatorWorker blockerEvaluatorWorker) {
        injectSetQueueManager(blockerEvaluatorWorker, this.p0Provider.mo10268get());
        injectSetWorkManager(blockerEvaluatorWorker, this.p0Provider2.mo10268get());
        injectSetRequestDao(blockerEvaluatorWorker, this.p0Provider3.mo10268get());
        injectSetMetrics(blockerEvaluatorWorker, this.p0Provider4.mo10268get());
        injectSetLogger(blockerEvaluatorWorker, this.p0Provider5.mo10268get());
        injectSetInternalEvaluator(blockerEvaluatorWorker, this.p0Provider6.mo10268get());
        injectSetUploadSummaryTracker(blockerEvaluatorWorker, this.p0Provider7.mo10268get());
        injectSetUploadRequestUpdatesNotifier(blockerEvaluatorWorker, this.p0Provider8.mo10268get());
        injectSetSchedulingCallback(blockerEvaluatorWorker, this.p0Provider9.mo10268get());
        injectSetTransactionRunner(blockerEvaluatorWorker, this.p0Provider10.mo10268get());
        blockerEvaluatorWorker.setNotificationUpdatesNotifier$AndroidPhotosUploader_release(this.p0Provider11.mo10268get());
    }
}
