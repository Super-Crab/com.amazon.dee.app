package com.amazon.alexa.presence.bleconn.identity.encryption;

import com.amazon.alexa.presence.bleconn.helpers.ByteHelper;
import com.amazonaws.services.s3.internal.crypto.JceEncryptionConstants;
import java.nio.ByteBuffer;
import java.util.Objects;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
/* loaded from: classes9.dex */
class RelationshipTokenCryptographyModule implements EncryptionModule, DecryptionModule {
    private static final String TRANSFORMATION = "AES/CBC/NoPadding";
    private final Cipher decryptionCipher;
    private final Cipher encryptionCipher;

    public RelationshipTokenCryptographyModule(byte[] bArr) {
        Objects.requireNonNull(bArr, "aes128key");
        this.encryptionCipher = aes128Cipher(bArr, 1);
        this.decryptionCipher = aes128Cipher(bArr, 2);
    }

    private static Cipher aes128Cipher(byte[] bArr, int i) {
        try {
            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            cipher.init(i, new SecretKeySpec(bArr, JceEncryptionConstants.SYMMETRIC_KEY_ALGORITHM), new IvParameterSpec(new byte[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}));
            return cipher;
        } catch (Throwable th) {
            throw new RuntimeException(th);
        }
    }

    private static byte[] process(Cipher cipher, byte[] bArr) {
        try {
            ByteBuffer wrap = ByteBuffer.wrap(bArr);
            ByteBuffer allocate = ByteBuffer.allocate(cipher.getOutputSize(bArr.length));
            int i = 0;
            while (wrap.remaining() > 0) {
                i += cipher.update(wrap, allocate);
            }
            return ByteHelper.slice(allocate.array(), 0, i + cipher.doFinal(wrap, allocate));
        } catch (Throwable th) {
            throw new RuntimeException(th);
        }
    }

    @Override // com.amazon.alexa.presence.bleconn.identity.encryption.DecryptionModule
    public byte[] decrypt(byte[] bArr) {
        return process(this.decryptionCipher, bArr);
    }

    @Override // com.amazon.alexa.presence.bleconn.identity.encryption.EncryptionModule
    public byte[] encrypt(byte[] bArr) {
        return process(this.encryptionCipher, bArr);
    }
}
