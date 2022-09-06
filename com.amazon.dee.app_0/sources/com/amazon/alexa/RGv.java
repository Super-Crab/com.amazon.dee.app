package com.amazon.alexa;

import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import dagger.internal.Factory;
import javax.inject.Provider;
/* compiled from: ServiceActivityAuthority_Factory.java */
/* loaded from: classes.dex */
public final class RGv implements Factory<wLb> {
    public final Provider<qxC> BIo;
    public final Provider<IYJ> zQM;
    public final Provider<AlexaClientEventBus> zZm;

    public RGv(Provider<AlexaClientEventBus> provider, Provider<qxC> provider2, Provider<IYJ> provider3) {
        this.zZm = provider;
        this.BIo = provider2;
        this.zQM = provider3;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return new wLb(this.zZm.mo10268get(), this.BIo.mo10268get(), this.zQM.mo10268get());
    }
}
