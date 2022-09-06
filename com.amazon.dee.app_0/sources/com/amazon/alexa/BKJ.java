package com.amazon.alexa;

import android.content.Context;
import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import com.amazon.alexa.handsfree.devices.AMPDInformationProvider;
import com.amazon.alexa.handsfree.devices.DeviceInformation;
import com.amazon.alexa.utils.TimeProvider;
import dagger.internal.Factory;
import javax.inject.Provider;
/* compiled from: InternalTrustedStatesProvider_Factory.java */
/* loaded from: classes.dex */
public final class BKJ implements Factory<adp> {
    public final Provider<peZ> BIo;
    public final Provider<AlexaClientEventBus> JTe;
    public final Provider<gSO> LPk;
    public final Provider<TimeProvider> Qle;
    public final Provider<Msx> jiA;
    public final Provider<AMPDInformationProvider> yPL;
    public final Provider<Box> zQM;
    public final Provider<Context> zZm;
    public final Provider<DeviceInformation> zyO;

    public BKJ(Provider<Context> provider, Provider<peZ> provider2, Provider<Box> provider3, Provider<DeviceInformation> provider4, Provider<Msx> provider5, Provider<TimeProvider> provider6, Provider<AlexaClientEventBus> provider7, Provider<gSO> provider8, Provider<AMPDInformationProvider> provider9) {
        this.zZm = provider;
        this.BIo = provider2;
        this.zQM = provider3;
        this.zyO = provider4;
        this.jiA = provider5;
        this.Qle = provider6;
        this.JTe = provider7;
        this.LPk = provider8;
        this.yPL = provider9;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return new adp(this.zZm.mo10268get(), this.BIo.mo10268get(), this.zQM.mo10268get(), this.zyO.mo10268get(), this.jiA.mo10268get(), this.Qle.mo10268get(), this.JTe.mo10268get(), this.LPk.mo10268get(), this.yPL.mo10268get());
    }
}
