package com.amazon.deecomms.calling.datachannel;
/* loaded from: classes12.dex */
public enum Capability {
    SCREEN_EVENTS("screen_events"),
    APPLICATION("application");
    
    private final String value;

    Capability(String str) {
        this.value = str;
    }

    public String getValue() {
        return this.value;
    }
}
