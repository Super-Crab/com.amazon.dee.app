package com.amazonaws.services.s3.model;

import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes13.dex */
public enum SSEAlgorithm {
    AES256("AES256"),
    KMS("aws:kms");
    
    private final String algorithm;

    SSEAlgorithm(String str) {
        this.algorithm = str;
    }

    public static SSEAlgorithm fromString(String str) {
        SSEAlgorithm[] values;
        if (str == null) {
            return null;
        }
        for (SSEAlgorithm sSEAlgorithm : values()) {
            if (sSEAlgorithm.getAlgorithm().equals(str)) {
                return sSEAlgorithm;
            }
        }
        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline72("Unsupported algorithm ", str));
    }

    public static SSEAlgorithm getDefault() {
        return AES256;
    }

    public String getAlgorithm() {
        return this.algorithm;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.algorithm;
    }
}
