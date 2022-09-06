package com.amazon.photos.discovery.internal.worker;

import android.content.ContentResolver;
import android.content.SharedPreferences;
import com.amazon.clouddrive.android.core.interfaces.Logger;
import com.amazon.clouddrive.android.core.interfaces.Metrics;
import com.amazon.clouddrive.android.core.interfaces.SystemUtil;
import com.amazon.photos.discovery.DiscoveryConfiguration;
import com.amazon.photos.discovery.internal.dao.WorkerDao;
import com.amazon.photos.discovery.internal.dedupe.filter.FilterEvents;
import com.amazon.photos.discovery.internal.observers.ContentChangeNotifier;
import com.amazon.photos.discovery.internal.util.MediaStoreUtil;
import com.amazon.photos.discovery.internal.util.WorkerHelper;
import dagger.MembersInjector;
import javax.inject.Provider;
/* loaded from: classes13.dex */
public final class ScanAddedWorker_MembersInjector implements MembersInjector<ScanAddedWorker> {
    private final Provider<DiscoveryConfiguration> configurationProvider;
    private final Provider<ContentChangeNotifier> contentChangeNotifierProvider;
    private final Provider<ContentResolver> contentResolverProvider;
    private final Provider<FilterEvents> filterEventsProvider;
    private final Provider<Logger> loggerProvider;
    private final Provider<MediaStoreUtil> mediaStoreUtilProvider;
    private final Provider<Metrics> metricsProvider;
    private final Provider<SharedPreferences> sharedPreferencesProvider;
    private final Provider<SystemUtil> systemUtilProvider;
    private final Provider<WorkerDao> workerDaoProvider;
    private final Provider<WorkerHelper> workerHelperProvider;

    public ScanAddedWorker_MembersInjector(Provider<ContentResolver> provider, Provider<SharedPreferences> provider2, Provider<WorkerDao> provider3, Provider<FilterEvents> provider4, Provider<Metrics> provider5, Provider<Logger> provider6, Provider<SystemUtil> provider7, Provider<DiscoveryConfiguration> provider8, Provider<WorkerHelper> provider9, Provider<MediaStoreUtil> provider10, Provider<ContentChangeNotifier> provider11) {
        this.contentResolverProvider = provider;
        this.sharedPreferencesProvider = provider2;
        this.workerDaoProvider = provider3;
        this.filterEventsProvider = provider4;
        this.metricsProvider = provider5;
        this.loggerProvider = provider6;
        this.systemUtilProvider = provider7;
        this.configurationProvider = provider8;
        this.workerHelperProvider = provider9;
        this.mediaStoreUtilProvider = provider10;
        this.contentChangeNotifierProvider = provider11;
    }

    public static MembersInjector<ScanAddedWorker> create(Provider<ContentResolver> provider, Provider<SharedPreferences> provider2, Provider<WorkerDao> provider3, Provider<FilterEvents> provider4, Provider<Metrics> provider5, Provider<Logger> provider6, Provider<SystemUtil> provider7, Provider<DiscoveryConfiguration> provider8, Provider<WorkerHelper> provider9, Provider<MediaStoreUtil> provider10, Provider<ContentChangeNotifier> provider11) {
        return new ScanAddedWorker_MembersInjector(provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8, provider9, provider10, provider11);
    }

    public static void injectConfiguration(ScanAddedWorker scanAddedWorker, DiscoveryConfiguration discoveryConfiguration) {
        scanAddedWorker.configuration = discoveryConfiguration;
    }

    public static void injectContentChangeNotifier(ScanAddedWorker scanAddedWorker, ContentChangeNotifier contentChangeNotifier) {
        scanAddedWorker.contentChangeNotifier = contentChangeNotifier;
    }

    public static void injectContentResolver(ScanAddedWorker scanAddedWorker, ContentResolver contentResolver) {
        scanAddedWorker.contentResolver = contentResolver;
    }

    public static void injectFilterEvents(ScanAddedWorker scanAddedWorker, FilterEvents filterEvents) {
        scanAddedWorker.filterEvents = filterEvents;
    }

    public static void injectLogger(ScanAddedWorker scanAddedWorker, Logger logger) {
        scanAddedWorker.logger = logger;
    }

    public static void injectMediaStoreUtil(ScanAddedWorker scanAddedWorker, MediaStoreUtil mediaStoreUtil) {
        scanAddedWorker.mediaStoreUtil = mediaStoreUtil;
    }

    public static void injectMetrics(ScanAddedWorker scanAddedWorker, Metrics metrics) {
        scanAddedWorker.metrics = metrics;
    }

    public static void injectSharedPreferences(ScanAddedWorker scanAddedWorker, SharedPreferences sharedPreferences) {
        scanAddedWorker.sharedPreferences = sharedPreferences;
    }

    public static void injectSystemUtil(ScanAddedWorker scanAddedWorker, SystemUtil systemUtil) {
        scanAddedWorker.systemUtil = systemUtil;
    }

    public static void injectWorkerDao(ScanAddedWorker scanAddedWorker, WorkerDao workerDao) {
        scanAddedWorker.workerDao = workerDao;
    }

    public static void injectWorkerHelper(ScanAddedWorker scanAddedWorker, WorkerHelper workerHelper) {
        scanAddedWorker.workerHelper = workerHelper;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(ScanAddedWorker scanAddedWorker) {
        injectContentResolver(scanAddedWorker, this.contentResolverProvider.mo10268get());
        injectSharedPreferences(scanAddedWorker, this.sharedPreferencesProvider.mo10268get());
        injectWorkerDao(scanAddedWorker, this.workerDaoProvider.mo10268get());
        injectFilterEvents(scanAddedWorker, this.filterEventsProvider.mo10268get());
        injectMetrics(scanAddedWorker, this.metricsProvider.mo10268get());
        injectLogger(scanAddedWorker, this.loggerProvider.mo10268get());
        injectSystemUtil(scanAddedWorker, this.systemUtilProvider.mo10268get());
        injectConfiguration(scanAddedWorker, this.configurationProvider.mo10268get());
        injectWorkerHelper(scanAddedWorker, this.workerHelperProvider.mo10268get());
        injectMediaStoreUtil(scanAddedWorker, this.mediaStoreUtilProvider.mo10268get());
        injectContentChangeNotifier(scanAddedWorker, this.contentChangeNotifierProvider.mo10268get());
    }
}
