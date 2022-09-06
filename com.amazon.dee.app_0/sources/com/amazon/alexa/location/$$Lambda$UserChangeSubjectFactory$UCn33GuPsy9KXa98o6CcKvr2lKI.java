package com.amazon.alexa.location;

import com.amazon.alexa.eventbus.api.Message;
import com.amazon.alexa.eventbus.api.MessageFilter;
import com.amazon.alexa.identity.api.IdentityEvent;
import java.util.Objects;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.location.-$$Lambda$UserChangeSubjectFactory$UCn33GuPsy9KXa98o6CcKvr2lKI  reason: invalid class name */
/* loaded from: classes9.dex */
public final /* synthetic */ class $$Lambda$UserChangeSubjectFactory$UCn33GuPsy9KXa98o6CcKvr2lKI implements MessageFilter {
    public static final /* synthetic */ $$Lambda$UserChangeSubjectFactory$UCn33GuPsy9KXa98o6CcKvr2lKI INSTANCE = new $$Lambda$UserChangeSubjectFactory$UCn33GuPsy9KXa98o6CcKvr2lKI();

    private /* synthetic */ $$Lambda$UserChangeSubjectFactory$UCn33GuPsy9KXa98o6CcKvr2lKI() {
    }

    @Override // com.amazon.alexa.eventbus.api.MessageFilter
    public final boolean isMatch(Message message) {
        return Objects.equals(message.getEventType(), IdentityEvent.IDENTITY_CHANGED);
    }
}
