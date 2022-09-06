package com.amazon.alexa;

import android.content.Context;
import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import com.amazon.alexa.utils.device.AlexaHandsFreeDeviceInformation;
import dagger.internal.Factory;
import javax.inject.Provider;
/* compiled from: HandsFreeTimestampSender_Factory.java */
/* loaded from: classes.dex */
public final class ljO implements Factory<Nyy> {
    public final Provider<Context> BIo;
    public final Provider<AlexaHandsFreeDeviceInformation> zQM;
    public final Provider<AlexaClientEventBus> zZm;

    public ljO(Provider<AlexaClientEventBus> provider, Provider<Context> provider2, Provider<AlexaHandsFreeDeviceInformation> provider3) {
        this.zZm = provider;
        this.BIo = provider2;
        this.zQM = provider3;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return new Nyy(this.zZm.mo10268get(), this.BIo.mo10268get(), this.zQM.mo10268get());
    }
}
