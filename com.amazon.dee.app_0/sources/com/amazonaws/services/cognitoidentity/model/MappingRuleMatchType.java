package com.amazonaws.services.cognitoidentity.model;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes13.dex */
public enum MappingRuleMatchType {
    Equals("Equals"),
    Contains("Contains"),
    StartsWith("StartsWith"),
    NotEqual("NotEqual");
    
    private static final Map<String, MappingRuleMatchType> enumMap = new HashMap();
    private String value;

    static {
        enumMap.put("Equals", Equals);
        enumMap.put("Contains", Contains);
        enumMap.put("StartsWith", StartsWith);
        enumMap.put("NotEqual", NotEqual);
    }

    MappingRuleMatchType(String str) {
        this.value = str;
    }

    public static MappingRuleMatchType fromValue(String str) {
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
