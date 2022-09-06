package com.amazon.photos.autosave.internal.workers;

import com.amazon.clouddrive.android.core.interfaces.Metrics;
import com.amazon.photos.autosave.internal.db.AutosaveTransactionRunner;
import com.amazon.photos.autosave.internal.metrics.MetricsHelper;
import com.amazon.photos.autosave.internal.upload.UploadHelper;
import com.amazon.photos.uploader.UploadManager;
import dagger.MembersInjector;
import javax.inject.Provider;
/* loaded from: classes13.dex */
public final class CancelAndRescheduleWorker_MembersInjector implements MembersInjector<CancelAndRescheduleWorker> {
    private final Provider<Metrics> p0Provider;
    private final Provider<AutosaveTransactionRunner> p0Provider2;
    private final Provider<UploadManager> p0Provider3;
    private final Provider<UploadHelper> p0Provider4;
    private final Provider<MetricsHelper> p0Provider5;

    public CancelAndRescheduleWorker_MembersInjector(Provider<Metrics> provider, Provider<AutosaveTransactionRunner> provider2, Provider<UploadManager> provider3, Provider<UploadHelper> provider4, Provider<MetricsHelper> provider5) {
        this.p0Provider = provider;
        this.p0Provider2 = provider2;
        this.p0Provider3 = provider3;
        this.p0Provider4 = provider4;
        this.p0Provider5 = provider5;
    }

    public static MembersInjector<CancelAndRescheduleWorker> create(Provider<Metrics> provider, Provider<AutosaveTransactionRunner> provider2, Provider<UploadManager> provider3, Provider<UploadHelper> provider4, Provider<MetricsHelper> provider5) {
        return new CancelAndRescheduleWorker_MembersInjector(provider, provider2, provider3, provider4, provider5);
    }

    public static void injectSetMetrics(CancelAndRescheduleWorker cancelAndRescheduleWorker, Metrics metrics) {
        cancelAndRescheduleWorker.setMetrics(metrics);
    }

    public static void injectSetMetricsHelper(CancelAndRescheduleWorker cancelAndRescheduleWorker, MetricsHelper metricsHelper) {
        cancelAndRescheduleWorker.setMetricsHelper(metricsHelper);
    }

    public static void injectSetTransactionRunner(CancelAndRescheduleWorker cancelAndRescheduleWorker, AutosaveTransactionRunner autosaveTransactionRunner) {
        cancelAndRescheduleWorker.setTransactionRunner(autosaveTransactionRunner);
    }

    public static void injectSetUploadHelper(CancelAndRescheduleWorker cancelAndRescheduleWorker, UploadHelper uploadHelper) {
        cancelAndRescheduleWorker.setUploadHelper(uploadHelper);
    }

    public static void injectSetUploadManager(CancelAndRescheduleWorker cancelAndRescheduleWorker, UploadManager uploadManager) {
        cancelAndRescheduleWorker.setUploadManager(uploadManager);
    }

    @Override // dagger.MembersInjector
    public void injectMembers(CancelAndRescheduleWorker cancelAndRescheduleWorker) {
        injectSetMetrics(cancelAndRescheduleWorker, this.p0Provider.mo10268get());
        injectSetTransactionRunner(cancelAndRescheduleWorker, this.p0Provider2.mo10268get());
        injectSetUploadManager(cancelAndRescheduleWorker, this.p0Provider3.mo10268get());
        injectSetUploadHelper(cancelAndRescheduleWorker, this.p0Provider4.mo10268get());
        injectSetMetricsHelper(cancelAndRescheduleWorker, this.p0Provider5.mo10268get());
    }
}
