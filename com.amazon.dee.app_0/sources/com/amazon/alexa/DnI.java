package com.amazon.alexa;

import android.content.Context;
import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import com.amazon.alexa.utils.device.AlexaHandsFreeDeviceInformation;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import javax.inject.Provider;
/* compiled from: UserSpeechProviderAuthority_Factory.java */
/* loaded from: classes.dex */
public final class DnI implements Factory<OGT> {
    public final Provider<AlexaClientEventBus> BIo;
    public final Provider<dSq> JTe;
    public final Provider<AlexaHandsFreeDeviceInformation> LPk;
    public final Provider<vkx> Qle;
    public final Provider<Wyh> jiA;
    public final Provider<jdJ> zQM;
    public final Provider<Context> zZm;
    public final Provider<iDr> zyO;

    public DnI(Provider<Context> provider, Provider<AlexaClientEventBus> provider2, Provider<jdJ> provider3, Provider<iDr> provider4, Provider<Wyh> provider5, Provider<vkx> provider6, Provider<dSq> provider7, Provider<AlexaHandsFreeDeviceInformation> provider8) {
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
        return new OGT(this.zZm.mo10268get(), this.BIo.mo10268get(), this.zQM.mo10268get(), this.zyO.mo10268get(), this.jiA.mo10268get(), this.Qle.mo10268get(), DoubleCheck.lazy(this.JTe), this.LPk.mo10268get());
    }
}
