package com.amazon.whispercloak.keygen.provider;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.crypto.SecretKey;
/* loaded from: classes13.dex */
public class WhisperCloakSecretKey implements SecretKey {
    private static final int AES_KEY_LENGTH = 16;
    private final String algorithm;
    private final byte[] encodedKey = new byte[16];

    public WhisperCloakSecretKey(SecretKey secretKey) {
        try {
            System.arraycopy(MessageDigest.getInstance("SHA-256").digest(secretKey.getEncoded()), 0, this.encodedKey, 0, 16);
            this.algorithm = secretKey.getAlgorithm();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("SHA-256 algorithm not supported", e);
        }
    }

    @Override // java.security.Key
    public String getAlgorithm() {
        return this.algorithm;
    }

    @Override // java.security.Key
    public byte[] getEncoded() {
        return this.encodedKey;
    }

    @Override // java.security.Key
    public String getFormat() {
        return "RAW";
    }
}
