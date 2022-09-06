package com.amazon.photos.autosave.internal.workers;

import com.amazon.clouddrive.android.core.interfaces.Logger;
import com.amazon.clouddrive.android.core.interfaces.Metrics;
import com.amazon.photos.autosave.internal.dao.AutosaveBucketDao;
import com.amazon.photos.autosave.internal.dao.AutosaveItemDao;
import com.amazon.photos.autosave.internal.db.AutosaveTransactionRunner;
import com.amazon.photos.autosave.internal.metrics.MetricsHelper;
import com.amazon.photos.uploader.UploadManager;
import dagger.MembersInjector;
import javax.inject.Provider;
/* loaded from: classes13.dex */
public final class CancelUploadsWorker_MembersInjector implements MembersInjector<CancelUploadsWorker> {
    private final Provider<Metrics> p0Provider;
    private final Provider<Logger> p0Provider2;
    private final Provider<AutosaveTransactionRunner> p0Provider3;
    private final Provider<AutosaveItemDao> p0Provider4;
    private final Provider<AutosaveBucketDao> p0Provider5;
    private final Provider<UploadManager> p0Provider6;
    private final Provider<MetricsHelper> p0Provider7;

    public CancelUploadsWorker_MembersInjector(Provider<Metrics> provider, Provider<Logger> provider2, Provider<AutosaveTransactionRunner> provider3, Provider<AutosaveItemDao> provider4, Provider<AutosaveBucketDao> provider5, Provider<UploadManager> provider6, Provider<MetricsHelper> provider7) {
        this.p0Provider = provider;
        this.p0Provider2 = provider2;
        this.p0Provider3 = provider3;
        this.p0Provider4 = provider4;
        this.p0Provider5 = provider5;
        this.p0Provider6 = provider6;
        this.p0Provider7 = provider7;
    }

    public static MembersInjector<CancelUploadsWorker> create(Provider<Metrics> provider, Provider<Logger> provider2, Provider<AutosaveTransactionRunner> provider3, Provider<AutosaveItemDao> provider4, Provider<AutosaveBucketDao> provider5, Provider<UploadManager> provider6, Provider<MetricsHelper> provider7) {
        return new CancelUploadsWorker_MembersInjector(provider, provider2, provider3, provider4, provider5, provider6, provider7);
    }

    public static void injectSetAutosaveBucketDao(CancelUploadsWorker cancelUploadsWorker, AutosaveBucketDao autosaveBucketDao) {
        cancelUploadsWorker.setAutosaveBucketDao(autosaveBucketDao);
    }

    public static void injectSetAutosaveItemDao(CancelUploadsWorker cancelUploadsWorker, AutosaveItemDao autosaveItemDao) {
        cancelUploadsWorker.setAutosaveItemDao(autosaveItemDao);
    }

    public static void injectSetLogger(CancelUploadsWorker cancelUploadsWorker, Logger logger) {
        cancelUploadsWorker.setLogger(logger);
    }

    public static void injectSetMetrics(CancelUploadsWorker cancelUploadsWorker, Metrics metrics) {
        cancelUploadsWorker.setMetrics(metrics);
    }

    public static void injectSetMetricsHelper(CancelUploadsWorker cancelUploadsWorker, MetricsHelper metricsHelper) {
        cancelUploadsWorker.setMetricsHelper(metricsHelper);
    }

    public static void injectSetTransactionRunner(CancelUploadsWorker cancelUploadsWorker, AutosaveTransactionRunner autosaveTransactionRunner) {
        cancelUploadsWorker.setTransactionRunner(autosaveTransactionRunner);
    }

    public static void injectSetUploadManager(CancelUploadsWorker cancelUploadsWorker, UploadManager uploadManager) {
        cancelUploadsWorker.setUploadManager(uploadManager);
    }

    @Override // dagger.MembersInjector
    public void injectMembers(CancelUploadsWorker cancelUploadsWorker) {
        injectSetMetrics(cancelUploadsWorker, this.p0Provider.mo10268get());
        injectSetLogger(cancelUploadsWorker, this.p0Provider2.mo10268get());
        injectSetTransactionRunner(cancelUploadsWorker, this.p0Provider3.mo10268get());
        injectSetAutosaveItemDao(cancelUploadsWorker, this.p0Provider4.mo10268get());
        injectSetAutosaveBucketDao(cancelUploadsWorker, this.p0Provider5.mo10268get());
        injectSetUploadManager(cancelUploadsWorker, this.p0Provider6.mo10268get());
        injectSetMetricsHelper(cancelUploadsWorker, this.p0Provider7.mo10268get());
    }
}
