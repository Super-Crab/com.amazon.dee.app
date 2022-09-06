package com.here.sdk.routing;
/* loaded from: classes3.dex */
public enum RoadType {
    HIGHWAY(0),
    RURAL(1),
    URBAN(2),
    UNKNOWN(3);
    
    public final int value;

    RoadType(int i) {
        this.value = i;
    }
}
