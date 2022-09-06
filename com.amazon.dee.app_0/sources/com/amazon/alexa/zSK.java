package com.amazon.alexa;

import dagger.internal.Factory;
import javax.inject.Provider;
/* compiled from: EnsureInitializationChain_Factory.java */
/* loaded from: classes.dex */
public final class zSK implements Factory<pzz> {
    public final Provider<WnL> BIo;
    public final Provider<yDI> zZm;

    public zSK(Provider<yDI> provider, Provider<WnL> provider2) {
        this.zZm = provider;
        this.BIo = provider2;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return new pzz(this.zZm.mo10268get(), this.BIo.mo10268get());
    }
}
