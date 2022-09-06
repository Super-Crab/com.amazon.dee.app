package com.amazon.alexa;

import android.content.Context;
import android.content.pm.PackageManager;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* compiled from: ContextModule_ProvidesPackageManagerFactory.java */
/* loaded from: classes.dex */
public final class anq implements Factory<PackageManager> {
    public final Provider<Context> BIo;
    public final dyd zZm;

    public anq(dyd dydVar, Provider<Context> provider) {
        this.zZm = dydVar;
        this.BIo = provider;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return (PackageManager) Preconditions.checkNotNull(this.zZm.zzR(this.BIo.mo10268get()), "Cannot return null from a non-@Nullable @Provides method");
    }
}
