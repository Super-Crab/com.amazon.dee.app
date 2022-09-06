package com.amazon.dee.app.services.metrics;

import com.amazon.alexa.eventbus.api.Message;
import com.amazon.alexa.eventbus.api.MessageHandler;
import com.amazon.dee.app.services.metrics.LatencyService;
/* compiled from: lambda */
/* renamed from: com.amazon.dee.app.services.metrics.-$$Lambda$LatencyService$_Ymp7z_911ooKRxpQqIPhA-tuzI  reason: invalid class name */
/* loaded from: classes12.dex */
public final /* synthetic */ class $$Lambda$LatencyService$_Ymp7z_911ooKRxpQqIPhAtuzI implements MessageHandler {
    public static final /* synthetic */ $$Lambda$LatencyService$_Ymp7z_911ooKRxpQqIPhAtuzI INSTANCE = new $$Lambda$LatencyService$_Ymp7z_911ooKRxpQqIPhAtuzI();

    private /* synthetic */ $$Lambda$LatencyService$_Ymp7z_911ooKRxpQqIPhAtuzI() {
    }

    @Override // com.amazon.alexa.eventbus.api.MessageHandler
    public final void handle(Message message) {
        LatencyService.complete(LatencyService.Component.COMMS, LatencyService.Completion.OOBE);
    }
}
