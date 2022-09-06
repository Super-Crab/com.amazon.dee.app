package com.amazonaws.services.iot.model;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes13.dex */
public enum DayOfWeek {
    SUN("SUN"),
    MON("MON"),
    TUE("TUE"),
    WED("WED"),
    THU("THU"),
    FRI("FRI"),
    SAT("SAT");
    
    private static final Map<String, DayOfWeek> enumMap = new HashMap();
    private String value;

    static {
        enumMap.put("SUN", SUN);
        enumMap.put("MON", MON);
        enumMap.put("TUE", TUE);
        enumMap.put("WED", WED);
        enumMap.put("THU", THU);
        enumMap.put("FRI", FRI);
        enumMap.put("SAT", SAT);
    }

    DayOfWeek(String str) {
        this.value = str;
    }

    public static DayOfWeek fromValue(String str) {
        if (str != null && !str.isEmpty()) {
            if (enumMap.containsKey(str)) {
                return enumMap.get(str);
            }
            throw new IllegalArgumentException(GeneratedOutlineSupport1.outline75("Cannot create enum from ", str, " value!"));
        }
        throw new IllegalArgumentException("Value cannot be null or empty!");
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.value;
    }
}
