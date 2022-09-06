package com.amazon.whispercloak.cipher;

import com.amazon.whispercloak.SecureMessage;
import com.amazon.whispercloak.random.SecureRandomProvider;
import java.security.SecureRandom;
import java.util.Arrays;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.engines.AESFastEngine;
import org.bouncycastle.crypto.modes.GCMBlockCipher;
import org.bouncycastle.crypto.params.AEADParameters;
import org.bouncycastle.crypto.params.KeyParameter;
/* loaded from: classes13.dex */
public class AesGcmCipher {
    private static final int BITS_IN_BYTE = 8;
    private static final int IV_SIZE_BYTES = 16;
    private static final int KEY_SIZE_BYTES = 16;
    private static final int MAC_SIZE_BITS = 128;
    private final GCMBlockCipher mCipher = new GCMBlockCipher(new AESFastEngine());
    private final SecureRandom mSecureRandom = SecureRandomProvider.getInstance();

    private byte[] createIV() {
        byte[] bArr = new byte[16];
        this.mSecureRandom.nextBytes(bArr);
        return bArr;
    }

    private AEADParameters getCipherParameters(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        if (bArr.length > 16) {
            bArr = Arrays.copyOf(bArr, 16);
        }
        if (bArr3 == null) {
            return new AEADParameters(new KeyParameter(bArr), 128, bArr2);
        }
        return new AEADParameters(new KeyParameter(bArr), 128, bArr2, bArr3);
    }

    public byte[] decrypt(byte[] bArr, SecureMessage secureMessage) {
        this.mCipher.init(false, getCipherParameters(bArr, secureMessage.getIv(), secureMessage.getAad()));
        byte[] cipherText = secureMessage.getCipherText();
        byte[] mac = secureMessage.getMac();
        byte[] bArr2 = new byte[cipherText.length + mac.length];
        System.arraycopy(cipherText, 0, bArr2, 0, cipherText.length);
        System.arraycopy(mac, 0, bArr2, cipherText.length, mac.length);
        byte[] bArr3 = new byte[this.mCipher.getOutputSize(bArr2.length)];
        try {
            this.mCipher.doFinal(bArr3, this.mCipher.processBytes(bArr2, 0, bArr2.length, bArr3, 0));
            return bArr3;
        } catch (InvalidCipherTextException e) {
            throw new RuntimeException(e);
        }
    }

    public SecureMessage encrypt(byte[] bArr, byte[] bArr2) {
        return encrypt(bArr, bArr2, null);
    }

    public SecureMessage encrypt(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        byte[] createIV = createIV();
        this.mCipher.init(true, getCipherParameters(bArr, createIV, bArr3));
        byte[] bArr4 = new byte[this.mCipher.getOutputSize(bArr2.length)];
        int processBytes = this.mCipher.processBytes(bArr2, 0, bArr2.length, bArr4, 0);
        try {
            int doFinal = (processBytes + this.mCipher.doFinal(bArr4, processBytes)) - 16;
            byte[] bArr5 = new byte[doFinal];
            byte[] bArr6 = new byte[16];
            System.arraycopy(bArr4, 0, bArr5, 0, bArr5.length);
            System.arraycopy(bArr4, doFinal, bArr6, 0, bArr6.length);
            return new SecureMessage(createIV, bArr5, bArr6, bArr3);
        } catch (InvalidCipherTextException e) {
            throw new RuntimeException(e);
        }
    }
}
