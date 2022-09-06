package com.amazon.alexa;

import android.content.Context;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* compiled from: MetricsModule_ProvideAndroidIdFactory.java */
/* loaded from: classes.dex */
public final class NOk implements Factory<String> {
    public final Provider<Context> BIo;
    public final kbj zZm;

    public NOk(kbj kbjVar, Provider<Context> provider) {
        this.zZm = kbjVar;
        this.BIo = provider;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return (String) Preconditions.checkNotNull(this.zZm.zZm(this.BIo.mo10268get()), "Cannot return null from a non-@Nullable @Provides method");
    }
}
