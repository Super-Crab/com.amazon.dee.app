package com.amazon.alexa;

import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import dagger.internal.Factory;
import javax.inject.Provider;
/* compiled from: SpeechSynthesizerCapabilityAgent_Factory.java */
/* loaded from: classes.dex */
public final class Nom implements Factory<fjm> {
    public final Provider<AlexaClientEventBus> BIo;
    public final Provider<jSO> JTe;
    public final Provider<Wyh> LPk;
    public final Provider<QeM> Qle;
    public final Provider<vkx> jiA;
    public final Provider<iHK> zQM;
    public final Provider<dDK> zZm;
    public final Provider<yqV> zyO;

    public Nom(Provider<dDK> provider, Provider<AlexaClientEventBus> provider2, Provider<iHK> provider3, Provider<yqV> provider4, Provider<vkx> provider5, Provider<QeM> provider6, Provider<jSO> provider7, Provider<Wyh> provider8) {
        this.zZm = provider;
        this.BIo = provider2;
        this.zQM = provider3;
        this.zyO = provider4;
        this.jiA = provider5;
        this.Qle = provider6;
        this.JTe = provider7;
        this.LPk = provider8;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return new fjm(this.zZm.mo10268get(), this.BIo.mo10268get(), this.zQM.mo10268get(), this.zyO.mo10268get(), this.jiA.mo10268get(), this.Qle.mo10268get(), this.JTe.mo10268get(), this.LPk.mo10268get());
    }
}
