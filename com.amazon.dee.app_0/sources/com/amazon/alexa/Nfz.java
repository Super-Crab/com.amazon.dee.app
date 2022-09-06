package com.amazon.alexa;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* compiled from: ClientMetricsModule_ProvidesClientMetricsDaoFactory.java */
/* loaded from: classes.dex */
public final class Nfz implements Factory<IUt> {
    public final Provider<pOk> BIo;
    public final eAC zZm;

    public Nfz(eAC eac, Provider<pOk> provider) {
        this.zZm = eac;
        this.BIo = provider;
    }

    public static Nfz zZm(eAC eac, Provider<pOk> provider) {
        return new Nfz(eac, provider);
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return (IUt) Preconditions.checkNotNull(this.zZm.zZm(this.BIo.mo10268get()), "Cannot return null from a non-@Nullable @Provides method");
    }
}
