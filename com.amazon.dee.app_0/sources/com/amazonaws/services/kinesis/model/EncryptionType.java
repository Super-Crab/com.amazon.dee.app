package com.amazonaws.services.kinesis.model;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes13.dex */
public enum EncryptionType {
    NONE("NONE"),
    KMS("KMS");
    
    private static final Map<String, EncryptionType> enumMap = new HashMap();
    private String value;

    static {
        enumMap.put("NONE", NONE);
        enumMap.put("KMS", KMS);
    }

    EncryptionType(String str) {
        this.value = str;
    }

    public static EncryptionType fromValue(String str) {
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
