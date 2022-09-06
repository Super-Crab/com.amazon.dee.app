package com.amazon.alexa;

import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import dagger.internal.Factory;
import java.util.concurrent.ScheduledExecutorService;
import javax.inject.Provider;
/* compiled from: TimeZoneAuthority_Factory.java */
/* loaded from: classes.dex */
public final class oMl implements Factory<zDj> {
    public final Provider<Box> BIo;
    public final Provider<gSO> zQM;
    public final Provider<AlexaClientEventBus> zZm;
    public final Provider<ScheduledExecutorService> zyO;

    public oMl(Provider<AlexaClientEventBus> provider, Provider<Box> provider2, Provider<gSO> provider3, Provider<ScheduledExecutorService> provider4) {
        this.zZm = provider;
        this.BIo = provider2;
        this.zQM = provider3;
        this.zyO = provider4;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return new zDj(this.zZm.mo10268get(), this.BIo.mo10268get(), this.zQM.mo10268get(), this.zyO.mo10268get());
    }
}
