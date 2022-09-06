package com.amazon.deecomms.services;

import com.amazon.alexa.eventbus.api.Message;
import com.amazon.alexa.eventbus.api.MessageFilter;
import com.amazon.alexa.mode.ModeConstants;
/* compiled from: lambda */
/* renamed from: com.amazon.deecomms.services.-$$Lambda$CommsServiceV2Impl$noaJG5p0WFwnjTulIm4RJTHZd5g  reason: invalid class name */
/* loaded from: classes12.dex */
public final /* synthetic */ class $$Lambda$CommsServiceV2Impl$noaJG5p0WFwnjTulIm4RJTHZd5g implements MessageFilter {
    public static final /* synthetic */ $$Lambda$CommsServiceV2Impl$noaJG5p0WFwnjTulIm4RJTHZd5g INSTANCE = new $$Lambda$CommsServiceV2Impl$noaJG5p0WFwnjTulIm4RJTHZd5g();

    private /* synthetic */ $$Lambda$CommsServiceV2Impl$noaJG5p0WFwnjTulIm4RJTHZd5g() {
    }

    @Override // com.amazon.alexa.eventbus.api.MessageFilter
    public final boolean isMatch(Message message) {
        boolean equals;
        equals = ModeConstants.MODE_SWITCHED_EVENT.equals(message.getEventType());
        return equals;
    }
}
