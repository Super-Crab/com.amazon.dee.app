package com.amazonaws.services.iot.model;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes13.dex */
public enum ThingIndexingMode {
    OFF("OFF"),
    REGISTRY("REGISTRY"),
    REGISTRY_AND_SHADOW("REGISTRY_AND_SHADOW");
    
    private static final Map<String, ThingIndexingMode> enumMap = new HashMap();
    private String value;

    static {
        enumMap.put("OFF", OFF);
        enumMap.put("REGISTRY", REGISTRY);
        enumMap.put("REGISTRY_AND_SHADOW", REGISTRY_AND_SHADOW);
    }

    ThingIndexingMode(String str) {
        this.value = str;
    }

    public static ThingIndexingMode fromValue(String str) {
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
