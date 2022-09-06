package com.amazon.photos.discovery.internal.dagger.component;

import android.content.SharedPreferences;
import com.amazon.clouddrive.android.core.interfaces.Logger;
import com.amazon.clouddrive.android.core.interfaces.Metrics;
import com.amazon.clouddrive.android.core.interfaces.SystemUtil;
import com.amazon.clouddrive.cdasdk.util.MD5Fingerprint;
import com.amazon.photos.discovery.Discovery;
import com.amazon.photos.discovery.DiscoveryConfiguration;
import com.amazon.photos.discovery.DiscoveryDaos;
import com.amazon.photos.discovery.DiscoveryDaos_MembersInjector;
import com.amazon.photos.discovery.DiscoveryLiveWorkInfo;
import com.amazon.photos.discovery.DiscoveryLiveWorkInfo_MembersInjector;
import com.amazon.photos.discovery.DiscoveryOperations;
import com.amazon.photos.discovery.DiscoveryOperations_MembersInjector;
import com.amazon.photos.discovery.dao.EditDao;
import com.amazon.photos.discovery.dao.LocalFolderDao;
import com.amazon.photos.discovery.dao.LocalItemDao;
import com.amazon.photos.discovery.dao.UnifiedItemDao;
import com.amazon.photos.discovery.dedupe.DedupeStage;
import com.amazon.photos.discovery.dedupe.stages.DigestBreakUpStage;
import com.amazon.photos.discovery.dedupe.stages.DigestBreakUpStage_MembersInjector;
import com.amazon.photos.discovery.dedupe.stages.DigestCalculatorStage;
import com.amazon.photos.discovery.dedupe.stages.DigestCalculatorStage_MembersInjector;
import com.amazon.photos.discovery.dedupe.stages.DigestDeduplicatorStage;
import com.amazon.photos.discovery.dedupe.stages.DigestDeduplicatorStage_MembersInjector;
import com.amazon.photos.discovery.dedupe.stages.MetadataDeduplicatorStage;
import com.amazon.photos.discovery.dedupe.stages.MetadataDeduplicatorStage_MembersInjector;
import com.amazon.photos.discovery.internal.dagger.module.DataModule;
import com.amazon.photos.discovery.internal.dagger.module.DataModule_ProvideDedupeFilterFactory;
import com.amazon.photos.discovery.internal.dagger.module.DataModule_ProvideEditDaoFactory;
import com.amazon.photos.discovery.internal.dagger.module.DataModule_ProvideFilterEventsFactory;
import com.amazon.photos.discovery.internal.dagger.module.DataModule_ProvideFilterUtilsFactory;
import com.amazon.photos.discovery.internal.dagger.module.DataModule_ProvideLocalFolderDaoFactory;
import com.amazon.photos.discovery.internal.dagger.module.DataModule_ProvideLocalItemDaoFactory;
import com.amazon.photos.discovery.internal.dagger.module.DataModule_ProvideMediaStoreContentResolverFactory;
import com.amazon.photos.discovery.internal.dagger.module.DataModule_ProvideOrphanRemoverFactory;
import com.amazon.photos.discovery.internal.dagger.module.DataModule_ProvideSharedPreferencesFactory;
import com.amazon.photos.discovery.internal.dagger.module.DataModule_ProvideUnifiedItemDaoFactory;
import com.amazon.photos.discovery.internal.dagger.module.DataModule_ProvideWorkerDaoFactory;
import com.amazon.photos.discovery.internal.dagger.module.DiscoveryModule;
import com.amazon.photos.discovery.internal.dagger.module.DiscoveryModule_ProvideCloudDigestAssociatorFactory;
import com.amazon.photos.discovery.internal.dagger.module.DiscoveryModule_ProvideContentChangeNotifier$AndroidPhotosDiscovery_releaseFactory;
import com.amazon.photos.discovery.internal.dagger.module.DiscoveryModule_ProvideDateMatcherFactory;
import com.amazon.photos.discovery.internal.dagger.module.DiscoveryModule_ProvideDedupeIdMapFactory;
import com.amazon.photos.discovery.internal.dagger.module.DiscoveryModule_ProvideDedupeStagesFactory;
import com.amazon.photos.discovery.internal.dagger.module.DiscoveryModule_ProvideDiscoveryConfigurationFactory;
import com.amazon.photos.discovery.internal.dagger.module.DiscoveryModule_ProvideDiscoveryFactory;
import com.amazon.photos.discovery.internal.dagger.module.DiscoveryModule_ProvideFileUtilsFactory;
import com.amazon.photos.discovery.internal.dagger.module.DiscoveryModule_ProvideHashedDirectedIdFactory;
import com.amazon.photos.discovery.internal.dagger.module.DiscoveryModule_ProvideItemMappingUtilsFactory;
import com.amazon.photos.discovery.internal.dagger.module.DiscoveryModule_ProvideLocalDigestAssociatorFactory;
import com.amazon.photos.discovery.internal.dagger.module.DiscoveryModule_ProvideLoggerFactory;
import com.amazon.photos.discovery.internal.dagger.module.DiscoveryModule_ProvideMd5FingerprintFactory;
import com.amazon.photos.discovery.internal.dagger.module.DiscoveryModule_ProvideMediaStoreUtilFactory;
import com.amazon.photos.discovery.internal.dagger.module.DiscoveryModule_ProvideMetricsFactory;
import com.amazon.photos.discovery.internal.dagger.module.DiscoveryModule_ProvideNextDedupeStageIdMapFactory;
import com.amazon.photos.discovery.internal.dagger.module.DiscoveryModule_ProvideNodeUtilsFactory;
import com.amazon.photos.discovery.internal.dagger.module.DiscoveryModule_ProvideServiceApiFactory;
import com.amazon.photos.discovery.internal.dagger.module.DiscoveryModule_ProvideSystemUtilFactory;
import com.amazon.photos.discovery.internal.dagger.module.DiscoveryModule_ProvideWorkManagerFactory;
import com.amazon.photos.discovery.internal.dagger.module.DiscoveryModule_ProvideWorkerHelperFactory;
import com.amazon.photos.discovery.internal.dao.WorkerDao;
import com.amazon.photos.discovery.internal.dedupe.digest.CloudDigestAssociator;
import com.amazon.photos.discovery.internal.dedupe.digest.LocalDigestAssociator;
import com.amazon.photos.discovery.internal.dedupe.filter.DedupeFilter;
import com.amazon.photos.discovery.internal.dedupe.filter.FilterEvents;
import com.amazon.photos.discovery.internal.dedupe.filter.FilterUtils;
import com.amazon.photos.discovery.internal.dedupe.metadata.DateMatcher;
import com.amazon.photos.discovery.internal.dedupe.metadata.ItemMappingUtils;
import com.amazon.photos.discovery.internal.observers.ContentChangeNotifier;
import com.amazon.photos.discovery.internal.server.ServiceApi;
import com.amazon.photos.discovery.internal.util.FileUtils;
import com.amazon.photos.discovery.internal.util.MediaStoreUtil;
import com.amazon.photos.discovery.internal.util.NodeUtils;
import com.amazon.photos.discovery.internal.util.OrphanRemover;
import com.amazon.photos.discovery.internal.util.WorkerHelper;
import com.amazon.photos.discovery.internal.worker.DedupeWorker;
import com.amazon.photos.discovery.internal.worker.DedupeWorker_MembersInjector;
import com.amazon.photos.discovery.internal.worker.MediaStoreChangeWorker;
import com.amazon.photos.discovery.internal.worker.MediaStoreChangeWorker_MembersInjector;
import com.amazon.photos.discovery.internal.worker.MonitorWorker;
import com.amazon.photos.discovery.internal.worker.MonitorWorker_MembersInjector;
import com.amazon.photos.discovery.internal.worker.ScanAddedWorker;
import com.amazon.photos.discovery.internal.worker.ScanAddedWorker_MembersInjector;
import com.amazon.photos.discovery.internal.worker.ScanDeletedWorker;
import com.amazon.photos.discovery.internal.worker.ScanDeletedWorker_MembersInjector;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import dagger.internal.DoubleCheck;
import dagger.internal.Preconditions;
import java.util.List;
import java.util.Map;
import javax.inject.Provider;
/* loaded from: classes13.dex */
public final class DaggerDiscoveryComponent implements DiscoveryComponent {
    private final DataModule dataModule;
    private final DiscoveryModule discoveryModule;
    private Provider<CloudDigestAssociator> provideCloudDigestAssociatorProvider;
    private Provider<ContentChangeNotifier> provideContentChangeNotifier$AndroidPhotosDiscovery_releaseProvider;
    private Provider<DedupeFilter> provideDedupeFilterProvider;
    private Provider<Map<Integer, DedupeStage>> provideDedupeIdMapProvider;
    private Provider<List<DedupeStage>> provideDedupeStagesProvider;
    private Provider<DiscoveryConfiguration> provideDiscoveryConfigurationProvider;
    private Provider<Discovery> provideDiscoveryProvider;
    private Provider<EditDao> provideEditDaoProvider;
    private Provider<FileUtils> provideFileUtilsProvider;
    private Provider<FilterEvents> provideFilterEventsProvider;
    private Provider<FilterUtils> provideFilterUtilsProvider;
    private Provider<String> provideHashedDirectedIdProvider;
    private Provider<ItemMappingUtils> provideItemMappingUtilsProvider;
    private Provider<LocalDigestAssociator> provideLocalDigestAssociatorProvider;
    private Provider<LocalFolderDao> provideLocalFolderDaoProvider;
    private Provider<LocalItemDao> provideLocalItemDaoProvider;
    private Provider<Logger> provideLoggerProvider;
    private Provider<MD5Fingerprint> provideMd5FingerprintProvider;
    private Provider<Metrics> provideMetricsProvider;
    private Provider<Map<Integer, Integer>> provideNextDedupeStageIdMapProvider;
    private Provider<NodeUtils> provideNodeUtilsProvider;
    private Provider<OrphanRemover> provideOrphanRemoverProvider;
    private Provider<ServiceApi> provideServiceApiProvider;
    private Provider<SharedPreferences> provideSharedPreferencesProvider;
    private Provider<SystemUtil> provideSystemUtilProvider;
    private Provider<UnifiedItemDao> provideUnifiedItemDaoProvider;
    private Provider<WorkerDao> provideWorkerDaoProvider;
    private Provider<WorkerHelper> provideWorkerHelperProvider;

    /* loaded from: classes13.dex */
    public static final class Builder {
        private DataModule dataModule;
        private DiscoveryModule discoveryModule;

        public DiscoveryComponent build() {
            Preconditions.checkBuilderRequirement(this.discoveryModule, DiscoveryModule.class);
            Preconditions.checkBuilderRequirement(this.dataModule, DataModule.class);
            return new DaggerDiscoveryComponent(this.discoveryModule, this.dataModule);
        }

        public Builder dataModule(DataModule dataModule) {
            this.dataModule = (DataModule) Preconditions.checkNotNull(dataModule);
            return this;
        }

        public Builder discoveryModule(DiscoveryModule discoveryModule) {
            this.discoveryModule = (DiscoveryModule) Preconditions.checkNotNull(discoveryModule);
            return this;
        }

        private Builder() {
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    private DateMatcher getDateMatcher() {
        DiscoveryModule discoveryModule = this.discoveryModule;
        return DiscoveryModule_ProvideDateMatcherFactory.provideDateMatcher(discoveryModule, DiscoveryModule_ProvideLoggerFactory.provideLogger(discoveryModule), DiscoveryModule_ProvideNodeUtilsFactory.provideNodeUtils(this.discoveryModule));
    }

    private MediaStoreUtil getMediaStoreUtil() {
        DiscoveryModule discoveryModule = this.discoveryModule;
        return DiscoveryModule_ProvideMediaStoreUtilFactory.provideMediaStoreUtil(discoveryModule, DiscoveryModule_ProvideLoggerFactory.provideLogger(discoveryModule), this.provideMetricsProvider.mo10268get());
    }

    private void initialize(DiscoveryModule discoveryModule, DataModule dataModule) {
        this.provideLocalItemDaoProvider = DoubleCheck.provider(DataModule_ProvideLocalItemDaoFactory.create(dataModule));
        this.provideUnifiedItemDaoProvider = DoubleCheck.provider(DataModule_ProvideUnifiedItemDaoFactory.create(dataModule));
        this.provideEditDaoProvider = DoubleCheck.provider(DataModule_ProvideEditDaoFactory.create(dataModule));
        this.provideLocalFolderDaoProvider = DoubleCheck.provider(DataModule_ProvideLocalFolderDaoFactory.create(dataModule));
        this.provideSystemUtilProvider = DiscoveryModule_ProvideSystemUtilFactory.create(discoveryModule);
        this.provideLoggerProvider = DiscoveryModule_ProvideLoggerFactory.create(discoveryModule);
        this.provideMetricsProvider = DoubleCheck.provider(DiscoveryModule_ProvideMetricsFactory.create(discoveryModule));
        this.provideWorkerHelperProvider = DoubleCheck.provider(DiscoveryModule_ProvideWorkerHelperFactory.create(discoveryModule, this.provideSystemUtilProvider, this.provideLoggerProvider, this.provideMetricsProvider));
        this.provideContentChangeNotifier$AndroidPhotosDiscovery_releaseProvider = DoubleCheck.provider(DiscoveryModule_ProvideContentChangeNotifier$AndroidPhotosDiscovery_releaseFactory.create(discoveryModule));
        this.provideHashedDirectedIdProvider = DoubleCheck.provider(DiscoveryModule_ProvideHashedDirectedIdFactory.create(discoveryModule));
        this.provideSharedPreferencesProvider = DoubleCheck.provider(DataModule_ProvideSharedPreferencesFactory.create(dataModule));
        this.provideWorkerDaoProvider = DoubleCheck.provider(DataModule_ProvideWorkerDaoFactory.create(dataModule));
        this.provideFilterEventsProvider = DoubleCheck.provider(DataModule_ProvideFilterEventsFactory.create(dataModule, this.provideSharedPreferencesProvider, this.provideLoggerProvider));
        this.provideDiscoveryConfigurationProvider = DoubleCheck.provider(DiscoveryModule_ProvideDiscoveryConfigurationFactory.create(discoveryModule));
        this.provideOrphanRemoverProvider = DoubleCheck.provider(DataModule_ProvideOrphanRemoverFactory.create(dataModule, this.provideWorkerDaoProvider, this.provideMetricsProvider));
        this.provideDedupeStagesProvider = DoubleCheck.provider(DiscoveryModule_ProvideDedupeStagesFactory.create(discoveryModule));
        this.provideDiscoveryProvider = DoubleCheck.provider(DiscoveryModule_ProvideDiscoveryFactory.create(discoveryModule));
        this.provideDedupeIdMapProvider = DoubleCheck.provider(DiscoveryModule_ProvideDedupeIdMapFactory.create(discoveryModule));
        this.provideNextDedupeStageIdMapProvider = DoubleCheck.provider(DiscoveryModule_ProvideNextDedupeStageIdMapFactory.create(discoveryModule));
        this.provideNodeUtilsProvider = DiscoveryModule_ProvideNodeUtilsFactory.create(discoveryModule);
        this.provideItemMappingUtilsProvider = DoubleCheck.provider(DiscoveryModule_ProvideItemMappingUtilsFactory.create(discoveryModule, this.provideMetricsProvider, this.provideLoggerProvider, this.provideNodeUtilsProvider));
        this.provideFileUtilsProvider = DoubleCheck.provider(DiscoveryModule_ProvideFileUtilsFactory.create(discoveryModule));
        this.provideFilterUtilsProvider = DataModule_ProvideFilterUtilsFactory.create(dataModule, this.provideMetricsProvider, this.provideLoggerProvider);
        this.provideDedupeFilterProvider = DoubleCheck.provider(DataModule_ProvideDedupeFilterFactory.create(dataModule, this.provideFileUtilsProvider, this.provideSharedPreferencesProvider, this.provideFilterUtilsProvider));
        this.provideMd5FingerprintProvider = DoubleCheck.provider(DiscoveryModule_ProvideMd5FingerprintFactory.create(discoveryModule));
        this.provideLocalDigestAssociatorProvider = DoubleCheck.provider(DiscoveryModule_ProvideLocalDigestAssociatorFactory.create(discoveryModule, this.provideMetricsProvider));
        this.provideServiceApiProvider = DoubleCheck.provider(DiscoveryModule_ProvideServiceApiFactory.create(discoveryModule));
        this.provideCloudDigestAssociatorProvider = DoubleCheck.provider(DiscoveryModule_ProvideCloudDigestAssociatorFactory.create(discoveryModule, this.provideServiceApiProvider, this.provideLoggerProvider, this.provideMetricsProvider, this.provideNodeUtilsProvider, this.provideDedupeFilterProvider));
    }

    @CanIgnoreReturnValue
    private DedupeWorker injectDedupeWorker(DedupeWorker dedupeWorker) {
        DedupeWorker_MembersInjector.injectDedupeStages(dedupeWorker, this.provideDedupeStagesProvider.mo10268get());
        DedupeWorker_MembersInjector.injectWorkerDao(dedupeWorker, this.provideWorkerDaoProvider.mo10268get());
        DedupeWorker_MembersInjector.injectEditDao(dedupeWorker, this.provideEditDaoProvider.mo10268get());
        DedupeWorker_MembersInjector.injectOrphanRemover(dedupeWorker, this.provideOrphanRemoverProvider.mo10268get());
        DedupeWorker_MembersInjector.injectDiscovery(dedupeWorker, this.provideDiscoveryProvider.mo10268get());
        DedupeWorker_MembersInjector.injectDedupeIdMap(dedupeWorker, this.provideDedupeIdMapProvider.mo10268get());
        DedupeWorker_MembersInjector.injectNextDedupeStageIdMap(dedupeWorker, this.provideNextDedupeStageIdMapProvider.mo10268get());
        DedupeWorker_MembersInjector.injectLogger(dedupeWorker, DiscoveryModule_ProvideLoggerFactory.provideLogger(this.discoveryModule));
        DedupeWorker_MembersInjector.injectMetrics(dedupeWorker, this.provideMetricsProvider.mo10268get());
        DedupeWorker_MembersInjector.injectSystemUtil(dedupeWorker, DiscoveryModule_ProvideSystemUtilFactory.provideSystemUtil(this.discoveryModule));
        DedupeWorker_MembersInjector.injectWorkerHelper(dedupeWorker, this.provideWorkerHelperProvider.mo10268get());
        DedupeWorker_MembersInjector.injectContentChangeNotifier(dedupeWorker, this.provideContentChangeNotifier$AndroidPhotosDiscovery_releaseProvider.mo10268get());
        return dedupeWorker;
    }

    @CanIgnoreReturnValue
    private DigestBreakUpStage injectDigestBreakUpStage(DigestBreakUpStage digestBreakUpStage) {
        DigestBreakUpStage_MembersInjector.injectLogger(digestBreakUpStage, DiscoveryModule_ProvideLoggerFactory.provideLogger(this.discoveryModule));
        DigestBreakUpStage_MembersInjector.injectMetrics(digestBreakUpStage, this.provideMetricsProvider.mo10268get());
        return digestBreakUpStage;
    }

    @CanIgnoreReturnValue
    private DigestCalculatorStage injectDigestCalculatorStage(DigestCalculatorStage digestCalculatorStage) {
        DigestCalculatorStage_MembersInjector.injectMetrics(digestCalculatorStage, this.provideMetricsProvider.mo10268get());
        DigestCalculatorStage_MembersInjector.injectLogger(digestCalculatorStage, DiscoveryModule_ProvideLoggerFactory.provideLogger(this.discoveryModule));
        DigestCalculatorStage_MembersInjector.injectSystemUtil(digestCalculatorStage, DiscoveryModule_ProvideSystemUtilFactory.provideSystemUtil(this.discoveryModule));
        DigestCalculatorStage_MembersInjector.injectFileUtils(digestCalculatorStage, this.provideFileUtilsProvider.mo10268get());
        DigestCalculatorStage_MembersInjector.injectMd5Fingerprint(digestCalculatorStage, this.provideMd5FingerprintProvider.mo10268get());
        DigestCalculatorStage_MembersInjector.injectContentResolver(digestCalculatorStage, DataModule_ProvideMediaStoreContentResolverFactory.provideMediaStoreContentResolver(this.dataModule));
        DigestCalculatorStage_MembersInjector.injectMediaStoreUtil(digestCalculatorStage, getMediaStoreUtil());
        DigestCalculatorStage_MembersInjector.injectConfiguration(digestCalculatorStage, this.provideDiscoveryConfigurationProvider.mo10268get());
        return digestCalculatorStage;
    }

    @CanIgnoreReturnValue
    private DigestDeduplicatorStage injectDigestDeduplicatorStage(DigestDeduplicatorStage digestDeduplicatorStage) {
        DigestDeduplicatorStage_MembersInjector.injectLocalAssociator(digestDeduplicatorStage, this.provideLocalDigestAssociatorProvider.mo10268get());
        DigestDeduplicatorStage_MembersInjector.injectCloudAssociator(digestDeduplicatorStage, this.provideCloudDigestAssociatorProvider.mo10268get());
        return digestDeduplicatorStage;
    }

    @CanIgnoreReturnValue
    private DiscoveryDaos injectDiscoveryDaos(DiscoveryDaos discoveryDaos) {
        DiscoveryDaos_MembersInjector.injectInternalLocalItemDao(discoveryDaos, this.provideLocalItemDaoProvider.mo10268get());
        DiscoveryDaos_MembersInjector.injectInternalUnifiedItemDao(discoveryDaos, this.provideUnifiedItemDaoProvider.mo10268get());
        DiscoveryDaos_MembersInjector.injectInternalEditDao(discoveryDaos, this.provideEditDaoProvider.mo10268get());
        DiscoveryDaos_MembersInjector.injectInternalLocalFolderDao(discoveryDaos, this.provideLocalFolderDaoProvider.mo10268get());
        return discoveryDaos;
    }

    @CanIgnoreReturnValue
    private DiscoveryLiveWorkInfo injectDiscoveryLiveWorkInfo(DiscoveryLiveWorkInfo discoveryLiveWorkInfo) {
        DiscoveryLiveWorkInfo_MembersInjector.injectHashedDirectedId(discoveryLiveWorkInfo, this.provideHashedDirectedIdProvider.mo10268get());
        DiscoveryLiveWorkInfo_MembersInjector.injectWorkManager(discoveryLiveWorkInfo, DiscoveryModule_ProvideWorkManagerFactory.provideWorkManager(this.discoveryModule));
        return discoveryLiveWorkInfo;
    }

    @CanIgnoreReturnValue
    private DiscoveryOperations injectDiscoveryOperations(DiscoveryOperations discoveryOperations) {
        DiscoveryOperations_MembersInjector.injectWorkManager(discoveryOperations, DiscoveryModule_ProvideWorkManagerFactory.provideWorkManager(this.discoveryModule));
        DiscoveryOperations_MembersInjector.injectWorkerHelper(discoveryOperations, this.provideWorkerHelperProvider.mo10268get());
        DiscoveryOperations_MembersInjector.injectSystemUtil(discoveryOperations, DiscoveryModule_ProvideSystemUtilFactory.provideSystemUtil(this.discoveryModule));
        DiscoveryOperations_MembersInjector.injectContentChangeNotifier(discoveryOperations, this.provideContentChangeNotifier$AndroidPhotosDiscovery_releaseProvider.mo10268get());
        return discoveryOperations;
    }

    @CanIgnoreReturnValue
    private MediaStoreChangeWorker injectMediaStoreChangeWorker(MediaStoreChangeWorker mediaStoreChangeWorker) {
        MediaStoreChangeWorker_MembersInjector.injectDiscovery(mediaStoreChangeWorker, this.provideDiscoveryProvider.mo10268get());
        MediaStoreChangeWorker_MembersInjector.injectLogger(mediaStoreChangeWorker, DiscoveryModule_ProvideLoggerFactory.provideLogger(this.discoveryModule));
        MediaStoreChangeWorker_MembersInjector.injectMetrics(mediaStoreChangeWorker, this.provideMetricsProvider.mo10268get());
        return mediaStoreChangeWorker;
    }

    @CanIgnoreReturnValue
    private MetadataDeduplicatorStage injectMetadataDeduplicatorStage(MetadataDeduplicatorStage metadataDeduplicatorStage) {
        MetadataDeduplicatorStage_MembersInjector.injectLogger(metadataDeduplicatorStage, DiscoveryModule_ProvideLoggerFactory.provideLogger(this.discoveryModule));
        MetadataDeduplicatorStage_MembersInjector.injectMetrics(metadataDeduplicatorStage, this.provideMetricsProvider.mo10268get());
        MetadataDeduplicatorStage_MembersInjector.injectItemMappingUtils(metadataDeduplicatorStage, this.provideItemMappingUtilsProvider.mo10268get());
        MetadataDeduplicatorStage_MembersInjector.injectDateMatcher(metadataDeduplicatorStage, getDateMatcher());
        MetadataDeduplicatorStage_MembersInjector.injectFilterEvents(metadataDeduplicatorStage, this.provideFilterEventsProvider.mo10268get());
        MetadataDeduplicatorStage_MembersInjector.injectDedupeFilter(metadataDeduplicatorStage, this.provideDedupeFilterProvider.mo10268get());
        MetadataDeduplicatorStage_MembersInjector.injectNodeUtils(metadataDeduplicatorStage, DiscoveryModule_ProvideNodeUtilsFactory.provideNodeUtils(this.discoveryModule));
        return metadataDeduplicatorStage;
    }

    @CanIgnoreReturnValue
    private MonitorWorker injectMonitorWorker(MonitorWorker monitorWorker) {
        MonitorWorker_MembersInjector.injectLogger(monitorWorker, DiscoveryModule_ProvideLoggerFactory.provideLogger(this.discoveryModule));
        MonitorWorker_MembersInjector.injectMetrics(monitorWorker, this.provideMetricsProvider.mo10268get());
        MonitorWorker_MembersInjector.injectSystemUtil(monitorWorker, DiscoveryModule_ProvideSystemUtilFactory.provideSystemUtil(this.discoveryModule));
        MonitorWorker_MembersInjector.injectContentResolver(monitorWorker, DataModule_ProvideMediaStoreContentResolverFactory.provideMediaStoreContentResolver(this.dataModule));
        MonitorWorker_MembersInjector.injectWorkerDao(monitorWorker, this.provideWorkerDaoProvider.mo10268get());
        MonitorWorker_MembersInjector.injectDiscovery(monitorWorker, this.provideDiscoveryProvider.mo10268get());
        MonitorWorker_MembersInjector.injectDedupeStages(monitorWorker, this.provideDedupeStagesProvider.mo10268get());
        MonitorWorker_MembersInjector.injectMediaStoreUtil(monitorWorker, getMediaStoreUtil());
        MonitorWorker_MembersInjector.injectFileUtils(monitorWorker, this.provideFileUtilsProvider.mo10268get());
        MonitorWorker_MembersInjector.injectSharedPreferences(monitorWorker, this.provideSharedPreferencesProvider.mo10268get());
        MonitorWorker_MembersInjector.injectWorkerHelper(monitorWorker, this.provideWorkerHelperProvider.mo10268get());
        MonitorWorker_MembersInjector.injectConfiguration(monitorWorker, this.provideDiscoveryConfigurationProvider.mo10268get());
        return monitorWorker;
    }

    @CanIgnoreReturnValue
    private ScanAddedWorker injectScanAddedWorker(ScanAddedWorker scanAddedWorker) {
        ScanAddedWorker_MembersInjector.injectContentResolver(scanAddedWorker, DataModule_ProvideMediaStoreContentResolverFactory.provideMediaStoreContentResolver(this.dataModule));
        ScanAddedWorker_MembersInjector.injectSharedPreferences(scanAddedWorker, this.provideSharedPreferencesProvider.mo10268get());
        ScanAddedWorker_MembersInjector.injectWorkerDao(scanAddedWorker, this.provideWorkerDaoProvider.mo10268get());
        ScanAddedWorker_MembersInjector.injectFilterEvents(scanAddedWorker, this.provideFilterEventsProvider.mo10268get());
        ScanAddedWorker_MembersInjector.injectMetrics(scanAddedWorker, this.provideMetricsProvider.mo10268get());
        ScanAddedWorker_MembersInjector.injectLogger(scanAddedWorker, DiscoveryModule_ProvideLoggerFactory.provideLogger(this.discoveryModule));
        ScanAddedWorker_MembersInjector.injectSystemUtil(scanAddedWorker, DiscoveryModule_ProvideSystemUtilFactory.provideSystemUtil(this.discoveryModule));
        ScanAddedWorker_MembersInjector.injectConfiguration(scanAddedWorker, this.provideDiscoveryConfigurationProvider.mo10268get());
        ScanAddedWorker_MembersInjector.injectWorkerHelper(scanAddedWorker, this.provideWorkerHelperProvider.mo10268get());
        ScanAddedWorker_MembersInjector.injectMediaStoreUtil(scanAddedWorker, getMediaStoreUtil());
        ScanAddedWorker_MembersInjector.injectContentChangeNotifier(scanAddedWorker, this.provideContentChangeNotifier$AndroidPhotosDiscovery_releaseProvider.mo10268get());
        return scanAddedWorker;
    }

    @CanIgnoreReturnValue
    private ScanDeletedWorker injectScanDeletedWorker(ScanDeletedWorker scanDeletedWorker) {
        ScanDeletedWorker_MembersInjector.injectWorkerDao(scanDeletedWorker, this.provideWorkerDaoProvider.mo10268get());
        ScanDeletedWorker_MembersInjector.injectContentResolver(scanDeletedWorker, DataModule_ProvideMediaStoreContentResolverFactory.provideMediaStoreContentResolver(this.dataModule));
        ScanDeletedWorker_MembersInjector.injectSharedPreferences(scanDeletedWorker, this.provideSharedPreferencesProvider.mo10268get());
        ScanDeletedWorker_MembersInjector.injectLogger(scanDeletedWorker, DiscoveryModule_ProvideLoggerFactory.provideLogger(this.discoveryModule));
        ScanDeletedWorker_MembersInjector.injectMetrics(scanDeletedWorker, this.provideMetricsProvider.mo10268get());
        ScanDeletedWorker_MembersInjector.injectSystemUtil(scanDeletedWorker, DiscoveryModule_ProvideSystemUtilFactory.provideSystemUtil(this.discoveryModule));
        ScanDeletedWorker_MembersInjector.injectConfiguration(scanDeletedWorker, this.provideDiscoveryConfigurationProvider.mo10268get());
        ScanDeletedWorker_MembersInjector.injectWorkerHelper(scanDeletedWorker, this.provideWorkerHelperProvider.mo10268get());
        ScanDeletedWorker_MembersInjector.injectMediaStoreUtil(scanDeletedWorker, getMediaStoreUtil());
        ScanDeletedWorker_MembersInjector.injectOrphanRemover(scanDeletedWorker, this.provideOrphanRemoverProvider.mo10268get());
        ScanDeletedWorker_MembersInjector.injectContentChangeNotifier(scanDeletedWorker, this.provideContentChangeNotifier$AndroidPhotosDiscovery_releaseProvider.mo10268get());
        return scanDeletedWorker;
    }

    @Override // com.amazon.photos.discovery.internal.dagger.component.DiscoveryComponent
    public Discovery getDiscovery() {
        return this.provideDiscoveryProvider.mo10268get();
    }

    @Override // com.amazon.photos.discovery.internal.dagger.component.DiscoveryComponent
    public ServiceApi getServiceApi() {
        return this.provideServiceApiProvider.mo10268get();
    }

    @Override // com.amazon.photos.discovery.internal.dagger.component.DiscoveryComponent
    public void inject(DiscoveryDaos discoveryDaos) {
        injectDiscoveryDaos(discoveryDaos);
    }

    @Override // com.amazon.photos.discovery.internal.dagger.component.DiscoveryComponent
    public void inject(CloudDigestAssociator cloudDigestAssociator) {
    }

    private DaggerDiscoveryComponent(DiscoveryModule discoveryModule, DataModule dataModule) {
        this.discoveryModule = discoveryModule;
        this.dataModule = dataModule;
        initialize(discoveryModule, dataModule);
    }

    @Override // com.amazon.photos.discovery.internal.dagger.component.DiscoveryComponent
    public void inject(DiscoveryOperations discoveryOperations) {
        injectDiscoveryOperations(discoveryOperations);
    }

    @Override // com.amazon.photos.discovery.internal.dagger.component.DiscoveryComponent
    public void inject(DiscoveryLiveWorkInfo discoveryLiveWorkInfo) {
        injectDiscoveryLiveWorkInfo(discoveryLiveWorkInfo);
    }

    @Override // com.amazon.photos.discovery.internal.dagger.component.DiscoveryComponent
    public void inject(ScanAddedWorker scanAddedWorker) {
        injectScanAddedWorker(scanAddedWorker);
    }

    @Override // com.amazon.photos.discovery.internal.dagger.component.DiscoveryComponent
    public void inject(ScanDeletedWorker scanDeletedWorker) {
        injectScanDeletedWorker(scanDeletedWorker);
    }

    @Override // com.amazon.photos.discovery.internal.dagger.component.DiscoveryComponent
    public void inject(DedupeWorker dedupeWorker) {
        injectDedupeWorker(dedupeWorker);
    }

    @Override // com.amazon.photos.discovery.internal.dagger.component.DiscoveryComponent
    public void inject(MetadataDeduplicatorStage metadataDeduplicatorStage) {
        injectMetadataDeduplicatorStage(metadataDeduplicatorStage);
    }

    @Override // com.amazon.photos.discovery.internal.dagger.component.DiscoveryComponent
    public void inject(MediaStoreChangeWorker mediaStoreChangeWorker) {
        injectMediaStoreChangeWorker(mediaStoreChangeWorker);
    }

    @Override // com.amazon.photos.discovery.internal.dagger.component.DiscoveryComponent
    public void inject(DigestCalculatorStage digestCalculatorStage) {
        injectDigestCalculatorStage(digestCalculatorStage);
    }

    @Override // com.amazon.photos.discovery.internal.dagger.component.DiscoveryComponent
    public void inject(DigestBreakUpStage digestBreakUpStage) {
        injectDigestBreakUpStage(digestBreakUpStage);
    }

    @Override // com.amazon.photos.discovery.internal.dagger.component.DiscoveryComponent
    public void inject(DigestDeduplicatorStage digestDeduplicatorStage) {
        injectDigestDeduplicatorStage(digestDeduplicatorStage);
    }

    @Override // com.amazon.photos.discovery.internal.dagger.component.DiscoveryComponent
    public void inject(MonitorWorker monitorWorker) {
        injectMonitorWorker(monitorWorker);
    }
}
