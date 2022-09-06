package com.amazon.alexa;

import android.content.Context;
import com.amazon.alexa.client.core.configuration.ClientConfiguration;
import com.amazon.alexa.client.metrics.core.DeviceInformation;
import com.amazon.client.metrics.common.MetricsFactory;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* compiled from: MetricsModule_ProvideMetricsFactoryFactory.java */
/* loaded from: classes.dex */
public final class keg implements Factory<MetricsFactory> {
    public final Provider<Context> BIo;
    public final Provider<DeviceInformation> zQM;
    public final kbj zZm;
    public final Provider<ClientConfiguration> zyO;

    public keg(kbj kbjVar, Provider<Context> provider, Provider<DeviceInformation> provider2, Provider<ClientConfiguration> provider3) {
        this.zZm = kbjVar;
        this.BIo = provider;
        this.zQM = provider2;
        this.zyO = provider3;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return (MetricsFactory) Preconditions.checkNotNull(this.zZm.zZm(this.BIo.mo10268get(), this.zQM.mo10268get(), DoubleCheck.lazy(this.zyO)), "Cannot return null from a non-@Nullable @Provides method");
    }
}
