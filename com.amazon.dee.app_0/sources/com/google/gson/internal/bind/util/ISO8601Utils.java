package com.google.gson.internal.bind.util;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;
import kotlinx.serialization.json.internal.JsonReaderKt;
import org.bouncycastle.pqc.math.linearalgebra.Matrix;
/* loaded from: classes3.dex */
public class ISO8601Utils {
    private static final TimeZone TIMEZONE_UTC = TimeZone.getTimeZone("UTC");
    private static final String UTC_ID = "UTC";

    private static boolean checkOffset(String str, int i, char c) {
        return i < str.length() && str.charAt(i) == c;
    }

    public static String format(Date date) {
        return format(date, false, TIMEZONE_UTC);
    }

    private static int indexOfNonDigit(String str, int i) {
        while (i < str.length()) {
            char charAt = str.charAt(i);
            if (charAt < '0' || charAt > '9') {
                return i;
            }
            i++;
        }
        return str.length();
    }

    private static void padInt(StringBuilder sb, int i, int i2) {
        String num = Integer.toString(i);
        for (int length = i2 - num.length(); length > 0; length--) {
            sb.append('0');
        }
        sb.append(num);
    }

    /* JADX WARN: Removed duplicated region for block: B:48:0x00c9 A[Catch: IllegalArgumentException | IndexOutOfBoundsException | NumberFormatException -> 0x01b6, NumberFormatException -> 0x01b8, IndexOutOfBoundsException -> 0x01ba, TryCatch #2 {IllegalArgumentException | IndexOutOfBoundsException | NumberFormatException -> 0x01b6, blocks: (B:3:0x0004, B:5:0x0016, B:6:0x0018, B:8:0x0024, B:9:0x0026, B:11:0x0035, B:13:0x003b, B:17:0x0050, B:19:0x0060, B:20:0x0062, B:22:0x006e, B:23:0x0070, B:25:0x0076, B:29:0x0080, B:34:0x0090, B:36:0x0098, B:46:0x00c1, B:48:0x00c9, B:50:0x00d0, B:74:0x017d, B:54:0x00da, B:55:0x00f5, B:56:0x00f6, B:60:0x0112, B:62:0x011f, B:65:0x0128, B:67:0x0147, B:70:0x0156, B:71:0x0178, B:73:0x017b, B:59:0x0101, B:76:0x01ae, B:77:0x01b5, B:40:0x00b0, B:41:0x00b3), top: B:93:0x0004 }] */
    /* JADX WARN: Removed duplicated region for block: B:76:0x01ae A[Catch: IllegalArgumentException | IndexOutOfBoundsException | NumberFormatException -> 0x01b6, NumberFormatException -> 0x01b8, IndexOutOfBoundsException -> 0x01ba, TryCatch #2 {IllegalArgumentException | IndexOutOfBoundsException | NumberFormatException -> 0x01b6, blocks: (B:3:0x0004, B:5:0x0016, B:6:0x0018, B:8:0x0024, B:9:0x0026, B:11:0x0035, B:13:0x003b, B:17:0x0050, B:19:0x0060, B:20:0x0062, B:22:0x006e, B:23:0x0070, B:25:0x0076, B:29:0x0080, B:34:0x0090, B:36:0x0098, B:46:0x00c1, B:48:0x00c9, B:50:0x00d0, B:74:0x017d, B:54:0x00da, B:55:0x00f5, B:56:0x00f6, B:60:0x0112, B:62:0x011f, B:65:0x0128, B:67:0x0147, B:70:0x0156, B:71:0x0178, B:73:0x017b, B:59:0x0101, B:76:0x01ae, B:77:0x01b5, B:40:0x00b0, B:41:0x00b3), top: B:93:0x0004 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.util.Date parse(java.lang.String r17, java.text.ParsePosition r18) throws java.text.ParseException {
        /*
            Method dump skipped, instructions count: 526
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.gson.internal.bind.util.ISO8601Utils.parse(java.lang.String, java.text.ParsePosition):java.util.Date");
    }

    private static int parseInt(String str, int i, int i2) throws NumberFormatException {
        int i3;
        int i4;
        if (i < 0 || i2 > str.length() || i > i2) {
            throw new NumberFormatException(str);
        }
        if (i < i2) {
            i4 = i + 1;
            int digit = Character.digit(str.charAt(i), 10);
            if (digit < 0) {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Invalid number: ");
                outline107.append(str.substring(i, i2));
                throw new NumberFormatException(outline107.toString());
            }
            i3 = -digit;
        } else {
            i3 = 0;
            i4 = i;
        }
        while (i4 < i2) {
            int i5 = i4 + 1;
            int digit2 = Character.digit(str.charAt(i4), 10);
            if (digit2 < 0) {
                StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("Invalid number: ");
                outline1072.append(str.substring(i, i2));
                throw new NumberFormatException(outline1072.toString());
            }
            i3 = (i3 * 10) - digit2;
            i4 = i5;
        }
        return -i3;
    }

    public static String format(Date date, boolean z) {
        return format(date, z, TIMEZONE_UTC);
    }

    public static String format(Date date, boolean z, TimeZone timeZone) {
        GregorianCalendar gregorianCalendar = new GregorianCalendar(timeZone, Locale.US);
        gregorianCalendar.setTime(date);
        StringBuilder sb = new StringBuilder(19 + (z ? 4 : 0) + (timeZone.getRawOffset() == 0 ? 1 : 6));
        padInt(sb, gregorianCalendar.get(1), 4);
        char c = '-';
        sb.append('-');
        padInt(sb, gregorianCalendar.get(2) + 1, 2);
        sb.append('-');
        padInt(sb, gregorianCalendar.get(5), 2);
        sb.append('T');
        padInt(sb, gregorianCalendar.get(11), 2);
        sb.append(JsonReaderKt.COLON);
        padInt(sb, gregorianCalendar.get(12), 2);
        sb.append(JsonReaderKt.COLON);
        padInt(sb, gregorianCalendar.get(13), 2);
        if (z) {
            sb.append('.');
            padInt(sb, gregorianCalendar.get(14), 3);
        }
        int offset = timeZone.getOffset(gregorianCalendar.getTimeInMillis());
        if (offset != 0) {
            int i = offset / 60000;
            int abs = Math.abs(i / 60);
            int abs2 = Math.abs(i % 60);
            if (offset >= 0) {
                c = '+';
            }
            sb.append(c);
            padInt(sb, abs, 2);
            sb.append(JsonReaderKt.COLON);
            padInt(sb, abs2, 2);
        } else {
            sb.append(Matrix.MATRIX_TYPE_ZERO);
        }
        return sb.toString();
    }
}
