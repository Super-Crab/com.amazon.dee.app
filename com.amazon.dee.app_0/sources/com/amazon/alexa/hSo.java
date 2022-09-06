package com.amazon.alexa;

import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import dagger.internal.Factory;
import javax.inject.Provider;
/* compiled from: InteractionModelCapabilityAgent_Factory.java */
/* loaded from: classes.dex */
public final class hSo implements Factory<zMV> {
    public final Provider<AlexaClientEventBus> BIo;
    public final Provider<vkx> zQM;
    public final Provider<jSO> zZm;

    public hSo(Provider<jSO> provider, Provider<AlexaClientEventBus> provider2, Provider<vkx> provider3) {
        this.zZm = provider;
        this.BIo = provider2;
        this.zQM = provider3;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return new zMV(this.zZm.mo10268get(), this.BIo.mo10268get(), this.zQM.mo10268get());
    }
}
