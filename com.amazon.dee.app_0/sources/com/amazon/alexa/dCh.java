package com.amazon.alexa;

import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import dagger.internal.Factory;
import javax.inject.Provider;
/* compiled from: PublishCapabilitiesChain_Factory.java */
/* loaded from: classes.dex */
public final class dCh implements Factory<xQf> {
    public final Provider<yDI> BIo;
    public final Provider<WnL> zQM;
    public final Provider<pZY> zZm;
    public final Provider<AlexaClientEventBus> zyO;

    public dCh(Provider<pZY> provider, Provider<yDI> provider2, Provider<WnL> provider3, Provider<AlexaClientEventBus> provider4) {
        this.zZm = provider;
        this.BIo = provider2;
        this.zQM = provider3;
        this.zyO = provider4;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return new xQf(this.zZm.mo10268get(), this.BIo.mo10268get(), this.zQM.mo10268get(), this.zyO.mo10268get());
    }
}
