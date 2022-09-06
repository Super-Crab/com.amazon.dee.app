package com.amazon.alexa;

import com.amazon.alexa.client.core.device.PersistentStorage;
import com.amazon.alexa.utils.TimeProvider;
import com.google.gson.Gson;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import javax.inject.Provider;
/* compiled from: PersistedDataLoaderFactory_Factory.java */
/* loaded from: classes.dex */
public final class RcD implements Factory<UCV> {
    public final Provider<TimeProvider> BIo;
    public final Provider<Gson> zQM;
    public final Provider<PersistentStorage> zZm;

    public RcD(Provider<PersistentStorage> provider, Provider<TimeProvider> provider2, Provider<Gson> provider3) {
        this.zZm = provider;
        this.BIo = provider2;
        this.zQM = provider3;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return new UCV(DoubleCheck.lazy(this.zZm), this.BIo.mo10268get(), this.zQM.mo10268get());
    }
}
