package com.amazon.deecomms.core.decoupling;

import com.amazon.alexa.eventbus.api.Message;
import com.amazon.alexa.eventbus.api.MessageFilter;
import com.amazon.deecomms.common.EventBusEventType;
/* compiled from: lambda */
/* renamed from: com.amazon.deecomms.core.decoupling.-$$Lambda$AlexaCommsServiceImpl$ywzsjv0cIct-a9GUpR4meyJatOI  reason: invalid class name */
/* loaded from: classes12.dex */
public final /* synthetic */ class $$Lambda$AlexaCommsServiceImpl$ywzsjv0cIcta9GUpR4meyJatOI implements MessageFilter {
    public static final /* synthetic */ $$Lambda$AlexaCommsServiceImpl$ywzsjv0cIcta9GUpR4meyJatOI INSTANCE = new $$Lambda$AlexaCommsServiceImpl$ywzsjv0cIcta9GUpR4meyJatOI();

    private /* synthetic */ $$Lambda$AlexaCommsServiceImpl$ywzsjv0cIcta9GUpR4meyJatOI() {
    }

    @Override // com.amazon.alexa.eventbus.api.MessageFilter
    public final boolean isMatch(Message message) {
        boolean equals;
        equals = EventBusEventType.ACCESSORY_CALL_VIP_CONTACT_EVENT.toString().equals(message.getEventType());
        return equals;
    }
}
