package com.amazonaws.services.logs.model;

import com.amazon.alexa.presence.bleconn.service.PresenceBleService;
import com.amazon.clouddrive.model.ResumeState;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes13.dex */
public enum ExportTaskStatusCode {
    CANCELLED("CANCELLED"),
    COMPLETED(ResumeState.COMPLETED),
    FAILED("FAILED"),
    PENDING("PENDING"),
    PENDING_CANCEL("PENDING_CANCEL"),
    RUNNING(PresenceBleService.ServiceState.RUNNING);
    
    private static final Map<String, ExportTaskStatusCode> enumMap = new HashMap();
    private String value;

    static {
        enumMap.put("CANCELLED", CANCELLED);
        enumMap.put(ResumeState.COMPLETED, COMPLETED);
        enumMap.put("FAILED", FAILED);
        enumMap.put("PENDING", PENDING);
        enumMap.put("PENDING_CANCEL", PENDING_CANCEL);
        enumMap.put(PresenceBleService.ServiceState.RUNNING, RUNNING);
    }

    ExportTaskStatusCode(String str) {
        this.value = str;
    }

    public static ExportTaskStatusCode fromValue(String str) {
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
