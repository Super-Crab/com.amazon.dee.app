package com.amazon.alexa.voice.elements;

import com.amazon.alexa.api.AlexaServicesConnection;
import com.amazon.alexa.eventbus.api.EventBus;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes11.dex */
public final class AlexaCardModule_ProvideAlexaCardEventSenderFactory implements Factory<AlexaCardEventSender> {
    private final Provider<AlexaCardAPI> alexaCardAPIProvider;
    private final Provider<AlexaServicesConnection> alexaServicesConnectionProvider;
    private final Provider<EventBus> eventBusProvider;

    public AlexaCardModule_ProvideAlexaCardEventSenderFactory(Provider<AlexaServicesConnection> provider, Provider<AlexaCardAPI> provider2, Provider<EventBus> provider3) {
        this.alexaServicesConnectionProvider = provider;
        this.alexaCardAPIProvider = provider2;
        this.eventBusProvider = provider3;
    }

    public static AlexaCardModule_ProvideAlexaCardEventSenderFactory create(Provider<AlexaServicesConnection> provider, Provider<AlexaCardAPI> provider2, Provider<EventBus> provider3) {
        return new AlexaCardModule_ProvideAlexaCardEventSenderFactory(provider, provider2, provider3);
    }

    public static AlexaCardEventSender provideInstance(Provider<AlexaServicesConnection> provider, Provider<AlexaCardAPI> provider2, Provider<EventBus> provider3) {
        return proxyProvideAlexaCardEventSender(provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get());
    }

    public static AlexaCardEventSender proxyProvideAlexaCardEventSender(AlexaServicesConnection alexaServicesConnection, AlexaCardAPI alexaCardAPI, EventBus eventBus) {
        return (AlexaCardEventSender) Preconditions.checkNotNull(AlexaCardModule.provideAlexaCardEventSender(alexaServicesConnection, alexaCardAPI, eventBus), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public AlexaCardEventSender mo10268get() {
        return provideInstance(this.alexaServicesConnectionProvider, this.alexaCardAPIProvider, this.eventBusProvider);
    }
}
