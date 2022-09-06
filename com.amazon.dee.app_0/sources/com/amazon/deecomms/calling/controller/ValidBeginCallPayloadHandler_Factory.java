package com.amazon.deecomms.calling.controller;

import android.content.Context;
import com.amazon.deecomms.api.CommsIdentityManager;
import com.amazon.deecomms.calling.core.CallInitiationAuthority;
import com.amazon.deecomms.calling.model.CallContext;
import com.amazon.deecomms.common.sip.SipClientState;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class ValidBeginCallPayloadHandler_Factory implements Factory<ValidBeginCallPayloadHandler> {
    private final Provider<CallContext> callContextProvider;
    private final Provider<CallInitiationAuthority> callInitiationAuthorityProvider;
    private final Provider<CommsIdentityManager> commsIdentityManagerProvider;
    private final Provider<Context> contextProvider;
    private final Provider<SipClientState> sipClientStateProvider;

    public ValidBeginCallPayloadHandler_Factory(Provider<Context> provider, Provider<CommsIdentityManager> provider2, Provider<SipClientState> provider3, Provider<CallInitiationAuthority> provider4, Provider<CallContext> provider5) {
        this.contextProvider = provider;
        this.commsIdentityManagerProvider = provider2;
        this.sipClientStateProvider = provider3;
        this.callInitiationAuthorityProvider = provider4;
        this.callContextProvider = provider5;
    }

    public static ValidBeginCallPayloadHandler_Factory create(Provider<Context> provider, Provider<CommsIdentityManager> provider2, Provider<SipClientState> provider3, Provider<CallInitiationAuthority> provider4, Provider<CallContext> provider5) {
        return new ValidBeginCallPayloadHandler_Factory(provider, provider2, provider3, provider4, provider5);
    }

    public static ValidBeginCallPayloadHandler newValidBeginCallPayloadHandler(Context context, CommsIdentityManager commsIdentityManager, SipClientState sipClientState, CallInitiationAuthority callInitiationAuthority, CallContext callContext) {
        return new ValidBeginCallPayloadHandler(context, commsIdentityManager, sipClientState, callInitiationAuthority, callContext);
    }

    public static ValidBeginCallPayloadHandler provideInstance(Provider<Context> provider, Provider<CommsIdentityManager> provider2, Provider<SipClientState> provider3, Provider<CallInitiationAuthority> provider4, Provider<CallContext> provider5) {
        return new ValidBeginCallPayloadHandler(provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get(), provider4.mo10268get(), provider5.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public ValidBeginCallPayloadHandler mo10268get() {
        return provideInstance(this.contextProvider, this.commsIdentityManagerProvider, this.sipClientStateProvider, this.callInitiationAuthorityProvider, this.callContextProvider);
    }
}
