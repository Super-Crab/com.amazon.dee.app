package com.amazon.alexa;

import android.content.Context;
import com.amazon.alexa.utils.device.AlexaHandsFreeDeviceInformation;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* compiled from: AlexaHandsFreeDeviceInformationModule_ProvideAlexaHandsFreeDeviceInformationFactory.java */
/* loaded from: classes.dex */
public final class itI implements Factory<AlexaHandsFreeDeviceInformation> {
    public final Provider<Context> BIo;
    public final Auv zZm;

    public itI(Auv auv, Provider<Context> provider) {
        this.zZm = auv;
        this.BIo = provider;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return (AlexaHandsFreeDeviceInformation) Preconditions.checkNotNull(this.zZm.zZm(this.BIo.mo10268get()), "Cannot return null from a non-@Nullable @Provides method");
    }
}
