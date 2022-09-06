package com.amazon.photos.discovery.internal.worker;

import android.content.ContentResolver;
import android.content.SharedPreferences;
import com.amazon.clouddrive.android.core.interfaces.Logger;
import com.amazon.clouddrive.android.core.interfaces.Metrics;
import com.amazon.clouddrive.android.core.interfaces.SystemUtil;
import com.amazon.photos.discovery.Discovery;
import com.amazon.photos.discovery.DiscoveryConfiguration;
import com.amazon.photos.discovery.dedupe.DedupeStage;
import com.amazon.photos.discovery.internal.dao.WorkerDao;
import com.amazon.photos.discovery.internal.util.FileUtils;
import com.amazon.photos.discovery.internal.util.MediaStoreUtil;
import com.amazon.photos.discovery.internal.util.WorkerHelper;
import dagger.MembersInjector;
import java.util.List;
import javax.inject.Provider;
/* loaded from: classes13.dex */
public final class MonitorWorker_MembersInjector implements MembersInjector<MonitorWorker> {
    private final Provider<DiscoveryConfiguration> configurationProvider;
    private final Provider<ContentResolver> contentResolverProvider;
    private final Provider<List<DedupeStage>> dedupeStagesProvider;
    private final Provider<Discovery> discoveryProvider;
    private final Provider<FileUtils> fileUtilsProvider;
    private final Provider<Logger> loggerProvider;
    private final Provider<MediaStoreUtil> mediaStoreUtilProvider;
    private final Provider<Metrics> metricsProvider;
    private final Provider<SharedPreferences> sharedPreferencesProvider;
    private final Provider<SystemUtil> systemUtilProvider;
    private final Provider<WorkerDao> workerDaoProvider;
    private final Provider<WorkerHelper> workerHelperProvider;

    public MonitorWorker_MembersInjector(Provider<Logger> provider, Provider<Metrics> provider2, Provider<SystemUtil> provider3, Provider<ContentResolver> provider4, Provider<WorkerDao> provider5, Provider<Discovery> provider6, Provider<List<DedupeStage>> provider7, Provider<MediaStoreUtil> provider8, Provider<FileUtils> provider9, Provider<SharedPreferences> provider10, Provider<WorkerHelper> provider11, Provider<DiscoveryConfiguration> provider12) {
        this.loggerProvider = provider;
        this.metricsProvider = provider2;
        this.systemUtilProvider = provider3;
        this.contentResolverProvider = provider4;
        this.workerDaoProvider = provider5;
        this.discoveryProvider = provider6;
        this.dedupeStagesProvider = provider7;
        this.mediaStoreUtilProvider = provider8;
        this.fileUtilsProvider = provider9;
        this.sharedPreferencesProvider = provider10;
        this.workerHelperProvider = provider11;
        this.configurationProvider = provider12;
    }

    public static MembersInjector<MonitorWorker> create(Provider<Logger> provider, Provider<Metrics> provider2, Provider<SystemUtil> provider3, Provider<ContentResolver> provider4, Provider<WorkerDao> provider5, Provider<Discovery> provider6, Provider<List<DedupeStage>> provider7, Provider<MediaStoreUtil> provider8, Provider<FileUtils> provider9, Provider<SharedPreferences> provider10, Provider<WorkerHelper> provider11, Provider<DiscoveryConfiguration> provider12) {
        return new MonitorWorker_MembersInjector(provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8, provider9, provider10, provider11, provider12);
    }

    public static void injectConfiguration(MonitorWorker monitorWorker, DiscoveryConfiguration discoveryConfiguration) {
        monitorWorker.configuration = discoveryConfiguration;
    }

    public static void injectContentResolver(MonitorWorker monitorWorker, ContentResolver contentResolver) {
        monitorWorker.contentResolver = contentResolver;
    }

    public static void injectDedupeStages(MonitorWorker monitorWorker, List<DedupeStage> list) {
        monitorWorker.dedupeStages = list;
    }

    public static void injectDiscovery(MonitorWorker monitorWorker, Discovery discovery) {
        monitorWorker.discovery = discovery;
    }

    public static void injectFileUtils(MonitorWorker monitorWorker, FileUtils fileUtils) {
        monitorWorker.fileUtils = fileUtils;
    }

    public static void injectLogger(MonitorWorker monitorWorker, Logger logger) {
        monitorWorker.logger = logger;
    }

    public static void injectMediaStoreUtil(MonitorWorker monitorWorker, MediaStoreUtil mediaStoreUtil) {
        monitorWorker.mediaStoreUtil = mediaStoreUtil;
    }

    public static void injectMetrics(MonitorWorker monitorWorker, Metrics metrics) {
        monitorWorker.metrics = metrics;
    }

    public static void injectSharedPreferences(MonitorWorker monitorWorker, SharedPreferences sharedPreferences) {
        monitorWorker.sharedPreferences = sharedPreferences;
    }

    public static void injectSystemUtil(MonitorWorker monitorWorker, SystemUtil systemUtil) {
        monitorWorker.systemUtil = systemUtil;
    }

    public static void injectWorkerDao(MonitorWorker monitorWorker, WorkerDao workerDao) {
        monitorWorker.workerDao = workerDao;
    }

    public static void injectWorkerHelper(MonitorWorker monitorWorker, WorkerHelper workerHelper) {
        monitorWorker.workerHelper = workerHelper;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(MonitorWorker monitorWorker) {
        injectLogger(monitorWorker, this.loggerProvider.mo10268get());
        injectMetrics(monitorWorker, this.metricsProvider.mo10268get());
        injectSystemUtil(monitorWorker, this.systemUtilProvider.mo10268get());
        injectContentResolver(monitorWorker, this.contentResolverProvider.mo10268get());
        injectWorkerDao(monitorWorker, this.workerDaoProvider.mo10268get());
        injectDiscovery(monitorWorker, this.discoveryProvider.mo10268get());
        injectDedupeStages(monitorWorker, this.dedupeStagesProvider.mo10268get());
        injectMediaStoreUtil(monitorWorker, this.mediaStoreUtilProvider.mo10268get());
        injectFileUtils(monitorWorker, this.fileUtilsProvider.mo10268get());
        injectSharedPreferences(monitorWorker, this.sharedPreferencesProvider.mo10268get());
        injectWorkerHelper(monitorWorker, this.workerHelperProvider.mo10268get());
        injectConfiguration(monitorWorker, this.configurationProvider.mo10268get());
    }
}
