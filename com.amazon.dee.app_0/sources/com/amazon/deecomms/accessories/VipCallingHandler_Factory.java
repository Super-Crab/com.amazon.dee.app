package com.amazon.deecomms.accessories;

import com.amazon.deecomms.alexa.AlexaInterface;
import com.amazon.deecomms.common.sip.SipClientState;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class VipCallingHandler_Factory implements Factory<VipCallingHandler> {
    private final Provider<AlexaInterface> alexaInterfaceProvider;
    private final Provider<SipClientState> sipClientStateProvider;

    public VipCallingHandler_Factory(Provider<AlexaInterface> provider, Provider<SipClientState> provider2) {
        this.alexaInterfaceProvider = provider;
        this.sipClientStateProvider = provider2;
    }

    public static VipCallingHandler_Factory create(Provider<AlexaInterface> provider, Provider<SipClientState> provider2) {
        return new VipCallingHandler_Factory(provider, provider2);
    }

    public static VipCallingHandler newVipCallingHandler(AlexaInterface alexaInterface, SipClientState sipClientState) {
        return new VipCallingHandler(alexaInterface, sipClientState);
    }

    public static VipCallingHandler provideInstance(Provider<AlexaInterface> provider, Provider<SipClientState> provider2) {
        return new VipCallingHandler(provider.mo10268get(), provider2.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public VipCallingHandler mo10268get() {
        return provideInstance(this.alexaInterfaceProvider, this.sipClientStateProvider);
    }
}
