package com.amazon.alexa.handsfree.remotecontentservice.apigateway.remoteconfig;

import android.content.Context;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes8.dex */
public final class RemoteConfigDeserializer_Factory implements Factory<RemoteConfigDeserializer> {
    private final Provider<Context> arg0Provider;
    private final Provider<MetricsReporter> arg1Provider;

    public RemoteConfigDeserializer_Factory(Provider<Context> provider, Provider<MetricsReporter> provider2) {
        this.arg0Provider = provider;
        this.arg1Provider = provider2;
    }

    public static RemoteConfigDeserializer_Factory create(Provider<Context> provider, Provider<MetricsReporter> provider2) {
        return new RemoteConfigDeserializer_Factory(provider, provider2);
    }

    public static RemoteConfigDeserializer newRemoteConfigDeserializer(Context context, MetricsReporter metricsReporter) {
        return new RemoteConfigDeserializer(context, metricsReporter);
    }

    public static RemoteConfigDeserializer provideInstance(Provider<Context> provider, Provider<MetricsReporter> provider2) {
        return new RemoteConfigDeserializer(provider.mo10268get(), provider2.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public RemoteConfigDeserializer mo10268get() {
        return provideInstance(this.arg0Provider, this.arg1Provider);
    }
}
