package com.amazon.alexa.sensor.metrics;

import androidx.annotation.NonNull;
import com.amazon.alexa.sensor.api.metrics.events.TimerEvent;
/* loaded from: classes10.dex */
public class SystemTimerEvent extends TimerEvent {
    private long startTime;

    public SystemTimerEvent(@NonNull String str, @NonNull String str2, @NonNull String str3) {
        super(str, str2, str3);
        this.startTime = -1L;
    }

    @Override // com.amazon.alexa.sensor.api.metrics.events.TimerEvent
    public void startTimer() {
        this.startTime = System.currentTimeMillis();
    }

    @Override // com.amazon.alexa.sensor.api.metrics.events.TimerEvent
    public void stopTimer() {
        long j = 0;
        if (this.startTime < 0) {
            return;
        }
        long currentTimeMillis = System.currentTimeMillis();
        long j2 = this.startTime;
        if (currentTimeMillis > j2) {
            j = currentTimeMillis - j2;
        }
        this.elapsedTime = j;
        this.startTime = -1L;
    }
}
