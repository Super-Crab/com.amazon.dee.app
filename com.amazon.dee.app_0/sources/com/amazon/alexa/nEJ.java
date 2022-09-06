package com.amazon.alexa;

import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import javax.inject.Provider;
/* compiled from: ChannelStatePersister_Factory.java */
/* loaded from: classes.dex */
public final class nEJ implements Factory<AzE> {
    public final Provider<uTP> zZm;

    public nEJ(Provider<uTP> provider) {
        this.zZm = provider;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return new AzE(DoubleCheck.lazy(this.zZm));
    }
}
