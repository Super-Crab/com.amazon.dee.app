package com.amazon.deecomms.calling.controller;

import com.amazon.alexa.api.AlexaServicesConnection;
import com.amazon.deecomms.alexa.fireos.CommsAlexaServicesConnectionListener;
import com.amazon.deecomms.api.CommsIdentityManager;
import com.amazon.deecomms.calling.model.BeginCallMapper;
import com.amazon.deecomms.calling.model.CallContext;
import com.amazon.deecomms.core.CapabilitiesManager;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class CallInitiationOrchestrator_Factory implements Factory<CallInitiationOrchestrator> {
    private final Provider<AlexaServicesConnection> alexaServicesConnectionProvider;
    private final Provider<BeginCallMapper> beginCallMapperProvider;
    private final Provider<CallContext> callContextProvider;
    private final Provider<CallPayloadValidator> callPayloadValidatorProvider;
    private final Provider<CapabilitiesManager> capabilitiesManagerProvider;
    private final Provider<CommsAlexaServicesConnectionListener> commsAlexaServicesConnectionListenerProvider;
    private final Provider<CommsIdentityManager> commsIdentityManagerProvider;
    private final Provider<ValidBeginCallPayloadHandler> validPayloadHandlerProvider;

    public CallInitiationOrchestrator_Factory(Provider<CallPayloadValidator> provider, Provider<CommsIdentityManager> provider2, Provider<BeginCallMapper> provider3, Provider<ValidBeginCallPayloadHandler> provider4, Provider<AlexaServicesConnection> provider5, Provider<CommsAlexaServicesConnectionListener> provider6, Provider<CallContext> provider7, Provider<CapabilitiesManager> provider8) {
        this.callPayloadValidatorProvider = provider;
        this.commsIdentityManagerProvider = provider2;
        this.beginCallMapperProvider = provider3;
        this.validPayloadHandlerProvider = provider4;
        this.alexaServicesConnectionProvider = provider5;
        this.commsAlexaServicesConnectionListenerProvider = provider6;
        this.callContextProvider = provider7;
        this.capabilitiesManagerProvider = provider8;
    }

    public static CallInitiationOrchestrator_Factory create(Provider<CallPayloadValidator> provider, Provider<CommsIdentityManager> provider2, Provider<BeginCallMapper> provider3, Provider<ValidBeginCallPayloadHandler> provider4, Provider<AlexaServicesConnection> provider5, Provider<CommsAlexaServicesConnectionListener> provider6, Provider<CallContext> provider7, Provider<CapabilitiesManager> provider8) {
        return new CallInitiationOrchestrator_Factory(provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8);
    }

    public static CallInitiationOrchestrator newCallInitiationOrchestrator(CallPayloadValidator callPayloadValidator, CommsIdentityManager commsIdentityManager, BeginCallMapper beginCallMapper, ValidBeginCallPayloadHandler validBeginCallPayloadHandler, AlexaServicesConnection alexaServicesConnection, CommsAlexaServicesConnectionListener commsAlexaServicesConnectionListener, CallContext callContext, CapabilitiesManager capabilitiesManager) {
        return new CallInitiationOrchestrator(callPayloadValidator, commsIdentityManager, beginCallMapper, validBeginCallPayloadHandler, alexaServicesConnection, commsAlexaServicesConnectionListener, callContext, capabilitiesManager);
    }

    public static CallInitiationOrchestrator provideInstance(Provider<CallPayloadValidator> provider, Provider<CommsIdentityManager> provider2, Provider<BeginCallMapper> provider3, Provider<ValidBeginCallPayloadHandler> provider4, Provider<AlexaServicesConnection> provider5, Provider<CommsAlexaServicesConnectionListener> provider6, Provider<CallContext> provider7, Provider<CapabilitiesManager> provider8) {
        return new CallInitiationOrchestrator(provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get(), provider4.mo10268get(), provider5.mo10268get(), provider6.mo10268get(), provider7.mo10268get(), provider8.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public CallInitiationOrchestrator mo10268get() {
        return provideInstance(this.callPayloadValidatorProvider, this.commsIdentityManagerProvider, this.beginCallMapperProvider, this.validPayloadHandlerProvider, this.alexaServicesConnectionProvider, this.commsAlexaServicesConnectionListenerProvider, this.callContextProvider, this.capabilitiesManagerProvider);
    }
}
