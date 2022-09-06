package com.amazon.deecomms.common;

import com.amazon.alexa.eventbus.api.Message;
import com.amazon.alexa.eventbus.api.MessageFilter;
/* compiled from: lambda */
/* renamed from: com.amazon.deecomms.common.-$$Lambda$CommsInternal$MiCi36gEJcvNhhfs0j11m8mUwFg  reason: invalid class name */
/* loaded from: classes12.dex */
public final /* synthetic */ class $$Lambda$CommsInternal$MiCi36gEJcvNhhfs0j11m8mUwFg implements MessageFilter {
    public static final /* synthetic */ $$Lambda$CommsInternal$MiCi36gEJcvNhhfs0j11m8mUwFg INSTANCE = new $$Lambda$CommsInternal$MiCi36gEJcvNhhfs0j11m8mUwFg();

    private /* synthetic */ $$Lambda$CommsInternal$MiCi36gEJcvNhhfs0j11m8mUwFg() {
    }

    @Override // com.amazon.alexa.eventbus.api.MessageFilter
    public final boolean isMatch(Message message) {
        boolean equals;
        equals = EventBusEventType.SEND_SHARING_PARSE.toString().equals(message.getEventType());
        return equals;
    }
}
