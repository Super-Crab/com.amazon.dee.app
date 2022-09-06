package com.amazon.alexa;

import com.amazon.alexa.utils.TimeProvider;
import com.amazon.alexa.utils.device.AlexaHandsFreeDeviceInformation;
import dagger.internal.Factory;
import javax.inject.Provider;
/* compiled from: AudioLatencyTimestampRepository_Factory.java */
/* loaded from: classes.dex */
public final class CdV implements Factory<vrF> {
    public final Provider<TimeProvider> BIo;
    public final Provider<AlexaHandsFreeDeviceInformation> zQM;
    public final Provider<Nyy> zZm;

    public CdV(Provider<Nyy> provider, Provider<TimeProvider> provider2, Provider<AlexaHandsFreeDeviceInformation> provider3) {
        this.zZm = provider;
        this.BIo = provider2;
        this.zQM = provider3;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return new vrF(this.zZm.mo10268get(), this.BIo.mo10268get(), this.zQM.mo10268get());
    }
}
