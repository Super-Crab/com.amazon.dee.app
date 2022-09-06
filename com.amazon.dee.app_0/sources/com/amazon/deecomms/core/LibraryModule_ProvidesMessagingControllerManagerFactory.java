package com.amazon.deecomms.core;

import com.amazon.alexa.api.AlexaServicesConnection;
import com.amazon.deecomms.smsmessaging.messagingcontroller.MessagingControllerContextProvider;
import com.amazon.deecomms.smsmessaging.messagingcontroller.MessagingControllerManager;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class LibraryModule_ProvidesMessagingControllerManagerFactory implements Factory<MessagingControllerManager> {
    private final Provider<AlexaServicesConnection> alexaServicesConnectionProvider;
    private final Provider<CapabilitiesManager> capabilitiesManagerProvider;
    private final Provider<MessagingControllerContextProvider> messagingControllerContextProvider;
    private final LibraryModule module;

    public LibraryModule_ProvidesMessagingControllerManagerFactory(LibraryModule libraryModule, Provider<CapabilitiesManager> provider, Provider<AlexaServicesConnection> provider2, Provider<MessagingControllerContextProvider> provider3) {
        this.module = libraryModule;
        this.capabilitiesManagerProvider = provider;
        this.alexaServicesConnectionProvider = provider2;
        this.messagingControllerContextProvider = provider3;
    }

    public static LibraryModule_ProvidesMessagingControllerManagerFactory create(LibraryModule libraryModule, Provider<CapabilitiesManager> provider, Provider<AlexaServicesConnection> provider2, Provider<MessagingControllerContextProvider> provider3) {
        return new LibraryModule_ProvidesMessagingControllerManagerFactory(libraryModule, provider, provider2, provider3);
    }

    public static MessagingControllerManager provideInstance(LibraryModule libraryModule, Provider<CapabilitiesManager> provider, Provider<AlexaServicesConnection> provider2, Provider<MessagingControllerContextProvider> provider3) {
        return (MessagingControllerManager) Preconditions.checkNotNull(libraryModule.providesMessagingControllerManager(provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static MessagingControllerManager proxyProvidesMessagingControllerManager(LibraryModule libraryModule, CapabilitiesManager capabilitiesManager, AlexaServicesConnection alexaServicesConnection, MessagingControllerContextProvider messagingControllerContextProvider) {
        return (MessagingControllerManager) Preconditions.checkNotNull(libraryModule.providesMessagingControllerManager(capabilitiesManager, alexaServicesConnection, messagingControllerContextProvider), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public MessagingControllerManager mo10268get() {
        return provideInstance(this.module, this.capabilitiesManagerProvider, this.alexaServicesConnectionProvider, this.messagingControllerContextProvider);
    }
}
