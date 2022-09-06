package com.amazon.alexa;

import dagger.internal.Factory;
import javax.inject.Provider;
/* compiled from: SoundAuthority_Factory.java */
/* loaded from: classes.dex */
public final class Vxu implements Factory<jcJ> {
    public final Provider<dcs> BIo;
    public final Provider<dcs> zZm;

    public Vxu(Provider<dcs> provider, Provider<dcs> provider2) {
        this.zZm = provider;
        this.BIo = provider2;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return new jcJ(this.zZm.mo10268get(), this.BIo.mo10268get());
    }
}
