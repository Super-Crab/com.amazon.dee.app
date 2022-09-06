package com.amazon.deecomms.services;

import com.amazon.alexa.eventbus.api.Message;
import com.amazon.alexa.eventbus.api.MessageFilter;
import com.amazon.alexa.identity.api.IdentityEvent;
/* compiled from: lambda */
/* renamed from: com.amazon.deecomms.services.-$$Lambda$CommsServiceV2Impl$EBRqsyHoybXPWsU9g6pn2d3akXY  reason: invalid class name */
/* loaded from: classes12.dex */
public final /* synthetic */ class $$Lambda$CommsServiceV2Impl$EBRqsyHoybXPWsU9g6pn2d3akXY implements MessageFilter {
    public static final /* synthetic */ $$Lambda$CommsServiceV2Impl$EBRqsyHoybXPWsU9g6pn2d3akXY INSTANCE = new $$Lambda$CommsServiceV2Impl$EBRqsyHoybXPWsU9g6pn2d3akXY();

    private /* synthetic */ $$Lambda$CommsServiceV2Impl$EBRqsyHoybXPWsU9g6pn2d3akXY() {
    }

    @Override // com.amazon.alexa.eventbus.api.MessageFilter
    public final boolean isMatch(Message message) {
        boolean equals;
        equals = IdentityEvent.IDENTITY_PROFILE_CHANGED.equals(message.getEventType());
        return equals;
    }
}
