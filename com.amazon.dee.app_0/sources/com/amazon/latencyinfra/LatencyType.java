package com.amazon.latencyinfra;

import com.amazon.alexa.mobilytics.configuration.Constants;
/* loaded from: classes12.dex */
public enum LatencyType {
    SINGLE("single"),
    TIMELINE(Constants.TIMELINE);
    
    private final String type;

    LatencyType(String str) {
        this.type = str;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.type;
    }
}
