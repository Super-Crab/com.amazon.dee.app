package com.here.sdk.search;
/* loaded from: classes3.dex */
public enum PlaceType {
    UNIT(0),
    HOUSE_NUMBER(1),
    HOUSE_NUMBER_INTERPOLATED(2),
    ADDRESS_BLOCK(3),
    STREET(4),
    LOCALITY(5),
    ADMINISTRATIVE_AREA(6),
    POI(7);
    
    public final int value;

    PlaceType(int i) {
        this.value = i;
    }
}
