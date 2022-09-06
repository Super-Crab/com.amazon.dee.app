package com.amazon.deecomms.core;

import com.amazon.comms.calling.sipclient.SipHeaders;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class SipModule_ProvidesSipHeadersFactory implements Factory<SipHeaders> {
    private final Provider<String> appVersionProvider;
    private final SipModule module;

    public SipModule_ProvidesSipHeadersFactory(SipModule sipModule, Provider<String> provider) {
        this.module = sipModule;
        this.appVersionProvider = provider;
    }

    public static SipModule_ProvidesSipHeadersFactory create(SipModule sipModule, Provider<String> provider) {
        return new SipModule_ProvidesSipHeadersFactory(sipModule, provider);
    }

    public static SipHeaders provideInstance(SipModule sipModule, Provider<String> provider) {
        return (SipHeaders) Preconditions.checkNotNull(sipModule.providesSipHeaders(provider.mo10268get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static SipHeaders proxyProvidesSipHeaders(SipModule sipModule, String str) {
        return (SipHeaders) Preconditions.checkNotNull(sipModule.providesSipHeaders(str), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public SipHeaders mo10268get() {
        return provideInstance(this.module, this.appVersionProvider);
    }
}
