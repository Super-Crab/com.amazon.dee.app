package com.amazon.alexa;

import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import dagger.internal.Factory;
import java.util.concurrent.ScheduledExecutorService;
import javax.inject.Provider;
/* compiled from: AVSConnectionStateAuthority_Factory.java */
/* loaded from: classes.dex */
public final class mrP implements Factory<eDG> {
    public final Provider<WnL> BIo;
    public final Provider<AlexaClientEventBus> zQM;
    public final Provider<yDI> zZm;
    public final Provider<ScheduledExecutorService> zyO;

    public mrP(Provider<yDI> provider, Provider<WnL> provider2, Provider<AlexaClientEventBus> provider3, Provider<ScheduledExecutorService> provider4) {
        this.zZm = provider;
        this.BIo = provider2;
        this.zQM = provider3;
        this.zyO = provider4;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return new eDG(this.zZm.mo10268get(), this.BIo.mo10268get(), this.zQM.mo10268get(), this.zyO.mo10268get());
    }
}
