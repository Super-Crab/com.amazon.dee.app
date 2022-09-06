package com.amazon.alexa;

import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import com.amazon.alexa.client.core.configuration.ClientConfiguration;
import com.amazon.alexa.utils.TimeProvider;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import javax.inject.Provider;
/* compiled from: DriveModeVoiceCardMetricsAuthority_Factory.java */
/* loaded from: classes.dex */
public final class yNW implements Factory<Kcd> {
    public final Provider<paF> BIo;
    public final Provider<ClientConfiguration> jiA;
    public final Provider<TimeProvider> zQM;
    public final Provider<AlexaClientEventBus> zZm;
    public final Provider<rqw> zyO;

    public yNW(Provider<AlexaClientEventBus> provider, Provider<paF> provider2, Provider<TimeProvider> provider3, Provider<rqw> provider4, Provider<ClientConfiguration> provider5) {
        this.zZm = provider;
        this.BIo = provider2;
        this.zQM = provider3;
        this.zyO = provider4;
        this.jiA = provider5;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return new Kcd(this.zZm.mo10268get(), this.BIo.mo10268get(), this.zQM.mo10268get(), this.zyO.mo10268get(), DoubleCheck.lazy(this.jiA));
    }
}
