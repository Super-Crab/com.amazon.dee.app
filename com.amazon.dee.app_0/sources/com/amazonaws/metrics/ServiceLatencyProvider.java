package com.amazonaws.metrics;

import com.amazonaws.logging.LogFactory;
import com.amazonaws.util.TimingInfo;
/* loaded from: classes13.dex */
public class ServiceLatencyProvider {
    private final ServiceMetricType serviceMetricType;
    private final long startNano = System.nanoTime();
    private long endNano = this.startNano;

    public ServiceLatencyProvider(ServiceMetricType serviceMetricType) {
        this.serviceMetricType = serviceMetricType;
    }

    public ServiceLatencyProvider endTiming() {
        if (this.endNano == this.startNano) {
            this.endNano = System.nanoTime();
            return this;
        }
        throw new IllegalStateException();
    }

    public double getDurationMilli() {
        if (this.endNano == this.startNano) {
            LogFactory.getLog(ServiceLatencyProvider.class).debug("Likely to be a missing invocation of endTiming().");
        }
        return TimingInfo.durationMilliOf(this.startNano, this.endNano);
    }

    public String getProviderId() {
        return super.toString();
    }

    public ServiceMetricType getServiceMetricType() {
        return this.serviceMetricType;
    }

    public String toString() {
        return String.format("providerId=%s, serviceMetricType=%s, startNano=%d, endNano=%d", getProviderId(), this.serviceMetricType, Long.valueOf(this.startNano), Long.valueOf(this.endNano));
    }
}
