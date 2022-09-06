package com.amazon.alexa;

import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import javax.inject.Provider;
/* compiled from: PlayerStatePersister_Factory.java */
/* loaded from: classes.dex */
public final class ZSc implements Factory<OWw> {
    public final Provider<uTP> zZm;

    public ZSc(Provider<uTP> provider) {
        this.zZm = provider;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return new OWw(DoubleCheck.lazy(this.zZm));
    }
}
