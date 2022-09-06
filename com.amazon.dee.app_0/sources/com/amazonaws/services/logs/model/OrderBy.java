package com.amazonaws.services.logs.model;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes13.dex */
public enum OrderBy {
    LogStreamName("LogStreamName"),
    LastEventTime("LastEventTime");
    
    private static final Map<String, OrderBy> enumMap = new HashMap();
    private String value;

    static {
        enumMap.put("LogStreamName", LogStreamName);
        enumMap.put("LastEventTime", LastEventTime);
    }

    OrderBy(String str) {
        this.value = str;
    }

    public static OrderBy fromValue(String str) {
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
