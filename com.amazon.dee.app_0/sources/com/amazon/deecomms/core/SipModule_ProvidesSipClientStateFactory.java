package com.amazon.deecomms.core;

import com.amazon.deecomms.common.sip.SipClientState;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes12.dex */
public final class SipModule_ProvidesSipClientStateFactory implements Factory<SipClientState> {
    private final SipModule module;

    public SipModule_ProvidesSipClientStateFactory(SipModule sipModule) {
        this.module = sipModule;
    }

    public static SipModule_ProvidesSipClientStateFactory create(SipModule sipModule) {
        return new SipModule_ProvidesSipClientStateFactory(sipModule);
    }

    public static SipClientState provideInstance(SipModule sipModule) {
        return (SipClientState) Preconditions.checkNotNull(sipModule.providesSipClientState(), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static SipClientState proxyProvidesSipClientState(SipModule sipModule) {
        return (SipClientState) Preconditions.checkNotNull(sipModule.providesSipClientState(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public SipClientState mo10268get() {
        return provideInstance(this.module);
    }
}
