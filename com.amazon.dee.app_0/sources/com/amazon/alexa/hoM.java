package com.amazon.alexa;

import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import dagger.internal.Factory;
import javax.inject.Provider;
/* compiled from: SpeakerCapabilityAgent_Factory.java */
/* loaded from: classes.dex */
public final class hoM implements Factory<DJb> {
    public final Provider<QMz> BIo;
    public final Provider<vVi> zQM;
    public final Provider<AlexaClientEventBus> zZm;

    public hoM(Provider<AlexaClientEventBus> provider, Provider<QMz> provider2, Provider<vVi> provider3) {
        this.zZm = provider;
        this.BIo = provider2;
        this.zQM = provider3;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return new DJb(this.zZm.mo10268get(), this.BIo.mo10268get(), this.zQM.mo10268get());
    }
}
