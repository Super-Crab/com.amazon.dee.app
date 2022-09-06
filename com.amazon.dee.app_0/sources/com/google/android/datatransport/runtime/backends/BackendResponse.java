package com.google.android.datatransport.runtime.backends;

import com.google.auto.value.AutoValue;
/* compiled from: com.google.android.datatransport:transport-runtime@@2.2.0 */
@AutoValue
/* loaded from: classes2.dex */
public abstract class BackendResponse {

    /* compiled from: com.google.android.datatransport:transport-runtime@@2.2.0 */
    /* loaded from: classes2.dex */
    public enum Status {
        OK,
        TRANSIENT_ERROR,
        FATAL_ERROR
    }

    public static BackendResponse fatalError() {
        return new AutoValue_BackendResponse(Status.FATAL_ERROR, -1L);
    }

    public static BackendResponse ok(long j) {
        return new AutoValue_BackendResponse(Status.OK, j);
    }

    public static BackendResponse transientError() {
        return new AutoValue_BackendResponse(Status.TRANSIENT_ERROR, -1L);
    }

    public abstract long getNextRequestWaitMillis();

    public abstract Status getStatus();
}
