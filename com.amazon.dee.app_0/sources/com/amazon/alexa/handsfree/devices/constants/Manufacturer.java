package com.amazon.alexa.handsfree.devices.constants;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes8.dex */
public enum Manufacturer {
    ACME("TCL"),
    LAMBDA("LGE"),
    SIGMA("Sony"),
    MIKE("motorola"),
    XRAY("Xiaomi"),
    QUEBEC("QUALCOMM"),
    OSCAR("OnePlus"),
    HAWKING("HAWKING"),
    NEWTON("NEWTON"),
    FARADAY("FARADAY"),
    GALILEI("GALILEI"),
    ROMEO("realme"),
    ALPS("alps"),
    PIXEL("Google"),
    OPERA("OPPO");
    
    private final String mManufacturer;

    Manufacturer(@NonNull String str) {
        this.mManufacturer = str;
    }

    @NonNull
    public static List<String> getAllManufacturers() {
        ArrayList arrayList = new ArrayList();
        for (Manufacturer manufacturer : values()) {
            arrayList.add(manufacturer.getManufacturer());
        }
        return arrayList;
    }

    @NonNull
    public String getManufacturer() {
        return this.mManufacturer;
    }

    @Nullable
    public static Manufacturer getManufacturer(@NonNull String str) {
        Manufacturer[] values;
        for (Manufacturer manufacturer : values()) {
            if (manufacturer.getManufacturer().equals(str)) {
                return manufacturer;
            }
        }
        return null;
    }
}
