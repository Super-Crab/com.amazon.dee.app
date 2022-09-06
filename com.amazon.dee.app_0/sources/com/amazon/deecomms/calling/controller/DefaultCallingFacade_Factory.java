package com.amazon.deecomms.calling.controller;

import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class DefaultCallingFacade_Factory implements Factory<DefaultCallingFacade> {
    private final Provider<CallInitiationOrchestrator> callInitiationOrchestratorProvider;

    public DefaultCallingFacade_Factory(Provider<CallInitiationOrchestrator> provider) {
        this.callInitiationOrchestratorProvider = provider;
    }

    public static DefaultCallingFacade_Factory create(Provider<CallInitiationOrchestrator> provider) {
        return new DefaultCallingFacade_Factory(provider);
    }

    public static DefaultCallingFacade newDefaultCallingFacade(CallInitiationOrchestrator callInitiationOrchestrator) {
        return new DefaultCallingFacade(callInitiationOrchestrator);
    }

    public static DefaultCallingFacade provideInstance(Provider<CallInitiationOrchestrator> provider) {
        return new DefaultCallingFacade(provider.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public DefaultCallingFacade mo10268get() {
        return provideInstance(this.callInitiationOrchestratorProvider);
    }
}
