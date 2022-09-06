package com.amazon.alexa.voice.tta.metrics;

import androidx.annotation.NonNull;
/* loaded from: classes11.dex */
public interface EventClock {
    @NonNull
    EventTime fromSystemTimeMilliseconds(long j);

    @NonNull
    EventTime now();
}
