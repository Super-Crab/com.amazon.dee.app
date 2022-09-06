package com.amazon.comms.ringservice.util;
/* loaded from: classes12.dex */
public enum Capability {
    AUDIO("audio"),
    VIDEO("video");
    
    private final String name;

    Capability(String str) {
        this.name = str;
    }

    @Override // java.lang.Enum
    public String toString() {
        return name();
    }
}
