package com.amazon.crypto.asymmetric;

import androidx.annotation.NonNull;
import com.amazon.crypto.utils.Preconditions;
import com.amazon.whispercloak.KeyUtils;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.interfaces.RSAKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Objects;
/* loaded from: classes12.dex */
public final class PublishedKey {
    private final PublicKey publicKey;

    private PublishedKey(PublicKey publicKey) {
        boolean z = true;
        Preconditions.isTrue(KeyUtils.ALGORITHM_RSA.equals(publicKey.getAlgorithm()));
        boolean[] zArr = new boolean[1];
        zArr[0] = 2048 > ((RSAKey) publicKey).getModulus().bitLength() ? false : z;
        Preconditions.isTrue(zArr);
        this.publicKey = publicKey;
    }

    @NonNull
    public static PublishedKey from(@NonNull byte[] bArr) throws IllegalArgumentException, SecurityException {
        Preconditions.notNull(bArr);
        try {
            try {
                return new PublishedKey(KeyFactory.getInstance(KeyUtils.ALGORITHM_RSA).generatePublic(new X509EncodedKeySpec(bArr)));
            } catch (InvalidKeySpecException e) {
                throw new IllegalArgumentException(e);
            }
        } catch (NoSuchAlgorithmException e2) {
            throw new SecurityException(e2);
        }
    }

    public boolean equals(Object obj) {
        return obj != null && obj.getClass().equals(PublishedKey.class) && toJavaPublicKey().equals(((PublishedKey) obj).toJavaPublicKey());
    }

    public int hashCode() {
        return Objects.hash(toJavaPublicKey());
    }

    public byte[] toBytes() {
        return this.publicKey.getEncoded();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public PublicKey toJavaPublicKey() {
        return this.publicKey;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @NonNull
    public static PublishedKey from(@NonNull PublicKey publicKey) {
        Preconditions.notNull(publicKey);
        return new PublishedKey(publicKey);
    }
}
