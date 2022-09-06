package com.amazon.alexa.fitness.service.hds;

import com.amazon.alexa.fitness.configuration.HdsClientConfigurationProvider;
import com.amazon.alexa.fitness.identity.IdentityManager;
import com.amazon.alexa.fitness.logs.ILog;
import com.amazon.alexa.fitness.repository.SessionSummaryCache;
import com.amazon.alexa.mobilytics.Mobilytics;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes8.dex */
public final class HdsClientImpl_Factory implements Factory<HdsClientImpl> {
    private final Provider<HdsClientConfigurationProvider> configurationProvider;
    private final Provider<HdsThreadHandler> hdsThreadHandlerProvider;
    private final Provider<HttpClient> httpClientProvider;
    private final Provider<IdentityManager> identityManagerProvider;
    private final Provider<ILog> logProvider;
    private final Provider<Mobilytics> metricsProvider;
    private final Provider<SessionSummaryCache> sessionSummaryCacheProvider;

    public HdsClientImpl_Factory(Provider<HdsClientConfigurationProvider> provider, Provider<HdsThreadHandler> provider2, Provider<HttpClient> provider3, Provider<IdentityManager> provider4, Provider<Mobilytics> provider5, Provider<SessionSummaryCache> provider6, Provider<ILog> provider7) {
        this.configurationProvider = provider;
        this.hdsThreadHandlerProvider = provider2;
        this.httpClientProvider = provider3;
        this.identityManagerProvider = provider4;
        this.metricsProvider = provider5;
        this.sessionSummaryCacheProvider = provider6;
        this.logProvider = provider7;
    }

    public static HdsClientImpl_Factory create(Provider<HdsClientConfigurationProvider> provider, Provider<HdsThreadHandler> provider2, Provider<HttpClient> provider3, Provider<IdentityManager> provider4, Provider<Mobilytics> provider5, Provider<SessionSummaryCache> provider6, Provider<ILog> provider7) {
        return new HdsClientImpl_Factory(provider, provider2, provider3, provider4, provider5, provider6, provider7);
    }

    public static HdsClientImpl newHdsClientImpl(HdsClientConfigurationProvider hdsClientConfigurationProvider, HdsThreadHandler hdsThreadHandler, HttpClient httpClient, IdentityManager identityManager, Mobilytics mobilytics, SessionSummaryCache sessionSummaryCache, ILog iLog) {
        return new HdsClientImpl(hdsClientConfigurationProvider, hdsThreadHandler, httpClient, identityManager, mobilytics, sessionSummaryCache, iLog);
    }

    public static HdsClientImpl provideInstance(Provider<HdsClientConfigurationProvider> provider, Provider<HdsThreadHandler> provider2, Provider<HttpClient> provider3, Provider<IdentityManager> provider4, Provider<Mobilytics> provider5, Provider<SessionSummaryCache> provider6, Provider<ILog> provider7) {
        return new HdsClientImpl(provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get(), provider4.mo10268get(), provider5.mo10268get(), provider6.mo10268get(), provider7.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public HdsClientImpl mo10268get() {
        return provideInstance(this.configurationProvider, this.hdsThreadHandlerProvider, this.httpClientProvider, this.identityManagerProvider, this.metricsProvider, this.sessionSummaryCacheProvider, this.logProvider);
    }
}
