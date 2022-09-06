package com.amazon.clouddrive.model;
/* loaded from: classes11.dex */
public enum FitType {
    Bound("bound"),
    Clip("clip");
    
    private final String value;

    FitType(String str) {
        this.value = str;
    }

    public String getValue() {
        return this.value;
    }

    public boolean isDefault() {
        return Bound.getValue().equals(getValue());
    }
}
