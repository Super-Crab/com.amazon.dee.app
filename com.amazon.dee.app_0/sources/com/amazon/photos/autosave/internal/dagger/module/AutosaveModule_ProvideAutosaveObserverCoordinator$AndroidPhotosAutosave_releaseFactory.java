package com.amazon.photos.autosave.internal.dagger.module;

import android.content.SharedPreferences;
import com.amazon.clouddrive.android.core.interfaces.Metrics;
import com.amazon.photos.autosave.internal.AutosaveOperations;
import com.amazon.photos.autosave.internal.dao.AutosaveBucketDao;
import com.amazon.photos.autosave.internal.dao.AutosaveItemDao;
import com.amazon.photos.autosave.internal.db.AutosaveTransactionRunner;
import com.amazon.photos.autosave.internal.metrics.AutosaveLatencyRecorder;
import com.amazon.photos.autosave.internal.observers.AutosaveEventNotifier;
import com.amazon.photos.autosave.internal.observers.AutosaveObserverCoordinator;
import com.amazon.photos.autosave.internal.preferences.InternalPreferences;
import com.amazon.photos.discovery.Discovery;
import com.amazon.photos.uploader.UploadManager;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes13.dex */
public final class AutosaveModule_ProvideAutosaveObserverCoordinator$AndroidPhotosAutosave_releaseFactory implements Factory<AutosaveObserverCoordinator> {
    private final Provider<AutosaveBucketDao> autosaveBucketDaoProvider;
    private final Provider<AutosaveEventNotifier> autosaveEventNotifierProvider;
    private final Provider<AutosaveItemDao> autosaveItemDaoProvider;
    private final Provider<AutosaveLatencyRecorder> autosaveLatencyRecorderProvider;
    private final Provider<InternalPreferences> autosavePreferencesProvider;
    private final Provider<Discovery> discoveryProvider;
    private final Provider<Metrics> metricsProvider;
    private final AutosaveModule module;
    private final Provider<AutosaveOperations> operationsProvider;
    private final Provider<SharedPreferences> sharedPreferencesProvider;
    private final Provider<AutosaveTransactionRunner> transactionRunnerProvider;
    private final Provider<UploadManager> uploadManagerProvider;

    public AutosaveModule_ProvideAutosaveObserverCoordinator$AndroidPhotosAutosave_releaseFactory(AutosaveModule autosaveModule, Provider<Discovery> provider, Provider<UploadManager> provider2, Provider<AutosaveOperations> provider3, Provider<AutosaveItemDao> provider4, Provider<AutosaveBucketDao> provider5, Provider<InternalPreferences> provider6, Provider<AutosaveTransactionRunner> provider7, Provider<Metrics> provider8, Provider<AutosaveEventNotifier> provider9, Provider<SharedPreferences> provider10, Provider<AutosaveLatencyRecorder> provider11) {
        this.module = autosaveModule;
        this.discoveryProvider = provider;
        this.uploadManagerProvider = provider2;
        this.operationsProvider = provider3;
        this.autosaveItemDaoProvider = provider4;
        this.autosaveBucketDaoProvider = provider5;
        this.autosavePreferencesProvider = provider6;
        this.transactionRunnerProvider = provider7;
        this.metricsProvider = provider8;
        this.autosaveEventNotifierProvider = provider9;
        this.sharedPreferencesProvider = provider10;
        this.autosaveLatencyRecorderProvider = provider11;
    }

    public static AutosaveModule_ProvideAutosaveObserverCoordinator$AndroidPhotosAutosave_releaseFactory create(AutosaveModule autosaveModule, Provider<Discovery> provider, Provider<UploadManager> provider2, Provider<AutosaveOperations> provider3, Provider<AutosaveItemDao> provider4, Provider<AutosaveBucketDao> provider5, Provider<InternalPreferences> provider6, Provider<AutosaveTransactionRunner> provider7, Provider<Metrics> provider8, Provider<AutosaveEventNotifier> provider9, Provider<SharedPreferences> provider10, Provider<AutosaveLatencyRecorder> provider11) {
        return new AutosaveModule_ProvideAutosaveObserverCoordinator$AndroidPhotosAutosave_releaseFactory(autosaveModule, provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8, provider9, provider10, provider11);
    }

    public static AutosaveObserverCoordinator provideAutosaveObserverCoordinator$AndroidPhotosAutosave_release(AutosaveModule autosaveModule, Discovery discovery, UploadManager uploadManager, AutosaveOperations autosaveOperations, AutosaveItemDao autosaveItemDao, AutosaveBucketDao autosaveBucketDao, InternalPreferences internalPreferences, AutosaveTransactionRunner autosaveTransactionRunner, Metrics metrics, AutosaveEventNotifier autosaveEventNotifier, SharedPreferences sharedPreferences, AutosaveLatencyRecorder autosaveLatencyRecorder) {
        return (AutosaveObserverCoordinator) Preconditions.checkNotNull(autosaveModule.provideAutosaveObserverCoordinator$AndroidPhotosAutosave_release(discovery, uploadManager, autosaveOperations, autosaveItemDao, autosaveBucketDao, internalPreferences, autosaveTransactionRunner, metrics, autosaveEventNotifier, sharedPreferences, autosaveLatencyRecorder), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public AutosaveObserverCoordinator mo10268get() {
        return provideAutosaveObserverCoordinator$AndroidPhotosAutosave_release(this.module, this.discoveryProvider.mo10268get(), this.uploadManagerProvider.mo10268get(), this.operationsProvider.mo10268get(), this.autosaveItemDaoProvider.mo10268get(), this.autosaveBucketDaoProvider.mo10268get(), this.autosavePreferencesProvider.mo10268get(), this.transactionRunnerProvider.mo10268get(), this.metricsProvider.mo10268get(), this.autosaveEventNotifierProvider.mo10268get(), this.sharedPreferencesProvider.mo10268get(), this.autosaveLatencyRecorderProvider.mo10268get());
    }
}
