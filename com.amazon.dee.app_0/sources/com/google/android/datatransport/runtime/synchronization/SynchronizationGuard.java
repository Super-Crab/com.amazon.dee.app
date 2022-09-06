package com.google.android.datatransport.runtime.synchronization;

import androidx.annotation.WorkerThread;
/* compiled from: com.google.android.datatransport:transport-runtime@@2.2.0 */
@WorkerThread
/* loaded from: classes2.dex */
public interface SynchronizationGuard {

    /* compiled from: com.google.android.datatransport:transport-runtime@@2.2.0 */
    /* loaded from: classes2.dex */
    public interface CriticalSection<T> {
        T execute();
    }

    <T> T runCriticalSection(CriticalSection<T> criticalSection);
}
