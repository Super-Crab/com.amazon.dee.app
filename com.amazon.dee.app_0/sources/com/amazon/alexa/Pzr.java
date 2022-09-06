package com.amazon.alexa;

import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import dagger.internal.Factory;
import javax.inject.Provider;
/* compiled from: WakeWordMetricsListener_Factory.java */
/* loaded from: classes.dex */
public final class Pzr implements Factory<FdV> {
    public final Provider<AlexaClientEventBus> zZm;

    public Pzr(Provider<AlexaClientEventBus> provider) {
        this.zZm = provider;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return new FdV(this.zZm.mo10268get());
    }
}
