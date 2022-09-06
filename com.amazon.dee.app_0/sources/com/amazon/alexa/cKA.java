package com.amazon.alexa;

import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import dagger.internal.Factory;
import javax.inject.Provider;
/* compiled from: VoiceInteractionAuthority_Factory.java */
/* loaded from: classes.dex */
public final class cKA implements Factory<rte> {
    public final Provider<Wiq> BIo;
    public final Provider<AlexaClientEventBus> zZm;

    public cKA(Provider<AlexaClientEventBus> provider, Provider<Wiq> provider2) {
        this.zZm = provider;
        this.BIo = provider2;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return new rte(this.zZm.mo10268get(), this.BIo.mo10268get());
    }
}
