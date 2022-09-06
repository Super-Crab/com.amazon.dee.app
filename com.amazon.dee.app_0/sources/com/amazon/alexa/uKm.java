package com.amazon.alexa;

import android.content.Context;
import androidx.annotation.Nullable;
import com.amazon.alexa.handsfree.devices.DeviceInformation;
import dagger.internal.Factory;
import javax.inject.Provider;
/* compiled from: IOComponentModule_ProvidesDeviceInformationFactory.java */
/* loaded from: classes.dex */
public final class uKm implements Factory<DeviceInformation> {
    public final Provider<Context> BIo;
    public final FLJ zZm;

    public uKm(FLJ flj, Provider<Context> provider) {
        this.zZm = flj;
        this.BIo = provider;
    }

    @Override // javax.inject.Provider
    @Nullable
    /* renamed from: get */
    public Object mo10268get() {
        return this.zZm.zZm(this.BIo.mo10268get());
    }
}
