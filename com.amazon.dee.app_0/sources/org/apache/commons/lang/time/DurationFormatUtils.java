package org.apache.commons.lang.time;

import androidx.exifinterface.media.ExifInterface;
import com.amazon.mobile.heremapsexplore.Constants.ReactProperties;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.text.StrBuilder;
/* loaded from: classes4.dex */
public class DurationFormatUtils {
    public static final String ISO_EXTENDED_FORMAT_PATTERN = "'P'yyyy'Y'M'M'd'DT'H'H'm'M's.S'S'";
    static final Object y = ReactProperties.HereMapMarker.Y;
    static final Object M = "M";
    static final Object d = "d";
    static final Object H = "H";
    static final Object m = "m";
    static final Object s = "s";
    static final Object S = ExifInterface.LATITUDE_SOUTH;

    static String format(Token[] tokenArr, int i, int i2, int i3, int i4, int i5, int i6, int i7, boolean z) {
        StrBuilder strBuilder = new StrBuilder();
        int i8 = i7;
        boolean z2 = false;
        for (Token token : tokenArr) {
            Object value = token.getValue();
            int count = token.getCount();
            if (value instanceof StringBuffer) {
                strBuilder.append(value.toString());
            } else {
                if (value == y) {
                    String num = Integer.toString(i);
                    if (z) {
                        num = StringUtils.leftPad(num, count, '0');
                    }
                    strBuilder.append(num);
                } else if (value == M) {
                    String num2 = Integer.toString(i2);
                    if (z) {
                        num2 = StringUtils.leftPad(num2, count, '0');
                    }
                    strBuilder.append(num2);
                } else if (value == d) {
                    String num3 = Integer.toString(i3);
                    if (z) {
                        num3 = StringUtils.leftPad(num3, count, '0');
                    }
                    strBuilder.append(num3);
                } else if (value == H) {
                    String num4 = Integer.toString(i4);
                    if (z) {
                        num4 = StringUtils.leftPad(num4, count, '0');
                    }
                    strBuilder.append(num4);
                } else if (value == m) {
                    String num5 = Integer.toString(i5);
                    if (z) {
                        num5 = StringUtils.leftPad(num5, count, '0');
                    }
                    strBuilder.append(num5);
                } else if (value == s) {
                    String num6 = Integer.toString(i6);
                    if (z) {
                        num6 = StringUtils.leftPad(num6, count, '0');
                    }
                    strBuilder.append(num6);
                    z2 = true;
                } else if (value == S) {
                    if (z2) {
                        i8 += 1000;
                        String num7 = Integer.toString(i8);
                        if (z) {
                            num7 = StringUtils.leftPad(num7, count, '0');
                        }
                        strBuilder.append(num7.substring(1));
                    } else {
                        String num8 = Integer.toString(i8);
                        if (z) {
                            num8 = StringUtils.leftPad(num8, count, '0');
                        }
                        strBuilder.append(num8);
                    }
                }
                z2 = false;
            }
        }
        return strBuilder.toString();
    }

    public static String formatDuration(long j, String str) {
        return formatDuration(j, str, true);
    }

    public static String formatDurationHMS(long j) {
        return formatDuration(j, "H:mm:ss.SSS");
    }

    public static String formatDurationISO(long j) {
        return formatDuration(j, ISO_EXTENDED_FORMAT_PATTERN, false);
    }

    public static String formatDurationWords(long j, boolean z, boolean z2) {
        String formatDuration = formatDuration(j, "d' days 'H' hours 'm' minutes 's' seconds'");
        if (z) {
            formatDuration = GeneratedOutlineSupport1.outline71(" ", formatDuration);
            String replaceOnce = StringUtils.replaceOnce(formatDuration, " 0 days", "");
            if (replaceOnce.length() != formatDuration.length()) {
                String replaceOnce2 = StringUtils.replaceOnce(replaceOnce, " 0 hours", "");
                if (replaceOnce2.length() != replaceOnce.length()) {
                    formatDuration = StringUtils.replaceOnce(replaceOnce2, " 0 minutes", "");
                    if (formatDuration.length() != formatDuration.length()) {
                        formatDuration = StringUtils.replaceOnce(formatDuration, " 0 seconds", "");
                    }
                } else {
                    formatDuration = replaceOnce;
                }
            }
            if (formatDuration.length() != 0) {
                formatDuration = formatDuration.substring(1);
            }
        }
        if (z2) {
            String replaceOnce3 = StringUtils.replaceOnce(formatDuration, " 0 seconds", "");
            if (replaceOnce3.length() != formatDuration.length()) {
                formatDuration = StringUtils.replaceOnce(replaceOnce3, " 0 minutes", "");
                if (formatDuration.length() != replaceOnce3.length()) {
                    String replaceOnce4 = StringUtils.replaceOnce(formatDuration, " 0 hours", "");
                    if (replaceOnce4.length() != formatDuration.length()) {
                        formatDuration = StringUtils.replaceOnce(replaceOnce4, " 0 days", "");
                    }
                } else {
                    formatDuration = replaceOnce3;
                }
            }
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(" ");
        stringBuffer.append(formatDuration);
        return StringUtils.replaceOnce(StringUtils.replaceOnce(StringUtils.replaceOnce(StringUtils.replaceOnce(stringBuffer.toString(), " 1 seconds", " 1 second"), " 1 minutes", " 1 minute"), " 1 hours", " 1 hour"), " 1 days", " 1 day").trim();
    }

    public static String formatPeriod(long j, long j2, String str) {
        return formatPeriod(j, j2, str, true, TimeZone.getDefault());
    }

    public static String formatPeriodISO(long j, long j2) {
        return formatPeriod(j, j2, ISO_EXTENDED_FORMAT_PATTERN, false, TimeZone.getDefault());
    }

    static Token[] lexx(String str) {
        Object obj;
        char[] charArray = str.toCharArray();
        ArrayList arrayList = new ArrayList(charArray.length);
        boolean z = false;
        StringBuffer stringBuffer = null;
        Token token = null;
        for (char c : charArray) {
            if (!z || c == '\'') {
                if (c != '\'') {
                    if (c == 'H') {
                        obj = H;
                    } else if (c == 'M') {
                        obj = M;
                    } else if (c == 'S') {
                        obj = S;
                    } else if (c == 'd') {
                        obj = d;
                    } else if (c == 'm') {
                        obj = m;
                    } else if (c == 's') {
                        obj = s;
                    } else if (c != 'y') {
                        if (stringBuffer == null) {
                            stringBuffer = new StringBuffer();
                            arrayList.add(new Token(stringBuffer));
                        }
                        stringBuffer.append(c);
                        obj = null;
                    } else {
                        obj = y;
                    }
                } else if (z) {
                    z = false;
                    stringBuffer = null;
                    obj = null;
                } else {
                    StringBuffer stringBuffer2 = new StringBuffer();
                    arrayList.add(new Token(stringBuffer2));
                    obj = null;
                    stringBuffer = stringBuffer2;
                    z = true;
                }
                if (obj != null) {
                    if (token != null && token.getValue() == obj) {
                        token.increment();
                    } else {
                        token = new Token(obj);
                        arrayList.add(token);
                    }
                    stringBuffer = null;
                }
            } else {
                stringBuffer.append(c);
            }
        }
        return (Token[]) arrayList.toArray(new Token[arrayList.size()]);
    }

    public static String formatDuration(long j, String str, boolean z) {
        int i;
        int i2;
        int i3;
        int i4;
        Token[] lexx = lexx(str);
        if (Token.containsTokenWithValue(lexx, d)) {
            int i5 = (int) (j / 86400000);
            j -= i5 * 86400000;
            i = i5;
        } else {
            i = 0;
        }
        if (Token.containsTokenWithValue(lexx, H)) {
            int i6 = (int) (j / 3600000);
            j -= i6 * 3600000;
            i2 = i6;
        } else {
            i2 = 0;
        }
        if (Token.containsTokenWithValue(lexx, m)) {
            int i7 = (int) (j / 60000);
            j -= i7 * 60000;
            i3 = i7;
        } else {
            i3 = 0;
        }
        if (Token.containsTokenWithValue(lexx, s)) {
            int i8 = (int) (j / 1000);
            j -= i8 * 1000;
            i4 = i8;
        } else {
            i4 = 0;
        }
        return format(lexx, 0, 0, i, i2, i3, i4, Token.containsTokenWithValue(lexx, S) ? (int) j : 0, z);
    }

    public static String formatPeriod(long j, long j2, String str, boolean z, TimeZone timeZone) {
        int i;
        int i2;
        int i3;
        Token[] lexx = lexx(str);
        Calendar calendar = Calendar.getInstance(timeZone);
        calendar.setTime(new Date(j));
        Calendar calendar2 = Calendar.getInstance(timeZone);
        calendar2.setTime(new Date(j2));
        int i4 = calendar2.get(14) - calendar.get(14);
        int i5 = calendar2.get(13) - calendar.get(13);
        int i6 = calendar2.get(12) - calendar.get(12);
        int i7 = calendar2.get(11) - calendar.get(11);
        int i8 = calendar2.get(5) - calendar.get(5);
        int i9 = calendar2.get(2) - calendar.get(2);
        int i10 = calendar2.get(1) - calendar.get(1);
        while (i4 < 0) {
            i4 += 1000;
            i5--;
        }
        while (i5 < 0) {
            i5 += 60;
            i6--;
        }
        while (i6 < 0) {
            i6 += 60;
            i7--;
        }
        while (i7 < 0) {
            i7 += 24;
            i8--;
        }
        int i11 = 0;
        if (Token.containsTokenWithValue(lexx, M)) {
            while (i8 < 0) {
                i8 += calendar.getActualMaximum(5);
                i9--;
                calendar.add(2, 1);
            }
            while (i9 < 0) {
                i9 += 12;
                i10--;
            }
            if (!Token.containsTokenWithValue(lexx, y) && i10 != 0) {
                while (i10 != 0) {
                    i9 += i10 * 12;
                    i10 = 0;
                }
            }
            i = i9;
        } else {
            if (!Token.containsTokenWithValue(lexx, y)) {
                int i12 = calendar2.get(1);
                if (i9 < 0) {
                    i12--;
                }
                while (calendar.get(1) != i12) {
                    int actualMaximum = (calendar.getActualMaximum(6) - calendar.get(6)) + i8;
                    if ((calendar instanceof GregorianCalendar) && calendar.get(2) == 1 && calendar.get(5) == 29) {
                        actualMaximum++;
                    }
                    calendar.add(1, 1);
                    i8 = calendar.get(6) + actualMaximum;
                }
                i10 = 0;
            }
            while (calendar.get(2) != calendar2.get(2)) {
                i8 += calendar.getActualMaximum(5);
                calendar.add(2, 1);
            }
            i = 0;
            while (i8 < 0) {
                i8 += calendar.getActualMaximum(5);
                i--;
                calendar.add(2, 1);
            }
        }
        int i13 = i10;
        if (!Token.containsTokenWithValue(lexx, d)) {
            i7 += i8 * 24;
            i2 = 0;
        } else {
            i2 = i8;
        }
        if (!Token.containsTokenWithValue(lexx, H)) {
            i6 += i7 * 60;
            i7 = 0;
        }
        if (!Token.containsTokenWithValue(lexx, m)) {
            i5 += i6 * 60;
            i6 = 0;
        }
        if (!Token.containsTokenWithValue(lexx, s)) {
            i3 = (i5 * 1000) + i4;
        } else {
            i3 = i4;
            i11 = i5;
        }
        return format(lexx, i13, i, i2, i7, i6, i11, i3, z);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public static class Token {
        private int count;
        private Object value;

        Token(Object obj) {
            this.value = obj;
            this.count = 1;
        }

        static boolean containsTokenWithValue(Token[] tokenArr, Object obj) {
            for (Token token : tokenArr) {
                if (token.getValue() == obj) {
                    return true;
                }
            }
            return false;
        }

        public boolean equals(Object obj) {
            if (obj instanceof Token) {
                Token token = (Token) obj;
                if (this.value.getClass() != token.value.getClass() || this.count != token.count) {
                    return false;
                }
                Object obj2 = this.value;
                if (obj2 instanceof StringBuffer) {
                    return obj2.toString().equals(token.value.toString());
                }
                if (obj2 instanceof Number) {
                    return obj2.equals(token.value);
                }
                return obj2 == token.value;
            }
            return false;
        }

        int getCount() {
            return this.count;
        }

        Object getValue() {
            return this.value;
        }

        public int hashCode() {
            return this.value.hashCode();
        }

        void increment() {
            this.count++;
        }

        public String toString() {
            return StringUtils.repeat(this.value.toString(), this.count);
        }

        Token(Object obj, int i) {
            this.value = obj;
            this.count = i;
        }
    }
}
