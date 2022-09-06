package com.here.sdk.gestures;
/* loaded from: classes3.dex */
public enum GestureType {
    TWO_FINGER_TAP(0),
    DOUBLE_TAP(1),
    PAN(2),
    TWO_FINGER_PAN(3),
    PINCH_ROTATE(4);
    
    public final int value;

    GestureType(int i) {
        this.value = i;
    }
}
