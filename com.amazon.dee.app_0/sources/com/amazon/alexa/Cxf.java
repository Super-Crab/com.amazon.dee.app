package com.amazon.alexa;

import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import com.amazon.alexa.client.core.configuration.ClientConfiguration;
import com.amazon.alexa.system.UserInactivityAuthority;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import java.util.concurrent.ScheduledExecutorService;
import javax.inject.Provider;
/* compiled from: SystemCapabilityAgent_Factory.java */
/* loaded from: classes.dex */
public final class Cxf implements Factory<yED> {
    public final Provider<xUA> BIo;
    public final Provider<MBE> JTe;
    public final Provider<ScheduledExecutorService> LPk;
    public final Provider<gSO> Mlj;
    public final Provider<Box> Qle;
    public final Provider<zDj> jiA;
    public final Provider<ClientConfiguration> yPL;
    public final Provider<UserInactivityAuthority> zQM;
    public final Provider<AlexaClientEventBus> zZm;
    public final Provider<lhN> zyO;

    public Cxf(Provider<AlexaClientEventBus> provider, Provider<xUA> provider2, Provider<UserInactivityAuthority> provider3, Provider<lhN> provider4, Provider<zDj> provider5, Provider<Box> provider6, Provider<MBE> provider7, Provider<ScheduledExecutorService> provider8, Provider<ClientConfiguration> provider9, Provider<gSO> provider10) {
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
        return new yED(this.zZm.mo10268get(), DoubleCheck.lazy(this.BIo), this.zQM.mo10268get(), this.zyO.mo10268get(), this.jiA.mo10268get(), this.Qle.mo10268get(), this.JTe.mo10268get(), this.LPk.mo10268get(), DoubleCheck.lazy(this.yPL), DoubleCheck.lazy(this.Mlj));
    }
}
