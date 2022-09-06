package com.amazon.deecomms.calling.ui;

import com.amazon.deecomms.api.CommsIdentityManager;
import com.amazon.deecomms.calling.controller.CallManager;
import com.amazon.deecomms.common.sip.SipClientState;
import com.amazon.deecomms.core.CapabilitiesManager;
import dagger.MembersInjector;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class CallPermissionActivity_MembersInjector implements MembersInjector<CallPermissionActivity> {
    private final Provider<CallManager> callManagerProvider;
    private final Provider<CapabilitiesManager> capabilitiesManagerProvider;
    private final Provider<CommsIdentityManager> commsIdentityManagerProvider;
    private final Provider<SipClientState> currentSipClientStateProvider;

    public CallPermissionActivity_MembersInjector(Provider<CommsIdentityManager> provider, Provider<CapabilitiesManager> provider2, Provider<CallManager> provider3, Provider<SipClientState> provider4) {
        this.commsIdentityManagerProvider = provider;
        this.capabilitiesManagerProvider = provider2;
        this.callManagerProvider = provider3;
        this.currentSipClientStateProvider = provider4;
    }

    public static MembersInjector<CallPermissionActivity> create(Provider<CommsIdentityManager> provider, Provider<CapabilitiesManager> provider2, Provider<CallManager> provider3, Provider<SipClientState> provider4) {
        return new CallPermissionActivity_MembersInjector(provider, provider2, provider3, provider4);
    }

    public static void injectCallManager(CallPermissionActivity callPermissionActivity, CallManager callManager) {
        callPermissionActivity.callManager = callManager;
    }

    public static void injectCapabilitiesManager(CallPermissionActivity callPermissionActivity, CapabilitiesManager capabilitiesManager) {
        callPermissionActivity.capabilitiesManager = capabilitiesManager;
    }

    public static void injectCommsIdentityManager(CallPermissionActivity callPermissionActivity, CommsIdentityManager commsIdentityManager) {
        callPermissionActivity.commsIdentityManager = commsIdentityManager;
    }

    public static void injectCurrentSipClientState(CallPermissionActivity callPermissionActivity, SipClientState sipClientState) {
        callPermissionActivity.currentSipClientState = sipClientState;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(CallPermissionActivity callPermissionActivity) {
        callPermissionActivity.commsIdentityManager = this.commsIdentityManagerProvider.mo10268get();
        callPermissionActivity.capabilitiesManager = this.capabilitiesManagerProvider.mo10268get();
        callPermissionActivity.callManager = this.callManagerProvider.mo10268get();
        callPermissionActivity.currentSipClientState = this.currentSipClientStateProvider.mo10268get();
    }
}
