package com.amazon.alexa.device.setup.echo.bouncycastle.util;

import java.math.BigInteger;
/* loaded from: classes.dex */
public final class Arrays {
    private Arrays() {
    }

    public static boolean areEqual(boolean[] zArr, boolean[] zArr2) {
        if (zArr == zArr2) {
            return true;
        }
        if (zArr == null || zArr2 == null || zArr.length != zArr2.length) {
            return false;
        }
        for (int i = 0; i != zArr.length; i++) {
            if (zArr[i] != zArr2[i]) {
                return false;
            }
        }
        return true;
    }

    public static byte[] clone(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        byte[] bArr2 = new byte[bArr.length];
        System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
        return bArr2;
    }

    public static boolean constantTimeAreEqual(byte[] bArr, byte[] bArr2) {
        if (bArr == bArr2) {
            return true;
        }
        if (bArr == null || bArr2 == null || bArr.length != bArr2.length) {
            return false;
        }
        int i = 0;
        for (int i2 = 0; i2 != bArr.length; i2++) {
            i |= bArr[i2] ^ bArr2[i2];
        }
        return i == 0;
    }

    public static byte[] copyOf(byte[] bArr, int i) {
        byte[] bArr2 = new byte[i];
        if (i < bArr.length) {
            System.arraycopy(bArr, 0, bArr2, 0, i);
        } else {
            System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
        }
        return bArr2;
    }

    public static byte[] copyOfRange(byte[] bArr, int i, int i2) {
        int length = getLength(i, i2);
        byte[] bArr2 = new byte[length];
        if (bArr.length - i < length) {
            System.arraycopy(bArr, i, bArr2, 0, bArr.length - i);
        } else {
            System.arraycopy(bArr, i, bArr2, 0, length);
        }
        return bArr2;
    }

    public static void fill(byte[] bArr, byte b) {
        for (int i = 0; i < bArr.length; i++) {
            bArr[i] = b;
        }
    }

    private static int getLength(int i, int i2) {
        int i3 = i2 - i;
        if (i3 >= 0) {
            return i3;
        }
        throw new IllegalArgumentException(i + " > " + i2);
    }

    public static int hashCode(byte[] bArr) {
        if (bArr == null) {
            return 0;
        }
        int length = bArr.length;
        int i = length + 1;
        while (true) {
            length--;
            if (length < 0) {
                return i;
            }
            i = (i * 257) ^ bArr[length];
        }
    }

    public static int[] clone(int[] iArr) {
        if (iArr == null) {
            return null;
        }
        int[] iArr2 = new int[iArr.length];
        System.arraycopy(iArr, 0, iArr2, 0, iArr.length);
        return iArr2;
    }

    public static void fill(long[] jArr, long j) {
        for (int i = 0; i < jArr.length; i++) {
            jArr[i] = j;
        }
    }

    public static int hashCode(char[] cArr) {
        if (cArr == null) {
            return 0;
        }
        int length = cArr.length;
        int i = length + 1;
        while (true) {
            length--;
            if (length < 0) {
                return i;
            }
            i = (i * 257) ^ cArr[length];
        }
    }

    public static boolean areEqual(char[] cArr, char[] cArr2) {
        if (cArr == cArr2) {
            return true;
        }
        if (cArr == null || cArr2 == null || cArr.length != cArr2.length) {
            return false;
        }
        for (int i = 0; i != cArr.length; i++) {
            if (cArr[i] != cArr2[i]) {
                return false;
            }
        }
        return true;
    }

    public static BigInteger[] clone(BigInteger[] bigIntegerArr) {
        if (bigIntegerArr == null) {
            return null;
        }
        BigInteger[] bigIntegerArr2 = new BigInteger[bigIntegerArr.length];
        System.arraycopy(bigIntegerArr, 0, bigIntegerArr2, 0, bigIntegerArr.length);
        return bigIntegerArr2;
    }

    public static int[] copyOf(int[] iArr, int i) {
        int[] iArr2 = new int[i];
        if (i < iArr.length) {
            System.arraycopy(iArr, 0, iArr2, 0, i);
        } else {
            System.arraycopy(iArr, 0, iArr2, 0, iArr.length);
        }
        return iArr2;
    }

    public static void fill(short[] sArr, short s) {
        for (int i = 0; i < sArr.length; i++) {
            sArr[i] = s;
        }
    }

    public static int hashCode(int[] iArr) {
        if (iArr == null) {
            return 0;
        }
        int length = iArr.length;
        int i = length + 1;
        while (true) {
            length--;
            if (length < 0) {
                return i;
            }
            i = (i * 257) ^ iArr[length];
        }
    }

    public static int[] copyOfRange(int[] iArr, int i, int i2) {
        int length = getLength(i, i2);
        int[] iArr2 = new int[length];
        if (iArr.length - i < length) {
            System.arraycopy(iArr, i, iArr2, 0, iArr.length - i);
        } else {
            System.arraycopy(iArr, i, iArr2, 0, length);
        }
        return iArr2;
    }

    public static boolean areEqual(byte[] bArr, byte[] bArr2) {
        if (bArr == bArr2) {
            return true;
        }
        if (bArr == null || bArr2 == null || bArr.length != bArr2.length) {
            return false;
        }
        for (int i = 0; i != bArr.length; i++) {
            if (bArr[i] != bArr2[i]) {
                return false;
            }
        }
        return true;
    }

    public static void fill(int[] iArr, int i) {
        for (int i2 = 0; i2 < iArr.length; i2++) {
            iArr[i2] = i;
        }
    }

    public static int hashCode(BigInteger[] bigIntegerArr) {
        if (bigIntegerArr == null) {
            return 0;
        }
        int length = bigIntegerArr.length;
        int i = length + 1;
        while (true) {
            length--;
            if (length < 0) {
                return i;
            }
            i = (i * 257) ^ bigIntegerArr[length].hashCode();
        }
    }

    public static long[] copyOf(long[] jArr, int i) {
        long[] jArr2 = new long[i];
        if (i < jArr.length) {
            System.arraycopy(jArr, 0, jArr2, 0, i);
        } else {
            System.arraycopy(jArr, 0, jArr2, 0, jArr.length);
        }
        return jArr2;
    }

    public static boolean areEqual(int[] iArr, int[] iArr2) {
        if (iArr == iArr2) {
            return true;
        }
        if (iArr == null || iArr2 == null || iArr.length != iArr2.length) {
            return false;
        }
        for (int i = 0; i != iArr.length; i++) {
            if (iArr[i] != iArr2[i]) {
                return false;
            }
        }
        return true;
    }

    public static long[] copyOfRange(long[] jArr, int i, int i2) {
        int length = getLength(i, i2);
        long[] jArr2 = new long[length];
        if (jArr.length - i < length) {
            System.arraycopy(jArr, i, jArr2, 0, jArr.length - i);
        } else {
            System.arraycopy(jArr, i, jArr2, 0, length);
        }
        return jArr2;
    }

    public static boolean areEqual(long[] jArr, long[] jArr2) {
        if (jArr == jArr2) {
            return true;
        }
        if (jArr == null || jArr2 == null || jArr.length != jArr2.length) {
            return false;
        }
        for (int i = 0; i != jArr.length; i++) {
            if (jArr[i] != jArr2[i]) {
                return false;
            }
        }
        return true;
    }

    public static BigInteger[] copyOf(BigInteger[] bigIntegerArr, int i) {
        BigInteger[] bigIntegerArr2 = new BigInteger[i];
        if (i < bigIntegerArr.length) {
            System.arraycopy(bigIntegerArr, 0, bigIntegerArr2, 0, i);
        } else {
            System.arraycopy(bigIntegerArr, 0, bigIntegerArr2, 0, bigIntegerArr.length);
        }
        return bigIntegerArr2;
    }

    public static boolean areEqual(BigInteger[] bigIntegerArr, BigInteger[] bigIntegerArr2) {
        if (bigIntegerArr == bigIntegerArr2) {
            return true;
        }
        if (bigIntegerArr == null || bigIntegerArr2 == null || bigIntegerArr.length != bigIntegerArr2.length) {
            return false;
        }
        for (int i = 0; i != bigIntegerArr.length; i++) {
            if (!bigIntegerArr[i].equals(bigIntegerArr2[i])) {
                return false;
            }
        }
        return true;
    }

    public static BigInteger[] copyOfRange(BigInteger[] bigIntegerArr, int i, int i2) {
        int length = getLength(i, i2);
        BigInteger[] bigIntegerArr2 = new BigInteger[length];
        if (bigIntegerArr.length - i < length) {
            System.arraycopy(bigIntegerArr, i, bigIntegerArr2, 0, bigIntegerArr.length - i);
        } else {
            System.arraycopy(bigIntegerArr, i, bigIntegerArr2, 0, length);
        }
        return bigIntegerArr2;
    }
}
