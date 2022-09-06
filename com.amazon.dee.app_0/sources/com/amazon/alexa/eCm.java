package com.amazon.alexa;

import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import com.amazon.alexa.wakeword.pryon.WakeWordDetectorProvider;
import dagger.internal.Factory;
import javax.inject.Provider;
/* compiled from: DriveModeAuthority_Factory.java */
/* loaded from: classes.dex */
public final class eCm implements Factory<rqw> {
    public final Provider<WakeWordDetectorProvider> BIo;
    public final Provider<AlexaClientEventBus> zZm;

    public eCm(Provider<AlexaClientEventBus> provider, Provider<WakeWordDetectorProvider> provider2) {
        this.zZm = provider;
        this.BIo = provider2;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return new rqw(this.zZm.mo10268get(), this.BIo.mo10268get());
    }
}
