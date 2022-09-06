package com.amazon.alexa;

import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import dagger.internal.Factory;
import javax.inject.Provider;
/* compiled from: PlaybackController_Factory.java */
/* loaded from: classes.dex */
public final class BCb implements Factory<fla> {
    public final Provider<lhN> BIo;
    public final Provider<AlexaClientEventBus> zZm;

    public BCb(Provider<AlexaClientEventBus> provider, Provider<lhN> provider2) {
        this.zZm = provider;
        this.BIo = provider2;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return new fla(this.zZm.mo10268get(), this.BIo.mo10268get());
    }
}
