package org.bouncycastle.math.raw;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.math.BigInteger;
import org.bouncycastle.util.Pack;
/* loaded from: classes5.dex */
public abstract class Nat224 {
    private static final long M = 4294967295L;

    public static int add(int[] iArr, int i, int[] iArr2, int i2, int[] iArr3, int i3) {
        long j = (iArr[i + 0] & 4294967295L) + (iArr2[i2 + 0] & 4294967295L) + 0;
        iArr3[i3 + 0] = (int) j;
        long j2 = (iArr[i + 1] & 4294967295L) + (iArr2[i2 + 1] & 4294967295L) + (j >>> 32);
        iArr3[i3 + 1] = (int) j2;
        long j3 = (iArr[i + 2] & 4294967295L) + (iArr2[i2 + 2] & 4294967295L) + (j2 >>> 32);
        iArr3[i3 + 2] = (int) j3;
        long j4 = (iArr[i + 3] & 4294967295L) + (iArr2[i2 + 3] & 4294967295L) + (j3 >>> 32);
        iArr3[i3 + 3] = (int) j4;
        long j5 = (iArr[i + 4] & 4294967295L) + (iArr2[i2 + 4] & 4294967295L) + (j4 >>> 32);
        iArr3[i3 + 4] = (int) j5;
        long j6 = (iArr[i + 5] & 4294967295L) + (iArr2[i2 + 5] & 4294967295L) + (j5 >>> 32);
        iArr3[i3 + 5] = (int) j6;
        long j7 = (iArr[i + 6] & 4294967295L) + (iArr2[i2 + 6] & 4294967295L) + (j6 >>> 32);
        iArr3[i3 + 6] = (int) j7;
        return (int) (j7 >>> 32);
    }

    public static int add(int[] iArr, int[] iArr2, int[] iArr3) {
        long j = (iArr[0] & 4294967295L) + (iArr2[0] & 4294967295L) + 0;
        iArr3[0] = (int) j;
        long j2 = (iArr[1] & 4294967295L) + (iArr2[1] & 4294967295L) + (j >>> 32);
        iArr3[1] = (int) j2;
        long j3 = (iArr[2] & 4294967295L) + (iArr2[2] & 4294967295L) + (j2 >>> 32);
        iArr3[2] = (int) j3;
        long j4 = (iArr[3] & 4294967295L) + (iArr2[3] & 4294967295L) + (j3 >>> 32);
        iArr3[3] = (int) j4;
        long j5 = (iArr[4] & 4294967295L) + (iArr2[4] & 4294967295L) + (j4 >>> 32);
        iArr3[4] = (int) j5;
        long j6 = (iArr[5] & 4294967295L) + (iArr2[5] & 4294967295L) + (j5 >>> 32);
        iArr3[5] = (int) j6;
        long j7 = (iArr[6] & 4294967295L) + (iArr2[6] & 4294967295L) + (j6 >>> 32);
        iArr3[6] = (int) j7;
        return (int) (j7 >>> 32);
    }

    public static int addBothTo(int[] iArr, int i, int[] iArr2, int i2, int[] iArr3, int i3) {
        int i4 = i3 + 0;
        long j = (iArr[i + 0] & 4294967295L) + (iArr2[i2 + 0] & 4294967295L) + (iArr3[i4] & 4294967295L) + 0;
        iArr3[i4] = (int) j;
        int i5 = i3 + 1;
        long j2 = (iArr[i + 1] & 4294967295L) + (iArr2[i2 + 1] & 4294967295L) + (iArr3[i5] & 4294967295L) + (j >>> 32);
        iArr3[i5] = (int) j2;
        int i6 = i3 + 2;
        long j3 = (iArr[i + 2] & 4294967295L) + (iArr2[i2 + 2] & 4294967295L) + (iArr3[i6] & 4294967295L) + (j2 >>> 32);
        iArr3[i6] = (int) j3;
        int i7 = i3 + 3;
        long j4 = (iArr[i + 3] & 4294967295L) + (iArr2[i2 + 3] & 4294967295L) + (iArr3[i7] & 4294967295L) + (j3 >>> 32);
        iArr3[i7] = (int) j4;
        int i8 = i3 + 4;
        long j5 = (iArr[i + 4] & 4294967295L) + (iArr2[i2 + 4] & 4294967295L) + (iArr3[i8] & 4294967295L) + (j4 >>> 32);
        iArr3[i8] = (int) j5;
        int i9 = i3 + 5;
        long j6 = (iArr[i + 5] & 4294967295L) + (iArr2[i2 + 5] & 4294967295L) + (iArr3[i9] & 4294967295L) + (j5 >>> 32);
        iArr3[i9] = (int) j6;
        int i10 = i3 + 6;
        long j7 = (iArr[i + 6] & 4294967295L) + (iArr2[i2 + 6] & 4294967295L) + (iArr3[i10] & 4294967295L) + (j6 >>> 32);
        iArr3[i10] = (int) j7;
        return (int) (j7 >>> 32);
    }

    public static int addBothTo(int[] iArr, int[] iArr2, int[] iArr3) {
        long j = (iArr[0] & 4294967295L) + (iArr2[0] & 4294967295L) + (iArr3[0] & 4294967295L) + 0;
        iArr3[0] = (int) j;
        long j2 = (iArr[1] & 4294967295L) + (iArr2[1] & 4294967295L) + (iArr3[1] & 4294967295L) + (j >>> 32);
        iArr3[1] = (int) j2;
        long j3 = (iArr[2] & 4294967295L) + (iArr2[2] & 4294967295L) + (iArr3[2] & 4294967295L) + (j2 >>> 32);
        iArr3[2] = (int) j3;
        long j4 = (iArr[3] & 4294967295L) + (iArr2[3] & 4294967295L) + (iArr3[3] & 4294967295L) + (j3 >>> 32);
        iArr3[3] = (int) j4;
        long j5 = (iArr[4] & 4294967295L) + (iArr2[4] & 4294967295L) + (iArr3[4] & 4294967295L) + (j4 >>> 32);
        iArr3[4] = (int) j5;
        long j6 = (iArr[5] & 4294967295L) + (iArr2[5] & 4294967295L) + (iArr3[5] & 4294967295L) + (j5 >>> 32);
        iArr3[5] = (int) j6;
        long j7 = (iArr[6] & 4294967295L) + (iArr2[6] & 4294967295L) + (iArr3[6] & 4294967295L) + (j6 >>> 32);
        iArr3[6] = (int) j7;
        return (int) (j7 >>> 32);
    }

    public static int addTo(int[] iArr, int i, int[] iArr2, int i2, int i3) {
        int i4 = i2 + 0;
        long j = (iArr[i + 0] & 4294967295L) + (iArr2[i4] & 4294967295L) + (i3 & 4294967295L);
        iArr2[i4] = (int) j;
        int i5 = i2 + 1;
        long j2 = (iArr[i + 1] & 4294967295L) + (iArr2[i5] & 4294967295L) + (j >>> 32);
        iArr2[i5] = (int) j2;
        int i6 = i2 + 2;
        long j3 = (iArr[i + 2] & 4294967295L) + (iArr2[i6] & 4294967295L) + (j2 >>> 32);
        iArr2[i6] = (int) j3;
        int i7 = i2 + 3;
        long j4 = (iArr[i + 3] & 4294967295L) + (iArr2[i7] & 4294967295L) + (j3 >>> 32);
        iArr2[i7] = (int) j4;
        int i8 = i2 + 4;
        long j5 = (iArr[i + 4] & 4294967295L) + (iArr2[i8] & 4294967295L) + (j4 >>> 32);
        iArr2[i8] = (int) j5;
        int i9 = i2 + 5;
        long j6 = (iArr[i + 5] & 4294967295L) + (iArr2[i9] & 4294967295L) + (j5 >>> 32);
        iArr2[i9] = (int) j6;
        int i10 = i2 + 6;
        long j7 = (iArr[i + 6] & 4294967295L) + (4294967295L & iArr2[i10]) + (j6 >>> 32);
        iArr2[i10] = (int) j7;
        return (int) (j7 >>> 32);
    }

    public static int addTo(int[] iArr, int[] iArr2) {
        long j = (iArr[0] & 4294967295L) + (iArr2[0] & 4294967295L) + 0;
        iArr2[0] = (int) j;
        long j2 = (iArr[1] & 4294967295L) + (iArr2[1] & 4294967295L) + (j >>> 32);
        iArr2[1] = (int) j2;
        long j3 = (iArr[2] & 4294967295L) + (iArr2[2] & 4294967295L) + (j2 >>> 32);
        iArr2[2] = (int) j3;
        long j4 = (iArr[3] & 4294967295L) + (iArr2[3] & 4294967295L) + (j3 >>> 32);
        iArr2[3] = (int) j4;
        long j5 = (iArr[4] & 4294967295L) + (iArr2[4] & 4294967295L) + (j4 >>> 32);
        iArr2[4] = (int) j5;
        long j6 = (iArr[5] & 4294967295L) + (iArr2[5] & 4294967295L) + (j5 >>> 32);
        iArr2[5] = (int) j6;
        long j7 = (iArr[6] & 4294967295L) + (4294967295L & iArr2[6]) + (j6 >>> 32);
        iArr2[6] = (int) j7;
        return (int) (j7 >>> 32);
    }

    public static int addToEachOther(int[] iArr, int i, int[] iArr2, int i2) {
        int i3 = i + 0;
        int i4 = i2 + 0;
        long j = (iArr[i3] & 4294967295L) + (iArr2[i4] & 4294967295L) + 0;
        int i5 = (int) j;
        iArr[i3] = i5;
        iArr2[i4] = i5;
        int i6 = i + 1;
        int i7 = i2 + 1;
        long j2 = (iArr[i6] & 4294967295L) + (iArr2[i7] & 4294967295L) + (j >>> 32);
        int i8 = (int) j2;
        iArr[i6] = i8;
        iArr2[i7] = i8;
        int i9 = i + 2;
        int i10 = i2 + 2;
        long j3 = (iArr[i9] & 4294967295L) + (iArr2[i10] & 4294967295L) + (j2 >>> 32);
        int i11 = (int) j3;
        iArr[i9] = i11;
        iArr2[i10] = i11;
        int i12 = i + 3;
        int i13 = i2 + 3;
        long j4 = (iArr[i12] & 4294967295L) + (iArr2[i13] & 4294967295L) + (j3 >>> 32);
        int i14 = (int) j4;
        iArr[i12] = i14;
        iArr2[i13] = i14;
        int i15 = i + 4;
        int i16 = i2 + 4;
        long j5 = (iArr[i15] & 4294967295L) + (iArr2[i16] & 4294967295L) + (j4 >>> 32);
        int i17 = (int) j5;
        iArr[i15] = i17;
        iArr2[i16] = i17;
        int i18 = i + 5;
        int i19 = i2 + 5;
        long j6 = (iArr[i18] & 4294967295L) + (iArr2[i19] & 4294967295L) + (j5 >>> 32);
        int i20 = (int) j6;
        iArr[i18] = i20;
        iArr2[i19] = i20;
        int i21 = i + 6;
        int i22 = i2 + 6;
        long j7 = (iArr[i21] & 4294967295L) + (4294967295L & iArr2[i22]) + (j6 >>> 32);
        int i23 = (int) j7;
        iArr[i21] = i23;
        iArr2[i22] = i23;
        return (int) (j7 >>> 32);
    }

    public static void copy(int[] iArr, int i, int[] iArr2, int i2) {
        iArr2[i2 + 0] = iArr[i + 0];
        iArr2[i2 + 1] = iArr[i + 1];
        iArr2[i2 + 2] = iArr[i + 2];
        iArr2[i2 + 3] = iArr[i + 3];
        iArr2[i2 + 4] = iArr[i + 4];
        iArr2[i2 + 5] = iArr[i + 5];
        iArr2[i2 + 6] = iArr[i + 6];
    }

    public static void copy(int[] iArr, int[] iArr2) {
        iArr2[0] = iArr[0];
        iArr2[1] = iArr[1];
        iArr2[2] = iArr[2];
        iArr2[3] = iArr[3];
        iArr2[4] = iArr[4];
        iArr2[5] = iArr[5];
        iArr2[6] = iArr[6];
    }

    public static int[] create() {
        return new int[7];
    }

    public static int[] createExt() {
        return new int[14];
    }

    public static boolean diff(int[] iArr, int i, int[] iArr2, int i2, int[] iArr3, int i3) {
        boolean gte = gte(iArr, i, iArr2, i2);
        if (gte) {
            sub(iArr, i, iArr2, i2, iArr3, i3);
        } else {
            sub(iArr2, i2, iArr, i, iArr3, i3);
        }
        return gte;
    }

    public static boolean eq(int[] iArr, int[] iArr2) {
        for (int i = 6; i >= 0; i--) {
            if (iArr[i] != iArr2[i]) {
                return false;
            }
        }
        return true;
    }

    public static int[] fromBigInteger(BigInteger bigInteger) {
        if (bigInteger.signum() < 0 || bigInteger.bitLength() > 224) {
            throw new IllegalArgumentException();
        }
        int[] create = create();
        for (int i = 0; i < 7; i++) {
            create[i] = bigInteger.intValue();
            bigInteger = bigInteger.shiftRight(32);
        }
        return create;
    }

    public static int getBit(int[] iArr, int i) {
        int i2;
        if (i == 0) {
            i2 = iArr[0];
        } else {
            int i3 = i >> 5;
            if (i3 < 0 || i3 >= 7) {
                return 0;
            }
            i2 = iArr[i3] >>> (i & 31);
        }
        return i2 & 1;
    }

    public static boolean gte(int[] iArr, int i, int[] iArr2, int i2) {
        for (int i3 = 6; i3 >= 0; i3--) {
            int i4 = iArr[i + i3] ^ Integer.MIN_VALUE;
            int i5 = Integer.MIN_VALUE ^ iArr2[i2 + i3];
            if (i4 < i5) {
                return false;
            }
            if (i4 > i5) {
                return true;
            }
        }
        return true;
    }

    public static boolean gte(int[] iArr, int[] iArr2) {
        for (int i = 6; i >= 0; i--) {
            int i2 = iArr[i] ^ Integer.MIN_VALUE;
            int i3 = Integer.MIN_VALUE ^ iArr2[i];
            if (i2 < i3) {
                return false;
            }
            if (i2 > i3) {
                return true;
            }
        }
        return true;
    }

    public static boolean isOne(int[] iArr) {
        if (iArr[0] != 1) {
            return false;
        }
        for (int i = 1; i < 7; i++) {
            if (iArr[i] != 0) {
                return false;
            }
        }
        return true;
    }

    public static boolean isZero(int[] iArr) {
        for (int i = 0; i < 7; i++) {
            if (iArr[i] != 0) {
                return false;
            }
        }
        return true;
    }

    public static void mul(int[] iArr, int i, int[] iArr2, int i2, int[] iArr3, int i3) {
        int i4;
        int i5;
        int i6;
        int i7;
        int i8;
        int i9;
        int i10;
        long j = iArr2[i2 + 0] & 4294967295L;
        long j2 = iArr2[i2 + 1] & 4294967295L;
        long j3 = iArr2[i2 + 2] & 4294967295L;
        long j4 = iArr2[i2 + 3] & 4294967295L;
        long j5 = iArr2[i2 + 4] & 4294967295L;
        long j6 = iArr2[i2 + 5] & 4294967295L;
        long j7 = iArr2[i2 + 6] & 4294967295L;
        long j8 = iArr[i + 0] & 4294967295L;
        long j9 = (j8 * j) + 0;
        iArr3[i3 + 0] = (int) j9;
        long j10 = (j8 * j2) + (j9 >>> 32);
        iArr3[i3 + 1] = (int) j10;
        long j11 = (j8 * j3) + (j10 >>> 32);
        iArr3[i3 + 2] = (int) j11;
        long j12 = (j8 * j4) + (j11 >>> 32);
        iArr3[i3 + 3] = (int) j12;
        long j13 = (j8 * j5) + (j12 >>> 32);
        iArr3[i3 + 4] = (int) j13;
        long j14 = (j8 * j6) + (j13 >>> 32);
        iArr3[i3 + 5] = (int) j14;
        long j15 = (j8 * j7) + (j14 >>> 32);
        iArr3[i3 + 6] = (int) j15;
        iArr3[i3 + 7] = (int) (j15 >>> 32);
        int i11 = 1;
        int i12 = i3;
        int i13 = 1;
        while (i13 < 7) {
            i12 += i11;
            long j16 = iArr[i + i13] & 4294967295L;
            long j17 = (j16 * j) + (iArr3[i4] & 4294967295L) + 0;
            iArr3[i12 + 0] = (int) j17;
            long j18 = j6;
            long j19 = (j16 * j2) + (iArr3[i5] & 4294967295L) + (j17 >>> 32);
            iArr3[i12 + 1] = (int) j19;
            long j20 = (j16 * j3) + (iArr3[i6] & 4294967295L) + (j19 >>> 32);
            iArr3[i12 + 2] = (int) j20;
            long j21 = (j16 * j4) + (iArr3[i7] & 4294967295L) + (j20 >>> 32);
            iArr3[i12 + 3] = (int) j21;
            long j22 = (j16 * j5) + (iArr3[i8] & 4294967295L) + (j21 >>> 32);
            iArr3[i12 + 4] = (int) j22;
            long j23 = (j16 * j18) + (iArr3[i9] & 4294967295L) + (j22 >>> 32);
            iArr3[i12 + 5] = (int) j23;
            long j24 = (j16 * j7) + (iArr3[i10] & 4294967295L) + (j23 >>> 32);
            iArr3[i12 + 6] = (int) j24;
            iArr3[i12 + 7] = (int) (j24 >>> 32);
            i13++;
            j6 = j18;
            j3 = j3;
            i11 = 1;
        }
    }

    public static void mul(int[] iArr, int[] iArr2, int[] iArr3) {
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        long j = iArr2[0] & 4294967295L;
        long j2 = iArr2[1] & 4294967295L;
        long j3 = iArr2[2] & 4294967295L;
        long j4 = iArr2[3] & 4294967295L;
        long j5 = iArr2[4] & 4294967295L;
        long j6 = iArr2[5] & 4294967295L;
        long j7 = iArr2[6] & 4294967295L;
        long j8 = iArr[0] & 4294967295L;
        long j9 = (j8 * j) + 0;
        iArr3[0] = (int) j9;
        long j10 = (j8 * j2) + (j9 >>> 32);
        iArr3[1] = (int) j10;
        long j11 = (j8 * j3) + (j10 >>> 32);
        iArr3[2] = (int) j11;
        long j12 = (j8 * j4) + (j11 >>> 32);
        iArr3[3] = (int) j12;
        long j13 = (j8 * j5) + (j12 >>> 32);
        iArr3[4] = (int) j13;
        long j14 = (j8 * j6) + (j13 >>> 32);
        iArr3[5] = (int) j14;
        long j15 = (j8 * j7) + (j14 >>> 32);
        iArr3[6] = (int) j15;
        iArr3[7] = (int) (j15 >>> 32);
        int i7 = 1;
        for (int i8 = 7; i7 < i8; i8 = 7) {
            long j16 = iArr[i7] & 4294967295L;
            long j17 = j;
            long j18 = (j16 * j) + (iArr3[i] & 4294967295L) + 0;
            iArr3[i7 + 0] = (int) j18;
            int i9 = i7 + 1;
            long j19 = j2;
            long j20 = (j16 * j2) + (iArr3[i9] & 4294967295L) + (j18 >>> 32);
            iArr3[i9] = (int) j20;
            long j21 = (j16 * j3) + (iArr3[i2] & 4294967295L) + (j20 >>> 32);
            iArr3[i7 + 2] = (int) j21;
            long j22 = (j16 * j4) + (iArr3[i3] & 4294967295L) + (j21 >>> 32);
            iArr3[i7 + 3] = (int) j22;
            long j23 = (j16 * j5) + (iArr3[i4] & 4294967295L) + (j22 >>> 32);
            iArr3[i7 + 4] = (int) j23;
            long j24 = (j16 * j6) + (iArr3[i5] & 4294967295L) + (j23 >>> 32);
            iArr3[i7 + 5] = (int) j24;
            long j25 = j24 >>> 32;
            long j26 = (j16 * j7) + (iArr3[i6] & 4294967295L) + j25;
            iArr3[i7 + 6] = (int) j26;
            iArr3[i7 + 7] = (int) (j26 >>> 32);
            i7 = i9;
            j = j17;
            j2 = j19;
        }
    }

    public static long mul33Add(int i, int[] iArr, int i2, int[] iArr2, int i3, int[] iArr3, int i4) {
        long j = i & 4294967295L;
        long j2 = iArr[i2 + 0] & 4294967295L;
        long j3 = (j * j2) + (iArr2[i3 + 0] & 4294967295L) + 0;
        iArr3[i4 + 0] = (int) j3;
        long j4 = iArr[i2 + 1] & 4294967295L;
        long j5 = (j * j4) + j2 + (iArr2[i3 + 1] & 4294967295L) + (j3 >>> 32);
        iArr3[i4 + 1] = (int) j5;
        long j6 = iArr[i2 + 2] & 4294967295L;
        long j7 = (j * j6) + j4 + (iArr2[i3 + 2] & 4294967295L) + (j5 >>> 32);
        iArr3[i4 + 2] = (int) j7;
        long j8 = iArr[i2 + 3] & 4294967295L;
        long j9 = (j * j8) + j6 + (iArr2[i3 + 3] & 4294967295L) + (j7 >>> 32);
        iArr3[i4 + 3] = (int) j9;
        long j10 = iArr[i2 + 4] & 4294967295L;
        long j11 = (j * j10) + j8 + (iArr2[i3 + 4] & 4294967295L) + (j9 >>> 32);
        iArr3[i4 + 4] = (int) j11;
        long j12 = iArr[i2 + 5] & 4294967295L;
        long j13 = (j * j12) + j10 + (iArr2[i3 + 5] & 4294967295L) + (j11 >>> 32);
        iArr3[i4 + 5] = (int) j13;
        long j14 = iArr[i2 + 6] & 4294967295L;
        long j15 = (j * j14) + j12 + (4294967295L & iArr2[i3 + 6]) + (j13 >>> 32);
        iArr3[i4 + 6] = (int) j15;
        return (j15 >>> 32) + j14;
    }

    public static int mul33DWordAdd(int i, long j, int[] iArr, int i2) {
        long j2 = i & 4294967295L;
        long j3 = j & 4294967295L;
        int i3 = i2 + 0;
        long j4 = (j2 * j3) + (iArr[i3] & 4294967295L) + 0;
        iArr[i3] = (int) j4;
        long j5 = j >>> 32;
        long j6 = (j2 * j5) + j3;
        int i4 = i2 + 1;
        long j7 = j6 + (iArr[i4] & 4294967295L) + (j4 >>> 32);
        iArr[i4] = (int) j7;
        int i5 = i2 + 2;
        long j8 = j5 + (iArr[i5] & 4294967295L) + (j7 >>> 32);
        iArr[i5] = (int) j8;
        int i6 = i2 + 3;
        long j9 = (j8 >>> 32) + (4294967295L & iArr[i6]);
        iArr[i6] = (int) j9;
        if ((j9 >>> 32) == 0) {
            return 0;
        }
        return Nat.incAt(7, iArr, i2, 4);
    }

    public static int mul33WordAdd(int i, int i2, int[] iArr, int i3) {
        long j = i2 & 4294967295L;
        int i4 = i3 + 0;
        long j2 = ((i & 4294967295L) * j) + (iArr[i4] & 4294967295L) + 0;
        iArr[i4] = (int) j2;
        int i5 = i3 + 1;
        long j3 = j + (iArr[i5] & 4294967295L) + (j2 >>> 32);
        iArr[i5] = (int) j3;
        int i6 = i3 + 2;
        long j4 = (j3 >>> 32) + (iArr[i6] & 4294967295L);
        iArr[i6] = (int) j4;
        if ((j4 >>> 32) == 0) {
            return 0;
        }
        return Nat.incAt(7, iArr, i3, 3);
    }

    public static int mulAddTo(int[] iArr, int i, int[] iArr2, int i2, int[] iArr3, int i3) {
        int i4;
        int i5;
        int i6;
        int i7;
        int i8;
        int i9;
        int i10;
        long j = iArr2[i2 + 0] & 4294967295L;
        long j2 = iArr2[i2 + 1] & 4294967295L;
        long j3 = iArr2[i2 + 2] & 4294967295L;
        long j4 = iArr2[i2 + 3] & 4294967295L;
        long j5 = iArr2[i2 + 4] & 4294967295L;
        long j6 = iArr2[i2 + 5] & 4294967295L;
        long j7 = iArr2[i2 + 6] & 4294967295L;
        int i11 = i3;
        long j8 = 0;
        int i12 = 0;
        while (i12 < 7) {
            long j9 = iArr[i + i12] & 4294967295L;
            long j10 = j;
            long j11 = (j9 * j) + (iArr3[i4] & 4294967295L) + 0;
            iArr3[i11 + 0] = (int) j11;
            int i13 = i11 + 1;
            long j12 = j2;
            long j13 = (j9 * j2) + (iArr3[i13] & 4294967295L) + (j11 >>> 32);
            iArr3[i13] = (int) j13;
            long j14 = j3;
            long j15 = (j9 * j3) + (iArr3[i5] & 4294967295L) + (j13 >>> 32);
            iArr3[i11 + 2] = (int) j15;
            long j16 = (j9 * j4) + (iArr3[i6] & 4294967295L) + (j15 >>> 32);
            iArr3[i11 + 3] = (int) j16;
            long j17 = (j9 * j5) + (iArr3[i7] & 4294967295L) + (j16 >>> 32);
            iArr3[i11 + 4] = (int) j17;
            long j18 = (j9 * j6) + (iArr3[i8] & 4294967295L) + (j17 >>> 32);
            iArr3[i11 + 5] = (int) j18;
            long j19 = j18 >>> 32;
            long j20 = (j9 * j7) + (iArr3[i9] & 4294967295L) + j19;
            iArr3[i11 + 6] = (int) j20;
            long j21 = (j20 >>> 32) + (iArr3[i10] & 4294967295L) + j8;
            iArr3[i11 + 7] = (int) j21;
            j8 = j21 >>> 32;
            i12++;
            i11 = i13;
            j = j10;
            j2 = j12;
            j3 = j14;
        }
        return (int) j8;
    }

    public static int mulAddTo(int[] iArr, int[] iArr2, int[] iArr3) {
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        long j = iArr2[0] & 4294967295L;
        long j2 = iArr2[1] & 4294967295L;
        long j3 = iArr2[2] & 4294967295L;
        long j4 = iArr2[3] & 4294967295L;
        long j5 = iArr2[4] & 4294967295L;
        long j6 = iArr2[5] & 4294967295L;
        long j7 = iArr2[6] & 4294967295L;
        long j8 = 0;
        int i8 = 0;
        while (i8 < 7) {
            long j9 = j7;
            long j10 = iArr[i8] & 4294967295L;
            long j11 = j6;
            long j12 = (j10 * j) + (iArr3[i] & 4294967295L) + 0;
            iArr3[i8 + 0] = (int) j12;
            int i9 = i8 + 1;
            long j13 = j2;
            long j14 = (j10 * j2) + (iArr3[i9] & 4294967295L) + (j12 >>> 32);
            iArr3[i9] = (int) j14;
            long j15 = (j10 * j3) + (iArr3[i2] & 4294967295L) + (j14 >>> 32);
            iArr3[i8 + 2] = (int) j15;
            long j16 = (j10 * j4) + (iArr3[i3] & 4294967295L) + (j15 >>> 32);
            iArr3[i8 + 3] = (int) j16;
            long j17 = (j10 * j5) + (iArr3[i4] & 4294967295L) + (j16 >>> 32);
            iArr3[i8 + 4] = (int) j17;
            long j18 = (j10 * j11) + (iArr3[i5] & 4294967295L) + (j17 >>> 32);
            iArr3[i8 + 5] = (int) j18;
            long j19 = (j10 * j9) + (iArr3[i6] & 4294967295L) + (j18 >>> 32);
            iArr3[i8 + 6] = (int) j19;
            long j20 = (j19 >>> 32) + (iArr3[i7] & 4294967295L) + j8;
            iArr3[i8 + 7] = (int) j20;
            j8 = j20 >>> 32;
            i8 = i9;
            j7 = j9;
            j6 = j11;
            j3 = j3;
            j2 = j13;
        }
        return (int) j8;
    }

    public static int mulByWord(int i, int[] iArr) {
        long j = i & 4294967295L;
        long j2 = ((iArr[0] & 4294967295L) * j) + 0;
        iArr[0] = (int) j2;
        long j3 = ((iArr[1] & 4294967295L) * j) + (j2 >>> 32);
        iArr[1] = (int) j3;
        long j4 = ((iArr[2] & 4294967295L) * j) + (j3 >>> 32);
        iArr[2] = (int) j4;
        long j5 = ((iArr[3] & 4294967295L) * j) + (j4 >>> 32);
        iArr[3] = (int) j5;
        long j6 = ((iArr[4] & 4294967295L) * j) + (j5 >>> 32);
        iArr[4] = (int) j6;
        long j7 = ((iArr[5] & 4294967295L) * j) + (j6 >>> 32);
        iArr[5] = (int) j7;
        long j8 = (j * (4294967295L & iArr[6])) + (j7 >>> 32);
        iArr[6] = (int) j8;
        return (int) (j8 >>> 32);
    }

    public static int mulByWordAddTo(int i, int[] iArr, int[] iArr2) {
        long j = i & 4294967295L;
        long j2 = ((iArr2[0] & 4294967295L) * j) + (iArr[0] & 4294967295L) + 0;
        iArr2[0] = (int) j2;
        long j3 = ((iArr2[1] & 4294967295L) * j) + (iArr[1] & 4294967295L) + (j2 >>> 32);
        iArr2[1] = (int) j3;
        long j4 = ((iArr2[2] & 4294967295L) * j) + (iArr[2] & 4294967295L) + (j3 >>> 32);
        iArr2[2] = (int) j4;
        long j5 = ((iArr2[3] & 4294967295L) * j) + (iArr[3] & 4294967295L) + (j4 >>> 32);
        iArr2[3] = (int) j5;
        long j6 = ((iArr2[4] & 4294967295L) * j) + (iArr[4] & 4294967295L) + (j5 >>> 32);
        iArr2[4] = (int) j6;
        long j7 = ((iArr2[5] & 4294967295L) * j) + (iArr[5] & 4294967295L) + (j6 >>> 32);
        iArr2[5] = (int) j7;
        long j8 = (j * (iArr2[6] & 4294967295L)) + (4294967295L & iArr[6]) + (j7 >>> 32);
        iArr2[6] = (int) j8;
        return (int) (j8 >>> 32);
    }

    public static int mulWord(int i, int[] iArr, int[] iArr2, int i2) {
        long j = i & 4294967295L;
        long j2 = 0;
        int i3 = 0;
        do {
            long j3 = ((iArr[i3] & 4294967295L) * j) + j2;
            iArr2[i2 + i3] = (int) j3;
            j2 = j3 >>> 32;
            i3++;
        } while (i3 < 7);
        return (int) j2;
    }

    public static int mulWordAddTo(int i, int[] iArr, int i2, int[] iArr2, int i3) {
        long j = i & 4294967295L;
        int i4 = i3 + 0;
        long j2 = ((iArr[i2 + 0] & 4294967295L) * j) + (iArr2[i4] & 4294967295L) + 0;
        iArr2[i4] = (int) j2;
        int i5 = i3 + 1;
        long j3 = ((iArr[i2 + 1] & 4294967295L) * j) + (iArr2[i5] & 4294967295L) + (j2 >>> 32);
        iArr2[i5] = (int) j3;
        int i6 = i3 + 2;
        long j4 = ((iArr[i2 + 2] & 4294967295L) * j) + (iArr2[i6] & 4294967295L) + (j3 >>> 32);
        iArr2[i6] = (int) j4;
        int i7 = i3 + 3;
        long j5 = ((iArr[i2 + 3] & 4294967295L) * j) + (iArr2[i7] & 4294967295L) + (j4 >>> 32);
        iArr2[i7] = (int) j5;
        int i8 = i3 + 4;
        long j6 = ((iArr[i2 + 4] & 4294967295L) * j) + (iArr2[i8] & 4294967295L) + (j5 >>> 32);
        iArr2[i8] = (int) j6;
        int i9 = i3 + 5;
        long j7 = ((iArr[i2 + 5] & 4294967295L) * j) + (iArr2[i9] & 4294967295L) + (j6 >>> 32);
        iArr2[i9] = (int) j7;
        int i10 = i3 + 6;
        long j8 = (j * (iArr[i2 + 6] & 4294967295L)) + (iArr2[i10] & 4294967295L) + (j7 >>> 32);
        iArr2[i10] = (int) j8;
        return (int) (j8 >>> 32);
    }

    public static int mulWordDwordAdd(int i, long j, int[] iArr, int i2) {
        long j2 = i & 4294967295L;
        int i3 = i2 + 0;
        long j3 = ((j & 4294967295L) * j2) + (iArr[i3] & 4294967295L) + 0;
        iArr[i3] = (int) j3;
        long j4 = j2 * (j >>> 32);
        int i4 = i2 + 1;
        long j5 = j4 + (iArr[i4] & 4294967295L) + (j3 >>> 32);
        iArr[i4] = (int) j5;
        long j6 = j5 >>> 32;
        int i5 = i2 + 2;
        long j7 = j6 + (iArr[i5] & 4294967295L);
        iArr[i5] = (int) j7;
        if ((j7 >>> 32) == 0) {
            return 0;
        }
        return Nat.incAt(7, iArr, i2, 3);
    }

    public static void square(int[] iArr, int i, int[] iArr2, int i2) {
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        int i8;
        int i9;
        int i10;
        int i11;
        int i12;
        int i13;
        long j = iArr[i + 0] & 4294967295L;
        int i14 = 0;
        int i15 = 14;
        int i16 = 6;
        while (true) {
            int i17 = i16 - 1;
            long j2 = iArr[i + i16] & 4294967295L;
            long j3 = j2 * j2;
            int i18 = i15 - 1;
            iArr2[i2 + i18] = (i14 << 31) | ((int) (j3 >>> 33));
            i15 = i18 - 1;
            iArr2[i2 + i15] = (int) (j3 >>> 1);
            i14 = (int) j3;
            if (i17 <= 0) {
                long j4 = j * j;
                long j5 = ((i14 << 31) & 4294967295L) | (j4 >>> 33);
                iArr2[i2 + 0] = (int) j4;
                int i19 = 1 & ((int) (j4 >>> 32));
                long j6 = iArr[i + 1] & 4294967295L;
                long j7 = (j6 * j) + j5;
                int i20 = (int) j7;
                iArr2[i2 + 1] = i19 | (i20 << 1);
                long j8 = iArr[i + 2] & 4294967295L;
                long j9 = (j8 * j) + (iArr2[i3] & 4294967295L) + (j7 >>> 32);
                int i21 = (int) j9;
                iArr2[i2 + 2] = (i20 >>> 31) | (i21 << 1);
                long outline9 = GeneratedOutlineSupport1.outline9(j8, j6, j9 >>> 32, iArr2[i4] & 4294967295L);
                long j10 = (iArr2[i5] & 4294967295L) + (outline9 >>> 32);
                long j11 = iArr[i + 3] & 4294967295L;
                long j12 = (iArr2[i6] & 4294967295L) + (j10 >>> 32);
                long j13 = (j11 * j) + (outline9 & 4294967295L);
                int i22 = (int) j13;
                iArr2[i2 + 3] = (i21 >>> 31) | (i22 << 1);
                long outline92 = GeneratedOutlineSupport1.outline9(j11, j6, j13 >>> 32, j10 & 4294967295L);
                long outline93 = GeneratedOutlineSupport1.outline9(j11, j8, outline92 >>> 32, j12 & 4294967295L);
                long j14 = (iArr2[i7] & 4294967295L) + (j12 >>> 32) + (outline93 >>> 32);
                long j15 = outline93 & 4294967295L;
                long j16 = iArr[i + 4] & 4294967295L;
                long j17 = (iArr2[i8] & 4294967295L) + (j14 >>> 32);
                long j18 = (iArr2[i9] & 4294967295L) + (j17 >>> 32);
                long j19 = (j16 * j) + (outline92 & 4294967295L);
                int i23 = (int) j19;
                iArr2[i2 + 4] = (i23 << 1) | (i22 >>> 31);
                long outline94 = GeneratedOutlineSupport1.outline9(j16, j6, j19 >>> 32, j15);
                long outline95 = GeneratedOutlineSupport1.outline9(j16, j8, outline94 >>> 32, j14 & 4294967295L);
                long outline96 = GeneratedOutlineSupport1.outline9(j16, j11, outline95 >>> 32, j17 & 4294967295L);
                long j20 = outline95 & 4294967295L;
                long j21 = j18 + (outline96 >>> 32);
                long j22 = outline96 & 4294967295L;
                long j23 = iArr[i + 5] & 4294967295L;
                long j24 = (iArr2[i10] & 4294967295L) + (j21 >>> 32);
                long j25 = (iArr2[i11] & 4294967295L) + (j24 >>> 32);
                long j26 = j24 & 4294967295L;
                long j27 = (j23 * j) + (outline94 & 4294967295L);
                int i24 = (int) j27;
                iArr2[i2 + 5] = (i23 >>> 31) | (i24 << 1);
                int i25 = i24 >>> 31;
                long outline97 = GeneratedOutlineSupport1.outline9(j23, j6, j27 >>> 32, j20);
                long outline98 = GeneratedOutlineSupport1.outline9(j23, j8, outline97 >>> 32, j22);
                long outline99 = GeneratedOutlineSupport1.outline9(j23, j11, outline98 >>> 32, j21 & 4294967295L);
                long j28 = outline98 & 4294967295L;
                long outline910 = GeneratedOutlineSupport1.outline9(j23, j16, outline99 >>> 32, j26);
                long j29 = outline99 & 4294967295L;
                long j30 = j25 + (outline910 >>> 32);
                long j31 = outline910 & 4294967295L;
                long j32 = iArr[i + 6] & 4294967295L;
                long j33 = (iArr2[i12] & 4294967295L) + (j30 >>> 32);
                long j34 = j30 & 4294967295L;
                long j35 = (j * j32) + (outline97 & 4294967295L);
                int i26 = (int) j35;
                iArr2[i2 + 6] = i25 | (i26 << 1);
                int i27 = i26 >>> 31;
                long outline911 = GeneratedOutlineSupport1.outline9(j32, j6, j35 >>> 32, j28);
                long outline912 = GeneratedOutlineSupport1.outline9(j32, j8, outline911 >>> 32, j29);
                long outline913 = GeneratedOutlineSupport1.outline9(j32, j11, outline912 >>> 32, j31);
                long outline914 = GeneratedOutlineSupport1.outline9(j32, j16, outline913 >>> 32, j34);
                long outline915 = GeneratedOutlineSupport1.outline9(j32, j23, outline914 >>> 32, j33 & 4294967295L);
                long j36 = (iArr2[i13] & 4294967295L) + (j33 >>> 32) + (outline915 >>> 32);
                int i28 = (int) outline911;
                iArr2[i2 + 7] = (i28 << 1) | i27;
                int i29 = (int) outline912;
                iArr2[i2 + 8] = (i28 >>> 31) | (i29 << 1);
                int i30 = i29 >>> 31;
                int i31 = (int) outline913;
                iArr2[i2 + 9] = i30 | (i31 << 1);
                int i32 = i31 >>> 31;
                int i33 = (int) outline914;
                iArr2[i2 + 10] = i32 | (i33 << 1);
                int i34 = i33 >>> 31;
                int i35 = (int) outline915;
                iArr2[i2 + 11] = i34 | (i35 << 1);
                int i36 = i35 >>> 31;
                int i37 = (int) j36;
                iArr2[i2 + 12] = i36 | (i37 << 1);
                int i38 = i37 >>> 31;
                int i39 = i2 + 13;
                iArr2[i39] = i38 | ((iArr2[i39] + ((int) (j36 >>> 32))) << 1);
                return;
            }
            i16 = i17;
        }
    }

    public static void square(int[] iArr, int[] iArr2) {
        long j = iArr[0] & 4294967295L;
        int i = 14;
        int i2 = 6;
        int i3 = 0;
        while (true) {
            int i4 = i2 - 1;
            long j2 = iArr[i2] & 4294967295L;
            long j3 = j2 * j2;
            int i5 = i - 1;
            iArr2[i5] = (i3 << 31) | ((int) (j3 >>> 33));
            i = i5 - 1;
            iArr2[i] = (int) (j3 >>> 1);
            int i6 = (int) j3;
            if (i4 <= 0) {
                long j4 = j * j;
                long j5 = (j4 >>> 33) | ((i6 << 31) & 4294967295L);
                iArr2[0] = (int) j4;
                long j6 = iArr[1] & 4294967295L;
                long j7 = (j6 * j) + j5;
                int i7 = (int) j7;
                iArr2[1] = (((int) (j4 >>> 32)) & 1) | (i7 << 1);
                long j8 = iArr[2] & 4294967295L;
                long j9 = (j8 * j) + (iArr2[2] & 4294967295L) + (j7 >>> 32);
                int i8 = (int) j9;
                iArr2[2] = (i7 >>> 31) | (i8 << 1);
                int i9 = i8 >>> 31;
                long outline9 = GeneratedOutlineSupport1.outline9(j8, j6, j9 >>> 32, iArr2[3] & 4294967295L);
                long j10 = (4294967295L & iArr2[4]) + (outline9 >>> 32);
                long j11 = iArr[3] & 4294967295L;
                long j12 = (iArr2[5] & 4294967295L) + (j10 >>> 32);
                long j13 = j12 & 4294967295L;
                long j14 = (j11 * j) + (outline9 & 4294967295L);
                int i10 = (int) j14;
                iArr2[3] = i9 | (i10 << 1);
                int i11 = i10 >>> 31;
                long outline92 = GeneratedOutlineSupport1.outline9(j11, j6, j14 >>> 32, j10 & 4294967295L);
                long outline93 = GeneratedOutlineSupport1.outline9(j11, j8, outline92 >>> 32, j13);
                long j15 = (iArr2[6] & 4294967295L) + (j12 >>> 32) + (outline93 >>> 32);
                long j16 = iArr[4] & 4294967295L;
                long j17 = (iArr2[7] & 4294967295L) + (j15 >>> 32);
                long j18 = j17 & 4294967295L;
                long j19 = (j16 * j) + (outline92 & 4294967295L);
                int i12 = (int) j19;
                iArr2[4] = i11 | (i12 << 1);
                long outline94 = GeneratedOutlineSupport1.outline9(j16, j6, j19 >>> 32, outline93 & 4294967295L);
                long outline95 = GeneratedOutlineSupport1.outline9(j16, j8, outline94 >>> 32, j15 & 4294967295L);
                long outline96 = GeneratedOutlineSupport1.outline9(j16, j11, outline95 >>> 32, j18);
                long j20 = outline95 & 4294967295L;
                long j21 = (outline96 >>> 32) + (iArr2[8] & 4294967295L) + (j17 >>> 32);
                long j22 = iArr[5] & 4294967295L;
                long j23 = (iArr2[9] & 4294967295L) + (j21 >>> 32);
                long j24 = j21 & 4294967295L;
                long j25 = (iArr2[10] & 4294967295L) + (j23 >>> 32);
                long j26 = (j22 * j) + (outline94 & 4294967295L);
                int i13 = (int) j26;
                iArr2[5] = (i12 >>> 31) | (i13 << 1);
                int i14 = i13 >>> 31;
                long outline97 = GeneratedOutlineSupport1.outline9(j22, j6, j26 >>> 32, j20);
                long outline98 = GeneratedOutlineSupport1.outline9(j22, j8, outline97 >>> 32, outline96 & 4294967295L);
                long outline99 = GeneratedOutlineSupport1.outline9(j22, j11, outline98 >>> 32, j24);
                long j27 = outline98 & 4294967295L;
                long outline910 = GeneratedOutlineSupport1.outline9(j22, j16, outline99 >>> 32, j23 & 4294967295L);
                long j28 = j25 + (outline910 >>> 32);
                long j29 = outline910 & 4294967295L;
                long j30 = iArr[6] & 4294967295L;
                long j31 = (iArr2[11] & 4294967295L) + (j28 >>> 32);
                long j32 = j31 & 4294967295L;
                long j33 = (j * j30) + (outline97 & 4294967295L);
                int i15 = (int) j33;
                iArr2[6] = i14 | (i15 << 1);
                long outline911 = GeneratedOutlineSupport1.outline9(j6, j30, j33 >>> 32, j27);
                long outline912 = GeneratedOutlineSupport1.outline9(j30, j8, outline911 >>> 32, outline99 & 4294967295L);
                long outline913 = GeneratedOutlineSupport1.outline9(j30, j11, outline912 >>> 32, j29);
                long outline914 = GeneratedOutlineSupport1.outline9(j30, j16, outline913 >>> 32, j28 & 4294967295L);
                long outline915 = GeneratedOutlineSupport1.outline9(j30, j22, outline914 >>> 32, j32);
                long j34 = (iArr2[12] & 4294967295L) + (j31 >>> 32) + (outline915 >>> 32);
                int i16 = (int) outline911;
                iArr2[7] = (i15 >>> 31) | (i16 << 1);
                int i17 = i16 >>> 31;
                int i18 = (int) outline912;
                iArr2[8] = i17 | (i18 << 1);
                int i19 = i18 >>> 31;
                int i20 = (int) outline913;
                iArr2[9] = i19 | (i20 << 1);
                int i21 = i20 >>> 31;
                int i22 = (int) outline914;
                iArr2[10] = i21 | (i22 << 1);
                int i23 = i22 >>> 31;
                int i24 = (int) outline915;
                iArr2[11] = i23 | (i24 << 1);
                int i25 = i24 >>> 31;
                int i26 = (int) j34;
                iArr2[12] = i25 | (i26 << 1);
                iArr2[13] = (i26 >>> 31) | ((iArr2[13] + ((int) (j34 >>> 32))) << 1);
                return;
            }
            i2 = i4;
            i3 = i6;
        }
    }

    public static int sub(int[] iArr, int i, int[] iArr2, int i2, int[] iArr3, int i3) {
        long j = ((iArr[i + 0] & 4294967295L) - (iArr2[i2 + 0] & 4294967295L)) + 0;
        iArr3[i3 + 0] = (int) j;
        long j2 = ((iArr[i + 1] & 4294967295L) - (iArr2[i2 + 1] & 4294967295L)) + (j >> 32);
        iArr3[i3 + 1] = (int) j2;
        long j3 = ((iArr[i + 2] & 4294967295L) - (iArr2[i2 + 2] & 4294967295L)) + (j2 >> 32);
        iArr3[i3 + 2] = (int) j3;
        long j4 = ((iArr[i + 3] & 4294967295L) - (iArr2[i2 + 3] & 4294967295L)) + (j3 >> 32);
        iArr3[i3 + 3] = (int) j4;
        long j5 = ((iArr[i + 4] & 4294967295L) - (iArr2[i2 + 4] & 4294967295L)) + (j4 >> 32);
        iArr3[i3 + 4] = (int) j5;
        long j6 = ((iArr[i + 5] & 4294967295L) - (iArr2[i2 + 5] & 4294967295L)) + (j5 >> 32);
        iArr3[i3 + 5] = (int) j6;
        long j7 = ((iArr[i + 6] & 4294967295L) - (iArr2[i2 + 6] & 4294967295L)) + (j6 >> 32);
        iArr3[i3 + 6] = (int) j7;
        return (int) (j7 >> 32);
    }

    public static int sub(int[] iArr, int[] iArr2, int[] iArr3) {
        long j = ((iArr[0] & 4294967295L) - (iArr2[0] & 4294967295L)) + 0;
        iArr3[0] = (int) j;
        long j2 = ((iArr[1] & 4294967295L) - (iArr2[1] & 4294967295L)) + (j >> 32);
        iArr3[1] = (int) j2;
        long j3 = ((iArr[2] & 4294967295L) - (iArr2[2] & 4294967295L)) + (j2 >> 32);
        iArr3[2] = (int) j3;
        long j4 = ((iArr[3] & 4294967295L) - (iArr2[3] & 4294967295L)) + (j3 >> 32);
        iArr3[3] = (int) j4;
        long j5 = ((iArr[4] & 4294967295L) - (iArr2[4] & 4294967295L)) + (j4 >> 32);
        iArr3[4] = (int) j5;
        long j6 = ((iArr[5] & 4294967295L) - (iArr2[5] & 4294967295L)) + (j5 >> 32);
        iArr3[5] = (int) j6;
        long j7 = ((iArr[6] & 4294967295L) - (iArr2[6] & 4294967295L)) + (j6 >> 32);
        iArr3[6] = (int) j7;
        return (int) (j7 >> 32);
    }

    public static int subBothFrom(int[] iArr, int[] iArr2, int[] iArr3) {
        long j = (((iArr3[0] & 4294967295L) - (iArr[0] & 4294967295L)) - (iArr2[0] & 4294967295L)) + 0;
        iArr3[0] = (int) j;
        long j2 = (((iArr3[1] & 4294967295L) - (iArr[1] & 4294967295L)) - (iArr2[1] & 4294967295L)) + (j >> 32);
        iArr3[1] = (int) j2;
        long j3 = (((iArr3[2] & 4294967295L) - (iArr[2] & 4294967295L)) - (iArr2[2] & 4294967295L)) + (j2 >> 32);
        iArr3[2] = (int) j3;
        long j4 = (((iArr3[3] & 4294967295L) - (iArr[3] & 4294967295L)) - (iArr2[3] & 4294967295L)) + (j3 >> 32);
        iArr3[3] = (int) j4;
        long j5 = (((iArr3[4] & 4294967295L) - (iArr[4] & 4294967295L)) - (iArr2[4] & 4294967295L)) + (j4 >> 32);
        iArr3[4] = (int) j5;
        long j6 = (((iArr3[5] & 4294967295L) - (iArr[5] & 4294967295L)) - (iArr2[5] & 4294967295L)) + (j5 >> 32);
        iArr3[5] = (int) j6;
        long j7 = (((iArr3[6] & 4294967295L) - (iArr[6] & 4294967295L)) - (iArr2[6] & 4294967295L)) + (j6 >> 32);
        iArr3[6] = (int) j7;
        return (int) (j7 >> 32);
    }

    public static int subFrom(int[] iArr, int i, int[] iArr2, int i2) {
        int i3 = i2 + 0;
        long j = ((iArr2[i3] & 4294967295L) - (iArr[i + 0] & 4294967295L)) + 0;
        iArr2[i3] = (int) j;
        int i4 = i2 + 1;
        long j2 = ((iArr2[i4] & 4294967295L) - (iArr[i + 1] & 4294967295L)) + (j >> 32);
        iArr2[i4] = (int) j2;
        int i5 = i2 + 2;
        long j3 = ((iArr2[i5] & 4294967295L) - (iArr[i + 2] & 4294967295L)) + (j2 >> 32);
        iArr2[i5] = (int) j3;
        int i6 = i2 + 3;
        long j4 = ((iArr2[i6] & 4294967295L) - (iArr[i + 3] & 4294967295L)) + (j3 >> 32);
        iArr2[i6] = (int) j4;
        int i7 = i2 + 4;
        long j5 = ((iArr2[i7] & 4294967295L) - (iArr[i + 4] & 4294967295L)) + (j4 >> 32);
        iArr2[i7] = (int) j5;
        int i8 = i2 + 5;
        long j6 = ((iArr2[i8] & 4294967295L) - (iArr[i + 5] & 4294967295L)) + (j5 >> 32);
        iArr2[i8] = (int) j6;
        int i9 = i2 + 6;
        long j7 = ((iArr2[i9] & 4294967295L) - (iArr[i + 6] & 4294967295L)) + (j6 >> 32);
        iArr2[i9] = (int) j7;
        return (int) (j7 >> 32);
    }

    public static int subFrom(int[] iArr, int[] iArr2) {
        long j = ((iArr2[0] & 4294967295L) - (iArr[0] & 4294967295L)) + 0;
        iArr2[0] = (int) j;
        long j2 = ((iArr2[1] & 4294967295L) - (iArr[1] & 4294967295L)) + (j >> 32);
        iArr2[1] = (int) j2;
        long j3 = ((iArr2[2] & 4294967295L) - (iArr[2] & 4294967295L)) + (j2 >> 32);
        iArr2[2] = (int) j3;
        long j4 = ((iArr2[3] & 4294967295L) - (iArr[3] & 4294967295L)) + (j3 >> 32);
        iArr2[3] = (int) j4;
        long j5 = ((iArr2[4] & 4294967295L) - (iArr[4] & 4294967295L)) + (j4 >> 32);
        iArr2[4] = (int) j5;
        long j6 = ((iArr2[5] & 4294967295L) - (iArr[5] & 4294967295L)) + (j5 >> 32);
        iArr2[5] = (int) j6;
        long j7 = ((iArr2[6] & 4294967295L) - (4294967295L & iArr[6])) + (j6 >> 32);
        iArr2[6] = (int) j7;
        return (int) (j7 >> 32);
    }

    public static BigInteger toBigInteger(int[] iArr) {
        byte[] bArr = new byte[28];
        for (int i = 0; i < 7; i++) {
            int i2 = iArr[i];
            if (i2 != 0) {
                Pack.intToBigEndian(i2, bArr, (6 - i) << 2);
            }
        }
        return new BigInteger(1, bArr);
    }

    public static void zero(int[] iArr) {
        iArr[0] = 0;
        iArr[1] = 0;
        iArr[2] = 0;
        iArr[3] = 0;
        iArr[4] = 0;
        iArr[5] = 0;
        iArr[6] = 0;
    }
}
