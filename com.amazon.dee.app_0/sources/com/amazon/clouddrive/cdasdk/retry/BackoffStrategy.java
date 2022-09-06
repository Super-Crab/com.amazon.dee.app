package com.amazon.clouddrive.cdasdk.retry;

import androidx.annotation.NonNull;
/* loaded from: classes11.dex */
public interface BackoffStrategy {
    long computeDelayBeforeNextRetry(@NonNull RetryContext retryContext);
}
