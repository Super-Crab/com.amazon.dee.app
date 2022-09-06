package com.amazon.alexa;

import android.content.Context;
import com.amazon.alexa.client.core.device.PersistentStorage;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* compiled from: MetricsModule_ProvideVoiceInteractionStateStorageFactory.java */
/* loaded from: classes.dex */
public final class bXm implements Factory<PersistentStorage> {
    public final Provider<Context> BIo;
    public final kbj zZm;

    public bXm(kbj kbjVar, Provider<Context> provider) {
        this.zZm = kbjVar;
        this.BIo = provider;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return (PersistentStorage) Preconditions.checkNotNull(this.zZm.jiA(this.BIo.mo10268get()), "Cannot return null from a non-@Nullable @Provides method");
    }
}
