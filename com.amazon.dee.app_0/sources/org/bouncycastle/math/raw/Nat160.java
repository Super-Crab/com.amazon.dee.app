package org.bouncycastle.math.raw;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.math.BigInteger;
import org.bouncycastle.util.Pack;
/* loaded from: classes5.dex */
public abstract class Nat160 {
    private static final long M = 4294967295L;

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
        return (int) (j5 >>> 32);
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
        return (int) (j5 >>> 32);
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
        long j5 = (iArr[i + 4] & 4294967295L) + (4294967295L & iArr2[i8]) + (j4 >>> 32);
        iArr2[i8] = (int) j5;
        return (int) (j5 >>> 32);
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
        long j5 = (iArr[4] & 4294967295L) + (4294967295L & iArr2[4]) + (j4 >>> 32);
        iArr2[4] = (int) j5;
        return (int) (j5 >>> 32);
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
        long j5 = (iArr[i15] & 4294967295L) + (4294967295L & iArr2[i16]) + (j4 >>> 32);
        int i17 = (int) j5;
        iArr[i15] = i17;
        iArr2[i16] = i17;
        return (int) (j5 >>> 32);
    }

    public static void copy(int[] iArr, int i, int[] iArr2, int i2) {
        iArr2[i2 + 0] = iArr[i + 0];
        iArr2[i2 + 1] = iArr[i + 1];
        iArr2[i2 + 2] = iArr[i + 2];
        iArr2[i2 + 3] = iArr[i + 3];
        iArr2[i2 + 4] = iArr[i + 4];
    }

    public static void copy(int[] iArr, int[] iArr2) {
        iArr2[0] = iArr[0];
        iArr2[1] = iArr[1];
        iArr2[2] = iArr[2];
        iArr2[3] = iArr[3];
        iArr2[4] = iArr[4];
    }

    public static int[] create() {
        return new int[5];
    }

    public static int[] createExt() {
        return new int[10];
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
        for (int i = 4; i >= 0; i--) {
            if (iArr[i] != iArr2[i]) {
                return false;
            }
        }
        return true;
    }

    public static int[] fromBigInteger(BigInteger bigInteger) {
        if (bigInteger.signum() < 0 || bigInteger.bitLength() > 160) {
            throw new IllegalArgumentException();
        }
        int[] create = create();
        for (int i = 0; i < 5; i++) {
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
            if (i3 < 0 || i3 >= 5) {
                return 0;
            }
            i2 = iArr[i3] >>> (i & 31);
        }
        return i2 & 1;
    }

    public static boolean gte(int[] iArr, int i, int[] iArr2, int i2) {
        for (int i3 = 4; i3 >= 0; i3--) {
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
        for (int i = 4; i >= 0; i--) {
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
        for (int i = 1; i < 5; i++) {
            if (iArr[i] != 0) {
                return false;
            }
        }
        return true;
    }

    public static boolean isZero(int[] iArr) {
        for (int i = 0; i < 5; i++) {
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
        long j = iArr2[i2 + 0] & 4294967295L;
        long j2 = iArr2[i2 + 1] & 4294967295L;
        long j3 = iArr2[i2 + 2] & 4294967295L;
        long j4 = iArr2[i2 + 3] & 4294967295L;
        long j5 = iArr2[i2 + 4] & 4294967295L;
        long j6 = iArr[i + 0] & 4294967295L;
        long j7 = (j6 * j) + 0;
        iArr3[i3 + 0] = (int) j7;
        long j8 = (j6 * j2) + (j7 >>> 32);
        iArr3[i3 + 1] = (int) j8;
        long j9 = (j6 * j3) + (j8 >>> 32);
        iArr3[i3 + 2] = (int) j9;
        long j10 = (j6 * j4) + (j9 >>> 32);
        iArr3[i3 + 3] = (int) j10;
        long j11 = (j6 * j5) + (j10 >>> 32);
        iArr3[i3 + 4] = (int) j11;
        iArr3[i3 + 5] = (int) (j11 >>> 32);
        int i9 = 1;
        int i10 = i3;
        int i11 = 1;
        while (i11 < 5) {
            i10 += i9;
            long j12 = iArr[i + i11] & 4294967295L;
            long j13 = (j12 * j) + (iArr3[i4] & 4294967295L) + 0;
            iArr3[i10 + 0] = (int) j13;
            long j14 = j;
            long j15 = (j12 * j2) + (iArr3[i5] & 4294967295L) + (j13 >>> 32);
            iArr3[i10 + 1] = (int) j15;
            long j16 = j3;
            long j17 = (j12 * j3) + (iArr3[i6] & 4294967295L) + (j15 >>> 32);
            iArr3[i10 + 2] = (int) j17;
            long j18 = (j12 * j4) + (iArr3[i7] & 4294967295L) + (j17 >>> 32);
            iArr3[i10 + 3] = (int) j18;
            long j19 = (j12 * j5) + (iArr3[i8] & 4294967295L) + (j18 >>> 32);
            iArr3[i10 + 4] = (int) j19;
            iArr3[i10 + 5] = (int) (j19 >>> 32);
            i11++;
            j3 = j16;
            j = j14;
            i9 = 1;
        }
    }

    public static void mul(int[] iArr, int[] iArr2, int[] iArr3) {
        long j = iArr2[0] & 4294967295L;
        int i = 1;
        long j2 = iArr2[1] & 4294967295L;
        long j3 = iArr2[2] & 4294967295L;
        long j4 = iArr2[3] & 4294967295L;
        long j5 = iArr2[4] & 4294967295L;
        long j6 = iArr[0] & 4294967295L;
        long j7 = (j6 * j) + 0;
        iArr3[0] = (int) j7;
        long j8 = (j6 * j2) + (j7 >>> 32);
        iArr3[1] = (int) j8;
        long j9 = (j6 * j3) + (j8 >>> 32);
        iArr3[2] = (int) j9;
        long j10 = (j6 * j4) + (j9 >>> 32);
        iArr3[3] = (int) j10;
        long j11 = (j6 * j5) + (j10 >>> 32);
        iArr3[4] = (int) j11;
        iArr3[5] = (int) (j11 >>> 32);
        for (int i2 = 5; i < i2; i2 = 5) {
            long j12 = iArr[i] & 4294967295L;
            int i3 = i + 0;
            long j13 = j;
            long j14 = (j12 * j) + (iArr3[i3] & 4294967295L) + 0;
            iArr3[i3] = (int) j14;
            int i4 = i + 1;
            long j15 = j2;
            long j16 = (j12 * j2) + (iArr3[i4] & 4294967295L) + (j14 >>> 32);
            iArr3[i4] = (int) j16;
            int i5 = i + 2;
            long j17 = (j12 * j3) + (iArr3[i5] & 4294967295L) + (j16 >>> 32);
            iArr3[i5] = (int) j17;
            int i6 = i + 3;
            long j18 = (j12 * j4) + (iArr3[i6] & 4294967295L) + (j17 >>> 32);
            iArr3[i6] = (int) j18;
            long j19 = j18 >>> 32;
            int i7 = i + 4;
            long j20 = (j12 * j5) + (iArr3[i7] & 4294967295L) + j19;
            iArr3[i7] = (int) j20;
            iArr3[i + 5] = (int) (j20 >>> 32);
            i = i4;
            j = j13;
            j2 = j15;
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
        long j11 = (j * j10) + j8 + (4294967295L & iArr2[i3 + 4]) + (j9 >>> 32);
        iArr3[i4 + 4] = (int) j11;
        return (j11 >>> 32) + j10;
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
        return Nat.incAt(5, iArr, i2, 4);
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
        return Nat.incAt(5, iArr, i3, 3);
    }

    public static int mulAddTo(int[] iArr, int i, int[] iArr2, int i2, int[] iArr3, int i3) {
        int i4;
        int i5;
        int i6;
        int i7;
        int i8;
        long j = 4294967295L;
        long j2 = iArr2[i2 + 0] & 4294967295L;
        long j3 = iArr2[i2 + 1] & 4294967295L;
        long j4 = iArr2[i2 + 2] & 4294967295L;
        long j5 = iArr2[i2 + 3] & 4294967295L;
        long j6 = iArr2[i2 + 4] & 4294967295L;
        int i9 = 0;
        int i10 = i3;
        long j7 = 0;
        while (i9 < 5) {
            long j8 = iArr[i + i9] & j;
            long j9 = j2;
            long j10 = (j8 * j2) + (iArr3[i4] & j) + 0;
            iArr3[i10 + 0] = (int) j10;
            int i11 = i10 + 1;
            long j11 = j3;
            long j12 = (j8 * j3) + (iArr3[i11] & 4294967295L) + (j10 >>> 32);
            iArr3[i11] = (int) j12;
            long j13 = (j8 * j4) + (iArr3[i5] & 4294967295L) + (j12 >>> 32);
            iArr3[i10 + 2] = (int) j13;
            long j14 = (j8 * j5) + (iArr3[i6] & 4294967295L) + (j13 >>> 32);
            iArr3[i10 + 3] = (int) j14;
            long j15 = j14 >>> 32;
            long j16 = (j8 * j6) + (iArr3[i7] & 4294967295L) + j15;
            iArr3[i10 + 4] = (int) j16;
            long j17 = (j16 >>> 32) + (iArr3[i8] & 4294967295L) + j7;
            iArr3[i10 + 5] = (int) j17;
            j7 = j17 >>> 32;
            i9++;
            i10 = i11;
            j2 = j9;
            j = 4294967295L;
            j3 = j11;
            j4 = j4;
        }
        return (int) j7;
    }

    public static int mulAddTo(int[] iArr, int[] iArr2, int[] iArr3) {
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        int i6 = 0;
        long j = 4294967295L;
        long j2 = iArr2[0] & 4294967295L;
        long j3 = iArr2[1] & 4294967295L;
        long j4 = iArr2[2] & 4294967295L;
        long j5 = iArr2[3] & 4294967295L;
        long j6 = iArr2[4] & 4294967295L;
        long j7 = 0;
        while (i6 < 5) {
            long j8 = iArr[i6] & j;
            long j9 = j2;
            long j10 = (j8 * j2) + (iArr3[i] & j) + 0;
            iArr3[i6 + 0] = (int) j10;
            int i7 = i6 + 1;
            long j11 = j3;
            long j12 = (j8 * j3) + (iArr3[i7] & 4294967295L) + (j10 >>> 32);
            iArr3[i7] = (int) j12;
            long j13 = (j8 * j4) + (iArr3[i2] & 4294967295L) + (j12 >>> 32);
            iArr3[i6 + 2] = (int) j13;
            long j14 = (j8 * j5) + (iArr3[i3] & 4294967295L) + (j13 >>> 32);
            iArr3[i6 + 3] = (int) j14;
            long j15 = j14 >>> 32;
            long j16 = (j8 * j6) + (iArr3[i4] & 4294967295L) + j15;
            iArr3[i6 + 4] = (int) j16;
            long j17 = (j16 >>> 32) + (iArr3[i5] & 4294967295L) + j7;
            iArr3[i6 + 5] = (int) j17;
            j7 = j17 >>> 32;
            j = 4294967295L;
            j2 = j9;
            i6 = i7;
            j3 = j11;
            j4 = j4;
        }
        return (int) j7;
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
        } while (i3 < 5);
        return (int) j2;
    }

    public static int mulWordAddExt(int i, int[] iArr, int i2, int[] iArr2, int i3) {
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
        long j6 = (j * (iArr[i2 + 4] & 4294967295L)) + (iArr2[i8] & 4294967295L) + (j5 >>> 32);
        iArr2[i8] = (int) j6;
        return (int) (j6 >>> 32);
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
        return Nat.incAt(5, iArr, i2, 3);
    }

    public static int mulWordsAdd(int i, int i2, int[] iArr, int i3) {
        int i4 = i3 + 0;
        long j = ((i2 & 4294967295L) * (i & 4294967295L)) + (iArr[i4] & 4294967295L) + 0;
        iArr[i4] = (int) j;
        int i5 = i3 + 1;
        long j2 = (j >>> 32) + (4294967295L & iArr[i5]);
        iArr[i5] = (int) j2;
        if ((j2 >>> 32) == 0) {
            return 0;
        }
        return Nat.incAt(5, iArr, i3, 2);
    }

    public static void square(int[] iArr, int i, int[] iArr2, int i2) {
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        int i8;
        int i9;
        long j = iArr[i + 0] & 4294967295L;
        int i10 = 0;
        int i11 = 10;
        int i12 = 4;
        while (true) {
            int i13 = i12 - 1;
            long j2 = iArr[i + i12] & 4294967295L;
            long j3 = j2 * j2;
            int i14 = i11 - 1;
            iArr2[i2 + i14] = (i10 << 31) | ((int) (j3 >>> 33));
            i11 = i14 - 1;
            iArr2[i2 + i11] = (int) (j3 >>> 1);
            i10 = (int) j3;
            if (i13 <= 0) {
                long j4 = j * j;
                long j5 = ((i10 << 31) & 4294967295L) | (j4 >>> 33);
                iArr2[i2 + 0] = (int) j4;
                int i15 = 1 & ((int) (j4 >>> 32));
                long j6 = iArr[i + 1] & 4294967295L;
                long j7 = (j6 * j) + j5;
                int i16 = (int) j7;
                iArr2[i2 + 1] = i15 | (i16 << 1);
                long j8 = iArr[i + 2] & 4294967295L;
                long j9 = (j8 * j) + (iArr2[i3] & 4294967295L) + (j7 >>> 32);
                int i17 = (int) j9;
                iArr2[i2 + 2] = (i16 >>> 31) | (i17 << 1);
                long outline9 = GeneratedOutlineSupport1.outline9(j8, j6, j9 >>> 32, iArr2[i4] & 4294967295L);
                long j10 = (iArr2[i5] & 4294967295L) + (outline9 >>> 32);
                long j11 = iArr[i + 3] & 4294967295L;
                long j12 = (iArr2[i6] & 4294967295L) + (j10 >>> 32);
                long j13 = (j11 * j) + (outline9 & 4294967295L);
                int i18 = (int) j13;
                iArr2[i2 + 3] = (i17 >>> 31) | (i18 << 1);
                long outline92 = GeneratedOutlineSupport1.outline9(j11, j6, j13 >>> 32, j10 & 4294967295L);
                long outline93 = GeneratedOutlineSupport1.outline9(j11, j8, outline92 >>> 32, j12 & 4294967295L);
                long j14 = (iArr2[i7] & 4294967295L) + (j12 >>> 32) + (outline93 >>> 32);
                long j15 = outline93 & 4294967295L;
                long j16 = iArr[i + 4] & 4294967295L;
                long j17 = (iArr2[i8] & 4294967295L) + (j14 >>> 32);
                long j18 = (j * j16) + (outline92 & 4294967295L);
                int i19 = (int) j18;
                iArr2[i2 + 4] = (i19 << 1) | (i18 >>> 31);
                long outline94 = GeneratedOutlineSupport1.outline9(j16, j6, j18 >>> 32, j15);
                long outline95 = GeneratedOutlineSupport1.outline9(j16, j8, outline94 >>> 32, j14 & 4294967295L);
                long outline96 = GeneratedOutlineSupport1.outline9(j16, j11, outline95 >>> 32, j17 & 4294967295L);
                long j19 = (iArr2[i9] & 4294967295L) + (j17 >>> 32) + (outline96 >>> 32);
                int i20 = (int) outline94;
                iArr2[i2 + 5] = (i20 << 1) | (i19 >>> 31);
                int i21 = (int) outline95;
                iArr2[i2 + 6] = (i20 >>> 31) | (i21 << 1);
                int i22 = i21 >>> 31;
                int i23 = (int) outline96;
                iArr2[i2 + 7] = i22 | (i23 << 1);
                int i24 = i23 >>> 31;
                int i25 = (int) j19;
                iArr2[i2 + 8] = i24 | (i25 << 1);
                int i26 = i25 >>> 31;
                int i27 = i2 + 9;
                iArr2[i27] = i26 | ((iArr2[i27] + ((int) (j19 >>> 32))) << 1);
                return;
            }
            i12 = i13;
        }
    }

    public static void square(int[] iArr, int[] iArr2) {
        long j = iArr[0] & 4294967295L;
        int i = 0;
        int i2 = 10;
        int i3 = 4;
        while (true) {
            int i4 = i3 - 1;
            long j2 = iArr[i3] & 4294967295L;
            long j3 = j2 * j2;
            int i5 = i2 - 1;
            iArr2[i5] = (i << 31) | ((int) (j3 >>> 33));
            i2 = i5 - 1;
            iArr2[i2] = (int) (j3 >>> 1);
            int i6 = (int) j3;
            if (i4 <= 0) {
                long j4 = j * j;
                long j5 = (j4 >>> 33) | ((i6 << 31) & 4294967295L);
                iArr2[0] = (int) j4;
                long j6 = iArr[1] & 4294967295L;
                long j7 = j5 + (j6 * j);
                int i7 = (int) j7;
                iArr2[1] = (i7 << 1) | (((int) (j4 >>> 32)) & 1);
                long j8 = (iArr2[2] & 4294967295L) + (j7 >>> 32);
                long j9 = iArr[2] & 4294967295L;
                long j10 = (j9 * j) + j8;
                int i8 = (int) j10;
                iArr2[2] = (i7 >>> 31) | (i8 << 1);
                long outline9 = GeneratedOutlineSupport1.outline9(j9, j6, j10 >>> 32, iArr2[3] & 4294967295L);
                long j11 = (iArr2[4] & 4294967295L) + (outline9 >>> 32);
                long j12 = iArr[3] & 4294967295L;
                long j13 = (iArr2[5] & 4294967295L) + (j11 >>> 32);
                long j14 = j11 & 4294967295L;
                long j15 = (iArr2[6] & 4294967295L) + (j13 >>> 32);
                long j16 = (j12 * j) + (outline9 & 4294967295L);
                int i9 = (int) j16;
                iArr2[3] = (i9 << 1) | (i8 >>> 31);
                long outline92 = GeneratedOutlineSupport1.outline9(j12, j6, j16 >>> 32, j14);
                long outline93 = GeneratedOutlineSupport1.outline9(j12, j9, outline92 >>> 32, j13 & 4294967295L);
                long j17 = j15 + (outline93 >>> 32);
                long j18 = iArr[4] & 4294967295L;
                long j19 = (iArr2[7] & 4294967295L) + (j17 >>> 32);
                long j20 = (iArr2[8] & 4294967295L) + (j19 >>> 32);
                long j21 = j19 & 4294967295L;
                long j22 = (j18 * j) + (outline92 & 4294967295L);
                int i10 = (int) j22;
                iArr2[4] = (i9 >>> 31) | (i10 << 1);
                int i11 = i10 >>> 31;
                long outline94 = GeneratedOutlineSupport1.outline9(j6, j18, j22 >>> 32, outline93 & 4294967295L);
                long outline95 = GeneratedOutlineSupport1.outline9(j18, j9, outline94 >>> 32, j17 & 4294967295L);
                long outline96 = GeneratedOutlineSupport1.outline9(j18, j12, outline95 >>> 32, j21);
                long j23 = j20 + (outline96 >>> 32);
                int i12 = (int) outline94;
                iArr2[5] = i11 | (i12 << 1);
                int i13 = (int) outline95;
                iArr2[6] = (i12 >>> 31) | (i13 << 1);
                int i14 = i13 >>> 31;
                int i15 = (int) outline96;
                iArr2[7] = i14 | (i15 << 1);
                int i16 = i15 >>> 31;
                int i17 = (int) j23;
                iArr2[8] = i16 | (i17 << 1);
                iArr2[9] = (i17 >>> 31) | ((iArr2[9] + ((int) (j23 >>> 32))) << 1);
                return;
            }
            i3 = i4;
            i = i6;
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
        return (int) (j5 >> 32);
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
        return (int) (j5 >> 32);
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
        return (int) (j5 >> 32);
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
        return (int) (j5 >> 32);
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
        long j5 = ((iArr2[4] & 4294967295L) - (4294967295L & iArr[4])) + (j4 >> 32);
        iArr2[4] = (int) j5;
        return (int) (j5 >> 32);
    }

    public static BigInteger toBigInteger(int[] iArr) {
        byte[] bArr = new byte[20];
        for (int i = 0; i < 5; i++) {
            int i2 = iArr[i];
            if (i2 != 0) {
                Pack.intToBigEndian(i2, bArr, (4 - i) << 2);
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
    }
}
