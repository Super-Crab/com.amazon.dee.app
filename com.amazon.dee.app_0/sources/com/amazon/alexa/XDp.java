package com.amazon.alexa;

import android.content.Context;
import com.amazon.alexa.client.core.device.PersistentStorage;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* compiled from: DeviceModule_ProvideDevicePreferencesStorageFactory.java */
/* loaded from: classes.dex */
public final class XDp implements Factory<PersistentStorage> {
    public final Provider<Context> BIo;
    public final JaC zZm;

    public XDp(JaC jaC, Provider<Context> provider) {
        this.zZm = jaC;
        this.BIo = provider;
    }

    public static XDp zZm(JaC jaC, Provider<Context> provider) {
        return new XDp(jaC, provider);
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return (PersistentStorage) Preconditions.checkNotNull(this.zZm.zQM(this.BIo.mo10268get()), "Cannot return null from a non-@Nullable @Provides method");
    }
}
