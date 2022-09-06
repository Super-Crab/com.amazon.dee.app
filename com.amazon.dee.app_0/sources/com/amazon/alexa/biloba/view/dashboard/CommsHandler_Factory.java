package com.amazon.alexa.biloba.view.dashboard;

import com.amazon.alexa.eventbus.api.EventBus;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes6.dex */
public final class CommsHandler_Factory implements Factory<CommsHandler> {
    private final Provider<EventBus> eventBusProvider;

    public CommsHandler_Factory(Provider<EventBus> provider) {
        this.eventBusProvider = provider;
    }

    public static CommsHandler_Factory create(Provider<EventBus> provider) {
        return new CommsHandler_Factory(provider);
    }

    public static CommsHandler newCommsHandler(Lazy<EventBus> lazy) {
        return new CommsHandler(lazy);
    }

    public static CommsHandler provideInstance(Provider<EventBus> provider) {
        return new CommsHandler(DoubleCheck.lazy(provider));
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public CommsHandler mo10268get() {
        return provideInstance(this.eventBusProvider);
    }
}
