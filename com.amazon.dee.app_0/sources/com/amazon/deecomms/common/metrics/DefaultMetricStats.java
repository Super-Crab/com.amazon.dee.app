package com.amazon.deecomms.common.metrics;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.deecomms.calling.util.HttpStatusCodeFamily;
/* loaded from: classes12.dex */
public class DefaultMetricStats implements MetricsStatsProvider {
    private final Long callDuration;
    private final int failCount;
    private final int faultCount;
    private final Integer httpStatusCode;
    private final String requestId;
    private final int successCount;
    private final int unknownCount;

    public DefaultMetricStats(@NonNull Integer num, @Nullable Long l, @Nullable String str, @NonNull HttpStatusCodeFamily httpStatusCodeFamily) {
        this.httpStatusCode = num;
        this.callDuration = l;
        this.requestId = str;
        this.failCount = httpStatusCodeFamily.getFailureCount();
        this.faultCount = httpStatusCodeFamily.getFaultCount();
        this.unknownCount = httpStatusCodeFamily.getUnknownCount();
        this.successCount = httpStatusCodeFamily.getSuccessCount();
    }

    @Override // com.amazon.deecomms.common.metrics.MetricsStatsProvider
    public Long getCallDuration() {
        return this.callDuration;
    }

    @Override // com.amazon.deecomms.common.metrics.MetricsStatsProvider
    public int getFailureCount() {
        return this.failCount;
    }

    @Override // com.amazon.deecomms.common.metrics.MetricsStatsProvider
    public int getFaultCount() {
        return this.faultCount;
    }

    @Override // com.amazon.deecomms.common.metrics.MetricsStatsProvider
    public Integer getHttpResponseCode() {
        return this.httpStatusCode;
    }

    @Override // com.amazon.deecomms.common.metrics.MetricsStatsProvider
    public String getRequestId() {
        return this.requestId;
    }

    @Override // com.amazon.deecomms.common.metrics.MetricsStatsProvider
    public int getSuccessCount() {
        return this.successCount;
    }

    @Override // com.amazon.deecomms.common.metrics.MetricsStatsProvider
    public int getUnknownCount() {
        return this.unknownCount;
    }

    public DefaultMetricStats(@Nullable Integer num, @Nullable Long l, @NonNull int i, @NonNull int i2, @NonNull int i3, @NonNull int i4) {
        this.httpStatusCode = num;
        this.callDuration = l;
        this.failCount = i;
        this.faultCount = i2;
        this.unknownCount = i3;
        this.successCount = i4;
        this.requestId = null;
    }
}
