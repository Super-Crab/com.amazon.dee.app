package com.amazon.alexa;

import android.content.Context;
import com.amazon.alexa.client.core.device.PersistentStorage;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* compiled from: DeviceModule_ProvideNotificationPreferencesStorageFactory.java */
/* loaded from: classes.dex */
public final class nWO implements Factory<PersistentStorage> {
    public final Provider<Context> BIo;
    public final JaC zZm;

    public nWO(JaC jaC, Provider<Context> provider) {
        this.zZm = jaC;
        this.BIo = provider;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return (PersistentStorage) Preconditions.checkNotNull(this.zZm.jiA(this.BIo.mo10268get()), "Cannot return null from a non-@Nullable @Provides method");
    }
}
