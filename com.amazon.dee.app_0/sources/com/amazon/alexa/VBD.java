package com.amazon.alexa;

import android.content.Context;
import dagger.internal.Factory;
import javax.inject.Provider;
/* compiled from: AlexaEventDatabase_Factory.java */
/* loaded from: classes.dex */
public final class VBD implements Factory<ptB> {
    public final Provider<Context> zZm;

    public VBD(Provider<Context> provider) {
        this.zZm = provider;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return new ptB(this.zZm.mo10268get());
    }
}
