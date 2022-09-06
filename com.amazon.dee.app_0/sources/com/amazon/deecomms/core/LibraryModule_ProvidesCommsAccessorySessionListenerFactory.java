package com.amazon.deecomms.core;

import android.content.Context;
import com.amazon.alexa.api.AlexaServicesConnection;
import com.amazon.deecomms.accessories.CommsAccessorySessionListener;
import com.amazon.deecomms.alexa.CommsEventSender;
import com.amazon.deecomms.alexa.connection.enpoint.pcc.PCCConnectionEndpointHandler;
import com.amazon.deecomms.alexa.fireos.CommsAlexaServicesConnectionListener;
import com.amazon.deecomms.calling.controller.CallManager;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class LibraryModule_ProvidesCommsAccessorySessionListenerFactory implements Factory<CommsAccessorySessionListener> {
    private final Provider<AlexaServicesConnection> alexaServicesConnectionProvider;
    private final Provider<CallManager> callManagerProvider;
    private final Provider<CapabilitiesManager> capabilitiesManagerProvider;
    private final Provider<CommsAlexaServicesConnectionListener> commsAlexaServicesConnectionListenerProvider;
    private final Provider<CommsEventSender> commsEventSenderProvider;
    private final Provider<Context> contextProvider;
    private final LibraryModule module;
    private final Provider<PCCConnectionEndpointHandler> pccConnectionEndpointHandlerProvider;

    public LibraryModule_ProvidesCommsAccessorySessionListenerFactory(LibraryModule libraryModule, Provider<AlexaServicesConnection> provider, Provider<Context> provider2, Provider<CommsAlexaServicesConnectionListener> provider3, Provider<CapabilitiesManager> provider4, Provider<CallManager> provider5, Provider<CommsEventSender> provider6, Provider<PCCConnectionEndpointHandler> provider7) {
        this.module = libraryModule;
        this.alexaServicesConnectionProvider = provider;
        this.contextProvider = provider2;
        this.commsAlexaServicesConnectionListenerProvider = provider3;
        this.capabilitiesManagerProvider = provider4;
        this.callManagerProvider = provider5;
        this.commsEventSenderProvider = provider6;
        this.pccConnectionEndpointHandlerProvider = provider7;
    }

    public static LibraryModule_ProvidesCommsAccessorySessionListenerFactory create(LibraryModule libraryModule, Provider<AlexaServicesConnection> provider, Provider<Context> provider2, Provider<CommsAlexaServicesConnectionListener> provider3, Provider<CapabilitiesManager> provider4, Provider<CallManager> provider5, Provider<CommsEventSender> provider6, Provider<PCCConnectionEndpointHandler> provider7) {
        return new LibraryModule_ProvidesCommsAccessorySessionListenerFactory(libraryModule, provider, provider2, provider3, provider4, provider5, provider6, provider7);
    }

    public static CommsAccessorySessionListener provideInstance(LibraryModule libraryModule, Provider<AlexaServicesConnection> provider, Provider<Context> provider2, Provider<CommsAlexaServicesConnectionListener> provider3, Provider<CapabilitiesManager> provider4, Provider<CallManager> provider5, Provider<CommsEventSender> provider6, Provider<PCCConnectionEndpointHandler> provider7) {
        return (CommsAccessorySessionListener) Preconditions.checkNotNull(libraryModule.providesCommsAccessorySessionListener(provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get(), provider4.mo10268get(), provider5.mo10268get(), provider6.mo10268get(), provider7.mo10268get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static CommsAccessorySessionListener proxyProvidesCommsAccessorySessionListener(LibraryModule libraryModule, AlexaServicesConnection alexaServicesConnection, Context context, CommsAlexaServicesConnectionListener commsAlexaServicesConnectionListener, CapabilitiesManager capabilitiesManager, CallManager callManager, CommsEventSender commsEventSender, PCCConnectionEndpointHandler pCCConnectionEndpointHandler) {
        return (CommsAccessorySessionListener) Preconditions.checkNotNull(libraryModule.providesCommsAccessorySessionListener(alexaServicesConnection, context, commsAlexaServicesConnectionListener, capabilitiesManager, callManager, commsEventSender, pCCConnectionEndpointHandler), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public CommsAccessorySessionListener mo10268get() {
        return provideInstance(this.module, this.alexaServicesConnectionProvider, this.contextProvider, this.commsAlexaServicesConnectionListenerProvider, this.capabilitiesManagerProvider, this.callManagerProvider, this.commsEventSenderProvider, this.pccConnectionEndpointHandlerProvider);
    }
}
