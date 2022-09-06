package com.amazon.deecomms.conversation;

import com.amazon.alexa.eventbus.api.Message;
import com.amazon.alexa.eventbus.api.MessageFilter;
import com.amazon.alexa.identity.api.IdentityEvent;
/* compiled from: lambda */
/* renamed from: com.amazon.deecomms.conversation.-$$Lambda$CommsConversationService$kzq_FEoFD63l9AocHU9f9HAGZM4  reason: invalid class name */
/* loaded from: classes12.dex */
public final /* synthetic */ class $$Lambda$CommsConversationService$kzq_FEoFD63l9AocHU9f9HAGZM4 implements MessageFilter {
    public static final /* synthetic */ $$Lambda$CommsConversationService$kzq_FEoFD63l9AocHU9f9HAGZM4 INSTANCE = new $$Lambda$CommsConversationService$kzq_FEoFD63l9AocHU9f9HAGZM4();

    private /* synthetic */ $$Lambda$CommsConversationService$kzq_FEoFD63l9AocHU9f9HAGZM4() {
    }

    @Override // com.amazon.alexa.eventbus.api.MessageFilter
    public final boolean isMatch(Message message) {
        boolean equals;
        equals = IdentityEvent.IDENTITY_CHANGED.equals(message.getEventType());
        return equals;
    }
}
