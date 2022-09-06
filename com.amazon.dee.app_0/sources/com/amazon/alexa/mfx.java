package com.amazon.alexa;

import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import dagger.internal.Factory;
import javax.inject.Provider;
/* compiled from: AlexaServiceScoMetrics_Factory.java */
/* loaded from: classes.dex */
public final class mfx implements Factory<MLT> {
    public final Provider<AlexaClientEventBus> zZm;

    public mfx(Provider<AlexaClientEventBus> provider) {
        this.zZm = provider;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return new MLT(this.zZm.mo10268get());
    }
}
