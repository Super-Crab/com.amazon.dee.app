package com.amazon.crypto.symmetric;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.crypto.ByteCipherSymmetric;
import com.amazon.crypto.utils.ByteArray;
import com.amazon.crypto.utils.Preconditions;
import java.security.GeneralSecurityException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Random;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
/* loaded from: classes12.dex */
final class AndroidSymmetricCipher implements ByteCipherSymmetric {
    private static final String CIPHER_AES_CGM = "AES/GCM/NoPadding";
    private static final int GCM_TAG_SIZE_IN_BITS = 128;
    private static final int IV_LENGTH = 12;
    private final SecretKey secretKey;
    private static final byte[] EMPTY_BYTES = new byte[0];
    private static final Random random = new SecureRandom();

    private AndroidSymmetricCipher(SymmetricKey symmetricKey) {
        this.secretKey = symmetricKey.toJavaSecretKey();
    }

    public static AndroidSymmetricCipher using(@NonNull SymmetricKey symmetricKey) {
        Preconditions.notNull(symmetricKey);
        return new AndroidSymmetricCipher(symmetricKey);
    }

    @Override // com.amazon.crypto.ByteDecryptor
    @Nullable
    public byte[] decrypt(@Nullable byte[] bArr) throws SecurityException {
        return bArr == null ? bArr : decrypt(bArr, EMPTY_BYTES);
    }

    @Override // com.amazon.crypto.ByteEncryptor
    @Nullable
    public byte[] encrypt(@Nullable byte[] bArr) throws SecurityException {
        return bArr == null ? bArr : encrypt(bArr, EMPTY_BYTES);
    }

    @Override // com.amazon.crypto.ByteDecryptorSymmetric
    public byte[] decrypt(@Nullable byte[] bArr, byte[] bArr2) throws SecurityException {
        if (bArr != null && bArr2 != null) {
            if (bArr.length >= 12) {
                try {
                    Cipher cipher = Cipher.getInstance(CIPHER_AES_CGM);
                    int length = bArr.length - 12;
                    cipher.init(2, this.secretKey, new GCMParameterSpec(128, bArr, length, 12));
                    cipher.updateAAD(bArr2);
                    return cipher.doFinal(bArr, 0, length);
                } catch (GeneralSecurityException e) {
                    throw new SecurityException(e);
                }
            }
            throw new IllegalArgumentException("cipherBytes too short");
        }
        throw new IllegalArgumentException();
    }

    @Override // com.amazon.crypto.ByteEncryptorSymmetric
    public byte[] encrypt(byte[] bArr, byte[] bArr2) throws SecurityException {
        if (bArr != null && bArr2 != null) {
            try {
                Cipher cipher = Cipher.getInstance(CIPHER_AES_CGM);
                byte[] random2 = ByteArray.random(12);
                cipher.init(1, this.secretKey, new GCMParameterSpec(128, random2));
                cipher.updateAAD(bArr2);
                byte[] doFinal = cipher.doFinal(bArr);
                byte[] copyOf = Arrays.copyOf(doFinal, doFinal.length + random2.length);
                System.arraycopy(random2, 0, copyOf, doFinal.length, random2.length);
                return copyOf;
            } catch (GeneralSecurityException e) {
                throw new SecurityException(e);
            }
        }
        throw new IllegalArgumentException();
    }
}
