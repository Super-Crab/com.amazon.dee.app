package com.amazon.alexa;

import dagger.internal.Factory;
import javax.inject.Provider;
/* compiled from: AttentionSystemAuthority_Factory.java */
/* loaded from: classes.dex */
public final class RqC implements Factory<hrT> {
    public final Provider<yGK> zZm;

    public RqC(Provider<yGK> provider) {
        this.zZm = provider;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return new hrT(this.zZm.mo10268get());
    }
}
