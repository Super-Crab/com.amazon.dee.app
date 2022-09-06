package com.amazon.deecomms.alexa.voice.usecase;

import com.amazon.alexa.api.AlexaServicesConnection;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class EventSenderUseCase_Factory implements Factory<EventSenderUseCase> {
    private final Provider<AlexaServicesConnection> alexaServicesConnectionProvider;

    public EventSenderUseCase_Factory(Provider<AlexaServicesConnection> provider) {
        this.alexaServicesConnectionProvider = provider;
    }

    public static EventSenderUseCase_Factory create(Provider<AlexaServicesConnection> provider) {
        return new EventSenderUseCase_Factory(provider);
    }

    public static EventSenderUseCase newEventSenderUseCase(AlexaServicesConnection alexaServicesConnection) {
        return new EventSenderUseCase(alexaServicesConnection);
    }

    public static EventSenderUseCase provideInstance(Provider<AlexaServicesConnection> provider) {
        return new EventSenderUseCase(provider.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public EventSenderUseCase mo10268get() {
        return provideInstance(this.alexaServicesConnectionProvider);
    }
}
