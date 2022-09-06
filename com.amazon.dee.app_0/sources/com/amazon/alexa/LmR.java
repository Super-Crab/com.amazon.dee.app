package com.amazon.alexa;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import java.util.concurrent.ScheduledExecutorService;
/* compiled from: ExecutorModule_ProvidesSharedSchedulerFactory.java */
/* loaded from: classes.dex */
public final class LmR implements Factory<ScheduledExecutorService> {
    public final TNh zZm;

    public LmR(TNh tNh) {
        this.zZm = tNh;
    }

    public static LmR zZm(TNh tNh) {
        return new LmR(tNh);
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return (ScheduledExecutorService) Preconditions.checkNotNull(this.zZm.zZm(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
