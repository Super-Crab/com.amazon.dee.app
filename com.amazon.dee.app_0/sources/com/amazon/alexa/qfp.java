package com.amazon.alexa;

import dagger.internal.Factory;
import javax.inject.Provider;
/* compiled from: AlexaDisplayWindowComponentStateProvider_Factory.java */
/* loaded from: classes.dex */
public final class qfp implements Factory<C0237wXy> {
    public final Provider<rqw> zZm;

    public qfp(Provider<rqw> provider) {
        this.zZm = provider;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return new C0237wXy(this.zZm.mo10268get());
    }
}
