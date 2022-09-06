package com.amazon.alexa;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* compiled from: ReleaseNetworkingModule_ProvidesEnsureInitializationChainFactory.java */
/* loaded from: classes.dex */
public final class KmO implements Factory<yDI> {
    public final Provider<pzz> zZm;

    public KmO(Provider<pzz> provider) {
        this.zZm = provider;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return (yDI) Preconditions.checkNotNull(this.zZm.mo10268get(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
