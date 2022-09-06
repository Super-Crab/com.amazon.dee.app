package com.amazon.alexa;

import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import com.amazon.alexa.utils.TimeProvider;
import com.google.gson.Gson;
import dagger.internal.Factory;
import javax.inject.Provider;
/* compiled from: AlexaLauncherCapabilityAgent_Factory.java */
/* loaded from: classes.dex */
public final class MUV implements Factory<tkT> {
    public final Provider<Msx> BIo;
    public final Provider<vrF> JTe;
    public final Provider<TimeProvider> Qle;
    public final Provider<Gson> jiA;
    public final Provider<AlexaClientEventBus> zQM;
    public final Provider<vYS> zZm;
    public final Provider<SCj> zyO;

    public MUV(Provider<vYS> provider, Provider<Msx> provider2, Provider<AlexaClientEventBus> provider3, Provider<SCj> provider4, Provider<Gson> provider5, Provider<TimeProvider> provider6, Provider<vrF> provider7) {
        this.zZm = provider;
        this.BIo = provider2;
        this.zQM = provider3;
        this.zyO = provider4;
        this.jiA = provider5;
        this.Qle = provider6;
        this.JTe = provider7;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return new tkT(this.zZm.mo10268get(), this.BIo.mo10268get(), this.zQM.mo10268get(), this.zyO.mo10268get(), this.jiA.mo10268get(), this.Qle.mo10268get(), this.JTe.mo10268get());
    }
}
