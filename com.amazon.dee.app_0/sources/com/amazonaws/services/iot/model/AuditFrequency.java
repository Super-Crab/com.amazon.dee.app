package com.amazonaws.services.iot.model;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes13.dex */
public enum AuditFrequency {
    DAILY("DAILY"),
    WEEKLY("WEEKLY"),
    BIWEEKLY("BIWEEKLY"),
    MONTHLY("MONTHLY");
    
    private static final Map<String, AuditFrequency> enumMap = new HashMap();
    private String value;

    static {
        enumMap.put("DAILY", DAILY);
        enumMap.put("WEEKLY", WEEKLY);
        enumMap.put("BIWEEKLY", BIWEEKLY);
        enumMap.put("MONTHLY", MONTHLY);
    }

    AuditFrequency(String str) {
        this.value = str;
    }

    public static AuditFrequency fromValue(String str) {
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
