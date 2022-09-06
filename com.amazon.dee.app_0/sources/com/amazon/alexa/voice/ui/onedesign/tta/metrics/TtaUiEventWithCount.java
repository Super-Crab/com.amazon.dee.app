package com.amazon.alexa.voice.ui.onedesign.tta.metrics;

import androidx.annotation.NonNull;
import com.amazon.alexa.voice.ui.tta.metrics.TtaEvent;
/* loaded from: classes11.dex */
public class TtaUiEventWithCount implements TtaEvent {
    private final int count;
    private final TtaUiEvent ttaUiEvent;

    public TtaUiEventWithCount(@NonNull TtaUiEvent ttaUiEvent, int i) {
        this.ttaUiEvent = ttaUiEvent;
        this.count = i;
    }

    public int getCount() {
        return this.count;
    }

    public TtaUiEvent getEvent() {
        return this.ttaUiEvent;
    }

    @Override // com.amazon.alexa.voice.ui.tta.metrics.TtaEvent
    @NonNull
    public String getName() {
        return this.ttaUiEvent.getName();
    }
}
