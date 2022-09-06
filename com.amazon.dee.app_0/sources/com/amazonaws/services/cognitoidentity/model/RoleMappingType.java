package com.amazonaws.services.cognitoidentity.model;

import com.amazon.identity.auth.device.datastore.DatabaseHelper;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes13.dex */
public enum RoleMappingType {
    Token(DatabaseHelper.authorizationToken_Token),
    Rules("Rules");
    
    private static final Map<String, RoleMappingType> enumMap = new HashMap();
    private String value;

    static {
        enumMap.put(DatabaseHelper.authorizationToken_Token, Token);
        enumMap.put("Rules", Rules);
    }

    RoleMappingType(String str) {
        this.value = str;
    }

    public static RoleMappingType fromValue(String str) {
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
