package com.amazonaws.mobileconnectors.iot;
/* loaded from: classes13.dex */
enum PEMObjectType {
    PRIVATE_KEY_PKCS1("-----BEGIN RSA PRIVATE KEY-----"),
    PRIVATE_KEY_PKCS8("-----BEGIN PRIVATE KEY-----"),
    PUBLIC_KEY_X509("-----BEGIN PUBLIC KEY-----"),
    CERTIFICATE_X509("-----BEGIN CERTIFICATE-----");
    
    private final String beginMarker;

    PEMObjectType(String str) {
        this.beginMarker = str;
    }

    public static PEMObjectType fromBeginMarker(String str) {
        PEMObjectType[] values;
        for (PEMObjectType pEMObjectType : values()) {
            if (pEMObjectType.getBeginMarker().equals(str)) {
                return pEMObjectType;
            }
        }
        return null;
    }

    public String getBeginMarker() {
        return this.beginMarker;
    }
}
