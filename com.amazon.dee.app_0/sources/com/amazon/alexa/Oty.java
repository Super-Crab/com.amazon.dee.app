package com.amazon.alexa;

import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import com.google.gson.Gson;
import dagger.internal.Factory;
import javax.inject.Provider;
/* compiled from: SpeechmarkId3Parser_Factory.java */
/* loaded from: classes.dex */
public final class Oty implements Factory<fUD> {
    public final Provider<DJX> BIo;
    public final Provider<Gson> zQM;
    public final Provider<Eaz> zZm;
    public final Provider<AlexaClientEventBus> zyO;

    public Oty(Provider<Eaz> provider, Provider<DJX> provider2, Provider<Gson> provider3, Provider<AlexaClientEventBus> provider4) {
        this.zZm = provider;
        this.BIo = provider2;
        this.zQM = provider3;
        this.zyO = provider4;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return new fUD(this.zZm.mo10268get(), this.BIo.mo10268get(), this.zQM.mo10268get(), this.zyO.mo10268get());
    }
}
