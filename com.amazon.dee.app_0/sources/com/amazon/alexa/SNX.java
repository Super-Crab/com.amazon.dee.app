package com.amazon.alexa;

import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import javax.inject.Provider;
/* compiled from: PlayerStructurePersister_Factory.java */
/* loaded from: classes.dex */
public final class SNX implements Factory<aim> {
    public final Provider<uTP> zZm;

    public SNX(Provider<uTP> provider) {
        this.zZm = provider;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return new aim(DoubleCheck.lazy(this.zZm));
    }
}
