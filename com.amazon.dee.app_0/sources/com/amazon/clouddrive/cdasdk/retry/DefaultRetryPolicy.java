package com.amazon.clouddrive.cdasdk.retry;

import androidx.annotation.NonNull;
import java.net.ConnectException;
/* loaded from: classes11.dex */
public class DefaultRetryPolicy implements RetryPolicy {
    @NonNull
    private final BackoffStrategy backoffStrategy;
    private final int maxRetries;

    public DefaultRetryPolicy(@NonNull BackoffStrategy backoffStrategy, int i) {
        this.backoffStrategy = backoffStrategy;
        this.maxRetries = i;
    }

    @Override // com.amazon.clouddrive.cdasdk.retry.RetryPolicy
    @NonNull
    public BackoffStrategy getBackoffStrategy() {
        return this.backoffStrategy;
    }

    @Override // com.amazon.clouddrive.cdasdk.retry.RetryPolicy
    public boolean shouldRetry(@NonNull RetryContext retryContext) {
        if (retryContext.getNumRetriesAttempted() >= this.maxRetries) {
            return false;
        }
        if (retryContext.getException() != null) {
            return retryContext.getException() instanceof ConnectException;
        }
        if (retryContext.getResponse() == null) {
            return false;
        }
        return RetryUtil.isRetryableError(retryContext.getResponse()) || RetryUtil.isThrottlingError(retryContext.getResponse());
    }
}
