package com.amazon.alexa;

import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import dagger.internal.Factory;
import javax.inject.Provider;
/* compiled from: ApiCallAuthority_Factory.java */
/* loaded from: classes.dex */
public final class wUw implements Factory<JXl> {
    public final Provider<AlexaClientEventBus> zZm;

    public wUw(Provider<AlexaClientEventBus> provider) {
        this.zZm = provider;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return new JXl(this.zZm.mo10268get());
    }
}
