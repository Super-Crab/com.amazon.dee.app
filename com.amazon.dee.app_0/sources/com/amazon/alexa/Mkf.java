package com.amazon.alexa;

import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import com.amazon.alexa.client.core.configuration.ClientConfiguration;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import javax.inject.Provider;
/* compiled from: TextUiMetricsAuthority_Factory.java */
/* loaded from: classes.dex */
public final class Mkf implements Factory<bwE> {
    public final Provider<paF> BIo;
    public final Provider<ClientConfiguration> zQM;
    public final Provider<AlexaClientEventBus> zZm;

    public Mkf(Provider<AlexaClientEventBus> provider, Provider<paF> provider2, Provider<ClientConfiguration> provider3) {
        this.zZm = provider;
        this.BIo = provider2;
        this.zQM = provider3;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return new bwE(this.zZm.mo10268get(), this.BIo.mo10268get(), DoubleCheck.lazy(this.zQM));
    }
}
