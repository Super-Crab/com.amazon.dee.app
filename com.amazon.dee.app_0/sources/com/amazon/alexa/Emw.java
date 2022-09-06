package com.amazon.alexa;

import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import dagger.internal.Factory;
import java.util.concurrent.ScheduledExecutorService;
import javax.inject.Provider;
/* compiled from: ExternalMediaPlayerCapabilityAgent_Factory.java */
/* loaded from: classes.dex */
public final class Emw implements Factory<DJw> {
    public final Provider<lhN> BIo;
    public final Provider<zmg> zQM;
    public final Provider<AlexaClientEventBus> zZm;
    public final Provider<ScheduledExecutorService> zyO;

    public Emw(Provider<AlexaClientEventBus> provider, Provider<lhN> provider2, Provider<zmg> provider3, Provider<ScheduledExecutorService> provider4) {
        this.zZm = provider;
        this.BIo = provider2;
        this.zQM = provider3;
        this.zyO = provider4;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return new DJw(this.zZm.mo10268get(), this.BIo.mo10268get(), this.zQM.mo10268get(), this.zyO.mo10268get());
    }
}
