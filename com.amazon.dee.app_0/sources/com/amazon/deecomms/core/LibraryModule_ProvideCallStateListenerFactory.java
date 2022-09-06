package com.amazon.deecomms.core;

import android.content.Context;
import com.amazon.alexa.api.AlexaServicesConnection;
import com.amazon.deecomms.alexa.CommsEventSender;
import com.amazon.deecomms.alexa.connection.enpoint.pcc.PCCConnectionEndpointHandler;
import com.amazon.deecomms.alexa.fireos.CommsAlexaServicesConnectionListener;
import com.amazon.deecomms.alexa.unsent.event.pcc.PCCQueuedEvents;
import com.amazon.deecomms.calling.controller.CallManager;
import com.amazon.deecomms.calling.phonecallcontroller.CallStateListener;
import com.amazon.deecomms.calling.phonecallcontroller.PCCContextProvider;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class LibraryModule_ProvideCallStateListenerFactory implements Factory<CallStateListener> {
    private final Provider<AlexaServicesConnection> alexaServicesConnectionProvider;
    private final Provider<CallManager> callManagerProvider;
    private final Provider<CapabilitiesManager> capabilitiesManagerProvider;
    private final Provider<CommsAlexaServicesConnectionListener> commsAlexaServicesConnectionListenerProvider;
    private final Provider<CommsEventSender> commsEventSenderProvider;
    private final Provider<Context> contextProvider;
    private final LibraryModule module;
    private final Provider<PCCConnectionEndpointHandler> pccConnectionEndpointHandlerLazyProvider;
    private final Provider<PCCContextProvider> pccContextProvider;
    private final Provider<PCCQueuedEvents> pccUnsentEventManagerProvider;

    public LibraryModule_ProvideCallStateListenerFactory(LibraryModule libraryModule, Provider<Context> provider, Provider<CallManager> provider2, Provider<CommsEventSender> provider3, Provider<AlexaServicesConnection> provider4, Provider<CommsAlexaServicesConnectionListener> provider5, Provider<CapabilitiesManager> provider6, Provider<PCCContextProvider> provider7, Provider<PCCQueuedEvents> provider8, Provider<PCCConnectionEndpointHandler> provider9) {
        this.module = libraryModule;
        this.contextProvider = provider;
        this.callManagerProvider = provider2;
        this.commsEventSenderProvider = provider3;
        this.alexaServicesConnectionProvider = provider4;
        this.commsAlexaServicesConnectionListenerProvider = provider5;
        this.capabilitiesManagerProvider = provider6;
        this.pccContextProvider = provider7;
        this.pccUnsentEventManagerProvider = provider8;
        this.pccConnectionEndpointHandlerLazyProvider = provider9;
    }

    public static LibraryModule_ProvideCallStateListenerFactory create(LibraryModule libraryModule, Provider<Context> provider, Provider<CallManager> provider2, Provider<CommsEventSender> provider3, Provider<AlexaServicesConnection> provider4, Provider<CommsAlexaServicesConnectionListener> provider5, Provider<CapabilitiesManager> provider6, Provider<PCCContextProvider> provider7, Provider<PCCQueuedEvents> provider8, Provider<PCCConnectionEndpointHandler> provider9) {
        return new LibraryModule_ProvideCallStateListenerFactory(libraryModule, provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8, provider9);
    }

    public static CallStateListener provideInstance(LibraryModule libraryModule, Provider<Context> provider, Provider<CallManager> provider2, Provider<CommsEventSender> provider3, Provider<AlexaServicesConnection> provider4, Provider<CommsAlexaServicesConnectionListener> provider5, Provider<CapabilitiesManager> provider6, Provider<PCCContextProvider> provider7, Provider<PCCQueuedEvents> provider8, Provider<PCCConnectionEndpointHandler> provider9) {
        return (CallStateListener) Preconditions.checkNotNull(libraryModule.provideCallStateListener(DoubleCheck.lazy(provider), DoubleCheck.lazy(provider2), DoubleCheck.lazy(provider3), DoubleCheck.lazy(provider4), DoubleCheck.lazy(provider5), DoubleCheck.lazy(provider6), DoubleCheck.lazy(provider7), DoubleCheck.lazy(provider8), DoubleCheck.lazy(provider9)), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static CallStateListener proxyProvideCallStateListener(LibraryModule libraryModule, Lazy<Context> lazy, Lazy<CallManager> lazy2, Lazy<CommsEventSender> lazy3, Lazy<AlexaServicesConnection> lazy4, Lazy<CommsAlexaServicesConnectionListener> lazy5, Lazy<CapabilitiesManager> lazy6, Lazy<PCCContextProvider> lazy7, Lazy<PCCQueuedEvents> lazy8, Lazy<PCCConnectionEndpointHandler> lazy9) {
        return (CallStateListener) Preconditions.checkNotNull(libraryModule.provideCallStateListener(lazy, lazy2, lazy3, lazy4, lazy5, lazy6, lazy7, lazy8, lazy9), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public CallStateListener mo10268get() {
        return provideInstance(this.module, this.contextProvider, this.callManagerProvider, this.commsEventSenderProvider, this.alexaServicesConnectionProvider, this.commsAlexaServicesConnectionListenerProvider, this.capabilitiesManagerProvider, this.pccContextProvider, this.pccUnsentEventManagerProvider, this.pccConnectionEndpointHandlerLazyProvider);
    }
}
