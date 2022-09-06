package com.amazon.alexa;

import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import dagger.internal.Factory;
import javax.inject.Provider;
/* compiled from: AlexaStateWakeWordPrecondition_Factory.java */
/* loaded from: classes.dex */
public final class PRo implements Factory<Snr> {
    public final Provider<AlexaClientEventBus> zZm;

    public PRo(Provider<AlexaClientEventBus> provider) {
        this.zZm = provider;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return new Snr(this.zZm.mo10268get());
    }
}
