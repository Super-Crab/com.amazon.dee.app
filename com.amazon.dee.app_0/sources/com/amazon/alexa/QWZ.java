package com.amazon.alexa;

import android.content.Context;
import com.amazon.alexa.handsfree.devices.AMPDInformationProvider;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* compiled from: DeviceModule_ProvidesAMPDInformationProviderFactory.java */
/* loaded from: classes.dex */
public final class QWZ implements Factory<AMPDInformationProvider> {
    public final Provider<Context> BIo;
    public final JaC zZm;

    public QWZ(JaC jaC, Provider<Context> provider) {
        this.zZm = jaC;
        this.BIo = provider;
    }

    public static QWZ zZm(JaC jaC, Provider<Context> provider) {
        return new QWZ(jaC, provider);
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return (AMPDInformationProvider) Preconditions.checkNotNull(this.zZm.LPk(this.BIo.mo10268get()), "Cannot return null from a non-@Nullable @Provides method");
    }
}
