package com.amazon.alexa;

import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import javax.inject.Provider;
/* compiled from: PlayerInFocusPersister_Factory.java */
/* loaded from: classes.dex */
public final class CGi implements Factory<XTJ> {
    public final Provider<uTP> zZm;

    public CGi(Provider<uTP> provider) {
        this.zZm = provider;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return new XTJ(DoubleCheck.lazy(this.zZm));
    }
}
