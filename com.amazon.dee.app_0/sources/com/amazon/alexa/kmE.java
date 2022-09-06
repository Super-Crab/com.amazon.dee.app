package com.amazon.alexa;

import dagger.internal.Factory;
import javax.inject.Provider;
/* compiled from: Downchannel_Factory.java */
/* loaded from: classes.dex */
public final class kmE implements Factory<fau> {
    public final Provider<jQK> zZm;

    public kmE(Provider<jQK> provider) {
        this.zZm = provider;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return new fau(this.zZm.mo10268get());
    }
}
