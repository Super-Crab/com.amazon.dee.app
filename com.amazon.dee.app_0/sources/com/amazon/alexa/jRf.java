package com.amazon.alexa;

import android.content.Context;
import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import dagger.internal.Factory;
import javax.inject.Provider;
/* compiled from: BatteryLevelReceiver_Factory.java */
/* loaded from: classes.dex */
public final class jRf implements Factory<ktr> {
    public final Provider<AlexaClientEventBus> BIo;
    public final Provider<Context> zZm;

    public jRf(Provider<Context> provider, Provider<AlexaClientEventBus> provider2) {
        this.zZm = provider;
        this.BIo = provider2;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return new ktr(this.zZm.mo10268get(), this.BIo.mo10268get());
    }
}
