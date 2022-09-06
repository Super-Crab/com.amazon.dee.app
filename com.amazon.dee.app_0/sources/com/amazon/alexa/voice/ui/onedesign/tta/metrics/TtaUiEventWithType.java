package com.amazon.alexa.voice.ui.onedesign.tta.metrics;

import androidx.annotation.NonNull;
import com.amazon.alexa.voice.ui.tta.metrics.TtaEvent;
/* loaded from: classes11.dex */
public class TtaUiEventWithType implements TtaEvent {
    private final TtaUiEvent event;
    private final String type;

    public TtaUiEventWithType(@NonNull TtaUiEvent ttaUiEvent, @NonNull String str) {
        this.event = ttaUiEvent;
        this.type = str;
    }

    public TtaUiEvent getEvent() {
        return this.event;
    }

    @Override // com.amazon.alexa.voice.ui.tta.metrics.TtaEvent
    @NonNull
    public String getName() {
        return this.event.getName() + ":" + this.type;
    }

    public String getType() {
        return this.type;
    }

    @NonNull
    public String toString() {
        return this.event.getName() + ":" + this.type;
    }
}
