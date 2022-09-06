package com.amazon.alexa;

import android.content.Context;
import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import dagger.internal.Factory;
import javax.inject.Provider;
/* compiled from: CapabilityAgentManager_Factory.java */
/* loaded from: classes.dex */
public final class Lpx implements Factory<bXh> {
    public final Provider<C0234ubm> BIo;
    public final Provider<Context> jiA;
    public final Provider<LrI> zQM;
    public final Provider<AlexaClientEventBus> zZm;
    public final Provider<VqX> zyO;

    public Lpx(Provider<AlexaClientEventBus> provider, Provider<C0234ubm> provider2, Provider<LrI> provider3, Provider<VqX> provider4, Provider<Context> provider5) {
        this.zZm = provider;
        this.BIo = provider2;
        this.zQM = provider3;
        this.zyO = provider4;
        this.jiA = provider5;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return new bXh(this.zZm.mo10268get(), this.BIo.mo10268get(), this.zQM.mo10268get(), this.zyO.mo10268get(), this.jiA.mo10268get());
    }
}
