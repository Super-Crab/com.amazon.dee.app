package com.amazon.alexa;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* compiled from: ReleaseNetworkingModule_ProvidesRefreshInternalCapabilitiesChainFactory.java */
/* loaded from: classes.dex */
public final class thd implements Factory<yDI> {
    public final Provider<RBt> zZm;

    public thd(Provider<RBt> provider) {
        this.zZm = provider;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return (yDI) Preconditions.checkNotNull(this.zZm.mo10268get(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
