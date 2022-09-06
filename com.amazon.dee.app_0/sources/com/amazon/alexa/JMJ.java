package com.amazon.alexa;

import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import javax.inject.Provider;
/* compiled from: VisualChannelStatePersister_Factory.java */
/* loaded from: classes.dex */
public final class JMJ implements Factory<Mpk> {
    public final Provider<uTP> zZm;

    public JMJ(Provider<uTP> provider) {
        this.zZm = provider;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return new Mpk(DoubleCheck.lazy(this.zZm));
    }
}
