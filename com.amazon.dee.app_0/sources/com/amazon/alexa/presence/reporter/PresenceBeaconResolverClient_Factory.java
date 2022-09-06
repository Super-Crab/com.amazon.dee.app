package com.amazon.alexa.presence.reporter;

import com.amazon.alexa.identity.api.PersonIdProvider;
import com.amazon.alexa.presence.detection.BeaconFormatLogic;
import com.dee.app.metrics.MetricsServiceV2;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes9.dex */
public final class PresenceBeaconResolverClient_Factory implements Factory<PresenceBeaconResolverClient> {
    private final Provider<BeaconFormatLogic> beaconFormatLogicProvider;
    private final Provider<HttpAsyncTaskInstanceFactory> httpAsyncTaskInstanceFactoryProvider;
    private final Provider<MetricsServiceV2> mMetricsServiceV2Provider;
    private final Provider<PersonIdProvider> personIdProvider;

    public PresenceBeaconResolverClient_Factory(Provider<HttpAsyncTaskInstanceFactory> provider, Provider<MetricsServiceV2> provider2, Provider<BeaconFormatLogic> provider3, Provider<PersonIdProvider> provider4) {
        this.httpAsyncTaskInstanceFactoryProvider = provider;
        this.mMetricsServiceV2Provider = provider2;
        this.beaconFormatLogicProvider = provider3;
        this.personIdProvider = provider4;
    }

    public static PresenceBeaconResolverClient_Factory create(Provider<HttpAsyncTaskInstanceFactory> provider, Provider<MetricsServiceV2> provider2, Provider<BeaconFormatLogic> provider3, Provider<PersonIdProvider> provider4) {
        return new PresenceBeaconResolverClient_Factory(provider, provider2, provider3, provider4);
    }

    public static PresenceBeaconResolverClient newPresenceBeaconResolverClient(HttpAsyncTaskInstanceFactory httpAsyncTaskInstanceFactory, MetricsServiceV2 metricsServiceV2, BeaconFormatLogic beaconFormatLogic, PersonIdProvider personIdProvider) {
        return new PresenceBeaconResolverClient(httpAsyncTaskInstanceFactory, metricsServiceV2, beaconFormatLogic, personIdProvider);
    }

    public static PresenceBeaconResolverClient provideInstance(Provider<HttpAsyncTaskInstanceFactory> provider, Provider<MetricsServiceV2> provider2, Provider<BeaconFormatLogic> provider3, Provider<PersonIdProvider> provider4) {
        return new PresenceBeaconResolverClient(provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get(), provider4.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public PresenceBeaconResolverClient mo10268get() {
        return provideInstance(this.httpAsyncTaskInstanceFactoryProvider, this.mMetricsServiceV2Provider, this.beaconFormatLogicProvider, this.personIdProvider);
    }
}
