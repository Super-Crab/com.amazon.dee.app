package com.amazon.alexa;

import com.amazon.alexa.utils.TimeProvider;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* compiled from: MetricsModule_ProvideTimeProviderFactory.java */
/* loaded from: classes.dex */
public final class UWm implements Factory<TimeProvider> {
    public final kbj zZm;

    public UWm(kbj kbjVar) {
        this.zZm = kbjVar;
    }

    public static UWm zZm(kbj kbjVar) {
        return new UWm(kbjVar);
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return (TimeProvider) Preconditions.checkNotNull(this.zZm.zZm(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
