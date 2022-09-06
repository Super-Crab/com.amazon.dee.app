package com.amazon.alexa.presence.reporter;

import com.amazon.alexa.presence.identity.PresenceAccessTokenProvider;
import com.amazon.alexa.presence.retry.PresenceRetryStrategy;
import com.dee.app.metrics.MetricsServiceV2;
import dagger.internal.Factory;
import javax.inject.Provider;
import okhttp3.OkHttpClient;
/* loaded from: classes9.dex */
public final class HttpAsyncTaskInstanceProvider_Factory implements Factory<HttpAsyncTaskInstanceProvider> {
    private final Provider<MetricsServiceV2> metricsServiceV2Provider;
    private final Provider<OkHttpClient> okHttpClientProvider;
    private final Provider<PresenceAccessTokenProvider> presenceAccessTokenProvider;
    private final Provider<PresenceRetryStrategy> presenceRetryStrategyProvider;

    public HttpAsyncTaskInstanceProvider_Factory(Provider<OkHttpClient> provider, Provider<PresenceAccessTokenProvider> provider2, Provider<PresenceRetryStrategy> provider3, Provider<MetricsServiceV2> provider4) {
        this.okHttpClientProvider = provider;
        this.presenceAccessTokenProvider = provider2;
        this.presenceRetryStrategyProvider = provider3;
        this.metricsServiceV2Provider = provider4;
    }

    public static HttpAsyncTaskInstanceProvider_Factory create(Provider<OkHttpClient> provider, Provider<PresenceAccessTokenProvider> provider2, Provider<PresenceRetryStrategy> provider3, Provider<MetricsServiceV2> provider4) {
        return new HttpAsyncTaskInstanceProvider_Factory(provider, provider2, provider3, provider4);
    }

    public static HttpAsyncTaskInstanceProvider newHttpAsyncTaskInstanceProvider(OkHttpClient okHttpClient, PresenceAccessTokenProvider presenceAccessTokenProvider, PresenceRetryStrategy presenceRetryStrategy, MetricsServiceV2 metricsServiceV2) {
        return new HttpAsyncTaskInstanceProvider(okHttpClient, presenceAccessTokenProvider, presenceRetryStrategy, metricsServiceV2);
    }

    public static HttpAsyncTaskInstanceProvider provideInstance(Provider<OkHttpClient> provider, Provider<PresenceAccessTokenProvider> provider2, Provider<PresenceRetryStrategy> provider3, Provider<MetricsServiceV2> provider4) {
        return new HttpAsyncTaskInstanceProvider(provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get(), provider4.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public HttpAsyncTaskInstanceProvider mo10268get() {
        return provideInstance(this.okHttpClientProvider, this.presenceAccessTokenProvider, this.presenceRetryStrategyProvider, this.metricsServiceV2Provider);
    }
}
