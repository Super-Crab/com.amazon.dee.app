package com.amazon.alexa;

import dagger.internal.Factory;
import javax.inject.Provider;
/* compiled from: AudioAttributesProvider_Factory.java */
/* loaded from: classes.dex */
public final class TIZ implements Factory<ZIZ> {
    public final Provider<vVi> zZm;

    public TIZ(Provider<vVi> provider) {
        this.zZm = provider;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return new ZIZ(this.zZm.mo10268get());
    }
}
