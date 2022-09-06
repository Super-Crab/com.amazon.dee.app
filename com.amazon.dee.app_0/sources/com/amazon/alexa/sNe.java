package com.amazon.alexa;

import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* compiled from: ChannelsModule_ProvidesVisualChannelsFactory.java */
/* loaded from: classes.dex */
public final class sNe implements Factory<Rpb> {
    public final Provider<AlexaClientEventBus> BIo;
    public final Provider<Mpk> zQM;
    public final dsY zZm;

    public sNe(dsY dsy, Provider<AlexaClientEventBus> provider, Provider<Mpk> provider2) {
        this.zZm = dsy;
        this.BIo = provider;
        this.zQM = provider2;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return (Rpb) Preconditions.checkNotNull(this.zZm.zZm(this.BIo.mo10268get(), this.zQM.mo10268get()), "Cannot return null from a non-@Nullable @Provides method");
    }
}
