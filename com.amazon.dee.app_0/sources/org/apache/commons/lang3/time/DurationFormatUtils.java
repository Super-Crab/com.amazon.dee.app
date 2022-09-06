package org.apache.commons.lang3.time;

import androidx.exifinterface.media.ExifInterface;
import com.amazon.mobile.heremapsexplore.Constants.ReactProperties;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
/* loaded from: classes4.dex */
public class DurationFormatUtils {
    public static final String ISO_EXTENDED_FORMAT_PATTERN = "'P'yyyy'Y'M'M'd'DT'H'H'm'M's.SSS'S'";
    static final Object y = ReactProperties.HereMapMarker.Y;
    static final Object M = "M";
    static final Object d = "d";
    static final Object H = "H";
    static final Object m = "m";
    static final Object s = "s";
    static final Object S = ExifInterface.LATITUDE_SOUTH;

    static String format(Token[] tokenArr, long j, long j2, long j3, long j4, long j5, long j6, long j7, boolean z) {
        int i;
        int i2;
        Token[] tokenArr2 = tokenArr;
        StringBuilder sb = new StringBuilder();
        int length = tokenArr2.length;
        int i3 = 0;
        boolean z2 = false;
        while (i3 < length) {
            Token token = tokenArr2[i3];
            Object value = token.getValue();
            int count = token.getCount();
            if (value instanceof StringBuilder) {
                sb.append(value.toString());
                i2 = length;
                i = i3;
            } else {
                if (value == y) {
                    sb.append(paddedValue(j, z, count));
                    i2 = length;
                    i = i3;
                } else {
                    if (value == M) {
                        i = i3;
                        sb.append(paddedValue(j2, z, count));
                    } else {
                        i = i3;
                        if (value == d) {
                            sb.append(paddedValue(j3, z, count));
                        } else if (value == H) {
                            sb.append(paddedValue(j4, z, count));
                            i2 = length;
                        } else if (value == m) {
                            sb.append(paddedValue(j5, z, count));
                            i2 = length;
                        } else {
                            if (value == s) {
                                i2 = length;
                                sb.append(paddedValue(j6, z, count));
                                z2 = true;
                            } else {
                                i2 = length;
                                if (value == S) {
                                    if (z2) {
                                        int i4 = 3;
                                        if (z) {
                                            i4 = Math.max(3, count);
                                        }
                                        sb.append(paddedValue(j7, true, i4));
                                    } else {
                                        sb.append(paddedValue(j7, z, count));
                                    }
                                    z2 = false;
                                }
                            }
                            i3 = i + 1;
                            length = i2;
                            tokenArr2 = tokenArr;
                        }
                    }
                    i2 = length;
                }
                z2 = false;
            }
            i3 = i + 1;
            length = i2;
            tokenArr2 = tokenArr;
        }
        return sb.toString();
    }

    public static String formatDuration(long j, String str) {
        return formatDuration(j, str, true);
    }

    public static String formatDurationHMS(long j) {
        return formatDuration(j, "HH:mm:ss.SSS");
    }

    public static String formatDurationISO(long j) {
        return formatDuration(j, ISO_EXTENDED_FORMAT_PATTERN, false);
    }

    public static String formatDurationWords(long j, boolean z, boolean z2) {
        String formatDuration = formatDuration(j, "d' days 'H' hours 'm' minutes 's' seconds'");
        if (z) {
            formatDuration = GeneratedOutlineSupport1.outline72(" ", formatDuration);
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
        return StringUtils.replaceOnce(StringUtils.replaceOnce(StringUtils.replaceOnce(StringUtils.replaceOnce(" " + formatDuration, " 1 seconds", " 1 second"), " 1 minutes", " 1 minute"), " 1 hours", " 1 hour"), " 1 days", " 1 day").trim();
    }

    public static String formatPeriod(long j, long j2, String str) {
        return formatPeriod(j, j2, str, true, TimeZone.getDefault());
    }

    public static String formatPeriodISO(long j, long j2) {
        return formatPeriod(j, j2, ISO_EXTENDED_FORMAT_PATTERN, false, TimeZone.getDefault());
    }

    static Token[] lexx(String str) {
        Object obj;
        ArrayList arrayList = new ArrayList(str.length());
        boolean z = false;
        StringBuilder sb = null;
        Token token = null;
        for (int i = 0; i < str.length(); i++) {
            char charAt = str.charAt(i);
            if (!z || charAt == '\'') {
                if (charAt != '\'') {
                    if (charAt == 'H') {
                        obj = H;
                    } else if (charAt == 'M') {
                        obj = M;
                    } else if (charAt == 'S') {
                        obj = S;
                    } else if (charAt == 'd') {
                        obj = d;
                    } else if (charAt == 'm') {
                        obj = m;
                    } else if (charAt == 's') {
                        obj = s;
                    } else if (charAt != 'y') {
                        if (sb == null) {
                            sb = new StringBuilder();
                            arrayList.add(new Token(sb));
                        }
                        sb.append(charAt);
                        obj = null;
                    } else {
                        obj = y;
                    }
                } else if (z) {
                    z = false;
                    sb = null;
                    obj = null;
                } else {
                    StringBuilder sb2 = new StringBuilder();
                    arrayList.add(new Token(sb2));
                    obj = null;
                    sb = sb2;
                    z = true;
                }
                if (obj != null) {
                    if (token != null && token.getValue() == obj) {
                        token.increment();
                    } else {
                        token = new Token(obj);
                        arrayList.add(token);
                    }
                    sb = null;
                }
            } else {
                sb.append(charAt);
            }
        }
        if (!z) {
            return (Token[]) arrayList.toArray(new Token[arrayList.size()]);
        }
        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline72("Unmatched quote in format: ", str));
    }

    private static String paddedValue(long j, boolean z, int i) {
        String l = Long.toString(j);
        return z ? StringUtils.leftPad(l, i, '0') : l;
    }

    public static String formatDuration(long j, String str, boolean z) {
        long j2;
        long j3;
        long j4;
        long j5;
        long j6;
        long j7;
        Validate.inclusiveBetween(0L, Long.MAX_VALUE, j, "durationMillis must not be negative");
        Token[] lexx = lexx(str);
        if (Token.containsTokenWithValue(lexx, d)) {
            long j8 = j / 86400000;
            j2 = j - (86400000 * j8);
            j3 = j8;
        } else {
            j2 = j;
            j3 = 0;
        }
        if (Token.containsTokenWithValue(lexx, H)) {
            long j9 = j2 / 3600000;
            j2 -= 3600000 * j9;
            j4 = j9;
        } else {
            j4 = 0;
        }
        if (Token.containsTokenWithValue(lexx, m)) {
            long j10 = j2 / 60000;
            j2 -= 60000 * j10;
            j5 = j10;
        } else {
            j5 = 0;
        }
        if (Token.containsTokenWithValue(lexx, s)) {
            long j11 = j2 / 1000;
            j7 = j2 - (1000 * j11);
            j6 = j11;
        } else {
            j6 = 0;
            j7 = j2;
        }
        return format(lexx, 0L, 0L, j3, j4, j5, j6, j7, z);
    }

    public static String formatPeriod(long j, long j2, String str, boolean z, TimeZone timeZone) {
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        Validate.isTrue(j <= j2, "startMillis must not be greater than endMillis", new Object[0]);
        Token[] lexx = lexx(str);
        Calendar calendar = Calendar.getInstance(timeZone);
        calendar.setTime(new Date(j));
        Calendar calendar2 = Calendar.getInstance(timeZone);
        calendar2.setTime(new Date(j2));
        int i7 = calendar2.get(14) - calendar.get(14);
        int i8 = calendar2.get(13) - calendar.get(13);
        int i9 = calendar2.get(12) - calendar.get(12);
        int i10 = calendar2.get(11) - calendar.get(11);
        int i11 = calendar2.get(5) - calendar.get(5);
        int i12 = calendar2.get(2) - calendar.get(2);
        int i13 = calendar2.get(1) - calendar.get(1);
        while (i7 < 0) {
            i7 += 1000;
            i8--;
        }
        while (i8 < 0) {
            i8 += 60;
            i9--;
        }
        while (i9 < 0) {
            i9 += 60;
            i10--;
        }
        while (i10 < 0) {
            i10 += 24;
            i11--;
        }
        if (Token.containsTokenWithValue(lexx, M)) {
            while (i11 < 0) {
                i11 += calendar.getActualMaximum(5);
                i12--;
                calendar.add(2, 1);
            }
            while (i12 < 0) {
                i12 += 12;
                i13--;
            }
            if (!Token.containsTokenWithValue(lexx, y) && i13 != 0) {
                while (i13 != 0) {
                    i12 += i13 * 12;
                    i13 = 0;
                }
            }
        } else {
            if (!Token.containsTokenWithValue(lexx, y)) {
                int i14 = calendar2.get(1);
                if (i12 < 0) {
                    i14--;
                }
                while (calendar.get(1) != i14) {
                    int actualMaximum = (calendar.getActualMaximum(6) - calendar.get(6)) + i11;
                    if ((calendar instanceof GregorianCalendar) && calendar.get(2) == 1 && calendar.get(5) == 29) {
                        actualMaximum++;
                    }
                    calendar.add(1, 1);
                    i11 = calendar.get(6) + actualMaximum;
                }
                i13 = 0;
            }
            while (calendar.get(2) != calendar2.get(2)) {
                i11 += calendar.getActualMaximum(5);
                calendar.add(2, 1);
            }
            i12 = 0;
            while (i11 < 0) {
                i11 += calendar.getActualMaximum(5);
                i12--;
                calendar.add(2, 1);
            }
        }
        if (!Token.containsTokenWithValue(lexx, d)) {
            i2 = (i11 * 24) + i10;
            i = 0;
        } else {
            i = i11;
            i2 = i10;
        }
        if (!Token.containsTokenWithValue(lexx, H)) {
            i4 = (i2 * 60) + i9;
            i3 = 0;
        } else {
            i3 = i2;
            i4 = i9;
        }
        if (!Token.containsTokenWithValue(lexx, m)) {
            i5 = (i4 * 60) + i8;
            i6 = 0;
        } else {
            int i15 = i4;
            i5 = i8;
            i6 = i15;
        }
        if (!Token.containsTokenWithValue(lexx, s)) {
            i7 += i5 * 1000;
            i5 = 0;
        }
        return format(lexx, i13, i12, i, i3, i6, i5, i7, z);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public static class Token {
        private int count;
        private final Object value;

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
                if (obj2 instanceof StringBuilder) {
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
