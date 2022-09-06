package com.amazon.alexa.fitness.service.hds;

import com.amazon.alexa.fitness.logs.ILog;
import com.amazon.alexa.fitness.metrics.MetricEventFactory;
import com.amazon.alexa.fitness.metrics.MetricEventRecorder;
import com.amazon.alexa.fitness.repository.SessionSummaryCache;
import com.amazon.alexa.mobilytics.Mobilytics;
import com.amazon.alexa.protocols.network.NetworkService;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes8.dex */
public final class HdsRetryHandlerImpl_Factory implements Factory<HdsRetryHandlerImpl> {
    private final Provider<HdsClient> hdsClientProvider;
    private final Provider<HdsThreadHandler> hdsThreadHandlerProvider;
    private final Provider<ILog> logProvider;
    private final Provider<MetricEventFactory> metricEventFactoryProvider;
    private final Provider<MetricEventRecorder> metricEventRecorderProvider;
    private final Provider<Mobilytics> metricsProvider;
    private final Provider<NetworkService> networkServiceProvider;
    private final Provider<SessionSummaryCache> sessionSummaryCacheProvider;

    public HdsRetryHandlerImpl_Factory(Provider<HdsClient> provider, Provider<HdsThreadHandler> provider2, Provider<Mobilytics> provider3, Provider<MetricEventFactory> provider4, Provider<MetricEventRecorder> provider5, Provider<NetworkService> provider6, Provider<SessionSummaryCache> provider7, Provider<ILog> provider8) {
        this.hdsClientProvider = provider;
        this.hdsThreadHandlerProvider = provider2;
        this.metricsProvider = provider3;
        this.metricEventFactoryProvider = provider4;
        this.metricEventRecorderProvider = provider5;
        this.networkServiceProvider = provider6;
        this.sessionSummaryCacheProvider = provider7;
        this.logProvider = provider8;
    }

    public static HdsRetryHandlerImpl_Factory create(Provider<HdsClient> provider, Provider<HdsThreadHandler> provider2, Provider<Mobilytics> provider3, Provider<MetricEventFactory> provider4, Provider<MetricEventRecorder> provider5, Provider<NetworkService> provider6, Provider<SessionSummaryCache> provider7, Provider<ILog> provider8) {
        return new HdsRetryHandlerImpl_Factory(provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8);
    }

    public static HdsRetryHandlerImpl newHdsRetryHandlerImpl(HdsClient hdsClient, HdsThreadHandler hdsThreadHandler, Mobilytics mobilytics, MetricEventFactory metricEventFactory, MetricEventRecorder metricEventRecorder, NetworkService networkService, SessionSummaryCache sessionSummaryCache, ILog iLog) {
        return new HdsRetryHandlerImpl(hdsClient, hdsThreadHandler, mobilytics, metricEventFactory, metricEventRecorder, networkService, sessionSummaryCache, iLog);
    }

    public static HdsRetryHandlerImpl provideInstance(Provider<HdsClient> provider, Provider<HdsThreadHandler> provider2, Provider<Mobilytics> provider3, Provider<MetricEventFactory> provider4, Provider<MetricEventRecorder> provider5, Provider<NetworkService> provider6, Provider<SessionSummaryCache> provider7, Provider<ILog> provider8) {
        return new HdsRetryHandlerImpl(provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get(), provider4.mo10268get(), provider5.mo10268get(), provider6.mo10268get(), provider7.mo10268get(), provider8.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public HdsRetryHandlerImpl mo10268get() {
        return provideInstance(this.hdsClientProvider, this.hdsThreadHandlerProvider, this.metricsProvider, this.metricEventFactoryProvider, this.metricEventRecorderProvider, this.networkServiceProvider, this.sessionSummaryCacheProvider, this.logProvider);
    }
}
