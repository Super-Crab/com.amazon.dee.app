package org.bouncycastle.crypto.digests;

import com.android.tools.r8.GeneratedOutlineSupport1;
import org.bouncycastle.util.Memoable;
import org.bouncycastle.util.Pack;
/* loaded from: classes4.dex */
public class SM3Digest extends GeneralDigest {
    private static final int BLOCK_SIZE = 16;
    private static final int DIGEST_LENGTH = 32;
    private static final int[] T = new int[64];
    private int[] V;
    private int[] W;
    private int[] inwords;
    private int xOff;

    static {
        int i;
        int i2 = 0;
        while (true) {
            if (i2 >= 16) {
                break;
            }
            T[i2] = (2043430169 >>> (32 - i2)) | (2043430169 << i2);
            i2++;
        }
        for (i = 16; i < 64; i++) {
            int i3 = i % 32;
            T[i] = (2055708042 >>> (32 - i3)) | (2055708042 << i3);
        }
    }

    public SM3Digest() {
        this.V = new int[8];
        this.inwords = new int[16];
        this.W = new int[68];
        reset();
    }

    public SM3Digest(SM3Digest sM3Digest) {
        super(sM3Digest);
        this.V = new int[8];
        this.inwords = new int[16];
        this.W = new int[68];
        copyIn(sM3Digest);
    }

    private int FF0(int i, int i2, int i3) {
        return (i ^ i2) ^ i3;
    }

    private int FF1(int i, int i2, int i3) {
        return (i & i3) | (i & i2) | (i2 & i3);
    }

    private int GG0(int i, int i2, int i3) {
        return (i ^ i2) ^ i3;
    }

    private int GG1(int i, int i2, int i3) {
        return ((~i) & i3) | (i2 & i);
    }

    private int P0(int i) {
        return (i ^ ((i << 9) | (i >>> 23))) ^ ((i << 17) | (i >>> 15));
    }

    private int P1(int i) {
        return (i ^ ((i << 15) | (i >>> 17))) ^ ((i << 23) | (i >>> 9));
    }

    private void copyIn(SM3Digest sM3Digest) {
        int[] iArr = sM3Digest.V;
        int[] iArr2 = this.V;
        System.arraycopy(iArr, 0, iArr2, 0, iArr2.length);
        int[] iArr3 = sM3Digest.inwords;
        int[] iArr4 = this.inwords;
        System.arraycopy(iArr3, 0, iArr4, 0, iArr4.length);
        this.xOff = sM3Digest.xOff;
    }

    @Override // org.bouncycastle.util.Memoable
    public Memoable copy() {
        return new SM3Digest(this);
    }

    @Override // org.bouncycastle.crypto.Digest
    public int doFinal(byte[] bArr, int i) {
        finish();
        Pack.intToBigEndian(this.V, bArr, i);
        reset();
        return 32;
    }

    @Override // org.bouncycastle.crypto.Digest
    public String getAlgorithmName() {
        return "SM3";
    }

    @Override // org.bouncycastle.crypto.Digest
    public int getDigestSize() {
        return 32;
    }

    @Override // org.bouncycastle.crypto.digests.GeneralDigest
    protected void processBlock() {
        int i;
        int i2 = 0;
        while (true) {
            if (i2 >= 16) {
                break;
            }
            this.W[i2] = this.inwords[i2];
            i2++;
        }
        for (int i3 = 16; i3 < 68; i3++) {
            int[] iArr = this.W;
            int i4 = iArr[i3 - 3];
            int i5 = iArr[i3 - 13];
            iArr[i3] = (P1(((i4 >>> 17) | (i4 << 15)) ^ (iArr[i3 - 16] ^ iArr[i3 - 9])) ^ ((i5 >>> 25) | (i5 << 7))) ^ this.W[i3 - 6];
        }
        int[] iArr2 = this.V;
        int i6 = iArr2[0];
        int i7 = iArr2[1];
        int i8 = iArr2[2];
        int i9 = iArr2[3];
        int i10 = iArr2[4];
        int i11 = iArr2[5];
        int i12 = iArr2[6];
        int i13 = iArr2[7];
        int i14 = 0;
        for (i = 16; i14 < i; i = 16) {
            int i15 = (i6 << 12) | (i6 >>> 20);
            int i16 = i15 + i10 + T[i14];
            int i17 = (i16 << 7) | (i16 >>> 25);
            int[] iArr3 = this.W;
            int i18 = iArr3[i14];
            int outline1 = GeneratedOutlineSupport1.outline1(FF0(i6, i7, i8), i9, i15 ^ i17, iArr3[i14 + 4] ^ i18);
            int outline12 = GeneratedOutlineSupport1.outline1(GG0(i10, i11, i12), i13, i17, i18);
            i14++;
            i9 = i8;
            i11 = i10;
            i13 = i12;
            i10 = P0(outline12);
            i8 = (i7 >>> 23) | (i7 << 9);
            i12 = (i11 << 19) | (i11 >>> 13);
            i7 = i6;
            i6 = outline1;
        }
        int i19 = 16;
        int i20 = i7;
        int i21 = i11;
        int i22 = i13;
        int i23 = i6;
        int i24 = i9;
        int i25 = i10;
        int i26 = i12;
        int i27 = i8;
        while (i19 < 64) {
            int i28 = (i23 << 12) | (i23 >>> 20);
            int i29 = i28 + i25 + T[i19];
            int i30 = (i29 >>> 25) | (i29 << 7);
            int[] iArr4 = this.W;
            int i31 = iArr4[i19];
            int outline13 = GeneratedOutlineSupport1.outline1(FF1(i23, i20, i27), i24, i28 ^ i30, iArr4[i19 + 4] ^ i31);
            int outline14 = GeneratedOutlineSupport1.outline1(GG1(i25, i21, i26), i22, i30, i31);
            i19++;
            int i32 = i27;
            i27 = (i20 >>> 23) | (i20 << 9);
            i20 = i23;
            i23 = outline13;
            i24 = i32;
            int i33 = i26;
            i26 = (i21 >>> 13) | (i21 << 19);
            i21 = i25;
            i25 = P0(outline14);
            i22 = i33;
        }
        int[] iArr5 = this.V;
        iArr5[0] = i23 ^ iArr5[0];
        iArr5[1] = i20 ^ iArr5[1];
        iArr5[2] = iArr5[2] ^ i27;
        iArr5[3] = i24 ^ iArr5[3];
        iArr5[4] = iArr5[4] ^ i25;
        iArr5[5] = iArr5[5] ^ i21;
        iArr5[6] = iArr5[6] ^ i26;
        iArr5[7] = iArr5[7] ^ i22;
        this.xOff = 0;
    }

    @Override // org.bouncycastle.crypto.digests.GeneralDigest
    protected void processLength(long j) {
        int i = this.xOff;
        if (i > 14) {
            this.inwords[i] = 0;
            this.xOff = i + 1;
            processBlock();
        }
        while (true) {
            int i2 = this.xOff;
            if (i2 >= 14) {
                int[] iArr = this.inwords;
                this.xOff = i2 + 1;
                iArr[i2] = (int) (j >>> 32);
                int i3 = this.xOff;
                this.xOff = i3 + 1;
                iArr[i3] = (int) j;
                return;
            }
            this.inwords[i2] = 0;
            this.xOff = i2 + 1;
        }
    }

    @Override // org.bouncycastle.crypto.digests.GeneralDigest
    protected void processWord(byte[] bArr, int i) {
        int i2 = i + 1;
        int i3 = i2 + 1;
        int i4 = (bArr[i3 + 1] & 255) | ((bArr[i] & 255) << 24) | ((bArr[i2] & 255) << 16) | ((bArr[i3] & 255) << 8);
        int[] iArr = this.inwords;
        int i5 = this.xOff;
        iArr[i5] = i4;
        this.xOff = i5 + 1;
        if (this.xOff >= 16) {
            processBlock();
        }
    }

    @Override // org.bouncycastle.crypto.digests.GeneralDigest, org.bouncycastle.crypto.Digest
    public void reset() {
        super.reset();
        int[] iArr = this.V;
        iArr[0] = 1937774191;
        iArr[1] = 1226093241;
        iArr[2] = 388252375;
        iArr[3] = -628488704;
        iArr[4] = -1452330820;
        iArr[5] = 372324522;
        iArr[6] = -477237683;
        iArr[7] = -1325724082;
        this.xOff = 0;
    }

    @Override // org.bouncycastle.util.Memoable
    public void reset(Memoable memoable) {
        SM3Digest sM3Digest = (SM3Digest) memoable;
        super.copyIn((GeneralDigest) sM3Digest);
        copyIn(sM3Digest);
    }
}
