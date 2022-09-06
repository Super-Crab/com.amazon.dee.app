package com.amazonaws.services.iot.model;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes13.dex */
public enum OTAUpdateStatus {
    CREATE_PENDING("CREATE_PENDING"),
    CREATE_IN_PROGRESS("CREATE_IN_PROGRESS"),
    CREATE_COMPLETE("CREATE_COMPLETE"),
    CREATE_FAILED("CREATE_FAILED");
    
    private static final Map<String, OTAUpdateStatus> enumMap = new HashMap();
    private String value;

    static {
        enumMap.put("CREATE_PENDING", CREATE_PENDING);
        enumMap.put("CREATE_IN_PROGRESS", CREATE_IN_PROGRESS);
        enumMap.put("CREATE_COMPLETE", CREATE_COMPLETE);
        enumMap.put("CREATE_FAILED", CREATE_FAILED);
    }

    OTAUpdateStatus(String str) {
        this.value = str;
    }

    public static OTAUpdateStatus fromValue(String str) {
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
