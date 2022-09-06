package com.amazon.alexa;

import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import dagger.internal.Factory;
import javax.inject.Provider;
/* compiled from: AlertsAuthority_Factory.java */
/* loaded from: classes.dex */
public final class cyt implements Factory<QIY> {
    public final Provider<AlexaClientEventBus> zZm;

    public cyt(Provider<AlexaClientEventBus> provider) {
        this.zZm = provider;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return new QIY(this.zZm.mo10268get());
    }
}
