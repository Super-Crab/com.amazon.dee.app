package com.amazon.alexa;

import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import com.amazon.alexa.utils.TimeProvider;
import dagger.internal.Factory;
import javax.inject.Provider;
/* compiled from: ReadinessAuthority_Factory.java */
/* loaded from: classes.dex */
public final class TCW implements Factory<XUD> {
    public final Provider<qxC> BIo;
    public final Provider<TimeProvider> zQM;
    public final Provider<AlexaClientEventBus> zZm;

    public TCW(Provider<AlexaClientEventBus> provider, Provider<qxC> provider2, Provider<TimeProvider> provider3) {
        this.zZm = provider;
        this.BIo = provider2;
        this.zQM = provider3;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return new XUD(this.zZm.mo10268get(), this.BIo.mo10268get(), this.zQM.mo10268get());
    }
}
