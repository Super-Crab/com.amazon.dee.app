package com.amazon.alexa.voice.elements;

import com.amazon.alexa.eventbus.api.Message;
import com.amazon.alexa.eventbus.api.MessageFilter;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.voice.elements.-$$Lambda$AlexaCardEventSender$BDkUa_c_ZVIK29_CAShlKLOBvgw  reason: invalid class name */
/* loaded from: classes11.dex */
public final /* synthetic */ class $$Lambda$AlexaCardEventSender$BDkUa_c_ZVIK29_CAShlKLOBvgw implements MessageFilter {
    public static final /* synthetic */ $$Lambda$AlexaCardEventSender$BDkUa_c_ZVIK29_CAShlKLOBvgw INSTANCE = new $$Lambda$AlexaCardEventSender$BDkUa_c_ZVIK29_CAShlKLOBvgw();

    private /* synthetic */ $$Lambda$AlexaCardEventSender$BDkUa_c_ZVIK29_CAShlKLOBvgw() {
    }

    @Override // com.amazon.alexa.eventbus.api.MessageFilter
    public final boolean isMatch(Message message) {
        return "vox::enable_alexa_card_event_request".equals(message.getEventType());
    }
}
