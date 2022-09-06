package com.amazon.alexa;

import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import dagger.internal.Factory;
import javax.inject.Provider;
/* compiled from: EstablishDownchannelChain_Factory.java */
/* loaded from: classes.dex */
public final class FKG implements Factory<lAm> {
    public final Provider<yDI> BIo;
    public final Provider<AlexaClientEventBus> jiA;
    public final Provider<qxC> zQM;
    public final Provider<dAN> zZm;
    public final Provider<WnL> zyO;

    public FKG(Provider<dAN> provider, Provider<yDI> provider2, Provider<qxC> provider3, Provider<WnL> provider4, Provider<AlexaClientEventBus> provider5) {
        this.zZm = provider;
        this.BIo = provider2;
        this.zQM = provider3;
        this.zyO = provider4;
        this.jiA = provider5;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return new lAm(this.zZm.mo10268get(), this.BIo.mo10268get(), this.zQM.mo10268get(), this.zyO.mo10268get(), this.jiA.mo10268get());
    }
}
