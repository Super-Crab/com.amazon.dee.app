package com.amazon.deecomms.core;

import com.amazon.deecomms.calling.controller.CallInitiationOrchestrator;
import com.amazon.deecomms.calling.controller.CallingFacade;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class SipModule_ProvidesCallingFacadeFactory implements Factory<CallingFacade> {
    private final Provider<CallInitiationOrchestrator> callInitiationOrchestratorProvider;
    private final SipModule module;

    public SipModule_ProvidesCallingFacadeFactory(SipModule sipModule, Provider<CallInitiationOrchestrator> provider) {
        this.module = sipModule;
        this.callInitiationOrchestratorProvider = provider;
    }

    public static SipModule_ProvidesCallingFacadeFactory create(SipModule sipModule, Provider<CallInitiationOrchestrator> provider) {
        return new SipModule_ProvidesCallingFacadeFactory(sipModule, provider);
    }

    public static CallingFacade provideInstance(SipModule sipModule, Provider<CallInitiationOrchestrator> provider) {
        return (CallingFacade) Preconditions.checkNotNull(sipModule.providesCallingFacade(provider.mo10268get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static CallingFacade proxyProvidesCallingFacade(SipModule sipModule, CallInitiationOrchestrator callInitiationOrchestrator) {
        return (CallingFacade) Preconditions.checkNotNull(sipModule.providesCallingFacade(callInitiationOrchestrator), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public CallingFacade mo10268get() {
        return provideInstance(this.module, this.callInitiationOrchestratorProvider);
    }
}
