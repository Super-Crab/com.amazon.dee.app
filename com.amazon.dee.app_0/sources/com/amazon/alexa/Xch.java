package com.amazon.alexa;

import android.content.pm.PackageManager;
import com.google.gson.Gson;
import dagger.internal.Factory;
import javax.inject.Provider;
/* compiled from: ManifestExtractor_Factory.java */
/* loaded from: classes.dex */
public final class Xch implements Factory<Qva> {
    public final Provider<Gson> BIo;
    public final Provider<PackageManager> zZm;

    public Xch(Provider<PackageManager> provider, Provider<Gson> provider2) {
        this.zZm = provider;
        this.BIo = provider2;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return new Qva(this.zZm.mo10268get(), this.BIo.mo10268get());
    }
}
