package com.here.sdk.core;
/* loaded from: classes3.dex */
public enum UnitSystem {
    METRIC(0),
    IMPERIAL(1),
    IMPERIAL_UK(2),
    IMPERIAL_US(3);
    
    public final int value;

    UnitSystem(int i) {
        this.value = i;
    }
}
