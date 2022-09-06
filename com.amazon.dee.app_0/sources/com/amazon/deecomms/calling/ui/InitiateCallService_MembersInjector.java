package com.amazon.deecomms.calling.ui;

import com.amazon.deecomms.api.CommsIdentityManager;
import com.amazon.deecomms.common.sip.SipClientState;
import com.amazon.deecomms.core.CapabilitiesManager;
import dagger.MembersInjector;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class InitiateCallService_MembersInjector implements MembersInjector<InitiateCallService> {
    private final Provider<CapabilitiesManager> capabilitiesManagerProvider;
    private final Provider<CommsIdentityManager> commsIdentityManagerProvider;
    private final Provider<SipClientState> sipClientStateProvider;

    public InitiateCallService_MembersInjector(Provider<SipClientState> provider, Provider<CommsIdentityManager> provider2, Provider<CapabilitiesManager> provider3) {
        this.sipClientStateProvider = provider;
        this.commsIdentityManagerProvider = provider2;
        this.capabilitiesManagerProvider = provider3;
    }

    public static MembersInjector<InitiateCallService> create(Provider<SipClientState> provider, Provider<CommsIdentityManager> provider2, Provider<CapabilitiesManager> provider3) {
        return new InitiateCallService_MembersInjector(provider, provider2, provider3);
    }

    public static void injectCapabilitiesManager(InitiateCallService initiateCallService, CapabilitiesManager capabilitiesManager) {
        initiateCallService.capabilitiesManager = capabilitiesManager;
    }

    public static void injectCommsIdentityManager(InitiateCallService initiateCallService, CommsIdentityManager commsIdentityManager) {
        initiateCallService.commsIdentityManager = commsIdentityManager;
    }

    public static void injectSipClientState(InitiateCallService initiateCallService, SipClientState sipClientState) {
        initiateCallService.sipClientState = sipClientState;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(InitiateCallService initiateCallService) {
        initiateCallService.sipClientState = this.sipClientStateProvider.mo10268get();
        initiateCallService.commsIdentityManager = this.commsIdentityManagerProvider.mo10268get();
        initiateCallService.capabilitiesManager = this.capabilitiesManagerProvider.mo10268get();
    }
}
