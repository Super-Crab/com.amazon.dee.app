package com.amazon.client.metrics.common.internal.fireos;

import com.amazon.client.metrics.NullMetricEvent;
import com.amazon.client.metrics.common.DataPoint;
import com.amazon.client.metrics.common.DataPointConverter;
import com.amazon.client.metrics.common.MetricEventType;
import com.amazon.client.metrics.common.MetricsException;
import java.util.List;
import java.util.Map;
/* loaded from: classes11.dex */
public class FireOSNullMetricEvent extends FireOSMetricEvent {
    private final NullMetricEvent mDelegateFirstPartyNullMetricEvent;

    public FireOSNullMetricEvent(NullMetricEvent nullMetricEvent) {
        super(nullMetricEvent);
        this.mDelegateFirstPartyNullMetricEvent = nullMetricEvent;
    }

    @Override // com.amazon.client.metrics.common.internal.fireos.FireOSMetricEvent, com.amazon.client.metrics.common.MetricEvent
    public void addCounter(String str, double d) {
    }

    @Override // com.amazon.client.metrics.common.internal.fireos.FireOSMetricEvent, com.amazon.client.metrics.common.MetricEvent
    public void addDataPoint(DataPoint dataPoint) throws MetricsException {
    }

    @Override // com.amazon.client.metrics.common.internal.fireos.FireOSMetricEvent, com.amazon.client.metrics.common.MetricEvent
    public void addDataPoints(List<DataPoint> list) throws MetricsException {
    }

    @Override // com.amazon.client.metrics.common.internal.fireos.FireOSMetricEvent, com.amazon.client.metrics.common.MetricEvent
    public void addString(String str, String str2) {
    }

    @Override // com.amazon.client.metrics.common.internal.fireos.FireOSMetricEvent, com.amazon.client.metrics.common.MetricEvent
    public void addTimer(String str, double d) {
    }

    @Override // com.amazon.client.metrics.common.internal.fireos.FireOSMetricEvent, com.amazon.client.metrics.common.MetricEvent
    public void addTimer(String str, double d, int i) {
    }

    @Override // com.amazon.client.metrics.common.internal.fireos.FireOSMetricEvent, com.amazon.client.metrics.common.MetricEvent
    public void appendString(String str, String str2) {
    }

    @Override // com.amazon.client.metrics.common.internal.fireos.FireOSMetricEvent, com.amazon.client.metrics.common.MetricEvent
    public void clear() {
    }

    @Override // com.amazon.client.metrics.common.internal.fireos.FireOSMetricEvent, com.amazon.client.metrics.common.MetricEvent
    public boolean getAnonymous() {
        return this.mDelegateFirstPartyNullMetricEvent.getAnonymous();
    }

    @Override // com.amazon.client.metrics.common.internal.fireos.FireOSMetricEvent, com.amazon.client.metrics.common.MetricEvent
    public List<DataPoint> getAsDataPoints() {
        return DataPointConverter.convertFirstPartyToCommon(this.mDelegateFirstPartyNullMetricEvent.getAsDataPoints());
    }

    @Override // com.amazon.client.metrics.common.internal.fireos.FireOSMetricEvent, com.amazon.client.metrics.common.MetricEvent
    public MetricEventType getMetricEventType() {
        return MetricEventType.valueOf(this.mDelegateFirstPartyNullMetricEvent.getMetricEventType().name());
    }

    @Override // com.amazon.client.metrics.common.internal.fireos.FireOSMetricEvent, com.amazon.client.metrics.common.MetricEvent
    public String getNonAnonymousCustomerId() {
        return this.mDelegateFirstPartyNullMetricEvent.getNonAnonymousCustomerId();
    }

    @Override // com.amazon.client.metrics.common.internal.fireos.FireOSMetricEvent, com.amazon.client.metrics.common.MetricEvent
    public String getNonAnonymousSessionId() {
        return this.mDelegateFirstPartyNullMetricEvent.getNonAnonymousSessionId();
    }

    @Override // com.amazon.client.metrics.common.internal.fireos.FireOSMetricEvent, com.amazon.client.metrics.common.MetricEvent
    public String getProgram() {
        return this.mDelegateFirstPartyNullMetricEvent.getProgram();
    }

    @Override // com.amazon.client.metrics.common.internal.fireos.FireOSMetricEvent, com.amazon.client.metrics.common.MetricEvent
    public String getSource() {
        return this.mDelegateFirstPartyNullMetricEvent.getSource();
    }

    @Override // com.amazon.client.metrics.common.internal.fireos.FireOSMetricEvent, com.amazon.client.metrics.common.MetricEvent
    public boolean hasDataPoints() {
        return this.mDelegateFirstPartyNullMetricEvent.hasDataPoints();
    }

    @Override // com.amazon.client.metrics.common.internal.fireos.FireOSMetricEvent, com.amazon.client.metrics.common.MetricEvent
    public void incrementCounter(String str, double d) {
    }

    @Override // com.amazon.client.metrics.common.internal.fireos.FireOSMetricEvent, com.amazon.client.metrics.common.MetricEvent
    public void removeCounter(String str) {
    }

    @Override // com.amazon.client.metrics.common.internal.fireos.FireOSMetricEvent, com.amazon.client.metrics.common.MetricEvent
    public void removeString(String str) {
    }

    @Override // com.amazon.client.metrics.common.internal.fireos.FireOSMetricEvent, com.amazon.client.metrics.common.MetricEvent
    public void removeTimer(String str) {
    }

    @Override // com.amazon.client.metrics.common.internal.fireos.FireOSMetricEvent, com.amazon.client.metrics.common.MetricEvent
    public void restoreFromMap(Map<String, String> map) throws IllegalArgumentException {
    }

    @Override // com.amazon.client.metrics.common.internal.fireos.FireOSMetricEvent, com.amazon.client.metrics.common.MetricEvent
    public void saveToMap(Map<String, String> map) {
    }

    @Override // com.amazon.client.metrics.common.internal.fireos.FireOSMetricEvent, com.amazon.client.metrics.common.MetricEvent
    public void setAnonymous(boolean z) {
    }

    @Override // com.amazon.client.metrics.common.internal.fireos.FireOSMetricEvent, com.amazon.client.metrics.common.MetricEvent
    public void setClickstreamUserAgent(String str) {
    }

    @Override // com.amazon.client.metrics.common.internal.fireos.FireOSMetricEvent, com.amazon.client.metrics.common.MetricEvent
    public void setNonAnonymousCustomerId(String str) {
    }

    @Override // com.amazon.client.metrics.common.internal.fireos.FireOSMetricEvent, com.amazon.client.metrics.common.MetricEvent
    public void setNonAnonymousSessionId(String str) {
    }

    @Override // com.amazon.client.metrics.common.internal.fireos.FireOSMetricEvent, com.amazon.client.metrics.common.MetricEvent
    public void startTimer(String str) {
    }

    @Override // com.amazon.client.metrics.common.internal.fireos.FireOSMetricEvent, com.amazon.client.metrics.common.MetricEvent
    public void stopTimer(String str) {
    }

    @Override // com.amazon.client.metrics.common.internal.fireos.FireOSMetricEvent
    /* renamed from: getDelegateMetricEvent  reason: collision with other method in class */
    public NullMetricEvent mo2886getDelegateMetricEvent() {
        return this.mDelegateFirstPartyNullMetricEvent;
    }
}
