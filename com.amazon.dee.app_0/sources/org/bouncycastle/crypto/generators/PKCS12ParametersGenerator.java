package org.bouncycastle.crypto.generators;

import com.android.tools.r8.GeneratedOutlineSupport1;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.ExtendedDigest;
import org.bouncycastle.crypto.PBEParametersGenerator;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.ParametersWithIV;
/* loaded from: classes4.dex */
public class PKCS12ParametersGenerator extends PBEParametersGenerator {
    public static final int IV_MATERIAL = 2;
    public static final int KEY_MATERIAL = 1;
    public static final int MAC_MATERIAL = 3;
    private Digest digest;
    private int u;
    private int v;

    public PKCS12ParametersGenerator(Digest digest) {
        this.digest = digest;
        if (digest instanceof ExtendedDigest) {
            this.u = digest.getDigestSize();
            this.v = ((ExtendedDigest) digest).getByteLength();
            return;
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Digest ");
        outline107.append(digest.getAlgorithmName());
        outline107.append(" unsupported");
        throw new IllegalArgumentException(outline107.toString());
    }

    private void adjust(byte[] bArr, int i, byte[] bArr2) {
        int i2 = (bArr2[bArr2.length - 1] & 255) + (bArr[(bArr2.length + i) - 1] & 255) + 1;
        bArr[(bArr2.length + i) - 1] = (byte) i2;
        int i3 = i2 >>> 8;
        for (int length = bArr2.length - 2; length >= 0; length--) {
            int i4 = i + length;
            int i5 = (bArr2[length] & 255) + (bArr[i4] & 255) + i3;
            bArr[i4] = (byte) i5;
            i3 = i5 >>> 8;
        }
    }

    private byte[] generateDerivedKey(int i, int i2) {
        byte[] bArr;
        byte[] bArr2;
        byte[] bArr3 = new byte[this.v];
        byte[] bArr4 = new byte[i2];
        for (int i3 = 0; i3 != bArr3.length; i3++) {
            bArr3[i3] = (byte) i;
        }
        byte[] bArr5 = this.salt;
        if (bArr5 == null || bArr5.length == 0) {
            bArr = new byte[0];
        } else {
            int i4 = this.v;
            bArr = new byte[(((bArr5.length + i4) - 1) / i4) * i4];
            for (int i5 = 0; i5 != bArr.length; i5++) {
                byte[] bArr6 = this.salt;
                bArr[i5] = bArr6[i5 % bArr6.length];
            }
        }
        byte[] bArr7 = this.password;
        if (bArr7 == null || bArr7.length == 0) {
            bArr2 = new byte[0];
        } else {
            int i6 = this.v;
            bArr2 = new byte[(((bArr7.length + i6) - 1) / i6) * i6];
            for (int i7 = 0; i7 != bArr2.length; i7++) {
                byte[] bArr8 = this.password;
                bArr2[i7] = bArr8[i7 % bArr8.length];
            }
        }
        byte[] bArr9 = new byte[bArr.length + bArr2.length];
        System.arraycopy(bArr, 0, bArr9, 0, bArr.length);
        System.arraycopy(bArr2, 0, bArr9, bArr.length, bArr2.length);
        byte[] bArr10 = new byte[this.v];
        int i8 = this.u;
        int i9 = ((i2 + i8) - 1) / i8;
        byte[] bArr11 = new byte[i8];
        for (int i10 = 1; i10 <= i9; i10++) {
            this.digest.update(bArr3, 0, bArr3.length);
            this.digest.update(bArr9, 0, bArr9.length);
            this.digest.doFinal(bArr11, 0);
            for (int i11 = 1; i11 < this.iterationCount; i11++) {
                this.digest.update(bArr11, 0, bArr11.length);
                this.digest.doFinal(bArr11, 0);
            }
            for (int i12 = 0; i12 != bArr10.length; i12++) {
                bArr10[i12] = bArr11[i12 % bArr11.length];
            }
            int i13 = 0;
            while (true) {
                int length = bArr9.length;
                int i14 = this.v;
                if (i13 == length / i14) {
                    break;
                }
                adjust(bArr9, i14 * i13, bArr10);
                i13++;
            }
            int i15 = i10 - 1;
            int i16 = this.u;
            if (i10 == i9) {
                System.arraycopy(bArr11, 0, bArr4, i15 * i16, bArr4.length - (i15 * i16));
            } else {
                System.arraycopy(bArr11, 0, bArr4, i15 * i16, bArr11.length);
            }
        }
        return bArr4;
    }

    @Override // org.bouncycastle.crypto.PBEParametersGenerator
    public CipherParameters generateDerivedMacParameters(int i) {
        int i2 = i / 8;
        return new KeyParameter(generateDerivedKey(3, i2), 0, i2);
    }

    @Override // org.bouncycastle.crypto.PBEParametersGenerator
    public CipherParameters generateDerivedParameters(int i) {
        int i2 = i / 8;
        return new KeyParameter(generateDerivedKey(1, i2), 0, i2);
    }

    @Override // org.bouncycastle.crypto.PBEParametersGenerator
    public CipherParameters generateDerivedParameters(int i, int i2) {
        int i3 = i / 8;
        int i4 = i2 / 8;
        byte[] generateDerivedKey = generateDerivedKey(1, i3);
        return new ParametersWithIV(new KeyParameter(generateDerivedKey, 0, i3), generateDerivedKey(2, i4), 0, i4);
    }
}
