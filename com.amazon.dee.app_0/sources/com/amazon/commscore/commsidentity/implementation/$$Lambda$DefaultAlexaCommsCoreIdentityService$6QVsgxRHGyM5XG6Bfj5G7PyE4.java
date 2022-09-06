package com.amazon.commscore.commsidentity.implementation;

import com.amazon.alexa.eventbus.api.Message;
import com.amazon.alexa.eventbus.api.MessageFilter;
import com.amazon.alexa.identity.api.IdentityEvent;
/* compiled from: lambda */
/* renamed from: com.amazon.commscore.commsidentity.implementation.-$$Lambda$DefaultAlexaCommsCoreIdentityService$6Q-VsgxRHGyM5XG6Bfj5G7P-yE4  reason: invalid class name */
/* loaded from: classes12.dex */
public final /* synthetic */ class $$Lambda$DefaultAlexaCommsCoreIdentityService$6QVsgxRHGyM5XG6Bfj5G7PyE4 implements MessageFilter {
    public static final /* synthetic */ $$Lambda$DefaultAlexaCommsCoreIdentityService$6QVsgxRHGyM5XG6Bfj5G7PyE4 INSTANCE = new $$Lambda$DefaultAlexaCommsCoreIdentityService$6QVsgxRHGyM5XG6Bfj5G7PyE4();

    private /* synthetic */ $$Lambda$DefaultAlexaCommsCoreIdentityService$6QVsgxRHGyM5XG6Bfj5G7PyE4() {
    }

    @Override // com.amazon.alexa.eventbus.api.MessageFilter
    public final boolean isMatch(Message message) {
        return IdentityEvent.IDENTITY_OOBE_PROFILE_SELECTED.equals(message.getEventType());
    }
}
