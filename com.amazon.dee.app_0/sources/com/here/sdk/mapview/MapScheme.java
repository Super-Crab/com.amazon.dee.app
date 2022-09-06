package com.here.sdk.mapview;
/* loaded from: classes3.dex */
public enum MapScheme {
    NORMAL_DAY(0),
    NORMAL_NIGHT(1),
    GREY_DAY(2),
    GREY_NIGHT(3),
    SATELLITE(4),
    HYBRID_DAY(5),
    HYBRID_NIGHT(6);
    
    public final int value;

    MapScheme(int i) {
        this.value = i;
    }
}
