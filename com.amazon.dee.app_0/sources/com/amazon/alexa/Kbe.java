package com.amazon.alexa;

import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import dagger.internal.Factory;
import javax.inject.Provider;
/* compiled from: VisualInteractionScheduler_Factory.java */
/* loaded from: classes.dex */
public final class Kbe implements Factory<gMz> {
    public final Provider<AlexaClientEventBus> BIo;
    public final Provider<Rpb> zZm;

    public Kbe(Provider<Rpb> provider, Provider<AlexaClientEventBus> provider2) {
        this.zZm = provider;
        this.BIo = provider2;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return new gMz(this.zZm.mo10268get(), this.BIo.mo10268get());
    }
}
