package com.amazon.alexa;

import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import javax.inject.Provider;
/* compiled from: PingCall_Factory.java */
/* loaded from: classes.dex */
public final class iwG implements Factory<RZN> {
    public final Provider<dAN> BIo;
    public final Provider<xUA> zQM;
    public final Provider<qxC> zZm;

    public iwG(Provider<qxC> provider, Provider<dAN> provider2, Provider<xUA> provider3) {
        this.zZm = provider;
        this.BIo = provider2;
        this.zQM = provider3;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return new RZN(this.zZm.mo10268get(), this.BIo.mo10268get(), DoubleCheck.lazy(this.zQM));
    }
}
