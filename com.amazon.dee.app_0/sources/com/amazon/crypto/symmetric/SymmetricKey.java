package com.amazon.crypto.symmetric;

import androidx.annotation.NonNull;
import com.amazon.crypto.utils.Preconditions;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
/* loaded from: classes12.dex */
public final class SymmetricKey {
    static final String ALGORITHM = "AES";
    static final int KEY_SIZE_BITS = 256;
    private final SecretKey secretKey;

    private SymmetricKey(SecretKey secretKey) {
        boolean z = true;
        Preconditions.isTrue("AES".equals(secretKey.getAlgorithm()));
        boolean[] zArr = new boolean[1];
        zArr[0] = 256 != secretKey.getEncoded().length * 8 ? false : z;
        Preconditions.isTrue(zArr);
        this.secretKey = secretKey;
    }

    @NonNull
    public static SymmetricKey from(@NonNull byte[] bArr) throws IllegalArgumentException {
        Preconditions.notNull(bArr);
        return new SymmetricKey(new SecretKeySpec(bArr, 0, bArr.length, "AES"));
    }

    @NonNull
    public static SymmetricKey generate() throws SecurityException {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            keyGenerator.init(256, new SecureRandom());
            return new SymmetricKey(keyGenerator.generateKey());
        } catch (NoSuchAlgorithmException e) {
            throw new SecurityException(e);
        }
    }

    @NonNull
    public byte[] toBytes() {
        return this.secretKey.getEncoded();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @NonNull
    public SecretKey toJavaSecretKey() {
        return this.secretKey;
    }

    @NonNull
    static SymmetricKey from(@NonNull SecretKey secretKey) {
        Preconditions.notNull(secretKey);
        return new SymmetricKey(secretKey);
    }
}
