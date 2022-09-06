package com.amazon.alexa;

import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import dagger.internal.Factory;
import javax.inject.Provider;
/* compiled from: MessageProcessingSequencer_Factory.java */
/* loaded from: classes.dex */
public final class vPC implements Factory<Qoi> {
    public final Provider<jSO> BIo;
    public final Provider<AlexaClientEventBus> zZm;

    public vPC(Provider<AlexaClientEventBus> provider, Provider<jSO> provider2) {
        this.zZm = provider;
        this.BIo = provider2;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return new Qoi(this.zZm.mo10268get(), this.BIo.mo10268get());
    }
}
