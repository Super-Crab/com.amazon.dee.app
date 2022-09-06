package com.amazon.alexa.handsfree.devices.constants;

import androidx.annotation.NonNull;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes8.dex */
public enum Carrier {
    NO_CARRIER("NO_CARRIER"),
    VESPER("verizon"),
    ATT("att"),
    TM("t-mobile"),
    US_CELLULAR("US_CELLULAR"),
    CRICKET("Cricket"),
    MPCS("MPCS"),
    Canada("Canada"),
    DISH("Dish");
    
    private final String mCarrier;

    Carrier(@NonNull String str) {
        this.mCarrier = str;
    }

    @NonNull
    public static List<String> getAllCarriers() {
        ArrayList arrayList = new ArrayList();
        for (Carrier carrier : values()) {
            arrayList.add(carrier.getCarrier());
        }
        return arrayList;
    }

    @NonNull
    public String getCarrier() {
        return this.mCarrier;
    }
}
