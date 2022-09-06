package com.amazon.alexa;

import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import dagger.internal.Factory;
import javax.inject.Provider;
/* compiled from: AlexaAudioPlaybackAuthority_Factory.java */
/* loaded from: classes.dex */
public final class yPL implements Factory<LPk> {
    public final Provider<SKB> BIo;
    public final Provider<fla> zQM;
    public final Provider<AlexaClientEventBus> zZm;
    public final Provider<QJr> zyO;

    public yPL(Provider<AlexaClientEventBus> provider, Provider<SKB> provider2, Provider<fla> provider3, Provider<QJr> provider4) {
        this.zZm = provider;
        this.BIo = provider2;
        this.zQM = provider3;
        this.zyO = provider4;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return new LPk(this.zZm.mo10268get(), this.BIo.mo10268get(), this.zQM.mo10268get(), this.zyO.mo10268get());
    }
}
