package com.amazon.alexa;

import dagger.internal.Factory;
import javax.inject.Provider;
/* compiled from: IOComponentsComponentStateProvider_Factory.java */
/* loaded from: classes.dex */
public final class YRk implements Factory<pBR> {
    public final Provider<PRf> zZm;

    public YRk(Provider<PRf> provider) {
        this.zZm = provider;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return new pBR(this.zZm.mo10268get());
    }
}
