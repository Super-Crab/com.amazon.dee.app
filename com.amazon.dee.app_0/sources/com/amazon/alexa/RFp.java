package com.amazon.alexa;

import com.amazon.alexa.client.core.device.PersistentStorage;
import com.google.gson.Gson;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import javax.inject.Provider;
/* compiled from: AlexaServiceSettingsStore_Factory.java */
/* loaded from: classes.dex */
public final class RFp implements Factory<Box> {
    public final Provider<Gson> BIo;
    public final Provider<PersistentStorage> zZm;

    public RFp(Provider<PersistentStorage> provider, Provider<Gson> provider2) {
        this.zZm = provider;
        this.BIo = provider2;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return new Box(DoubleCheck.lazy(this.zZm), DoubleCheck.lazy(this.BIo));
    }
}
