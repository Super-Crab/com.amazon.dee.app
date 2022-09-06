package com.amazon.alexa;

import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import dagger.internal.Factory;
import javax.inject.Provider;
/* compiled from: AlexaUserSpeechAuthority_Factory.java */
/* loaded from: classes.dex */
public final class uuO implements Factory<NXS> {
    public final Provider<AlexaClientEventBus> zZm;

    public uuO(Provider<AlexaClientEventBus> provider) {
        this.zZm = provider;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return new NXS(this.zZm.mo10268get());
    }
}
