package com.amazon.deecomms.core;

import com.amazon.deecomms.calling.controller.CallPayloadValidator;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes12.dex */
public final class SipModule_ProvidesCallPayloadValidatorFactory implements Factory<CallPayloadValidator> {
    private final SipModule module;

    public SipModule_ProvidesCallPayloadValidatorFactory(SipModule sipModule) {
        this.module = sipModule;
    }

    public static SipModule_ProvidesCallPayloadValidatorFactory create(SipModule sipModule) {
        return new SipModule_ProvidesCallPayloadValidatorFactory(sipModule);
    }

    public static CallPayloadValidator provideInstance(SipModule sipModule) {
        return (CallPayloadValidator) Preconditions.checkNotNull(sipModule.providesCallPayloadValidator(), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static CallPayloadValidator proxyProvidesCallPayloadValidator(SipModule sipModule) {
        return (CallPayloadValidator) Preconditions.checkNotNull(sipModule.providesCallPayloadValidator(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public CallPayloadValidator mo10268get() {
        return provideInstance(this.module);
    }
}
