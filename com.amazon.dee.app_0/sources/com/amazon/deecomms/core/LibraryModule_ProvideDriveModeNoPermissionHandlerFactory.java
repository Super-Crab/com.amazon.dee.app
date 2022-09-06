package com.amazon.deecomms.core;

import android.content.Context;
import com.amazon.alexa.api.AlexaServicesConnection;
import com.amazon.deecomms.alexa.CommsEventSender;
import com.amazon.deecomms.alexa.connection.enpoint.pcc.PCCConnectionEndpointHandler;
import com.amazon.deecomms.alexa.fireos.CommsAlexaServicesConnectionListener;
import com.amazon.deecomms.alexa.unsent.event.pcc.PCCQueuedEvents;
import com.amazon.deecomms.calling.phonecallcontroller.DriveModeCallPermissionHandler;
import com.amazon.deecomms.calling.phonecallcontroller.PCCContextProvider;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class LibraryModule_ProvideDriveModeNoPermissionHandlerFactory implements Factory<DriveModeCallPermissionHandler> {
    private final Provider<AlexaServicesConnection> alexaServicesConnectionProvider;
    private final Provider<CapabilitiesManager> capabilitiesManagerProvider;
    private final Provider<CommsEventSender> commsEventSenderProvider;
    private final Provider<Context> contextProvider;
    private final Provider<CommsAlexaServicesConnectionListener> listenerProvider;
    private final LibraryModule module;
    private final Provider<PCCConnectionEndpointHandler> pccConnectionEndpointHandlerProvider;
    private final Provider<PCCContextProvider> pccContextProvider;
    private final Provider<PCCQueuedEvents> pccPendingEventsManagerProvider;

    public LibraryModule_ProvideDriveModeNoPermissionHandlerFactory(LibraryModule libraryModule, Provider<AlexaServicesConnection> provider, Provider<CapabilitiesManager> provider2, Provider<CommsEventSender> provider3, Provider<CommsAlexaServicesConnectionListener> provider4, Provider<PCCContextProvider> provider5, Provider<Context> provider6, Provider<PCCQueuedEvents> provider7, Provider<PCCConnectionEndpointHandler> provider8) {
        this.module = libraryModule;
        this.alexaServicesConnectionProvider = provider;
        this.capabilitiesManagerProvider = provider2;
        this.commsEventSenderProvider = provider3;
        this.listenerProvider = provider4;
        this.pccContextProvider = provider5;
        this.contextProvider = provider6;
        this.pccPendingEventsManagerProvider = provider7;
        this.pccConnectionEndpointHandlerProvider = provider8;
    }

    public static LibraryModule_ProvideDriveModeNoPermissionHandlerFactory create(LibraryModule libraryModule, Provider<AlexaServicesConnection> provider, Provider<CapabilitiesManager> provider2, Provider<CommsEventSender> provider3, Provider<CommsAlexaServicesConnectionListener> provider4, Provider<PCCContextProvider> provider5, Provider<Context> provider6, Provider<PCCQueuedEvents> provider7, Provider<PCCConnectionEndpointHandler> provider8) {
        return new LibraryModule_ProvideDriveModeNoPermissionHandlerFactory(libraryModule, provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8);
    }

    public static DriveModeCallPermissionHandler provideInstance(LibraryModule libraryModule, Provider<AlexaServicesConnection> provider, Provider<CapabilitiesManager> provider2, Provider<CommsEventSender> provider3, Provider<CommsAlexaServicesConnectionListener> provider4, Provider<PCCContextProvider> provider5, Provider<Context> provider6, Provider<PCCQueuedEvents> provider7, Provider<PCCConnectionEndpointHandler> provider8) {
        return (DriveModeCallPermissionHandler) Preconditions.checkNotNull(libraryModule.provideDriveModeNoPermissionHandler(provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get(), provider4.mo10268get(), provider5.mo10268get(), provider6.mo10268get(), provider7.mo10268get(), provider8.mo10268get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static DriveModeCallPermissionHandler proxyProvideDriveModeNoPermissionHandler(LibraryModule libraryModule, AlexaServicesConnection alexaServicesConnection, CapabilitiesManager capabilitiesManager, CommsEventSender commsEventSender, CommsAlexaServicesConnectionListener commsAlexaServicesConnectionListener, PCCContextProvider pCCContextProvider, Context context, PCCQueuedEvents pCCQueuedEvents, PCCConnectionEndpointHandler pCCConnectionEndpointHandler) {
        return (DriveModeCallPermissionHandler) Preconditions.checkNotNull(libraryModule.provideDriveModeNoPermissionHandler(alexaServicesConnection, capabilitiesManager, commsEventSender, commsAlexaServicesConnectionListener, pCCContextProvider, context, pCCQueuedEvents, pCCConnectionEndpointHandler), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public DriveModeCallPermissionHandler mo10268get() {
        return provideInstance(this.module, this.alexaServicesConnectionProvider, this.capabilitiesManagerProvider, this.commsEventSenderProvider, this.listenerProvider, this.pccContextProvider, this.contextProvider, this.pccPendingEventsManagerProvider, this.pccConnectionEndpointHandlerProvider);
    }
}
