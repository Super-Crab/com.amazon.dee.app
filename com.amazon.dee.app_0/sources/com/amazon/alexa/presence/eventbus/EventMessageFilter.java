package com.amazon.alexa.presence.eventbus;

import androidx.annotation.NonNull;
import com.amazon.alexa.eventbus.api.Message;
import com.amazon.alexa.eventbus.api.MessageFilter;
import javax.inject.Inject;
/* loaded from: classes9.dex */
public class EventMessageFilter implements MessageFilter {
    private final String matchString;

    @Inject
    public EventMessageFilter(@NonNull String str) {
        this.matchString = str;
    }

    @Override // com.amazon.alexa.eventbus.api.MessageFilter
    public boolean isMatch(@NonNull Message message) {
        if (message.getEventType() == null) {
            return false;
        }
        return message.getEventType().equals(this.matchString);
    }
}
