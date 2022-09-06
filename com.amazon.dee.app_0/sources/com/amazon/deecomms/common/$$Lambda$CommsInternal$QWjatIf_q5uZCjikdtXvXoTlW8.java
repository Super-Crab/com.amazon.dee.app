package com.amazon.deecomms.common;

import com.amazon.alexa.eventbus.api.Message;
import com.amazon.alexa.eventbus.api.MessageFilter;
/* compiled from: lambda */
/* renamed from: com.amazon.deecomms.common.-$$Lambda$CommsInternal$QWjatI-f_q5uZCjikdtXvXoTlW8  reason: invalid class name */
/* loaded from: classes12.dex */
public final /* synthetic */ class $$Lambda$CommsInternal$QWjatIf_q5uZCjikdtXvXoTlW8 implements MessageFilter {
    public static final /* synthetic */ $$Lambda$CommsInternal$QWjatIf_q5uZCjikdtXvXoTlW8 INSTANCE = new $$Lambda$CommsInternal$QWjatIf_q5uZCjikdtXvXoTlW8();

    private /* synthetic */ $$Lambda$CommsInternal$QWjatIf_q5uZCjikdtXvXoTlW8() {
    }

    @Override // com.amazon.alexa.eventbus.api.MessageFilter
    public final boolean isMatch(Message message) {
        boolean equals;
        equals = EventBusEventType.RECEIVE_SHARING_PARSE.toString().equals(message.getEventType());
        return equals;
    }
}
