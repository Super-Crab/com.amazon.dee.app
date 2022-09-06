package com.amazon.clouddrive.cdasdk.retry;

import androidx.annotation.NonNull;
/* loaded from: classes11.dex */
public interface RetryPolicy {
    @NonNull
    BackoffStrategy getBackoffStrategy();

    boolean shouldRetry(@NonNull RetryContext retryContext);
}
