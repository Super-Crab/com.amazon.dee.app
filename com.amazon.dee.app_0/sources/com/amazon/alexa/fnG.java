package com.amazon.alexa;

import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import dagger.internal.Factory;
import java.util.concurrent.ScheduledExecutorService;
import javax.inject.Provider;
/* compiled from: TextCapabilityAgent_Factory.java */
/* loaded from: classes.dex */
public final class fnG implements Factory<Jvr> {
    public final Provider<Wyh> BIo;
    public final Provider<tAW> JTe;
    public final Provider<yjS> Qle;
    public final Provider<ScheduledExecutorService> jiA;
    public final Provider<vkx> zQM;
    public final Provider<AlexaClientEventBus> zZm;
    public final Provider<lhN> zyO;

    public fnG(Provider<AlexaClientEventBus> provider, Provider<Wyh> provider2, Provider<vkx> provider3, Provider<lhN> provider4, Provider<ScheduledExecutorService> provider5, Provider<yjS> provider6, Provider<tAW> provider7) {
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
        return new Jvr(this.zZm.mo10268get(), this.BIo.mo10268get(), this.zQM.mo10268get(), this.zyO.mo10268get(), this.jiA.mo10268get(), this.Qle.mo10268get(), this.JTe.mo10268get());
    }
}
