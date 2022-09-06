package com.amazon.alexa;

import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import com.amazon.alexa.client.core.configuration.ClientConfiguration;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import java.util.concurrent.ScheduledExecutorService;
import javax.inject.Provider;
/* compiled from: SpeechRecognizerCapabilityAgent_Factory.java */
/* loaded from: classes.dex */
public final class aOh implements Factory<LuX> {
    public final Provider<Wyh> BIo;
    public final Provider<gSO> HvC;
    public final Provider<vYS> JTe;
    public final Provider<peZ> LPk;
    public final Provider<pBR> Mlj;
    public final Provider<BOa> Qle;
    public final Provider<ClientConfiguration> dMe;
    public final Provider<njf> jiA;
    public final Provider<ScheduledExecutorService> lOf;
    public final Provider<vrF> uzr;
    public final Provider<shl> vkx;
    public final Provider<Msx> yPL;
    public final Provider<lhN> zQM;
    public final Provider<AlexaClientEventBus> zZm;
    public final Provider<vkx> zyO;
    public final Provider<yqV> zzR;

    public aOh(Provider<AlexaClientEventBus> provider, Provider<Wyh> provider2, Provider<lhN> provider3, Provider<vkx> provider4, Provider<njf> provider5, Provider<BOa> provider6, Provider<vYS> provider7, Provider<peZ> provider8, Provider<Msx> provider9, Provider<pBR> provider10, Provider<yqV> provider11, Provider<ScheduledExecutorService> provider12, Provider<ClientConfiguration> provider13, Provider<vrF> provider14, Provider<gSO> provider15, Provider<shl> provider16) {
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
        this.uzr = provider14;
        this.HvC = provider15;
        this.vkx = provider16;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return new LuX(this.zZm.mo10268get(), this.BIo.mo10268get(), this.zQM.mo10268get(), this.zyO.mo10268get(), this.jiA.mo10268get(), this.Qle.mo10268get(), this.JTe.mo10268get(), this.LPk.mo10268get(), this.yPL.mo10268get(), this.Mlj.mo10268get(), this.zzR.mo10268get(), this.lOf.mo10268get(), DoubleCheck.lazy(this.dMe), this.uzr.mo10268get(), this.HvC.mo10268get(), this.vkx.mo10268get());
    }
}
