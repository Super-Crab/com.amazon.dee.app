package com.amazonaws.services.kinesis.model;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes13.dex */
public enum StreamStatus {
    CREATING("CREATING"),
    DELETING("DELETING"),
    ACTIVE("ACTIVE"),
    UPDATING("UPDATING");
    
    private static final Map<String, StreamStatus> enumMap = new HashMap();
    private String value;

    static {
        enumMap.put("CREATING", CREATING);
        enumMap.put("DELETING", DELETING);
        enumMap.put("ACTIVE", ACTIVE);
        enumMap.put("UPDATING", UPDATING);
    }

    StreamStatus(String str) {
        this.value = str;
    }

    public static StreamStatus fromValue(String str) {
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
