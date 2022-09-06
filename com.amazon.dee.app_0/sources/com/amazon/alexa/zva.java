package com.amazon.alexa;

import dagger.internal.Factory;
import javax.inject.Provider;
/* compiled from: UpdateVolumeFromSystemCallable_Factory.java */
/* loaded from: classes.dex */
public final class zva implements Factory<Kfo> {
    public final Provider<QMz> zZm;

    public zva(Provider<QMz> provider) {
        this.zZm = provider;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return new Kfo(this.zZm.mo10268get());
    }
}
