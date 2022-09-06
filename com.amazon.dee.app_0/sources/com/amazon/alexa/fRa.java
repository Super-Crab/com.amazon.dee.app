package com.amazon.alexa;

import com.google.gson.Gson;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* compiled from: ReleaseComponentStateModule_ProvidesConnectedAccessoriesStatusProviderFactory.java */
/* loaded from: classes.dex */
public final class fRa implements Factory<C0245zoo> {
    public final Provider<Gson> BIo;
    public final jDY zZm;

    public fRa(jDY jdy, Provider<Gson> provider) {
        this.zZm = jdy;
        this.BIo = provider;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return (C0245zoo) Preconditions.checkNotNull(this.zZm.zZm(this.BIo.mo10268get()), "Cannot return null from a non-@Nullable @Provides method");
    }
}
