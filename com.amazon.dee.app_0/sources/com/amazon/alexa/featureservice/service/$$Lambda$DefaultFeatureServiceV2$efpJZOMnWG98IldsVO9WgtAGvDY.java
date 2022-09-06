package com.amazon.alexa.featureservice.service;

import com.amazon.alexa.eventbus.api.Message;
import com.amazon.alexa.eventbus.api.MessageFilter;
import com.amazon.alexa.identity.api.IdentityEvent;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.featureservice.service.-$$Lambda$DefaultFeatureServiceV2$efpJZOMnWG98IldsVO9WgtAGvDY  reason: invalid class name */
/* loaded from: classes7.dex */
public final /* synthetic */ class $$Lambda$DefaultFeatureServiceV2$efpJZOMnWG98IldsVO9WgtAGvDY implements MessageFilter {
    public static final /* synthetic */ $$Lambda$DefaultFeatureServiceV2$efpJZOMnWG98IldsVO9WgtAGvDY INSTANCE = new $$Lambda$DefaultFeatureServiceV2$efpJZOMnWG98IldsVO9WgtAGvDY();

    private /* synthetic */ $$Lambda$DefaultFeatureServiceV2$efpJZOMnWG98IldsVO9WgtAGvDY() {
    }

    @Override // com.amazon.alexa.eventbus.api.MessageFilter
    public final boolean isMatch(Message message) {
        return IdentityEvent.IDENTITY_SIGN_OUT_SUCCESS.equals(message.getEventType());
    }
}
