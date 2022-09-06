package com.amazon.photos.uploader.internal.workers;

import androidx.work.WorkManager;
import com.amazon.clouddrive.android.core.interfaces.Metrics;
import com.amazon.photos.uploader.SchedulingCallback;
import com.amazon.photos.uploader.internal.InternalEvaluator;
import com.amazon.photos.uploader.internal.NotificationUpdatesNotifier;
import com.amazon.photos.uploader.log.UploadLogger;
import dagger.MembersInjector;
import javax.inject.Provider;
/* loaded from: classes13.dex */
public final class ReevaluateWorker_MembersInjector implements MembersInjector<ReevaluateWorker> {
    private final Provider<WorkManager> p0Provider;
    private final Provider<Metrics> p0Provider2;
    private final Provider<UploadLogger> p0Provider3;
    private final Provider<SchedulingCallback> p0Provider4;
    private final Provider<InternalEvaluator> p0Provider5;
    private final Provider<NotificationUpdatesNotifier> p0Provider6;

    public ReevaluateWorker_MembersInjector(Provider<WorkManager> provider, Provider<Metrics> provider2, Provider<UploadLogger> provider3, Provider<SchedulingCallback> provider4, Provider<InternalEvaluator> provider5, Provider<NotificationUpdatesNotifier> provider6) {
        this.p0Provider = provider;
        this.p0Provider2 = provider2;
        this.p0Provider3 = provider3;
        this.p0Provider4 = provider4;
        this.p0Provider5 = provider5;
        this.p0Provider6 = provider6;
    }

    public static MembersInjector<ReevaluateWorker> create(Provider<WorkManager> provider, Provider<Metrics> provider2, Provider<UploadLogger> provider3, Provider<SchedulingCallback> provider4, Provider<InternalEvaluator> provider5, Provider<NotificationUpdatesNotifier> provider6) {
        return new ReevaluateWorker_MembersInjector(provider, provider2, provider3, provider4, provider5, provider6);
    }

    public static void injectSetInternalEvaluator(ReevaluateWorker reevaluateWorker, InternalEvaluator internalEvaluator) {
        reevaluateWorker.setInternalEvaluator(internalEvaluator);
    }

    public static void injectSetLogger(ReevaluateWorker reevaluateWorker, UploadLogger uploadLogger) {
        reevaluateWorker.setLogger(uploadLogger);
    }

    public static void injectSetMetrics(ReevaluateWorker reevaluateWorker, Metrics metrics) {
        reevaluateWorker.setMetrics(metrics);
    }

    public static void injectSetSchedulingCallback(ReevaluateWorker reevaluateWorker, SchedulingCallback schedulingCallback) {
        reevaluateWorker.setSchedulingCallback(schedulingCallback);
    }

    public static void injectSetWorkManager(ReevaluateWorker reevaluateWorker, WorkManager workManager) {
        reevaluateWorker.setWorkManager(workManager);
    }

    @Override // dagger.MembersInjector
    public void injectMembers(ReevaluateWorker reevaluateWorker) {
        injectSetWorkManager(reevaluateWorker, this.p0Provider.mo10268get());
        injectSetMetrics(reevaluateWorker, this.p0Provider2.mo10268get());
        injectSetLogger(reevaluateWorker, this.p0Provider3.mo10268get());
        injectSetSchedulingCallback(reevaluateWorker, this.p0Provider4.mo10268get());
        injectSetInternalEvaluator(reevaluateWorker, this.p0Provider5.mo10268get());
        reevaluateWorker.setNotificationUpdatesNotifier$AndroidPhotosUploader_release(this.p0Provider6.mo10268get());
    }
}
