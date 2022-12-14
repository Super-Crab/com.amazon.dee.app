package com.google.android.datatransport.runtime.scheduling.persistence;

import androidx.annotation.Nullable;
import androidx.annotation.WorkerThread;
import com.google.android.datatransport.runtime.EventInternal;
import com.google.android.datatransport.runtime.TransportContext;
import java.io.Closeable;
/* compiled from: com.google.android.datatransport:transport-runtime@@2.2.0 */
@WorkerThread
/* loaded from: classes2.dex */
public interface EventStore extends Closeable {
    int cleanUp();

    long getNextCallTime(TransportContext transportContext);

    boolean hasPendingEventsFor(TransportContext transportContext);

    Iterable<TransportContext> loadActiveContexts();

    Iterable<PersistedEvent> loadBatch(TransportContext transportContext);

    @Nullable
    PersistedEvent persist(TransportContext transportContext, EventInternal eventInternal);

    void recordFailure(Iterable<PersistedEvent> iterable);

    void recordNextCallTime(TransportContext transportContext, long j);

    void recordSuccess(Iterable<PersistedEvent> iterable);
}
