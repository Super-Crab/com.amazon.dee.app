package com.amazon.whispercloak;

import java.util.Arrays;
/* loaded from: classes13.dex */
public class SecureMessage {
    private final byte[] mAad;
    private final byte[] mCipherText;
    private final byte[] mIV;
    private final byte[] mMAC;

    public SecureMessage(byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4) {
        verifyParams(bArr, bArr2, bArr3, bArr4);
        this.mIV = bArr;
        this.mCipherText = bArr2;
        this.mMAC = bArr3;
        this.mAad = bArr4;
    }

    private void verifyParams(byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4) {
        if (bArr != null) {
            if (bArr2 == null) {
                throw new IllegalArgumentException("cipherText can'tbe null");
            }
            if (bArr3 == null) {
                throw new IllegalArgumentException("Mac Can't be null");
            }
            return;
        }
        throw new IllegalArgumentException("IV can't be null");
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || SecureMessage.class != obj.getClass()) {
            return false;
        }
        SecureMessage secureMessage = (SecureMessage) obj;
        if (!Arrays.equals(this.mIV, secureMessage.mIV) || !Arrays.equals(this.mCipherText, secureMessage.mCipherText) || !Arrays.equals(this.mMAC, secureMessage.mMAC)) {
            return false;
        }
        return Arrays.equals(this.mAad, secureMessage.mAad);
    }

    public byte[] getAad() {
        return this.mAad;
    }

    public byte[] getCipherText() {
        return this.mCipherText;
    }

    public byte[] getIv() {
        return this.mIV;
    }

    public byte[] getMac() {
        return this.mMAC;
    }

    public int hashCode() {
        int hashCode = Arrays.hashCode(this.mCipherText);
        int hashCode2 = Arrays.hashCode(this.mMAC);
        return Arrays.hashCode(this.mAad) + ((hashCode2 + ((hashCode + (Arrays.hashCode(this.mIV) * 31)) * 31)) * 31);
    }
}
