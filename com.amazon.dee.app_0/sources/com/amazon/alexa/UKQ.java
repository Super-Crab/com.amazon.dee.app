package com.amazon.alexa;

import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import com.amazon.alexa.client.core.configuration.ClientConfiguration;
import com.amazon.alexa.client.crashreporting.CrashReporter;
import com.google.gson.Gson;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* compiled from: ReleaseNetworkingModule_ProvidesRequestComposerFactory.java */
/* loaded from: classes.dex */
public final class UKQ implements Factory<aew> {
    public final Provider<dAN> BIo;
    public final Provider<xUA> JTe;
    public final Provider<DYu> LPk;
    public final Provider<lSb> Mlj;
    public final Provider<qxC> Qle;
    public final Provider<ZPU> dMe;
    public final Provider<shl> jiA;
    public final Provider<WnL> lOf;
    public final Provider<tol> yPL;
    public final Provider<Gson> zQM;
    public final Provider<ClientConfiguration> zZm;
    public final Provider<AlexaClientEventBus> zyO;
    public final Provider<CrashReporter> zzR;

    public UKQ(Provider<ClientConfiguration> provider, Provider<dAN> provider2, Provider<Gson> provider3, Provider<AlexaClientEventBus> provider4, Provider<shl> provider5, Provider<qxC> provider6, Provider<xUA> provider7, Provider<DYu> provider8, Provider<tol> provider9, Provider<lSb> provider10, Provider<CrashReporter> provider11, Provider<WnL> provider12, Provider<ZPU> provider13) {
        this.zZm = provider;
        this.BIo = provider2;
        this.zQM = provider3;
        this.zyO = provider4;
        this.jiA = provider5;
        this.Qle = provider6;
        this.JTe = provider7;
        this.LPk = provider8;
        this.yPL = provider9;
        this.Mlj = provider10;
        this.zzR = provider11;
        this.lOf = provider12;
        this.dMe = provider13;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        Provider<ClientConfiguration> provider = this.zZm;
        Provider<dAN> provider2 = this.BIo;
        Provider<Gson> provider3 = this.zQM;
        Provider<AlexaClientEventBus> provider4 = this.zyO;
        Provider<shl> provider5 = this.jiA;
        Provider<qxC> provider6 = this.Qle;
        Provider<xUA> provider7 = this.JTe;
        Provider<DYu> provider8 = this.LPk;
        Provider<tol> provider9 = this.yPL;
        Provider<lSb> provider10 = this.Mlj;
        Provider<CrashReporter> provider11 = this.zzR;
        Provider<WnL> provider12 = this.lOf;
        Provider<ZPU> provider13 = this.dMe;
        return (aew) Preconditions.checkNotNull(new aew(DoubleCheck.lazy(provider), provider2.mo10268get(), provider3.mo10268get(), provider4.mo10268get(), provider5.mo10268get(), provider6.mo10268get(), DoubleCheck.lazy(provider7), provider8.mo10268get(), provider10.mo10268get(), provider9.mo10268get(), provider11.mo10268get(), provider12.mo10268get(), provider13.mo10268get()), "Cannot return null from a non-@Nullable @Provides method");
    }
}
