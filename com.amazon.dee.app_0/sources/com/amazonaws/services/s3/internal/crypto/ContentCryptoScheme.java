package com.amazonaws.services.s3.internal.crypto;

import com.amazonaws.AmazonClientException;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.nio.ByteBuffer;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Provider;
import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
@Deprecated
/* loaded from: classes13.dex */
abstract class ContentCryptoScheme {
    private static final int BYTE_SIZE = 4;
    private static final int CBC_SHIFT_VALUE = 48;
    private static final int DEFAULT_BIT_COUNTER = 16;
    private static final int DEFAULT_RIGHTMOST_BIT_START = 12;
    private static final int GCM_SHIFT_VALUE = 32;
    private static final int LONG_BYTE_SIZE = 8;
    private static final long LONG_VALUE = 1;
    static final long MAX_CBC_BYTES = 4503599627370496L;
    static final long MAX_CTR_BYTES = -1;
    static final long MAX_GCM_BLOCKS = 4294967294L;
    static final long MAX_GCM_BYTES = 68719476704L;
    static final ContentCryptoScheme AES_CBC = new AesCbc();
    static final ContentCryptoScheme AES_GCM = new AesGcm();
    static final ContentCryptoScheme AES_CTR = new AesCtr();

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ContentCryptoScheme fromCEKAlgo(String str) {
        return fromCEKAlgo(str, false);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static byte[] incrementBlocks(byte[] bArr, long j) {
        if (j == 0) {
            return bArr;
        }
        if (bArr == null || bArr.length != 16) {
            throw new IllegalArgumentException();
        }
        if (j <= MAX_GCM_BLOCKS) {
            ByteBuffer allocate = ByteBuffer.allocate(8);
            for (int i = 12; i <= 15; i++) {
                allocate.put(i - 8, bArr[i]);
            }
            long j2 = allocate.getLong() + j;
            if (j2 <= MAX_GCM_BLOCKS) {
                allocate.rewind();
                byte[] array = allocate.putLong(j2).array();
                for (int i2 = 12; i2 <= 15; i2++) {
                    bArr[i2] = array[i2 - 8];
                }
                return bArr;
            }
            throw new IllegalStateException();
        }
        throw new IllegalStateException();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public byte[] adjustIV(byte[] bArr, long j) {
        return bArr;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public CipherLite createAuxillaryCipher(SecretKey secretKey, byte[] bArr, int i, Provider provider, long j) throws NoSuchAlgorithmException, NoSuchProviderException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException {
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public CipherLite createCipherLite(SecretKey secretKey, byte[] bArr, int i, Provider provider) {
        Cipher cipher;
        String specificCipherProvider = getSpecificCipherProvider();
        try {
            if (provider != null) {
                cipher = Cipher.getInstance(getCipherAlgorithm(), provider);
            } else if (specificCipherProvider != null) {
                cipher = Cipher.getInstance(getCipherAlgorithm(), specificCipherProvider);
            } else {
                cipher = Cipher.getInstance(getCipherAlgorithm());
            }
            cipher.init(i, secretKey, new IvParameterSpec(bArr));
            return newCipherLite(cipher, secretKey, i);
        } catch (Exception e) {
            if (e instanceof RuntimeException) {
                throw ((RuntimeException) e);
            }
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Unable to build cipher: ");
            outline107.append(e.getMessage());
            outline107.append("\nMake sure you have the JCE unlimited strength policy files installed and configured for your JVM");
            throw new AmazonClientException(outline107.toString(), e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract int getBlockSizeInBytes();

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract String getCipherAlgorithm();

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract int getIVLengthInBytes();

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract String getKeyGeneratorAlgorithm();

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract int getKeyLengthInBits();

    /* JADX INFO: Access modifiers changed from: package-private */
    public final String getKeySpec() {
        return getKeyGeneratorAlgorithm() + "_" + getKeyLengthInBits();
    }

    abstract long getMaxPlaintextSize();

    String getSpecificCipherProvider() {
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getTagLengthInBits() {
        return 0;
    }

    protected CipherLite newCipherLite(Cipher cipher, SecretKey secretKey, int i) {
        return new CipherLite(cipher, this, secretKey, i);
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("cipherAlgo=");
        outline107.append(getCipherAlgorithm());
        outline107.append(", blockSizeInBytes=");
        outline107.append(getBlockSizeInBytes());
        outline107.append(", ivLengthInBytes=");
        outline107.append(getIVLengthInBytes());
        outline107.append(", keyGenAlgo=");
        outline107.append(getKeyGeneratorAlgorithm());
        outline107.append(", keyLengthInBits=");
        outline107.append(getKeyLengthInBits());
        outline107.append(", specificProvider=");
        outline107.append(getSpecificCipherProvider());
        outline107.append(", tagLengthInBits=");
        outline107.append(getTagLengthInBits());
        return outline107.toString();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ContentCryptoScheme fromCEKAlgo(String str, boolean z) {
        if (AES_GCM.getCipherAlgorithm().equals(str)) {
            return z ? AES_CTR : AES_GCM;
        } else if (str != null && !AES_CBC.getCipherAlgorithm().equals(str)) {
            throw new UnsupportedOperationException(GeneratedOutlineSupport1.outline72("Unsupported content encryption scheme: ", str));
        } else {
            return AES_CBC;
        }
    }

    CipherLite createCipherLite(SecretKey secretKey, byte[] bArr, int i) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchProviderException, NoSuchPaddingException, InvalidAlgorithmParameterException {
        return createCipherLite(secretKey, bArr, i, null);
    }
}
