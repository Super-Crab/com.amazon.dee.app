package com.amazon.alexa;

import android.app.NotificationManager;
import android.content.Context;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* compiled from: ContextModule_ProvidesAndroidNotificationManagerFactory.java */
/* loaded from: classes.dex */
public final class Pri implements Factory<NotificationManager> {
    public final Provider<Context> BIo;
    public final dyd zZm;

    public Pri(dyd dydVar, Provider<Context> provider) {
        this.zZm = dydVar;
        this.BIo = provider;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return (NotificationManager) Preconditions.checkNotNull(this.zZm.JTe(this.BIo.mo10268get()), "Cannot return null from a non-@Nullable @Provides method");
    }
}
