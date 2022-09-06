package com.here.sdk.core;
/* loaded from: classes3.dex */
public enum MetadataType {
    CUSTOM(0),
    DOUBLE(1),
    GEO_COORDINATES(2),
    INTEGER(3),
    STRING(4);
    
    public final int value;

    MetadataType(int i) {
        this.value = i;
    }
}
