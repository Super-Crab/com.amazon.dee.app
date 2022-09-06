package com.amazon.alexa.rangecontroller.lib.model;

import edu.umd.cs.findbugs.annotations.NonNull;
/* loaded from: classes9.dex */
public enum HeaderName {
    ADJUST_RANGE_VALUE("AdjustRangeValue"),
    CHANGE_REPORT("ChangeReport"),
    RESPONSE("Response"),
    ERROR_RESPONSE("ErrorResponse");
    
    private final String name;

    HeaderName(@NonNull String str) {
        this.name = str;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.name;
    }
}
