package com.amazon.alexa.featureservice.util;

import com.amazon.alexa.eventbus.api.EventBus;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes7.dex */
public final class SafeEventBus_Factory implements Factory<SafeEventBus> {
    private final Provider<EventBus> eventBusLazyProvider;

    public SafeEventBus_Factory(Provider<EventBus> provider) {
        this.eventBusLazyProvider = provider;
    }

    public static SafeEventBus_Factory create(Provider<EventBus> provider) {
        return new SafeEventBus_Factory(provider);
    }

    public static SafeEventBus newSafeEventBus(Lazy<EventBus> lazy) {
        return new SafeEventBus(lazy);
    }

    public static SafeEventBus provideInstance(Provider<EventBus> provider) {
        return new SafeEventBus(DoubleCheck.lazy(provider));
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public SafeEventBus mo10268get() {
        return provideInstance(this.eventBusLazyProvider);
    }
}
