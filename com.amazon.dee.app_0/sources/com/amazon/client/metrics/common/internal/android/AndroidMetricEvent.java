package com.amazon.client.metrics.common.internal.android;

import com.amazon.client.metrics.common.DataPoint;
import com.amazon.client.metrics.common.DataPointConverter;
import com.amazon.client.metrics.common.MetricEvent;
import com.amazon.client.metrics.common.MetricEventType;
import com.amazon.client.metrics.common.MetricsException;
import java.util.List;
import java.util.Map;
/* loaded from: classes11.dex */
public class AndroidMetricEvent implements MetricEvent {
    private final com.amazon.client.metrics.thirdparty.MetricEvent mThirdPartyDelegateMetricEvent;

    public AndroidMetricEvent(com.amazon.client.metrics.thirdparty.MetricEvent metricEvent) {
        if (metricEvent != null) {
            this.mThirdPartyDelegateMetricEvent = metricEvent;
            return;
        }
        throw new NullPointerException("DelegateMetricEvent may not be null");
    }

    @Override // com.amazon.client.metrics.common.MetricEvent
    public void addCounter(String str, double d) {
        this.mThirdPartyDelegateMetricEvent.addCounter(str, d);
    }

    @Override // com.amazon.client.metrics.common.MetricEvent
    public void addDataPoint(DataPoint dataPoint) throws MetricsException {
        try {
            this.mThirdPartyDelegateMetricEvent.addDataPoint(DataPointConverter.convertCommonToThirdParty(dataPoint));
        } catch (com.amazon.client.metrics.thirdparty.MetricsException e) {
            throw new MetricsException(e);
        }
    }

    @Override // com.amazon.client.metrics.common.MetricEvent
    public void addDataPoints(List<DataPoint> list) throws MetricsException {
        try {
            this.mThirdPartyDelegateMetricEvent.addDataPoints(DataPointConverter.convertCommonToThirdParty(list));
        } catch (com.amazon.client.metrics.thirdparty.MetricsException e) {
            throw new MetricsException(e);
        }
    }

    @Override // com.amazon.client.metrics.common.MetricEvent
    public void addString(String str, String str2) {
        this.mThirdPartyDelegateMetricEvent.addString(str, str2);
    }

    @Override // com.amazon.client.metrics.common.MetricEvent
    public void addTimer(String str, double d) {
        this.mThirdPartyDelegateMetricEvent.addTimer(str, d);
    }

    @Override // com.amazon.client.metrics.common.MetricEvent
    public void appendString(String str, String str2) {
        this.mThirdPartyDelegateMetricEvent.appendString(str, str2);
    }

    @Override // com.amazon.client.metrics.common.MetricEvent
    public void clear() {
        this.mThirdPartyDelegateMetricEvent.clear();
    }

    @Override // com.amazon.client.metrics.common.MetricEvent
    public boolean getAnonymous() {
        return this.mThirdPartyDelegateMetricEvent.getAnonymous();
    }

    @Override // com.amazon.client.metrics.common.MetricEvent
    public List<DataPoint> getAsDataPoints() {
        return DataPointConverter.convertThirdPartyToCommon(this.mThirdPartyDelegateMetricEvent.getAsDataPoints());
    }

    /* renamed from: getDelegateMetricEvent */
    public com.amazon.client.metrics.thirdparty.MetricEvent mo2883getDelegateMetricEvent() {
        return this.mThirdPartyDelegateMetricEvent;
    }

    @Override // com.amazon.client.metrics.common.MetricEvent
    public MetricEventType getMetricEventType() {
        return MetricEventType.valueOf(this.mThirdPartyDelegateMetricEvent.getMetricEventType().name());
    }

    @Override // com.amazon.client.metrics.common.MetricEvent
    public String getNonAnonymousCustomerId() {
        return this.mThirdPartyDelegateMetricEvent.getNonAnonymousCustomerId();
    }

    @Override // com.amazon.client.metrics.common.MetricEvent
    public String getNonAnonymousSessionId() {
        return this.mThirdPartyDelegateMetricEvent.getNonAnonymousSessionId();
    }

    @Override // com.amazon.client.metrics.common.MetricEvent
    public String getProgram() {
        return this.mThirdPartyDelegateMetricEvent.getProgram();
    }

    @Override // com.amazon.client.metrics.common.MetricEvent
    public String getSource() {
        return this.mThirdPartyDelegateMetricEvent.getSource();
    }

    @Override // com.amazon.client.metrics.common.MetricEvent
    @Deprecated
    public boolean hasDataPoints() {
        return this.mThirdPartyDelegateMetricEvent.hasDataPoints();
    }

    @Override // com.amazon.client.metrics.common.MetricEvent
    public void incrementCounter(String str, double d) {
        this.mThirdPartyDelegateMetricEvent.incrementCounter(str, d);
    }

    @Override // com.amazon.client.metrics.common.MetricEvent
    public void removeCounter(String str) {
        this.mThirdPartyDelegateMetricEvent.removeCounter(str);
    }

    @Override // com.amazon.client.metrics.common.MetricEvent
    public void removeString(String str) {
        this.mThirdPartyDelegateMetricEvent.removeString(str);
    }

    @Override // com.amazon.client.metrics.common.MetricEvent
    public void removeTimer(String str) {
        this.mThirdPartyDelegateMetricEvent.removeTimer(str);
    }

    @Override // com.amazon.client.metrics.common.MetricEvent
    public void restoreFromMap(Map<String, String> map) throws IllegalArgumentException {
        this.mThirdPartyDelegateMetricEvent.restoreFromMap(map);
    }

    @Override // com.amazon.client.metrics.common.MetricEvent
    public void saveToMap(Map<String, String> map) {
        this.mThirdPartyDelegateMetricEvent.saveToMap(map);
    }

    @Override // com.amazon.client.metrics.common.MetricEvent
    public void setAnonymous(boolean z) {
        this.mThirdPartyDelegateMetricEvent.setAnonymous(z);
    }

    @Override // com.amazon.client.metrics.common.MetricEvent
    @Deprecated
    public void setClickstreamUserAgent(String str) {
        this.mThirdPartyDelegateMetricEvent.setClickstreamUserAgent(str);
    }

    @Override // com.amazon.client.metrics.common.MetricEvent
    public void setNonAnonymousCustomerId(String str) {
        this.mThirdPartyDelegateMetricEvent.setNonAnonymousCustomerId(str);
    }

    @Override // com.amazon.client.metrics.common.MetricEvent
    public void setNonAnonymousSessionId(String str) {
        this.mThirdPartyDelegateMetricEvent.setNonAnonymousSessionId(str);
    }

    @Override // com.amazon.client.metrics.common.MetricEvent
    public void startTimer(String str) {
        this.mThirdPartyDelegateMetricEvent.startTimer(str);
    }

    @Override // com.amazon.client.metrics.common.MetricEvent
    public void stopTimer(String str) {
        this.mThirdPartyDelegateMetricEvent.stopTimer(str);
    }

    public String toString() {
        return this.mThirdPartyDelegateMetricEvent.toString();
    }

    @Override // com.amazon.client.metrics.common.MetricEvent
    public void addTimer(String str, double d, int i) {
        this.mThirdPartyDelegateMetricEvent.addTimer(str, d, i);
    }
}
