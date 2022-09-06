package org.threeten.bp.jdk8;

import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes5.dex */
public final class Jdk8Methods {
    private Jdk8Methods() {
    }

    public static int compareInts(int i, int i2) {
        if (i < i2) {
            return -1;
        }
        return i > i2 ? 1 : 0;
    }

    public static int compareLongs(long j, long j2) {
        int i = (j > j2 ? 1 : (j == j2 ? 0 : -1));
        if (i < 0) {
            return -1;
        }
        return i > 0 ? 1 : 0;
    }

    public static boolean equals(Object obj, Object obj2) {
        if (obj == null) {
            return obj2 == null;
        } else if (obj2 != null) {
            return obj.equals(obj2);
        } else {
            return false;
        }
    }

    public static long floorDiv(long j, long j2) {
        return j >= 0 ? j / j2 : ((j + 1) / j2) - 1;
    }

    public static long floorMod(long j, long j2) {
        return ((j % j2) + j2) % j2;
    }

    public static <T> T requireNonNull(T t) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException("Value must not be null");
    }

    public static int safeAdd(int i, int i2) {
        int i3 = i + i2;
        if ((i ^ i3) >= 0 || (i ^ i2) < 0) {
            return i3;
        }
        throw new ArithmeticException(GeneratedOutlineSupport1.outline53("Addition overflows an int: ", i, " + ", i2));
    }

    public static int safeMultiply(int i, int i2) {
        long j = i * i2;
        if (j < -2147483648L || j > 2147483647L) {
            throw new ArithmeticException(GeneratedOutlineSupport1.outline53("Multiplication overflows an int: ", i, " * ", i2));
        }
        return (int) j;
    }

    public static int safeSubtract(int i, int i2) {
        int i3 = i - i2;
        if ((i ^ i3) >= 0 || (i ^ i2) >= 0) {
            return i3;
        }
        throw new ArithmeticException(GeneratedOutlineSupport1.outline53("Subtraction overflows an int: ", i, " - ", i2));
    }

    public static int safeToInt(long j) {
        if (j > 2147483647L || j < -2147483648L) {
            throw new ArithmeticException(GeneratedOutlineSupport1.outline56("Calculation overflows an int: ", j));
        }
        return (int) j;
    }

    public static int floorDiv(int i, int i2) {
        return i >= 0 ? i / i2 : ((i + 1) / i2) - 1;
    }

    public static int floorMod(long j, int i) {
        long j2 = i;
        return (int) (((j % j2) + j2) % j2);
    }

    public static <T> T requireNonNull(T t, String str) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(GeneratedOutlineSupport1.outline72(str, " must not be null"));
    }

    public static long safeAdd(long j, long j2) {
        long j3 = j + j2;
        if ((j ^ j3) >= 0 || (j ^ j2) < 0) {
            return j3;
        }
        StringBuilder outline111 = GeneratedOutlineSupport1.outline111("Addition overflows a long: ", j, " + ");
        outline111.append(j2);
        throw new ArithmeticException(outline111.toString());
    }

    public static long safeMultiply(long j, int i) {
        if (i == -1) {
            if (j != Long.MIN_VALUE) {
                return -j;
            }
            throw new ArithmeticException("Multiplication overflows a long: " + j + " * " + i);
        } else if (i == 0) {
            return 0L;
        } else {
            if (i == 1) {
                return j;
            }
            long j2 = i;
            long j3 = j * j2;
            if (j3 / j2 == j) {
                return j3;
            }
            throw new ArithmeticException("Multiplication overflows a long: " + j + " * " + i);
        }
    }

    public static long safeSubtract(long j, long j2) {
        long j3 = j - j2;
        if ((j ^ j3) >= 0 || (j ^ j2) >= 0) {
            return j3;
        }
        StringBuilder outline111 = GeneratedOutlineSupport1.outline111("Subtraction overflows a long: ", j, " - ");
        outline111.append(j2);
        throw new ArithmeticException(outline111.toString());
    }

    public static int floorMod(int i, int i2) {
        return ((i % i2) + i2) % i2;
    }

    public static long safeMultiply(long j, long j2) {
        if (j2 == 1) {
            return j;
        }
        if (j == 1) {
            return j2;
        }
        if (j == 0 || j2 == 0) {
            return 0L;
        }
        long j3 = j * j2;
        if (j3 / j2 == j && ((j != Long.MIN_VALUE || j2 != -1) && (j2 != Long.MIN_VALUE || j != -1))) {
            return j3;
        }
        StringBuilder outline111 = GeneratedOutlineSupport1.outline111("Multiplication overflows a long: ", j, " * ");
        outline111.append(j2);
        throw new ArithmeticException(outline111.toString());
    }
}
