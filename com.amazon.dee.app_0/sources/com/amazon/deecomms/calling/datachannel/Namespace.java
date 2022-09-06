package com.amazon.deecomms.calling.datachannel;
/* loaded from: classes12.dex */
public enum Namespace {
    IN_CALL_CAPABILITY("com.amazon.alexa.comms.incall.capability"),
    IN_CALL_PIP("com.amazon.alexa.comms.incall.pip");
    
    private final String value;

    Namespace(String str) {
        this.value = str;
    }

    public String getValue() {
        return this.value;
    }
}
