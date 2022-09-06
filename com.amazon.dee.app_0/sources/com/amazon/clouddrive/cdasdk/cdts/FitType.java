package com.amazon.clouddrive.cdasdk.cdts;

import com.fasterxml.jackson.annotation.JsonValue;
/* loaded from: classes11.dex */
public enum FitType {
    Bound("bound"),
    Clip("clip");
    
    private final String value;

    FitType(String str) {
        this.value = str;
    }

    @JsonValue
    public String getValue() {
        return this.value;
    }
}
