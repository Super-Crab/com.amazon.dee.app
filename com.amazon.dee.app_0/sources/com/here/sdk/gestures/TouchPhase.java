package com.here.sdk.gestures;
/* loaded from: classes3.dex */
enum TouchPhase {
    BEGIN(0),
    MOVE(1),
    STATIONARY(2),
    END(3),
    CANCEL(4);
    
    final int value;

    TouchPhase(int i) {
        this.value = i;
    }
}
