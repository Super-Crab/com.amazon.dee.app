package com.here.sdk.gestures;
/* loaded from: classes3.dex */
public enum GestureState {
    BEGIN(0),
    UPDATE(1),
    END(2),
    CANCEL(3);
    
    public final int value;

    GestureState(int i) {
        this.value = i;
    }
}
