package com.amazon.alexa;

import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import com.amazon.alexa.wakeword.WakeWordArbitration;
import dagger.internal.Factory;
import javax.inject.Provider;
/* compiled from: WakeWordVerifier_Factory.java */
/* loaded from: classes.dex */
public final class EXc implements Factory<BOa> {
    public final Provider<shl> BIo;
    public final Provider<uqp> jiA;
    public final Provider<WakeWordArbitration> zQM;
    public final Provider<AlexaClientEventBus> zZm;
    public final Provider<Wyh> zyO;

    public EXc(Provider<AlexaClientEventBus> provider, Provider<shl> provider2, Provider<WakeWordArbitration> provider3, Provider<Wyh> provider4, Provider<uqp> provider5) {
        this.zZm = provider;
        this.BIo = provider2;
        this.zQM = provider3;
        this.zyO = provider4;
        this.jiA = provider5;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return new BOa(this.zZm.mo10268get(), this.BIo.mo10268get(), this.zQM.mo10268get(), this.zyO.mo10268get(), this.jiA.mo10268get());
    }
}
