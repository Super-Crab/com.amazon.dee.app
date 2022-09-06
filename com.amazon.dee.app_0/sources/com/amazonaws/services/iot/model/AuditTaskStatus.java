package com.amazonaws.services.iot.model;

import com.amazon.clouddrive.model.ResumeState;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes13.dex */
public enum AuditTaskStatus {
    IN_PROGRESS("IN_PROGRESS"),
    COMPLETED(ResumeState.COMPLETED),
    FAILED("FAILED"),
    CANCELED("CANCELED");
    
    private static final Map<String, AuditTaskStatus> enumMap = new HashMap();
    private String value;

    static {
        enumMap.put("IN_PROGRESS", IN_PROGRESS);
        enumMap.put(ResumeState.COMPLETED, COMPLETED);
        enumMap.put("FAILED", FAILED);
        enumMap.put("CANCELED", CANCELED);
    }

    AuditTaskStatus(String str) {
        this.value = str;
    }

    public static AuditTaskStatus fromValue(String str) {
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
