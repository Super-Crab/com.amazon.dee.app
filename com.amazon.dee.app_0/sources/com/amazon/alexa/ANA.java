package com.amazon.alexa;

import com.amazon.alexa.utils.device.AlexaHandsFreeDeviceInformation;
import dagger.internal.Factory;
import javax.inject.Provider;
/* compiled from: OpusAuthority_Factory.java */
/* loaded from: classes.dex */
public final class ANA implements Factory<C0228ryy> {
    public final Provider<gSO> BIo;
    public final Provider<AlexaHandsFreeDeviceInformation> zQM;
    public final Provider<kjl> zZm;

    public ANA(Provider<kjl> provider, Provider<gSO> provider2, Provider<AlexaHandsFreeDeviceInformation> provider3) {
        this.zZm = provider;
        this.BIo = provider2;
        this.zQM = provider3;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return new C0228ryy(this.zZm.mo10268get(), this.BIo.mo10268get(), this.zQM.mo10268get());
    }
}
