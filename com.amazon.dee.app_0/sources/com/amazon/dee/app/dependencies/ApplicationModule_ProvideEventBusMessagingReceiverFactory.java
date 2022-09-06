package com.amazon.dee.app.dependencies;

import com.amazon.alexa.protocols.messaging.MessagingReceiver;
import com.amazon.dee.app.framework.EventBusMessagingReceiver;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class ApplicationModule_ProvideEventBusMessagingReceiverFactory implements Factory<MessagingReceiver> {
    private final Provider<EventBusMessagingReceiver> concreteReceiverProvider;
    private final ApplicationModule module;

    public ApplicationModule_ProvideEventBusMessagingReceiverFactory(ApplicationModule applicationModule, Provider<EventBusMessagingReceiver> provider) {
        this.module = applicationModule;
        this.concreteReceiverProvider = provider;
    }

    public static ApplicationModule_ProvideEventBusMessagingReceiverFactory create(ApplicationModule applicationModule, Provider<EventBusMessagingReceiver> provider) {
        return new ApplicationModule_ProvideEventBusMessagingReceiverFactory(applicationModule, provider);
    }

    public static MessagingReceiver provideInstance(ApplicationModule applicationModule, Provider<EventBusMessagingReceiver> provider) {
        return proxyProvideEventBusMessagingReceiver(applicationModule, provider.mo10268get());
    }

    public static MessagingReceiver proxyProvideEventBusMessagingReceiver(ApplicationModule applicationModule, EventBusMessagingReceiver eventBusMessagingReceiver) {
        return (MessagingReceiver) Preconditions.checkNotNull(applicationModule.provideEventBusMessagingReceiver(eventBusMessagingReceiver), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public MessagingReceiver mo10268get() {
        return provideInstance(this.module, this.concreteReceiverProvider);
    }
}
