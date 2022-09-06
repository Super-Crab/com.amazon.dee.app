package com.here.sdk.routing;
/* loaded from: classes3.dex */
public enum SectionTransportMode {
    CAR(0),
    TRUCK(1),
    PEDESTRIAN(2),
    FERRY(3),
    CAR_SHUTTLE_TRAIN(4),
    SCOOTER(5);
    
    public final int value;

    SectionTransportMode(int i) {
        this.value = i;
    }
}
