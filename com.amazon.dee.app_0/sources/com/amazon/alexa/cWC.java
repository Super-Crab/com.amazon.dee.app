package com.amazon.alexa;

import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import com.amazon.alexa.utils.TimeProvider;
import dagger.internal.Factory;
import javax.inject.Provider;
/* compiled from: FailedInteractionTracker_Factory.java */
/* loaded from: classes.dex */
public final class cWC implements Factory<hlu> {
    public final Provider<TimeProvider> BIo;
    public final Provider<AlexaClientEventBus> zZm;

    public cWC(Provider<AlexaClientEventBus> provider, Provider<TimeProvider> provider2) {
        this.zZm = provider;
        this.BIo = provider2;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return new hlu(this.zZm.mo10268get(), this.BIo.mo10268get());
    }
}
