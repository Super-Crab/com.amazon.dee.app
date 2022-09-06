package com.amazonaws.services.iot.model;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes13.dex */
public enum AuditTaskType {
    ON_DEMAND_AUDIT_TASK("ON_DEMAND_AUDIT_TASK"),
    SCHEDULED_AUDIT_TASK("SCHEDULED_AUDIT_TASK");
    
    private static final Map<String, AuditTaskType> enumMap = new HashMap();
    private String value;

    static {
        enumMap.put("ON_DEMAND_AUDIT_TASK", ON_DEMAND_AUDIT_TASK);
        enumMap.put("SCHEDULED_AUDIT_TASK", SCHEDULED_AUDIT_TASK);
    }

    AuditTaskType(String str) {
        this.value = str;
    }

    public static AuditTaskType fromValue(String str) {
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
