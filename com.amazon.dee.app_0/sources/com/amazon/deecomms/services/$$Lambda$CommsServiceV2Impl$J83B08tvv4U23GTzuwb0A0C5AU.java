package com.amazon.deecomms.services;

import com.amazon.alexa.eventbus.api.Message;
import com.amazon.alexa.eventbus.api.MessageFilter;
import com.amazon.deecomms.common.EventBusEventType;
/* compiled from: lambda */
/* renamed from: com.amazon.deecomms.services.-$$Lambda$CommsServiceV2Impl$J83B08tvv4U23GTzuwb0-A0C5AU  reason: invalid class name */
/* loaded from: classes12.dex */
public final /* synthetic */ class $$Lambda$CommsServiceV2Impl$J83B08tvv4U23GTzuwb0A0C5AU implements MessageFilter {
    public static final /* synthetic */ $$Lambda$CommsServiceV2Impl$J83B08tvv4U23GTzuwb0A0C5AU INSTANCE = new $$Lambda$CommsServiceV2Impl$J83B08tvv4U23GTzuwb0A0C5AU();

    private /* synthetic */ $$Lambda$CommsServiceV2Impl$J83B08tvv4U23GTzuwb0A0C5AU() {
    }

    @Override // com.amazon.alexa.eventbus.api.MessageFilter
    public final boolean isMatch(Message message) {
        boolean equals;
        equals = EventBusEventType.ACCESSORY_PRIVACY_BUTTON_TOGGLED.toString().equals(message.getEventType());
        return equals;
    }
}
