package com.amazon.alexa;

import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import dagger.internal.Factory;
import javax.inject.Provider;
/* compiled from: VisualFocusManager_Factory.java */
/* loaded from: classes.dex */
public final class hOU implements Factory<kDa> {
    public final Provider<AlexaClientEventBus> zZm;

    public hOU(Provider<AlexaClientEventBus> provider) {
        this.zZm = provider;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return new kDa(this.zZm.mo10268get());
    }
}
