package com.amazon.alexa;

import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import com.amazon.alexa.client.core.configuration.ClientConfiguration;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import javax.inject.Provider;
/* compiled from: DriveModeMetricsAuthority_Factory.java */
/* loaded from: classes.dex */
public final class Mec implements Factory<GQk> {
    public final Provider<paF> BIo;
    public final Provider<rqw> zQM;
    public final Provider<AlexaClientEventBus> zZm;
    public final Provider<ClientConfiguration> zyO;

    public Mec(Provider<AlexaClientEventBus> provider, Provider<paF> provider2, Provider<rqw> provider3, Provider<ClientConfiguration> provider4) {
        this.zZm = provider;
        this.BIo = provider2;
        this.zQM = provider3;
        this.zyO = provider4;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return new GQk(this.zZm.mo10268get(), this.BIo.mo10268get(), this.zQM.mo10268get(), DoubleCheck.lazy(this.zyO));
    }
}
