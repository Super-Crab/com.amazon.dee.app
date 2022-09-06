package com.amazon.clouddrive.cdasdk.cds.search;

import com.fasterxml.jackson.annotation.JsonValue;
/* loaded from: classes11.dex */
public enum AggregationContext {
    CUSTOMER("customer"),
    FAMILY("family"),
    ALL("all");
    
    private final String value;

    AggregationContext(String str) {
        this.value = str;
    }

    @JsonValue
    public String getValue() {
        return this.value;
    }
}
