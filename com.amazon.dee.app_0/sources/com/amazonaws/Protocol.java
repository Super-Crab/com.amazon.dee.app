package com.amazonaws;
/* loaded from: classes13.dex */
public enum Protocol {
    HTTP("http"),
    HTTPS("https");
    
    private final String protocol;

    Protocol(String str) {
        this.protocol = str;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.protocol;
    }
}
