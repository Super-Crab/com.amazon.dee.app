package com.amazon.client.metrics.common.internal.android;

import com.amazon.client.metrics.common.DataPoint;
import com.amazon.client.metrics.common.DataPointConverter;
import com.amazon.client.metrics.common.MetricEventType;
import com.amazon.client.metrics.common.MetricsException;
import com.amazon.client.metrics.thirdparty.NullMetricEvent;
import java.util.List;
import java.util.Map;
/* loaded from: classes11.dex */
public class AndroidNullMetricEvent extends AndroidMetricEvent {
    private final NullMetricEvent mDelegateThirdPartyNullMetricEvent;

    public AndroidNullMetricEvent(NullMetricEvent nullMetricEvent) {
        super(nullMetricEvent);
        this.mDelegateThirdPartyNullMetricEvent = nullMetricEvent;
    }

    @Override // com.amazon.client.metrics.common.internal.android.AndroidMetricEvent, com.amazon.client.metrics.common.MetricEvent
    public void addCounter(String str, double d) {
    }

    @Override // com.amazon.client.metrics.common.internal.android.AndroidMetricEvent, com.amazon.client.metrics.common.MetricEvent
    public void addDataPoint(DataPoint dataPoint) throws MetricsException {
    }

    @Override // com.amazon.client.metrics.common.internal.android.AndroidMetricEvent, com.amazon.client.metrics.common.MetricEvent
    public void addDataPoints(List<DataPoint> list) throws MetricsException {
    }

    @Override // com.amazon.client.metrics.common.internal.android.AndroidMetricEvent, com.amazon.client.metrics.common.MetricEvent
    public void addString(String str, String str2) {
    }

    @Override // com.amazon.client.metrics.common.internal.android.AndroidMetricEvent, com.amazon.client.metrics.common.MetricEvent
    public void addTimer(String str, double d) {
    }

    @Override // com.amazon.client.metrics.common.internal.android.AndroidMetricEvent, com.amazon.client.metrics.common.MetricEvent
    public void addTimer(String str, double d, int i) {
    }

    @Override // com.amazon.client.metrics.common.internal.android.AndroidMetricEvent, com.amazon.client.metrics.common.MetricEvent
    public void appendString(String str, String str2) {
    }

    @Override // com.amazon.client.metrics.common.internal.android.AndroidMetricEvent, com.amazon.client.metrics.common.MetricEvent
    public void clear() {
    }

    @Override // com.amazon.client.metrics.common.internal.android.AndroidMetricEvent, com.amazon.client.metrics.common.MetricEvent
    public boolean getAnonymous() {
        return this.mDelegateThirdPartyNullMetricEvent.getAnonymous();
    }

    @Override // com.amazon.client.metrics.common.internal.android.AndroidMetricEvent, com.amazon.client.metrics.common.MetricEvent
    public List<DataPoint> getAsDataPoints() {
        return DataPointConverter.convertThirdPartyToCommon(this.mDelegateThirdPartyNullMetricEvent.getAsDataPoints());
    }

    @Override // com.amazon.client.metrics.common.internal.android.AndroidMetricEvent, com.amazon.client.metrics.common.MetricEvent
    public MetricEventType getMetricEventType() {
        return MetricEventType.valueOf(this.mDelegateThirdPartyNullMetricEvent.getMetricEventType().name());
    }

    @Override // com.amazon.client.metrics.common.internal.android.AndroidMetricEvent, com.amazon.client.metrics.common.MetricEvent
    public String getNonAnonymousCustomerId() {
        return this.mDelegateThirdPartyNullMetricEvent.getNonAnonymousCustomerId();
    }

    @Override // com.amazon.client.metrics.common.internal.android.AndroidMetricEvent, com.amazon.client.metrics.common.MetricEvent
    public String getNonAnonymousSessionId() {
        return this.mDelegateThirdPartyNullMetricEvent.getNonAnonymousSessionId();
    }

    @Override // com.amazon.client.metrics.common.internal.android.AndroidMetricEvent, com.amazon.client.metrics.common.MetricEvent
    public String getProgram() {
        return this.mDelegateThirdPartyNullMetricEvent.getProgram();
    }

    @Override // com.amazon.client.metrics.common.internal.android.AndroidMetricEvent, com.amazon.client.metrics.common.MetricEvent
    public String getSource() {
        return this.mDelegateThirdPartyNullMetricEvent.getSource();
    }

    @Override // com.amazon.client.metrics.common.internal.android.AndroidMetricEvent, com.amazon.client.metrics.common.MetricEvent
    public boolean hasDataPoints() {
        return this.mDelegateThirdPartyNullMetricEvent.hasDataPoints();
    }

    @Override // com.amazon.client.metrics.common.internal.android.AndroidMetricEvent, com.amazon.client.metrics.common.MetricEvent
    public void incrementCounter(String str, double d) {
    }

    @Override // com.amazon.client.metrics.common.internal.android.AndroidMetricEvent, com.amazon.client.metrics.common.MetricEvent
    public void removeCounter(String str) {
    }

    @Override // com.amazon.client.metrics.common.internal.android.AndroidMetricEvent, com.amazon.client.metrics.common.MetricEvent
    public void removeString(String str) {
    }

    @Override // com.amazon.client.metrics.common.internal.android.AndroidMetricEvent, com.amazon.client.metrics.common.MetricEvent
    public void removeTimer(String str) {
    }

    @Override // com.amazon.client.metrics.common.internal.android.AndroidMetricEvent, com.amazon.client.metrics.common.MetricEvent
    public void restoreFromMap(Map<String, String> map) throws IllegalArgumentException {
    }

    @Override // com.amazon.client.metrics.common.internal.android.AndroidMetricEvent, com.amazon.client.metrics.common.MetricEvent
    public void saveToMap(Map<String, String> map) {
    }

    @Override // com.amazon.client.metrics.common.internal.android.AndroidMetricEvent, com.amazon.client.metrics.common.MetricEvent
    public void setAnonymous(boolean z) {
    }

    @Override // com.amazon.client.metrics.common.internal.android.AndroidMetricEvent, com.amazon.client.metrics.common.MetricEvent
    public void setClickstreamUserAgent(String str) {
    }

    @Override // com.amazon.client.metrics.common.internal.android.AndroidMetricEvent, com.amazon.client.metrics.common.MetricEvent
    public void setNonAnonymousCustomerId(String str) {
    }

    @Override // com.amazon.client.metrics.common.internal.android.AndroidMetricEvent, com.amazon.client.metrics.common.MetricEvent
    public void setNonAnonymousSessionId(String str) {
    }

    @Override // com.amazon.client.metrics.common.internal.android.AndroidMetricEvent, com.amazon.client.metrics.common.MetricEvent
    public void startTimer(String str) {
    }

    @Override // com.amazon.client.metrics.common.internal.android.AndroidMetricEvent, com.amazon.client.metrics.common.MetricEvent
    public void stopTimer(String str) {
    }

    @Override // com.amazon.client.metrics.common.internal.android.AndroidMetricEvent
    /* renamed from: getDelegateMetricEvent  reason: collision with other method in class */
    public NullMetricEvent mo2883getDelegateMetricEvent() {
        return this.mDelegateThirdPartyNullMetricEvent;
    }
}
