package com.amazon.alexa;

import android.content.Context;
import android.content.pm.PackageManager;
import dagger.internal.Factory;
import javax.inject.Provider;
/* compiled from: ApplicationMetadata_Factory.java */
/* renamed from: com.amazon.alexa.hHO  reason: case insensitive filesystem */
/* loaded from: classes.dex */
public final class C0206hHO implements Factory<jZN> {
    public final Provider<PackageManager> BIo;
    public final Provider<Context> zZm;

    public C0206hHO(Provider<Context> provider, Provider<PackageManager> provider2) {
        this.zZm = provider;
        this.BIo = provider2;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return new jZN(this.zZm.mo10268get(), this.BIo.mo10268get());
    }
}
