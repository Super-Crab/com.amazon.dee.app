package com.amazon.deecomms.common;

import com.amazon.alexa.eventbus.api.Message;
import com.amazon.alexa.eventbus.api.MessageFilter;
/* compiled from: lambda */
/* renamed from: com.amazon.deecomms.common.-$$Lambda$CommsInternal$opJHxB6IJiWI25I_lLhvaKlLOnI  reason: invalid class name */
/* loaded from: classes12.dex */
public final /* synthetic */ class $$Lambda$CommsInternal$opJHxB6IJiWI25I_lLhvaKlLOnI implements MessageFilter {
    public static final /* synthetic */ $$Lambda$CommsInternal$opJHxB6IJiWI25I_lLhvaKlLOnI INSTANCE = new $$Lambda$CommsInternal$opJHxB6IJiWI25I_lLhvaKlLOnI();

    private /* synthetic */ $$Lambda$CommsInternal$opJHxB6IJiWI25I_lLhvaKlLOnI() {
    }

    @Override // com.amazon.alexa.eventbus.api.MessageFilter
    public final boolean isMatch(Message message) {
        boolean equals;
        equals = EventBusEventType.UPDATE_CONVERSATION_INFO.toString().equals(message.getEventType());
        return equals;
    }
}
