package com.amazon.alexa;

import android.content.Context;
import android.content.pm.PackageManager;
import dagger.internal.Factory;
import javax.inject.Provider;
/* compiled from: TargetEvaluator_Factory.java */
/* loaded from: classes.dex */
public final class tXy implements Factory<SCj> {
    public final Provider<Dtt> BIo;
    public final Provider<Context> zQM;
    public final Provider<PackageManager> zZm;

    public tXy(Provider<PackageManager> provider, Provider<Dtt> provider2, Provider<Context> provider3) {
        this.zZm = provider;
        this.BIo = provider2;
        this.zQM = provider3;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return new SCj(this.zZm.mo10268get(), this.BIo.mo10268get(), this.zQM.mo10268get());
    }
}
