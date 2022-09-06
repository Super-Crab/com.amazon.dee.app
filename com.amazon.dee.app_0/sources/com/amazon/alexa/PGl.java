package com.amazon.alexa;

import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import dagger.internal.Factory;
import javax.inject.Provider;
/* compiled from: SynchronizeStateChain_Factory.java */
/* loaded from: classes.dex */
public final class PGl implements Factory<EdC> {
    public final Provider<WnL> BIo;
    public final Provider<AlexaClientEventBus> zQM;
    public final Provider<yED> zZm;

    public PGl(Provider<yED> provider, Provider<WnL> provider2, Provider<AlexaClientEventBus> provider3) {
        this.zZm = provider;
        this.BIo = provider2;
        this.zQM = provider3;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return new EdC(this.zZm.mo10268get(), this.BIo.mo10268get(), this.zQM.mo10268get());
    }
}
