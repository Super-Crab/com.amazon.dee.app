package com.amazon.alexa;

import android.content.Context;
import android.location.LocationManager;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* compiled from: ContextModule_ProvidesLocationManagerFactory.java */
/* loaded from: classes.dex */
public final class gsX implements Factory<LocationManager> {
    public final Provider<Context> BIo;
    public final dyd zZm;

    public gsX(dyd dydVar, Provider<Context> provider) {
        this.zZm = dydVar;
        this.BIo = provider;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return (LocationManager) Preconditions.checkNotNull(this.zZm.Mlj(this.BIo.mo10268get()), "Cannot return null from a non-@Nullable @Provides method");
    }
}
