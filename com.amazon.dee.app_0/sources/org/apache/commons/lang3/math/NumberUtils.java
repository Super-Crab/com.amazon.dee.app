package org.apache.commons.lang3.math;

import com.amazon.alexa.fitness.utils.FullScreenUpdaterUtilKt;
import com.amazon.comms.ringservice.webrtc.FrostVideoEffectController;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.lang.reflect.Array;
import java.math.BigDecimal;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.apache.logging.log4j.util.ProcessIdUtil;
/* loaded from: classes4.dex */
public class NumberUtils {
    public static final Long LONG_ZERO = 0L;
    public static final Long LONG_ONE = 1L;
    public static final Long LONG_MINUS_ONE = -1L;
    public static final Integer INTEGER_ZERO = 0;
    public static final Integer INTEGER_ONE = 1;
    public static final Integer INTEGER_MINUS_ONE = -1;
    public static final Short SHORT_ZERO = 0;
    public static final Short SHORT_ONE = 1;
    public static final Short SHORT_MINUS_ONE = -1;
    public static final Byte BYTE_ZERO = (byte) 0;
    public static final Byte BYTE_ONE = (byte) 1;
    public static final Byte BYTE_MINUS_ONE = (byte) -1;
    public static final Double DOUBLE_ZERO = Double.valueOf((double) FrostVideoEffectController.VIDEO_STRENGTH_CLEAR);
    public static final Double DOUBLE_ONE = Double.valueOf(1.0d);
    public static final Double DOUBLE_MINUS_ONE = Double.valueOf(-1.0d);
    public static final Float FLOAT_ZERO = Float.valueOf(0.0f);
    public static final Float FLOAT_ONE = Float.valueOf(1.0f);
    public static final Float FLOAT_MINUS_ONE = Float.valueOf(-1.0f);

    public static int compare(byte b, byte b2) {
        return b - b2;
    }

    public static int compare(int i, int i2) {
        if (i == i2) {
            return 0;
        }
        return i < i2 ? -1 : 1;
    }

    public static int compare(long j, long j2) {
        int i = (j > j2 ? 1 : (j == j2 ? 0 : -1));
        if (i == 0) {
            return 0;
        }
        return i < 0 ? -1 : 1;
    }

    public static int compare(short s, short s2) {
        if (s == s2) {
            return 0;
        }
        return s < s2 ? -1 : 1;
    }

    public static BigDecimal createBigDecimal(String str) {
        if (str == null) {
            return null;
        }
        if (!StringUtils.isBlank(str)) {
            if (!str.trim().startsWith(FullScreenUpdaterUtilKt.DEFAULT_DATA)) {
                return new BigDecimal(str);
            }
            throw new NumberFormatException(GeneratedOutlineSupport1.outline72(str, " is not a valid number."));
        }
        throw new NumberFormatException("A blank string is not a valid number");
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x0053  */
    /* JADX WARN: Removed duplicated region for block: B:28:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.math.BigInteger createBigInteger(java.lang.String r5) {
        /*
            if (r5 != 0) goto L4
            r5 = 0
            return r5
        L4:
            r0 = 10
            java.lang.String r1 = "-"
            boolean r1 = r5.startsWith(r1)
            r2 = 0
            r3 = 1
            if (r1 == 0) goto L12
            r2 = r3
            goto L13
        L12:
            r3 = r2
        L13:
            java.lang.String r1 = "0x"
            boolean r1 = r5.startsWith(r1, r2)
            r4 = 16
            if (r1 != 0) goto L45
            java.lang.String r1 = "0X"
            boolean r1 = r5.startsWith(r1, r2)
            if (r1 == 0) goto L26
            goto L45
        L26:
            java.lang.String r1 = "#"
            boolean r1 = r5.startsWith(r1, r2)
            if (r1 == 0) goto L31
            int r2 = r2 + 1
            goto L47
        L31:
            java.lang.String r1 = "0"
            boolean r1 = r5.startsWith(r1, r2)
            if (r1 == 0) goto L48
            int r1 = r5.length()
            int r4 = r2 + 1
            if (r1 <= r4) goto L48
            r0 = 8
            r2 = r4
            goto L48
        L45:
            int r2 = r2 + 2
        L47:
            r0 = r4
        L48:
            java.math.BigInteger r1 = new java.math.BigInteger
            java.lang.String r5 = r5.substring(r2)
            r1.<init>(r5, r0)
            if (r3 == 0) goto L57
            java.math.BigInteger r1 = r1.negate()
        L57:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.lang3.math.NumberUtils.createBigInteger(java.lang.String):java.math.BigInteger");
    }

    public static Double createDouble(String str) {
        if (str == null) {
            return null;
        }
        return Double.valueOf(str);
    }

    public static Float createFloat(String str) {
        if (str == null) {
            return null;
        }
        return Float.valueOf(str);
    }

    public static Integer createInteger(String str) {
        if (str == null) {
            return null;
        }
        return Integer.decode(str);
    }

    public static Long createLong(String str) {
        if (str == null) {
            return null;
        }
        return Long.decode(str);
    }

    /* JADX WARN: Code restructure failed: missing block: B:82:0x011d, code lost:
        if (r2 == 'l') goto L73;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.Number createNumber(java.lang.String r17) throws java.lang.NumberFormatException {
        /*
            Method dump skipped, instructions count: 503
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.lang3.math.NumberUtils.createNumber(java.lang.String):java.lang.Number");
    }

    private static String getMantissa(String str) {
        return getMantissa(str, str.length());
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
        if (StringUtils.isEmpty(str)) {
            return false;
        }
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /* JADX WARN: Code restructure failed: missing block: B:100:0x00e3, code lost:
        if (r11 == false) goto L111;
     */
    /* JADX WARN: Code restructure failed: missing block: B:102:0x00e6, code lost:
        return r5;
     */
    /* JADX WARN: Code restructure failed: missing block: B:159:?, code lost:
        return false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:160:?, code lost:
        return false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:161:?, code lost:
        return false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:162:?, code lost:
        return false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:163:?, code lost:
        return false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:164:?, code lost:
        return false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:165:?, code lost:
        return false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x0093, code lost:
        if (r3 >= r0.length) goto L107;
     */
    /* JADX WARN: Code restructure failed: missing block: B:62:0x0097, code lost:
        if (r0[r3] < '0') goto L72;
     */
    /* JADX WARN: Code restructure failed: missing block: B:64:0x009b, code lost:
        if (r0[r3] > '9') goto L72;
     */
    /* JADX WARN: Code restructure failed: missing block: B:65:0x009d, code lost:
        return r5;
     */
    /* JADX WARN: Code restructure failed: missing block: B:67:0x00a0, code lost:
        if (r0[r3] == 'e') goto L106;
     */
    /* JADX WARN: Code restructure failed: missing block: B:69:0x00a4, code lost:
        if (r0[r3] != 'E') goto L76;
     */
    /* JADX WARN: Code restructure failed: missing block: B:72:0x00a9, code lost:
        if (r0[r3] != '.') goto L82;
     */
    /* JADX WARN: Code restructure failed: missing block: B:73:0x00ab, code lost:
        if (r13 != false) goto L81;
     */
    /* JADX WARN: Code restructure failed: missing block: B:74:0x00ad, code lost:
        if (r12 == false) goto L80;
     */
    /* JADX WARN: Code restructure failed: missing block: B:76:0x00b0, code lost:
        return r11;
     */
    /* JADX WARN: Code restructure failed: missing block: B:77:0x00b1, code lost:
        return false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:78:0x00b2, code lost:
        if (r6 != false) goto L92;
     */
    /* JADX WARN: Code restructure failed: missing block: B:80:0x00b8, code lost:
        if (r0[r3] == 'd') goto L91;
     */
    /* JADX WARN: Code restructure failed: missing block: B:82:0x00be, code lost:
        if (r0[r3] == 'D') goto L91;
     */
    /* JADX WARN: Code restructure failed: missing block: B:84:0x00c2, code lost:
        if (r0[r3] == 'f') goto L91;
     */
    /* JADX WARN: Code restructure failed: missing block: B:86:0x00c8, code lost:
        if (r0[r3] != 'F') goto L92;
     */
    /* JADX WARN: Code restructure failed: missing block: B:87:0x00ca, code lost:
        return r11;
     */
    /* JADX WARN: Code restructure failed: missing block: B:89:0x00cf, code lost:
        if (r0[r3] == 'l') goto L97;
     */
    /* JADX WARN: Code restructure failed: missing block: B:91:0x00d5, code lost:
        if (r0[r3] != 'L') goto L96;
     */
    /* JADX WARN: Code restructure failed: missing block: B:93:0x00d8, code lost:
        return false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:94:0x00d9, code lost:
        if (r11 == false) goto L104;
     */
    /* JADX WARN: Code restructure failed: missing block: B:95:0x00db, code lost:
        if (r12 != false) goto L103;
     */
    /* JADX WARN: Code restructure failed: missing block: B:96:0x00dd, code lost:
        if (r13 != false) goto L102;
     */
    /* JADX WARN: Code restructure failed: missing block: B:98:0x00e0, code lost:
        return r5;
     */
    /* JADX WARN: Code restructure failed: missing block: B:99:0x00e1, code lost:
        if (r6 != false) goto L112;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static boolean isNumber(java.lang.String r16) {
        /*
            Method dump skipped, instructions count: 305
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.lang3.math.NumberUtils.isNumber(java.lang.String):boolean");
    }

    public static boolean isParsable(String str) {
        if (StringUtils.endsWith(str, ".")) {
            return false;
        }
        if (StringUtils.startsWith(str, ProcessIdUtil.DEFAULT_PROCESSID)) {
            return isDigits(StringUtils.replaceOnce(str.substring(1), ".", ""));
        }
        return isDigits(StringUtils.replaceOnce(str, ".", ""));
    }

    public static byte max(byte b, byte b2, byte b3) {
        if (b2 > b) {
            b = b2;
        }
        return b3 > b ? b3 : b;
    }

    public static int max(int i, int i2, int i3) {
        if (i2 > i) {
            i = i2;
        }
        return i3 > i ? i3 : i;
    }

    public static long max(long j, long j2, long j3) {
        if (j2 > j) {
            j = j2;
        }
        return j3 > j ? j3 : j;
    }

    public static long max(long... jArr) {
        validateArray(jArr);
        long j = jArr[0];
        for (int i = 1; i < jArr.length; i++) {
            if (jArr[i] > j) {
                j = jArr[i];
            }
        }
        return j;
    }

    public static short max(short s, short s2, short s3) {
        if (s2 > s) {
            s = s2;
        }
        return s3 > s ? s3 : s;
    }

    public static byte min(byte b, byte b2, byte b3) {
        if (b2 < b) {
            b = b2;
        }
        return b3 < b ? b3 : b;
    }

    public static int min(int i, int i2, int i3) {
        if (i2 < i) {
            i = i2;
        }
        return i3 < i ? i3 : i;
    }

    public static long min(long j, long j2, long j3) {
        if (j2 < j) {
            j = j2;
        }
        return j3 < j ? j3 : j;
    }

    public static long min(long... jArr) {
        validateArray(jArr);
        long j = jArr[0];
        for (int i = 1; i < jArr.length; i++) {
            if (jArr[i] < j) {
                j = jArr[i];
            }
        }
        return j;
    }

    public static short min(short s, short s2, short s3) {
        if (s2 < s) {
            s = s2;
        }
        return s3 < s ? s3 : s;
    }

    public static byte toByte(String str) {
        return toByte(str, (byte) 0);
    }

    public static double toDouble(String str) {
        return toDouble(str, FrostVideoEffectController.VIDEO_STRENGTH_CLEAR);
    }

    public static float toFloat(String str) {
        return toFloat(str, 0.0f);
    }

    public static int toInt(String str) {
        return toInt(str, 0);
    }

    public static long toLong(String str) {
        return toLong(str, 0L);
    }

    public static short toShort(String str) {
        return toShort(str, (short) 0);
    }

    private static void validateArray(Object obj) {
        if (obj != null) {
            Validate.isTrue(Array.getLength(obj) != 0, "Array cannot be empty.", new Object[0]);
            return;
        }
        throw new IllegalArgumentException("The Array must not be null");
    }

    private static String getMantissa(String str, int i) {
        char charAt = str.charAt(0);
        return charAt == '-' || charAt == '+' ? str.substring(1, i) : str.substring(0, i);
    }

    public static byte toByte(String str, byte b) {
        if (str == null) {
            return b;
        }
        try {
            return Byte.parseByte(str);
        } catch (NumberFormatException unused) {
            return b;
        }
    }

    public static double toDouble(String str, double d) {
        if (str == null) {
            return d;
        }
        try {
            return Double.parseDouble(str);
        } catch (NumberFormatException unused) {
            return d;
        }
    }

    public static float toFloat(String str, float f) {
        if (str == null) {
            return f;
        }
        try {
            return Float.parseFloat(str);
        } catch (NumberFormatException unused) {
            return f;
        }
    }

    public static int toInt(String str, int i) {
        if (str == null) {
            return i;
        }
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException unused) {
            return i;
        }
    }

    public static long toLong(String str, long j) {
        if (str == null) {
            return j;
        }
        try {
            return Long.parseLong(str);
        } catch (NumberFormatException unused) {
            return j;
        }
    }

    public static short toShort(String str, short s) {
        if (str == null) {
            return s;
        }
        try {
            return Short.parseShort(str);
        } catch (NumberFormatException unused) {
            return s;
        }
    }

    public static int max(int... iArr) {
        validateArray(iArr);
        int i = iArr[0];
        for (int i2 = 1; i2 < iArr.length; i2++) {
            if (iArr[i2] > i) {
                i = iArr[i2];
            }
        }
        return i;
    }

    public static int min(int... iArr) {
        validateArray(iArr);
        int i = iArr[0];
        for (int i2 = 1; i2 < iArr.length; i2++) {
            if (iArr[i2] < i) {
                i = iArr[i2];
            }
        }
        return i;
    }

    public static short max(short... sArr) {
        validateArray(sArr);
        short s = sArr[0];
        for (int i = 1; i < sArr.length; i++) {
            if (sArr[i] > s) {
                s = sArr[i];
            }
        }
        return s;
    }

    public static short min(short... sArr) {
        validateArray(sArr);
        short s = sArr[0];
        for (int i = 1; i < sArr.length; i++) {
            if (sArr[i] < s) {
                s = sArr[i];
            }
        }
        return s;
    }

    public static byte max(byte... bArr) {
        validateArray(bArr);
        byte b = bArr[0];
        for (int i = 1; i < bArr.length; i++) {
            if (bArr[i] > b) {
                b = bArr[i];
            }
        }
        return b;
    }

    public static byte min(byte... bArr) {
        validateArray(bArr);
        byte b = bArr[0];
        for (int i = 1; i < bArr.length; i++) {
            if (bArr[i] < b) {
                b = bArr[i];
            }
        }
        return b;
    }

    public static double max(double... dArr) {
        validateArray(dArr);
        double d = dArr[0];
        for (int i = 1; i < dArr.length; i++) {
            if (Double.isNaN(dArr[i])) {
                return Double.NaN;
            }
            if (dArr[i] > d) {
                d = dArr[i];
            }
        }
        return d;
    }

    public static double min(double... dArr) {
        validateArray(dArr);
        double d = dArr[0];
        for (int i = 1; i < dArr.length; i++) {
            if (Double.isNaN(dArr[i])) {
                return Double.NaN;
            }
            if (dArr[i] < d) {
                d = dArr[i];
            }
        }
        return d;
    }

    public static float max(float... fArr) {
        validateArray(fArr);
        float f = fArr[0];
        for (int i = 1; i < fArr.length; i++) {
            if (Float.isNaN(fArr[i])) {
                return Float.NaN;
            }
            if (fArr[i] > f) {
                f = fArr[i];
            }
        }
        return f;
    }

    public static float min(float... fArr) {
        validateArray(fArr);
        float f = fArr[0];
        for (int i = 1; i < fArr.length; i++) {
            if (Float.isNaN(fArr[i])) {
                return Float.NaN;
            }
            if (fArr[i] < f) {
                f = fArr[i];
            }
        }
        return f;
    }

    public static double max(double d, double d2, double d3) {
        return Math.max(Math.max(d, d2), d3);
    }

    public static double min(double d, double d2, double d3) {
        return Math.min(Math.min(d, d2), d3);
    }

    public static float max(float f, float f2, float f3) {
        return Math.max(Math.max(f, f2), f3);
    }

    public static float min(float f, float f2, float f3) {
        return Math.min(Math.min(f, f2), f3);
    }
}
