package com.amazon.alexa.voice.tta.metrics;

import androidx.annotation.NonNull;
import com.amazon.alexa.voice.ui.tta.metrics.TtaEvent;
/* loaded from: classes11.dex */
public enum MetricsLifecycleEvent implements TtaEvent {
    EVENT_SERVICE_START,
    EVENT_SERVICE_STOP;

    @Override // com.amazon.alexa.voice.ui.tta.metrics.TtaEvent
    @NonNull
    public String getName() {
        return name();
    }
}
