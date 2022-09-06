package com.amazon.alexa;

import android.app.ActivityManager;
import android.content.Context;
import android.telephony.TelephonyManager;
import android.view.WindowManager;
import com.amazon.alexa.client.core.device.PersistentStorage;
import com.amazon.alexa.client.metrics.core.AppInformation;
import com.amazon.alexa.client.metrics.core.DeviceInformation;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* compiled from: MetricsModule_ProvideDeviceInformationFactory.java */
/* loaded from: classes.dex */
public final class rGB implements Factory<DeviceInformation> {
    public final Provider<Context> BIo;
    public final Provider<AppInformation> JTe;
    public final Provider<PersistentStorage> LPk;
    public final Provider<qxC> Qle;
    public final Provider<ActivityManager> jiA;
    public final Provider<TelephonyManager> zQM;
    public final kbj zZm;
    public final Provider<WindowManager> zyO;

    public rGB(kbj kbjVar, Provider<Context> provider, Provider<TelephonyManager> provider2, Provider<WindowManager> provider3, Provider<ActivityManager> provider4, Provider<qxC> provider5, Provider<AppInformation> provider6, Provider<PersistentStorage> provider7) {
        this.zZm = kbjVar;
        this.BIo = provider;
        this.zQM = provider2;
        this.zyO = provider3;
        this.jiA = provider4;
        this.Qle = provider5;
        this.JTe = provider6;
        this.LPk = provider7;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return (DeviceInformation) Preconditions.checkNotNull(this.zZm.zZm(this.BIo.mo10268get(), this.zQM.mo10268get(), this.zyO.mo10268get(), this.jiA.mo10268get(), this.Qle.mo10268get(), this.JTe.mo10268get(), DoubleCheck.lazy(this.LPk)), "Cannot return null from a non-@Nullable @Provides method");
    }
}
