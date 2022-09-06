package com.amazon.alexa.sensor.metrics;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.mobilytics.Mobilytics;
import com.amazon.alexa.mobilytics.OwnerIdentifier;
import com.amazon.alexa.mobilytics.event.operational.MobilyticsMetricsCounter;
import com.amazon.alexa.protocols.service.api.ComponentGetter;
import com.amazon.alexa.protocols.service.api.LazyComponent;
import com.amazon.alexa.sensor.api.metrics.SensorAccessMetricsRecorder;
import com.amazon.alexa.sensor.api.metrics.events.CounterEvent;
import com.amazon.alexa.sensor.api.metrics.events.TimerEvent;
/* loaded from: classes10.dex */
public class SensorAccessMobilyticsRecorder implements SensorAccessMetricsRecorder {
    private final LazyComponent<Mobilytics> mobilytics;

    public SensorAccessMobilyticsRecorder(@NonNull ComponentGetter componentGetter, @NonNull Context context) {
        this.mobilytics = componentGetter.get(Mobilytics.class);
    }

    @Override // com.amazon.alexa.sensor.api.metrics.SensorAccessMetricsRecorder
    public void recordCounter(CounterEvent counterEvent) {
        MobilyticsMetricsCounter createCounter = this.mobilytics.mo10268get().createCounter(counterEvent.name, counterEvent.component, counterEvent.subComponent, OwnerIdentifier.APP_LOCATION_LIBRARIES_ANDROID);
        createCounter.incrementCounterByValue(counterEvent.getValue());
        this.mobilytics.mo10268get().recordCounter(createCounter);
    }

    @Override // com.amazon.alexa.sensor.api.metrics.SensorAccessMetricsRecorder
    public void recordEvent(@NonNull String str, @NonNull String str2, @NonNull String str3) {
        this.mobilytics.mo10268get().recordOperationalEvent(str, str2, str3, OwnerIdentifier.APP_LOCATION_LIBRARIES_ANDROID);
    }

    @Override // com.amazon.alexa.sensor.api.metrics.SensorAccessMetricsRecorder
    public void recordOccurrence(@NonNull String str, @NonNull String str2, @NonNull String str3, boolean z) {
        this.mobilytics.mo10268get().recordOccurrence(str, z, str2, str3, OwnerIdentifier.APP_LOCATION_LIBRARIES_ANDROID);
    }

    @Override // com.amazon.alexa.sensor.api.metrics.SensorAccessMetricsRecorder
    public void recordTimer(TimerEvent timerEvent) {
        this.mobilytics.mo10268get().recordTimer(this.mobilytics.mo10268get().createTimer(timerEvent.name, timerEvent.component, timerEvent.subComponent, timerEvent.getElapsedTime(), false, OwnerIdentifier.APP_LOCATION_LIBRARIES_ANDROID));
    }

    @VisibleForTesting
    SensorAccessMobilyticsRecorder(@NonNull LazyComponent<Mobilytics> lazyComponent) {
        this.mobilytics = lazyComponent;
    }
}
