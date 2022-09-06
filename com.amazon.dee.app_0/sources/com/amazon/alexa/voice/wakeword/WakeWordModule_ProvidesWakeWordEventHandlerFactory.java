package com.amazon.alexa.voice.wakeword;

import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.protocols.lifecycle.ApplicationLifecycleService;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes11.dex */
public final class WakeWordModule_ProvidesWakeWordEventHandlerFactory implements Factory<WakeWordEventHandler> {
    private final Provider<ApplicationLifecycleService> applicationLifecycleServiceProvider;
    private final Provider<EventBus> eventBusProvider;

    public WakeWordModule_ProvidesWakeWordEventHandlerFactory(Provider<EventBus> provider, Provider<ApplicationLifecycleService> provider2) {
        this.eventBusProvider = provider;
        this.applicationLifecycleServiceProvider = provider2;
    }

    public static WakeWordModule_ProvidesWakeWordEventHandlerFactory create(Provider<EventBus> provider, Provider<ApplicationLifecycleService> provider2) {
        return new WakeWordModule_ProvidesWakeWordEventHandlerFactory(provider, provider2);
    }

    public static WakeWordEventHandler provideInstance(Provider<EventBus> provider, Provider<ApplicationLifecycleService> provider2) {
        return proxyProvidesWakeWordEventHandler(provider.mo10268get(), provider2.mo10268get());
    }

    public static WakeWordEventHandler proxyProvidesWakeWordEventHandler(EventBus eventBus, ApplicationLifecycleService applicationLifecycleService) {
        return (WakeWordEventHandler) Preconditions.checkNotNull(WakeWordModule.providesWakeWordEventHandler(eventBus, applicationLifecycleService), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public WakeWordEventHandler mo10268get() {
        return provideInstance(this.eventBusProvider, this.applicationLifecycleServiceProvider);
    }
}
