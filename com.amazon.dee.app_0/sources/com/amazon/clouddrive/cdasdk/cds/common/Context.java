package com.amazon.clouddrive.cdasdk.cds.common;

import com.fasterxml.jackson.annotation.JsonValue;
/* loaded from: classes11.dex */
public enum Context {
    CUSTOMER("customer"),
    FAMILY("family"),
    ALL("all");
    
    private final String value;

    Context(String str) {
        this.value = str;
    }

    @JsonValue
    public String getValue() {
        return this.value;
    }
}
