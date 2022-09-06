package com.amazon.alexa;

import android.content.Context;
import com.amazon.alexa.client.core.device.PersistentStorage;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* compiled from: DeviceModule_ProvideServiceSettingsStorageFactory.java */
/* loaded from: classes.dex */
public final class FYq implements Factory<PersistentStorage> {
    public final Provider<Context> BIo;
    public final JaC zZm;

    public FYq(JaC jaC, Provider<Context> provider) {
        this.zZm = jaC;
        this.BIo = provider;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return (PersistentStorage) Preconditions.checkNotNull(this.zZm.Qle(this.BIo.mo10268get()), "Cannot return null from a non-@Nullable @Provides method");
    }
}
