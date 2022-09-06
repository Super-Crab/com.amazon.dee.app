package com.amazon.photos.uploader.internal.dagger.component;

import androidx.work.WorkManager;
import com.amazon.clouddrive.android.core.interfaces.Metrics;
import com.amazon.photos.uploader.AbandonedRequestHandler;
import com.amazon.photos.uploader.Feature;
import com.amazon.photos.uploader.FileSizeCategoryProvider;
import com.amazon.photos.uploader.QueueManager;
import com.amazon.photos.uploader.SchedulingCallback;
import com.amazon.photos.uploader.UploadFrameworkContext;
import com.amazon.photos.uploader.UploadManager;
import com.amazon.photos.uploader.Uploader;
import com.amazon.photos.uploader.UploaderDaos;
import com.amazon.photos.uploader.UploaderOperations;
import com.amazon.photos.uploader.blockers.BackoffBlockerEvaluator;
import com.amazon.photos.uploader.blockers.BlockerEvaluatorProvider;
import com.amazon.photos.uploader.blockers.GlobalBlockerEvaluator;
import com.amazon.photos.uploader.blockers.MeteredConnectionQueueBlockerEvaluator;
import com.amazon.photos.uploader.blockers.NetworkGlobalBlockerEvaluator;
import com.amazon.photos.uploader.blockers.NetworkMonitor;
import com.amazon.photos.uploader.blockers.PauseGlobalBlockerEvaluator;
import com.amazon.photos.uploader.blockers.PauseResumeState;
import com.amazon.photos.uploader.blockers.QueueBlockerEvaluator;
import com.amazon.photos.uploader.blockers.unauthorizedaccess.UnauthorizedAccessBlockerEvaluator;
import com.amazon.photos.uploader.blockers.unauthorizedaccess.UnauthorizedAccessPersistence;
import com.amazon.photos.uploader.dao.BlockerDao;
import com.amazon.photos.uploader.dao.EventDao;
import com.amazon.photos.uploader.dao.RequestDao;
import com.amazon.photos.uploader.internal.DestroyableDatabaseWrapper;
import com.amazon.photos.uploader.internal.InternalEvaluator;
import com.amazon.photos.uploader.internal.NotificationUpdatesNotifier;
import com.amazon.photos.uploader.internal.SchedulerConfiguration;
import com.amazon.photos.uploader.internal.UploadRequestUpdatesNotifier;
import com.amazon.photos.uploader.internal.UploadSummaryNotifier;
import com.amazon.photos.uploader.internal.UploadSummaryTracker;
import com.amazon.photos.uploader.internal.UploadWorkerConfiguration;
import com.amazon.photos.uploader.internal.UploaderTransactionRunner;
import com.amazon.photos.uploader.internal.contentsignature.ContentSignatureProvider;
import com.amazon.photos.uploader.internal.dagger.module.ConfigurationModule;
import com.amazon.photos.uploader.internal.dagger.module.ConfigurationModule_ProvideBackoffBlockerEvaluator$AndroidPhotosUploader_releaseFactory;
import com.amazon.photos.uploader.internal.dagger.module.ConfigurationModule_ProvideBatteryState$AndroidPhotosUploader_releaseFactory;
import com.amazon.photos.uploader.internal.dagger.module.ConfigurationModule_ProvideBatteryStateBlockerEvaluator$AndroidPhotosUploader_releaseFactory;
import com.amazon.photos.uploader.internal.dagger.module.ConfigurationModule_ProvideBlockerEvaluatorProvider$AndroidPhotosUploader_releaseFactory;
import com.amazon.photos.uploader.internal.dagger.module.ConfigurationModule_ProvideChargingBlockerEvaluator$AndroidPhotosUploader_releaseFactory;
import com.amazon.photos.uploader.internal.dagger.module.ConfigurationModule_ProvideContentSignatureProvider$AndroidPhotosUploader_releaseFactory;
import com.amazon.photos.uploader.internal.dagger.module.ConfigurationModule_ProvideCoroutineWorkerUtil$AndroidPhotosUploader_releaseFactory;
import com.amazon.photos.uploader.internal.dagger.module.ConfigurationModule_ProvideFileSizeCategoryProvider$AndroidPhotosUploader_releaseFactory;
import com.amazon.photos.uploader.internal.dagger.module.ConfigurationModule_ProvideFileUtils$AndroidPhotosUploader_releaseFactory;
import com.amazon.photos.uploader.internal.dagger.module.ConfigurationModule_ProvideGlobalBlockerEvaluators$AndroidPhotosUploader_releaseFactory;
import com.amazon.photos.uploader.internal.dagger.module.ConfigurationModule_ProvideGlobalBlockerProvider$AndroidPhotosUploader_releaseFactory;
import com.amazon.photos.uploader.internal.dagger.module.ConfigurationModule_ProvideInternalEvaluator$AndroidPhotosUploader_releaseFactory;
import com.amazon.photos.uploader.internal.dagger.module.ConfigurationModule_ProvideLogger$AndroidPhotosUploader_releaseFactory;
import com.amazon.photos.uploader.internal.dagger.module.ConfigurationModule_ProvideMeteredConnectionQueueBlockerEvaluator$AndroidPhotosUploader_releaseFactory;
import com.amazon.photos.uploader.internal.dagger.module.ConfigurationModule_ProvideMetrics$AndroidPhotosUploader_releaseFactory;
import com.amazon.photos.uploader.internal.dagger.module.ConfigurationModule_ProvideNetworkGlobalBlockerEvaluator$AndroidPhotosUploader_releaseFactory;
import com.amazon.photos.uploader.internal.dagger.module.ConfigurationModule_ProvideNotificationUpdatesNotifier$AndroidPhotosUploader_releaseFactory;
import com.amazon.photos.uploader.internal.dagger.module.ConfigurationModule_ProvidePauseResume$AndroidPhotosUploader_releaseFactory;
import com.amazon.photos.uploader.internal.dagger.module.ConfigurationModule_ProvidePauseResumeGlobalBlockerEvaluator$AndroidPhotosUploader_releaseFactory;
import com.amazon.photos.uploader.internal.dagger.module.ConfigurationModule_ProvidePermissionBlockerEvaluator$AndroidPhotosUploader_releaseFactory;
import com.amazon.photos.uploader.internal.dagger.module.ConfigurationModule_ProvidePersistentLogger$AndroidPhotosUploader_releaseFactory;
import com.amazon.photos.uploader.internal.dagger.module.ConfigurationModule_ProvideQueueBlockerEvaluators$AndroidPhotosUploader_releaseFactory;
import com.amazon.photos.uploader.internal.dagger.module.ConfigurationModule_ProvideQueueBlockerProvider$AndroidPhotosUploader_releaseFactory;
import com.amazon.photos.uploader.internal.dagger.module.ConfigurationModule_ProvideQueueManager$AndroidPhotosUploader_releaseFactory;
import com.amazon.photos.uploader.internal.dagger.module.ConfigurationModule_ProvideRunningRequestProvider$AndroidPhotosUploader_releaseFactory;
import com.amazon.photos.uploader.internal.dagger.module.ConfigurationModule_ProvideSchedulerConfiguration$AndroidPhotosUploader_releaseFactory;
import com.amazon.photos.uploader.internal.dagger.module.ConfigurationModule_ProvideSupportedFeatures$AndroidPhotosUploader_releaseFactory;
import com.amazon.photos.uploader.internal.dagger.module.ConfigurationModule_ProvideSystemUtil$AndroidPhotosUploader_releaseFactory;
import com.amazon.photos.uploader.internal.dagger.module.ConfigurationModule_ProvideUnauthorizedAccessBlockerEvaluator$AndroidPhotosUploader_releaseFactory;
import com.amazon.photos.uploader.internal.dagger.module.ConfigurationModule_ProvideUnauthorizedAccessPersistence$AndroidPhotosUploader_releaseFactory;
import com.amazon.photos.uploader.internal.dagger.module.ConfigurationModule_ProvideUploadFrameworkContext$AndroidPhotosUploader_releaseFactory;
import com.amazon.photos.uploader.internal.dagger.module.ConfigurationModule_ProvideUploadRequestObservable$AndroidPhotosUploader_releaseFactory;
import com.amazon.photos.uploader.internal.dagger.module.ConfigurationModule_ProvideUploadRequestUpdatesNotifier$AndroidPhotosUploader_releaseFactory;
import com.amazon.photos.uploader.internal.dagger.module.ConfigurationModule_ProvideUploadSummaryNotifier$AndroidPhotosUploader_releaseFactory;
import com.amazon.photos.uploader.internal.dagger.module.ConfigurationModule_ProvideUploadSummaryObservable$AndroidPhotosUploader_releaseFactory;
import com.amazon.photos.uploader.internal.dagger.module.ConfigurationModule_ProvideUploadSummaryTracker$AndroidPhotosUploader_releaseFactory;
import com.amazon.photos.uploader.internal.dagger.module.ConfigurationModule_ProvideWorkManager$AndroidPhotosUploader_releaseFactory;
import com.amazon.photos.uploader.internal.dagger.module.ConfigurationModule_ProvidesAbandonedRequestHandler$AndroidPhotosUploader_releaseFactory;
import com.amazon.photos.uploader.internal.dagger.module.ConfigurationModule_ProvidesBatteryBlockerMonitor$AndroidPhotosUploader_releaseFactory;
import com.amazon.photos.uploader.internal.dagger.module.ConfigurationModule_ProvidesNetworkBlockerMonitor$AndroidPhotosUploader_releaseFactory;
import com.amazon.photos.uploader.internal.dagger.module.ConfigurationModule_ProvidesSchedulingCallback$AndroidPhotosUploader_releaseFactory;
import com.amazon.photos.uploader.internal.dagger.module.ConfigurationModule_ProvidesUploadWorkerConfiguration$AndroidPhotosUploader_releaseFactory;
import com.amazon.photos.uploader.internal.dagger.module.ConfigurationModule_ProvidesUploader$AndroidPhotosUploader_releaseFactory;
import com.amazon.photos.uploader.internal.dagger.module.DatabaseModule;
import com.amazon.photos.uploader.internal.dagger.module.DatabaseModule_ProvideBlockerDao$AndroidPhotosUploader_releaseFactory;
import com.amazon.photos.uploader.internal.dagger.module.DatabaseModule_ProvideDestroyableDatabaseWrapper$AndroidPhotosUploader_releaseFactory;
import com.amazon.photos.uploader.internal.dagger.module.DatabaseModule_ProvideEventDao$AndroidPhotosUploader_releaseFactory;
import com.amazon.photos.uploader.internal.dagger.module.DatabaseModule_ProvideRequestDao$AndroidPhotosUploader_releaseFactory;
import com.amazon.photos.uploader.internal.dagger.module.DatabaseModule_ProvideTransactionRunner$AndroidPhotosUploader_releaseFactory;
import com.amazon.photos.uploader.internal.dagger.module.DatabaseModule_ProvideUploadRequestDao$AndroidPhotosUploader_releaseFactory;
import com.amazon.photos.uploader.internal.dao.UploadRequestDao;
import com.amazon.photos.uploader.internal.device.BatteryReceiverRegisterHelper;
import com.amazon.photos.uploader.internal.device.BatteryState;
import com.amazon.photos.uploader.internal.device.BatteryStateBlockerEvaluator;
import com.amazon.photos.uploader.internal.device.ChargingBlockerEvaluator;
import com.amazon.photos.uploader.internal.device.PermissionBlockerEvaluator;
import com.amazon.photos.uploader.internal.livedata.GlobalBlockerLiveDataProvider;
import com.amazon.photos.uploader.internal.livedata.QueueBlockerLiveDataProvider;
import com.amazon.photos.uploader.internal.livedata.RunningRequestProvider;
import com.amazon.photos.uploader.internal.utils.FileUtils;
import com.amazon.photos.uploader.internal.utils.PersistentLogger;
import com.amazon.photos.uploader.internal.utils.SystemUtil;
import com.amazon.photos.uploader.internal.workers.BlockerEvaluatorWorker;
import com.amazon.photos.uploader.internal.workers.BlockerEvaluatorWorker_MembersInjector;
import com.amazon.photos.uploader.internal.workers.CoroutineWorkerUtil;
import com.amazon.photos.uploader.internal.workers.ReevaluateWorker;
import com.amazon.photos.uploader.internal.workers.ReevaluateWorker_MembersInjector;
import com.amazon.photos.uploader.internal.workers.SchedulerWorker;
import com.amazon.photos.uploader.internal.workers.SchedulerWorker_MembersInjector;
import com.amazon.photos.uploader.internal.workers.UploadWorker;
import com.amazon.photos.uploader.internal.workers.UploadWorker_MembersInjector;
import com.amazon.photos.uploader.log.UploadLogger;
import com.amazon.photos.uploader.observables.UploadRequestObservable;
import com.amazon.photos.uploader.observables.UploadSummaryObservable;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import dagger.internal.DoubleCheck;
import dagger.internal.Preconditions;
import java.util.Set;
import javax.inject.Provider;
/* loaded from: classes13.dex */
public final class DaggerUploadFrameworkComponent implements UploadFrameworkComponent {
    private Provider<BackoffBlockerEvaluator> provideBackoffBlockerEvaluator$AndroidPhotosUploader_releaseProvider;
    private Provider<BatteryState> provideBatteryState$AndroidPhotosUploader_releaseProvider;
    private Provider<BatteryStateBlockerEvaluator> provideBatteryStateBlockerEvaluator$AndroidPhotosUploader_releaseProvider;
    private Provider<BlockerDao> provideBlockerDao$AndroidPhotosUploader_releaseProvider;
    private Provider<BlockerEvaluatorProvider> provideBlockerEvaluatorProvider$AndroidPhotosUploader_releaseProvider;
    private Provider<ChargingBlockerEvaluator> provideChargingBlockerEvaluator$AndroidPhotosUploader_releaseProvider;
    private Provider<ContentSignatureProvider> provideContentSignatureProvider$AndroidPhotosUploader_releaseProvider;
    private Provider<CoroutineWorkerUtil> provideCoroutineWorkerUtil$AndroidPhotosUploader_releaseProvider;
    private Provider<DestroyableDatabaseWrapper> provideDestroyableDatabaseWrapper$AndroidPhotosUploader_releaseProvider;
    private Provider<EventDao> provideEventDao$AndroidPhotosUploader_releaseProvider;
    private Provider<FileSizeCategoryProvider> provideFileSizeCategoryProvider$AndroidPhotosUploader_releaseProvider;
    private Provider<FileUtils> provideFileUtils$AndroidPhotosUploader_releaseProvider;
    private Provider<Set<GlobalBlockerEvaluator>> provideGlobalBlockerEvaluators$AndroidPhotosUploader_releaseProvider;
    private Provider<GlobalBlockerLiveDataProvider> provideGlobalBlockerProvider$AndroidPhotosUploader_releaseProvider;
    private Provider<InternalEvaluator> provideInternalEvaluator$AndroidPhotosUploader_releaseProvider;
    private Provider<UploadLogger> provideLogger$AndroidPhotosUploader_releaseProvider;
    private Provider<MeteredConnectionQueueBlockerEvaluator> provideMeteredConnectionQueueBlockerEvaluator$AndroidPhotosUploader_releaseProvider;
    private Provider<Metrics> provideMetrics$AndroidPhotosUploader_releaseProvider;
    private Provider<NetworkGlobalBlockerEvaluator> provideNetworkGlobalBlockerEvaluator$AndroidPhotosUploader_releaseProvider;
    private Provider<NotificationUpdatesNotifier> provideNotificationUpdatesNotifier$AndroidPhotosUploader_releaseProvider;
    private Provider<PauseResumeState> providePauseResume$AndroidPhotosUploader_releaseProvider;
    private Provider<PauseGlobalBlockerEvaluator> providePauseResumeGlobalBlockerEvaluator$AndroidPhotosUploader_releaseProvider;
    private Provider<PermissionBlockerEvaluator> providePermissionBlockerEvaluator$AndroidPhotosUploader_releaseProvider;
    private Provider<PersistentLogger> providePersistentLogger$AndroidPhotosUploader_releaseProvider;
    private Provider<Set<QueueBlockerEvaluator>> provideQueueBlockerEvaluators$AndroidPhotosUploader_releaseProvider;
    private Provider<QueueBlockerLiveDataProvider> provideQueueBlockerProvider$AndroidPhotosUploader_releaseProvider;
    private Provider<QueueManager> provideQueueManager$AndroidPhotosUploader_releaseProvider;
    private Provider<RequestDao> provideRequestDao$AndroidPhotosUploader_releaseProvider;
    private Provider<RunningRequestProvider> provideRunningRequestProvider$AndroidPhotosUploader_releaseProvider;
    private Provider<SchedulerConfiguration> provideSchedulerConfiguration$AndroidPhotosUploader_releaseProvider;
    private Provider<Set<Feature>> provideSupportedFeatures$AndroidPhotosUploader_releaseProvider;
    private Provider<SystemUtil> provideSystemUtil$AndroidPhotosUploader_releaseProvider;
    private Provider<UploaderTransactionRunner> provideTransactionRunner$AndroidPhotosUploader_releaseProvider;
    private Provider<UnauthorizedAccessBlockerEvaluator> provideUnauthorizedAccessBlockerEvaluator$AndroidPhotosUploader_releaseProvider;
    private Provider<UnauthorizedAccessPersistence> provideUnauthorizedAccessPersistence$AndroidPhotosUploader_releaseProvider;
    private Provider<UploadFrameworkContext> provideUploadFrameworkContext$AndroidPhotosUploader_releaseProvider;
    private Provider<UploadRequestDao> provideUploadRequestDao$AndroidPhotosUploader_releaseProvider;
    private Provider<UploadRequestObservable> provideUploadRequestObservable$AndroidPhotosUploader_releaseProvider;
    private Provider<UploadRequestUpdatesNotifier> provideUploadRequestUpdatesNotifier$AndroidPhotosUploader_releaseProvider;
    private Provider<UploadSummaryNotifier> provideUploadSummaryNotifier$AndroidPhotosUploader_releaseProvider;
    private Provider<UploadSummaryObservable> provideUploadSummaryObservable$AndroidPhotosUploader_releaseProvider;
    private Provider<UploadSummaryTracker> provideUploadSummaryTracker$AndroidPhotosUploader_releaseProvider;
    private Provider<WorkManager> provideWorkManager$AndroidPhotosUploader_releaseProvider;
    private Provider<AbandonedRequestHandler> providesAbandonedRequestHandler$AndroidPhotosUploader_releaseProvider;
    private Provider<BatteryReceiverRegisterHelper> providesBatteryBlockerMonitor$AndroidPhotosUploader_releaseProvider;
    private Provider<NetworkMonitor> providesNetworkBlockerMonitor$AndroidPhotosUploader_releaseProvider;
    private Provider<SchedulingCallback> providesSchedulingCallback$AndroidPhotosUploader_releaseProvider;
    private Provider<UploadWorkerConfiguration> providesUploadWorkerConfiguration$AndroidPhotosUploader_releaseProvider;
    private Provider<Uploader> providesUploader$AndroidPhotosUploader_releaseProvider;

    /* loaded from: classes13.dex */
    public static final class Builder {
        private ConfigurationModule configurationModule;
        private DatabaseModule databaseModule;

        public UploadFrameworkComponent build() {
            Preconditions.checkBuilderRequirement(this.configurationModule, ConfigurationModule.class);
            Preconditions.checkBuilderRequirement(this.databaseModule, DatabaseModule.class);
            return new DaggerUploadFrameworkComponent(this.configurationModule, this.databaseModule);
        }

        public Builder configurationModule(ConfigurationModule configurationModule) {
            this.configurationModule = (ConfigurationModule) Preconditions.checkNotNull(configurationModule);
            return this;
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

    private void initialize(ConfigurationModule configurationModule, DatabaseModule databaseModule) {
        this.provideUploadFrameworkContext$AndroidPhotosUploader_releaseProvider = DoubleCheck.provider(ConfigurationModule_ProvideUploadFrameworkContext$AndroidPhotosUploader_releaseFactory.create(configurationModule));
        this.provideUploadRequestDao$AndroidPhotosUploader_releaseProvider = DoubleCheck.provider(DatabaseModule_ProvideUploadRequestDao$AndroidPhotosUploader_releaseFactory.create(databaseModule));
        this.provideSystemUtil$AndroidPhotosUploader_releaseProvider = DoubleCheck.provider(ConfigurationModule_ProvideSystemUtil$AndroidPhotosUploader_releaseFactory.create(configurationModule, this.provideUploadFrameworkContext$AndroidPhotosUploader_releaseProvider));
        this.provideBatteryState$AndroidPhotosUploader_releaseProvider = DoubleCheck.provider(ConfigurationModule_ProvideBatteryState$AndroidPhotosUploader_releaseFactory.create(configurationModule, this.provideSystemUtil$AndroidPhotosUploader_releaseProvider));
        this.provideWorkManager$AndroidPhotosUploader_releaseProvider = DoubleCheck.provider(ConfigurationModule_ProvideWorkManager$AndroidPhotosUploader_releaseFactory.create(configurationModule));
        this.providesSchedulingCallback$AndroidPhotosUploader_releaseProvider = DoubleCheck.provider(ConfigurationModule_ProvidesSchedulingCallback$AndroidPhotosUploader_releaseFactory.create(configurationModule, this.provideUploadFrameworkContext$AndroidPhotosUploader_releaseProvider, this.provideWorkManager$AndroidPhotosUploader_releaseProvider));
        this.providesBatteryBlockerMonitor$AndroidPhotosUploader_releaseProvider = DoubleCheck.provider(ConfigurationModule_ProvidesBatteryBlockerMonitor$AndroidPhotosUploader_releaseFactory.create(configurationModule, this.provideBatteryState$AndroidPhotosUploader_releaseProvider, this.providesSchedulingCallback$AndroidPhotosUploader_releaseProvider));
        this.providesNetworkBlockerMonitor$AndroidPhotosUploader_releaseProvider = DoubleCheck.provider(ConfigurationModule_ProvidesNetworkBlockerMonitor$AndroidPhotosUploader_releaseFactory.create(configurationModule, this.provideUploadFrameworkContext$AndroidPhotosUploader_releaseProvider, this.provideSystemUtil$AndroidPhotosUploader_releaseProvider));
        this.provideUploadSummaryNotifier$AndroidPhotosUploader_releaseProvider = DoubleCheck.provider(ConfigurationModule_ProvideUploadSummaryNotifier$AndroidPhotosUploader_releaseFactory.create(configurationModule, this.providesBatteryBlockerMonitor$AndroidPhotosUploader_releaseProvider, this.providesNetworkBlockerMonitor$AndroidPhotosUploader_releaseProvider));
        this.provideMetrics$AndroidPhotosUploader_releaseProvider = DoubleCheck.provider(ConfigurationModule_ProvideMetrics$AndroidPhotosUploader_releaseFactory.create(configurationModule));
        this.provideQueueManager$AndroidPhotosUploader_releaseProvider = DoubleCheck.provider(ConfigurationModule_ProvideQueueManager$AndroidPhotosUploader_releaseFactory.create(configurationModule, this.providesSchedulingCallback$AndroidPhotosUploader_releaseProvider, this.provideMetrics$AndroidPhotosUploader_releaseProvider));
        this.provideUploadSummaryTracker$AndroidPhotosUploader_releaseProvider = DoubleCheck.provider(ConfigurationModule_ProvideUploadSummaryTracker$AndroidPhotosUploader_releaseFactory.create(configurationModule, this.provideUploadRequestDao$AndroidPhotosUploader_releaseProvider, this.provideUploadSummaryNotifier$AndroidPhotosUploader_releaseProvider, this.provideQueueManager$AndroidPhotosUploader_releaseProvider));
        this.provideUploadRequestUpdatesNotifier$AndroidPhotosUploader_releaseProvider = DoubleCheck.provider(ConfigurationModule_ProvideUploadRequestUpdatesNotifier$AndroidPhotosUploader_releaseFactory.create(configurationModule, this.provideUploadSummaryTracker$AndroidPhotosUploader_releaseProvider));
        this.provideLogger$AndroidPhotosUploader_releaseProvider = DoubleCheck.provider(ConfigurationModule_ProvideLogger$AndroidPhotosUploader_releaseFactory.create(configurationModule));
        this.providesUploader$AndroidPhotosUploader_releaseProvider = DoubleCheck.provider(ConfigurationModule_ProvidesUploader$AndroidPhotosUploader_releaseFactory.create(configurationModule));
        this.provideBlockerEvaluatorProvider$AndroidPhotosUploader_releaseProvider = DoubleCheck.provider(ConfigurationModule_ProvideBlockerEvaluatorProvider$AndroidPhotosUploader_releaseFactory.create(configurationModule, this.providesSchedulingCallback$AndroidPhotosUploader_releaseProvider, this.provideUploadRequestUpdatesNotifier$AndroidPhotosUploader_releaseProvider));
        this.providePauseResume$AndroidPhotosUploader_releaseProvider = DoubleCheck.provider(ConfigurationModule_ProvidePauseResume$AndroidPhotosUploader_releaseFactory.create(configurationModule, this.providesSchedulingCallback$AndroidPhotosUploader_releaseProvider));
        this.providePauseResumeGlobalBlockerEvaluator$AndroidPhotosUploader_releaseProvider = DoubleCheck.provider(ConfigurationModule_ProvidePauseResumeGlobalBlockerEvaluator$AndroidPhotosUploader_releaseFactory.create(configurationModule, this.providePauseResume$AndroidPhotosUploader_releaseProvider));
        this.provideEventDao$AndroidPhotosUploader_releaseProvider = DoubleCheck.provider(DatabaseModule_ProvideEventDao$AndroidPhotosUploader_releaseFactory.create(databaseModule));
        this.providePersistentLogger$AndroidPhotosUploader_releaseProvider = DoubleCheck.provider(ConfigurationModule_ProvidePersistentLogger$AndroidPhotosUploader_releaseFactory.create(configurationModule, this.provideEventDao$AndroidPhotosUploader_releaseProvider));
        this.provideBackoffBlockerEvaluator$AndroidPhotosUploader_releaseProvider = DoubleCheck.provider(ConfigurationModule_ProvideBackoffBlockerEvaluator$AndroidPhotosUploader_releaseFactory.create(configurationModule, this.provideMetrics$AndroidPhotosUploader_releaseProvider, this.providesSchedulingCallback$AndroidPhotosUploader_releaseProvider, this.provideUploadRequestUpdatesNotifier$AndroidPhotosUploader_releaseProvider, this.provideSystemUtil$AndroidPhotosUploader_releaseProvider, this.providePersistentLogger$AndroidPhotosUploader_releaseProvider));
        this.provideNetworkGlobalBlockerEvaluator$AndroidPhotosUploader_releaseProvider = DoubleCheck.provider(ConfigurationModule_ProvideNetworkGlobalBlockerEvaluator$AndroidPhotosUploader_releaseFactory.create(configurationModule, this.provideSystemUtil$AndroidPhotosUploader_releaseProvider, this.providesSchedulingCallback$AndroidPhotosUploader_releaseProvider, this.providesNetworkBlockerMonitor$AndroidPhotosUploader_releaseProvider));
        this.providePermissionBlockerEvaluator$AndroidPhotosUploader_releaseProvider = DoubleCheck.provider(ConfigurationModule_ProvidePermissionBlockerEvaluator$AndroidPhotosUploader_releaseFactory.create(configurationModule, this.provideSystemUtil$AndroidPhotosUploader_releaseProvider, this.providesSchedulingCallback$AndroidPhotosUploader_releaseProvider));
        this.provideUnauthorizedAccessPersistence$AndroidPhotosUploader_releaseProvider = DoubleCheck.provider(ConfigurationModule_ProvideUnauthorizedAccessPersistence$AndroidPhotosUploader_releaseFactory.create(configurationModule, this.provideUploadFrameworkContext$AndroidPhotosUploader_releaseProvider));
        this.provideUploadRequestObservable$AndroidPhotosUploader_releaseProvider = DoubleCheck.provider(ConfigurationModule_ProvideUploadRequestObservable$AndroidPhotosUploader_releaseFactory.create(configurationModule, this.provideUploadRequestUpdatesNotifier$AndroidPhotosUploader_releaseProvider));
        this.provideUnauthorizedAccessBlockerEvaluator$AndroidPhotosUploader_releaseProvider = DoubleCheck.provider(ConfigurationModule_ProvideUnauthorizedAccessBlockerEvaluator$AndroidPhotosUploader_releaseFactory.create(configurationModule, this.provideUnauthorizedAccessPersistence$AndroidPhotosUploader_releaseProvider, this.provideUploadRequestObservable$AndroidPhotosUploader_releaseProvider));
        this.provideGlobalBlockerEvaluators$AndroidPhotosUploader_releaseProvider = DoubleCheck.provider(ConfigurationModule_ProvideGlobalBlockerEvaluators$AndroidPhotosUploader_releaseFactory.create(configurationModule, this.providePauseResumeGlobalBlockerEvaluator$AndroidPhotosUploader_releaseProvider, this.provideBackoffBlockerEvaluator$AndroidPhotosUploader_releaseProvider, this.provideNetworkGlobalBlockerEvaluator$AndroidPhotosUploader_releaseProvider, this.providePermissionBlockerEvaluator$AndroidPhotosUploader_releaseProvider, this.provideUnauthorizedAccessBlockerEvaluator$AndroidPhotosUploader_releaseProvider));
        this.provideMeteredConnectionQueueBlockerEvaluator$AndroidPhotosUploader_releaseProvider = DoubleCheck.provider(ConfigurationModule_ProvideMeteredConnectionQueueBlockerEvaluator$AndroidPhotosUploader_releaseFactory.create(configurationModule, this.provideSystemUtil$AndroidPhotosUploader_releaseProvider, this.providesSchedulingCallback$AndroidPhotosUploader_releaseProvider, this.providesNetworkBlockerMonitor$AndroidPhotosUploader_releaseProvider));
        this.provideChargingBlockerEvaluator$AndroidPhotosUploader_releaseProvider = DoubleCheck.provider(ConfigurationModule_ProvideChargingBlockerEvaluator$AndroidPhotosUploader_releaseFactory.create(configurationModule, this.provideBatteryState$AndroidPhotosUploader_releaseProvider, this.providesSchedulingCallback$AndroidPhotosUploader_releaseProvider));
        this.provideBatteryStateBlockerEvaluator$AndroidPhotosUploader_releaseProvider = DoubleCheck.provider(ConfigurationModule_ProvideBatteryStateBlockerEvaluator$AndroidPhotosUploader_releaseFactory.create(configurationModule, this.provideBatteryState$AndroidPhotosUploader_releaseProvider, this.providesSchedulingCallback$AndroidPhotosUploader_releaseProvider));
        this.provideQueueBlockerEvaluators$AndroidPhotosUploader_releaseProvider = DoubleCheck.provider(ConfigurationModule_ProvideQueueBlockerEvaluators$AndroidPhotosUploader_releaseFactory.create(configurationModule, this.provideMeteredConnectionQueueBlockerEvaluator$AndroidPhotosUploader_releaseProvider, this.provideChargingBlockerEvaluator$AndroidPhotosUploader_releaseProvider, this.provideBatteryStateBlockerEvaluator$AndroidPhotosUploader_releaseProvider));
        this.provideInternalEvaluator$AndroidPhotosUploader_releaseProvider = DoubleCheck.provider(ConfigurationModule_ProvideInternalEvaluator$AndroidPhotosUploader_releaseFactory.create(configurationModule, this.provideBlockerEvaluatorProvider$AndroidPhotosUploader_releaseProvider, this.provideQueueManager$AndroidPhotosUploader_releaseProvider, this.provideGlobalBlockerEvaluators$AndroidPhotosUploader_releaseProvider, this.provideQueueBlockerEvaluators$AndroidPhotosUploader_releaseProvider));
        this.provideDestroyableDatabaseWrapper$AndroidPhotosUploader_releaseProvider = DoubleCheck.provider(DatabaseModule_ProvideDestroyableDatabaseWrapper$AndroidPhotosUploader_releaseFactory.create(databaseModule, this.provideUploadFrameworkContext$AndroidPhotosUploader_releaseProvider));
        this.provideNotificationUpdatesNotifier$AndroidPhotosUploader_releaseProvider = DoubleCheck.provider(ConfigurationModule_ProvideNotificationUpdatesNotifier$AndroidPhotosUploader_releaseFactory.create(configurationModule));
        this.provideRunningRequestProvider$AndroidPhotosUploader_releaseProvider = DoubleCheck.provider(ConfigurationModule_ProvideRunningRequestProvider$AndroidPhotosUploader_releaseFactory.create(configurationModule, this.provideUploadRequestObservable$AndroidPhotosUploader_releaseProvider));
        this.provideRequestDao$AndroidPhotosUploader_releaseProvider = DoubleCheck.provider(DatabaseModule_ProvideRequestDao$AndroidPhotosUploader_releaseFactory.create(databaseModule, this.provideRunningRequestProvider$AndroidPhotosUploader_releaseProvider));
        this.provideUploadSummaryObservable$AndroidPhotosUploader_releaseProvider = DoubleCheck.provider(ConfigurationModule_ProvideUploadSummaryObservable$AndroidPhotosUploader_releaseFactory.create(configurationModule, this.provideUploadSummaryNotifier$AndroidPhotosUploader_releaseProvider));
        this.provideGlobalBlockerProvider$AndroidPhotosUploader_releaseProvider = DoubleCheck.provider(ConfigurationModule_ProvideGlobalBlockerProvider$AndroidPhotosUploader_releaseFactory.create(configurationModule, this.provideUploadSummaryObservable$AndroidPhotosUploader_releaseProvider));
        this.provideQueueBlockerProvider$AndroidPhotosUploader_releaseProvider = DoubleCheck.provider(ConfigurationModule_ProvideQueueBlockerProvider$AndroidPhotosUploader_releaseFactory.create(configurationModule, this.provideUploadSummaryObservable$AndroidPhotosUploader_releaseProvider));
        this.provideBlockerDao$AndroidPhotosUploader_releaseProvider = DoubleCheck.provider(DatabaseModule_ProvideBlockerDao$AndroidPhotosUploader_releaseFactory.create(databaseModule, this.provideGlobalBlockerProvider$AndroidPhotosUploader_releaseProvider, this.provideQueueBlockerProvider$AndroidPhotosUploader_releaseProvider, this.provideUploadSummaryTracker$AndroidPhotosUploader_releaseProvider));
        this.providesAbandonedRequestHandler$AndroidPhotosUploader_releaseProvider = DoubleCheck.provider(ConfigurationModule_ProvidesAbandonedRequestHandler$AndroidPhotosUploader_releaseFactory.create(configurationModule));
        this.providesUploadWorkerConfiguration$AndroidPhotosUploader_releaseProvider = DoubleCheck.provider(ConfigurationModule_ProvidesUploadWorkerConfiguration$AndroidPhotosUploader_releaseFactory.create(configurationModule));
        this.provideContentSignatureProvider$AndroidPhotosUploader_releaseProvider = DoubleCheck.provider(ConfigurationModule_ProvideContentSignatureProvider$AndroidPhotosUploader_releaseFactory.create(configurationModule));
        this.provideTransactionRunner$AndroidPhotosUploader_releaseProvider = DoubleCheck.provider(DatabaseModule_ProvideTransactionRunner$AndroidPhotosUploader_releaseFactory.create(databaseModule));
        this.provideFileUtils$AndroidPhotosUploader_releaseProvider = DoubleCheck.provider(ConfigurationModule_ProvideFileUtils$AndroidPhotosUploader_releaseFactory.create(configurationModule));
        this.provideFileSizeCategoryProvider$AndroidPhotosUploader_releaseProvider = DoubleCheck.provider(ConfigurationModule_ProvideFileSizeCategoryProvider$AndroidPhotosUploader_releaseFactory.create(configurationModule));
        this.provideSchedulerConfiguration$AndroidPhotosUploader_releaseProvider = DoubleCheck.provider(ConfigurationModule_ProvideSchedulerConfiguration$AndroidPhotosUploader_releaseFactory.create(configurationModule));
        this.provideCoroutineWorkerUtil$AndroidPhotosUploader_releaseProvider = DoubleCheck.provider(ConfigurationModule_ProvideCoroutineWorkerUtil$AndroidPhotosUploader_releaseFactory.create(configurationModule));
        this.provideSupportedFeatures$AndroidPhotosUploader_releaseProvider = DoubleCheck.provider(ConfigurationModule_ProvideSupportedFeatures$AndroidPhotosUploader_releaseFactory.create(configurationModule));
    }

    @CanIgnoreReturnValue
    private BlockerEvaluatorWorker injectBlockerEvaluatorWorker(BlockerEvaluatorWorker blockerEvaluatorWorker) {
        BlockerEvaluatorWorker_MembersInjector.injectSetQueueManager(blockerEvaluatorWorker, this.provideQueueManager$AndroidPhotosUploader_releaseProvider.mo10268get());
        BlockerEvaluatorWorker_MembersInjector.injectSetWorkManager(blockerEvaluatorWorker, this.provideWorkManager$AndroidPhotosUploader_releaseProvider.mo10268get());
        BlockerEvaluatorWorker_MembersInjector.injectSetRequestDao(blockerEvaluatorWorker, this.provideUploadRequestDao$AndroidPhotosUploader_releaseProvider.mo10268get());
        BlockerEvaluatorWorker_MembersInjector.injectSetMetrics(blockerEvaluatorWorker, this.provideMetrics$AndroidPhotosUploader_releaseProvider.mo10268get());
        BlockerEvaluatorWorker_MembersInjector.injectSetLogger(blockerEvaluatorWorker, this.providePersistentLogger$AndroidPhotosUploader_releaseProvider.mo10268get());
        BlockerEvaluatorWorker_MembersInjector.injectSetInternalEvaluator(blockerEvaluatorWorker, this.provideInternalEvaluator$AndroidPhotosUploader_releaseProvider.mo10268get());
        BlockerEvaluatorWorker_MembersInjector.injectSetUploadSummaryTracker(blockerEvaluatorWorker, this.provideUploadSummaryTracker$AndroidPhotosUploader_releaseProvider.mo10268get());
        BlockerEvaluatorWorker_MembersInjector.injectSetUploadRequestUpdatesNotifier(blockerEvaluatorWorker, this.provideUploadRequestUpdatesNotifier$AndroidPhotosUploader_releaseProvider.mo10268get());
        BlockerEvaluatorWorker_MembersInjector.injectSetSchedulingCallback(blockerEvaluatorWorker, this.providesSchedulingCallback$AndroidPhotosUploader_releaseProvider.mo10268get());
        BlockerEvaluatorWorker_MembersInjector.injectSetTransactionRunner(blockerEvaluatorWorker, this.provideTransactionRunner$AndroidPhotosUploader_releaseProvider.mo10268get());
        blockerEvaluatorWorker.setNotificationUpdatesNotifier$AndroidPhotosUploader_release(this.provideNotificationUpdatesNotifier$AndroidPhotosUploader_releaseProvider.mo10268get());
        return blockerEvaluatorWorker;
    }

    @CanIgnoreReturnValue
    private ReevaluateWorker injectReevaluateWorker(ReevaluateWorker reevaluateWorker) {
        ReevaluateWorker_MembersInjector.injectSetWorkManager(reevaluateWorker, this.provideWorkManager$AndroidPhotosUploader_releaseProvider.mo10268get());
        ReevaluateWorker_MembersInjector.injectSetMetrics(reevaluateWorker, this.provideMetrics$AndroidPhotosUploader_releaseProvider.mo10268get());
        ReevaluateWorker_MembersInjector.injectSetLogger(reevaluateWorker, this.provideLogger$AndroidPhotosUploader_releaseProvider.mo10268get());
        ReevaluateWorker_MembersInjector.injectSetSchedulingCallback(reevaluateWorker, this.providesSchedulingCallback$AndroidPhotosUploader_releaseProvider.mo10268get());
        ReevaluateWorker_MembersInjector.injectSetInternalEvaluator(reevaluateWorker, this.provideInternalEvaluator$AndroidPhotosUploader_releaseProvider.mo10268get());
        reevaluateWorker.setNotificationUpdatesNotifier$AndroidPhotosUploader_release(this.provideNotificationUpdatesNotifier$AndroidPhotosUploader_releaseProvider.mo10268get());
        return reevaluateWorker;
    }

    @CanIgnoreReturnValue
    private SchedulerWorker injectSchedulerWorker(SchedulerWorker schedulerWorker) {
        SchedulerWorker_MembersInjector.injectSetRequestDao(schedulerWorker, this.provideUploadRequestDao$AndroidPhotosUploader_releaseProvider.mo10268get());
        SchedulerWorker_MembersInjector.injectSetWorkManager(schedulerWorker, this.provideWorkManager$AndroidPhotosUploader_releaseProvider.mo10268get());
        SchedulerWorker_MembersInjector.injectSetQueueManager(schedulerWorker, this.provideQueueManager$AndroidPhotosUploader_releaseProvider.mo10268get());
        SchedulerWorker_MembersInjector.injectSetMetrics(schedulerWorker, this.provideMetrics$AndroidPhotosUploader_releaseProvider.mo10268get());
        SchedulerWorker_MembersInjector.injectSetLogger(schedulerWorker, this.provideLogger$AndroidPhotosUploader_releaseProvider.mo10268get());
        SchedulerWorker_MembersInjector.injectSetSchedulerConfiguration(schedulerWorker, this.provideSchedulerConfiguration$AndroidPhotosUploader_releaseProvider.mo10268get());
        SchedulerWorker_MembersInjector.injectSetCoroutineWorkerUtil(schedulerWorker, this.provideCoroutineWorkerUtil$AndroidPhotosUploader_releaseProvider.mo10268get());
        schedulerWorker.setNotificationUpdatesNotifier$AndroidPhotosUploader_release(this.provideNotificationUpdatesNotifier$AndroidPhotosUploader_releaseProvider.mo10268get());
        return schedulerWorker;
    }

    @CanIgnoreReturnValue
    private UploadManager injectUploadManager(UploadManager uploadManager) {
        uploadManager.setUploadFrameworkContext$AndroidPhotosUploader_release(this.provideUploadFrameworkContext$AndroidPhotosUploader_releaseProvider.mo10268get());
        uploadManager.setUploadRequestUpdatesNotifier$AndroidPhotosUploader_release(this.provideUploadRequestUpdatesNotifier$AndroidPhotosUploader_releaseProvider.mo10268get());
        uploadManager.setQueueManagerInternal$AndroidPhotosUploader_release(this.provideQueueManager$AndroidPhotosUploader_releaseProvider.mo10268get());
        uploadManager.setUploadSummaryNotifier$AndroidPhotosUploader_release(this.provideUploadSummaryNotifier$AndroidPhotosUploader_releaseProvider.mo10268get());
        uploadManager.setLogger$AndroidPhotosUploader_release(this.provideLogger$AndroidPhotosUploader_releaseProvider.mo10268get());
        uploadManager.setUploader$AndroidPhotosUploader_release(this.providesUploader$AndroidPhotosUploader_releaseProvider.mo10268get());
        uploadManager.setInternalEvaluator$AndroidPhotosUploader_release(this.provideInternalEvaluator$AndroidPhotosUploader_releaseProvider.mo10268get());
        uploadManager.setDatabaseWrapper$AndroidPhotosUploader_release(this.provideDestroyableDatabaseWrapper$AndroidPhotosUploader_releaseProvider.mo10268get());
        return uploadManager;
    }

    @CanIgnoreReturnValue
    private UploadWorker injectUploadWorker(UploadWorker uploadWorker) {
        UploadWorker_MembersInjector.injectSetRequestDao(uploadWorker, this.provideUploadRequestDao$AndroidPhotosUploader_releaseProvider.mo10268get());
        UploadWorker_MembersInjector.injectSetUploader(uploadWorker, this.providesUploader$AndroidPhotosUploader_releaseProvider.mo10268get());
        UploadWorker_MembersInjector.injectSetWorkManager(uploadWorker, this.provideWorkManager$AndroidPhotosUploader_releaseProvider.mo10268get());
        UploadWorker_MembersInjector.injectSetUploadRequestUpdatesNotifier(uploadWorker, this.provideUploadRequestUpdatesNotifier$AndroidPhotosUploader_releaseProvider.mo10268get());
        UploadWorker_MembersInjector.injectSetMetrics(uploadWorker, this.provideMetrics$AndroidPhotosUploader_releaseProvider.mo10268get());
        UploadWorker_MembersInjector.injectSetLogger(uploadWorker, this.providePersistentLogger$AndroidPhotosUploader_releaseProvider.mo10268get());
        UploadWorker_MembersInjector.injectSetInternalEvaluator(uploadWorker, this.provideInternalEvaluator$AndroidPhotosUploader_releaseProvider.mo10268get());
        UploadWorker_MembersInjector.injectSetAbandonedRequestHandler(uploadWorker, this.providesAbandonedRequestHandler$AndroidPhotosUploader_releaseProvider.mo10268get());
        UploadWorker_MembersInjector.injectSetUploadWorkerConfiguration(uploadWorker, this.providesUploadWorkerConfiguration$AndroidPhotosUploader_releaseProvider.mo10268get());
        UploadWorker_MembersInjector.injectSetContentSignatureProvider(uploadWorker, this.provideContentSignatureProvider$AndroidPhotosUploader_releaseProvider.mo10268get());
        UploadWorker_MembersInjector.injectSetTransactionRunner(uploadWorker, this.provideTransactionRunner$AndroidPhotosUploader_releaseProvider.mo10268get());
        UploadWorker_MembersInjector.injectSetSystemUtil(uploadWorker, this.provideSystemUtil$AndroidPhotosUploader_releaseProvider.mo10268get());
        UploadWorker_MembersInjector.injectSetFileUtils(uploadWorker, this.provideFileUtils$AndroidPhotosUploader_releaseProvider.mo10268get());
        UploadWorker_MembersInjector.injectSetFileSizeCategoryProvider(uploadWorker, this.provideFileSizeCategoryProvider$AndroidPhotosUploader_releaseProvider.mo10268get());
        uploadWorker.setNotificationUpdatesNotifier$AndroidPhotosUploader_release(this.provideNotificationUpdatesNotifier$AndroidPhotosUploader_releaseProvider.mo10268get());
        return uploadWorker;
    }

    @CanIgnoreReturnValue
    private UploaderDaos injectUploaderDaos(UploaderDaos uploaderDaos) {
        uploaderDaos.setEventDao$AndroidPhotosUploader_release(this.provideEventDao$AndroidPhotosUploader_releaseProvider.mo10268get());
        uploaderDaos.setRequestDao$AndroidPhotosUploader_release(this.provideRequestDao$AndroidPhotosUploader_releaseProvider.mo10268get());
        uploaderDaos.setBlockerDao$AndroidPhotosUploader_release(this.provideBlockerDao$AndroidPhotosUploader_releaseProvider.mo10268get());
        return uploaderDaos;
    }

    @CanIgnoreReturnValue
    private UploaderOperations injectUploaderOperations(UploaderOperations uploaderOperations) {
        uploaderOperations.setRequestDao$AndroidPhotosUploader_release(this.provideUploadRequestDao$AndroidPhotosUploader_releaseProvider.mo10268get());
        uploaderOperations.setInternalEvaluator$AndroidPhotosUploader_release(this.provideInternalEvaluator$AndroidPhotosUploader_releaseProvider.mo10268get());
        uploaderOperations.setUploadFrameworkContext$AndroidPhotosUploader_release(this.provideUploadFrameworkContext$AndroidPhotosUploader_releaseProvider.mo10268get());
        uploaderOperations.setUploadRequestUpdatesNotifier$AndroidPhotosUploader_release(this.provideUploadRequestUpdatesNotifier$AndroidPhotosUploader_releaseProvider.mo10268get());
        uploaderOperations.setMetrics$AndroidPhotosUploader_release(this.provideMetrics$AndroidPhotosUploader_releaseProvider.mo10268get());
        uploaderOperations.setLogger$AndroidPhotosUploader_release(this.provideLogger$AndroidPhotosUploader_releaseProvider.mo10268get());
        uploaderOperations.setPauseResumeState$AndroidPhotosUploader_release(this.providePauseResume$AndroidPhotosUploader_releaseProvider.mo10268get());
        uploaderOperations.setSchedulingCallback$AndroidPhotosUploader_release(this.providesSchedulingCallback$AndroidPhotosUploader_releaseProvider.mo10268get());
        uploaderOperations.setWorkManager$AndroidPhotosUploader_release(this.provideWorkManager$AndroidPhotosUploader_releaseProvider.mo10268get());
        uploaderOperations.setBackoffBlockerEvaluator$AndroidPhotosUploader_release(this.provideBackoffBlockerEvaluator$AndroidPhotosUploader_releaseProvider.mo10268get());
        uploaderOperations.setSystemUtil$AndroidPhotosUploader_release(this.provideSystemUtil$AndroidPhotosUploader_releaseProvider.mo10268get());
        uploaderOperations.setNotificationUpdatesNotifier$AndroidPhotosUploader_release(this.provideNotificationUpdatesNotifier$AndroidPhotosUploader_releaseProvider.mo10268get());
        uploaderOperations.setUploader$AndroidPhotosUploader_release(this.providesUploader$AndroidPhotosUploader_releaseProvider.mo10268get());
        uploaderOperations.setQueueManager$AndroidPhotosUploader_release(this.provideQueueManager$AndroidPhotosUploader_releaseProvider.mo10268get());
        uploaderOperations.setUploadSummaryTracker$AndroidPhotosUploader_release(this.provideUploadSummaryTracker$AndroidPhotosUploader_releaseProvider.mo10268get());
        return uploaderOperations;
    }

    @Override // com.amazon.photos.uploader.internal.dagger.component.UploadFrameworkComponent
    public BlockerEvaluatorProvider getBlockerEvaluatorProvider() {
        return this.provideBlockerEvaluatorProvider$AndroidPhotosUploader_releaseProvider.mo10268get();
    }

    @Override // com.amazon.photos.uploader.internal.dagger.component.UploadFrameworkComponent
    public Set<Feature> getSupportedFeatures() {
        return this.provideSupportedFeatures$AndroidPhotosUploader_releaseProvider.mo10268get();
    }

    @Override // com.amazon.photos.uploader.internal.dagger.component.UploadFrameworkComponent
    public void inject(UploadManager uploadManager) {
        injectUploadManager(uploadManager);
    }

    @Override // com.amazon.photos.uploader.internal.dagger.component.UploadFrameworkComponent
    public void setBlockerEvaluatorProvider(BlockerEvaluatorProvider blockerEvaluatorProvider) {
    }

    private DaggerUploadFrameworkComponent(ConfigurationModule configurationModule, DatabaseModule databaseModule) {
        initialize(configurationModule, databaseModule);
    }

    @Override // com.amazon.photos.uploader.internal.dagger.component.UploadFrameworkComponent
    public void inject(UploaderOperations uploaderOperations) {
        injectUploaderOperations(uploaderOperations);
    }

    @Override // com.amazon.photos.uploader.internal.dagger.component.UploadFrameworkComponent
    public void inject(UploaderDaos uploaderDaos) {
        injectUploaderDaos(uploaderDaos);
    }

    @Override // com.amazon.photos.uploader.internal.dagger.component.UploadFrameworkComponent
    public void inject(UploadWorker uploadWorker) {
        injectUploadWorker(uploadWorker);
    }

    @Override // com.amazon.photos.uploader.internal.dagger.component.UploadFrameworkComponent
    public void inject(ReevaluateWorker reevaluateWorker) {
        injectReevaluateWorker(reevaluateWorker);
    }

    @Override // com.amazon.photos.uploader.internal.dagger.component.UploadFrameworkComponent
    public void inject(BlockerEvaluatorWorker blockerEvaluatorWorker) {
        injectBlockerEvaluatorWorker(blockerEvaluatorWorker);
    }

    @Override // com.amazon.photos.uploader.internal.dagger.component.UploadFrameworkComponent
    public void inject(SchedulerWorker schedulerWorker) {
        injectSchedulerWorker(schedulerWorker);
    }
}
