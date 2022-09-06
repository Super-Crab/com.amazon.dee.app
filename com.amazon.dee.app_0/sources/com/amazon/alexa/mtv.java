package com.amazon.alexa;

import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import com.amazon.alexa.client.core.configuration.ClientConfiguration;
import com.amazon.alexa.utils.TimeProvider;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import java.util.concurrent.ScheduledExecutorService;
import javax.inject.Provider;
/* compiled from: AudioPlayerInteractionFactory_Factory.java */
/* loaded from: classes.dex */
public final class mtv implements Factory<chR> {
    public final Provider<ScheduledExecutorService> BIo;
    public final Provider<TimeProvider> Qle;
    public final Provider<ClientConfiguration> jiA;
    public final Provider<AlexaClientEventBus> zQM;
    public final Provider<shl> zZm;
    public final Provider<fla> zyO;

    public mtv(Provider<shl> provider, Provider<ScheduledExecutorService> provider2, Provider<AlexaClientEventBus> provider3, Provider<fla> provider4, Provider<ClientConfiguration> provider5, Provider<TimeProvider> provider6) {
        this.zZm = provider;
        this.BIo = provider2;
        this.zQM = provider3;
        this.zyO = provider4;
        this.jiA = provider5;
        this.Qle = provider6;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return new chR(this.zZm.mo10268get(), this.BIo.mo10268get(), this.zQM.mo10268get(), this.zyO.mo10268get(), DoubleCheck.lazy(this.jiA), this.Qle.mo10268get());
    }
}
