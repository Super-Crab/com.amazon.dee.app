package com.amazon.deecomms.common.network;

import androidx.annotation.Nullable;
import com.amazon.deecomms.calling.util.HttpStatusCodeFamily;
import com.amazon.deecomms.common.metrics.MetricsStatsProvider;
/* loaded from: classes12.dex */
public class ServiceException extends Exception implements MetricsStatsProvider {
    private final Long callDuration;
    private final String clientId;
    private final HttpStatusCodeFamily family;
    private final Integer httpResponseCode;
    private final String requestId;

    public ServiceException() {
        this.httpResponseCode = null;
        this.requestId = null;
        this.clientId = null;
        this.callDuration = null;
        this.family = HttpStatusCodeFamily.familyFromStatusCode(null);
    }

    @Override // com.amazon.deecomms.common.metrics.MetricsStatsProvider
    public Long getCallDuration() {
        return this.callDuration;
    }

    @Nullable
    public String getClientId() {
        return this.clientId;
    }

    @Override // com.amazon.deecomms.common.metrics.MetricsStatsProvider
    public int getFailureCount() {
        return this.family.getFailureCount();
    }

    @Override // com.amazon.deecomms.common.metrics.MetricsStatsProvider
    public int getFaultCount() {
        return this.family.getFaultCount();
    }

    @Override // com.amazon.deecomms.common.metrics.MetricsStatsProvider
    public Integer getHttpResponseCode() {
        return this.httpResponseCode;
    }

    @Override // com.amazon.deecomms.common.metrics.MetricsStatsProvider
    public String getRequestId() {
        return this.requestId;
    }

    @Override // com.amazon.deecomms.common.metrics.MetricsStatsProvider
    public int getSuccessCount() {
        return this.family.getSuccessCount();
    }

    @Override // com.amazon.deecomms.common.metrics.MetricsStatsProvider
    public int getUnknownCount() {
        return this.family.getUnknownCount();
    }

    public ServiceException(String str) {
        super(str);
        this.httpResponseCode = null;
        this.requestId = null;
        this.clientId = null;
        this.callDuration = null;
        this.family = HttpStatusCodeFamily.familyFromStatusCode(null);
    }

    public ServiceException(String str, Throwable th) {
        super(str, th);
        this.httpResponseCode = null;
        this.requestId = null;
        this.clientId = null;
        this.callDuration = null;
        this.family = HttpStatusCodeFamily.familyFromStatusCode(null);
    }

    public ServiceException(Throwable th) {
        super(th);
        this.httpResponseCode = null;
        this.requestId = null;
        this.clientId = null;
        this.callDuration = null;
        this.family = HttpStatusCodeFamily.familyFromStatusCode(null);
    }

    public ServiceException(String str, int i, String str2, String str3) {
        super(str);
        this.httpResponseCode = Integer.valueOf(i);
        this.requestId = str2;
        this.clientId = str3;
        this.callDuration = null;
        this.family = HttpStatusCodeFamily.familyFromStatusCode(Integer.valueOf(i));
    }

    public ServiceException(String str, int i, String str2, String str3, Long l) {
        super(str);
        this.httpResponseCode = Integer.valueOf(i);
        this.requestId = str2;
        this.clientId = str3;
        this.callDuration = l;
        this.family = HttpStatusCodeFamily.familyFromStatusCode(Integer.valueOf(i));
    }
}
