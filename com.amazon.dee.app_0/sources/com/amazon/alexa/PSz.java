package com.amazon.alexa;

import android.content.Context;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* compiled from: ClientMetricsModule_ProvidesClientMetricDatabaseFactory.java */
/* loaded from: classes.dex */
public final class PSz implements Factory<pOk> {
    public final Provider<Context> BIo;
    public final eAC zZm;

    public PSz(eAC eac, Provider<Context> provider) {
        this.zZm = eac;
        this.BIo = provider;
    }

    public static PSz zZm(eAC eac, Provider<Context> provider) {
        return new PSz(eac, provider);
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return (pOk) Preconditions.checkNotNull(this.zZm.zZm(this.BIo.mo10268get()), "Cannot return null from a non-@Nullable @Provides method");
    }
}
