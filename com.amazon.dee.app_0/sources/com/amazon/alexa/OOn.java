package com.amazon.alexa;

import android.content.Context;
import android.media.AudioManager;
import android.telephony.TelephonyManager;
import com.amazon.alexa.bluetooth.sco.BluetoothScoController;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* compiled from: AudioPlayerModule_ProvideBluetoothScoControllerFactory.java */
/* loaded from: classes.dex */
public final class OOn implements Factory<BluetoothScoController> {
    public final Provider<Context> BIo;
    public final Provider<MLT> jiA;
    public final Provider<AudioManager> zQM;
    public final uuv zZm;
    public final Provider<TelephonyManager> zyO;

    public OOn(uuv uuvVar, Provider<Context> provider, Provider<AudioManager> provider2, Provider<TelephonyManager> provider3, Provider<MLT> provider4) {
        this.zZm = uuvVar;
        this.BIo = provider;
        this.zQM = provider2;
        this.zyO = provider3;
        this.jiA = provider4;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return (BluetoothScoController) Preconditions.checkNotNull(this.zZm.zZm(this.BIo.mo10268get(), this.zQM.mo10268get(), this.zyO.mo10268get(), this.jiA.mo10268get()), "Cannot return null from a non-@Nullable @Provides method");
    }
}
