package com.amazon.alexa;

import com.amazon.alexa.auth.TokenProvider;
import dagger.internal.Factory;
import javax.inject.Provider;
/* compiled from: AuthorizationInterceptor_Factory.java */
/* loaded from: classes.dex */
public final class ELH implements Factory<FqH> {
    public final Provider<TokenProvider> zZm;

    public ELH(Provider<TokenProvider> provider) {
        this.zZm = provider;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return new FqH(this.zZm.mo10268get());
    }
}
