package com.amazon.alexa.presence.reporter;

import com.amazon.alexa.presence.identity.PresenceAccessTokenProvider;
import com.amazon.alexa.presence.retry.PresenceRetryStrategy;
import com.dee.app.metrics.MetricsServiceV2;
import javax.inject.Inject;
import okhttp3.OkHttpClient;
/* loaded from: classes9.dex */
public class HttpAsyncTaskInstanceProvider implements HttpAsyncTaskInstanceFactory {
    private final MetricsServiceV2 metricsServiceV2;
    private final OkHttpClient okHttpClient;
    private final PresenceAccessTokenProvider presenceAccessTokenProvider;
    private final PresenceRetryStrategy presenceRetryStrategy;

    @Inject
    public HttpAsyncTaskInstanceProvider(OkHttpClient okHttpClient, PresenceAccessTokenProvider presenceAccessTokenProvider, PresenceRetryStrategy presenceRetryStrategy, MetricsServiceV2 metricsServiceV2) {
        this.okHttpClient = okHttpClient;
        this.presenceAccessTokenProvider = presenceAccessTokenProvider;
        this.presenceRetryStrategy = presenceRetryStrategy;
        this.metricsServiceV2 = metricsServiceV2;
    }

    @Override // com.amazon.alexa.presence.reporter.HttpAsyncTaskInstanceFactory
    public HttpAsyncTask getInstance() {
        return new HttpAsyncTask(this.okHttpClient, this.presenceAccessTokenProvider, this.presenceRetryStrategy, this.metricsServiceV2);
    }
}
