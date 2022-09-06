package com.amazon.alexa.eventbus.message;

import androidx.annotation.NonNull;
import com.amazon.alexa.eventbus.api.Message;
import com.amazon.alexa.eventbus.api.MessageFilter;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes7.dex */
public class EventTypeMessageFilter implements MessageFilter {
    private String matchString;

    public EventTypeMessageFilter(@NonNull String str) {
        this.matchString = str;
    }

    @Override // com.amazon.alexa.eventbus.api.MessageFilter
    public boolean isMatch(@NonNull Message message) {
        if (message.getEventType() == null) {
            return false;
        }
        String str = this.matchString;
        if (str.charAt(str.length() - 1) == '*') {
            return message.getEventType().startsWith(GeneratedOutlineSupport1.outline50(this.matchString, -2, 0));
        }
        return message.getEventType().equals(this.matchString);
    }
}
