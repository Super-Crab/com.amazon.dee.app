package com.amazon.alexa;

import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import dagger.internal.Factory;
import javax.inject.Provider;
/* compiled from: DialogAuthority_Factory.java */
/* loaded from: classes.dex */
public final class qgt implements Factory<jSO> {
    public final Provider<AlexaClientEventBus> zZm;

    public qgt(Provider<AlexaClientEventBus> provider) {
        this.zZm = provider;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return new jSO(this.zZm.mo10268get());
    }
}
