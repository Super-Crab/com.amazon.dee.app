package com.amazon.alexa.voice.tta.metrics;

import com.amazon.alexa.voice.tta.metrics.AggregateEventProcessor;
import com.amazon.alexa.voice.ui.onedesign.tta.metrics.TtaUiEvent;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.voice.tta.metrics.-$$Lambda$o4ftQieWudvnDVyyl6Mj71efwzU  reason: invalid class name */
/* loaded from: classes11.dex */
public final /* synthetic */ class $$Lambda$o4ftQieWudvnDVyyl6Mj71efwzU implements AggregateEventProcessor.Builder.OnEvent {
    private final /* synthetic */ LatencyTimer f$0;

    public /* synthetic */ $$Lambda$o4ftQieWudvnDVyyl6Mj71efwzU(LatencyTimer latencyTimer) {
        this.f$0 = latencyTimer;
    }

    @Override // com.amazon.alexa.voice.tta.metrics.AggregateEventProcessor.Builder.OnEvent
    public final void onEvent(Object obj, EventTime eventTime) {
        this.f$0.end((TtaUiEvent) obj, eventTime);
    }
}
