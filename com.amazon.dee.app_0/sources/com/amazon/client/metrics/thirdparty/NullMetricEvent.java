package com.amazon.client.metrics.thirdparty;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
/* loaded from: classes11.dex */
public class NullMetricEvent implements MetricEvent {
    private final MetricEventType mMetricEventType;
    private final String mProgram;
    private final String mSource;

    public NullMetricEvent(String str, String str2) {
        this(str, str2, MetricEventType.getDefault());
    }

    @Override // com.amazon.client.metrics.thirdparty.MetricEvent
    public void addCounter(String str, double d) {
    }

    @Override // com.amazon.client.metrics.thirdparty.MetricEvent
    public void addDataPoint(DataPoint dataPoint) {
    }

    @Override // com.amazon.client.metrics.thirdparty.MetricEvent
    public void addDataPoints(List<DataPoint> list) {
    }

    @Override // com.amazon.client.metrics.thirdparty.MetricEvent
    public void addString(String str, String str2) {
    }

    @Override // com.amazon.client.metrics.thirdparty.MetricEvent
    public void addTimer(String str, double d) {
    }

    @Override // com.amazon.client.metrics.thirdparty.MetricEvent
    public void addTimer(String str, double d, int i) {
    }

    @Override // com.amazon.client.metrics.thirdparty.MetricEvent
    public void appendString(String str, String str2) {
    }

    @Override // com.amazon.client.metrics.thirdparty.MetricEvent
    public void clear() {
    }

    @Override // com.amazon.client.metrics.thirdparty.MetricEvent
    public boolean getAnonymous() {
        return false;
    }

    @Override // com.amazon.client.metrics.thirdparty.MetricEvent
    public List<DataPoint> getAsDataPoints() {
        return new ArrayList(0);
    }

    @Override // com.amazon.client.metrics.thirdparty.MetricEvent
    public MetricEventType getMetricEventType() {
        return this.mMetricEventType;
    }

    @Override // com.amazon.client.metrics.thirdparty.MetricEvent
    public String getNonAnonymousCustomerId() {
        return null;
    }

    @Override // com.amazon.client.metrics.thirdparty.MetricEvent
    public String getNonAnonymousSessionId() {
        return null;
    }

    @Override // com.amazon.client.metrics.thirdparty.MetricEvent
    public String getProgram() {
        return this.mProgram;
    }

    @Override // com.amazon.client.metrics.thirdparty.MetricEvent
    public String getSource() {
        return this.mSource;
    }

    @Override // com.amazon.client.metrics.thirdparty.MetricEvent
    public boolean hasDataPoints() {
        return false;
    }

    @Override // com.amazon.client.metrics.thirdparty.MetricEvent
    public void incrementCounter(String str, double d) {
    }

    @Override // com.amazon.client.metrics.thirdparty.MetricEvent
    public void removeCounter(String str) {
    }

    @Override // com.amazon.client.metrics.thirdparty.MetricEvent
    public void removeString(String str) {
    }

    @Override // com.amazon.client.metrics.thirdparty.MetricEvent
    public void removeTimer(String str) {
    }

    @Override // com.amazon.client.metrics.thirdparty.MetricEvent
    public void restoreFromMap(Map<String, String> map) throws IllegalArgumentException {
    }

    @Override // com.amazon.client.metrics.thirdparty.MetricEvent
    public void saveToMap(Map<String, String> map) {
    }

    @Override // com.amazon.client.metrics.thirdparty.MetricEvent
    public void setAnonymous(boolean z) {
    }

    @Override // com.amazon.client.metrics.thirdparty.MetricEvent
    public void setClickstreamUserAgent(String str) {
    }

    @Override // com.amazon.client.metrics.thirdparty.MetricEvent
    public void setNonAnonymousCustomerId(String str) {
    }

    @Override // com.amazon.client.metrics.thirdparty.MetricEvent
    public void setNonAnonymousSessionId(String str) {
    }

    @Override // com.amazon.client.metrics.thirdparty.MetricEvent
    public void startTimer(String str) {
    }

    @Override // com.amazon.client.metrics.thirdparty.MetricEvent
    public void stopTimer(String str) {
    }

    public NullMetricEvent(String str, String str2, MetricEventType metricEventType) {
        this.mProgram = str;
        this.mSource = str2;
        this.mMetricEventType = metricEventType;
    }
}
