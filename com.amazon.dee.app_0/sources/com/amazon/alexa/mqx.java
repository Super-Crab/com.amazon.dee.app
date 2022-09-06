package com.amazon.alexa;

import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import com.amazon.alexa.client.core.configuration.ClientConfiguration;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import java.util.concurrent.ScheduledExecutorService;
import javax.inject.Provider;
/* compiled from: LocaleAuthority_Factory.java */
/* loaded from: classes.dex */
public final class mqx implements Factory<MBE> {
    public final Provider<ClientConfiguration> BIo;
    public final Provider<ScheduledExecutorService> jiA;
    public final Provider<Box> zQM;
    public final Provider<AlexaClientEventBus> zZm;
    public final Provider<gSO> zyO;

    public mqx(Provider<AlexaClientEventBus> provider, Provider<ClientConfiguration> provider2, Provider<Box> provider3, Provider<gSO> provider4, Provider<ScheduledExecutorService> provider5) {
        this.zZm = provider;
        this.BIo = provider2;
        this.zQM = provider3;
        this.zyO = provider4;
        this.jiA = provider5;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return new MBE(this.zZm.mo10268get(), DoubleCheck.lazy(this.BIo), this.zQM.mo10268get(), this.zyO.mo10268get(), this.jiA.mo10268get());
    }
}
