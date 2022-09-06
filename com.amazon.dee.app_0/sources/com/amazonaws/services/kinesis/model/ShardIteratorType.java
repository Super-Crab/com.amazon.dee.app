package com.amazonaws.services.kinesis.model;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes13.dex */
public enum ShardIteratorType {
    AT_SEQUENCE_NUMBER("AT_SEQUENCE_NUMBER"),
    AFTER_SEQUENCE_NUMBER("AFTER_SEQUENCE_NUMBER"),
    TRIM_HORIZON("TRIM_HORIZON"),
    LATEST("LATEST"),
    AT_TIMESTAMP("AT_TIMESTAMP");
    
    private static final Map<String, ShardIteratorType> enumMap = new HashMap();
    private String value;

    static {
        enumMap.put("AT_SEQUENCE_NUMBER", AT_SEQUENCE_NUMBER);
        enumMap.put("AFTER_SEQUENCE_NUMBER", AFTER_SEQUENCE_NUMBER);
        enumMap.put("TRIM_HORIZON", TRIM_HORIZON);
        enumMap.put("LATEST", LATEST);
        enumMap.put("AT_TIMESTAMP", AT_TIMESTAMP);
    }

    ShardIteratorType(String str) {
        this.value = str;
    }

    public static ShardIteratorType fromValue(String str) {
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
