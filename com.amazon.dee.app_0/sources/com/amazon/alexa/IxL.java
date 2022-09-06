package com.amazon.alexa;

import android.content.pm.PackageManager;
import dagger.internal.Factory;
import javax.inject.Provider;
/* compiled from: DeviceIOComponentsStateProviderFactory_Factory.java */
/* loaded from: classes.dex */
public final class IxL implements Factory<Efr> {
    public final Provider<PackageManager> BIo;
    public final Provider<String> zZm;

    public IxL(Provider<String> provider, Provider<PackageManager> provider2) {
        this.zZm = provider;
        this.BIo = provider2;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return new Efr(this.zZm.mo10268get(), this.BIo.mo10268get());
    }
}
