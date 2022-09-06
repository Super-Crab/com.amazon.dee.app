package com.amazon.deecomms.calling.datachannel;

import androidx.annotation.NonNull;
import com.google.common.base.Enums;
/* loaded from: classes12.dex */
public enum CallingEventName {
    PIPACTION("PIPAction"),
    CALL_SETUP("call_setup");
    
    private String value;

    CallingEventName(String str) {
        this.value = str;
    }

    public static CallingEventName lookupByEventName(String str) {
        return (CallingEventName) Enums.getIfPresent(CallingEventName.class, str).orNull();
    }

    @Override // java.lang.Enum
    @NonNull
    public String toString() {
        return this.value;
    }
}
