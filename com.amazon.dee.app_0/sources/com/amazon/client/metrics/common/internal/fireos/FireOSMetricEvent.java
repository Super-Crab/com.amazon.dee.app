package com.amazon.client.metrics.common.internal.fireos;

import com.amazon.client.metrics.common.DataPoint;
import com.amazon.client.metrics.common.DataPointConverter;
import com.amazon.client.metrics.common.MetricEvent;
import com.amazon.client.metrics.common.MetricEventType;
import com.amazon.client.metrics.common.MetricsException;
import java.util.List;
import java.util.Map;
/* loaded from: classes11.dex */
public class FireOSMetricEvent implements MetricEvent {
    private final com.amazon.client.metrics.MetricEvent mDelegateFirstPartyMetricEvent;

    public FireOSMetricEvent(com.amazon.client.metrics.MetricEvent metricEvent) {
        if (metricEvent != null) {
            this.mDelegateFirstPartyMetricEvent = metricEvent;
            return;
        }
        throw new NullPointerException("DelegateMetricEvent may not be null");
    }

    @Override // com.amazon.client.metrics.common.MetricEvent
    public void addCounter(String str, double d) {
        this.mDelegateFirstPartyMetricEvent.addCounter(str, d);
    }

    @Override // com.amazon.client.metrics.common.MetricEvent
    public void addDataPoint(DataPoint dataPoint) throws MetricsException {
        try {
            this.mDelegateFirstPartyMetricEvent.addDataPoint(DataPointConverter.convertCommonToFirstParty(dataPoint));
        } catch (com.amazon.client.metrics.MetricsException e) {
            throw new MetricsException((Throwable) e);
        }
    }

    @Override // com.amazon.client.metrics.common.MetricEvent
    public void addDataPoints(List<DataPoint> list) throws MetricsException {
        try {
            this.mDelegateFirstPartyMetricEvent.addDataPoints(DataPointConverter.convertCommonToFirstParty(list));
        } catch (com.amazon.client.metrics.MetricsException e) {
            throw new MetricsException((Throwable) e);
        }
    }

    @Override // com.amazon.client.metrics.common.MetricEvent
    public void addString(String str, String str2) {
        this.mDelegateFirstPartyMetricEvent.addString(str, str2);
    }

    @Override // com.amazon.client.metrics.common.MetricEvent
    public void addTimer(String str, double d) {
        this.mDelegateFirstPartyMetricEvent.addTimer(str, d);
    }

    @Override // com.amazon.client.metrics.common.MetricEvent
    public void appendString(String str, String str2) {
        this.mDelegateFirstPartyMetricEvent.appendString(str, str2);
    }

    @Override // com.amazon.client.metrics.common.MetricEvent
    public void clear() {
        this.mDelegateFirstPartyMetricEvent.clear();
    }

    @Override // com.amazon.client.metrics.common.MetricEvent
    public boolean getAnonymous() {
        return this.mDelegateFirstPartyMetricEvent.getAnonymous();
    }

    @Override // com.amazon.client.metrics.common.MetricEvent
    public List<DataPoint> getAsDataPoints() {
        return DataPointConverter.convertFirstPartyToCommon(this.mDelegateFirstPartyMetricEvent.getAsDataPoints());
    }

    /* renamed from: getDelegateMetricEvent */
    public com.amazon.client.metrics.MetricEvent mo2886getDelegateMetricEvent() {
        return this.mDelegateFirstPartyMetricEvent;
    }

    @Override // com.amazon.client.metrics.common.MetricEvent
    public MetricEventType getMetricEventType() {
        return MetricEventType.valueOf(this.mDelegateFirstPartyMetricEvent.getMetricEventType().name());
    }

    @Override // com.amazon.client.metrics.common.MetricEvent
    public String getNonAnonymousCustomerId() {
        return this.mDelegateFirstPartyMetricEvent.getNonAnonymousCustomerId();
    }

    @Override // com.amazon.client.metrics.common.MetricEvent
    public String getNonAnonymousSessionId() {
        return this.mDelegateFirstPartyMetricEvent.getNonAnonymousSessionId();
    }

    @Override // com.amazon.client.metrics.common.MetricEvent
    public String getProgram() {
        return this.mDelegateFirstPartyMetricEvent.getProgram();
    }

    @Override // com.amazon.client.metrics.common.MetricEvent
    public String getSource() {
        return this.mDelegateFirstPartyMetricEvent.getSource();
    }

    @Override // com.amazon.client.metrics.common.MetricEvent
    @Deprecated
    public boolean hasDataPoints() {
        return this.mDelegateFirstPartyMetricEvent.hasDataPoints();
    }

    @Override // com.amazon.client.metrics.common.MetricEvent
    public void incrementCounter(String str, double d) {
        this.mDelegateFirstPartyMetricEvent.incrementCounter(str, d);
    }

    @Override // com.amazon.client.metrics.common.MetricEvent
    public void removeCounter(String str) {
        this.mDelegateFirstPartyMetricEvent.removeCounter(str);
    }

    @Override // com.amazon.client.metrics.common.MetricEvent
    public void removeString(String str) {
        this.mDelegateFirstPartyMetricEvent.removeString(str);
    }

    @Override // com.amazon.client.metrics.common.MetricEvent
    public void removeTimer(String str) {
        this.mDelegateFirstPartyMetricEvent.removeTimer(str);
    }

    @Override // com.amazon.client.metrics.common.MetricEvent
    public void restoreFromMap(Map<String, String> map) throws IllegalArgumentException {
        this.mDelegateFirstPartyMetricEvent.restoreFromMap(map);
    }

    @Override // com.amazon.client.metrics.common.MetricEvent
    public void saveToMap(Map<String, String> map) {
        this.mDelegateFirstPartyMetricEvent.saveToMap(map);
    }

    @Override // com.amazon.client.metrics.common.MetricEvent
    public void setAnonymous(boolean z) {
        this.mDelegateFirstPartyMetricEvent.setAnonymous(z);
    }

    @Override // com.amazon.client.metrics.common.MetricEvent
    @Deprecated
    public void setClickstreamUserAgent(String str) {
        this.mDelegateFirstPartyMetricEvent.setClickstreamUserAgent(str);
    }

    @Override // com.amazon.client.metrics.common.MetricEvent
    public void setNonAnonymousCustomerId(String str) {
        this.mDelegateFirstPartyMetricEvent.setNonAnonymousCustomerId(str);
    }

    @Override // com.amazon.client.metrics.common.MetricEvent
    public void setNonAnonymousSessionId(String str) {
        this.mDelegateFirstPartyMetricEvent.setNonAnonymousSessionId(str);
    }

    @Override // com.amazon.client.metrics.common.MetricEvent
    public void startTimer(String str) {
        this.mDelegateFirstPartyMetricEvent.startTimer(str);
    }

    @Override // com.amazon.client.metrics.common.MetricEvent
    public void stopTimer(String str) {
        this.mDelegateFirstPartyMetricEvent.stopTimer(str);
    }

    public String toString() {
        return this.mDelegateFirstPartyMetricEvent.toString();
    }

    @Override // com.amazon.client.metrics.common.MetricEvent
    public void addTimer(String str, double d, int i) {
        this.mDelegateFirstPartyMetricEvent.addTimer(str, d, i);
    }
}
