package com.amazon.alexa.client.metrics.kinesis;

import com.amazon.alexa.client.core.configuration.ClientConfiguration;
import com.amazon.alexa.client.metrics.core.DirectedIDProvider;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes6.dex */
public final class KinesisMetricsConnector_Factory implements Factory<KinesisMetricsConnector> {
    private final Provider<DirectedIDProvider> authorizationAuthorityDirectedIDProvider;
    private final Provider<ClientConfiguration> clientConfigurationProvider;
    private final Provider<KinesisManager> kinesisManagerProvider;

    public KinesisMetricsConnector_Factory(Provider<KinesisManager> provider, Provider<DirectedIDProvider> provider2, Provider<ClientConfiguration> provider3) {
        this.kinesisManagerProvider = provider;
        this.authorizationAuthorityDirectedIDProvider = provider2;
        this.clientConfigurationProvider = provider3;
    }

    public static KinesisMetricsConnector_Factory create(Provider<KinesisManager> provider, Provider<DirectedIDProvider> provider2, Provider<ClientConfiguration> provider3) {
        return new KinesisMetricsConnector_Factory(provider, provider2, provider3);
    }

    public static KinesisMetricsConnector newKinesisMetricsConnector(Lazy<KinesisManager> lazy, Lazy<DirectedIDProvider> lazy2, Lazy<ClientConfiguration> lazy3) {
        return new KinesisMetricsConnector(lazy, lazy2, lazy3);
    }

    public static KinesisMetricsConnector provideInstance(Provider<KinesisManager> provider, Provider<DirectedIDProvider> provider2, Provider<ClientConfiguration> provider3) {
        return new KinesisMetricsConnector(DoubleCheck.lazy(provider), DoubleCheck.lazy(provider2), DoubleCheck.lazy(provider3));
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public KinesisMetricsConnector mo10268get() {
        return provideInstance(this.kinesisManagerProvider, this.authorizationAuthorityDirectedIDProvider, this.clientConfigurationProvider);
    }
}
