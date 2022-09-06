package com.here.sdk.routing;
/* loaded from: classes3.dex */
public enum RoadFeatures {
    SEASONAL_CLOSURE(0),
    TOLL_ROAD(1),
    CONTROLLED_ACCESS_HIGHWAY(2),
    FERRY(3),
    CAR_SHUTTLE_TRAIN(4),
    TUNNEL(5),
    DIRT_ROAD(6),
    DIFFICULT_TURNS(7);
    
    public final int value;

    RoadFeatures(int i) {
        this.value = i;
    }
}
