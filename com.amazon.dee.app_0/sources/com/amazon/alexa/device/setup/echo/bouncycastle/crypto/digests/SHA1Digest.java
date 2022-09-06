package com.amazon.alexa.device.setup.echo.bouncycastle.crypto.digests;

import com.amazon.alexa.device.setup.echo.bouncycastle.crypto.util.Pack;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes.dex */
public class SHA1Digest extends GeneralDigest {
    private static final int DIGEST_LENGTH = 20;
    private static final int Y1 = 1518500249;
    private static final int Y2 = 1859775393;
    private static final int Y3 = -1894007588;
    private static final int Y4 = -899497514;
    private int H1;
    private int H2;
    private int H3;
    private int H4;
    private int H5;
    private final int[] X;
    private int xOff;

    public SHA1Digest() {
        this.X = new int[80];
        reset();
    }

    private int f(int i, int i2, int i3) {
        return ((~i) & i3) | (i2 & i);
    }

    private int g(int i, int i2, int i3) {
        return (i & i3) | (i & i2) | (i2 & i3);
    }

    private int h(int i, int i2, int i3) {
        return (i ^ i2) ^ i3;
    }

    @Override // com.amazon.alexa.device.setup.echo.bouncycastle.crypto.Digest
    public int doFinal(byte[] bArr, int i) {
        finish();
        Pack.intToBigEndian(this.H1, bArr, i);
        Pack.intToBigEndian(this.H2, bArr, i + 4);
        Pack.intToBigEndian(this.H3, bArr, i + 8);
        Pack.intToBigEndian(this.H4, bArr, i + 12);
        Pack.intToBigEndian(this.H5, bArr, i + 16);
        reset();
        return 20;
    }

    @Override // com.amazon.alexa.device.setup.echo.bouncycastle.crypto.Digest
    public String getAlgorithmName() {
        return "SHA-1";
    }

    @Override // com.amazon.alexa.device.setup.echo.bouncycastle.crypto.Digest
    public int getDigestSize() {
        return 20;
    }

    @Override // com.amazon.alexa.device.setup.echo.bouncycastle.crypto.digests.GeneralDigest
    protected void processBlock() {
        for (int i = 16; i < 80; i++) {
            int[] iArr = this.X;
            int i2 = ((iArr[i - 3] ^ iArr[i - 8]) ^ iArr[i - 14]) ^ iArr[i - 16];
            iArr[i] = (i2 >>> 31) | (i2 << 1);
        }
        int i3 = this.H1;
        int i4 = this.H2;
        int i5 = this.H3;
        int i6 = this.H4;
        int i7 = this.H5;
        int i8 = 0;
        int i9 = i6;
        int i10 = i5;
        int i11 = i4;
        int i12 = i3;
        int i13 = 0;
        while (i13 < 4) {
            int i14 = i8 + 1;
            int outline1 = GeneratedOutlineSupport1.outline1(((i12 << 5) | (i12 >>> 27)) + f(i11, i10, i9), this.X[i8], Y1, i7);
            int i15 = (i11 >>> 2) | (i11 << 30);
            int i16 = i14 + 1;
            int outline12 = GeneratedOutlineSupport1.outline1(((outline1 << 5) | (outline1 >>> 27)) + f(i12, i15, i10), this.X[i14], Y1, i9);
            int i17 = (i12 >>> 2) | (i12 << 30);
            int i18 = i16 + 1;
            int outline13 = GeneratedOutlineSupport1.outline1(((outline12 << 5) | (outline12 >>> 27)) + f(outline1, i17, i15), this.X[i16], Y1, i10);
            i7 = (outline1 << 30) | (outline1 >>> 2);
            int i19 = i18 + 1;
            i11 = GeneratedOutlineSupport1.outline1(((outline13 << 5) | (outline13 >>> 27)) + f(outline12, i7, i17), this.X[i18], Y1, i15);
            i9 = (outline12 >>> 2) | (outline12 << 30);
            i12 = GeneratedOutlineSupport1.outline1(((i11 << 5) | (i11 >>> 27)) + f(outline13, i9, i7), this.X[i19], Y1, i17);
            i10 = (outline13 >>> 2) | (outline13 << 30);
            i13++;
            i8 = i19 + 1;
        }
        int i20 = 0;
        while (i20 < 4) {
            int i21 = i8 + 1;
            int outline14 = GeneratedOutlineSupport1.outline1(((i12 << 5) | (i12 >>> 27)) + h(i11, i10, i9), this.X[i8], Y2, i7);
            int i22 = (i11 >>> 2) | (i11 << 30);
            int i23 = i21 + 1;
            int outline15 = GeneratedOutlineSupport1.outline1(((outline14 << 5) | (outline14 >>> 27)) + h(i12, i22, i10), this.X[i21], Y2, i9);
            int i24 = (i12 >>> 2) | (i12 << 30);
            int i25 = i23 + 1;
            int outline16 = GeneratedOutlineSupport1.outline1(((outline15 << 5) | (outline15 >>> 27)) + h(outline14, i24, i22), this.X[i23], Y2, i10);
            i7 = (outline14 << 30) | (outline14 >>> 2);
            int i26 = i25 + 1;
            i11 = GeneratedOutlineSupport1.outline1(((outline16 << 5) | (outline16 >>> 27)) + h(outline15, i7, i24), this.X[i25], Y2, i22);
            i9 = (outline15 >>> 2) | (outline15 << 30);
            i12 = GeneratedOutlineSupport1.outline1(((i11 << 5) | (i11 >>> 27)) + h(outline16, i9, i7), this.X[i26], Y2, i24);
            i10 = (outline16 >>> 2) | (outline16 << 30);
            i20++;
            i8 = i26 + 1;
        }
        int i27 = 0;
        while (i27 < 4) {
            int i28 = i8 + 1;
            int outline17 = GeneratedOutlineSupport1.outline1(((i12 << 5) | (i12 >>> 27)) + g(i11, i10, i9), this.X[i8], Y3, i7);
            int i29 = (i11 >>> 2) | (i11 << 30);
            int i30 = i28 + 1;
            int outline18 = GeneratedOutlineSupport1.outline1(((outline17 << 5) | (outline17 >>> 27)) + g(i12, i29, i10), this.X[i28], Y3, i9);
            int i31 = (i12 >>> 2) | (i12 << 30);
            int i32 = i30 + 1;
            int outline19 = GeneratedOutlineSupport1.outline1(((outline18 << 5) | (outline18 >>> 27)) + g(outline17, i31, i29), this.X[i30], Y3, i10);
            i7 = (outline17 << 30) | (outline17 >>> 2);
            int i33 = i32 + 1;
            i11 = GeneratedOutlineSupport1.outline1(((outline19 << 5) | (outline19 >>> 27)) + g(outline18, i7, i31), this.X[i32], Y3, i29);
            i9 = (outline18 >>> 2) | (outline18 << 30);
            i12 = GeneratedOutlineSupport1.outline1(((i11 << 5) | (i11 >>> 27)) + g(outline19, i9, i7), this.X[i33], Y3, i31);
            i10 = (outline19 >>> 2) | (outline19 << 30);
            i27++;
            i8 = i33 + 1;
        }
        int i34 = 0;
        while (i34 <= 3) {
            int i35 = i8 + 1;
            int outline110 = GeneratedOutlineSupport1.outline1(((i12 << 5) | (i12 >>> 27)) + h(i11, i10, i9), this.X[i8], Y4, i7);
            int i36 = (i11 >>> 2) | (i11 << 30);
            int i37 = i35 + 1;
            int outline111 = GeneratedOutlineSupport1.outline1(((outline110 << 5) | (outline110 >>> 27)) + h(i12, i36, i10), this.X[i35], Y4, i9);
            int i38 = (i12 >>> 2) | (i12 << 30);
            int i39 = i37 + 1;
            int outline112 = GeneratedOutlineSupport1.outline1(((outline111 << 5) | (outline111 >>> 27)) + h(outline110, i38, i36), this.X[i37], Y4, i10);
            i7 = (outline110 << 30) | (outline110 >>> 2);
            int i40 = i39 + 1;
            i11 = GeneratedOutlineSupport1.outline1(((outline112 << 5) | (outline112 >>> 27)) + h(outline111, i7, i38), this.X[i39], Y4, i36);
            i9 = (outline111 >>> 2) | (outline111 << 30);
            i12 = GeneratedOutlineSupport1.outline1(((i11 << 5) | (i11 >>> 27)) + h(outline112, i9, i7), this.X[i40], Y4, i38);
            i10 = (outline112 >>> 2) | (outline112 << 30);
            i34++;
            i8 = i40 + 1;
        }
        this.H1 += i12;
        this.H2 += i11;
        this.H3 += i10;
        this.H4 += i9;
        this.H5 += i7;
        this.xOff = 0;
        for (int i41 = 0; i41 < 16; i41++) {
            this.X[i41] = 0;
        }
    }

    @Override // com.amazon.alexa.device.setup.echo.bouncycastle.crypto.digests.GeneralDigest
    protected void processLength(long j) {
        if (this.xOff > 14) {
            processBlock();
        }
        int[] iArr = this.X;
        iArr[14] = (int) (j >>> 32);
        iArr[15] = (int) (j & (-1));
    }

    @Override // com.amazon.alexa.device.setup.echo.bouncycastle.crypto.digests.GeneralDigest
    protected void processWord(byte[] bArr, int i) {
        int i2 = i + 1;
        int i3 = i2 + 1;
        int i4 = (bArr[i3 + 1] & 255) | (bArr[i] << 24) | ((bArr[i2] & 255) << 16) | ((bArr[i3] & 255) << 8);
        int[] iArr = this.X;
        int i5 = this.xOff;
        iArr[i5] = i4;
        int i6 = i5 + 1;
        this.xOff = i6;
        if (i6 == 16) {
            processBlock();
        }
    }

    @Override // com.amazon.alexa.device.setup.echo.bouncycastle.crypto.digests.GeneralDigest, com.amazon.alexa.device.setup.echo.bouncycastle.crypto.Digest
    public void reset() {
        super.reset();
        this.H1 = 1732584193;
        this.H2 = -271733879;
        this.H3 = -1732584194;
        this.H4 = 271733878;
        this.H5 = -1009589776;
        this.xOff = 0;
        int i = 0;
        while (true) {
            int[] iArr = this.X;
            if (i != iArr.length) {
                iArr[i] = 0;
                i++;
            } else {
                return;
            }
        }
    }

    public SHA1Digest(SHA1Digest sHA1Digest) {
        super(sHA1Digest);
        this.X = new int[80];
        this.H1 = sHA1Digest.H1;
        this.H2 = sHA1Digest.H2;
        this.H3 = sHA1Digest.H3;
        this.H4 = sHA1Digest.H4;
        this.H5 = sHA1Digest.H5;
        int[] iArr = sHA1Digest.X;
        System.arraycopy(iArr, 0, this.X, 0, iArr.length);
        this.xOff = sHA1Digest.xOff;
    }
}
