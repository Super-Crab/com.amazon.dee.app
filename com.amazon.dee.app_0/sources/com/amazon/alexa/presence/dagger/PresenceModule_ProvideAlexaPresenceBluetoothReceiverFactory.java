package com.amazon.alexa.presence.dagger;

import com.amazon.alexa.presence.PresenceController;
import com.amazon.alexa.presence.receiver.AlexaPresenceBluetoothReceiver;
import com.dee.app.metrics.MetricsServiceV2;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes9.dex */
public final class PresenceModule_ProvideAlexaPresenceBluetoothReceiverFactory implements Factory<AlexaPresenceBluetoothReceiver> {
    private final Provider<PresenceController> controllerProvider;
    private final Provider<MetricsServiceV2> metricsServiceV2Provider;
    private final PresenceModule module;

    public PresenceModule_ProvideAlexaPresenceBluetoothReceiverFactory(PresenceModule presenceModule, Provider<MetricsServiceV2> provider, Provider<PresenceController> provider2) {
        this.module = presenceModule;
        this.metricsServiceV2Provider = provider;
        this.controllerProvider = provider2;
    }

    public static PresenceModule_ProvideAlexaPresenceBluetoothReceiverFactory create(PresenceModule presenceModule, Provider<MetricsServiceV2> provider, Provider<PresenceController> provider2) {
        return new PresenceModule_ProvideAlexaPresenceBluetoothReceiverFactory(presenceModule, provider, provider2);
    }

    public static AlexaPresenceBluetoothReceiver provideInstance(PresenceModule presenceModule, Provider<MetricsServiceV2> provider, Provider<PresenceController> provider2) {
        return proxyProvideAlexaPresenceBluetoothReceiver(presenceModule, provider.mo10268get(), provider2.mo10268get());
    }

    public static AlexaPresenceBluetoothReceiver proxyProvideAlexaPresenceBluetoothReceiver(PresenceModule presenceModule, MetricsServiceV2 metricsServiceV2, PresenceController presenceController) {
        return (AlexaPresenceBluetoothReceiver) Preconditions.checkNotNull(presenceModule.provideAlexaPresenceBluetoothReceiver(metricsServiceV2, presenceController), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public AlexaPresenceBluetoothReceiver mo10268get() {
        return provideInstance(this.module, this.metricsServiceV2Provider, this.controllerProvider);
    }
}
