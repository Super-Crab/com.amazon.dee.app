package com.amazonaws.services.iot.model;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes13.dex */
public enum AuthDecision {
    ALLOWED("ALLOWED"),
    EXPLICIT_DENY("EXPLICIT_DENY"),
    IMPLICIT_DENY("IMPLICIT_DENY");
    
    private static final Map<String, AuthDecision> enumMap = new HashMap();
    private String value;

    static {
        enumMap.put("ALLOWED", ALLOWED);
        enumMap.put("EXPLICIT_DENY", EXPLICIT_DENY);
        enumMap.put("IMPLICIT_DENY", IMPLICIT_DENY);
    }

    AuthDecision(String str) {
        this.value = str;
    }

    public static AuthDecision fromValue(String str) {
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
