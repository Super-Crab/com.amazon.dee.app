package com.amazon.photos.autosave.internal.dagger.component;

import android.content.SharedPreferences;
import androidx.work.WorkManager;
import com.amazon.clouddrive.android.core.interfaces.Logger;
import com.amazon.clouddrive.android.core.interfaces.Metrics;
import com.amazon.photos.autosave.AutosaveFrameworkContext;
import com.amazon.photos.autosave.AutosaveManager;
import com.amazon.photos.autosave.internal.AutosaveOperations;
import com.amazon.photos.autosave.internal.coroutines.DispatcherProvider;
import com.amazon.photos.autosave.internal.dagger.module.AutosaveModule;
import com.amazon.photos.autosave.internal.dagger.module.AutosaveModule_ProvideAutosaveEventNotifier$AndroidPhotosAutosave_releaseFactory;
import com.amazon.photos.autosave.internal.dagger.module.AutosaveModule_ProvideAutosaveFrameworkContext$AndroidPhotosAutosave_releaseFactory;
import com.amazon.photos.autosave.internal.dagger.module.AutosaveModule_ProvideAutosaveLatencyRecorder$AndroidPhotosAutosave_releaseFactory;
import com.amazon.photos.autosave.internal.dagger.module.AutosaveModule_ProvideAutosaveObserverCoordinator$AndroidPhotosAutosave_releaseFactory;
import com.amazon.photos.autosave.internal.dagger.module.AutosaveModule_ProvideAutosaveOperations$AndroidPhotosAutosave_releaseFactory;
import com.amazon.photos.autosave.internal.dagger.module.AutosaveModule_ProvideAutosavePreferences$AndroidPhotosAutosave_releaseFactory;
import com.amazon.photos.autosave.internal.dagger.module.AutosaveModule_ProvideAutosavePreferencesListener$AndroidPhotosAutosave_releaseFactory;
import com.amazon.photos.autosave.internal.dagger.module.AutosaveModule_ProvideDateUtils$AndroidPhotosAutosave_releaseFactory;
import com.amazon.photos.autosave.internal.dagger.module.AutosaveModule_ProvideDiscovery$AndroidPhotosAutosave_releaseFactory;
import com.amazon.photos.autosave.internal.dagger.module.AutosaveModule_ProvideDispatcherProvider$AndroidPhotosAutosave_releaseFactory;
import com.amazon.photos.autosave.internal.dagger.module.AutosaveModule_ProvideLogger$AndroidPhotosAutosave_releaseFactory;
import com.amazon.photos.autosave.internal.dagger.module.AutosaveModule_ProvideMetrics$AndroidPhotosAutosave_releaseFactory;
import com.amazon.photos.autosave.internal.dagger.module.AutosaveModule_ProvideMetricsHelper$AndroidPhotosAutosave_releaseFactory;
import com.amazon.photos.autosave.internal.dagger.module.AutosaveModule_ProvidePreferenceQueueHelper$AndroidPhotosAutosave_releaseFactory;
import com.amazon.photos.autosave.internal.dagger.module.AutosaveModule_ProvideSystemUtil$AndroidPhotosAutosave_releaseFactory;
import com.amazon.photos.autosave.internal.dagger.module.AutosaveModule_ProvideUploadHelper$AndroidPhotosAutosave_releaseFactory;
import com.amazon.photos.autosave.internal.dagger.module.AutosaveModule_ProvideUploadManager$AndroidPhotosAutosave_releaseFactory;
import com.amazon.photos.autosave.internal.dagger.module.AutosaveModule_ProvideWorkManager$AndroidPhotosAutosave_releaseFactory;
import com.amazon.photos.autosave.internal.dagger.module.DatabaseModule;
import com.amazon.photos.autosave.internal.dagger.module.DatabaseModule_ProvideAutosaveBucketDao$AndroidPhotosAutosave_releaseFactory;
import com.amazon.photos.autosave.internal.dagger.module.DatabaseModule_ProvideAutosaveItemDao$AndroidPhotosAutosave_releaseFactory;
import com.amazon.photos.autosave.internal.dagger.module.DatabaseModule_ProvideDestroyableDatabaseWrapper$AndroidPhotosAutosave_releaseFactory;
import com.amazon.photos.autosave.internal.dagger.module.DatabaseModule_ProvideSharedPreferences$AndroidPhotosAutosave_releaseFactory;
import com.amazon.photos.autosave.internal.dagger.module.DatabaseModule_ProvideTransactionRunner$AndroidPhotosAutosave_releaseFactory;
import com.amazon.photos.autosave.internal.dao.AutosaveBucketDao;
import com.amazon.photos.autosave.internal.dao.AutosaveItemDao;
import com.amazon.photos.autosave.internal.db.AutosaveTransactionRunner;
import com.amazon.photos.autosave.internal.db.DestroyableDatabaseWrapper;
import com.amazon.photos.autosave.internal.metrics.AutosaveLatencyRecorder;
import com.amazon.photos.autosave.internal.metrics.MetricsHelper;
import com.amazon.photos.autosave.internal.observers.AutosaveEventNotifier;
import com.amazon.photos.autosave.internal.observers.AutosaveObserverCoordinator;
import com.amazon.photos.autosave.internal.preferences.AutosavePreferenceChangeListener;
import com.amazon.photos.autosave.internal.preferences.InternalPreferences;
import com.amazon.photos.autosave.internal.preferences.PreferenceUploadQueueHelper;
import com.amazon.photos.autosave.internal.upload.UploadHelper;
import com.amazon.photos.autosave.internal.utils.DateUtils;
import com.amazon.photos.autosave.internal.utils.SystemUtil;
import com.amazon.photos.autosave.internal.workers.AutosaveWorker;
import com.amazon.photos.autosave.internal.workers.AutosaveWorker_MembersInjector;
import com.amazon.photos.autosave.internal.workers.CancelAndRescheduleWorker;
import com.amazon.photos.autosave.internal.workers.CancelAndRescheduleWorker_MembersInjector;
import com.amazon.photos.autosave.internal.workers.CancelUploadsWorker;
import com.amazon.photos.autosave.internal.workers.CancelUploadsWorker_MembersInjector;
import com.amazon.photos.discovery.Discovery;
import com.amazon.photos.uploader.UploadManager;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import dagger.internal.DoubleCheck;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes13.dex */
public final class DaggerAutosaveComponent implements AutosaveComponent {
    private Provider<AutosaveBucketDao> provideAutosaveBucketDao$AndroidPhotosAutosave_releaseProvider;
    private Provider<AutosaveEventNotifier> provideAutosaveEventNotifier$AndroidPhotosAutosave_releaseProvider;
    private Provider<AutosaveFrameworkContext> provideAutosaveFrameworkContext$AndroidPhotosAutosave_releaseProvider;
    private Provider<AutosaveItemDao> provideAutosaveItemDao$AndroidPhotosAutosave_releaseProvider;
    private Provider<AutosaveLatencyRecorder> provideAutosaveLatencyRecorder$AndroidPhotosAutosave_releaseProvider;
    private Provider<AutosaveObserverCoordinator> provideAutosaveObserverCoordinator$AndroidPhotosAutosave_releaseProvider;
    private Provider<AutosaveOperations> provideAutosaveOperations$AndroidPhotosAutosave_releaseProvider;
    private Provider<InternalPreferences> provideAutosavePreferences$AndroidPhotosAutosave_releaseProvider;
    private Provider<AutosavePreferenceChangeListener> provideAutosavePreferencesListener$AndroidPhotosAutosave_releaseProvider;
    private Provider<DateUtils> provideDateUtils$AndroidPhotosAutosave_releaseProvider;
    private Provider<DestroyableDatabaseWrapper> provideDestroyableDatabaseWrapper$AndroidPhotosAutosave_releaseProvider;
    private Provider<Discovery> provideDiscovery$AndroidPhotosAutosave_releaseProvider;
    private Provider<DispatcherProvider> provideDispatcherProvider$AndroidPhotosAutosave_releaseProvider;
    private Provider<Logger> provideLogger$AndroidPhotosAutosave_releaseProvider;
    private Provider<Metrics> provideMetrics$AndroidPhotosAutosave_releaseProvider;
    private Provider<MetricsHelper> provideMetricsHelper$AndroidPhotosAutosave_releaseProvider;
    private Provider<PreferenceUploadQueueHelper> providePreferenceQueueHelper$AndroidPhotosAutosave_releaseProvider;
    private Provider<SharedPreferences> provideSharedPreferences$AndroidPhotosAutosave_releaseProvider;
    private Provider<SystemUtil> provideSystemUtil$AndroidPhotosAutosave_releaseProvider;
    private Provider<AutosaveTransactionRunner> provideTransactionRunner$AndroidPhotosAutosave_releaseProvider;
    private Provider<UploadHelper> provideUploadHelper$AndroidPhotosAutosave_releaseProvider;
    private Provider<UploadManager> provideUploadManager$AndroidPhotosAutosave_releaseProvider;
    private Provider<WorkManager> provideWorkManager$AndroidPhotosAutosave_releaseProvider;

    /* loaded from: classes13.dex */
    public static final class Builder {
        private AutosaveModule autosaveModule;
        private DatabaseModule databaseModule;

        public Builder autosaveModule(AutosaveModule autosaveModule) {
            this.autosaveModule = (AutosaveModule) Preconditions.checkNotNull(autosaveModule);
            return this;
        }

        public AutosaveComponent build() {
            Preconditions.checkBuilderRequirement(this.autosaveModule, AutosaveModule.class);
            Preconditions.checkBuilderRequirement(this.databaseModule, DatabaseModule.class);
            return new DaggerAutosaveComponent(this.autosaveModule, this.databaseModule);
        }

        public Builder databaseModule(DatabaseModule databaseModule) {
            this.databaseModule = (DatabaseModule) Preconditions.checkNotNull(databaseModule);
            return this;
        }

        private Builder() {
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    private void initialize(AutosaveModule autosaveModule, DatabaseModule databaseModule) {
        this.provideAutosaveFrameworkContext$AndroidPhotosAutosave_releaseProvider = DoubleCheck.provider(AutosaveModule_ProvideAutosaveFrameworkContext$AndroidPhotosAutosave_releaseFactory.create(autosaveModule));
        this.provideDestroyableDatabaseWrapper$AndroidPhotosAutosave_releaseProvider = DoubleCheck.provider(DatabaseModule_ProvideDestroyableDatabaseWrapper$AndroidPhotosAutosave_releaseFactory.create(databaseModule, this.provideAutosaveFrameworkContext$AndroidPhotosAutosave_releaseProvider));
        this.provideUploadManager$AndroidPhotosAutosave_releaseProvider = DoubleCheck.provider(AutosaveModule_ProvideUploadManager$AndroidPhotosAutosave_releaseFactory.create(autosaveModule));
        this.provideDiscovery$AndroidPhotosAutosave_releaseProvider = DoubleCheck.provider(AutosaveModule_ProvideDiscovery$AndroidPhotosAutosave_releaseFactory.create(autosaveModule));
        this.provideAutosaveBucketDao$AndroidPhotosAutosave_releaseProvider = DoubleCheck.provider(DatabaseModule_ProvideAutosaveBucketDao$AndroidPhotosAutosave_releaseFactory.create(databaseModule));
        this.provideDispatcherProvider$AndroidPhotosAutosave_releaseProvider = DoubleCheck.provider(AutosaveModule_ProvideDispatcherProvider$AndroidPhotosAutosave_releaseFactory.create(autosaveModule));
        this.provideWorkManager$AndroidPhotosAutosave_releaseProvider = DoubleCheck.provider(AutosaveModule_ProvideWorkManager$AndroidPhotosAutosave_releaseFactory.create(autosaveModule));
        this.provideAutosaveOperations$AndroidPhotosAutosave_releaseProvider = DoubleCheck.provider(AutosaveModule_ProvideAutosaveOperations$AndroidPhotosAutosave_releaseFactory.create(autosaveModule, this.provideWorkManager$AndroidPhotosAutosave_releaseProvider, this.provideAutosaveFrameworkContext$AndroidPhotosAutosave_releaseProvider, this.provideDiscovery$AndroidPhotosAutosave_releaseProvider));
        this.provideTransactionRunner$AndroidPhotosAutosave_releaseProvider = DoubleCheck.provider(DatabaseModule_ProvideTransactionRunner$AndroidPhotosAutosave_releaseFactory.create(databaseModule));
        this.provideSharedPreferences$AndroidPhotosAutosave_releaseProvider = DoubleCheck.provider(DatabaseModule_ProvideSharedPreferences$AndroidPhotosAutosave_releaseFactory.create(databaseModule));
        this.providePreferenceQueueHelper$AndroidPhotosAutosave_releaseProvider = DoubleCheck.provider(AutosaveModule_ProvidePreferenceQueueHelper$AndroidPhotosAutosave_releaseFactory.create(autosaveModule, this.provideUploadManager$AndroidPhotosAutosave_releaseProvider, this.provideAutosaveOperations$AndroidPhotosAutosave_releaseProvider, this.provideDiscovery$AndroidPhotosAutosave_releaseProvider, this.provideAutosaveBucketDao$AndroidPhotosAutosave_releaseProvider, this.provideTransactionRunner$AndroidPhotosAutosave_releaseProvider, this.provideSharedPreferences$AndroidPhotosAutosave_releaseProvider));
        this.provideAutosavePreferencesListener$AndroidPhotosAutosave_releaseProvider = DoubleCheck.provider(AutosaveModule_ProvideAutosavePreferencesListener$AndroidPhotosAutosave_releaseFactory.create(autosaveModule, this.provideDispatcherProvider$AndroidPhotosAutosave_releaseProvider, this.providePreferenceQueueHelper$AndroidPhotosAutosave_releaseProvider));
        this.provideAutosaveEventNotifier$AndroidPhotosAutosave_releaseProvider = DoubleCheck.provider(AutosaveModule_ProvideAutosaveEventNotifier$AndroidPhotosAutosave_releaseFactory.create(autosaveModule));
        this.provideAutosavePreferences$AndroidPhotosAutosave_releaseProvider = DoubleCheck.provider(AutosaveModule_ProvideAutosavePreferences$AndroidPhotosAutosave_releaseFactory.create(autosaveModule, this.provideAutosaveBucketDao$AndroidPhotosAutosave_releaseProvider, this.provideAutosavePreferencesListener$AndroidPhotosAutosave_releaseProvider, this.provideAutosaveOperations$AndroidPhotosAutosave_releaseProvider, this.provideDispatcherProvider$AndroidPhotosAutosave_releaseProvider, this.providePreferenceQueueHelper$AndroidPhotosAutosave_releaseProvider, this.provideSharedPreferences$AndroidPhotosAutosave_releaseProvider, this.provideAutosaveEventNotifier$AndroidPhotosAutosave_releaseProvider));
        this.provideAutosaveItemDao$AndroidPhotosAutosave_releaseProvider = DoubleCheck.provider(DatabaseModule_ProvideAutosaveItemDao$AndroidPhotosAutosave_releaseFactory.create(databaseModule));
        this.provideMetrics$AndroidPhotosAutosave_releaseProvider = DoubleCheck.provider(AutosaveModule_ProvideMetrics$AndroidPhotosAutosave_releaseFactory.create(autosaveModule));
        this.provideSystemUtil$AndroidPhotosAutosave_releaseProvider = DoubleCheck.provider(AutosaveModule_ProvideSystemUtil$AndroidPhotosAutosave_releaseFactory.create(autosaveModule));
        this.provideAutosaveLatencyRecorder$AndroidPhotosAutosave_releaseProvider = DoubleCheck.provider(AutosaveModule_ProvideAutosaveLatencyRecorder$AndroidPhotosAutosave_releaseFactory.create(autosaveModule, this.provideMetrics$AndroidPhotosAutosave_releaseProvider, this.provideSystemUtil$AndroidPhotosAutosave_releaseProvider, this.provideAutosaveEventNotifier$AndroidPhotosAutosave_releaseProvider));
        this.provideAutosaveObserverCoordinator$AndroidPhotosAutosave_releaseProvider = DoubleCheck.provider(AutosaveModule_ProvideAutosaveObserverCoordinator$AndroidPhotosAutosave_releaseFactory.create(autosaveModule, this.provideDiscovery$AndroidPhotosAutosave_releaseProvider, this.provideUploadManager$AndroidPhotosAutosave_releaseProvider, this.provideAutosaveOperations$AndroidPhotosAutosave_releaseProvider, this.provideAutosaveItemDao$AndroidPhotosAutosave_releaseProvider, this.provideAutosaveBucketDao$AndroidPhotosAutosave_releaseProvider, this.provideAutosavePreferences$AndroidPhotosAutosave_releaseProvider, this.provideTransactionRunner$AndroidPhotosAutosave_releaseProvider, this.provideMetrics$AndroidPhotosAutosave_releaseProvider, this.provideAutosaveEventNotifier$AndroidPhotosAutosave_releaseProvider, this.provideSharedPreferences$AndroidPhotosAutosave_releaseProvider, this.provideAutosaveLatencyRecorder$AndroidPhotosAutosave_releaseProvider));
        this.provideLogger$AndroidPhotosAutosave_releaseProvider = DoubleCheck.provider(AutosaveModule_ProvideLogger$AndroidPhotosAutosave_releaseFactory.create(autosaveModule));
        this.provideDateUtils$AndroidPhotosAutosave_releaseProvider = DoubleCheck.provider(AutosaveModule_ProvideDateUtils$AndroidPhotosAutosave_releaseFactory.create(autosaveModule));
        this.provideUploadHelper$AndroidPhotosAutosave_releaseProvider = DoubleCheck.provider(AutosaveModule_ProvideUploadHelper$AndroidPhotosAutosave_releaseFactory.create(autosaveModule, this.provideSystemUtil$AndroidPhotosAutosave_releaseProvider, this.provideTransactionRunner$AndroidPhotosAutosave_releaseProvider, this.provideDiscovery$AndroidPhotosAutosave_releaseProvider, this.provideDateUtils$AndroidPhotosAutosave_releaseProvider));
        this.provideMetricsHelper$AndroidPhotosAutosave_releaseProvider = DoubleCheck.provider(AutosaveModule_ProvideMetricsHelper$AndroidPhotosAutosave_releaseFactory.create(autosaveModule, this.provideMetrics$AndroidPhotosAutosave_releaseProvider));
    }

    @CanIgnoreReturnValue
    private AutosaveManager injectAutosaveManager(AutosaveManager autosaveManager) {
        autosaveManager.setAutosaveFrameworkContext$AndroidPhotosAutosave_release(this.provideAutosaveFrameworkContext$AndroidPhotosAutosave_releaseProvider.mo10268get());
        autosaveManager.setDatabaseWrapper$AndroidPhotosAutosave_release(this.provideDestroyableDatabaseWrapper$AndroidPhotosAutosave_releaseProvider.mo10268get());
        autosaveManager.setUploadManager$AndroidPhotosAutosave_release(this.provideUploadManager$AndroidPhotosAutosave_releaseProvider.mo10268get());
        autosaveManager.setDiscovery$AndroidPhotosAutosave_release(this.provideDiscovery$AndroidPhotosAutosave_releaseProvider.mo10268get());
        autosaveManager.setAutosavePreferences$AndroidPhotosAutosave_release(this.provideAutosavePreferences$AndroidPhotosAutosave_releaseProvider.mo10268get());
        autosaveManager.setOperations$AndroidPhotosAutosave_release(this.provideAutosaveOperations$AndroidPhotosAutosave_releaseProvider.mo10268get());
        autosaveManager.setAutosaveObserverCoordinator$AndroidPhotosAutosave_release(this.provideAutosaveObserverCoordinator$AndroidPhotosAutosave_releaseProvider.mo10268get());
        return autosaveManager;
    }

    @CanIgnoreReturnValue
    private AutosaveWorker injectAutosaveWorker(AutosaveWorker autosaveWorker) {
        AutosaveWorker_MembersInjector.injectSetLogger(autosaveWorker, this.provideLogger$AndroidPhotosAutosave_releaseProvider.mo10268get());
        AutosaveWorker_MembersInjector.injectSetMetrics(autosaveWorker, this.provideMetrics$AndroidPhotosAutosave_releaseProvider.mo10268get());
        AutosaveWorker_MembersInjector.injectSetTransactionRunner(autosaveWorker, this.provideTransactionRunner$AndroidPhotosAutosave_releaseProvider.mo10268get());
        AutosaveWorker_MembersInjector.injectSetDiscovery(autosaveWorker, this.provideDiscovery$AndroidPhotosAutosave_releaseProvider.mo10268get());
        AutosaveWorker_MembersInjector.injectSetUploadManager(autosaveWorker, this.provideUploadManager$AndroidPhotosAutosave_releaseProvider.mo10268get());
        AutosaveWorker_MembersInjector.injectSetAutosaveItemDao(autosaveWorker, this.provideAutosaveItemDao$AndroidPhotosAutosave_releaseProvider.mo10268get());
        AutosaveWorker_MembersInjector.injectSetAutosaveBucketDao(autosaveWorker, this.provideAutosaveBucketDao$AndroidPhotosAutosave_releaseProvider.mo10268get());
        AutosaveWorker_MembersInjector.injectSetAutosavePreferences(autosaveWorker, this.provideAutosavePreferences$AndroidPhotosAutosave_releaseProvider.mo10268get());
        AutosaveWorker_MembersInjector.injectSetUploadHelper(autosaveWorker, this.provideUploadHelper$AndroidPhotosAutosave_releaseProvider.mo10268get());
        AutosaveWorker_MembersInjector.injectSetMetricsHelper(autosaveWorker, this.provideMetricsHelper$AndroidPhotosAutosave_releaseProvider.mo10268get());
        AutosaveWorker_MembersInjector.injectSetSystemUtil(autosaveWorker, this.provideSystemUtil$AndroidPhotosAutosave_releaseProvider.mo10268get());
        AutosaveWorker_MembersInjector.injectSetAutosaveEventNotifier(autosaveWorker, this.provideAutosaveEventNotifier$AndroidPhotosAutosave_releaseProvider.mo10268get());
        AutosaveWorker_MembersInjector.injectSetSharedPreferences(autosaveWorker, this.provideSharedPreferences$AndroidPhotosAutosave_releaseProvider.mo10268get());
        AutosaveWorker_MembersInjector.injectSetAutosaveOperations(autosaveWorker, this.provideAutosaveOperations$AndroidPhotosAutosave_releaseProvider.mo10268get());
        return autosaveWorker;
    }

    @CanIgnoreReturnValue
    private CancelAndRescheduleWorker injectCancelAndRescheduleWorker(CancelAndRescheduleWorker cancelAndRescheduleWorker) {
        CancelAndRescheduleWorker_MembersInjector.injectSetMetrics(cancelAndRescheduleWorker, this.provideMetrics$AndroidPhotosAutosave_releaseProvider.mo10268get());
        CancelAndRescheduleWorker_MembersInjector.injectSetTransactionRunner(cancelAndRescheduleWorker, this.provideTransactionRunner$AndroidPhotosAutosave_releaseProvider.mo10268get());
        CancelAndRescheduleWorker_MembersInjector.injectSetUploadManager(cancelAndRescheduleWorker, this.provideUploadManager$AndroidPhotosAutosave_releaseProvider.mo10268get());
        CancelAndRescheduleWorker_MembersInjector.injectSetUploadHelper(cancelAndRescheduleWorker, this.provideUploadHelper$AndroidPhotosAutosave_releaseProvider.mo10268get());
        CancelAndRescheduleWorker_MembersInjector.injectSetMetricsHelper(cancelAndRescheduleWorker, this.provideMetricsHelper$AndroidPhotosAutosave_releaseProvider.mo10268get());
        return cancelAndRescheduleWorker;
    }

    @CanIgnoreReturnValue
    private CancelUploadsWorker injectCancelUploadsWorker(CancelUploadsWorker cancelUploadsWorker) {
        CancelUploadsWorker_MembersInjector.injectSetMetrics(cancelUploadsWorker, this.provideMetrics$AndroidPhotosAutosave_releaseProvider.mo10268get());
        CancelUploadsWorker_MembersInjector.injectSetLogger(cancelUploadsWorker, this.provideLogger$AndroidPhotosAutosave_releaseProvider.mo10268get());
        CancelUploadsWorker_MembersInjector.injectSetTransactionRunner(cancelUploadsWorker, this.provideTransactionRunner$AndroidPhotosAutosave_releaseProvider.mo10268get());
        CancelUploadsWorker_MembersInjector.injectSetAutosaveItemDao(cancelUploadsWorker, this.provideAutosaveItemDao$AndroidPhotosAutosave_releaseProvider.mo10268get());
        CancelUploadsWorker_MembersInjector.injectSetAutosaveBucketDao(cancelUploadsWorker, this.provideAutosaveBucketDao$AndroidPhotosAutosave_releaseProvider.mo10268get());
        CancelUploadsWorker_MembersInjector.injectSetUploadManager(cancelUploadsWorker, this.provideUploadManager$AndroidPhotosAutosave_releaseProvider.mo10268get());
        CancelUploadsWorker_MembersInjector.injectSetMetricsHelper(cancelUploadsWorker, this.provideMetricsHelper$AndroidPhotosAutosave_releaseProvider.mo10268get());
        return cancelUploadsWorker;
    }

    @Override // com.amazon.photos.autosave.internal.dagger.component.AutosaveComponent
    public void inject(AutosaveManager autosaveManager) {
        injectAutosaveManager(autosaveManager);
    }

    private DaggerAutosaveComponent(AutosaveModule autosaveModule, DatabaseModule databaseModule) {
        initialize(autosaveModule, databaseModule);
    }

    @Override // com.amazon.photos.autosave.internal.dagger.component.AutosaveComponent
    public void inject(AutosaveWorker autosaveWorker) {
        injectAutosaveWorker(autosaveWorker);
    }

    @Override // com.amazon.photos.autosave.internal.dagger.component.AutosaveComponent
    public void inject(CancelUploadsWorker cancelUploadsWorker) {
        injectCancelUploadsWorker(cancelUploadsWorker);
    }

    @Override // com.amazon.photos.autosave.internal.dagger.component.AutosaveComponent
    public void inject(CancelAndRescheduleWorker cancelAndRescheduleWorker) {
        injectCancelAndRescheduleWorker(cancelAndRescheduleWorker);
    }
}
