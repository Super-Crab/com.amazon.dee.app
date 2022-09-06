package com.amazon.alexa.mobilytics.event.operational;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.concurrent.atomic.AtomicLong;
/* loaded from: classes9.dex */
public class DefaultMobilyticsMetricsCounter extends DefaultMobilyticsOperationalEvent implements MobilyticsMetricsCounter {
    private transient AtomicLong currentCount;

    public DefaultMobilyticsMetricsCounter(@NonNull String str, @NonNull String str2, @Nullable String str3) {
        super(str, OperationalEventType.COUNTER, str2, str3);
        this.currentCount = new AtomicLong(0L);
        mo1484withEventNumericValue(0L);
    }

    @Override // com.amazon.alexa.mobilytics.event.operational.MobilyticsMetricsCounter
    public long getCount() {
        return this.currentCount.get();
    }

    @Override // com.amazon.alexa.mobilytics.event.operational.MobilyticsMetricsCounter
    public void incrementCounter() {
        incrementCounterByValue(1L);
    }

    @Override // com.amazon.alexa.mobilytics.event.operational.MobilyticsMetricsCounter
    public void incrementCounterByValue(long j) {
        mo1484withEventNumericValue(Long.valueOf(this.currentCount.addAndGet(j)));
    }

    @Override // com.amazon.alexa.mobilytics.event.operational.MobilyticsMetricsCounter
    public void resetCounter() {
        this.currentCount.set(0L);
        mo1484withEventNumericValue(0L);
    }

    public DefaultMobilyticsMetricsCounter(@NonNull String str, @NonNull String str2, @Nullable String str3, String str4) {
        super(str, OperationalEventType.COUNTER, str2, str3, str4);
        this.currentCount = new AtomicLong(0L);
        mo1484withEventNumericValue(0L);
    }

    public DefaultMobilyticsMetricsCounter(@NonNull String str, @NonNull String str2, @Nullable String str3, long j) {
        super(str, OperationalEventType.COUNTER, str2, str3);
        this.currentCount = new AtomicLong(0L);
        incrementCounterByValue(j);
    }

    public DefaultMobilyticsMetricsCounter(@NonNull String str, @NonNull String str2, @Nullable String str3, long j, String str4) {
        super(str, OperationalEventType.COUNTER, str2, str3, str4);
        this.currentCount = new AtomicLong(0L);
        incrementCounterByValue(j);
    }
}
