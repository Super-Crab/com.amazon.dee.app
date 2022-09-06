package com.amazon.alexa;

import com.amazon.alexa.api.AlexaCardExtras;
import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import com.google.gson.Gson;
import dagger.internal.Factory;
import javax.inject.Provider;
/* compiled from: CardRendererCapabilityAgent_Factory.java */
/* loaded from: classes.dex */
public final class dVw implements Factory<yYy> {
    public final Provider<Gson> BIo;
    public final Provider<UCV> zQM;
    public final Provider<AlexaClientEventBus> zZm;
    public final Provider<AlexaCardExtras> zyO;

    public dVw(Provider<AlexaClientEventBus> provider, Provider<Gson> provider2, Provider<UCV> provider3, Provider<AlexaCardExtras> provider4) {
        this.zZm = provider;
        this.BIo = provider2;
        this.zQM = provider3;
        this.zyO = provider4;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        Provider<AlexaClientEventBus> provider = this.zZm;
        Provider<Gson> provider2 = this.BIo;
        Provider<UCV> provider3 = this.zQM;
        return new yYy(provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get(), this.zyO);
    }
}
