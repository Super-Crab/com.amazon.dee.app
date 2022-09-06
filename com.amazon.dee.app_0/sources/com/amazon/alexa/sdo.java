package com.amazon.alexa;

import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import dagger.internal.Factory;
import javax.inject.Provider;
/* compiled from: UnsupportedMessageCapabilityAgent_Factory.java */
/* loaded from: classes.dex */
public final class sdo implements Factory<VqX> {
    public final Provider<AlexaClientEventBus> zZm;

    public sdo(Provider<AlexaClientEventBus> provider) {
        this.zZm = provider;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return new VqX(this.zZm.mo10268get());
    }
}
