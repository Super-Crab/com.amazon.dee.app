package com.amazon.alexa;

import dagger.internal.Factory;
import javax.inject.Provider;
/* compiled from: TrustedStatesComponentStateProvider_Factory.java */
/* loaded from: classes.dex */
public final class sVQ implements Factory<AYd> {
    public final Provider<VIE> zZm;

    public sVQ(Provider<VIE> provider) {
        this.zZm = provider;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return new AYd(this.zZm.mo10268get());
    }
}
