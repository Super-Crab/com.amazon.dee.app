package com.amazon.alexa;

import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import javax.inject.Provider;
/* compiled from: MediaPlayersAuthority_Factory.java */
/* renamed from: com.amazon.alexa.CnO  reason: case insensitive filesystem */
/* loaded from: classes.dex */
public final class C0167CnO implements Factory<zmg> {
    public final Provider<Blk> BIo;
    public final Provider<XTJ> JTe;
    public final Provider<Boy> LPk;
    public final Provider<FQX> Mlj;
    public final Provider<aim> Qle;
    public final Provider<vkx> dMe;
    public final Provider<OWw> jiA;
    public final Provider<qKe> lOf;
    public final Provider<gSO> uzr;
    public final Provider<HTC> yPL;
    public final Provider<OQS> zQM;
    public final Provider<AlexaClientEventBus> zZm;
    public final Provider<yWS> zyO;
    public final Provider<C0245zoo> zzR;

    public C0167CnO(Provider<AlexaClientEventBus> provider, Provider<Blk> provider2, Provider<OQS> provider3, Provider<yWS> provider4, Provider<OWw> provider5, Provider<aim> provider6, Provider<XTJ> provider7, Provider<Boy> provider8, Provider<HTC> provider9, Provider<FQX> provider10, Provider<C0245zoo> provider11, Provider<qKe> provider12, Provider<vkx> provider13, Provider<gSO> provider14) {
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
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return new zmg(this.zZm.mo10268get(), this.BIo.mo10268get(), this.zQM.mo10268get(), this.zyO.mo10268get(), this.jiA.mo10268get(), this.Qle.mo10268get(), this.JTe.mo10268get(), DoubleCheck.lazy(this.LPk), DoubleCheck.lazy(this.yPL), DoubleCheck.lazy(this.Mlj), DoubleCheck.lazy(this.zzR), this.lOf.mo10268get(), this.dMe.mo10268get(), this.uzr.mo10268get());
    }
}
