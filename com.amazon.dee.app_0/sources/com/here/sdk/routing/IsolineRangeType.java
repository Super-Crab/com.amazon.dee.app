package com.here.sdk.routing;
/* loaded from: classes3.dex */
public enum IsolineRangeType {
    DISTANCE_IN_METERS(0),
    TIME_IN_SECONDS(1),
    CONSUMPTION_IN_WATT_HOURS(2);
    
    public final int value;

    IsolineRangeType(int i) {
        this.value = i;
    }
}
