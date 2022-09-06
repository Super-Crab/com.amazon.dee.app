package com.amazon.alexa.presence.dagger;

import com.amazon.alexa.presence.identity.PresenceAccessTokenProvider;
import com.amazon.alexa.presence.reporter.HttpAsyncTaskInstanceFactory;
import com.amazon.alexa.presence.retry.PresenceRetryStrategy;
import com.dee.app.metrics.MetricsServiceV2;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes9.dex */
public final class PresenceModule_ProvideHttpAsyncTaskInstanceFactoryFactory implements Factory<HttpAsyncTaskInstanceFactory> {
    private final Provider<MetricsServiceV2> metricsServiceV2Provider;
    private final PresenceModule module;
    private final Provider<PresenceAccessTokenProvider> presenceAccessTokenProvider;
    private final Provider<PresenceRetryStrategy> presenceRetryStrategyProvider;

    public PresenceModule_ProvideHttpAsyncTaskInstanceFactoryFactory(PresenceModule presenceModule, Provider<PresenceAccessTokenProvider> provider, Provider<PresenceRetryStrategy> provider2, Provider<MetricsServiceV2> provider3) {
        this.module = presenceModule;
        this.presenceAccessTokenProvider = provider;
        this.presenceRetryStrategyProvider = provider2;
        this.metricsServiceV2Provider = provider3;
    }

    public static PresenceModule_ProvideHttpAsyncTaskInstanceFactoryFactory create(PresenceModule presenceModule, Provider<PresenceAccessTokenProvider> provider, Provider<PresenceRetryStrategy> provider2, Provider<MetricsServiceV2> provider3) {
        return new PresenceModule_ProvideHttpAsyncTaskInstanceFactoryFactory(presenceModule, provider, provider2, provider3);
    }

    public static HttpAsyncTaskInstanceFactory provideInstance(PresenceModule presenceModule, Provider<PresenceAccessTokenProvider> provider, Provider<PresenceRetryStrategy> provider2, Provider<MetricsServiceV2> provider3) {
        return proxyProvideHttpAsyncTaskInstanceFactory(presenceModule, provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get());
    }

    public static HttpAsyncTaskInstanceFactory proxyProvideHttpAsyncTaskInstanceFactory(PresenceModule presenceModule, PresenceAccessTokenProvider presenceAccessTokenProvider, PresenceRetryStrategy presenceRetryStrategy, MetricsServiceV2 metricsServiceV2) {
        return (HttpAsyncTaskInstanceFactory) Preconditions.checkNotNull(presenceModule.provideHttpAsyncTaskInstanceFactory(presenceAccessTokenProvider, presenceRetryStrategy, metricsServiceV2), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public HttpAsyncTaskInstanceFactory mo10268get() {
        return provideInstance(this.module, this.presenceAccessTokenProvider, this.presenceRetryStrategyProvider, this.metricsServiceV2Provider);
    }
}
