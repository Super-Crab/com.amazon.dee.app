package com.amazon.alexa.featureservice.sessionManagement;

import com.amazon.alexa.eventbus.api.Message;
import com.amazon.alexa.eventbus.api.MessageFilter;
import com.amazon.alexa.lifecycle.api.LifecycleEvent;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.featureservice.sessionManagement.-$$Lambda$SessionManager$idbYDkL8ljfo8jbSclmmrqhK2Ds  reason: invalid class name */
/* loaded from: classes7.dex */
public final /* synthetic */ class $$Lambda$SessionManager$idbYDkL8ljfo8jbSclmmrqhK2Ds implements MessageFilter {
    public static final /* synthetic */ $$Lambda$SessionManager$idbYDkL8ljfo8jbSclmmrqhK2Ds INSTANCE = new $$Lambda$SessionManager$idbYDkL8ljfo8jbSclmmrqhK2Ds();

    private /* synthetic */ $$Lambda$SessionManager$idbYDkL8ljfo8jbSclmmrqhK2Ds() {
    }

    @Override // com.amazon.alexa.eventbus.api.MessageFilter
    public final boolean isMatch(Message message) {
        boolean equals;
        equals = LifecycleEvent.APPLICATION_DID_BACKGROUND.name.equals(message.getEventType());
        return equals;
    }
}
