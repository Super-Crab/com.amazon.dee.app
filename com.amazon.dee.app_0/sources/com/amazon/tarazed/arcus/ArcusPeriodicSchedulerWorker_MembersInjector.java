package com.amazon.tarazed.arcus;

import com.amazon.tarazed.core.logging.TarazedLogger;
import com.amazon.tarazed.core.metrics.TarazedMetricsHelper;
import dagger.MembersInjector;
import javax.inject.Provider;
/* loaded from: classes13.dex */
public final class ArcusPeriodicSchedulerWorker_MembersInjector implements MembersInjector<ArcusPeriodicSchedulerWorker> {
    private final Provider<ArcusHelper> arcusHelperProvider;
    private final Provider<TarazedLogger> loggerProvider;
    private final Provider<TarazedMetricsHelper> metricsProvider;

    public ArcusPeriodicSchedulerWorker_MembersInjector(Provider<ArcusHelper> provider, Provider<TarazedLogger> provider2, Provider<TarazedMetricsHelper> provider3) {
        this.arcusHelperProvider = provider;
        this.loggerProvider = provider2;
        this.metricsProvider = provider3;
    }

    public static MembersInjector<ArcusPeriodicSchedulerWorker> create(Provider<ArcusHelper> provider, Provider<TarazedLogger> provider2, Provider<TarazedMetricsHelper> provider3) {
        return new ArcusPeriodicSchedulerWorker_MembersInjector(provider, provider2, provider3);
    }

    public static void injectArcusHelper(ArcusPeriodicSchedulerWorker arcusPeriodicSchedulerWorker, ArcusHelper arcusHelper) {
        arcusPeriodicSchedulerWorker.arcusHelper = arcusHelper;
    }

    public static void injectLogger(ArcusPeriodicSchedulerWorker arcusPeriodicSchedulerWorker, TarazedLogger tarazedLogger) {
        arcusPeriodicSchedulerWorker.logger = tarazedLogger;
    }

    public static void injectMetrics(ArcusPeriodicSchedulerWorker arcusPeriodicSchedulerWorker, TarazedMetricsHelper tarazedMetricsHelper) {
        arcusPeriodicSchedulerWorker.metrics = tarazedMetricsHelper;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(ArcusPeriodicSchedulerWorker arcusPeriodicSchedulerWorker) {
        injectArcusHelper(arcusPeriodicSchedulerWorker, this.arcusHelperProvider.mo10268get());
        injectLogger(arcusPeriodicSchedulerWorker, this.loggerProvider.mo10268get());
        injectMetrics(arcusPeriodicSchedulerWorker, this.metricsProvider.mo10268get());
    }
}
