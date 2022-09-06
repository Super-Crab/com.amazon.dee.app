package org.bouncycastle.math.raw;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Random;
import org.bouncycastle.util.Integers;
/* loaded from: classes5.dex */
public abstract class Mod {
    private static final int M30 = 1073741823;
    private static final long M32L = 4294967295L;

    public static void add(int[] iArr, int[] iArr2, int[] iArr3, int[] iArr4) {
        int length = iArr.length;
        if (Nat.add(length, iArr2, iArr3, iArr4) != 0) {
            Nat.subFrom(length, iArr, iArr4);
        }
    }

    private static int add30(int i, int[] iArr, int[] iArr2) {
        int i2 = i - 1;
        int i3 = 0;
        for (int i4 = 0; i4 < i2; i4++) {
            int i5 = iArr[i4] + iArr2[i4] + i3;
            iArr[i4] = 1073741823 & i5;
            i3 = i5 >> 30;
        }
        int i6 = iArr[i2] + iArr2[i2] + i3;
        iArr[i2] = i6;
        return i6 >> 30;
    }

    private static int cadd30(int i, int i2, int[] iArr, int[] iArr2) {
        int i3 = i - 1;
        int i4 = 0;
        for (int i5 = 0; i5 < i3; i5++) {
            int i6 = iArr[i5] + (iArr2[i5] & i2) + i4;
            iArr[i5] = 1073741823 & i6;
            i4 = i6 >> 30;
        }
        int i7 = iArr[i3] + (i2 & iArr2[i3]) + i4;
        iArr[i3] = i7;
        return i7 >> 30;
    }

    public static void checkedModOddInverse(int[] iArr, int[] iArr2, int[] iArr3) {
        if (modOddInverse(iArr, iArr2, iArr3) != 0) {
            return;
        }
        throw new ArithmeticException("Inverse does not exist.");
    }

    public static void checkedModOddInverseVar(int[] iArr, int[] iArr2, int[] iArr3) {
        if (modOddInverseVar(iArr, iArr2, iArr3)) {
            return;
        }
        throw new ArithmeticException("Inverse does not exist.");
    }

    private static int cnegate30(int i, int i2, int[] iArr) {
        int i3 = i - 1;
        int i4 = 0;
        for (int i5 = 0; i5 < i3; i5++) {
            int i6 = ((iArr[i5] ^ i2) - i2) + i4;
            iArr[i5] = 1073741823 & i6;
            i4 = i6 >> 30;
        }
        int i7 = ((iArr[i3] ^ i2) - i2) + i4;
        iArr[i3] = i7;
        return i7 >> 30;
    }

    private static int csub30(int i, int i2, int[] iArr, int[] iArr2) {
        int i3 = i - 1;
        int i4 = 0;
        for (int i5 = 0; i5 < i3; i5++) {
            int i6 = (iArr[i5] - (iArr2[i5] & i2)) + i4;
            iArr[i5] = 1073741823 & i6;
            i4 = i6 >> 30;
        }
        int i7 = (iArr[i3] - (i2 & iArr2[i3])) + i4;
        iArr[i3] = i7;
        return i7 >> 30;
    }

    private static void decode30(int i, int[] iArr, int i2, int[] iArr2, int i3) {
        int i4 = 0;
        long j = 0;
        while (i > 0) {
            while (i4 < Math.min(32, i)) {
                j |= iArr[i2] << i4;
                i4 += 30;
                i2++;
            }
            iArr2[i3] = (int) j;
            j >>>= 32;
            i4 -= 32;
            i -= 32;
            i3++;
        }
    }

    private static int divsteps30(int i, int i2, int i3, int[] iArr) {
        int i4 = i;
        int i5 = i2;
        int i6 = i3;
        int i7 = 0;
        int i8 = 0;
        int i9 = 1;
        int i10 = 1;
        for (int i11 = 0; i11 < 30; i11++) {
            int i12 = i4 >> 31;
            int i13 = -(i6 & 1);
            int i14 = i6 + (((i5 ^ i12) - i12) & i13);
            i8 += ((i9 ^ i12) - i12) & i13;
            i10 += ((i7 ^ i12) - i12) & i13;
            int i15 = i12 & i13;
            i4 = (i4 ^ i15) - (i15 + 1);
            i5 += i14 & i15;
            i6 = i14 >> 1;
            i9 = (i9 + (i8 & i15)) << 1;
            i7 = (i7 + (i15 & i10)) << 1;
        }
        iArr[0] = i9;
        iArr[1] = i7;
        iArr[2] = i8;
        iArr[3] = i10;
        return i4;
    }

    private static int divsteps30Var(int i, int i2, int i3, int[] iArr) {
        int i4;
        int i5;
        int i6;
        int i7 = i;
        int i8 = i2;
        int i9 = 0;
        int i10 = 0;
        int i11 = 1;
        int i12 = 1;
        int i13 = 30;
        int i14 = i3;
        while (true) {
            int numberOfTrailingZeros = Integers.numberOfTrailingZeros(((-1) << i13) | i14);
            int i15 = i14 >> numberOfTrailingZeros;
            int i16 = i11 << numberOfTrailingZeros;
            int i17 = i9 << numberOfTrailingZeros;
            i7 -= numberOfTrailingZeros;
            i13 -= numberOfTrailingZeros;
            if (i13 <= 0) {
                iArr[0] = i16;
                iArr[1] = i17;
                iArr[2] = i10;
                iArr[3] = i12;
                return i7;
            }
            if (i7 < 0) {
                i7 = -i7;
                int i18 = -i8;
                i5 = -i16;
                i6 = -i17;
                int i19 = i7 + 1;
                if (i19 > i13) {
                    i19 = i13;
                }
                i4 = ((-1) >>> (32 - i19)) & 63 & (((i15 * i15) - 2) * i15 * i18);
                i8 = i15;
                i15 = i18;
            } else {
                int i20 = i7 + 1;
                if (i20 > i13) {
                    i20 = i13;
                }
                i4 = ((-1) >>> (32 - i20)) & 15 & ((-((((i8 + 1) & 4) << 1) + i8)) * i15);
                int i21 = i10;
                i10 = i16;
                i5 = i21;
                int i22 = i12;
                i12 = i17;
                i6 = i22;
            }
            i14 = i15 + (i8 * i4);
            int i23 = i10;
            i10 = i5 + (i10 * i4);
            i11 = i23;
            int i24 = i12;
            i12 = i6 + (i4 * i12);
            i9 = i24;
        }
    }

    private static void encode30(int i, int[] iArr, int i2, int[] iArr2, int i3) {
        int i4 = 0;
        long j = 0;
        while (i > 0) {
            if (i4 < Math.min(30, i)) {
                j |= (iArr[i2] & 4294967295L) << i4;
                i4 += 32;
                i2++;
            }
            iArr2[i3] = ((int) j) & 1073741823;
            j >>>= 30;
            i4 -= 30;
            i -= 30;
            i3++;
        }
    }

    private static int getMaximumDivsteps(int i) {
        return ((i * 49) + (i < 46 ? 80 : 47)) / 17;
    }

    public static int inverse32(int i) {
        int i2 = (2 - (i * i)) * i;
        int i3 = (2 - (i * i2)) * i2;
        int i4 = (2 - (i * i3)) * i3;
        return (2 - (i * i4)) * i4;
    }

    public static void invert(int[] iArr, int[] iArr2, int[] iArr3) {
        checkedModOddInverseVar(iArr, iArr2, iArr3);
    }

    public static int modOddInverse(int[] iArr, int[] iArr2, int[] iArr3) {
        int length = iArr.length;
        int numberOfLeadingZeros = (length << 5) - Integers.numberOfLeadingZeros(iArr[length - 1]);
        int i = (numberOfLeadingZeros + 29) / 30;
        int i2 = (-inverse32(iArr[0])) << 2;
        int[] iArr4 = new int[4];
        int[] iArr5 = new int[i];
        int[] iArr6 = new int[i];
        int[] iArr7 = new int[i];
        int[] iArr8 = new int[i];
        int[] iArr9 = new int[i];
        iArr6[0] = 1;
        encode30(numberOfLeadingZeros, iArr2, 0, iArr8, 0);
        encode30(numberOfLeadingZeros, iArr, 0, iArr9, 0);
        System.arraycopy(iArr9, 0, iArr7, 0, i);
        int maximumDivsteps = getMaximumDivsteps(numberOfLeadingZeros);
        int i3 = -1;
        int i4 = 0;
        while (i4 < maximumDivsteps) {
            int divsteps30 = divsteps30(i3, iArr7[0], iArr8[0], iArr4);
            int[] iArr10 = iArr9;
            int[] iArr11 = iArr8;
            updateDE30(i, iArr5, iArr6, iArr4, i2, iArr10);
            updateFG30(i, iArr7, iArr11, iArr4);
            i4 += 30;
            iArr9 = iArr10;
            iArr8 = iArr11;
            i3 = divsteps30;
        }
        int[] iArr12 = iArr9;
        int i5 = iArr7[i - 1] >> 31;
        cnegate30(i, i5, iArr7);
        cadd30(i, cadd30(i, csub30(i, ~cnegate30(i, i5, iArr5), iArr5, iArr12), iArr5, iArr12), iArr5, iArr12);
        decode30(numberOfLeadingZeros, iArr5, 0, iArr3, 0);
        return Nat.equalTo(i, iArr7, 1) & Nat.equalToZero(i, iArr8);
    }

    /* JADX WARN: Type inference failed for: r10v0 */
    /* JADX WARN: Type inference failed for: r10v1, types: [boolean] */
    /* JADX WARN: Type inference failed for: r10v4 */
    public static boolean modOddInverseVar(int[] iArr, int[] iArr2, int[] iArr3) {
        int[] iArr4;
        int sub30;
        int length = iArr.length;
        int numberOfLeadingZeros = (length << 5) - Integers.numberOfLeadingZeros(iArr[length - 1]);
        int i = (numberOfLeadingZeros + 29) / 30;
        ?? r10 = 0;
        int i2 = (-inverse32(iArr[0])) << 2;
        int[] iArr5 = new int[4];
        int[] iArr6 = new int[i];
        int[] iArr7 = new int[i];
        int[] iArr8 = new int[i];
        int[] iArr9 = new int[i];
        int[] iArr10 = new int[i];
        iArr7[0] = 1;
        encode30(numberOfLeadingZeros, iArr2, 0, iArr9, 0);
        encode30(numberOfLeadingZeros, iArr, 0, iArr10, 0);
        System.arraycopy(iArr10, 0, iArr8, 0, i);
        int i3 = i - 1;
        int maximumDivsteps = getMaximumDivsteps(numberOfLeadingZeros);
        int i4 = i;
        int numberOfLeadingZeros2 = (-1) - (Integers.numberOfLeadingZeros(iArr9[i3] | 1) - (((i * 30) + 2) - numberOfLeadingZeros));
        int i5 = 0;
        while (!Nat.isZero(i4, iArr9)) {
            if (i5 >= maximumDivsteps) {
                return r10;
            }
            int i6 = i5 + 30;
            char c = r10 == true ? 1 : 0;
            char c2 = r10 == true ? 1 : 0;
            int divsteps30Var = divsteps30Var(numberOfLeadingZeros2, iArr8[c], iArr9[r10], iArr5);
            int i7 = i4;
            int i8 = maximumDivsteps;
            int[] iArr11 = iArr10;
            int[] iArr12 = iArr9;
            updateDE30(i, iArr6, iArr7, iArr5, i2, iArr11);
            updateFG30(i7, iArr8, iArr12, iArr5);
            int i9 = i7 - 1;
            int i10 = iArr8[i9];
            int i11 = iArr12[i9];
            int i12 = i7 - 2;
            if (((i12 >> 31) | ((i10 >> 31) ^ i10) | ((i11 >> 31) ^ i11)) == 0) {
                iArr8[i12] = (i10 << 30) | iArr8[i12];
                iArr12[i12] = iArr12[i12] | (i11 << 30);
                i4 = i7 - 1;
            } else {
                i4 = i7;
            }
            iArr10 = iArr11;
            numberOfLeadingZeros2 = divsteps30Var;
            iArr9 = iArr12;
            i5 = i6;
            maximumDivsteps = i8;
            r10 = 0;
        }
        int i13 = i4;
        int[] iArr13 = iArr10;
        if ((iArr8[i13 - 1] >> 31) != 0) {
            negate30(i13, iArr8);
            negate30(i, iArr6);
        }
        if (!Nat.isOne(i13, iArr8)) {
            return false;
        }
        if ((iArr6[i3] >> 31) < 0) {
            iArr4 = iArr13;
            sub30 = add30(i, iArr6, iArr4);
        } else {
            iArr4 = iArr13;
            sub30 = sub30(i, iArr6, iArr4);
        }
        if (sub30 < 0) {
            add30(i, iArr6, iArr4);
        }
        decode30(numberOfLeadingZeros, iArr6, 0, iArr3, 0);
        return true;
    }

    private static int negate30(int i, int[] iArr) {
        int i2 = i - 1;
        int i3 = 0;
        for (int i4 = 0; i4 < i2; i4++) {
            int i5 = i3 - iArr[i4];
            iArr[i4] = 1073741823 & i5;
            i3 = i5 >> 30;
        }
        int i6 = i3 - iArr[i2];
        iArr[i2] = i6;
        return i6 >> 30;
    }

    public static int[] random(int[] iArr) {
        int length = iArr.length;
        Random random = new Random();
        int[] create = Nat.create(length);
        int i = length - 1;
        int i2 = iArr[i];
        int i3 = i2 | (i2 >>> 1);
        int i4 = i3 | (i3 >>> 2);
        int i5 = i4 | (i4 >>> 4);
        int i6 = i5 | (i5 >>> 8);
        int i7 = i6 | (i6 >>> 16);
        do {
            for (int i8 = 0; i8 != length; i8++) {
                create[i8] = random.nextInt();
            }
            create[i] = create[i] & i7;
        } while (Nat.gte(length, create, iArr));
        return create;
    }

    private static int sub30(int i, int[] iArr, int[] iArr2) {
        int i2 = i - 1;
        int i3 = 0;
        for (int i4 = 0; i4 < i2; i4++) {
            int i5 = (iArr[i4] - iArr2[i4]) + i3;
            iArr[i4] = 1073741823 & i5;
            i3 = i5 >> 30;
        }
        int i6 = (iArr[i2] - iArr2[i2]) + i3;
        iArr[i2] = i6;
        return i6 >> 30;
    }

    public static void subtract(int[] iArr, int[] iArr2, int[] iArr3, int[] iArr4) {
        int length = iArr.length;
        if (Nat.sub(length, iArr2, iArr3, iArr4) != 0) {
            Nat.addTo(length, iArr, iArr4);
        }
    }

    private static void updateDE30(int i, int[] iArr, int[] iArr2, int[] iArr3, int i2, int[] iArr4) {
        int i3 = iArr3[0];
        int i4 = iArr3[1];
        int i5 = iArr3[2];
        int i6 = iArr3[3];
        long j = i3;
        long j2 = iArr[0];
        long j3 = i4;
        long j4 = iArr2[0];
        long j5 = (j * j2) + (j3 * j4);
        long j6 = i5;
        long j7 = i6;
        long j8 = (j4 * j7) + (j2 * j6);
        long j9 = (((int) j5) * i2) >> 2;
        long j10 = (((int) j8) * i2) >> 2;
        long j11 = ((iArr4[0] * j9) + j5) >> 30;
        long j12 = ((iArr4[0] * j10) + j8) >> 30;
        int i7 = 1;
        while (i7 < i) {
            long j13 = j10;
            long j14 = iArr[i7];
            long j15 = iArr2[i7];
            long outline9 = GeneratedOutlineSupport1.outline9(j3, j15, j * j14, j11);
            long outline92 = GeneratedOutlineSupport1.outline9(j15, j7, j6 * j14, j12);
            long j16 = (iArr4[i7] * j9) + outline9;
            long j17 = (iArr4[i7] * j13) + outline92;
            int i8 = i7 - 1;
            iArr[i8] = ((int) j16) & 1073741823;
            iArr2[i8] = 1073741823 & ((int) j17);
            long j18 = j17 >> 30;
            i7++;
            j11 = j16 >> 30;
            j12 = j18;
            j10 = j13;
        }
        int i9 = i - 1;
        iArr[i9] = (int) j11;
        iArr2[i9] = (int) j12;
    }

    private static void updateFG30(int i, int[] iArr, int[] iArr2, int[] iArr3) {
        int i2 = i;
        int i3 = iArr3[0];
        int i4 = 1;
        int i5 = iArr3[1];
        int i6 = iArr3[2];
        int i7 = iArr3[3];
        long j = i3;
        long j2 = iArr[0];
        long j3 = i5;
        long j4 = iArr2[0];
        long j5 = i6;
        long j6 = i7;
        long j7 = ((j3 * j4) + (j * j2)) >> 30;
        long j8 = ((j4 * j6) + (j2 * j5)) >> 30;
        int i8 = 1;
        while (i4 < i2) {
            long j9 = iArr[i4];
            long j10 = j * j9;
            long j11 = j;
            long j12 = iArr2[i4];
            long outline9 = GeneratedOutlineSupport1.outline9(j3, j12, j10, j7);
            long outline92 = GeneratedOutlineSupport1.outline9(j12, j6, j5 * j9, j8);
            int i9 = i4 - 1;
            iArr[i9] = ((int) outline9) & 1073741823;
            j7 = outline9 >> 30;
            iArr2[i9] = 1073741823 & ((int) outline92);
            j8 = outline92 >> 30;
            i4++;
            i8 = 1;
            i2 = i;
            j = j11;
        }
        int i10 = i - i8;
        iArr[i10] = (int) j7;
        iArr2[i10] = (int) j8;
    }
}
