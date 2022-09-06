package com.amazon.alexa;

import com.google.gson.Gson;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* compiled from: NetworkingModule_ProvidesClientMetricsDaoFactory.java */
/* loaded from: classes.dex */
public final class PBX implements Factory<lWz> {
    public final Provider<Gson> BIo;
    public final Provider<ZVz> zZm;

    public PBX(Provider<ZVz> provider, Provider<Gson> provider2) {
        this.zZm = provider;
        this.BIo = provider2;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return (lWz) Preconditions.checkNotNull(new wdQ(this.zZm.mo10268get(), this.BIo.mo10268get()), "Cannot return null from a non-@Nullable @Provides method");
    }
}
