package com.amazon.alexa;

import android.content.Context;
import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* compiled from: MetricsModule_ProvideEventBroadcastSenderFactory.java */
/* loaded from: classes.dex */
public final class INL implements Factory<pbo> {
    public final Provider<Context> BIo;
    public final Provider<AlexaClientEventBus> zQM;
    public final kbj zZm;

    public INL(kbj kbjVar, Provider<Context> provider, Provider<AlexaClientEventBus> provider2) {
        this.zZm = kbjVar;
        this.BIo = provider;
        this.zQM = provider2;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return (pbo) Preconditions.checkNotNull(this.zZm.zZm(this.BIo.mo10268get(), this.zQM.mo10268get()), "Cannot return null from a non-@Nullable @Provides method");
    }
}
