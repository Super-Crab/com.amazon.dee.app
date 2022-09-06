package com.amazon.blueshift.bluefront.android.common;

import com.google.common.base.Preconditions;
/* loaded from: classes11.dex */
public final class RequestTimeouts {
    public static final int DEFAULT_CONNECTION_TIMEOUT_MILLIS = 3000;
    public static final int DEFAULT_RESPONSE_TIMEOUT_MILLIS = 5000;
    private final int mConnectionTimeoutMillis;
    private final int mResponseTimeoutMillis;

    public RequestTimeouts() {
        this(5000, 3000);
    }

    public int getConnectionTimeoutMillis() {
        return this.mConnectionTimeoutMillis;
    }

    public int getResponseTimeoutMillis() {
        return this.mResponseTimeoutMillis;
    }

    public RequestTimeouts(int i, int i2) {
        boolean z = true;
        Preconditions.checkArgument(i > 0, "Response timeout must be positive.");
        Preconditions.checkArgument(i2 <= 0 ? false : z, "Connection timeout must be positive.");
        this.mResponseTimeoutMillis = i;
        this.mConnectionTimeoutMillis = i2;
    }
}
