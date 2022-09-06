package com.amazon.alexa;

import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import dagger.internal.Factory;
import javax.inject.Provider;
/* compiled from: RefreshInternalCapabilitiesChain_Factory.java */
/* loaded from: classes.dex */
public final class Lcz implements Factory<RBt> {
    public final Provider<yDI> BIo;
    public final Provider<WnL> zQM;
    public final Provider<bXh> zZm;
    public final Provider<AlexaClientEventBus> zyO;

    public Lcz(Provider<bXh> provider, Provider<yDI> provider2, Provider<WnL> provider3, Provider<AlexaClientEventBus> provider4) {
        this.zZm = provider;
        this.BIo = provider2;
        this.zQM = provider3;
        this.zyO = provider4;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return new RBt(this.zZm.mo10268get(), this.BIo.mo10268get(), this.zQM.mo10268get(), this.zyO.mo10268get());
    }
}
