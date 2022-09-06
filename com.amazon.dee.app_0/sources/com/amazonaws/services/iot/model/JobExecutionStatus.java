package com.amazonaws.services.iot.model;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes13.dex */
public enum JobExecutionStatus {
    QUEUED("QUEUED"),
    IN_PROGRESS("IN_PROGRESS"),
    SUCCEEDED("SUCCEEDED"),
    FAILED("FAILED"),
    TIMED_OUT("TIMED_OUT"),
    REJECTED("REJECTED"),
    REMOVED("REMOVED"),
    CANCELED("CANCELED");
    
    private static final Map<String, JobExecutionStatus> enumMap = new HashMap();
    private String value;

    static {
        enumMap.put("QUEUED", QUEUED);
        enumMap.put("IN_PROGRESS", IN_PROGRESS);
        enumMap.put("SUCCEEDED", SUCCEEDED);
        enumMap.put("FAILED", FAILED);
        enumMap.put("TIMED_OUT", TIMED_OUT);
        enumMap.put("REJECTED", REJECTED);
        enumMap.put("REMOVED", REMOVED);
        enumMap.put("CANCELED", CANCELED);
    }

    JobExecutionStatus(String str) {
        this.value = str;
    }

    public static JobExecutionStatus fromValue(String str) {
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
