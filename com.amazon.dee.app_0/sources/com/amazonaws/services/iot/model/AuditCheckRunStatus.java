package com.amazonaws.services.iot.model;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes13.dex */
public enum AuditCheckRunStatus {
    IN_PROGRESS("IN_PROGRESS"),
    WAITING_FOR_DATA_COLLECTION("WAITING_FOR_DATA_COLLECTION"),
    CANCELED("CANCELED"),
    COMPLETED_COMPLIANT("COMPLETED_COMPLIANT"),
    COMPLETED_NON_COMPLIANT("COMPLETED_NON_COMPLIANT"),
    FAILED("FAILED");
    
    private static final Map<String, AuditCheckRunStatus> enumMap = new HashMap();
    private String value;

    static {
        enumMap.put("IN_PROGRESS", IN_PROGRESS);
        enumMap.put("WAITING_FOR_DATA_COLLECTION", WAITING_FOR_DATA_COLLECTION);
        enumMap.put("CANCELED", CANCELED);
        enumMap.put("COMPLETED_COMPLIANT", COMPLETED_COMPLIANT);
        enumMap.put("COMPLETED_NON_COMPLIANT", COMPLETED_NON_COMPLIANT);
        enumMap.put("FAILED", FAILED);
    }

    AuditCheckRunStatus(String str) {
        this.value = str;
    }

    public static AuditCheckRunStatus fromValue(String str) {
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
