package com.amazon.alexa;

import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import dagger.internal.Factory;
import javax.inject.Provider;
/* compiled from: AlexaSuppressionAuthority_Factory.java */
/* loaded from: classes.dex */
public final class Tbw implements Factory<Qgh> {
    public final Provider<AlexaClientEventBus> zZm;

    public Tbw(Provider<AlexaClientEventBus> provider) {
        this.zZm = provider;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return new Qgh(this.zZm.mo10268get());
    }
}
