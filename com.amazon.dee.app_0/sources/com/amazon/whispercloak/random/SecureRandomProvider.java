package com.amazon.whispercloak.random;

import java.security.SecureRandom;
/* loaded from: classes13.dex */
public class SecureRandomProvider {
    private static SecureRandomProvider mInstance;
    private final SecureRandom mSecureRandom = new SecureRandom();

    private SecureRandomProvider() {
    }

    public static SecureRandom getInstance() {
        if (mInstance == null) {
            mInstance = new SecureRandomProvider();
        }
        return mInstance.mSecureRandom;
    }
}
