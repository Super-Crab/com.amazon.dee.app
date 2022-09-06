package com.amazon.alexa;

import android.content.Context;
import android.content.pm.PackageManager;
import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import com.amazon.alexa.client.core.device.PersistentStorage;
import com.amazon.alexa.client.metrics.core.DeviceInformation;
import com.amazon.alexa.preload.attribution.PreloadAttributionManager;
import com.amazon.alexa.utils.TimeProvider;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* compiled from: MetricsModule_ProvideMetricsAuthorityFactory.java */
/* loaded from: classes.dex */
public final class pcR implements Factory<Fsz> {
    public final Provider<Context> BIo;
    public final Provider<KPv> HvC;
    public final Provider<TimeProvider> JTe;
    public final Provider<DeviceInformation> LPk;
    public final Provider<C0211jTe> Mlj;
    public final Provider<paF> Qle;
    public final Provider<PWS> dMe;
    public final Provider<PersistentStorage> jiA;
    public final Provider<PackageManager> lOf;
    public final Provider<Dtt> noQ;
    public final Provider<bwE> uzr;
    public final Provider<GQk> vkx;
    public final Provider<IUt> wDP;
    public final Provider<PreloadAttributionManager> yPL;
    public final Provider<AlexaClientEventBus> zQM;
    public final kbj zZm;
    public final Provider<qxC> zyO;
    public final Provider<Kcd> zzR;

    public pcR(kbj kbjVar, Provider<Context> provider, Provider<AlexaClientEventBus> provider2, Provider<qxC> provider3, Provider<PersistentStorage> provider4, Provider<paF> provider5, Provider<TimeProvider> provider6, Provider<DeviceInformation> provider7, Provider<PreloadAttributionManager> provider8, Provider<C0211jTe> provider9, Provider<Kcd> provider10, Provider<PackageManager> provider11, Provider<PWS> provider12, Provider<bwE> provider13, Provider<KPv> provider14, Provider<GQk> provider15, Provider<IUt> provider16, Provider<Dtt> provider17) {
        this.zZm = kbjVar;
        this.BIo = provider;
        this.zQM = provider2;
        this.zyO = provider3;
        this.jiA = provider4;
        this.Qle = provider5;
        this.JTe = provider6;
        this.LPk = provider7;
        this.yPL = provider8;
        this.Mlj = provider9;
        this.zzR = provider10;
        this.lOf = provider11;
        this.dMe = provider12;
        this.uzr = provider13;
        this.HvC = provider14;
        this.vkx = provider15;
        this.wDP = provider16;
        this.noQ = provider17;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return (Fsz) Preconditions.checkNotNull(this.zZm.zZm(this.BIo.mo10268get(), this.zQM.mo10268get(), this.zyO.mo10268get(), DoubleCheck.lazy(this.jiA), this.Qle.mo10268get(), this.JTe.mo10268get(), this.LPk.mo10268get(), this.yPL.mo10268get(), this.Mlj.mo10268get(), this.zzR.mo10268get(), this.lOf.mo10268get(), this.dMe.mo10268get(), this.uzr.mo10268get(), this.HvC.mo10268get(), this.vkx.mo10268get(), this.wDP.mo10268get(), this.noQ.mo10268get()), "Cannot return null from a non-@Nullable @Provides method");
    }
}
