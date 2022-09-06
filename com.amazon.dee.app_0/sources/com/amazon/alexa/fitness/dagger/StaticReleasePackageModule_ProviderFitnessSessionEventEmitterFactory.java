package com.amazon.alexa.fitness.dagger;

import com.amazon.alexa.fitness.message.FitnessSessionEventEmitter;
import com.amazon.alexa.fitness.message.SpeechletEventEmitter;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes8.dex */
public final class StaticReleasePackageModule_ProviderFitnessSessionEventEmitterFactory implements Factory<FitnessSessionEventEmitter> {
    private final StaticReleasePackageModule module;
    private final Provider<SpeechletEventEmitter> speechletEventEmitterProvider;

    public StaticReleasePackageModule_ProviderFitnessSessionEventEmitterFactory(StaticReleasePackageModule staticReleasePackageModule, Provider<SpeechletEventEmitter> provider) {
        this.module = staticReleasePackageModule;
        this.speechletEventEmitterProvider = provider;
    }

    public static StaticReleasePackageModule_ProviderFitnessSessionEventEmitterFactory create(StaticReleasePackageModule staticReleasePackageModule, Provider<SpeechletEventEmitter> provider) {
        return new StaticReleasePackageModule_ProviderFitnessSessionEventEmitterFactory(staticReleasePackageModule, provider);
    }

    public static FitnessSessionEventEmitter provideInstance(StaticReleasePackageModule staticReleasePackageModule, Provider<SpeechletEventEmitter> provider) {
        return proxyProviderFitnessSessionEventEmitter(staticReleasePackageModule, provider.mo10268get());
    }

    public static FitnessSessionEventEmitter proxyProviderFitnessSessionEventEmitter(StaticReleasePackageModule staticReleasePackageModule, SpeechletEventEmitter speechletEventEmitter) {
        return (FitnessSessionEventEmitter) Preconditions.checkNotNull(staticReleasePackageModule.providerFitnessSessionEventEmitter(speechletEventEmitter), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public FitnessSessionEventEmitter mo10268get() {
        return provideInstance(this.module, this.speechletEventEmitterProvider);
    }
}
