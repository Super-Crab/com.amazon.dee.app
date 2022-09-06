package com.amazon.identity.auth.device.utils;

import org.apache.commons.codec.digest.MessageDigestAlgorithms;
/* loaded from: classes12.dex */
public enum HashAlgorithm {
    MD5(MessageDigestAlgorithms.MD5),
    SHA_256("SHA-256");
    
    private String algorithmName;

    HashAlgorithm(String str) {
        this.algorithmName = str;
    }

    public String getAlgorithmName() {
        return this.algorithmName;
    }
}
