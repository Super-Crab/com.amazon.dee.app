package com.here.sdk.routing;
/* loaded from: classes3.dex */
public enum HazardousGood {
    EXPLOSIVE(0),
    GAS(1),
    FLAMMABLE(2),
    COMBUSTIBLE(3),
    ORGANIC(4),
    POISON(5),
    RADIOACTIVE(6),
    CORROSIVE(7),
    POISONOUS_INHALATION(8),
    HARMFUL_TO_WATER(9),
    OTHER(10);
    
    public final int value;

    HazardousGood(int i) {
        this.value = i;
    }
}
