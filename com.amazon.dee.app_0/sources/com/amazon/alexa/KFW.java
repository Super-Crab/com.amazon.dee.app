package com.amazon.alexa;

import com.amazon.alexa.client.core.device.PersistentStorage;
import com.amazon.alexa.client.core.marketplace.MarketplaceAuthority;
import com.amazon.alexa.client.metrics.core.DeviceInformation;
import com.amazon.alexa.client.metrics.core.MetricsConnector;
import com.amazon.alexa.preload.attribution.PreloadAttributionManager;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* compiled from: MetricsModule_ProvideMetricsServiceFactory.java */
/* loaded from: classes.dex */
public final class KFW implements Factory<paF> {
    public final Provider<DeviceInformation> BIo;
    public final Provider<PreloadAttributionManager> JTe;
    public final Provider<MarketplaceAuthority> LPk;
    public final Provider<MetricsConnector> Qle;
    public final Provider<MetricsConnector> jiA;
    public final Provider<String> yPL;
    public final Provider<PersistentStorage> zQM;
    public final kbj zZm;
    public final Provider<MetricsConnector> zyO;

    public KFW(kbj kbjVar, Provider<DeviceInformation> provider, Provider<PersistentStorage> provider2, Provider<MetricsConnector> provider3, Provider<MetricsConnector> provider4, Provider<MetricsConnector> provider5, Provider<PreloadAttributionManager> provider6, Provider<MarketplaceAuthority> provider7, Provider<String> provider8) {
        this.zZm = kbjVar;
        this.BIo = provider;
        this.zQM = provider2;
        this.zyO = provider3;
        this.jiA = provider4;
        this.Qle = provider5;
        this.JTe = provider6;
        this.LPk = provider7;
        this.yPL = provider8;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return (paF) Preconditions.checkNotNull(this.zZm.zZm(this.BIo.mo10268get(), DoubleCheck.lazy(this.zQM), this.zyO.mo10268get(), this.jiA.mo10268get(), this.Qle.mo10268get(), DoubleCheck.lazy(this.JTe), this.LPk.mo10268get(), this.yPL.mo10268get()), "Cannot return null from a non-@Nullable @Provides method");
    }
}
