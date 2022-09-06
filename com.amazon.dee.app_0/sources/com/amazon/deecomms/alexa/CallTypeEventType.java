package com.amazon.deecomms.alexa;

import androidx.annotation.NonNull;
import com.amazon.deecomms.calling.incallcommands.constants.InCallEventsConstants;
/* loaded from: classes12.dex */
public enum CallTypeEventType {
    OUTBOUND_CALL_REQUEST(InCallEventsConstants.OUTBOUND_CALL_REQUEST);
    
    @NonNull
    private final String eventName;

    CallTypeEventType(@NonNull String str) {
        this.eventName = str;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.eventName;
    }
}
