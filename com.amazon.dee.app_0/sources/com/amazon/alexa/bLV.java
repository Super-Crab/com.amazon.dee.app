package com.amazon.alexa;

import android.content.Context;
import dagger.internal.Factory;
import javax.inject.Provider;
/* compiled from: ExternalComponentStateDatabase_Factory.java */
/* loaded from: classes.dex */
public final class bLV implements Factory<Whr> {
    public final Provider<Context> zZm;

    public bLV(Provider<Context> provider) {
        this.zZm = provider;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return new Whr(this.zZm.mo10268get());
    }
}
