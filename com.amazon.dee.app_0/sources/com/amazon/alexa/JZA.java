package com.amazon.alexa;

import android.content.Context;
import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import com.amazon.alexa.handsfree.devices.AMPDInformationProvider;
import com.amazon.alexa.utils.TimeProvider;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* compiled from: IOComponentModule_ProvidesTrustedStatesProviderFactory.java */
/* loaded from: classes.dex */
public final class JZA implements Factory<VIE> {
    public final Provider<Context> BIo;
    public final Provider<AlexaClientEventBus> JTe;
    public final Provider<gSO> LPk;
    public final Provider<TimeProvider> Qle;
    public final Provider<Msx> jiA;
    public final Provider<AMPDInformationProvider> yPL;
    public final Provider<peZ> zQM;
    public final FLJ zZm;
    public final Provider<Box> zyO;

    public JZA(FLJ flj, Provider<Context> provider, Provider<peZ> provider2, Provider<Box> provider3, Provider<Msx> provider4, Provider<TimeProvider> provider5, Provider<AlexaClientEventBus> provider6, Provider<gSO> provider7, Provider<AMPDInformationProvider> provider8) {
        this.zZm = flj;
        this.BIo = provider;
        this.zQM = provider2;
        this.zyO = provider3;
        this.jiA = provider4;
        this.Qle = provider5;
        this.JTe = provider6;
        this.LPk = provider7;
        this.yPL = provider8;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return (VIE) Preconditions.checkNotNull(this.zZm.zZm(this.BIo.mo10268get(), this.zQM.mo10268get(), this.zyO.mo10268get(), this.jiA.mo10268get(), this.Qle.mo10268get(), this.JTe.mo10268get(), this.LPk.mo10268get(), this.yPL.mo10268get()), "Cannot return null from a non-@Nullable @Provides method");
    }
}
