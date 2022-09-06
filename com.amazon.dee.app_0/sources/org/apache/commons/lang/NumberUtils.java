package org.apache.commons.lang;

import java.math.BigDecimal;
import java.math.BigInteger;
/* loaded from: classes4.dex */
public final class NumberUtils {
    public static int compare(double d, double d2) {
        if (d < d2) {
            return -1;
        }
        if (d > d2) {
            return 1;
        }
        int i = (Double.doubleToLongBits(d) > Double.doubleToLongBits(d2) ? 1 : (Double.doubleToLongBits(d) == Double.doubleToLongBits(d2) ? 0 : -1));
        if (i == 0) {
            return 0;
        }
        return i < 0 ? -1 : 1;
    }

    public static BigDecimal createBigDecimal(String str) {
        return new BigDecimal(str);
    }

    public static BigInteger createBigInteger(String str) {
        return new BigInteger(str);
    }

    public static Double createDouble(String str) {
        return Double.valueOf(str);
    }

    public static Float createFloat(String str) {
        return Float.valueOf(str);
    }

    public static Integer createInteger(String str) {
        return Integer.decode(str);
    }

    public static Long createLong(String str) {
        return Long.valueOf(str);
    }

    /* JADX WARN: Code restructure failed: missing block: B:58:0x00cd, code lost:
        if (r1 == 'l') goto L51;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.Number createNumber(java.lang.String r13) throws java.lang.NumberFormatException {
        /*
            Method dump skipped, instructions count: 423
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.lang.NumberUtils.createNumber(java.lang.String):java.lang.Number");
    }

    private static boolean isAllZeros(String str) {
        if (str == null) {
            return true;
        }
        for (int length = str.length() - 1; length >= 0; length--) {
            if (str.charAt(length) != '0') {
                return false;
            }
        }
        return str.length() > 0;
    }

    public static boolean isDigits(String str) {
        if (str == null || str.length() == 0) {
            return false;
        }
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /* JADX WARN: Code restructure failed: missing block: B:133:?, code lost:
        return false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:134:?, code lost:
        return false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:135:?, code lost:
        return false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:136:?, code lost:
        return false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:137:?, code lost:
        return false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:138:?, code lost:
        return false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x006d, code lost:
        if (r3 >= r0.length) goto L82;
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x0071, code lost:
        if (r0[r3] < '0') goto L55;
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x0075, code lost:
        if (r0[r3] > '9') goto L55;
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x0077, code lost:
        return r5;
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x007a, code lost:
        if (r0[r3] == 'e') goto L81;
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x007e, code lost:
        if (r0[r3] != 'E') goto L59;
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x0081, code lost:
        if (r6 != false) goto L69;
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x0087, code lost:
        if (r0[r3] == 'd') goto L68;
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x008d, code lost:
        if (r0[r3] == 'D') goto L68;
     */
    /* JADX WARN: Code restructure failed: missing block: B:62:0x0091, code lost:
        if (r0[r3] == 'f') goto L68;
     */
    /* JADX WARN: Code restructure failed: missing block: B:64:0x0095, code lost:
        if (r0[r3] != 'F') goto L69;
     */
    /* JADX WARN: Code restructure failed: missing block: B:65:0x0097, code lost:
        return r11;
     */
    /* JADX WARN: Code restructure failed: missing block: B:67:0x009c, code lost:
        if (r0[r3] == 'l') goto L74;
     */
    /* JADX WARN: Code restructure failed: missing block: B:69:0x00a2, code lost:
        if (r0[r3] != 'L') goto L73;
     */
    /* JADX WARN: Code restructure failed: missing block: B:71:0x00a5, code lost:
        return false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:72:0x00a6, code lost:
        if (r11 == false) goto L79;
     */
    /* JADX WARN: Code restructure failed: missing block: B:73:0x00a8, code lost:
        if (r12 != false) goto L78;
     */
    /* JADX WARN: Code restructure failed: missing block: B:75:0x00ab, code lost:
        return r5;
     */
    /* JADX WARN: Code restructure failed: missing block: B:76:0x00ac, code lost:
        if (r6 != false) goto L87;
     */
    /* JADX WARN: Code restructure failed: missing block: B:77:0x00ae, code lost:
        if (r11 == false) goto L86;
     */
    /* JADX WARN: Code restructure failed: missing block: B:79:0x00b1, code lost:
        return r5;
     */
    /* JADX WARN: Code restructure failed: missing block: B:91:0x00ce, code lost:
        return false;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static boolean isNumber(java.lang.String r16) {
        /*
            Method dump skipped, instructions count: 254
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.lang.NumberUtils.isNumber(java.lang.String):boolean");
    }

    public static int maximum(int i, int i2, int i3) {
        if (i2 > i) {
            i = i2;
        }
        return i3 > i ? i3 : i;
    }

    public static long maximum(long j, long j2, long j3) {
        if (j2 > j) {
            j = j2;
        }
        return j3 > j ? j3 : j;
    }

    public static int minimum(int i, int i2, int i3) {
        if (i2 < i) {
            i = i2;
        }
        return i3 < i ? i3 : i;
    }

    public static long minimum(long j, long j2, long j3) {
        if (j2 < j) {
            j = j2;
        }
        return j3 < j ? j3 : j;
    }

    public static int stringToInt(String str) {
        return stringToInt(str, 0);
    }

    public static int stringToInt(String str, int i) {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException unused) {
            return i;
        }
    }

    public static int compare(float f, float f2) {
        if (f < f2) {
            return -1;
        }
        if (f > f2) {
            return 1;
        }
        int floatToIntBits = Float.floatToIntBits(f);
        int floatToIntBits2 = Float.floatToIntBits(f2);
        if (floatToIntBits == floatToIntBits2) {
            return 0;
        }
        return floatToIntBits < floatToIntBits2 ? -1 : 1;
    }
}
