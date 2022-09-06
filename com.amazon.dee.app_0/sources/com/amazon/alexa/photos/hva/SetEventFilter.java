package com.amazon.alexa.photos.hva;

import androidx.annotation.NonNull;
import com.amazon.alexa.eventbus.api.Message;
import com.amazon.alexa.eventbus.api.MessageFilter;
import java.util.Set;
/* loaded from: classes9.dex */
class SetEventFilter implements MessageFilter {
    @NonNull
    private final Set<String> events;

    public SetEventFilter(@NonNull Set<String> set) {
        this.events = set;
    }

    @Override // com.amazon.alexa.eventbus.api.MessageFilter
    public boolean isMatch(@NonNull Message message) {
        return this.events.contains(message.getEventType());
    }
}
