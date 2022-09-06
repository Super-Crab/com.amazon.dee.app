package com.amazon.dee.app.services.metrics;

import com.amazon.alexa.eventbus.api.Message;
import com.amazon.alexa.eventbus.api.MessageHandler;
import com.amazon.dee.app.services.metrics.LatencyService;
/* compiled from: lambda */
/* renamed from: com.amazon.dee.app.services.metrics.-$$Lambda$LatencyService$FirstRenderObserver$yNWhzjfe9GBYwMdqjnE3BdL0y0Q  reason: invalid class name */
/* loaded from: classes12.dex */
public final /* synthetic */ class $$Lambda$LatencyService$FirstRenderObserver$yNWhzjfe9GBYwMdqjnE3BdL0y0Q implements MessageHandler {
    public static final /* synthetic */ $$Lambda$LatencyService$FirstRenderObserver$yNWhzjfe9GBYwMdqjnE3BdL0y0Q INSTANCE = new $$Lambda$LatencyService$FirstRenderObserver$yNWhzjfe9GBYwMdqjnE3BdL0y0Q();

    private /* synthetic */ $$Lambda$LatencyService$FirstRenderObserver$yNWhzjfe9GBYwMdqjnE3BdL0y0Q() {
    }

    @Override // com.amazon.alexa.eventbus.api.MessageHandler
    public final void handle(Message message) {
        LatencyService.complete(LatencyService.Component.FTUE, LatencyService.Completion.OOBE);
    }
}
