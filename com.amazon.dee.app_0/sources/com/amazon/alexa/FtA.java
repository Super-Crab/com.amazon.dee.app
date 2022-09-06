package com.amazon.alexa;

import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import dagger.internal.Factory;
import javax.inject.Provider;
/* compiled from: TextInteractionAuthority_Factory.java */
/* loaded from: classes.dex */
public final class FtA implements Factory<Ccz> {
    public final Provider<qHS> BIo;
    public final Provider<AlexaClientEventBus> zZm;

    public FtA(Provider<AlexaClientEventBus> provider, Provider<qHS> provider2) {
        this.zZm = provider;
        this.BIo = provider2;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return new Ccz(this.zZm.mo10268get(), this.BIo.mo10268get());
    }
}
