package com.amazon.alexa;

import com.amazon.alexa.client.metrics.core.MetricsStatusProvider;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* compiled from: MetricsModule_ProvidesMobilyticsMetricsStatusProviderFactory.java */
/* renamed from: com.amazon.alexa.dEI  reason: case insensitive filesystem */
/* loaded from: classes.dex */
public final class C0194dEI implements Factory<MetricsStatusProvider> {
    public final Provider<gSO> BIo;
    public final kbj zZm;

    public C0194dEI(kbj kbjVar, Provider<gSO> provider) {
        this.zZm = kbjVar;
        this.BIo = provider;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return (MetricsStatusProvider) Preconditions.checkNotNull(this.zZm.zQM(DoubleCheck.lazy(this.BIo)), "Cannot return null from a non-@Nullable @Provides method");
    }
}
