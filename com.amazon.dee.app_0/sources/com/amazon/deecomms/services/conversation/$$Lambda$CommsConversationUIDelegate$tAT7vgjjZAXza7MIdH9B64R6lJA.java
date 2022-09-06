package com.amazon.deecomms.services.conversation;

import com.amazon.alexa.routing.api.RoutingService;
import com.amazon.deecomms.util.Consumer;
/* compiled from: lambda */
/* renamed from: com.amazon.deecomms.services.conversation.-$$Lambda$CommsConversationUIDelegate$tAT7vgjjZAXza7MIdH9B64R6lJA  reason: invalid class name */
/* loaded from: classes12.dex */
public final /* synthetic */ class $$Lambda$CommsConversationUIDelegate$tAT7vgjjZAXza7MIdH9B64R6lJA implements Consumer {
    public static final /* synthetic */ $$Lambda$CommsConversationUIDelegate$tAT7vgjjZAXza7MIdH9B64R6lJA INSTANCE = new $$Lambda$CommsConversationUIDelegate$tAT7vgjjZAXza7MIdH9B64R6lJA();

    private /* synthetic */ $$Lambda$CommsConversationUIDelegate$tAT7vgjjZAXza7MIdH9B64R6lJA() {
    }

    @Override // com.amazon.deecomms.util.Consumer
    public final void accept(Object obj) {
        ((RoutingService.RoutingBuilder) obj).notifyNavigated();
    }
}
