package com.amazon.alexa;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.wifi.WifiManager;
import android.telephony.TelephonyManager;
import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import com.amazon.alexa.utils.device.AlexaHandsFreeDeviceInformation;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* compiled from: ReleaseNetworkingModule_ProvidesConnectivityAuthorityFactory.java */
/* loaded from: classes.dex */
public final class nHN implements Factory<qxC> {
    public final Provider<RZN> BIo;
    public final Provider<Szi> JTe;
    public final Provider<gSO> LPk;
    public final Provider<DYu> Mlj;
    public final Provider<AlexaClientEventBus> Qle;
    public final Provider<ConnectivityManager> jiA;
    public final Provider<AlexaHandsFreeDeviceInformation> yPL;
    public final Provider<WifiManager> zQM;
    public final Provider<Context> zZm;
    public final Provider<TelephonyManager> zyO;
    public final Provider<Dtt> zzR;

    public nHN(Provider<Context> provider, Provider<RZN> provider2, Provider<WifiManager> provider3, Provider<TelephonyManager> provider4, Provider<ConnectivityManager> provider5, Provider<AlexaClientEventBus> provider6, Provider<Szi> provider7, Provider<gSO> provider8, Provider<AlexaHandsFreeDeviceInformation> provider9, Provider<DYu> provider10, Provider<Dtt> provider11) {
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
        this.zzR = provider11;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return (qxC) Preconditions.checkNotNull(new qxC(this.zZm.mo10268get(), DoubleCheck.lazy(this.BIo), this.zQM.mo10268get(), this.zyO.mo10268get(), this.jiA.mo10268get(), this.Qle.mo10268get(), this.JTe.mo10268get(), this.LPk.mo10268get(), this.yPL.mo10268get(), this.Mlj.mo10268get(), this.zzR.mo10268get()), "Cannot return null from a non-@Nullable @Provides method");
    }
}
