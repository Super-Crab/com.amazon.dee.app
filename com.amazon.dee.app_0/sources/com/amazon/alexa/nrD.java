package com.amazon.alexa;

import android.content.Context;
import android.net.wifi.WifiManager;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* compiled from: ContextModule_ProvideWifiManagerFactory.java */
/* loaded from: classes.dex */
public final class nrD implements Factory<WifiManager> {
    public final Provider<Context> BIo;
    public final dyd zZm;

    public nrD(dyd dydVar, Provider<Context> provider) {
        this.zZm = dydVar;
        this.BIo = provider;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return (WifiManager) Preconditions.checkNotNull(this.zZm.jiA(this.BIo.mo10268get()), "Cannot return null from a non-@Nullable @Provides method");
    }
}
