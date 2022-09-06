package com.amazon.clouddrive.cdasdk.retry;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.clouddrive.cdasdk.util.SystemUtil;
/* loaded from: classes11.dex */
public class ExponentialJitterBackoffStrategy implements BackoffStrategy {
    private static final int DEFAULT_BASE_DELAY = 1000;
    private static final long DEFAULT_MAX_BACKOFF_TIME = 20000;
    private final int baseDelay;
    private final BackoffStrategy equalJitterStrategy;
    private final BackoffStrategy fullJitterStrategy;
    private final long maxBackoffTime;
    private final SystemUtil systemUtil;

    /* loaded from: classes11.dex */
    class EqualJitterBackoffStrategy implements BackoffStrategy {
        EqualJitterBackoffStrategy() {
        }

        @Override // com.amazon.clouddrive.cdasdk.retry.BackoffStrategy
        public long computeDelayBeforeNextRetry(@NonNull RetryContext retryContext) {
            int calculateExponentialDelay = ExponentialJitterBackoffStrategy.calculateExponentialDelay(retryContext.getNumRetriesAttempted(), ExponentialJitterBackoffStrategy.this.baseDelay, ExponentialJitterBackoffStrategy.this.maxBackoffTime) / 2;
            return ExponentialJitterBackoffStrategy.this.systemUtil.randomNextInt(calculateExponentialDelay + 1) + calculateExponentialDelay;
        }
    }

    /* loaded from: classes11.dex */
    class FullJitterBackoffStrategy implements BackoffStrategy {
        FullJitterBackoffStrategy() {
        }

        @Override // com.amazon.clouddrive.cdasdk.retry.BackoffStrategy
        public long computeDelayBeforeNextRetry(@NonNull RetryContext retryContext) {
            return ExponentialJitterBackoffStrategy.this.systemUtil.randomNextInt(ExponentialJitterBackoffStrategy.calculateExponentialDelay(retryContext.getNumRetriesAttempted(), ExponentialJitterBackoffStrategy.this.baseDelay, ExponentialJitterBackoffStrategy.this.maxBackoffTime));
        }
    }

    public ExponentialJitterBackoffStrategy(@NonNull SystemUtil systemUtil) {
        this.fullJitterStrategy = new FullJitterBackoffStrategy();
        this.equalJitterStrategy = new EqualJitterBackoffStrategy();
        this.baseDelay = 1000;
        this.maxBackoffTime = 20000L;
        this.systemUtil = systemUtil;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int calculateExponentialDelay(int i, int i2, long j) {
        return (int) Math.min((1 << i) * i2, j);
    }

    @Override // com.amazon.clouddrive.cdasdk.retry.BackoffStrategy
    public long computeDelayBeforeNextRetry(@NonNull RetryContext retryContext) {
        if (retryContext.getResponse() != null && RetryUtil.isThrottlingError(retryContext.getResponse())) {
            return this.equalJitterStrategy.computeDelayBeforeNextRetry(retryContext);
        }
        return this.fullJitterStrategy.computeDelayBeforeNextRetry(retryContext);
    }

    @VisibleForTesting
    ExponentialJitterBackoffStrategy(@NonNull SystemUtil systemUtil, int i, long j) {
        this.fullJitterStrategy = new FullJitterBackoffStrategy();
        this.equalJitterStrategy = new EqualJitterBackoffStrategy();
        this.baseDelay = i;
        this.maxBackoffTime = j;
        this.systemUtil = systemUtil;
    }
}
