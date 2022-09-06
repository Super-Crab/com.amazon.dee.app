package com.amazon.deecomms.core.decoupling;

import com.amazon.alexa.eventbus.api.Message;
import com.amazon.alexa.eventbus.api.MessageFilter;
import com.amazon.deecomms.common.EventBusEventType;
/* compiled from: lambda */
/* renamed from: com.amazon.deecomms.core.decoupling.-$$Lambda$AlexaCommsServiceImpl$ZiR1XbodiEkodSYmaSDOT4uTeMQ  reason: invalid class name */
/* loaded from: classes12.dex */
public final /* synthetic */ class $$Lambda$AlexaCommsServiceImpl$ZiR1XbodiEkodSYmaSDOT4uTeMQ implements MessageFilter {
    public static final /* synthetic */ $$Lambda$AlexaCommsServiceImpl$ZiR1XbodiEkodSYmaSDOT4uTeMQ INSTANCE = new $$Lambda$AlexaCommsServiceImpl$ZiR1XbodiEkodSYmaSDOT4uTeMQ();

    private /* synthetic */ $$Lambda$AlexaCommsServiceImpl$ZiR1XbodiEkodSYmaSDOT4uTeMQ() {
    }

    @Override // com.amazon.alexa.eventbus.api.MessageFilter
    public final boolean isMatch(Message message) {
        boolean equals;
        equals = EventBusEventType.REMOVE_EXPIRED_MEDIA_FROM_CACHE.toString().equals(message.getEventType());
        return equals;
    }
}
