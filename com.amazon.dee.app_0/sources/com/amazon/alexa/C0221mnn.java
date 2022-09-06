package com.amazon.alexa;

import android.content.Context;
import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import com.amazon.alexa.utils.TimeProvider;
import com.google.gson.Gson;
import dagger.internal.Factory;
import javax.inject.Provider;
/* compiled from: ExternalComponentStateStore_Factory.java */
/* renamed from: com.amazon.alexa.mnn  reason: case insensitive filesystem */
/* loaded from: classes.dex */
public final class C0221mnn implements Factory<ICz> {
    public final Provider<TFi> BIo;
    public final Provider<AlexaClientEventBus> jiA;
    public final Provider<TimeProvider> zQM;
    public final Provider<Context> zZm;
    public final Provider<Gson> zyO;

    public C0221mnn(Provider<Context> provider, Provider<TFi> provider2, Provider<TimeProvider> provider3, Provider<Gson> provider4, Provider<AlexaClientEventBus> provider5) {
        this.zZm = provider;
        this.BIo = provider2;
        this.zQM = provider3;
        this.zyO = provider4;
        this.jiA = provider5;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return new ICz(this.zZm.mo10268get(), this.BIo.mo10268get(), this.zQM.mo10268get(), this.zyO.mo10268get(), this.jiA.mo10268get());
    }
}
