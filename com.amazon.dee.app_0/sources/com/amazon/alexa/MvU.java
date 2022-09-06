package com.amazon.alexa;

import android.content.Context;
import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import com.amazon.alexa.client.core.configuration.ClientConfiguration;
import com.amazon.alexa.utils.TimeProvider;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import javax.inject.Provider;
/* compiled from: SettingsController_Factory.java */
/* loaded from: classes.dex */
public final class MvU implements Factory<AHr> {
    public final Provider<AlexaClientEventBus> BIo;
    public final Provider<TimeProvider> JTe;
    public final Provider<gSO> LPk;
    public final Provider<aeS> Qle;
    public final Provider<MBE> jiA;
    public final Provider<ClientConfiguration> zQM;
    public final Provider<Context> zZm;
    public final Provider<Box> zyO;

    public MvU(Provider<Context> provider, Provider<AlexaClientEventBus> provider2, Provider<ClientConfiguration> provider3, Provider<Box> provider4, Provider<MBE> provider5, Provider<aeS> provider6, Provider<TimeProvider> provider7, Provider<gSO> provider8) {
        this.zZm = provider;
        this.BIo = provider2;
        this.zQM = provider3;
        this.zyO = provider4;
        this.jiA = provider5;
        this.Qle = provider6;
        this.JTe = provider7;
        this.LPk = provider8;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return new AHr(this.zZm.mo10268get(), this.BIo.mo10268get(), DoubleCheck.lazy(this.zQM), this.zyO.mo10268get(), this.jiA.mo10268get(), this.Qle.mo10268get(), this.JTe.mo10268get(), this.LPk.mo10268get());
    }
}
