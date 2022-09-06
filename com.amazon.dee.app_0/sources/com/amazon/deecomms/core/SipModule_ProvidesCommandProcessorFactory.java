package com.amazon.deecomms.core;

import com.amazon.comms.calling.service.DeviceCallingService;
import com.amazon.comms.instrumentation.EventTracerFactory;
import com.amazon.deecomms.calling.accessibility.RealTimeTextEnablementAuthority;
import com.amazon.deecomms.calling.controller.CallManager;
import com.amazon.deecomms.calling.controller.CommandProcessor;
import com.amazon.deecomms.common.sip.SipClientState;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class SipModule_ProvidesCommandProcessorFactory implements Factory<CommandProcessor> {
    private final Provider<CallManager> callManagerProvider;
    private final Provider<CapabilitiesManager> capabilitiesManagerProvider;
    private final Provider<DeviceCallingService> deviceCallingServiceProvider;
    private final Provider<EventTracerFactory> eventTracerFactoryProvider;
    private final SipModule module;
    private final Provider<RealTimeTextEnablementAuthority> realTimeTextEnablementAuthorityProvider;
    private final Provider<SipClientState> sipClientStateProvider;

    public SipModule_ProvidesCommandProcessorFactory(SipModule sipModule, Provider<DeviceCallingService> provider, Provider<EventTracerFactory> provider2, Provider<SipClientState> provider3, Provider<CallManager> provider4, Provider<CapabilitiesManager> provider5, Provider<RealTimeTextEnablementAuthority> provider6) {
        this.module = sipModule;
        this.deviceCallingServiceProvider = provider;
        this.eventTracerFactoryProvider = provider2;
        this.sipClientStateProvider = provider3;
        this.callManagerProvider = provider4;
        this.capabilitiesManagerProvider = provider5;
        this.realTimeTextEnablementAuthorityProvider = provider6;
    }

    public static SipModule_ProvidesCommandProcessorFactory create(SipModule sipModule, Provider<DeviceCallingService> provider, Provider<EventTracerFactory> provider2, Provider<SipClientState> provider3, Provider<CallManager> provider4, Provider<CapabilitiesManager> provider5, Provider<RealTimeTextEnablementAuthority> provider6) {
        return new SipModule_ProvidesCommandProcessorFactory(sipModule, provider, provider2, provider3, provider4, provider5, provider6);
    }

    public static CommandProcessor provideInstance(SipModule sipModule, Provider<DeviceCallingService> provider, Provider<EventTracerFactory> provider2, Provider<SipClientState> provider3, Provider<CallManager> provider4, Provider<CapabilitiesManager> provider5, Provider<RealTimeTextEnablementAuthority> provider6) {
        return (CommandProcessor) Preconditions.checkNotNull(sipModule.providesCommandProcessor(provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get(), provider4.mo10268get(), provider5.mo10268get(), provider6.mo10268get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static CommandProcessor proxyProvidesCommandProcessor(SipModule sipModule, DeviceCallingService deviceCallingService, EventTracerFactory eventTracerFactory, SipClientState sipClientState, CallManager callManager, CapabilitiesManager capabilitiesManager, RealTimeTextEnablementAuthority realTimeTextEnablementAuthority) {
        return (CommandProcessor) Preconditions.checkNotNull(sipModule.providesCommandProcessor(deviceCallingService, eventTracerFactory, sipClientState, callManager, capabilitiesManager, realTimeTextEnablementAuthority), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public CommandProcessor mo10268get() {
        return provideInstance(this.module, this.deviceCallingServiceProvider, this.eventTracerFactoryProvider, this.sipClientStateProvider, this.callManagerProvider, this.capabilitiesManagerProvider, this.realTimeTextEnablementAuthorityProvider);
    }
}
