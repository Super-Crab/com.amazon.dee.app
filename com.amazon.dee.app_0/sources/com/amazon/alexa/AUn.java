package com.amazon.alexa;

import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import com.amazon.alexa.client.core.configuration.ClientConfiguration;
import com.amazon.alexa.client.core.device.PersistentStorage;
import com.amazon.alexa.client.metrics.core.DeviceInformation;
import com.google.gson.Gson;
import dagger.internal.Factory;
import java.util.concurrent.ScheduledExecutorService;
import javax.inject.Provider;
/* compiled from: CapabilityAuthority_Factory.java */
/* loaded from: classes.dex */
public final class AUn implements Factory<pZY> {
    public final Provider<Gson> BIo;
    public final Provider<WnL> JTe;
    public final Provider<lWz> LPk;
    public final Provider<ClientConfiguration> Mlj;
    public final Provider<DeviceInformation> Qle;
    public final Provider<Cta> jiA;
    public final Provider<ScheduledExecutorService> yPL;
    public final Provider<qxC> zQM;
    public final Provider<AlexaClientEventBus> zZm;
    public final Provider<PersistentStorage> zyO;

    public AUn(Provider<AlexaClientEventBus> provider, Provider<Gson> provider2, Provider<qxC> provider3, Provider<PersistentStorage> provider4, Provider<Cta> provider5, Provider<DeviceInformation> provider6, Provider<WnL> provider7, Provider<lWz> provider8, Provider<ScheduledExecutorService> provider9, Provider<ClientConfiguration> provider10) {
        this.zZm = provider;
        this.BIo = provider2;
        this.zQM = provider3;
        this.zyO = provider4;
        this.jiA = provider5;
        this.Qle = provider6;
        this.JTe = provider7;
        this.LPk = provider8;
        this.yPL = provider9;
        this.Mlj = provider10;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        Provider<AlexaClientEventBus> provider = this.zZm;
        Provider<Gson> provider2 = this.BIo;
        Provider<qxC> provider3 = this.zQM;
        Provider<PersistentStorage> provider4 = this.zyO;
        return new pZY(provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get(), provider4.mo10268get(), this.jiA, this.Qle.mo10268get(), this.JTe.mo10268get(), this.LPk.mo10268get(), this.yPL.mo10268get(), this.Mlj.mo10268get());
    }
}
