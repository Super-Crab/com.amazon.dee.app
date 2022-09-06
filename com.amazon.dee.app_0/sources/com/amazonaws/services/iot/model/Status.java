package com.amazonaws.services.iot.model;

import com.amazon.photos.uploader.internal.workers.UploadWorker;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes13.dex */
public enum Status {
    InProgress("InProgress"),
    Completed("Completed"),
    Failed(UploadWorker.FAILED_PREFIX),
    Cancelled("Cancelled"),
    Cancelling("Cancelling");
    
    private static final Map<String, Status> enumMap = new HashMap();
    private String value;

    static {
        enumMap.put("InProgress", InProgress);
        enumMap.put("Completed", Completed);
        enumMap.put(UploadWorker.FAILED_PREFIX, Failed);
        enumMap.put("Cancelled", Cancelled);
        enumMap.put("Cancelling", Cancelling);
    }

    Status(String str) {
        this.value = str;
    }

    public static Status fromValue(String str) {
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
