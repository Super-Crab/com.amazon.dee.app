package com.amazon.alexa.voice.tta.metrics;

import com.amazon.alexa.voice.ui.tta.metrics.TtaEvent;
/* loaded from: classes11.dex */
public enum TtaInternalEvent implements TtaEvent {
    SCREEN_LAUNCHED,
    ACTIVITY_RESUMED,
    ACTIVITY_PAUSED;

    @Override // com.amazon.alexa.voice.ui.tta.metrics.TtaEvent
    public String getName() {
        return name();
    }
}
