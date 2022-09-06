package org.bouncycastle.crypto.engines;

import com.android.tools.r8.GeneratedOutlineSupport1;
import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.signers.PSSSigner;
import org.bouncycastle.util.Integers;
import org.bouncycastle.util.Pack;
/* loaded from: classes4.dex */
public class NoekeonEngine implements BlockCipher {
    private static final int SIZE = 16;
    private static final byte[] roundConstants = {Byte.MIN_VALUE, 27, 54, 108, -40, -85, 77, -102, 47, 94, PSSSigner.TRAILER_IMPLICIT, 99, -58, -105, 53, 106, -44};
    private boolean _forEncryption;
    private final int[] k = new int[4];
    private boolean _initialised = false;

    private int decryptBlock(byte[] bArr, int i, byte[] bArr2, int i2) {
        int bigEndianToInt = Pack.bigEndianToInt(bArr, i);
        int bigEndianToInt2 = Pack.bigEndianToInt(bArr, i + 4);
        int bigEndianToInt3 = Pack.bigEndianToInt(bArr, i + 8);
        int bigEndianToInt4 = Pack.bigEndianToInt(bArr, i + 12);
        int[] iArr = this.k;
        int i3 = iArr[0];
        int i4 = iArr[1];
        int i5 = iArr[2];
        int i6 = iArr[3];
        int i7 = 16;
        while (true) {
            int i8 = bigEndianToInt ^ bigEndianToInt3;
            int rotateLeft = i8 ^ (Integers.rotateLeft(i8, 8) ^ Integers.rotateLeft(i8, 24));
            int i9 = (bigEndianToInt2 ^ rotateLeft) ^ i4;
            int i10 = (bigEndianToInt4 ^ rotateLeft) ^ i6;
            int i11 = i9 ^ i10;
            int rotateLeft2 = (Integers.rotateLeft(i11, 24) ^ Integers.rotateLeft(i11, 8)) ^ i11;
            int i12 = (bigEndianToInt3 ^ i5) ^ rotateLeft2;
            int i13 = ((bigEndianToInt ^ i3) ^ rotateLeft2) ^ (roundConstants[i7] & 255);
            i7--;
            if (i7 < 0) {
                Pack.intToBigEndian(i13, bArr2, i2);
                Pack.intToBigEndian(i9, bArr2, i2 + 4);
                Pack.intToBigEndian(i12, bArr2, i2 + 8);
                Pack.intToBigEndian(i10, bArr2, i2 + 12);
                return 16;
            }
            int rotateLeft3 = Integers.rotateLeft(i9, 1);
            int rotateLeft4 = Integers.rotateLeft(i12, 5);
            int rotateLeft5 = Integers.rotateLeft(i10, 2);
            int i14 = rotateLeft3 ^ ((~rotateLeft4) & (~rotateLeft5));
            int i15 = i13 ^ (rotateLeft4 & i14);
            int i16 = rotateLeft4 ^ ((rotateLeft5 ^ i14) ^ i15);
            int i17 = i14 ^ ((~i15) & (~i16));
            bigEndianToInt2 = Integers.rotateLeft(i17, 31);
            bigEndianToInt3 = Integers.rotateLeft(i16, 27);
            int rotateLeft6 = Integers.rotateLeft(i15, 30);
            bigEndianToInt = rotateLeft5 ^ (i16 & i17);
            bigEndianToInt4 = rotateLeft6;
        }
    }

    private int encryptBlock(byte[] bArr, int i, byte[] bArr2, int i2) {
        int bigEndianToInt = Pack.bigEndianToInt(bArr, i);
        int bigEndianToInt2 = Pack.bigEndianToInt(bArr, i + 4);
        int bigEndianToInt3 = Pack.bigEndianToInt(bArr, i + 8);
        int bigEndianToInt4 = Pack.bigEndianToInt(bArr, i + 12);
        int[] iArr = this.k;
        int i3 = 0;
        int i4 = iArr[0];
        int i5 = iArr[1];
        int i6 = iArr[2];
        int i7 = iArr[3];
        while (true) {
            int i8 = bigEndianToInt ^ (roundConstants[i3] & 255);
            int i9 = i8 ^ bigEndianToInt3;
            int rotateLeft = i9 ^ (Integers.rotateLeft(i9, 8) ^ Integers.rotateLeft(i9, 24));
            int i10 = (bigEndianToInt2 ^ rotateLeft) ^ i5;
            int i11 = (bigEndianToInt4 ^ rotateLeft) ^ i7;
            int i12 = i10 ^ i11;
            int rotateLeft2 = (Integers.rotateLeft(i12, 24) ^ Integers.rotateLeft(i12, 8)) ^ i12;
            int i13 = (i8 ^ i4) ^ rotateLeft2;
            int i14 = (bigEndianToInt3 ^ i6) ^ rotateLeft2;
            i3++;
            if (i3 > 16) {
                Pack.intToBigEndian(i13, bArr2, i2);
                Pack.intToBigEndian(i10, bArr2, i2 + 4);
                Pack.intToBigEndian(i14, bArr2, i2 + 8);
                Pack.intToBigEndian(i11, bArr2, i2 + 12);
                return 16;
            }
            int rotateLeft3 = Integers.rotateLeft(i10, 1);
            int rotateLeft4 = Integers.rotateLeft(i14, 5);
            int rotateLeft5 = Integers.rotateLeft(i11, 2);
            int i15 = rotateLeft3 ^ ((~rotateLeft5) & (~rotateLeft4));
            int i16 = i13 ^ (rotateLeft4 & i15);
            int i17 = rotateLeft4 ^ ((rotateLeft5 ^ i15) ^ i16);
            int i18 = i15 ^ ((~i16) & (~i17));
            bigEndianToInt2 = Integers.rotateLeft(i18, 31);
            bigEndianToInt3 = Integers.rotateLeft(i17, 27);
            int rotateLeft6 = Integers.rotateLeft(i16, 30);
            bigEndianToInt = rotateLeft5 ^ (i17 & i18);
            bigEndianToInt4 = rotateLeft6;
        }
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public String getAlgorithmName() {
        return "Noekeon";
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int getBlockSize() {
        return 16;
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void init(boolean z, CipherParameters cipherParameters) {
        if (cipherParameters instanceof KeyParameter) {
            this._forEncryption = z;
            this._initialised = true;
            Pack.bigEndianToInt(((KeyParameter) cipherParameters).getKey(), 0, this.k, 0, 4);
            if (z) {
                return;
            }
            int[] iArr = this.k;
            int i = iArr[0];
            int i2 = iArr[1];
            int i3 = iArr[2];
            int i4 = iArr[3];
            int i5 = i ^ i3;
            int rotateLeft = i5 ^ (Integers.rotateLeft(i5, 8) ^ Integers.rotateLeft(i5, 24));
            int i6 = i2 ^ rotateLeft;
            int i7 = i4 ^ rotateLeft;
            int i8 = i6 ^ i7;
            int rotateLeft2 = i8 ^ (Integers.rotateLeft(i8, 8) ^ Integers.rotateLeft(i8, 24));
            int i9 = i ^ rotateLeft2;
            int i10 = i3 ^ rotateLeft2;
            int[] iArr2 = this.k;
            iArr2[0] = i9;
            iArr2[1] = i6;
            iArr2[2] = i10;
            iArr2[3] = i7;
            return;
        }
        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline102(cipherParameters, GeneratedOutlineSupport1.outline107("invalid parameter passed to Noekeon init - ")));
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int processBlock(byte[] bArr, int i, byte[] bArr2, int i2) {
        if (!this._initialised) {
            throw new IllegalStateException(getAlgorithmName() + " not initialised");
        } else if (i > bArr.length - 16) {
            throw new DataLengthException("input buffer too short");
        } else {
            if (i2 > bArr2.length - 16) {
                throw new OutputLengthException("output buffer too short");
            }
            return this._forEncryption ? encryptBlock(bArr, i, bArr2, i2) : decryptBlock(bArr, i, bArr2, i2);
        }
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void reset() {
    }
}
