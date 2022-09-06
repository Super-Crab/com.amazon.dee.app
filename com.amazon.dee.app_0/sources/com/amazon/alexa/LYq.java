package com.amazon.alexa;

import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import dagger.internal.Factory;
import javax.inject.Provider;
/* compiled from: AudioPlayerComponentStateAuthority_Factory.java */
/* loaded from: classes.dex */
public final class LYq implements Factory<oGE> {
    public final Provider<UCV> BIo;
    public final Provider<AlexaClientEventBus> zZm;

    public LYq(Provider<AlexaClientEventBus> provider, Provider<UCV> provider2) {
        this.zZm = provider;
        this.BIo = provider2;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return new oGE(this.zZm.mo10268get(), this.BIo.mo10268get());
    }
}
