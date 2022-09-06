package com.amazon.commscore.commsidentity.implementation;

import com.amazon.alexa.eventbus.api.Message;
import com.amazon.alexa.eventbus.api.MessageFilter;
import com.amazon.alexa.identity.api.IdentityEvent;
/* compiled from: lambda */
/* renamed from: com.amazon.commscore.commsidentity.implementation.-$$Lambda$DefaultAlexaCommsCoreIdentityService$JNMKT-WCSv6Q5eLdA28N17-lUSs  reason: invalid class name */
/* loaded from: classes12.dex */
public final /* synthetic */ class $$Lambda$DefaultAlexaCommsCoreIdentityService$JNMKTWCSv6Q5eLdA28N17lUSs implements MessageFilter {
    public static final /* synthetic */ $$Lambda$DefaultAlexaCommsCoreIdentityService$JNMKTWCSv6Q5eLdA28N17lUSs INSTANCE = new $$Lambda$DefaultAlexaCommsCoreIdentityService$JNMKTWCSv6Q5eLdA28N17lUSs();

    private /* synthetic */ $$Lambda$DefaultAlexaCommsCoreIdentityService$JNMKTWCSv6Q5eLdA28N17lUSs() {
    }

    @Override // com.amazon.alexa.eventbus.api.MessageFilter
    public final boolean isMatch(Message message) {
        return IdentityEvent.IDENTITY_PROFILE_CHANGED.equals(message.getEventType());
    }
}
