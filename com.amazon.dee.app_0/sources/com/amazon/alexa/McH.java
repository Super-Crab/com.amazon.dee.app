package com.amazon.alexa;

import android.content.Context;
import com.amazon.alexa.utils.security.SignatureVerifier;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* compiled from: MessengerModule_ProvidesSignatureVerifierFactory.java */
/* loaded from: classes.dex */
public final class McH implements Factory<SignatureVerifier> {
    public final Provider<Context> BIo;
    public final C0178PyL zZm;

    public McH(C0178PyL c0178PyL, Provider<Context> provider) {
        this.zZm = c0178PyL;
        this.BIo = provider;
    }

    public static McH zZm(C0178PyL c0178PyL, Provider<Context> provider) {
        return new McH(c0178PyL, provider);
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return (SignatureVerifier) Preconditions.checkNotNull(this.zZm.zZm(this.BIo.mo10268get()), "Cannot return null from a non-@Nullable @Provides method");
    }
}
