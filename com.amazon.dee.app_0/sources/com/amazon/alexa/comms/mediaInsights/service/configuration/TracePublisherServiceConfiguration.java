package com.amazon.alexa.comms.mediaInsights.service.configuration;
/* loaded from: classes6.dex */
public interface TracePublisherServiceConfiguration {
    Integer getDeviceDetailsJobIntervalInMillis();

    Integer getExponentForHttpRetries();

    Long getFlushJobIntervalInMillis();

    Integer getFlushThreadPoolSize();

    Integer getHttpConnectTimeout();

    Integer getHttpReadTimeout();

    Integer getMaxNumberOfHttpRetries();

    Integer getMaxTraceTTLInMillis();

    Integer getMinimumDelayForHttpRetries();

    Integer getTotalTracesCapacityInBytes();

    String getTraceServiceHttpsEndpoint();

    Long getWakeLockTimeout();
}
