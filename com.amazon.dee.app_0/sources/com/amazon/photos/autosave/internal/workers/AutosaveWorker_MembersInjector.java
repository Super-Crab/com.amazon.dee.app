package com.amazon.photos.autosave.internal.workers;

import android.content.SharedPreferences;
import com.amazon.clouddrive.android.core.interfaces.Logger;
import com.amazon.clouddrive.android.core.interfaces.Metrics;
import com.amazon.photos.autosave.internal.AutosaveOperations;
import com.amazon.photos.autosave.internal.dao.AutosaveBucketDao;
import com.amazon.photos.autosave.internal.dao.AutosaveItemDao;
import com.amazon.photos.autosave.internal.db.AutosaveTransactionRunner;
import com.amazon.photos.autosave.internal.metrics.MetricsHelper;
import com.amazon.photos.autosave.internal.observers.AutosaveEventNotifier;
import com.amazon.photos.autosave.internal.preferences.InternalPreferences;
import com.amazon.photos.autosave.internal.upload.UploadHelper;
import com.amazon.photos.autosave.internal.utils.SystemUtil;
import com.amazon.photos.discovery.Discovery;
import com.amazon.photos.uploader.UploadManager;
import dagger.MembersInjector;
import javax.inject.Provider;
/* loaded from: classes13.dex */
public final class AutosaveWorker_MembersInjector implements MembersInjector<AutosaveWorker> {
    private final Provider<Logger> p0Provider;
    private final Provider<MetricsHelper> p0Provider10;
    private final Provider<SystemUtil> p0Provider11;
    private final Provider<AutosaveEventNotifier> p0Provider12;
    private final Provider<SharedPreferences> p0Provider13;
    private final Provider<AutosaveOperations> p0Provider14;
    private final Provider<Metrics> p0Provider2;
    private final Provider<AutosaveTransactionRunner> p0Provider3;
    private final Provider<Discovery> p0Provider4;
    private final Provider<UploadManager> p0Provider5;
    private final Provider<AutosaveItemDao> p0Provider6;
    private final Provider<AutosaveBucketDao> p0Provider7;
    private final Provider<InternalPreferences> p0Provider8;
    private final Provider<UploadHelper> p0Provider9;

    public AutosaveWorker_MembersInjector(Provider<Logger> provider, Provider<Metrics> provider2, Provider<AutosaveTransactionRunner> provider3, Provider<Discovery> provider4, Provider<UploadManager> provider5, Provider<AutosaveItemDao> provider6, Provider<AutosaveBucketDao> provider7, Provider<InternalPreferences> provider8, Provider<UploadHelper> provider9, Provider<MetricsHelper> provider10, Provider<SystemUtil> provider11, Provider<AutosaveEventNotifier> provider12, Provider<SharedPreferences> provider13, Provider<AutosaveOperations> provider14) {
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
    }

    public static MembersInjector<AutosaveWorker> create(Provider<Logger> provider, Provider<Metrics> provider2, Provider<AutosaveTransactionRunner> provider3, Provider<Discovery> provider4, Provider<UploadManager> provider5, Provider<AutosaveItemDao> provider6, Provider<AutosaveBucketDao> provider7, Provider<InternalPreferences> provider8, Provider<UploadHelper> provider9, Provider<MetricsHelper> provider10, Provider<SystemUtil> provider11, Provider<AutosaveEventNotifier> provider12, Provider<SharedPreferences> provider13, Provider<AutosaveOperations> provider14) {
        return new AutosaveWorker_MembersInjector(provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8, provider9, provider10, provider11, provider12, provider13, provider14);
    }

    public static void injectSetAutosaveBucketDao(AutosaveWorker autosaveWorker, AutosaveBucketDao autosaveBucketDao) {
        autosaveWorker.setAutosaveBucketDao(autosaveBucketDao);
    }

    public static void injectSetAutosaveEventNotifier(AutosaveWorker autosaveWorker, AutosaveEventNotifier autosaveEventNotifier) {
        autosaveWorker.setAutosaveEventNotifier(autosaveEventNotifier);
    }

    public static void injectSetAutosaveItemDao(AutosaveWorker autosaveWorker, AutosaveItemDao autosaveItemDao) {
        autosaveWorker.setAutosaveItemDao(autosaveItemDao);
    }

    public static void injectSetAutosaveOperations(AutosaveWorker autosaveWorker, AutosaveOperations autosaveOperations) {
        autosaveWorker.setAutosaveOperations(autosaveOperations);
    }

    public static void injectSetAutosavePreferences(AutosaveWorker autosaveWorker, InternalPreferences internalPreferences) {
        autosaveWorker.setAutosavePreferences(internalPreferences);
    }

    public static void injectSetDiscovery(AutosaveWorker autosaveWorker, Discovery discovery) {
        autosaveWorker.setDiscovery(discovery);
    }

    public static void injectSetLogger(AutosaveWorker autosaveWorker, Logger logger) {
        autosaveWorker.setLogger(logger);
    }

    public static void injectSetMetrics(AutosaveWorker autosaveWorker, Metrics metrics) {
        autosaveWorker.setMetrics(metrics);
    }

    public static void injectSetMetricsHelper(AutosaveWorker autosaveWorker, MetricsHelper metricsHelper) {
        autosaveWorker.setMetricsHelper(metricsHelper);
    }

    public static void injectSetSharedPreferences(AutosaveWorker autosaveWorker, SharedPreferences sharedPreferences) {
        autosaveWorker.setSharedPreferences(sharedPreferences);
    }

    public static void injectSetSystemUtil(AutosaveWorker autosaveWorker, SystemUtil systemUtil) {
        autosaveWorker.setSystemUtil(systemUtil);
    }

    public static void injectSetTransactionRunner(AutosaveWorker autosaveWorker, AutosaveTransactionRunner autosaveTransactionRunner) {
        autosaveWorker.setTransactionRunner(autosaveTransactionRunner);
    }

    public static void injectSetUploadHelper(AutosaveWorker autosaveWorker, UploadHelper uploadHelper) {
        autosaveWorker.setUploadHelper(uploadHelper);
    }

    public static void injectSetUploadManager(AutosaveWorker autosaveWorker, UploadManager uploadManager) {
        autosaveWorker.setUploadManager(uploadManager);
    }

    @Override // dagger.MembersInjector
    public void injectMembers(AutosaveWorker autosaveWorker) {
        injectSetLogger(autosaveWorker, this.p0Provider.mo10268get());
        injectSetMetrics(autosaveWorker, this.p0Provider2.mo10268get());
        injectSetTransactionRunner(autosaveWorker, this.p0Provider3.mo10268get());
        injectSetDiscovery(autosaveWorker, this.p0Provider4.mo10268get());
        injectSetUploadManager(autosaveWorker, this.p0Provider5.mo10268get());
        injectSetAutosaveItemDao(autosaveWorker, this.p0Provider6.mo10268get());
        injectSetAutosaveBucketDao(autosaveWorker, this.p0Provider7.mo10268get());
        injectSetAutosavePreferences(autosaveWorker, this.p0Provider8.mo10268get());
        injectSetUploadHelper(autosaveWorker, this.p0Provider9.mo10268get());
        injectSetMetricsHelper(autosaveWorker, this.p0Provider10.mo10268get());
        injectSetSystemUtil(autosaveWorker, this.p0Provider11.mo10268get());
        injectSetAutosaveEventNotifier(autosaveWorker, this.p0Provider12.mo10268get());
        injectSetSharedPreferences(autosaveWorker, this.p0Provider13.mo10268get());
        injectSetAutosaveOperations(autosaveWorker, this.p0Provider14.mo10268get());
    }
}
