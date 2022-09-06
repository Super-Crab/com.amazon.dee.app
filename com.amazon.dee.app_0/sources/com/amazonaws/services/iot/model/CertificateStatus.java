package com.amazonaws.services.iot.model;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes13.dex */
public enum CertificateStatus {
    ACTIVE("ACTIVE"),
    INACTIVE("INACTIVE"),
    REVOKED("REVOKED"),
    PENDING_TRANSFER("PENDING_TRANSFER"),
    REGISTER_INACTIVE("REGISTER_INACTIVE"),
    PENDING_ACTIVATION("PENDING_ACTIVATION");
    
    private static final Map<String, CertificateStatus> enumMap = new HashMap();
    private String value;

    static {
        enumMap.put("ACTIVE", ACTIVE);
        enumMap.put("INACTIVE", INACTIVE);
        enumMap.put("REVOKED", REVOKED);
        enumMap.put("PENDING_TRANSFER", PENDING_TRANSFER);
        enumMap.put("REGISTER_INACTIVE", REGISTER_INACTIVE);
        enumMap.put("PENDING_ACTIVATION", PENDING_ACTIVATION);
    }

    CertificateStatus(String str) {
        this.value = str;
    }

    public static CertificateStatus fromValue(String str) {
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
