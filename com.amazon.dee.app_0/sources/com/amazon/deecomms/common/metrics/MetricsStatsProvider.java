package com.amazon.deecomms.common.metrics;

import androidx.annotation.Nullable;
/* loaded from: classes12.dex */
public interface MetricsStatsProvider {
    @Nullable
    Long getCallDuration();

    int getFailureCount();

    int getFaultCount();

    @Nullable
    Integer getHttpResponseCode();

    @Nullable
    String getRequestId();

    int getSuccessCount();

    int getUnknownCount();
}
