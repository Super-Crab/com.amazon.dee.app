package org.bouncycastle.cert.selector;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.util.Pack;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes4.dex */
public class MSOutlookKeyIdCalculator {

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes4.dex */
    public static abstract class GeneralDigest {
        private static final int BYTE_LENGTH = 64;
        private long byteCount;
        private byte[] xBuf;
        private int xBufOff;

        protected GeneralDigest() {
            this.xBuf = new byte[4];
            this.xBufOff = 0;
        }

        protected GeneralDigest(GeneralDigest generalDigest) {
            this.xBuf = new byte[generalDigest.xBuf.length];
            copyIn(generalDigest);
        }

        protected void copyIn(GeneralDigest generalDigest) {
            byte[] bArr = generalDigest.xBuf;
            System.arraycopy(bArr, 0, this.xBuf, 0, bArr.length);
            this.xBufOff = generalDigest.xBufOff;
            this.byteCount = generalDigest.byteCount;
        }

        public void finish() {
            long j = this.byteCount << 3;
            byte b = Byte.MIN_VALUE;
            while (true) {
                update(b);
                if (this.xBufOff == 0) {
                    processLength(j);
                    processBlock();
                    return;
                }
                b = 0;
            }
        }

        protected abstract void processBlock();

        protected abstract void processLength(long j);

        protected abstract void processWord(byte[] bArr, int i);

        public void reset() {
            this.byteCount = 0L;
            this.xBufOff = 0;
            int i = 0;
            while (true) {
                byte[] bArr = this.xBuf;
                if (i < bArr.length) {
                    bArr[i] = 0;
                    i++;
                } else {
                    return;
                }
            }
        }

        public void update(byte b) {
            byte[] bArr = this.xBuf;
            int i = this.xBufOff;
            this.xBufOff = i + 1;
            bArr[i] = b;
            if (this.xBufOff == bArr.length) {
                processWord(bArr, 0);
                this.xBufOff = 0;
            }
            this.byteCount++;
        }

        public void update(byte[] bArr, int i, int i2) {
            while (this.xBufOff != 0 && i2 > 0) {
                update(bArr[i]);
                i++;
                i2--;
            }
            while (i2 > this.xBuf.length) {
                processWord(bArr, i);
                byte[] bArr2 = this.xBuf;
                i += bArr2.length;
                i2 -= bArr2.length;
                this.byteCount += bArr2.length;
            }
            while (i2 > 0) {
                update(bArr[i]);
                i++;
                i2--;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes4.dex */
    public static class SHA1Digest extends GeneralDigest {
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
        private int[] X = new int[80];
        private int xOff;

        public SHA1Digest() {
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

        public String getAlgorithmName() {
            return "SHA-1";
        }

        public int getDigestSize() {
            return 20;
        }

        @Override // org.bouncycastle.cert.selector.MSOutlookKeyIdCalculator.GeneralDigest
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

        @Override // org.bouncycastle.cert.selector.MSOutlookKeyIdCalculator.GeneralDigest
        protected void processLength(long j) {
            if (this.xOff > 14) {
                processBlock();
            }
            int[] iArr = this.X;
            iArr[14] = (int) (j >>> 32);
            iArr[15] = (int) (j & (-1));
        }

        @Override // org.bouncycastle.cert.selector.MSOutlookKeyIdCalculator.GeneralDigest
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

        @Override // org.bouncycastle.cert.selector.MSOutlookKeyIdCalculator.GeneralDigest
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
    }

    MSOutlookKeyIdCalculator() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static byte[] calculateKeyId(SubjectPublicKeyInfo subjectPublicKeyInfo) {
        SHA1Digest sHA1Digest = new SHA1Digest();
        byte[] bArr = new byte[sHA1Digest.getDigestSize()];
        try {
            byte[] encoded = subjectPublicKeyInfo.getEncoded("DER");
            sHA1Digest.update(encoded, 0, encoded.length);
            sHA1Digest.doFinal(bArr, 0);
            return bArr;
        } catch (IOException unused) {
            return new byte[0];
        }
    }
}
