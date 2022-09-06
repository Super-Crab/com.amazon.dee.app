package com.amazon.alexa.featureservice.sessionManagement;

import com.amazon.alexa.eventbus.api.Message;
import com.amazon.alexa.eventbus.api.MessageFilter;
import com.amazon.alexa.lifecycle.api.LifecycleEvent;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.featureservice.sessionManagement.-$$Lambda$SessionManager$jkTRrq2vxrMvbKqMBLwOsIZ-VD4  reason: invalid class name */
/* loaded from: classes7.dex */
public final /* synthetic */ class $$Lambda$SessionManager$jkTRrq2vxrMvbKqMBLwOsIZVD4 implements MessageFilter {
    public static final /* synthetic */ $$Lambda$SessionManager$jkTRrq2vxrMvbKqMBLwOsIZVD4 INSTANCE = new $$Lambda$SessionManager$jkTRrq2vxrMvbKqMBLwOsIZVD4();

    private /* synthetic */ $$Lambda$SessionManager$jkTRrq2vxrMvbKqMBLwOsIZVD4() {
    }

    @Override // com.amazon.alexa.eventbus.api.MessageFilter
    public final boolean isMatch(Message message) {
        boolean equals;
        equals = LifecycleEvent.APPLICATION_DID_FOREGROUND.name.equals(message.getEventType());
        return equals;
    }
}
