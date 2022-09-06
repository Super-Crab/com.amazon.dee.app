package com.amazon.client.metrics.common;

import com.amazon.client.metrics.common.internal.android.AndroidNullMetricEvent;
import com.amazon.client.metrics.common.internal.fireos.FireOSNullMetricEvent;
import com.amazon.client.metrics.common.internal.util.DevicePlatformIdentifierUtil;
import java.util.List;
import java.util.Map;
/* loaded from: classes11.dex */
public class NullMetricEvent implements MetricEvent {
    private final MetricEvent mDelegateMetricEvent;

    public NullMetricEvent(String str, String str2) {
        this(str, str2, MetricEventType.getDefault());
    }

    @Override // com.amazon.client.metrics.common.MetricEvent
    public void addCounter(String str, double d) {
    }

    @Override // com.amazon.client.metrics.common.MetricEvent
    public void addDataPoint(DataPoint dataPoint) throws MetricsException {
    }

    @Override // com.amazon.client.metrics.common.MetricEvent
    public void addDataPoints(List<DataPoint> list) throws MetricsException {
    }

    @Override // com.amazon.client.metrics.common.MetricEvent
    public void addString(String str, String str2) {
    }

    @Override // com.amazon.client.metrics.common.MetricEvent
    public void addTimer(String str, double d) {
    }

    @Override // com.amazon.client.metrics.common.MetricEvent
    public void addTimer(String str, double d, int i) {
    }

    @Override // com.amazon.client.metrics.common.MetricEvent
    public void appendString(String str, String str2) {
    }

    @Override // com.amazon.client.metrics.common.MetricEvent
    public void clear() {
    }

    @Override // com.amazon.client.metrics.common.MetricEvent
    public boolean getAnonymous() {
        return this.mDelegateMetricEvent.getAnonymous();
    }

    @Override // com.amazon.client.metrics.common.MetricEvent
    public List<DataPoint> getAsDataPoints() {
        return this.mDelegateMetricEvent.getAsDataPoints();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: getDelegateMetricEvent */
    public MetricEvent mo2877getDelegateMetricEvent() {
        return this.mDelegateMetricEvent;
    }

    @Override // com.amazon.client.metrics.common.MetricEvent
    public MetricEventType getMetricEventType() {
        return this.mDelegateMetricEvent.getMetricEventType();
    }

    @Override // com.amazon.client.metrics.common.MetricEvent
    public String getNonAnonymousCustomerId() {
        return this.mDelegateMetricEvent.getNonAnonymousCustomerId();
    }

    @Override // com.amazon.client.metrics.common.MetricEvent
    public String getNonAnonymousSessionId() {
        return this.mDelegateMetricEvent.getNonAnonymousSessionId();
    }

    @Override // com.amazon.client.metrics.common.MetricEvent
    public String getProgram() {
        return this.mDelegateMetricEvent.getProgram();
    }

    @Override // com.amazon.client.metrics.common.MetricEvent
    public String getSource() {
        return this.mDelegateMetricEvent.getSource();
    }

    @Override // com.amazon.client.metrics.common.MetricEvent
    public boolean hasDataPoints() {
        return this.mDelegateMetricEvent.hasDataPoints();
    }

    @Override // com.amazon.client.metrics.common.MetricEvent
    public void incrementCounter(String str, double d) {
    }

    @Override // com.amazon.client.metrics.common.MetricEvent
    public void removeCounter(String str) {
    }

    @Override // com.amazon.client.metrics.common.MetricEvent
    public void removeString(String str) {
    }

    @Override // com.amazon.client.metrics.common.MetricEvent
    public void removeTimer(String str) {
    }

    @Override // com.amazon.client.metrics.common.MetricEvent
    public void restoreFromMap(Map<String, String> map) throws IllegalArgumentException {
    }

    @Override // com.amazon.client.metrics.common.MetricEvent
    public void saveToMap(Map<String, String> map) {
    }

    @Override // com.amazon.client.metrics.common.MetricEvent
    public void setAnonymous(boolean z) {
    }

    @Override // com.amazon.client.metrics.common.MetricEvent
    public void setClickstreamUserAgent(String str) {
    }

    @Override // com.amazon.client.metrics.common.MetricEvent
    public void setNonAnonymousCustomerId(String str) {
    }

    @Override // com.amazon.client.metrics.common.MetricEvent
    public void setNonAnonymousSessionId(String str) {
    }

    @Override // com.amazon.client.metrics.common.MetricEvent
    public void startTimer(String str) {
    }

    @Override // com.amazon.client.metrics.common.MetricEvent
    public void stopTimer(String str) {
    }

    public NullMetricEvent(String str, String str2, MetricEventType metricEventType) {
        if (DevicePlatformIdentifierUtil.getInstance().isDevicePlatformFireOS()) {
            this.mDelegateMetricEvent = new FireOSNullMetricEvent(new com.amazon.client.metrics.NullMetricEvent(str, str2, com.amazon.client.metrics.MetricEventType.valueOf(metricEventType.name())));
        } else {
            this.mDelegateMetricEvent = new AndroidNullMetricEvent(new com.amazon.client.metrics.thirdparty.NullMetricEvent(str, str2, com.amazon.client.metrics.thirdparty.MetricEventType.valueOf(metricEventType.name())));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public NullMetricEvent(com.amazon.client.metrics.NullMetricEvent nullMetricEvent) {
        if (nullMetricEvent != null) {
            this.mDelegateMetricEvent = new FireOSNullMetricEvent(nullMetricEvent);
            return;
        }
        throw new NullPointerException("FirstParty NullMetricEvent may not be null");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public NullMetricEvent(com.amazon.client.metrics.thirdparty.NullMetricEvent nullMetricEvent) {
        if (nullMetricEvent != null) {
            this.mDelegateMetricEvent = new AndroidNullMetricEvent(nullMetricEvent);
            return;
        }
        throw new NullPointerException("ThirdParty NullMetricEvent may not be null");
    }
}
