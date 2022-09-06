package com.amazon.alexa.accessory.crypto.awscryptoutils;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Provider;
import java.util.Arrays;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.ShortBufferException;
import javax.crypto.spec.SecretKeySpec;
/* loaded from: classes.dex */
public final class Hkdf {
    private static final byte[] EMPTY_ARRAY = new byte[0];
    private final String algorithm;
    private SecretKey prk = null;
    private final Provider provider;

    private Hkdf(String str, Provider provider) {
        if (str.startsWith("Hmac")) {
            this.algorithm = str;
            this.provider = provider;
            return;
        }
        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline75("Invalid algorithm ", str, ". Hkdf may only be used with Hmac algorithms."));
    }

    private void assertInitialized() throws IllegalStateException {
        if (this.prk != null) {
            return;
        }
        throw new IllegalStateException("Hkdf has not been initialized");
    }

    private Mac createMac() {
        try {
            Mac mac = Mac.getInstance(this.algorithm, this.provider);
            mac.init(this.prk);
            return mac;
        } catch (InvalidKeyException e) {
            throw new RuntimeException(e);
        } catch (NoSuchAlgorithmException e2) {
            throw new RuntimeException(e2);
        }
    }

    public static Hkdf getInstance(String str) throws NoSuchAlgorithmException {
        return new Hkdf(str, Mac.getInstance(str).getProvider());
    }

    private void unsafeInitWithoutKeyExtraction(SecretKey secretKey) throws InvalidKeyException {
        if (secretKey.getAlgorithm().equals(this.algorithm)) {
            this.prk = secretKey;
            return;
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Algorithm for the provided key must match the algorithm for this Hkdf. Expected ");
        outline107.append(this.algorithm);
        outline107.append(" but found ");
        outline107.append(secretKey.getAlgorithm());
        throw new InvalidKeyException(outline107.toString());
    }

    public byte[] deriveKey(String str, int i) throws IllegalStateException {
        return deriveKey(str != null ? str.getBytes(StandardCharsets.UTF_8) : null, i);
    }

    public void init(byte[] bArr) {
        init(bArr, null);
    }

    public byte[] deriveKey(byte[] bArr, int i) throws IllegalStateException {
        byte[] bArr2 = new byte[i];
        try {
            deriveKey(bArr, i, bArr2, 0);
            return bArr2;
        } catch (ShortBufferException e) {
            throw new RuntimeException(e);
        }
    }

    public void init(byte[] bArr, byte[] bArr2) {
        byte[] bArr3 = bArr2 == null ? EMPTY_ARRAY : (byte[]) bArr2.clone();
        byte[] bArr4 = EMPTY_ARRAY;
        try {
            try {
                Mac mac = Mac.getInstance(this.algorithm, this.provider);
                if (bArr3.length == 0) {
                    bArr3 = new byte[mac.getMacLength()];
                    Arrays.fill(bArr3, (byte) 0);
                }
                mac.init(new SecretKeySpec(bArr3, this.algorithm));
                bArr4 = mac.doFinal(bArr);
                SecretKeySpec secretKeySpec = new SecretKeySpec(bArr4, this.algorithm);
                Arrays.fill(bArr4, (byte) 0);
                unsafeInitWithoutKeyExtraction(secretKeySpec);
            } catch (GeneralSecurityException e) {
                throw new RuntimeException("Unexpected exception", e);
            }
        } finally {
            Arrays.fill(bArr4, (byte) 0);
        }
    }

    public static Hkdf getInstance(String str, String str2) throws NoSuchAlgorithmException, NoSuchProviderException {
        return new Hkdf(str, Mac.getInstance(str, str2).getProvider());
    }

    public static Hkdf getInstance(String str, Provider provider) throws NoSuchAlgorithmException {
        return new Hkdf(str, Mac.getInstance(str, provider).getProvider());
    }

    public void deriveKey(byte[] bArr, int i, byte[] bArr2, int i2) throws ShortBufferException, IllegalStateException {
        int i3;
        assertInitialized();
        if (i >= 0) {
            if (bArr2.length >= i2 + i) {
                Mac createMac = createMac();
                if (i <= createMac.getMacLength() * 255) {
                    byte b = 1;
                    byte[] bArr3 = EMPTY_ARRAY;
                    for (int i4 = 0; i4 < i; i4 = i3) {
                        try {
                            createMac.update(bArr3);
                            createMac.update(bArr);
                            createMac.update(b);
                            bArr3 = createMac.doFinal();
                            i3 = i4;
                            int i5 = 0;
                            while (i5 < bArr3.length && i3 < i) {
                                bArr2[i3] = bArr3[i5];
                                i5++;
                                i3++;
                            }
                            b = (byte) (b + 1);
                        } finally {
                            Arrays.fill(bArr3, (byte) 0);
                        }
                    }
                    return;
                }
                throw new IllegalArgumentException("Requested keys may not be longer than 255 times the underlying HMAC length.");
            }
            throw new ShortBufferException();
        }
        throw new IllegalArgumentException("Length must be a non-negative value.");
    }
}
