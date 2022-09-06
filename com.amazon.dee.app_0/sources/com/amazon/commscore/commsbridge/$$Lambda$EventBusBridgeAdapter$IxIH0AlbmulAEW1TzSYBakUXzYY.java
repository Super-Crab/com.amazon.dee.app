package com.amazon.commscore.commsbridge;

import com.amazon.alexa.eventbus.api.Message;
import com.amazon.alexa.eventbus.api.MessageFilter;
/* compiled from: lambda */
/* renamed from: com.amazon.commscore.commsbridge.-$$Lambda$EventBusBridgeAdapter$IxIH0AlbmulAEW1TzSYBakUXzYY  reason: invalid class name */
/* loaded from: classes12.dex */
public final /* synthetic */ class $$Lambda$EventBusBridgeAdapter$IxIH0AlbmulAEW1TzSYBakUXzYY implements MessageFilter {
    public static final /* synthetic */ $$Lambda$EventBusBridgeAdapter$IxIH0AlbmulAEW1TzSYBakUXzYY INSTANCE = new $$Lambda$EventBusBridgeAdapter$IxIH0AlbmulAEW1TzSYBakUXzYY();

    private /* synthetic */ $$Lambda$EventBusBridgeAdapter$IxIH0AlbmulAEW1TzSYBakUXzYY() {
    }

    @Override // com.amazon.alexa.eventbus.api.MessageFilter
    public final boolean isMatch(Message message) {
        boolean equalsIgnoreCase;
        equalsIgnoreCase = EventBusBridgeAdapter.REACT_EB_MSG.equalsIgnoreCase(message.getEventType());
        return equalsIgnoreCase;
    }
}
