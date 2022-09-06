package com.amazon.alexa;

import android.content.Context;
import android.telephony.TelephonyManager;
import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import com.amazon.alexa.utils.device.AlexaHandsFreeDeviceInformation;
import dagger.internal.Factory;
import javax.inject.Provider;
/* compiled from: PhoneCallControllerComponentStateAuthority_Factory.java */
/* loaded from: classes.dex */
public final class KKi implements Factory<Arv> {
    public final Provider<AlexaClientEventBus> BIo;
    public final Provider<Dtt> JTe;
    public final Provider<AlexaHandsFreeDeviceInformation> Qle;
    public final Provider<TelephonyManager> jiA;
    public final Provider<lEV> zQM;
    public final Provider<Context> zZm;
    public final Provider<LrI> zyO;

    public KKi(Provider<Context> provider, Provider<AlexaClientEventBus> provider2, Provider<lEV> provider3, Provider<LrI> provider4, Provider<TelephonyManager> provider5, Provider<AlexaHandsFreeDeviceInformation> provider6, Provider<Dtt> provider7) {
        this.zZm = provider;
        this.BIo = provider2;
        this.zQM = provider3;
        this.zyO = provider4;
        this.jiA = provider5;
        this.Qle = provider6;
        this.JTe = provider7;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return new Arv(this.zZm.mo10268get(), this.BIo.mo10268get(), this.zQM.mo10268get(), this.zyO.mo10268get(), this.jiA.mo10268get(), this.Qle.mo10268get(), this.JTe.mo10268get());
    }
}
