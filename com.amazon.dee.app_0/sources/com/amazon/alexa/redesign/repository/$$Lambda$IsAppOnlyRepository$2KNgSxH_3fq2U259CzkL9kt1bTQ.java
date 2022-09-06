package com.amazon.alexa.redesign.repository;

import com.amazon.alexa.eventbus.api.Message;
import com.amazon.alexa.eventbus.api.MessageFilter;
import com.amazon.alexa.identity.api.IdentityEvent;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.redesign.repository.-$$Lambda$IsAppOnlyRepository$2KNgSxH_3fq2U259CzkL9kt1bTQ  reason: invalid class name */
/* loaded from: classes10.dex */
public final /* synthetic */ class $$Lambda$IsAppOnlyRepository$2KNgSxH_3fq2U259CzkL9kt1bTQ implements MessageFilter {
    public static final /* synthetic */ $$Lambda$IsAppOnlyRepository$2KNgSxH_3fq2U259CzkL9kt1bTQ INSTANCE = new $$Lambda$IsAppOnlyRepository$2KNgSxH_3fq2U259CzkL9kt1bTQ();

    private /* synthetic */ $$Lambda$IsAppOnlyRepository$2KNgSxH_3fq2U259CzkL9kt1bTQ() {
    }

    @Override // com.amazon.alexa.eventbus.api.MessageFilter
    public final boolean isMatch(Message message) {
        return IdentityEvent.IDENTITY_SIGN_OUT_SUCCESS.equals(message.getEventType());
    }
}
