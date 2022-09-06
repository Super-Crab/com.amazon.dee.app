package com.amazon.deecomms.core.decoupling;

import com.amazon.alexa.eventbus.api.Message;
import com.amazon.alexa.eventbus.api.MessageFilter;
import com.amazon.alexa.mode.ModeConstants;
/* compiled from: lambda */
/* renamed from: com.amazon.deecomms.core.decoupling.-$$Lambda$AlexaCommsServiceImpl$r2Px88sxdm9gkUowKOKKIRfGD1E  reason: invalid class name */
/* loaded from: classes12.dex */
public final /* synthetic */ class $$Lambda$AlexaCommsServiceImpl$r2Px88sxdm9gkUowKOKKIRfGD1E implements MessageFilter {
    public static final /* synthetic */ $$Lambda$AlexaCommsServiceImpl$r2Px88sxdm9gkUowKOKKIRfGD1E INSTANCE = new $$Lambda$AlexaCommsServiceImpl$r2Px88sxdm9gkUowKOKKIRfGD1E();

    private /* synthetic */ $$Lambda$AlexaCommsServiceImpl$r2Px88sxdm9gkUowKOKKIRfGD1E() {
    }

    @Override // com.amazon.alexa.eventbus.api.MessageFilter
    public final boolean isMatch(Message message) {
        boolean equals;
        equals = ModeConstants.MODE_SWITCHED_EVENT.equals(message.getEventType());
        return equals;
    }
}
