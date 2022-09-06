package com.amazon.photos.discovery.internal.worker;

import android.content.ContentResolver;
import android.content.SharedPreferences;
import com.amazon.clouddrive.android.core.interfaces.Logger;
import com.amazon.clouddrive.android.core.interfaces.Metrics;
import com.amazon.clouddrive.android.core.interfaces.SystemUtil;
import com.amazon.photos.discovery.DiscoveryConfiguration;
import com.amazon.photos.discovery.internal.dao.WorkerDao;
import com.amazon.photos.discovery.internal.observers.ContentChangeNotifier;
import com.amazon.photos.discovery.internal.util.MediaStoreUtil;
import com.amazon.photos.discovery.internal.util.OrphanRemover;
import com.amazon.photos.discovery.internal.util.WorkerHelper;
import dagger.MembersInjector;
import javax.inject.Provider;
/* loaded from: classes13.dex */
public final class ScanDeletedWorker_MembersInjector implements MembersInjector<ScanDeletedWorker> {
    private final Provider<DiscoveryConfiguration> configurationProvider;
    private final Provider<ContentChangeNotifier> contentChangeNotifierProvider;
    private final Provider<ContentResolver> contentResolverProvider;
    private final Provider<Logger> loggerProvider;
    private final Provider<MediaStoreUtil> mediaStoreUtilProvider;
    private final Provider<Metrics> metricsProvider;
    private final Provider<OrphanRemover> orphanRemoverProvider;
    private final Provider<SharedPreferences> sharedPreferencesProvider;
    private final Provider<SystemUtil> systemUtilProvider;
    private final Provider<WorkerDao> workerDaoProvider;
    private final Provider<WorkerHelper> workerHelperProvider;

    public ScanDeletedWorker_MembersInjector(Provider<WorkerDao> provider, Provider<ContentResolver> provider2, Provider<SharedPreferences> provider3, Provider<Logger> provider4, Provider<Metrics> provider5, Provider<SystemUtil> provider6, Provider<DiscoveryConfiguration> provider7, Provider<WorkerHelper> provider8, Provider<MediaStoreUtil> provider9, Provider<OrphanRemover> provider10, Provider<ContentChangeNotifier> provider11) {
        this.workerDaoProvider = provider;
        this.contentResolverProvider = provider2;
        this.sharedPreferencesProvider = provider3;
        this.loggerProvider = provider4;
        this.metricsProvider = provider5;
        this.systemUtilProvider = provider6;
        this.configurationProvider = provider7;
        this.workerHelperProvider = provider8;
        this.mediaStoreUtilProvider = provider9;
        this.orphanRemoverProvider = provider10;
        this.contentChangeNotifierProvider = provider11;
    }

    public static MembersInjector<ScanDeletedWorker> create(Provider<WorkerDao> provider, Provider<ContentResolver> provider2, Provider<SharedPreferences> provider3, Provider<Logger> provider4, Provider<Metrics> provider5, Provider<SystemUtil> provider6, Provider<DiscoveryConfiguration> provider7, Provider<WorkerHelper> provider8, Provider<MediaStoreUtil> provider9, Provider<OrphanRemover> provider10, Provider<ContentChangeNotifier> provider11) {
        return new ScanDeletedWorker_MembersInjector(provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8, provider9, provider10, provider11);
    }

    public static void injectConfiguration(ScanDeletedWorker scanDeletedWorker, DiscoveryConfiguration discoveryConfiguration) {
        scanDeletedWorker.configuration = discoveryConfiguration;
    }

    public static void injectContentChangeNotifier(ScanDeletedWorker scanDeletedWorker, ContentChangeNotifier contentChangeNotifier) {
        scanDeletedWorker.contentChangeNotifier = contentChangeNotifier;
    }

    public static void injectContentResolver(ScanDeletedWorker scanDeletedWorker, ContentResolver contentResolver) {
        scanDeletedWorker.contentResolver = contentResolver;
    }

    public static void injectLogger(ScanDeletedWorker scanDeletedWorker, Logger logger) {
        scanDeletedWorker.logger = logger;
    }

    public static void injectMediaStoreUtil(ScanDeletedWorker scanDeletedWorker, MediaStoreUtil mediaStoreUtil) {
        scanDeletedWorker.mediaStoreUtil = mediaStoreUtil;
    }

    public static void injectMetrics(ScanDeletedWorker scanDeletedWorker, Metrics metrics) {
        scanDeletedWorker.metrics = metrics;
    }

    public static void injectOrphanRemover(ScanDeletedWorker scanDeletedWorker, OrphanRemover orphanRemover) {
        scanDeletedWorker.orphanRemover = orphanRemover;
    }

    public static void injectSharedPreferences(ScanDeletedWorker scanDeletedWorker, SharedPreferences sharedPreferences) {
        scanDeletedWorker.sharedPreferences = sharedPreferences;
    }

    public static void injectSystemUtil(ScanDeletedWorker scanDeletedWorker, SystemUtil systemUtil) {
        scanDeletedWorker.systemUtil = systemUtil;
    }

    public static void injectWorkerDao(ScanDeletedWorker scanDeletedWorker, WorkerDao workerDao) {
        scanDeletedWorker.workerDao = workerDao;
    }

    public static void injectWorkerHelper(ScanDeletedWorker scanDeletedWorker, WorkerHelper workerHelper) {
        scanDeletedWorker.workerHelper = workerHelper;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(ScanDeletedWorker scanDeletedWorker) {
        injectWorkerDao(scanDeletedWorker, this.workerDaoProvider.mo10268get());
        injectContentResolver(scanDeletedWorker, this.contentResolverProvider.mo10268get());
        injectSharedPreferences(scanDeletedWorker, this.sharedPreferencesProvider.mo10268get());
        injectLogger(scanDeletedWorker, this.loggerProvider.mo10268get());
        injectMetrics(scanDeletedWorker, this.metricsProvider.mo10268get());
        injectSystemUtil(scanDeletedWorker, this.systemUtilProvider.mo10268get());
        injectConfiguration(scanDeletedWorker, this.configurationProvider.mo10268get());
        injectWorkerHelper(scanDeletedWorker, this.workerHelperProvider.mo10268get());
        injectMediaStoreUtil(scanDeletedWorker, this.mediaStoreUtilProvider.mo10268get());
        injectOrphanRemover(scanDeletedWorker, this.orphanRemoverProvider.mo10268get());
        injectContentChangeNotifier(scanDeletedWorker, this.contentChangeNotifierProvider.mo10268get());
    }
}
