package com.google.common.util.concurrent;

import com.google.common.annotations.GwtIncompatible;
import java.util.concurrent.Callable;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
@GwtIncompatible
/* loaded from: classes3.dex */
public interface ListeningScheduledExecutorService extends ScheduledExecutorService, ListeningExecutorService {
    @Override // java.util.concurrent.ScheduledExecutorService
    /* renamed from: schedule */
    ListenableScheduledFuture<?> mo8281schedule(Runnable runnable, long j, TimeUnit timeUnit);

    @Override // java.util.concurrent.ScheduledExecutorService
    /* renamed from: schedule */
    <V> ListenableScheduledFuture<V> mo8282schedule(Callable<V> callable, long j, TimeUnit timeUnit);

    @Override // java.util.concurrent.ScheduledExecutorService
    /* renamed from: scheduleAtFixedRate */
    ListenableScheduledFuture<?> mo8283scheduleAtFixedRate(Runnable runnable, long j, long j2, TimeUnit timeUnit);

    @Override // java.util.concurrent.ScheduledExecutorService
    /* renamed from: scheduleWithFixedDelay */
    ListenableScheduledFuture<?> mo8284scheduleWithFixedDelay(Runnable runnable, long j, long j2, TimeUnit timeUnit);
}
