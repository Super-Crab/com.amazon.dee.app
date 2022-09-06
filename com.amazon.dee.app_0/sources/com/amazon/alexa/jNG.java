package com.amazon.alexa;

import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import javax.inject.Provider;
/* compiled from: PlayerRuntimeStatePersister_Factory.java */
/* loaded from: classes.dex */
public final class jNG implements Factory<qKe> {
    public final Provider<uTP> zZm;

    public jNG(Provider<uTP> provider) {
        this.zZm = provider;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return new qKe(DoubleCheck.lazy(this.zZm));
    }
}
