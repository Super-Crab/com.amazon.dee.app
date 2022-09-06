package com.amazon.alexa;

import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import dagger.internal.Factory;
import javax.inject.Provider;
/* compiled from: InteractionChannelWakeWordPrecondition_Factory.java */
/* loaded from: classes.dex */
public final class ezd implements Factory<jxu> {
    public final Provider<AlexaClientEventBus> zZm;

    public ezd(Provider<AlexaClientEventBus> provider) {
        this.zZm = provider;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return new jxu(this.zZm.mo10268get());
    }
}
