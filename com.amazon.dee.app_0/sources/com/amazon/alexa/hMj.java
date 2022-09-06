package com.amazon.alexa;

import com.google.gson.Gson;
import dagger.internal.Factory;
import javax.inject.Provider;
/* compiled from: SqliteGuaranteedDeliveryAlexaEventDao_Factory.java */
/* loaded from: classes.dex */
public final class hMj implements Factory<VBC> {
    public final Provider<Gson> BIo;
    public final Provider<ptB> zZm;

    public hMj(Provider<ptB> provider, Provider<Gson> provider2) {
        this.zZm = provider;
        this.BIo = provider2;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return new VBC(this.zZm.mo10268get(), this.BIo.mo10268get());
    }
}
