package com.amazon.crypto.asymmetric;

import androidx.annotation.NonNull;
import com.amazon.crypto.utils.Preconditions;
import com.amazon.whispercloak.KeyUtils;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.interfaces.RSAKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Objects;
/* loaded from: classes12.dex */
public final class WithheldKey {
    private final PrivateKey privateKey;

    private WithheldKey(PrivateKey privateKey) {
        boolean z = true;
        boolean[] zArr = new boolean[1];
        zArr[0] = KeyUtils.ALGORITHM_RSA == privateKey.getAlgorithm();
        Preconditions.isTrue(zArr);
        boolean[] zArr2 = new boolean[1];
        zArr2[0] = 2048 > ((RSAKey) privateKey).getModulus().bitLength() ? false : z;
        Preconditions.isTrue(zArr2);
        this.privateKey = privateKey;
    }

    @NonNull
    public static WithheldKey from(@NonNull byte[] bArr) throws IllegalArgumentException, SecurityException {
        Preconditions.notNull(bArr);
        try {
            try {
                return new WithheldKey(KeyFactory.getInstance(KeyUtils.ALGORITHM_RSA).generatePrivate(new PKCS8EncodedKeySpec(bArr)));
            } catch (InvalidKeySpecException e) {
                throw new IllegalArgumentException(e);
            }
        } catch (NoSuchAlgorithmException e2) {
            throw new SecurityException(e2);
        }
    }

    public boolean equals(Object obj) {
        return obj != null && obj.getClass().equals(WithheldKey.class) && toJavaPrivateKey().equals(((WithheldKey) obj).toJavaPrivateKey());
    }

    public int hashCode() {
        return Objects.hash(toJavaPrivateKey());
    }

    @NonNull
    public byte[] toBytes() {
        return this.privateKey.getEncoded();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @NonNull
    public PrivateKey toJavaPrivateKey() {
        return this.privateKey;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @NonNull
    public static WithheldKey from(@NonNull PrivateKey privateKey) {
        Preconditions.notNull(privateKey);
        return new WithheldKey(privateKey);
    }
}
