package com.amazon.alexa;

import android.content.Context;
import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import dagger.internal.Factory;
import javax.inject.Provider;
/* compiled from: PackagesChangedBroadcastReceiver_Factory.java */
/* loaded from: classes.dex */
public final class zZi implements Factory<QMR> {
    public final Provider<AlexaClientEventBus> BIo;
    public final Provider<Context> zZm;

    public zZi(Provider<Context> provider, Provider<AlexaClientEventBus> provider2) {
        this.zZm = provider;
        this.BIo = provider2;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return new QMR(this.zZm.mo10268get(), this.BIo.mo10268get());
    }
}
