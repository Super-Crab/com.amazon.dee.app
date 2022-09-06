package com.amazon.communication;

import java.util.concurrent.TimeUnit;
/* loaded from: classes12.dex */
public interface BackoffScheduler {
    void cancel(int i);

    long getMinimumDelayMillis();

    void schedule(int i, Runnable runnable, long j, TimeUnit timeUnit);
}
