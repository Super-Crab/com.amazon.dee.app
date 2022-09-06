package com.amazon.alexa;

import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import dagger.internal.Factory;
import java.util.concurrent.ScheduledExecutorService;
import javax.inject.Provider;
/* compiled from: MediaChangeListenerFactory_Factory.java */
/* loaded from: classes.dex */
public final class sku implements Factory<yjR> {
    public final Provider<OWw> BIo;
    public final Provider<ScheduledExecutorService> zQM;
    public final Provider<AlexaClientEventBus> zZm;
    public final Provider<qKe> zyO;

    public sku(Provider<AlexaClientEventBus> provider, Provider<OWw> provider2, Provider<ScheduledExecutorService> provider3, Provider<qKe> provider4) {
        this.zZm = provider;
        this.BIo = provider2;
        this.zQM = provider3;
        this.zyO = provider4;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return new yjR(this.zZm.mo10268get(), this.BIo.mo10268get(), this.zQM.mo10268get(), this.zyO.mo10268get());
    }
}
