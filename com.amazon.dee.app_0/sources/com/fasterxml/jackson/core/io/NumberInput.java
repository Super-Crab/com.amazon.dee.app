package com.fasterxml.jackson.core.io;

import com.amazon.alexa.comms.mediaInsights.service.configuration.TracePublisherServiceConfigImpl;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.math.BigDecimal;
/* loaded from: classes2.dex */
public final class NumberInput {
    static final long L_BILLION = 1000000000;
    public static final String NASTY_SMALL_DOUBLE = "2.2250738585072012e-308";
    static final String MIN_LONG_STR_NO_SIGN = String.valueOf(Long.MIN_VALUE).substring(1);
    static final String MAX_LONG_STR = String.valueOf(Long.MAX_VALUE);

    private static NumberFormatException _badBD(String str) {
        return new NumberFormatException(GeneratedOutlineSupport1.outline75("Value \"", str, "\" can not be represented as BigDecimal"));
    }

    public static boolean inLongRange(char[] cArr, int i, int i2, boolean z) {
        String str = z ? MIN_LONG_STR_NO_SIGN : MAX_LONG_STR;
        int length = str.length();
        if (i2 < length) {
            return true;
        }
        if (i2 > length) {
            return false;
        }
        for (int i3 = 0; i3 < length; i3++) {
            int charAt = cArr[i + i3] - str.charAt(i3);
            if (charAt != 0) {
                return charAt < 0;
            }
        }
        return true;
    }

    public static double parseAsDouble(String str, double d) {
        if (str == null) {
            return d;
        }
        String trim = str.trim();
        if (trim.length() == 0) {
            return d;
        }
        try {
            return parseDouble(trim);
        } catch (NumberFormatException unused) {
            return d;
        }
    }

    public static int parseAsInt(String str, int i) {
        String trim;
        int length;
        if (str == null || (length = (trim = str.trim()).length()) == 0) {
            return i;
        }
        int i2 = 0;
        char charAt = trim.charAt(0);
        if (charAt == '+') {
            trim = trim.substring(1);
            length = trim.length();
        } else if (charAt == '-') {
            i2 = 1;
        }
        while (i2 < length) {
            char charAt2 = trim.charAt(i2);
            if (charAt2 > '9' || charAt2 < '0') {
                try {
                    return (int) parseDouble(trim);
                } catch (NumberFormatException unused) {
                    return i;
                }
            }
            i2++;
        }
        try {
            return Integer.parseInt(trim);
        } catch (NumberFormatException unused2) {
            return i;
        }
    }

    public static long parseAsLong(String str, long j) {
        String trim;
        int length;
        if (str == null || (length = (trim = str.trim()).length()) == 0) {
            return j;
        }
        int i = 0;
        char charAt = trim.charAt(0);
        if (charAt == '+') {
            trim = trim.substring(1);
            length = trim.length();
        } else if (charAt == '-') {
            i = 1;
        }
        while (i < length) {
            char charAt2 = trim.charAt(i);
            if (charAt2 > '9' || charAt2 < '0') {
                try {
                    return (long) parseDouble(trim);
                } catch (NumberFormatException unused) {
                    return j;
                }
            }
            i++;
        }
        try {
            return Long.parseLong(trim);
        } catch (NumberFormatException unused2) {
            return j;
        }
    }

    public static BigDecimal parseBigDecimal(String str) throws NumberFormatException {
        try {
            return new BigDecimal(str);
        } catch (NumberFormatException unused) {
            throw _badBD(str);
        }
    }

    public static double parseDouble(String str) throws NumberFormatException {
        if ("2.2250738585072012e-308".equals(str)) {
            return Double.MIN_VALUE;
        }
        return Double.parseDouble(str);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public static int parseInt(char[] cArr, int i, int i2) {
        int i3 = cArr[(i + i2) - 1] - '0';
        switch (i2) {
            case 2:
                break;
            case 3:
                i3 = GeneratedOutlineSupport1.outline2(cArr[i], -48, 100, i3);
                i++;
                break;
            case 4:
                i3 = GeneratedOutlineSupport1.outline2(cArr[i], -48, 1000, i3);
                i++;
                i3 = GeneratedOutlineSupport1.outline2(cArr[i], -48, 100, i3);
                i++;
                break;
            case 5:
                i3 = GeneratedOutlineSupport1.outline2(cArr[i], -48, 10000, i3);
                i++;
                i3 = GeneratedOutlineSupport1.outline2(cArr[i], -48, 1000, i3);
                i++;
                i3 = GeneratedOutlineSupport1.outline2(cArr[i], -48, 100, i3);
                i++;
                break;
            case 6:
                i3 = GeneratedOutlineSupport1.outline2(cArr[i], -48, 100000, i3);
                i++;
                i3 = GeneratedOutlineSupport1.outline2(cArr[i], -48, 10000, i3);
                i++;
                i3 = GeneratedOutlineSupport1.outline2(cArr[i], -48, 1000, i3);
                i++;
                i3 = GeneratedOutlineSupport1.outline2(cArr[i], -48, 100, i3);
                i++;
                break;
            case 7:
                i3 = GeneratedOutlineSupport1.outline2(cArr[i], -48, TracePublisherServiceConfigImpl.TOTAL_TRACES_CAPACITY_IN_BYTES, i3);
                i++;
                i3 = GeneratedOutlineSupport1.outline2(cArr[i], -48, 100000, i3);
                i++;
                i3 = GeneratedOutlineSupport1.outline2(cArr[i], -48, 10000, i3);
                i++;
                i3 = GeneratedOutlineSupport1.outline2(cArr[i], -48, 1000, i3);
                i++;
                i3 = GeneratedOutlineSupport1.outline2(cArr[i], -48, 100, i3);
                i++;
                break;
            case 8:
                i3 = GeneratedOutlineSupport1.outline2(cArr[i], -48, 10000000, i3);
                i++;
                i3 = GeneratedOutlineSupport1.outline2(cArr[i], -48, TracePublisherServiceConfigImpl.TOTAL_TRACES_CAPACITY_IN_BYTES, i3);
                i++;
                i3 = GeneratedOutlineSupport1.outline2(cArr[i], -48, 100000, i3);
                i++;
                i3 = GeneratedOutlineSupport1.outline2(cArr[i], -48, 10000, i3);
                i++;
                i3 = GeneratedOutlineSupport1.outline2(cArr[i], -48, 1000, i3);
                i++;
                i3 = GeneratedOutlineSupport1.outline2(cArr[i], -48, 100, i3);
                i++;
                break;
            case 9:
                i3 = GeneratedOutlineSupport1.outline2(cArr[i], -48, 100000000, i3);
                i++;
                i3 = GeneratedOutlineSupport1.outline2(cArr[i], -48, 10000000, i3);
                i++;
                i3 = GeneratedOutlineSupport1.outline2(cArr[i], -48, TracePublisherServiceConfigImpl.TOTAL_TRACES_CAPACITY_IN_BYTES, i3);
                i++;
                i3 = GeneratedOutlineSupport1.outline2(cArr[i], -48, 100000, i3);
                i++;
                i3 = GeneratedOutlineSupport1.outline2(cArr[i], -48, 10000, i3);
                i++;
                i3 = GeneratedOutlineSupport1.outline2(cArr[i], -48, 1000, i3);
                i++;
                i3 = GeneratedOutlineSupport1.outline2(cArr[i], -48, 100, i3);
                i++;
                break;
            default:
                return i3;
        }
        return GeneratedOutlineSupport1.outline2(cArr[i], -48, 10, i3);
    }

    public static long parseLong(char[] cArr, int i, int i2) {
        int i3 = i2 - 9;
        return (parseInt(cArr, i, i3) * 1000000000) + parseInt(cArr, i + i3, 9);
    }

    public static BigDecimal parseBigDecimal(char[] cArr) throws NumberFormatException {
        return parseBigDecimal(cArr, 0, cArr.length);
    }

    public static long parseLong(String str) {
        if (str.length() <= 9) {
            return parseInt(str);
        }
        return Long.parseLong(str);
    }

    public static boolean inLongRange(String str, boolean z) {
        String str2 = z ? MIN_LONG_STR_NO_SIGN : MAX_LONG_STR;
        int length = str2.length();
        int length2 = str.length();
        if (length2 < length) {
            return true;
        }
        if (length2 > length) {
            return false;
        }
        for (int i = 0; i < length; i++) {
            int charAt = str.charAt(i) - str2.charAt(i);
            if (charAt != 0) {
                return charAt < 0;
            }
        }
        return true;
    }

    public static BigDecimal parseBigDecimal(char[] cArr, int i, int i2) throws NumberFormatException {
        try {
            return new BigDecimal(cArr, i, i2);
        } catch (NumberFormatException unused) {
            throw _badBD(new String(cArr, i, i2));
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:45:0x0075, code lost:
        return java.lang.Integer.parseInt(r9);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static int parseInt(java.lang.String r9) {
        /*
            r0 = 0
            char r1 = r9.charAt(r0)
            int r2 = r9.length()
            r3 = 1
            r4 = 45
            if (r1 != r4) goto Lf
            r0 = r3
        Lf:
            r4 = 10
            if (r0 == 0) goto L26
            if (r2 == r3) goto L21
            if (r2 <= r4) goto L18
            goto L21
        L18:
            r1 = 2
            char r3 = r9.charAt(r3)
            r8 = r3
            r3 = r1
            r1 = r8
            goto L2f
        L21:
            int r9 = java.lang.Integer.parseInt(r9)
            return r9
        L26:
            r5 = 9
            if (r2 <= r5) goto L2f
            int r9 = java.lang.Integer.parseInt(r9)
            return r9
        L2f:
            r5 = 57
            if (r1 > r5) goto L84
            r6 = 48
            if (r1 >= r6) goto L38
            goto L84
        L38:
            int r1 = r1 - r6
            if (r3 >= r2) goto L80
            int r7 = r3 + 1
            char r3 = r9.charAt(r3)
            if (r3 > r5) goto L7b
            if (r3 >= r6) goto L46
            goto L7b
        L46:
            int r1 = r1 * 10
            int r3 = r3 - r6
            int r1 = r1 + r3
            if (r7 >= r2) goto L80
            int r3 = r7 + 1
            char r7 = r9.charAt(r7)
            if (r7 > r5) goto L76
            if (r7 >= r6) goto L57
            goto L76
        L57:
            int r1 = r1 * 10
            int r7 = r7 - r6
            int r1 = r1 + r7
            if (r3 >= r2) goto L80
        L5d:
            int r7 = r3 + 1
            char r3 = r9.charAt(r3)
            if (r3 > r5) goto L71
            if (r3 >= r6) goto L68
            goto L71
        L68:
            int r1 = r1 * r4
            int r3 = r3 + (-48)
            int r1 = r1 + r3
            if (r7 < r2) goto L6f
            goto L80
        L6f:
            r3 = r7
            goto L5d
        L71:
            int r9 = java.lang.Integer.parseInt(r9)
            return r9
        L76:
            int r9 = java.lang.Integer.parseInt(r9)
            return r9
        L7b:
            int r9 = java.lang.Integer.parseInt(r9)
            return r9
        L80:
            if (r0 == 0) goto L83
            int r1 = -r1
        L83:
            return r1
        L84:
            int r9 = java.lang.Integer.parseInt(r9)
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.core.io.NumberInput.parseInt(java.lang.String):int");
    }
}
