package com.amazon.dee.app.services.metrics;

import com.amazon.alexa.eventbus.api.Message;
import com.amazon.alexa.eventbus.api.MessageHandler;
import com.amazon.dee.app.services.metrics.LatencyService;
/* compiled from: lambda */
/* renamed from: com.amazon.dee.app.services.metrics.-$$Lambda$LatencyService$fDujmb8Nz5ZQfuZS5c04i3v0Yso  reason: invalid class name */
/* loaded from: classes12.dex */
public final /* synthetic */ class $$Lambda$LatencyService$fDujmb8Nz5ZQfuZS5c04i3v0Yso implements MessageHandler {
    public static final /* synthetic */ $$Lambda$LatencyService$fDujmb8Nz5ZQfuZS5c04i3v0Yso INSTANCE = new $$Lambda$LatencyService$fDujmb8Nz5ZQfuZS5c04i3v0Yso();

    private /* synthetic */ $$Lambda$LatencyService$fDujmb8Nz5ZQfuZS5c04i3v0Yso() {
    }

    @Override // com.amazon.alexa.eventbus.api.MessageHandler
    public final void handle(Message message) {
        LatencyService.complete(LatencyService.Component.COMMS, LatencyService.Completion.CONVERSATION);
    }
}
