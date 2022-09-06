package com.amazon.deecomms.services;

import com.amazon.alexa.eventbus.api.Message;
import com.amazon.alexa.eventbus.api.MessageFilter;
import com.amazon.deecomms.common.EventBusEventType;
/* compiled from: lambda */
/* renamed from: com.amazon.deecomms.services.-$$Lambda$CommsServiceV2Impl$pbuSfkWh8qM7k7TDGjIkzS0JW-M  reason: invalid class name */
/* loaded from: classes12.dex */
public final /* synthetic */ class $$Lambda$CommsServiceV2Impl$pbuSfkWh8qM7k7TDGjIkzS0JWM implements MessageFilter {
    public static final /* synthetic */ $$Lambda$CommsServiceV2Impl$pbuSfkWh8qM7k7TDGjIkzS0JWM INSTANCE = new $$Lambda$CommsServiceV2Impl$pbuSfkWh8qM7k7TDGjIkzS0JWM();

    private /* synthetic */ $$Lambda$CommsServiceV2Impl$pbuSfkWh8qM7k7TDGjIkzS0JWM() {
    }

    @Override // com.amazon.alexa.eventbus.api.MessageFilter
    public final boolean isMatch(Message message) {
        boolean equals;
        equals = EventBusEventType.ACCESSORY_CALL_VIP_CONTACT_EVENT.toString().equals(message.getEventType());
        return equals;
    }
}
