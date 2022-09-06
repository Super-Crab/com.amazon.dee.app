package com.amazonaws.services.iot.model;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes13.dex */
public enum ResourceType {
    DEVICE_CERTIFICATE("DEVICE_CERTIFICATE"),
    CA_CERTIFICATE("CA_CERTIFICATE"),
    IOT_POLICY("IOT_POLICY"),
    COGNITO_IDENTITY_POOL("COGNITO_IDENTITY_POOL"),
    CLIENT_ID("CLIENT_ID"),
    ACCOUNT_SETTINGS("ACCOUNT_SETTINGS");
    
    private static final Map<String, ResourceType> enumMap = new HashMap();
    private String value;

    static {
        enumMap.put("DEVICE_CERTIFICATE", DEVICE_CERTIFICATE);
        enumMap.put("CA_CERTIFICATE", CA_CERTIFICATE);
        enumMap.put("IOT_POLICY", IOT_POLICY);
        enumMap.put("COGNITO_IDENTITY_POOL", COGNITO_IDENTITY_POOL);
        enumMap.put("CLIENT_ID", CLIENT_ID);
        enumMap.put("ACCOUNT_SETTINGS", ACCOUNT_SETTINGS);
    }

    ResourceType(String str) {
        this.value = str;
    }

    public static ResourceType fromValue(String str) {
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
