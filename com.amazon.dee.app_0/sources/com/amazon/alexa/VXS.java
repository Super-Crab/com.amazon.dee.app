package com.amazon.alexa;

import dagger.internal.Factory;
import javax.inject.Provider;
/* compiled from: TextAvailabilityProvider_Factory.java */
/* loaded from: classes.dex */
public final class VXS implements Factory<FQn> {
    public final Provider<MBE> BIo;
    public final Provider<gSO> zZm;

    public VXS(Provider<gSO> provider, Provider<MBE> provider2) {
        this.zZm = provider;
        this.BIo = provider2;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return new FQn(this.zZm.mo10268get(), this.BIo.mo10268get());
    }
}
