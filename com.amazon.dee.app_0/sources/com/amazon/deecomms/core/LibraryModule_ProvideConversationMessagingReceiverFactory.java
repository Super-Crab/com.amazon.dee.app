package com.amazon.deecomms.core;

import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.protocols.messaging.MessagingReceiver;
import com.amazon.deecomms.api.CommsManager;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class LibraryModule_ProvideConversationMessagingReceiverFactory implements Factory<MessagingReceiver> {
    private final Provider<CommsManager> commsManagerProvider;
    private final Provider<EventBus> eventBusProvider;
    private final LibraryModule module;

    public LibraryModule_ProvideConversationMessagingReceiverFactory(LibraryModule libraryModule, Provider<CommsManager> provider, Provider<EventBus> provider2) {
        this.module = libraryModule;
        this.commsManagerProvider = provider;
        this.eventBusProvider = provider2;
    }

    public static LibraryModule_ProvideConversationMessagingReceiverFactory create(LibraryModule libraryModule, Provider<CommsManager> provider, Provider<EventBus> provider2) {
        return new LibraryModule_ProvideConversationMessagingReceiverFactory(libraryModule, provider, provider2);
    }

    public static MessagingReceiver provideInstance(LibraryModule libraryModule, Provider<CommsManager> provider, Provider<EventBus> provider2) {
        return (MessagingReceiver) Preconditions.checkNotNull(libraryModule.provideConversationMessagingReceiver(provider.mo10268get(), provider2.mo10268get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static MessagingReceiver proxyProvideConversationMessagingReceiver(LibraryModule libraryModule, CommsManager commsManager, EventBus eventBus) {
        return (MessagingReceiver) Preconditions.checkNotNull(libraryModule.provideConversationMessagingReceiver(commsManager, eventBus), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public MessagingReceiver mo10268get() {
        return provideInstance(this.module, this.commsManagerProvider, this.eventBusProvider);
    }
}
