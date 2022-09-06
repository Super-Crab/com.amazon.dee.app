package com.amazon.alexa.rangecontroller.lib.model;

import edu.umd.cs.findbugs.annotations.NonNull;
/* loaded from: classes9.dex */
public enum PropertyName {
    TIMESTAMP("timestamp");
    
    private final String name;

    PropertyName(@NonNull String str) {
        this.name = str;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.name;
    }
}
