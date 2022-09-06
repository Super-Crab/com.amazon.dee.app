package com.amazon.alexa;

import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import dagger.internal.Factory;
import javax.inject.Provider;
/* compiled from: RequestEventAuthority_Factory.java */
/* loaded from: classes.dex */
public final class vvT implements Factory<DYu> {
    public final Provider<AlexaClientEventBus> zZm;

    public vvT(Provider<AlexaClientEventBus> provider) {
        this.zZm = provider;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return new DYu(this.zZm.mo10268get());
    }
}
