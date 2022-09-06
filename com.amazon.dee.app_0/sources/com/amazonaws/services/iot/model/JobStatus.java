package com.amazonaws.services.iot.model;

import com.amazon.clouddrive.model.ResumeState;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes13.dex */
public enum JobStatus {
    IN_PROGRESS("IN_PROGRESS"),
    CANCELED("CANCELED"),
    COMPLETED(ResumeState.COMPLETED),
    DELETION_IN_PROGRESS("DELETION_IN_PROGRESS");
    
    private static final Map<String, JobStatus> enumMap = new HashMap();
    private String value;

    static {
        enumMap.put("IN_PROGRESS", IN_PROGRESS);
        enumMap.put("CANCELED", CANCELED);
        enumMap.put(ResumeState.COMPLETED, COMPLETED);
        enumMap.put("DELETION_IN_PROGRESS", DELETION_IN_PROGRESS);
    }

    JobStatus(String str) {
        this.value = str;
    }

    public static JobStatus fromValue(String str) {
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
