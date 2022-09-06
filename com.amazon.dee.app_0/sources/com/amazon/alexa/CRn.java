package com.amazon.alexa;

import com.google.gson.Gson;
import dagger.internal.Factory;
import javax.inject.Provider;
/* compiled from: InitiatorAuthority_Factory.java */
/* loaded from: classes.dex */
public final class CRn implements Factory<njf> {
    public final Provider<Gson> zZm;

    public CRn(Provider<Gson> provider) {
        this.zZm = provider;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return new njf(this.zZm.mo10268get());
    }
}
