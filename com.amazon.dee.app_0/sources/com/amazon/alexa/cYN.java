package com.amazon.alexa;

import android.content.Context;
import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import dagger.internal.Factory;
import javax.inject.Provider;
/* compiled from: BecomingNoisyManager_Factory.java */
/* loaded from: classes.dex */
public final class cYN implements Factory<tjk> {
    public final Provider<AlexaClientEventBus> BIo;
    public final Provider<QJr> zQM;
    public final Provider<Context> zZm;

    public cYN(Provider<Context> provider, Provider<AlexaClientEventBus> provider2, Provider<QJr> provider3) {
        this.zZm = provider;
        this.BIo = provider2;
        this.zQM = provider3;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return new tjk(this.zZm.mo10268get(), this.BIo.mo10268get(), this.zQM.mo10268get());
    }
}
