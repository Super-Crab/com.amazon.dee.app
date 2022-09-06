package com.amazon.alexa.voice.wakeword;

import com.amazon.alexa.api.AlexaServicesConnection;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes11.dex */
public final class WakeWordModule_ProvideWakeWordFeatureGateFactory implements Factory<WakeWordFeatureGate> {
    private final Provider<AbiCompatibilityInterface> compatibilityInterfaceProvider;
    private final Provider<AlexaServicesConnection> connectionProvider;

    public WakeWordModule_ProvideWakeWordFeatureGateFactory(Provider<AlexaServicesConnection> provider, Provider<AbiCompatibilityInterface> provider2) {
        this.connectionProvider = provider;
        this.compatibilityInterfaceProvider = provider2;
    }

    public static WakeWordModule_ProvideWakeWordFeatureGateFactory create(Provider<AlexaServicesConnection> provider, Provider<AbiCompatibilityInterface> provider2) {
        return new WakeWordModule_ProvideWakeWordFeatureGateFactory(provider, provider2);
    }

    public static WakeWordFeatureGate provideInstance(Provider<AlexaServicesConnection> provider, Provider<AbiCompatibilityInterface> provider2) {
        return proxyProvideWakeWordFeatureGate(provider.mo10268get(), provider2.mo10268get());
    }

    public static WakeWordFeatureGate proxyProvideWakeWordFeatureGate(AlexaServicesConnection alexaServicesConnection, AbiCompatibilityInterface abiCompatibilityInterface) {
        return (WakeWordFeatureGate) Preconditions.checkNotNull(WakeWordModule.provideWakeWordFeatureGate(alexaServicesConnection, abiCompatibilityInterface), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public WakeWordFeatureGate mo10268get() {
        return provideInstance(this.connectionProvider, this.compatibilityInterfaceProvider);
    }
}
