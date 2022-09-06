package com.amazon.alexa.fitness.dagger;

import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.fitness.accessory.FitnessAccessorySensorProvider;
import com.amazon.alexa.fitness.algorithm.StepsToDistanceAlgorithmAdapter;
import com.amazon.alexa.fitness.algorithm.aggregatedDistance.AggregatedDistanceAlgorithm;
import com.amazon.alexa.fitness.algorithm.calories.CaloriesAlgorithmAdapter;
import com.amazon.alexa.fitness.api.afx.FeatureService;
import com.amazon.alexa.fitness.api.afx.FitnessSessionOrchestrator;
import com.amazon.alexa.fitness.context.SessionSummaryProvider;
import com.amazon.alexa.fitness.location.LocationSensorProvider;
import com.amazon.alexa.fitness.logs.ILog;
import com.amazon.alexa.fitness.message.notification.FitnessNotificationPublisher;
import com.amazon.alexa.fitness.metrics.MetricEventFactory;
import com.amazon.alexa.fitness.metrics.MetricEventRecorder;
import com.amazon.alexa.fitness.metrics.MetricsAggregator;
import com.amazon.alexa.fitness.metrics.SessionMetrics;
import com.amazon.alexa.fitness.repository.SessionSummaryCache;
import com.amazon.alexa.fitness.sdk.AfxMessageProcessor;
import com.amazon.alexa.fitness.sdk.MetricsAggregatorRecovery;
import com.amazon.alexa.fitness.sdk.SessionManager;
import com.amazon.alexa.fitness.sdk.sample.SampleStore;
import com.amazon.alexa.fitness.service.hds.HdsClient;
import com.amazon.alexa.protocols.service.api.ComponentRegistry;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes8.dex */
public final class StaticReleasePackageModule_ProvideFitnessSessionOrchestratorFactory implements Factory<FitnessSessionOrchestrator> {
    private final Provider<FitnessAccessorySensorProvider> accessorySensorProvider;
    private final Provider<AfxMessageProcessor> afxMessageProcessorProvider;
    private final Provider<AggregatedDistanceAlgorithm> aggregatedDistanceAlgorithmProvider;
    private final Provider<CaloriesAlgorithmAdapter> caloriesAlgorithmAdapterProvider;
    private final Provider<ComponentRegistry> componentRegistryProvider;
    private final Provider<EventBus> eventBusProvider;
    private final Provider<FeatureService> featureServiceProvider;
    private final Provider<FitnessNotificationPublisher> fitnessNotificationPublisherProvider;
    private final Provider<HdsClient> hdsClientProvider;
    private final Provider<LocationSensorProvider> locationSensorProvider;
    private final Provider<ILog> logProvider;
    private final Provider<MetricEventFactory> metricEventFactoryProvider;
    private final Provider<MetricEventRecorder> metricEventRecorderProvider;
    private final Provider<MetricsAggregator> metricsAggregatorProvider;
    private final Provider<MetricsAggregatorRecovery> metricsAggregatorRecoveryProvider;
    private final StaticReleasePackageModule module;
    private final Provider<SampleStore> sampleStoreProvider;
    private final Provider<SessionManager> sessionManagerProvider;
    private final Provider<SessionMetrics> sessionMetricsProvider;
    private final Provider<SessionSummaryCache> sessionSummaryCacheProvider;
    private final Provider<SessionSummaryProvider> sessionSummaryProvider;
    private final Provider<StepsToDistanceAlgorithmAdapter> stepsToDistanceAlgorithmAdapterProvider;

    public StaticReleasePackageModule_ProvideFitnessSessionOrchestratorFactory(StaticReleasePackageModule staticReleasePackageModule, Provider<ComponentRegistry> provider, Provider<AfxMessageProcessor> provider2, Provider<HdsClient> provider3, Provider<FeatureService> provider4, Provider<FitnessNotificationPublisher> provider5, Provider<SessionSummaryProvider> provider6, Provider<SessionManager> provider7, Provider<FitnessAccessorySensorProvider> provider8, Provider<LocationSensorProvider> provider9, Provider<MetricEventRecorder> provider10, Provider<MetricEventFactory> provider11, Provider<MetricsAggregator> provider12, Provider<MetricsAggregatorRecovery> provider13, Provider<StepsToDistanceAlgorithmAdapter> provider14, Provider<CaloriesAlgorithmAdapter> provider15, Provider<AggregatedDistanceAlgorithm> provider16, Provider<SessionMetrics> provider17, Provider<SampleStore> provider18, Provider<SessionSummaryCache> provider19, Provider<EventBus> provider20, Provider<ILog> provider21) {
        this.module = staticReleasePackageModule;
        this.componentRegistryProvider = provider;
        this.afxMessageProcessorProvider = provider2;
        this.hdsClientProvider = provider3;
        this.featureServiceProvider = provider4;
        this.fitnessNotificationPublisherProvider = provider5;
        this.sessionSummaryProvider = provider6;
        this.sessionManagerProvider = provider7;
        this.accessorySensorProvider = provider8;
        this.locationSensorProvider = provider9;
        this.metricEventRecorderProvider = provider10;
        this.metricEventFactoryProvider = provider11;
        this.metricsAggregatorProvider = provider12;
        this.metricsAggregatorRecoveryProvider = provider13;
        this.stepsToDistanceAlgorithmAdapterProvider = provider14;
        this.caloriesAlgorithmAdapterProvider = provider15;
        this.aggregatedDistanceAlgorithmProvider = provider16;
        this.sessionMetricsProvider = provider17;
        this.sampleStoreProvider = provider18;
        this.sessionSummaryCacheProvider = provider19;
        this.eventBusProvider = provider20;
        this.logProvider = provider21;
    }

    public static StaticReleasePackageModule_ProvideFitnessSessionOrchestratorFactory create(StaticReleasePackageModule staticReleasePackageModule, Provider<ComponentRegistry> provider, Provider<AfxMessageProcessor> provider2, Provider<HdsClient> provider3, Provider<FeatureService> provider4, Provider<FitnessNotificationPublisher> provider5, Provider<SessionSummaryProvider> provider6, Provider<SessionManager> provider7, Provider<FitnessAccessorySensorProvider> provider8, Provider<LocationSensorProvider> provider9, Provider<MetricEventRecorder> provider10, Provider<MetricEventFactory> provider11, Provider<MetricsAggregator> provider12, Provider<MetricsAggregatorRecovery> provider13, Provider<StepsToDistanceAlgorithmAdapter> provider14, Provider<CaloriesAlgorithmAdapter> provider15, Provider<AggregatedDistanceAlgorithm> provider16, Provider<SessionMetrics> provider17, Provider<SampleStore> provider18, Provider<SessionSummaryCache> provider19, Provider<EventBus> provider20, Provider<ILog> provider21) {
        return new StaticReleasePackageModule_ProvideFitnessSessionOrchestratorFactory(staticReleasePackageModule, provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8, provider9, provider10, provider11, provider12, provider13, provider14, provider15, provider16, provider17, provider18, provider19, provider20, provider21);
    }

    public static FitnessSessionOrchestrator provideInstance(StaticReleasePackageModule staticReleasePackageModule, Provider<ComponentRegistry> provider, Provider<AfxMessageProcessor> provider2, Provider<HdsClient> provider3, Provider<FeatureService> provider4, Provider<FitnessNotificationPublisher> provider5, Provider<SessionSummaryProvider> provider6, Provider<SessionManager> provider7, Provider<FitnessAccessorySensorProvider> provider8, Provider<LocationSensorProvider> provider9, Provider<MetricEventRecorder> provider10, Provider<MetricEventFactory> provider11, Provider<MetricsAggregator> provider12, Provider<MetricsAggregatorRecovery> provider13, Provider<StepsToDistanceAlgorithmAdapter> provider14, Provider<CaloriesAlgorithmAdapter> provider15, Provider<AggregatedDistanceAlgorithm> provider16, Provider<SessionMetrics> provider17, Provider<SampleStore> provider18, Provider<SessionSummaryCache> provider19, Provider<EventBus> provider20, Provider<ILog> provider21) {
        return proxyProvideFitnessSessionOrchestrator(staticReleasePackageModule, provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get(), provider4.mo10268get(), provider5.mo10268get(), DoubleCheck.lazy(provider6), provider7.mo10268get(), provider8.mo10268get(), provider9.mo10268get(), provider10.mo10268get(), provider11.mo10268get(), provider12.mo10268get(), provider13.mo10268get(), provider14, provider15, provider16, provider17.mo10268get(), provider18.mo10268get(), provider19.mo10268get(), provider20.mo10268get(), provider21.mo10268get());
    }

    public static FitnessSessionOrchestrator proxyProvideFitnessSessionOrchestrator(StaticReleasePackageModule staticReleasePackageModule, ComponentRegistry componentRegistry, AfxMessageProcessor afxMessageProcessor, HdsClient hdsClient, FeatureService featureService, FitnessNotificationPublisher fitnessNotificationPublisher, Lazy<SessionSummaryProvider> lazy, SessionManager sessionManager, FitnessAccessorySensorProvider fitnessAccessorySensorProvider, LocationSensorProvider locationSensorProvider, MetricEventRecorder metricEventRecorder, MetricEventFactory metricEventFactory, MetricsAggregator metricsAggregator, MetricsAggregatorRecovery metricsAggregatorRecovery, Provider<StepsToDistanceAlgorithmAdapter> provider, Provider<CaloriesAlgorithmAdapter> provider2, Provider<AggregatedDistanceAlgorithm> provider3, SessionMetrics sessionMetrics, SampleStore sampleStore, SessionSummaryCache sessionSummaryCache, EventBus eventBus, ILog iLog) {
        return (FitnessSessionOrchestrator) Preconditions.checkNotNull(staticReleasePackageModule.provideFitnessSessionOrchestrator(componentRegistry, afxMessageProcessor, hdsClient, featureService, fitnessNotificationPublisher, lazy, sessionManager, fitnessAccessorySensorProvider, locationSensorProvider, metricEventRecorder, metricEventFactory, metricsAggregator, metricsAggregatorRecovery, provider, provider2, provider3, sessionMetrics, sampleStore, sessionSummaryCache, eventBus, iLog), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public FitnessSessionOrchestrator mo10268get() {
        return provideInstance(this.module, this.componentRegistryProvider, this.afxMessageProcessorProvider, this.hdsClientProvider, this.featureServiceProvider, this.fitnessNotificationPublisherProvider, this.sessionSummaryProvider, this.sessionManagerProvider, this.accessorySensorProvider, this.locationSensorProvider, this.metricEventRecorderProvider, this.metricEventFactoryProvider, this.metricsAggregatorProvider, this.metricsAggregatorRecoveryProvider, this.stepsToDistanceAlgorithmAdapterProvider, this.caloriesAlgorithmAdapterProvider, this.aggregatedDistanceAlgorithmProvider, this.sessionMetricsProvider, this.sampleStoreProvider, this.sessionSummaryCacheProvider, this.eventBusProvider, this.logProvider);
    }
}
