package com.amazon.deecomms.core;

import com.amazon.alexa.api.AlexaServicesConnection;
import com.amazon.deecomms.alexa.CommsEventSender;
import com.amazon.deecomms.alexa.connection.enpoint.pcc.PCCConnectionEndpointHandler;
import com.amazon.deecomms.alexa.unsent.event.pcc.PCCQueuedEvents;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class LibraryModule_ProvidesPCCUnsentEventsManagerFactory implements Factory<PCCQueuedEvents> {
    private final Provider<AlexaServicesConnection> alexaServicesConnectionProvider;
    private final Provider<CommsEventSender> commsEventSenderProvider;
    private final Provider<PCCConnectionEndpointHandler> connectionEndpointHandlerProvider;
    private final LibraryModule module;

    public LibraryModule_ProvidesPCCUnsentEventsManagerFactory(LibraryModule libraryModule, Provider<CommsEventSender> provider, Provider<AlexaServicesConnection> provider2, Provider<PCCConnectionEndpointHandler> provider3) {
        this.module = libraryModule;
        this.commsEventSenderProvider = provider;
        this.alexaServicesConnectionProvider = provider2;
        this.connectionEndpointHandlerProvider = provider3;
    }

    public static LibraryModule_ProvidesPCCUnsentEventsManagerFactory create(LibraryModule libraryModule, Provider<CommsEventSender> provider, Provider<AlexaServicesConnection> provider2, Provider<PCCConnectionEndpointHandler> provider3) {
        return new LibraryModule_ProvidesPCCUnsentEventsManagerFactory(libraryModule, provider, provider2, provider3);
    }

    public static PCCQueuedEvents provideInstance(LibraryModule libraryModule, Provider<CommsEventSender> provider, Provider<AlexaServicesConnection> provider2, Provider<PCCConnectionEndpointHandler> provider3) {
        return (PCCQueuedEvents) Preconditions.checkNotNull(libraryModule.providesPCCUnsentEventsManager(provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static PCCQueuedEvents proxyProvidesPCCUnsentEventsManager(LibraryModule libraryModule, CommsEventSender commsEventSender, AlexaServicesConnection alexaServicesConnection, PCCConnectionEndpointHandler pCCConnectionEndpointHandler) {
        return (PCCQueuedEvents) Preconditions.checkNotNull(libraryModule.providesPCCUnsentEventsManager(commsEventSender, alexaServicesConnection, pCCConnectionEndpointHandler), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public PCCQueuedEvents mo10268get() {
        return provideInstance(this.module, this.commsEventSenderProvider, this.alexaServicesConnectionProvider, this.connectionEndpointHandlerProvider);
    }
}
