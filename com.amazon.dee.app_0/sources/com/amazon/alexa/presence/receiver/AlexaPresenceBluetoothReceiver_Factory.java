package com.amazon.alexa.presence.receiver;

import com.amazon.alexa.presence.PresenceController;
import com.dee.app.metrics.MetricsServiceV2;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes9.dex */
public final class AlexaPresenceBluetoothReceiver_Factory implements Factory<AlexaPresenceBluetoothReceiver> {
    private final Provider<PresenceController> controllerProvider;
    private final Provider<MetricsServiceV2> metricsServiceV2Provider;

    public AlexaPresenceBluetoothReceiver_Factory(Provider<MetricsServiceV2> provider, Provider<PresenceController> provider2) {
        this.metricsServiceV2Provider = provider;
        this.controllerProvider = provider2;
    }

    public static AlexaPresenceBluetoothReceiver_Factory create(Provider<MetricsServiceV2> provider, Provider<PresenceController> provider2) {
        return new AlexaPresenceBluetoothReceiver_Factory(provider, provider2);
    }

    public static AlexaPresenceBluetoothReceiver newAlexaPresenceBluetoothReceiver(MetricsServiceV2 metricsServiceV2, PresenceController presenceController) {
        return new AlexaPresenceBluetoothReceiver(metricsServiceV2, presenceController);
    }

    public static AlexaPresenceBluetoothReceiver provideInstance(Provider<MetricsServiceV2> provider, Provider<PresenceController> provider2) {
        return new AlexaPresenceBluetoothReceiver(provider.mo10268get(), provider2.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public AlexaPresenceBluetoothReceiver mo10268get() {
        return provideInstance(this.metricsServiceV2Provider, this.controllerProvider);
    }
}
