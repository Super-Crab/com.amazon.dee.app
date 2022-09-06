package com.amazon.alexa;

import com.amazon.alexa.utils.security.SignatureVerifier;
import dagger.internal.Factory;
import javax.inject.Provider;
/* compiled from: MessageReceiverAuthority_Factory.java */
/* loaded from: classes.dex */
public final class amn implements Factory<KvZ> {
    public final Provider<UYN> BIo;
    public final Provider<SignatureVerifier> zZm;

    public amn(Provider<SignatureVerifier> provider, Provider<UYN> provider2) {
        this.zZm = provider;
        this.BIo = provider2;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return new KvZ(this.zZm.mo10268get(), this.BIo.mo10268get());
    }
}
