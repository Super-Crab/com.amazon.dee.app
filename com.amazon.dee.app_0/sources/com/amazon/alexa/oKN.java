package com.amazon.alexa;

import android.content.Context;
import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import dagger.internal.Factory;
import javax.inject.Provider;
/* compiled from: DeviceLocaleChangedBroadcastReceiver_Factory.java */
/* loaded from: classes.dex */
public final class oKN implements Factory<IcB> {
    public final Provider<AlexaClientEventBus> BIo;
    public final Provider<Context> zZm;

    public oKN(Provider<Context> provider, Provider<AlexaClientEventBus> provider2) {
        this.zZm = provider;
        this.BIo = provider2;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return new IcB(this.zZm.mo10268get(), this.BIo.mo10268get());
    }
}
