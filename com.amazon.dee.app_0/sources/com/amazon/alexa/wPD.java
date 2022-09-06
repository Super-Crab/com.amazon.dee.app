package com.amazon.alexa;

import android.content.Context;
import com.amazon.alexa.client.core.device.PersistentStorage;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* compiled from: MetricsModule_ProvideTextInteractionStateStorageFactory.java */
/* loaded from: classes.dex */
public final class wPD implements Factory<PersistentStorage> {
    public final Provider<Context> BIo;
    public final kbj zZm;

    public wPD(kbj kbjVar, Provider<Context> provider) {
        this.zZm = kbjVar;
        this.BIo = provider;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return (PersistentStorage) Preconditions.checkNotNull(this.zZm.zyO(this.BIo.mo10268get()), "Cannot return null from a non-@Nullable @Provides method");
    }
}
