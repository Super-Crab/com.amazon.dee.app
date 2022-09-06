package com.amazon.clouddrive.metrics;

import com.amazon.clouddrive.exceptions.CloudDriveException;
/* loaded from: classes11.dex */
public class MetricEvent {
    private final CloudDriveException mException;
    private final String mOperationName;
    private final int mRetryCount;
    private final long mTimeInMillis;

    public MetricEvent(String str, long j, int i, CloudDriveException cloudDriveException) {
        this.mOperationName = str;
        this.mTimeInMillis = j;
        this.mRetryCount = i;
        this.mException = cloudDriveException;
    }

    public CloudDriveException getCloudDriveException() {
        return this.mException;
    }

    public String getOperationName() {
        return this.mOperationName;
    }

    public int getRetryCount() {
        return this.mRetryCount;
    }

    public long getTimeInMillis() {
        return this.mTimeInMillis;
    }

    public boolean hasSucceeded() {
        return this.mException == null;
    }
}
