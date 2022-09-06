package com.amazon.alexa;

import com.google.gson.Gson;
import dagger.internal.Factory;
import javax.inject.Provider;
/* compiled from: SqliteExternalCapabilitiesDao_Factory.java */
/* loaded from: classes.dex */
public final class acp implements Factory<wdQ> {
    public final Provider<Gson> BIo;
    public final Provider<ZVz> zZm;

    public acp(Provider<ZVz> provider, Provider<Gson> provider2) {
        this.zZm = provider;
        this.BIo = provider2;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return new wdQ(this.zZm.mo10268get(), this.BIo.mo10268get());
    }
}
