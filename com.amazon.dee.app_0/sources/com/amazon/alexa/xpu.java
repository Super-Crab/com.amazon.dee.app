package com.amazon.alexa;

import com.google.gson.Gson;
import dagger.internal.Factory;
import javax.inject.Provider;
/* compiled from: IOComponentsStateMerger_Factory.java */
/* loaded from: classes.dex */
public final class xpu implements Factory<ClS> {
    public final Provider<Gson> zZm;

    public xpu(Provider<Gson> provider) {
        this.zZm = provider;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return new ClS(this.zZm.mo10268get());
    }
}
