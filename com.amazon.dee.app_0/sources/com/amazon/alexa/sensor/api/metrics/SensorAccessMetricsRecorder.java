package com.amazon.alexa.sensor.api.metrics;

import androidx.annotation.NonNull;
import com.amazon.alexa.sensor.api.metrics.events.CounterEvent;
import com.amazon.alexa.sensor.api.metrics.events.TimerEvent;
/* loaded from: classes10.dex */
public interface SensorAccessMetricsRecorder {
    void recordCounter(CounterEvent counterEvent);

    void recordEvent(@NonNull String str, @NonNull String str2, @NonNull String str3);

    void recordOccurrence(@NonNull String str, @NonNull String str2, @NonNull String str3, boolean z);

    void recordTimer(TimerEvent timerEvent);
}
