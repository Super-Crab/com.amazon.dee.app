package com.amazon.deecomms.calling.controller;

import android.content.Context;
import com.amazon.deecomms.api.CommsIdentityManager;
import com.amazon.deecomms.calling.phonecallcontroller.PCCContextProvider;
import com.amazon.deecomms.common.sip.SipClientState;
import com.amazon.deecomms.core.CapabilitiesManager;
import dagger.MembersInjector;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class CallHelper_MembersInjector implements MembersInjector<CallHelper> {
    private final Provider<CallManager> callManagerProvider;
    private final Provider<CapabilitiesManager> capabilitiesManagerProvider;
    private final Provider<CommsIdentityManager> commsIdentityManagerProvider;
    private final Provider<Context> mContextProvider;
    private final Provider<PCCContextProvider> pccContextProvider;
    private final Provider<SipClientState> sipClientStateProvider;

    public CallHelper_MembersInjector(Provider<Context> provider, Provider<SipClientState> provider2, Provider<CallManager> provider3, Provider<CommsIdentityManager> provider4, Provider<PCCContextProvider> provider5, Provider<CapabilitiesManager> provider6) {
        this.mContextProvider = provider;
        this.sipClientStateProvider = provider2;
        this.callManagerProvider = provider3;
        this.commsIdentityManagerProvider = provider4;
        this.pccContextProvider = provider5;
        this.capabilitiesManagerProvider = provider6;
    }

    public static MembersInjector<CallHelper> create(Provider<Context> provider, Provider<SipClientState> provider2, Provider<CallManager> provider3, Provider<CommsIdentityManager> provider4, Provider<PCCContextProvider> provider5, Provider<CapabilitiesManager> provider6) {
        return new CallHelper_MembersInjector(provider, provider2, provider3, provider4, provider5, provider6);
    }

    public static void injectCallManager(CallHelper callHelper, CallManager callManager) {
        callHelper.callManager = callManager;
    }

    public static void injectCapabilitiesManager(CallHelper callHelper, CapabilitiesManager capabilitiesManager) {
        callHelper.capabilitiesManager = capabilitiesManager;
    }

    public static void injectCommsIdentityManager(CallHelper callHelper, CommsIdentityManager commsIdentityManager) {
        callHelper.commsIdentityManager = commsIdentityManager;
    }

    public static void injectMContext(CallHelper callHelper, Context context) {
        callHelper.mContext = context;
    }

    public static void injectPccContextProvider(CallHelper callHelper, PCCContextProvider pCCContextProvider) {
        callHelper.pccContextProvider = pCCContextProvider;
    }

    public static void injectSipClientState(CallHelper callHelper, SipClientState sipClientState) {
        callHelper.sipClientState = sipClientState;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(CallHelper callHelper) {
        callHelper.mContext = this.mContextProvider.mo10268get();
        callHelper.sipClientState = this.sipClientStateProvider.mo10268get();
        callHelper.callManager = this.callManagerProvider.mo10268get();
        callHelper.commsIdentityManager = this.commsIdentityManagerProvider.mo10268get();
        callHelper.pccContextProvider = this.pccContextProvider.mo10268get();
        callHelper.capabilitiesManager = this.capabilitiesManagerProvider.mo10268get();
    }
}
