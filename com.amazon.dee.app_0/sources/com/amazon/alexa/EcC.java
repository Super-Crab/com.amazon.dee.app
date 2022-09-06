package com.amazon.alexa;

import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import dagger.internal.Factory;
import javax.inject.Provider;
/* compiled from: MediaBrowserPlayerFinder_Factory.java */
/* loaded from: classes.dex */
public final class EcC implements Factory<Lnt> {
    public final Provider<zmg> BIo;
    public final Provider<Qva> zQM;
    public final Provider<AlexaClientEventBus> zZm;

    public EcC(Provider<AlexaClientEventBus> provider, Provider<zmg> provider2, Provider<Qva> provider3) {
        this.zZm = provider;
        this.BIo = provider2;
        this.zQM = provider3;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return new Lnt(this.zZm.mo10268get(), this.BIo.mo10268get(), this.zQM.mo10268get());
    }
}
