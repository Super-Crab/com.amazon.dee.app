package com.amazon.alexa.tarazed.account;

import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.tarazed.eventbus.EventBusHandler;
import com.amazon.tarazed.core.logging.TarazedSessionLogger;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes10.dex */
public final class IdentityEventListener_Factory implements Factory<IdentityEventListener> {
    private final Provider<EventBusHandler> eventBusHandlerProvider;
    private final Provider<EventBus> eventBusProvider;
    private final Provider<TarazedSessionLogger> loggerProvider;

    public IdentityEventListener_Factory(Provider<EventBus> provider, Provider<EventBusHandler> provider2, Provider<TarazedSessionLogger> provider3) {
        this.eventBusProvider = provider;
        this.eventBusHandlerProvider = provider2;
        this.loggerProvider = provider3;
    }

    public static IdentityEventListener_Factory create(Provider<EventBus> provider, Provider<EventBusHandler> provider2, Provider<TarazedSessionLogger> provider3) {
        return new IdentityEventListener_Factory(provider, provider2, provider3);
    }

    public static IdentityEventListener newIdentityEventListener(EventBus eventBus, EventBusHandler eventBusHandler, TarazedSessionLogger tarazedSessionLogger) {
        return new IdentityEventListener(eventBus, eventBusHandler, tarazedSessionLogger);
    }

    public static IdentityEventListener provideInstance(Provider<EventBus> provider, Provider<EventBusHandler> provider2, Provider<TarazedSessionLogger> provider3) {
        return new IdentityEventListener(provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public IdentityEventListener mo10268get() {
        return provideInstance(this.eventBusProvider, this.eventBusHandlerProvider, this.loggerProvider);
    }
}
