package com.amazon.alexa;

import dagger.internal.Factory;
import javax.inject.Provider;
/* compiled from: DefaultConfigurationLoader_Factory.java */
/* loaded from: classes.dex */
public final class qUR implements Factory<mOV> {
    public final Provider<jZN> zZm;

    public qUR(Provider<jZN> provider) {
        this.zZm = provider;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return new mOV(this.zZm.mo10268get());
    }
}
