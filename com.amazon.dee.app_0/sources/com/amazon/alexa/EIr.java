package com.amazon.alexa;

import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import com.amazon.alexa.client.core.configuration.ClientConfiguration;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import javax.inject.Provider;
/* compiled from: EndpointAuthority_Factory.java */
/* loaded from: classes.dex */
public final class EIr implements Factory<xUA> {
    public final Provider<Box> BIo;
    public final Provider<ClientConfiguration> zQM;
    public final Provider<AlexaClientEventBus> zZm;

    public EIr(Provider<AlexaClientEventBus> provider, Provider<Box> provider2, Provider<ClientConfiguration> provider3) {
        this.zZm = provider;
        this.BIo = provider2;
        this.zQM = provider3;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return new xUA(this.zZm.mo10268get(), this.BIo.mo10268get(), DoubleCheck.lazy(this.zQM));
    }
}
