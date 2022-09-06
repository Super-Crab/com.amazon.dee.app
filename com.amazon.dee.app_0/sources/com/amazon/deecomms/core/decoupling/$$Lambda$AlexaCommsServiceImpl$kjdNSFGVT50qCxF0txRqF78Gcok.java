package com.amazon.deecomms.core.decoupling;

import com.amazon.alexa.eventbus.api.Message;
import com.amazon.alexa.eventbus.api.MessageFilter;
import com.amazon.deecomms.common.EventBusEventType;
/* compiled from: lambda */
/* renamed from: com.amazon.deecomms.core.decoupling.-$$Lambda$AlexaCommsServiceImpl$kjdNSFGVT50qCxF0txRqF78Gcok  reason: invalid class name */
/* loaded from: classes12.dex */
public final /* synthetic */ class $$Lambda$AlexaCommsServiceImpl$kjdNSFGVT50qCxF0txRqF78Gcok implements MessageFilter {
    public static final /* synthetic */ $$Lambda$AlexaCommsServiceImpl$kjdNSFGVT50qCxF0txRqF78Gcok INSTANCE = new $$Lambda$AlexaCommsServiceImpl$kjdNSFGVT50qCxF0txRqF78Gcok();

    private /* synthetic */ $$Lambda$AlexaCommsServiceImpl$kjdNSFGVT50qCxF0txRqF78Gcok() {
    }

    @Override // com.amazon.alexa.eventbus.api.MessageFilter
    public final boolean isMatch(Message message) {
        boolean equals;
        equals = EventBusEventType.ACCESSORY_PRIVACY_BUTTON_TOGGLED.toString().equals(message.getEventType());
        return equals;
    }
}
