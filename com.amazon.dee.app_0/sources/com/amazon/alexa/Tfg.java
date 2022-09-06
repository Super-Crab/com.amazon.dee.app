package com.amazon.alexa;

import com.amazon.alexa.client.metrics.core.MetricsStatusProvider;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* compiled from: MetricsModule_ProvidesKinesisMetricsConnectorFactory.java */
/* loaded from: classes.dex */
public final class Tfg implements Factory<MetricsStatusProvider> {
    public final Provider<gSO> BIo;
    public final kbj zZm;

    public Tfg(kbj kbjVar, Provider<gSO> provider) {
        this.zZm = kbjVar;
        this.BIo = provider;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return (MetricsStatusProvider) Preconditions.checkNotNull(this.zZm.BIo(DoubleCheck.lazy(this.BIo)), "Cannot return null from a non-@Nullable @Provides method");
    }
}
