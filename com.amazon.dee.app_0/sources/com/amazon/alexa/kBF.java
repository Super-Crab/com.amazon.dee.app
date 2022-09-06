package com.amazon.alexa;

import android.content.Context;
import android.net.ConnectivityManager;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* compiled from: ContextModule_ProvidesConnectivityManagerFactory.java */
/* loaded from: classes.dex */
public final class kBF implements Factory<ConnectivityManager> {
    public final Provider<Context> BIo;
    public final dyd zZm;

    public kBF(dyd dydVar, Provider<Context> provider) {
        this.zZm = dydVar;
        this.BIo = provider;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return (ConnectivityManager) Preconditions.checkNotNull(this.zZm.yPL(this.BIo.mo10268get()), "Cannot return null from a non-@Nullable @Provides method");
    }
}
