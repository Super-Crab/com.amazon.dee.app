package org.bouncycastle.pqc.crypto.gmss.util;

import org.bouncycastle.crypto.Digest;
/* loaded from: classes5.dex */
public class WinternitzOTSVerify {
    private int mdsize;
    private Digest messDigestOTS;
    private int w;

    public WinternitzOTSVerify(Digest digest, int i) {
        this.w = i;
        this.messDigestOTS = digest;
        this.mdsize = this.messDigestOTS.getDigestSize();
    }

    private void hashSignatureBlock(byte[] bArr, int i, int i2, byte[] bArr2, int i3) {
        if (i2 < 1) {
            System.arraycopy(bArr, i, bArr2, i3, this.mdsize);
            return;
        }
        this.messDigestOTS.update(bArr, i, this.mdsize);
        while (true) {
            this.messDigestOTS.doFinal(bArr2, i3);
            i2--;
            if (i2 <= 0) {
                return;
            }
            this.messDigestOTS.update(bArr2, i3, this.mdsize);
        }
    }

    public byte[] Verify(byte[] bArr, byte[] bArr2) {
        int i;
        int i2;
        byte[] bArr3 = new byte[this.mdsize];
        int i3 = 0;
        this.messDigestOTS.update(bArr, 0, bArr.length);
        this.messDigestOTS.doFinal(bArr3, 0);
        int i4 = this.w;
        int i5 = ((i4 - 1) + (this.mdsize << 3)) / i4;
        int log = getLog((i5 << i4) + 1);
        int i6 = this.w;
        int i7 = this.mdsize;
        int i8 = ((((log + i6) - 1) / i6) + i5) * i7;
        if (i8 != bArr2.length) {
            return null;
        }
        byte[] bArr4 = new byte[i8];
        int i9 = 8;
        if (8 % i6 == 0) {
            int i10 = 8 / i6;
            int i11 = (1 << i6) - 1;
            int i12 = 0;
            int i13 = 0;
            int i14 = 0;
            while (i14 < bArr3.length) {
                int i15 = i13;
                int i16 = 0;
                while (i16 < i10) {
                    int i17 = bArr3[i14] & i11;
                    int i18 = i12 + i17;
                    int i19 = this.mdsize;
                    int i20 = i14;
                    hashSignatureBlock(bArr2, i15 * i19, i11 - i17, bArr4, i15 * i19);
                    bArr3[i20] = (byte) (bArr3[i20] >>> this.w);
                    i15++;
                    i16++;
                    i12 = i18;
                    i14 = i20;
                }
                i14++;
                i13 = i15;
            }
            int i21 = i13;
            int i22 = (i5 << this.w) - i12;
            int i23 = 0;
            while (i23 < log) {
                int i24 = this.mdsize;
                hashSignatureBlock(bArr2, i21 * i24, i11 - (i22 & i11), bArr4, i21 * i24);
                int i25 = this.w;
                i22 >>>= i25;
                i21++;
                i23 += i25;
            }
            i2 = 0;
        } else {
            if (i6 < 8) {
                int i26 = i7 / i6;
                int i27 = (1 << i6) - 1;
                int i28 = 0;
                int i29 = 0;
                int i30 = 0;
                int i31 = 0;
                while (i31 < i26) {
                    int i32 = i28;
                    int i33 = i3;
                    long j = 0;
                    while (i33 < this.w) {
                        j ^= (bArr3[i32] & 255) << (i33 << 3);
                        i32++;
                        i33++;
                        i5 = i5;
                    }
                    int i34 = i5;
                    int i35 = i30;
                    int i36 = 0;
                    while (i36 < i9) {
                        int i37 = (int) (j & i27);
                        int i38 = i29 + i37;
                        int i39 = this.mdsize;
                        hashSignatureBlock(bArr2, i35 * i39, i27 - i37, bArr4, i35 * i39);
                        j >>>= this.w;
                        i35++;
                        i36++;
                        i29 = i38;
                        i27 = i27;
                        i31 = i31;
                        i26 = i26;
                        i9 = 8;
                    }
                    i31++;
                    i30 = i35;
                    i28 = i32;
                    i3 = 0;
                    i9 = 8;
                    i5 = i34;
                }
                int i40 = i27;
                int i41 = i5;
                int i42 = this.mdsize % this.w;
                int i43 = i28;
                long j2 = 0;
                for (int i44 = 0; i44 < i42; i44++) {
                    j2 ^= (bArr3[i43] & 255) << (i44 << 3);
                    i43++;
                }
                int i45 = i42 << 3;
                int i46 = i30;
                int i47 = 0;
                while (i47 < i45) {
                    int i48 = (int) (j2 & i40);
                    int i49 = i29 + i48;
                    int i50 = this.mdsize;
                    hashSignatureBlock(bArr2, i46 * i50, i40 - i48, bArr4, i46 * i50);
                    int i51 = this.w;
                    j2 >>>= i51;
                    i46++;
                    i47 += i51;
                    i29 = i49;
                }
                int i52 = (i41 << this.w) - i29;
                int i53 = 0;
                while (i53 < log) {
                    int i54 = this.mdsize;
                    hashSignatureBlock(bArr2, i46 * i54, i40 - (i52 & i40), bArr4, i46 * i54);
                    int i55 = this.w;
                    i52 >>>= i55;
                    i46++;
                    i53 += i55;
                }
            } else if (i6 < 57) {
                int i56 = (i7 << 3) - i6;
                int i57 = (1 << i6) - 1;
                byte[] bArr5 = new byte[i7];
                int i58 = 0;
                int i59 = 0;
                int i60 = 0;
                while (i58 <= i56) {
                    int i61 = i58 >>> 3;
                    int i62 = i58 % 8;
                    i58 += this.w;
                    int i63 = 0;
                    long j3 = 0;
                    while (i61 < ((i58 + 7) >>> 3)) {
                        j3 ^= (bArr3[i61] & 255) << (i63 << 3);
                        i63++;
                        i61++;
                        log = log;
                    }
                    int i64 = log;
                    long j4 = i57;
                    long j5 = (j3 >>> i62) & j4;
                    i59 = (int) (i59 + j5);
                    int i65 = this.mdsize;
                    int i66 = i56;
                    System.arraycopy(bArr2, i60 * i65, bArr5, 0, i65);
                    while (j5 < j4) {
                        this.messDigestOTS.update(bArr5, 0, bArr5.length);
                        this.messDigestOTS.doFinal(bArr5, 0);
                        j5++;
                    }
                    int i67 = this.mdsize;
                    System.arraycopy(bArr5, 0, bArr4, i60 * i67, i67);
                    i60++;
                    i56 = i66;
                    log = i64;
                }
                int i68 = log;
                int i69 = i58 >>> 3;
                if (i69 < this.mdsize) {
                    int i70 = i58 % 8;
                    int i71 = 0;
                    long j6 = 0;
                    while (true) {
                        i = this.mdsize;
                        if (i69 >= i) {
                            break;
                        }
                        j6 ^= (bArr3[i69] & 255) << (i71 << 3);
                        i71++;
                        i69++;
                    }
                    long j7 = i57;
                    long j8 = (j6 >>> i70) & j7;
                    i59 = (int) (i59 + j8);
                    System.arraycopy(bArr2, i60 * i, bArr5, 0, i);
                    while (j8 < j7) {
                        this.messDigestOTS.update(bArr5, 0, bArr5.length);
                        this.messDigestOTS.doFinal(bArr5, 0);
                        j8++;
                    }
                    int i72 = this.mdsize;
                    System.arraycopy(bArr5, 0, bArr4, i60 * i72, i72);
                    i60++;
                }
                int i73 = (i5 << this.w) - i59;
                int i74 = 0;
                while (i74 < i68) {
                    int i75 = this.mdsize;
                    System.arraycopy(bArr2, i60 * i75, bArr5, 0, i75);
                    for (long j9 = i73 & i57; j9 < i57; j9++) {
                        this.messDigestOTS.update(bArr5, 0, bArr5.length);
                        this.messDigestOTS.doFinal(bArr5, 0);
                    }
                    int i76 = this.mdsize;
                    System.arraycopy(bArr5, 0, bArr4, i60 * i76, i76);
                    int i77 = this.w;
                    i73 >>>= i77;
                    i60++;
                    i74 += i77;
                }
            }
            i2 = 0;
        }
        this.messDigestOTS.update(bArr4, i2, bArr4.length);
        byte[] bArr6 = new byte[this.mdsize];
        this.messDigestOTS.doFinal(bArr6, i2);
        return bArr6;
    }

    public int getLog(int i) {
        int i2 = 1;
        int i3 = 2;
        while (i3 < i) {
            i3 <<= 1;
            i2++;
        }
        return i2;
    }

    public int getSignatureLength() {
        int digestSize = this.messDigestOTS.getDigestSize();
        int i = this.w;
        int i2 = ((i - 1) + (digestSize << 3)) / i;
        int log = getLog((i2 << i) + 1);
        int i3 = this.w;
        return ((((log + i3) - 1) / i3) + i2) * digestSize;
    }
}
