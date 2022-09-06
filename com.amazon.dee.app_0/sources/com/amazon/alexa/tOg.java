package com.amazon.alexa;

import com.google.gson.Gson;
import dagger.internal.Factory;
import javax.inject.Provider;
/* compiled from: TrustedStatesStateMerger_Factory.java */
/* loaded from: classes.dex */
public final class tOg implements Factory<ZCC> {
    public final Provider<Gson> zZm;

    public tOg(Provider<Gson> provider) {
        this.zZm = provider;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return new ZCC(this.zZm.mo10268get());
    }
}
