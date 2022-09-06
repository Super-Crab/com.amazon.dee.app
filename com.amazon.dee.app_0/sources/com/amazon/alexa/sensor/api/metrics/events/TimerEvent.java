package com.amazon.alexa.sensor.api.metrics.events;

import androidx.annotation.NonNull;
/* loaded from: classes10.dex */
public abstract class TimerEvent extends Event {
    protected long elapsedTime;

    public TimerEvent(@NonNull String str, @NonNull String str2, @NonNull String str3) {
        super(str, str2, str3);
        this.elapsedTime = 0L;
    }

    public long getElapsedTime() {
        return this.elapsedTime;
    }

    public void setElapsedTime(long j) {
        this.elapsedTime = j;
    }

    public abstract void startTimer();

    public abstract void stopTimer();
}
