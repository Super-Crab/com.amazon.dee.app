package com.amazon.alexa;

import android.content.Context;
import android.content.SharedPreferences;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* compiled from: OfflinePromptsModule_ProvideSharedPreferencesFactory.java */
/* loaded from: classes.dex */
public final class OYZ implements Factory<SharedPreferences> {
    public final Provider<Context> BIo;
    public final QYP zZm;

    public OYZ(QYP qyp, Provider<Context> provider) {
        this.zZm = qyp;
        this.BIo = provider;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return (SharedPreferences) Preconditions.checkNotNull(this.zZm.BIo(this.BIo.mo10268get()), "Cannot return null from a non-@Nullable @Provides method");
    }
}
