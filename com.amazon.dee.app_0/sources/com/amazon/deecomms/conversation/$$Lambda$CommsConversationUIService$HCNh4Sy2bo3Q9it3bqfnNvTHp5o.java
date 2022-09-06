package com.amazon.deecomms.conversation;

import com.amazon.alexa.eventbus.api.Message;
import com.amazon.alexa.eventbus.api.MessageFilter;
import com.amazon.alexa.identity.api.IdentityEvent;
/* compiled from: lambda */
/* renamed from: com.amazon.deecomms.conversation.-$$Lambda$CommsConversationUIService$HCNh4Sy2bo3Q9it3bqfnNvTHp5o  reason: invalid class name */
/* loaded from: classes12.dex */
public final /* synthetic */ class $$Lambda$CommsConversationUIService$HCNh4Sy2bo3Q9it3bqfnNvTHp5o implements MessageFilter {
    public static final /* synthetic */ $$Lambda$CommsConversationUIService$HCNh4Sy2bo3Q9it3bqfnNvTHp5o INSTANCE = new $$Lambda$CommsConversationUIService$HCNh4Sy2bo3Q9it3bqfnNvTHp5o();

    private /* synthetic */ $$Lambda$CommsConversationUIService$HCNh4Sy2bo3Q9it3bqfnNvTHp5o() {
    }

    @Override // com.amazon.alexa.eventbus.api.MessageFilter
    public final boolean isMatch(Message message) {
        boolean equals;
        equals = IdentityEvent.IDENTITY_CHANGED.equals(message.getEventType());
        return equals;
    }
}
