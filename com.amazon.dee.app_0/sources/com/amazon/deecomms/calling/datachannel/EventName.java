package com.amazon.deecomms.calling.datachannel;
/* loaded from: classes12.dex */
public enum EventName {
    RESERVE_IN_CALL_CONTROLS("reserve_in_call_controls"),
    SCREEN_EVENT("screen_event"),
    BEGIN_APPLICATION("begin_application"),
    EXIT_APPLICATION("exit_application"),
    NEW_SCREEN_ORIENTATION("new_screen_orientation");
    
    private final String value;

    EventName(String str) {
        this.value = str;
    }

    public String getValue() {
        return this.value;
    }
}
