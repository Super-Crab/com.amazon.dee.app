package com.amazon.alexa.presence.dagger;

import com.amazon.alexa.presence.detection.BeaconFormatLogic;
import com.amazon.alexa.presence.detection.BlePacketConsumer;
import com.amazon.alexa.presence.reporter.PresenceBeaconResolverClient;
import com.dee.app.metrics.MetricsServiceV2;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes9.dex */
public final class PresenceModule_ProvidePostDetectionLogicFactory implements Factory<BlePacketConsumer> {
    private final Provider<BeaconFormatLogic> beaconFormatLogicProvider;
    private final Provider<MetricsServiceV2> metricsServiceV2Provider;
    private final PresenceModule module;
    private final Provider<PresenceBeaconResolverClient> resolverClientProvider;

    public PresenceModule_ProvidePostDetectionLogicFactory(PresenceModule presenceModule, Provider<MetricsServiceV2> provider, Provider<BeaconFormatLogic> provider2, Provider<PresenceBeaconResolverClient> provider3) {
        this.module = presenceModule;
        this.metricsServiceV2Provider = provider;
        this.beaconFormatLogicProvider = provider2;
        this.resolverClientProvider = provider3;
    }

    public static PresenceModule_ProvidePostDetectionLogicFactory create(PresenceModule presenceModule, Provider<MetricsServiceV2> provider, Provider<BeaconFormatLogic> provider2, Provider<PresenceBeaconResolverClient> provider3) {
        return new PresenceModule_ProvidePostDetectionLogicFactory(presenceModule, provider, provider2, provider3);
    }

    public static BlePacketConsumer provideInstance(PresenceModule presenceModule, Provider<MetricsServiceV2> provider, Provider<BeaconFormatLogic> provider2, Provider<PresenceBeaconResolverClient> provider3) {
        return proxyProvidePostDetectionLogic(presenceModule, provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get());
    }

    public static BlePacketConsumer proxyProvidePostDetectionLogic(PresenceModule presenceModule, MetricsServiceV2 metricsServiceV2, BeaconFormatLogic beaconFormatLogic, PresenceBeaconResolverClient presenceBeaconResolverClient) {
        return (BlePacketConsumer) Preconditions.checkNotNull(presenceModule.providePostDetectionLogic(metricsServiceV2, beaconFormatLogic, presenceBeaconResolverClient), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public BlePacketConsumer mo10268get() {
        return provideInstance(this.module, this.metricsServiceV2Provider, this.beaconFormatLogicProvider, this.resolverClientProvider);
    }
}
