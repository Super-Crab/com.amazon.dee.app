package com.amazon.photos.uploader.internal.workers;

import androidx.work.WorkManager;
import com.amazon.clouddrive.android.core.interfaces.Metrics;
import com.amazon.photos.uploader.QueueManager;
import com.amazon.photos.uploader.internal.NotificationUpdatesNotifier;
import com.amazon.photos.uploader.internal.SchedulerConfiguration;
import com.amazon.photos.uploader.internal.dao.UploadRequestDao;
import com.amazon.photos.uploader.log.UploadLogger;
import dagger.MembersInjector;
import javax.inject.Provider;
/* loaded from: classes13.dex */
public final class SchedulerWorker_MembersInjector implements MembersInjector<SchedulerWorker> {
    private final Provider<UploadRequestDao> p0Provider;
    private final Provider<WorkManager> p0Provider2;
    private final Provider<QueueManager> p0Provider3;
    private final Provider<Metrics> p0Provider4;
    private final Provider<UploadLogger> p0Provider5;
    private final Provider<SchedulerConfiguration> p0Provider6;
    private final Provider<CoroutineWorkerUtil> p0Provider7;
    private final Provider<NotificationUpdatesNotifier> p0Provider8;

    public SchedulerWorker_MembersInjector(Provider<UploadRequestDao> provider, Provider<WorkManager> provider2, Provider<QueueManager> provider3, Provider<Metrics> provider4, Provider<UploadLogger> provider5, Provider<SchedulerConfiguration> provider6, Provider<CoroutineWorkerUtil> provider7, Provider<NotificationUpdatesNotifier> provider8) {
        this.p0Provider = provider;
        this.p0Provider2 = provider2;
        this.p0Provider3 = provider3;
        this.p0Provider4 = provider4;
        this.p0Provider5 = provider5;
        this.p0Provider6 = provider6;
        this.p0Provider7 = provider7;
        this.p0Provider8 = provider8;
    }

    public static MembersInjector<SchedulerWorker> create(Provider<UploadRequestDao> provider, Provider<WorkManager> provider2, Provider<QueueManager> provider3, Provider<Metrics> provider4, Provider<UploadLogger> provider5, Provider<SchedulerConfiguration> provider6, Provider<CoroutineWorkerUtil> provider7, Provider<NotificationUpdatesNotifier> provider8) {
        return new SchedulerWorker_MembersInjector(provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8);
    }

    public static void injectSetCoroutineWorkerUtil(SchedulerWorker schedulerWorker, CoroutineWorkerUtil coroutineWorkerUtil) {
        schedulerWorker.setCoroutineWorkerUtil(coroutineWorkerUtil);
    }

    public static void injectSetLogger(SchedulerWorker schedulerWorker, UploadLogger uploadLogger) {
        schedulerWorker.setLogger(uploadLogger);
    }

    public static void injectSetMetrics(SchedulerWorker schedulerWorker, Metrics metrics) {
        schedulerWorker.setMetrics(metrics);
    }

    public static void injectSetQueueManager(SchedulerWorker schedulerWorker, QueueManager queueManager) {
        schedulerWorker.setQueueManager(queueManager);
    }

    public static void injectSetRequestDao(SchedulerWorker schedulerWorker, UploadRequestDao uploadRequestDao) {
        schedulerWorker.setRequestDao(uploadRequestDao);
    }

    public static void injectSetSchedulerConfiguration(SchedulerWorker schedulerWorker, SchedulerConfiguration schedulerConfiguration) {
        schedulerWorker.setSchedulerConfiguration(schedulerConfiguration);
    }

    public static void injectSetWorkManager(SchedulerWorker schedulerWorker, WorkManager workManager) {
        schedulerWorker.setWorkManager(workManager);
    }

    @Override // dagger.MembersInjector
    public void injectMembers(SchedulerWorker schedulerWorker) {
        injectSetRequestDao(schedulerWorker, this.p0Provider.mo10268get());
        injectSetWorkManager(schedulerWorker, this.p0Provider2.mo10268get());
        injectSetQueueManager(schedulerWorker, this.p0Provider3.mo10268get());
        injectSetMetrics(schedulerWorker, this.p0Provider4.mo10268get());
        injectSetLogger(schedulerWorker, this.p0Provider5.mo10268get());
        injectSetSchedulerConfiguration(schedulerWorker, this.p0Provider6.mo10268get());
        injectSetCoroutineWorkerUtil(schedulerWorker, this.p0Provider7.mo10268get());
        schedulerWorker.setNotificationUpdatesNotifier$AndroidPhotosUploader_release(this.p0Provider8.mo10268get());
    }
}
