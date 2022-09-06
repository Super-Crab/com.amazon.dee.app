package com.amazon.dee.app.dependencies;

import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.dee.app.framework.EventBusMessagingReceiver;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class ApplicationModule_ProvideConcreteEventBusMessagingReceiverFactory implements Factory<EventBusMessagingReceiver> {
    private final Provider<EventBus> busProvider;
    private final ApplicationModule module;

    public ApplicationModule_ProvideConcreteEventBusMessagingReceiverFactory(ApplicationModule applicationModule, Provider<EventBus> provider) {
        this.module = applicationModule;
        this.busProvider = provider;
    }

    public static ApplicationModule_ProvideConcreteEventBusMessagingReceiverFactory create(ApplicationModule applicationModule, Provider<EventBus> provider) {
        return new ApplicationModule_ProvideConcreteEventBusMessagingReceiverFactory(applicationModule, provider);
    }

    public static EventBusMessagingReceiver provideInstance(ApplicationModule applicationModule, Provider<EventBus> provider) {
        return proxyProvideConcreteEventBusMessagingReceiver(applicationModule, provider.mo10268get());
    }

    public static EventBusMessagingReceiver proxyProvideConcreteEventBusMessagingReceiver(ApplicationModule applicationModule, EventBus eventBus) {
        return (EventBusMessagingReceiver) Preconditions.checkNotNull(applicationModule.provideConcreteEventBusMessagingReceiver(eventBus), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public EventBusMessagingReceiver mo10268get() {
        return provideInstance(this.module, this.busProvider);
    }
}
