package com.amazon.alexa;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* compiled from: ReleaseNetworkingModule_ProvidesSynchronizeStateChainFactory.java */
/* loaded from: classes.dex */
public final class bCa implements Factory<yDI> {
    public final Provider<EdC> zZm;

    public bCa(Provider<EdC> provider) {
        this.zZm = provider;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return (yDI) Preconditions.checkNotNull(this.zZm.mo10268get(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
