package com.amazon.comms.instrumentation;
/* loaded from: classes11.dex */
public interface Clocks {
    long calculateRelativeTimestamp(long j);

    long getCurrentTimeMillis();

    long getElapsedRealtime();
}
