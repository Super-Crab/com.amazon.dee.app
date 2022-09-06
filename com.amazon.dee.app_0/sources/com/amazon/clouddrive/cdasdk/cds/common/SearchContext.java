package com.amazon.clouddrive.cdasdk.cds.common;

import com.amazon.clouddrive.cdasdk.aps.account.FeatureName;
import com.fasterxml.jackson.annotation.JsonValue;
/* loaded from: classes11.dex */
public enum SearchContext {
    CUSTOMER("customer"),
    FAMILY("family"),
    GROUPS(FeatureName.GROUPS),
    ALL("all");
    
    private final String value;

    SearchContext(String str) {
        this.value = str;
    }

    @JsonValue
    public String getValue() {
        return this.value;
    }
}
