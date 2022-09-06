package com.amazon.alexa;

import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import dagger.internal.Factory;
import javax.inject.Provider;
/* compiled from: ClientConnectionsAuthority_Factory.java */
/* loaded from: classes.dex */
public final class XRI implements Factory<IYJ> {
    public final Provider<AlexaClientEventBus> zZm;

    public XRI(Provider<AlexaClientEventBus> provider) {
        this.zZm = provider;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return new IYJ(this.zZm.mo10268get());
    }
}
