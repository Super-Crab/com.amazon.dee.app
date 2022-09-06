package com.amazon.deecomms.calling.initiation;

import com.amazon.deecomms.api.CommsIdentityManager;
import com.amazon.deecomms.calling.controller.CallingFacade;
import com.amazon.deecomms.core.CapabilitiesManager;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class InitiationLogicFactory_Factory implements Factory<InitiationLogicFactory> {
    private final Provider<CallingFacade> callingFacadeProvider;
    private final Provider<CapabilitiesManager> capabilitiesManagerProvider;
    private final Provider<CommsIdentityManager> commsIdentityManagerProvider;

    public InitiationLogicFactory_Factory(Provider<CapabilitiesManager> provider, Provider<CallingFacade> provider2, Provider<CommsIdentityManager> provider3) {
        this.capabilitiesManagerProvider = provider;
        this.callingFacadeProvider = provider2;
        this.commsIdentityManagerProvider = provider3;
    }

    public static InitiationLogicFactory_Factory create(Provider<CapabilitiesManager> provider, Provider<CallingFacade> provider2, Provider<CommsIdentityManager> provider3) {
        return new InitiationLogicFactory_Factory(provider, provider2, provider3);
    }

    public static InitiationLogicFactory newInitiationLogicFactory(CapabilitiesManager capabilitiesManager, CallingFacade callingFacade, CommsIdentityManager commsIdentityManager) {
        return new InitiationLogicFactory(capabilitiesManager, callingFacade, commsIdentityManager);
    }

    public static InitiationLogicFactory provideInstance(Provider<CapabilitiesManager> provider, Provider<CallingFacade> provider2, Provider<CommsIdentityManager> provider3) {
        return new InitiationLogicFactory(provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public InitiationLogicFactory mo10268get() {
        return provideInstance(this.capabilitiesManagerProvider, this.callingFacadeProvider, this.commsIdentityManagerProvider);
    }
}
