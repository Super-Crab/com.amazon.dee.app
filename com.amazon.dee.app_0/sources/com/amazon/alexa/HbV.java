package com.amazon.alexa;

import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import dagger.internal.Factory;
import javax.inject.Provider;
/* compiled from: MicrophoneInitializationMetricsListener_Factory.java */
/* loaded from: classes.dex */
public final class HbV implements Factory<nno> {
    public final Provider<AlexaClientEventBus> zZm;

    public HbV(Provider<AlexaClientEventBus> provider) {
        this.zZm = provider;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return new nno(this.zZm.mo10268get());
    }
}
