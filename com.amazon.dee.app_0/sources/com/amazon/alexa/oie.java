package com.amazon.alexa;

import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* compiled from: ReleaseNetworkingModule_ProvidesDownChannelSchedulerFactory.java */
/* loaded from: classes.dex */
public final class oie implements Factory<DVu> {
    public final Provider<RZN> BIo;
    public final Provider<AlexaClientEventBus> zQM;
    public final Provider<fau> zZm;

    public oie(Provider<fau> provider, Provider<RZN> provider2, Provider<AlexaClientEventBus> provider3) {
        this.zZm = provider;
        this.BIo = provider2;
        this.zQM = provider3;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return (DVu) Preconditions.checkNotNull(new DVu(this.zZm, this.BIo.mo10268get(), this.zQM.mo10268get()), "Cannot return null from a non-@Nullable @Provides method");
    }
}
