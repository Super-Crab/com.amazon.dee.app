package com.amazon.deecomms.core.decoupling;

import com.amazon.alexa.eventbus.api.Message;
import com.amazon.alexa.eventbus.api.MessageFilter;
import com.amazon.alexa.identity.api.IdentityEvent;
/* compiled from: lambda */
/* renamed from: com.amazon.deecomms.core.decoupling.-$$Lambda$AlexaCommsServiceImpl$uHfRAxVoAEYe3wZXoJfWE1nRXR8  reason: invalid class name */
/* loaded from: classes12.dex */
public final /* synthetic */ class $$Lambda$AlexaCommsServiceImpl$uHfRAxVoAEYe3wZXoJfWE1nRXR8 implements MessageFilter {
    public static final /* synthetic */ $$Lambda$AlexaCommsServiceImpl$uHfRAxVoAEYe3wZXoJfWE1nRXR8 INSTANCE = new $$Lambda$AlexaCommsServiceImpl$uHfRAxVoAEYe3wZXoJfWE1nRXR8();

    private /* synthetic */ $$Lambda$AlexaCommsServiceImpl$uHfRAxVoAEYe3wZXoJfWE1nRXR8() {
    }

    @Override // com.amazon.alexa.eventbus.api.MessageFilter
    public final boolean isMatch(Message message) {
        boolean equals;
        equals = IdentityEvent.IDENTITY_PROFILE_CHANGED.equals(message.getEventType());
        return equals;
    }
}
