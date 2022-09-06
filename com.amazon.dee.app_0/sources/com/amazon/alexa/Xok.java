package com.amazon.alexa;

import android.content.Context;
import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import dagger.internal.Factory;
import javax.inject.Provider;
/* compiled from: AttentionSystemLatencyProcessor_Factory.java */
/* loaded from: classes.dex */
public final class Xok implements Factory<AxK> {
    public final Provider<vrF> BIo;
    public final Provider<AlexaClientEventBus> zQM;
    public final Provider<Context> zZm;

    public Xok(Provider<Context> provider, Provider<vrF> provider2, Provider<AlexaClientEventBus> provider3) {
        this.zZm = provider;
        this.BIo = provider2;
        this.zQM = provider3;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return new AxK(this.zZm.mo10268get(), this.BIo.mo10268get(), this.zQM.mo10268get());
    }
}
