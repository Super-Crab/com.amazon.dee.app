package com.amazon.alexa;

import com.amazon.alexa.client.core.device.PersistentStorage;
import com.amazon.alexa.handsfree.devices.AMPDInformationProvider;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import javax.inject.Provider;
/* compiled from: AlexaDevicePreferences_Factory.java */
/* loaded from: classes.dex */
public final class zNZ implements Factory<peZ> {
    public final Provider<gSO> BIo;
    public final Provider<AMPDInformationProvider> zQM;
    public final Provider<PersistentStorage> zZm;

    public zNZ(Provider<PersistentStorage> provider, Provider<gSO> provider2, Provider<AMPDInformationProvider> provider3) {
        this.zZm = provider;
        this.BIo = provider2;
        this.zQM = provider3;
    }

    public static zNZ zZm(Provider<PersistentStorage> provider, Provider<gSO> provider2, Provider<AMPDInformationProvider> provider3) {
        return new zNZ(provider, provider2, provider3);
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return new peZ(DoubleCheck.lazy(this.zZm), DoubleCheck.lazy(this.BIo), this.zQM.mo10268get());
    }
}
