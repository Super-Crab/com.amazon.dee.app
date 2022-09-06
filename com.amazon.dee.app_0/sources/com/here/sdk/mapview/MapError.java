package com.here.sdk.mapview;
/* loaded from: classes3.dex */
public enum MapError {
    OPERATION_IN_PROGRESS(1),
    DUPLICATE_LAYER(2),
    INVALID_DATA_SOURCE(3),
    INVALID_SCENE(4),
    INVALID_CONTENT(5),
    INVALID_STATE(6),
    UNKNOWN_LAYER(7),
    UNKNOWN(8);
    
    public final int value;

    MapError(int i) {
        this.value = i;
    }
}
