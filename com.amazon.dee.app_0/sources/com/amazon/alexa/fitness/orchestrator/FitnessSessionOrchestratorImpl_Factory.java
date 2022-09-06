package com.amazon.alexa.fitness.orchestrator;

import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.fitness.algorithm.StepsToDistanceAlgorithmAdapter;
import com.amazon.alexa.fitness.algorithm.aggregatedDistance.AggregatedDistanceAlgorithm;
import com.amazon.alexa.fitness.algorithm.calories.CaloriesAlgorithmAdapter;
import com.amazon.alexa.fitness.api.afx.FeatureService;
import com.amazon.alexa.fitness.context.SessionSummaryProvider;
import com.amazon.alexa.fitness.logs.ILog;
import com.amazon.alexa.fitness.message.notification.FitnessNotificationPublisher;
import com.amazon.alexa.fitness.metrics.MetricEventFactory;
import com.amazon.alexa.fitness.metrics.MetricEventRecorder;
import com.amazon.alexa.fitness.metrics.MetricsAggregator;
import com.amazon.alexa.fitness.metrics.SessionMetrics;
import com.amazon.alexa.fitness.repository.SessionSummaryCache;
import com.amazon.alexa.fitness.sdk.AfxMessageProcessor;
import com.amazon.alexa.fitness.sdk.MetricsAggregatorRecovery;
import com.amazon.alexa.fitness.sdk.SensorProvider;
import com.amazon.alexa.fitness.sdk.SessionManager;
import com.amazon.alexa.fitness.sdk.sample.SampleStore;
import com.amazon.alexa.fitness.service.hds.HdsClient;
import com.amazon.alexa.protocols.service.api.ComponentRegistry;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import java.util.List;
import javax.inject.Provider;
/* loaded from: classes8.dex */
public final class FitnessSessionOrchestratorImpl_Factory implements Factory<FitnessSessionOrchestratorImpl> {
    private final Provider<AfxMessageProcessor> afxMessageProcessorProvider;
    private final Provider<AggregatedDistanceAlgorithm> aggregatedDistanceAlgorithmProvider;
    private final Provider<CaloriesAlgorithmAdapter> caloriesAlgorithmProvider;
    private final Provider<ComponentRegistry> componentRegistryProvider;
    private final Provider<EventBus> eventBusProvider;
    private final Provider<FeatureService> featureServiceProvider;
    private final Provider<FitnessNotificationPublisher> fitnessNotificationPublisherProvider;
    private final Provider<HdsClient> hdsClientProvider;
    private final Provider<ILog> logProvider;
    private final Provider<MetricEventFactory> metricEventFactoryProvider;
    private final Provider<MetricEventRecorder> metricEventRecorderProvider;
    private final Provider<MetricsAggregator> metricsAggregatorProvider;
    private final Provider<MetricsAggregatorRecovery> metricsAggregatorRecoveryProvider;
    private final Provider<SampleStore> sampleStoreProvider;
    private final Provider<List<? extends SensorProvider>> sensorProvidersProvider;
    private final Provider<SessionManager> sessionManagerProvider;
    private final Provider<SessionMetrics> sessionMetricsProvider;
    private final Provider<SessionSummaryCache> sessionSummaryCacheProvider;
    private final Provider<SessionSummaryProvider> sessionSummaryProvider;
    private final Provider<StepsToDistanceAlgorithmAdapter> stepsToDistanceAlgorithmProvider;

    public FitnessSessionOrchestratorImpl_Factory(Provider<ComponentRegistry> provider, Provider<AfxMessageProcessor> provider2, Provider<HdsClient> provider3, Provider<FeatureService> provider4, Provider<FitnessNotificationPublisher> provider5, Provider<SessionSummaryProvider> provider6, Provider<SessionManager> provider7, Provider<List<? extends SensorProvider>> provider8, Provider<MetricEventRecorder> provider9, Provider<MetricEventFactory> provider10, Provider<StepsToDistanceAlgorithmAdapter> provider11, Provider<CaloriesAlgorithmAdapter> provider12, Provider<AggregatedDistanceAlgorithm> provider13, Provider<MetricsAggregator> provider14, Provider<SessionMetrics> provider15, Provider<MetricsAggregatorRecovery> provider16, Provider<SampleStore> provider17, Provider<SessionSummaryCache> provider18, Provider<EventBus> provider19, Provider<ILog> provider20) {
        this.componentRegistryProvider = provider;
        this.afxMessageProcessorProvider = provider2;
        this.hdsClientProvider = provider3;
        this.featureServiceProvider = provider4;
        this.fitnessNotificationPublisherProvider = provider5;
        this.sessionSummaryProvider = provider6;
        this.sessionManagerProvider = provider7;
        this.sensorProvidersProvider = provider8;
        this.metricEventRecorderProvider = provider9;
        this.metricEventFactoryProvider = provider10;
        this.stepsToDistanceAlgorithmProvider = provider11;
        this.caloriesAlgorithmProvider = provider12;
        this.aggregatedDistanceAlgorithmProvider = provider13;
        this.metricsAggregatorProvider = provider14;
        this.sessionMetricsProvider = provider15;
        this.metricsAggregatorRecoveryProvider = provider16;
        this.sampleStoreProvider = provider17;
        this.sessionSummaryCacheProvider = provider18;
        this.eventBusProvider = provider19;
        this.logProvider = provider20;
    }

    public static FitnessSessionOrchestratorImpl_Factory create(Provider<ComponentRegistry> provider, Provider<AfxMessageProcessor> provider2, Provider<HdsClient> provider3, Provider<FeatureService> provider4, Provider<FitnessNotificationPublisher> provider5, Provider<SessionSummaryProvider> provider6, Provider<SessionManager> provider7, Provider<List<? extends SensorProvider>> provider8, Provider<MetricEventRecorder> provider9, Provider<MetricEventFactory> provider10, Provider<StepsToDistanceAlgorithmAdapter> provider11, Provider<CaloriesAlgorithmAdapter> provider12, Provider<AggregatedDistanceAlgorithm> provider13, Provider<MetricsAggregator> provider14, Provider<SessionMetrics> provider15, Provider<MetricsAggregatorRecovery> provider16, Provider<SampleStore> provider17, Provider<SessionSummaryCache> provider18, Provider<EventBus> provider19, Provider<ILog> provider20) {
        return new FitnessSessionOrchestratorImpl_Factory(provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8, provider9, provider10, provider11, provider12, provider13, provider14, provider15, provider16, provider17, provider18, provider19, provider20);
    }

    public static FitnessSessionOrchestratorImpl newFitnessSessionOrchestratorImpl(ComponentRegistry componentRegistry, AfxMessageProcessor afxMessageProcessor, HdsClient hdsClient, FeatureService featureService, FitnessNotificationPublisher fitnessNotificationPublisher, Lazy<SessionSummaryProvider> lazy, SessionManager sessionManager, List<? extends SensorProvider> list, MetricEventRecorder metricEventRecorder, MetricEventFactory metricEventFactory, Provider<StepsToDistanceAlgorithmAdapter> provider, Provider<CaloriesAlgorithmAdapter> provider2, Provider<AggregatedDistanceAlgorithm> provider3, MetricsAggregator metricsAggregator, SessionMetrics sessionMetrics, MetricsAggregatorRecovery metricsAggregatorRecovery, SampleStore sampleStore, SessionSummaryCache sessionSummaryCache, EventBus eventBus, ILog iLog) {
        return new FitnessSessionOrchestratorImpl(componentRegistry, afxMessageProcessor, hdsClient, featureService, fitnessNotificationPublisher, lazy, sessionManager, list, metricEventRecorder, metricEventFactory, provider, provider2, provider3, metricsAggregator, sessionMetrics, metricsAggregatorRecovery, sampleStore, sessionSummaryCache, eventBus, iLog);
    }

    public static FitnessSessionOrchestratorImpl provideInstance(Provider<ComponentRegistry> provider, Provider<AfxMessageProcessor> provider2, Provider<HdsClient> provider3, Provider<FeatureService> provider4, Provider<FitnessNotificationPublisher> provider5, Provider<SessionSummaryProvider> provider6, Provider<SessionManager> provider7, Provider<List<? extends SensorProvider>> provider8, Provider<MetricEventRecorder> provider9, Provider<MetricEventFactory> provider10, Provider<StepsToDistanceAlgorithmAdapter> provider11, Provider<CaloriesAlgorithmAdapter> provider12, Provider<AggregatedDistanceAlgorithm> provider13, Provider<MetricsAggregator> provider14, Provider<SessionMetrics> provider15, Provider<MetricsAggregatorRecovery> provider16, Provider<SampleStore> provider17, Provider<SessionSummaryCache> provider18, Provider<EventBus> provider19, Provider<ILog> provider20) {
        return new FitnessSessionOrchestratorImpl(provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get(), provider4.mo10268get(), provider5.mo10268get(), DoubleCheck.lazy(provider6), provider7.mo10268get(), provider8.mo10268get(), provider9.mo10268get(), provider10.mo10268get(), provider11, provider12, provider13, provider14.mo10268get(), provider15.mo10268get(), provider16.mo10268get(), provider17.mo10268get(), provider18.mo10268get(), provider19.mo10268get(), provider20.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public FitnessSessionOrchestratorImpl mo10268get() {
        return provideInstance(this.componentRegistryProvider, this.afxMessageProcessorProvider, this.hdsClientProvider, this.featureServiceProvider, this.fitnessNotificationPublisherProvider, this.sessionSummaryProvider, this.sessionManagerProvider, this.sensorProvidersProvider, this.metricEventRecorderProvider, this.metricEventFactoryProvider, this.stepsToDistanceAlgorithmProvider, this.caloriesAlgorithmProvider, this.aggregatedDistanceAlgorithmProvider, this.metricsAggregatorProvider, this.sessionMetricsProvider, this.metricsAggregatorRecoveryProvider, this.sampleStoreProvider, this.sessionSummaryCacheProvider, this.eventBusProvider, this.logProvider);
    }
}
