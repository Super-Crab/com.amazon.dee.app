package com.amazon.alexa;

import android.content.Context;
import android.os.PowerManager;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* compiled from: ContextModule_ProvidePowerManagerFactory.java */
/* loaded from: classes.dex */
public final class TVC implements Factory<PowerManager> {
    public final Provider<Context> BIo;
    public final dyd zZm;

    public TVC(dyd dydVar, Provider<Context> provider) {
        this.zZm = dydVar;
        this.BIo = provider;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return (PowerManager) Preconditions.checkNotNull(this.zZm.zQM(this.BIo.mo10268get()), "Cannot return null from a non-@Nullable @Provides method");
    }
}
