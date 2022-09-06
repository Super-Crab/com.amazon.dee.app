package com.amazon.alexa;

import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import dagger.internal.Factory;
import javax.inject.Provider;
/* compiled from: BatteryAuthority_Factory.java */
/* loaded from: classes.dex */
public final class LTs implements Factory<kjl> {
    public final Provider<DtD> BIo;
    public final Provider<ktr> zQM;
    public final Provider<AlexaClientEventBus> zZm;

    public LTs(Provider<AlexaClientEventBus> provider, Provider<DtD> provider2, Provider<ktr> provider3) {
        this.zZm = provider;
        this.BIo = provider2;
        this.zQM = provider3;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return new kjl(this.zZm.mo10268get(), this.BIo.mo10268get(), this.zQM.mo10268get());
    }
}
