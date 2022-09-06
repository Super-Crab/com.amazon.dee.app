package com.amazon.alexa;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* compiled from: ReleaseNetworkingModule_ProvidesInternetConnectivityVerifierFactory.java */
/* loaded from: classes.dex */
public final class FaQ implements Factory<Szi> {
    public static final FaQ zZm = new FaQ();

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return (Szi) Preconditions.checkNotNull(new Szi(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
