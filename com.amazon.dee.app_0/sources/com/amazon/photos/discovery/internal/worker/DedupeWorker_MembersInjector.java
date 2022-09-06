package com.amazon.photos.discovery.internal.worker;

import com.amazon.clouddrive.android.core.interfaces.Logger;
import com.amazon.clouddrive.android.core.interfaces.Metrics;
import com.amazon.clouddrive.android.core.interfaces.SystemUtil;
import com.amazon.photos.discovery.Discovery;
import com.amazon.photos.discovery.dao.EditDao;
import com.amazon.photos.discovery.dedupe.DedupeStage;
import com.amazon.photos.discovery.internal.dao.WorkerDao;
import com.amazon.photos.discovery.internal.observers.ContentChangeNotifier;
import com.amazon.photos.discovery.internal.util.OrphanRemover;
import com.amazon.photos.discovery.internal.util.WorkerHelper;
import dagger.MembersInjector;
import java.util.List;
import java.util.Map;
import javax.inject.Provider;
/* loaded from: classes13.dex */
public final class DedupeWorker_MembersInjector implements MembersInjector<DedupeWorker> {
    private final Provider<ContentChangeNotifier> contentChangeNotifierProvider;
    private final Provider<Map<Integer, DedupeStage>> dedupeIdMapProvider;
    private final Provider<List<DedupeStage>> dedupeStagesProvider;
    private final Provider<Discovery> discoveryProvider;
    private final Provider<EditDao> editDaoProvider;
    private final Provider<Logger> loggerProvider;
    private final Provider<Metrics> metricsProvider;
    private final Provider<Map<Integer, Integer>> nextDedupeStageIdMapProvider;
    private final Provider<OrphanRemover> orphanRemoverProvider;
    private final Provider<SystemUtil> systemUtilProvider;
    private final Provider<WorkerDao> workerDaoProvider;
    private final Provider<WorkerHelper> workerHelperProvider;

    public DedupeWorker_MembersInjector(Provider<List<DedupeStage>> provider, Provider<WorkerDao> provider2, Provider<EditDao> provider3, Provider<OrphanRemover> provider4, Provider<Discovery> provider5, Provider<Map<Integer, DedupeStage>> provider6, Provider<Map<Integer, Integer>> provider7, Provider<Logger> provider8, Provider<Metrics> provider9, Provider<SystemUtil> provider10, Provider<WorkerHelper> provider11, Provider<ContentChangeNotifier> provider12) {
        this.dedupeStagesProvider = provider;
        this.workerDaoProvider = provider2;
        this.editDaoProvider = provider3;
        this.orphanRemoverProvider = provider4;
        this.discoveryProvider = provider5;
        this.dedupeIdMapProvider = provider6;
        this.nextDedupeStageIdMapProvider = provider7;
        this.loggerProvider = provider8;
        this.metricsProvider = provider9;
        this.systemUtilProvider = provider10;
        this.workerHelperProvider = provider11;
        this.contentChangeNotifierProvider = provider12;
    }

    public static MembersInjector<DedupeWorker> create(Provider<List<DedupeStage>> provider, Provider<WorkerDao> provider2, Provider<EditDao> provider3, Provider<OrphanRemover> provider4, Provider<Discovery> provider5, Provider<Map<Integer, DedupeStage>> provider6, Provider<Map<Integer, Integer>> provider7, Provider<Logger> provider8, Provider<Metrics> provider9, Provider<SystemUtil> provider10, Provider<WorkerHelper> provider11, Provider<ContentChangeNotifier> provider12) {
        return new DedupeWorker_MembersInjector(provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8, provider9, provider10, provider11, provider12);
    }

    public static void injectContentChangeNotifier(DedupeWorker dedupeWorker, ContentChangeNotifier contentChangeNotifier) {
        dedupeWorker.contentChangeNotifier = contentChangeNotifier;
    }

    public static void injectDedupeIdMap(DedupeWorker dedupeWorker, Map<Integer, DedupeStage> map) {
        dedupeWorker.dedupeIdMap = map;
    }

    public static void injectDedupeStages(DedupeWorker dedupeWorker, List<DedupeStage> list) {
        dedupeWorker.dedupeStages = list;
    }

    public static void injectDiscovery(DedupeWorker dedupeWorker, Discovery discovery) {
        dedupeWorker.discovery = discovery;
    }

    public static void injectEditDao(DedupeWorker dedupeWorker, EditDao editDao) {
        dedupeWorker.editDao = editDao;
    }

    public static void injectLogger(DedupeWorker dedupeWorker, Logger logger) {
        dedupeWorker.logger = logger;
    }

    public static void injectMetrics(DedupeWorker dedupeWorker, Metrics metrics) {
        dedupeWorker.metrics = metrics;
    }

    public static void injectNextDedupeStageIdMap(DedupeWorker dedupeWorker, Map<Integer, Integer> map) {
        dedupeWorker.nextDedupeStageIdMap = map;
    }

    public static void injectOrphanRemover(DedupeWorker dedupeWorker, OrphanRemover orphanRemover) {
        dedupeWorker.orphanRemover = orphanRemover;
    }

    public static void injectSystemUtil(DedupeWorker dedupeWorker, SystemUtil systemUtil) {
        dedupeWorker.systemUtil = systemUtil;
    }

    public static void injectWorkerDao(DedupeWorker dedupeWorker, WorkerDao workerDao) {
        dedupeWorker.workerDao = workerDao;
    }

    public static void injectWorkerHelper(DedupeWorker dedupeWorker, WorkerHelper workerHelper) {
        dedupeWorker.workerHelper = workerHelper;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(DedupeWorker dedupeWorker) {
        injectDedupeStages(dedupeWorker, this.dedupeStagesProvider.mo10268get());
        injectWorkerDao(dedupeWorker, this.workerDaoProvider.mo10268get());
        injectEditDao(dedupeWorker, this.editDaoProvider.mo10268get());
        injectOrphanRemover(dedupeWorker, this.orphanRemoverProvider.mo10268get());
        injectDiscovery(dedupeWorker, this.discoveryProvider.mo10268get());
        injectDedupeIdMap(dedupeWorker, this.dedupeIdMapProvider.mo10268get());
        injectNextDedupeStageIdMap(dedupeWorker, this.nextDedupeStageIdMapProvider.mo10268get());
        injectLogger(dedupeWorker, this.loggerProvider.mo10268get());
        injectMetrics(dedupeWorker, this.metricsProvider.mo10268get());
        injectSystemUtil(dedupeWorker, this.systemUtilProvider.mo10268get());
        injectWorkerHelper(dedupeWorker, this.workerHelperProvider.mo10268get());
        injectContentChangeNotifier(dedupeWorker, this.contentChangeNotifierProvider.mo10268get());
    }
}
