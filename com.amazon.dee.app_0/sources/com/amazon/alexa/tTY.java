package com.amazon.alexa;

import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import dagger.internal.Factory;
import javax.inject.Provider;
/* compiled from: SpeechInteractionAudioMetadataFactory_Factory.java */
/* loaded from: classes.dex */
public final class tTY implements Factory<yqV> {
    public final Provider<AlexaClientEventBus> zZm;

    public tTY(Provider<AlexaClientEventBus> provider) {
        this.zZm = provider;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return new yqV(this.zZm.mo10268get());
    }
}
