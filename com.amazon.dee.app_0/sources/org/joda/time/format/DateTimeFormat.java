package org.joda.time.format;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicReferenceArray;
import org.apache.logging.log4j.util.Chars;
import org.joda.time.Chronology;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.ReadablePartial;
/* loaded from: classes5.dex */
public class DateTimeFormat {
    static final int DATE = 0;
    static final int DATETIME = 2;
    static final int FULL = 0;
    static final int LONG = 1;
    static final int MEDIUM = 2;
    static final int NONE = 4;
    private static final int PATTERN_CACHE_SIZE = 500;
    static final int SHORT = 3;
    static final int TIME = 1;
    private static final ConcurrentHashMap<String, DateTimeFormatter> cPatternCache = new ConcurrentHashMap<>();
    private static final AtomicReferenceArray<DateTimeFormatter> cStyleCache = new AtomicReferenceArray<>(25);

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes5.dex */
    public static class StyleFormatter implements InternalPrinter, InternalParser {
        private static final ConcurrentHashMap<StyleFormatterCacheKey, DateTimeFormatter> cCache = new ConcurrentHashMap<>();
        private final int iDateStyle;
        private final int iTimeStyle;
        private final int iType;

        StyleFormatter(int i, int i2, int i3) {
            this.iDateStyle = i;
            this.iTimeStyle = i2;
            this.iType = i3;
        }

        private DateTimeFormatter getFormatter(Locale locale) {
            if (locale == null) {
                locale = Locale.getDefault();
            }
            StyleFormatterCacheKey styleFormatterCacheKey = new StyleFormatterCacheKey(this.iType, this.iDateStyle, this.iTimeStyle, locale);
            DateTimeFormatter dateTimeFormatter = cCache.get(styleFormatterCacheKey);
            if (dateTimeFormatter == null) {
                DateTimeFormatter forPattern = DateTimeFormat.forPattern(getPattern(locale));
                DateTimeFormatter putIfAbsent = cCache.putIfAbsent(styleFormatterCacheKey, forPattern);
                return putIfAbsent != null ? putIfAbsent : forPattern;
            }
            return dateTimeFormatter;
        }

        @Override // org.joda.time.format.InternalParser
        public int estimateParsedLength() {
            return 40;
        }

        @Override // org.joda.time.format.InternalPrinter
        public int estimatePrintedLength() {
            return 40;
        }

        String getPattern(Locale locale) {
            int i = this.iType;
            DateFormat dateTimeInstance = i != 0 ? i != 1 ? i != 2 ? null : DateFormat.getDateTimeInstance(this.iDateStyle, this.iTimeStyle, locale) : DateFormat.getTimeInstance(this.iTimeStyle, locale) : DateFormat.getDateInstance(this.iDateStyle, locale);
            if (dateTimeInstance instanceof SimpleDateFormat) {
                return ((SimpleDateFormat) dateTimeInstance).toPattern();
            }
            throw new IllegalArgumentException("No datetime pattern for locale: " + locale);
        }

        @Override // org.joda.time.format.InternalParser
        public int parseInto(DateTimeParserBucket dateTimeParserBucket, CharSequence charSequence, int i) {
            return getFormatter(dateTimeParserBucket.getLocale()).getParser0().parseInto(dateTimeParserBucket, charSequence, i);
        }

        @Override // org.joda.time.format.InternalPrinter
        public void printTo(Appendable appendable, long j, Chronology chronology, int i, DateTimeZone dateTimeZone, Locale locale) throws IOException {
            getFormatter(locale).getPrinter0().printTo(appendable, j, chronology, i, dateTimeZone, locale);
        }

        @Override // org.joda.time.format.InternalPrinter
        public void printTo(Appendable appendable, ReadablePartial readablePartial, Locale locale) throws IOException {
            getFormatter(locale).getPrinter0().printTo(appendable, readablePartial, locale);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes5.dex */
    public static class StyleFormatterCacheKey {
        private final int combinedTypeAndStyle;
        private final Locale locale;

        public StyleFormatterCacheKey(int i, int i2, int i3, Locale locale) {
            this.locale = locale;
            this.combinedTypeAndStyle = i + (i2 << 4) + (i3 << 8);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || !(obj instanceof StyleFormatterCacheKey)) {
                return false;
            }
            StyleFormatterCacheKey styleFormatterCacheKey = (StyleFormatterCacheKey) obj;
            if (this.combinedTypeAndStyle != styleFormatterCacheKey.combinedTypeAndStyle) {
                return false;
            }
            Locale locale = this.locale;
            Locale locale2 = styleFormatterCacheKey.locale;
            if (locale == null) {
                if (locale2 != null) {
                    return false;
                }
            } else if (!locale.equals(locale2)) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            int i = (this.combinedTypeAndStyle + 31) * 31;
            Locale locale = this.locale;
            return i + (locale == null ? 0 : locale.hashCode());
        }
    }

    protected DateTimeFormat() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void appendPatternTo(DateTimeFormatterBuilder dateTimeFormatterBuilder, String str) {
        parsePatternTo(dateTimeFormatterBuilder, str);
    }

    private static DateTimeFormatter createDateTimeFormatter(int i, int i2) {
        StyleFormatter styleFormatter = new StyleFormatter(i, i2, i == 4 ? 1 : i2 == 4 ? 0 : 2);
        return new DateTimeFormatter(styleFormatter, styleFormatter);
    }

    private static DateTimeFormatter createFormatterForPattern(String str) {
        DateTimeFormatter putIfAbsent;
        if (str == null || str.length() == 0) {
            throw new IllegalArgumentException("Invalid pattern specification");
        }
        DateTimeFormatter dateTimeFormatter = cPatternCache.get(str);
        if (dateTimeFormatter != null) {
            return dateTimeFormatter;
        }
        DateTimeFormatterBuilder dateTimeFormatterBuilder = new DateTimeFormatterBuilder();
        parsePatternTo(dateTimeFormatterBuilder, str);
        DateTimeFormatter formatter = dateTimeFormatterBuilder.toFormatter();
        return (cPatternCache.size() >= 500 || (putIfAbsent = cPatternCache.putIfAbsent(str, formatter)) == null) ? formatter : putIfAbsent;
    }

    private static DateTimeFormatter createFormatterForStyle(String str) {
        if (str == null || str.length() != 2) {
            throw new IllegalArgumentException(GeneratedOutlineSupport1.outline72("Invalid style specification: ", str));
        }
        int selectStyle = selectStyle(str.charAt(0));
        int selectStyle2 = selectStyle(str.charAt(1));
        if (selectStyle != 4 || selectStyle2 != 4) {
            return createFormatterForStyleIndex(selectStyle, selectStyle2);
        }
        throw new IllegalArgumentException("Style '--' is invalid");
    }

    private static DateTimeFormatter createFormatterForStyleIndex(int i, int i2) {
        int i3 = (i << 2) + i + i2;
        if (i3 >= cStyleCache.length()) {
            return createDateTimeFormatter(i, i2);
        }
        DateTimeFormatter dateTimeFormatter = cStyleCache.get(i3);
        if (dateTimeFormatter != null) {
            return dateTimeFormatter;
        }
        DateTimeFormatter createDateTimeFormatter = createDateTimeFormatter(i, i2);
        return !cStyleCache.compareAndSet(i3, null, createDateTimeFormatter) ? cStyleCache.get(i3) : createDateTimeFormatter;
    }

    public static DateTimeFormatter forPattern(String str) {
        return createFormatterForPattern(str);
    }

    public static DateTimeFormatter forStyle(String str) {
        return createFormatterForStyle(str);
    }

    public static DateTimeFormatter fullDate() {
        return createFormatterForStyleIndex(0, 4);
    }

    public static DateTimeFormatter fullDateTime() {
        return createFormatterForStyleIndex(0, 0);
    }

    public static DateTimeFormatter fullTime() {
        return createFormatterForStyleIndex(4, 0);
    }

    /* JADX WARN: Removed duplicated region for block: B:9:0x0013 A[RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static boolean isNumericToken(java.lang.String r3) {
        /*
            int r0 = r3.length()
            r1 = 0
            if (r0 <= 0) goto L14
            char r3 = r3.charAt(r1)
            r2 = 1
            switch(r3) {
                case 67: goto L13;
                case 68: goto L13;
                case 70: goto L13;
                case 72: goto L13;
                case 75: goto L13;
                case 77: goto L10;
                case 83: goto L13;
                case 87: goto L13;
                case 89: goto L13;
                case 99: goto L13;
                case 100: goto L13;
                case 101: goto L13;
                case 104: goto L13;
                case 107: goto L13;
                case 109: goto L13;
                case 115: goto L13;
                case 119: goto L13;
                case 120: goto L13;
                case 121: goto L13;
                default: goto Lf;
            }
        Lf:
            goto L14
        L10:
            r3 = 2
            if (r0 > r3) goto L14
        L13:
            return r2
        L14:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.joda.time.format.DateTimeFormat.isNumericToken(java.lang.String):boolean");
    }

    public static DateTimeFormatter longDate() {
        return createFormatterForStyleIndex(1, 4);
    }

    public static DateTimeFormatter longDateTime() {
        return createFormatterForStyleIndex(1, 1);
    }

    public static DateTimeFormatter longTime() {
        return createFormatterForStyleIndex(4, 1);
    }

    public static DateTimeFormatter mediumDate() {
        return createFormatterForStyleIndex(2, 4);
    }

    public static DateTimeFormatter mediumDateTime() {
        return createFormatterForStyleIndex(2, 2);
    }

    public static DateTimeFormatter mediumTime() {
        return createFormatterForStyleIndex(4, 2);
    }

    private static void parsePatternTo(DateTimeFormatterBuilder dateTimeFormatterBuilder, String str) {
        boolean z;
        String str2;
        boolean z2;
        int length = str.length();
        int[] iArr = new int[1];
        int i = 0;
        while (i < length) {
            iArr[0] = i;
            String parseToken = parseToken(str, iArr);
            int i2 = iArr[0];
            int length2 = parseToken.length();
            if (length2 == 0) {
                return;
            }
            char charAt = parseToken.charAt(0);
            if (charAt == '\'') {
                String substring = parseToken.substring(1);
                if (substring.length() == 1) {
                    dateTimeFormatterBuilder.appendLiteral(substring.charAt(0));
                } else {
                    dateTimeFormatterBuilder.appendLiteral(new String(substring));
                }
            } else if (charAt == 'K') {
                dateTimeFormatterBuilder.appendHourOfHalfday(length2);
            } else if (charAt != 'M') {
                if (charAt == 'S') {
                    dateTimeFormatterBuilder.appendFractionOfSecond(length2, length2);
                } else if (charAt == 'a') {
                    dateTimeFormatterBuilder.appendHalfdayOfDayText();
                } else if (charAt == 'h') {
                    dateTimeFormatterBuilder.appendClockhourOfHalfday(length2);
                } else if (charAt == 'k') {
                    dateTimeFormatterBuilder.appendClockhourOfDay(length2);
                } else if (charAt == 'm') {
                    dateTimeFormatterBuilder.appendMinuteOfHour(length2);
                } else if (charAt == 's') {
                    dateTimeFormatterBuilder.appendSecondOfMinute(length2);
                } else if (charAt == 'G') {
                    dateTimeFormatterBuilder.appendEraText();
                } else if (charAt != 'H') {
                    if (charAt != 'Y') {
                        if (charAt == 'Z') {
                            if (length2 == 1) {
                                str2 = null;
                                z2 = false;
                            } else if (length2 == 2) {
                                str2 = null;
                                z2 = true;
                            } else {
                                dateTimeFormatterBuilder.appendTimeZoneId();
                            }
                            dateTimeFormatterBuilder.appendTimeZoneOffset(str2, "Z", z2, 2, 2);
                        } else if (charAt == 'd') {
                            dateTimeFormatterBuilder.appendDayOfMonth(length2);
                        } else if (charAt != 'e') {
                            switch (charAt) {
                                case 'C':
                                    dateTimeFormatterBuilder.appendCenturyOfEra(length2, length2);
                                    break;
                                case 'D':
                                    dateTimeFormatterBuilder.appendDayOfYear(length2);
                                    break;
                                case 'E':
                                    if (length2 < 4) {
                                        dateTimeFormatterBuilder.appendDayOfWeekShortText();
                                        break;
                                    } else {
                                        dateTimeFormatterBuilder.appendDayOfWeekText();
                                        break;
                                    }
                                default:
                                    switch (charAt) {
                                        case 'w':
                                            dateTimeFormatterBuilder.appendWeekOfWeekyear(length2);
                                            break;
                                        case 'x':
                                        case 'y':
                                            break;
                                        case 'z':
                                            if (length2 < 4) {
                                                dateTimeFormatterBuilder.appendTimeZoneShortName(null);
                                                break;
                                            } else {
                                                dateTimeFormatterBuilder.appendTimeZoneName();
                                                break;
                                            }
                                        default:
                                            throw new IllegalArgumentException(GeneratedOutlineSupport1.outline72("Illegal pattern component: ", parseToken));
                                    }
                            }
                        } else {
                            dateTimeFormatterBuilder.appendDayOfWeek(length2);
                        }
                    }
                    if (length2 == 2) {
                        if (i2 + 1 < length) {
                            iArr[0] = iArr[0] + 1;
                            z = !isNumericToken(parseToken(str, iArr));
                            iArr[0] = iArr[0] - 1;
                        } else {
                            z = true;
                        }
                        if (charAt != 'x') {
                            dateTimeFormatterBuilder.appendTwoDigitYear(new DateTime().getYear() - 30, z);
                        } else {
                            dateTimeFormatterBuilder.appendTwoDigitWeekyear(new DateTime().getWeekyear() - 30, z);
                        }
                    } else {
                        int i3 = 9;
                        if (i2 + 1 < length) {
                            iArr[0] = iArr[0] + 1;
                            if (isNumericToken(parseToken(str, iArr))) {
                                i3 = length2;
                            }
                            iArr[0] = iArr[0] - 1;
                        }
                        if (charAt == 'Y') {
                            dateTimeFormatterBuilder.appendYearOfEra(length2, i3);
                        } else if (charAt == 'x') {
                            dateTimeFormatterBuilder.appendWeekyear(length2, i3);
                        } else if (charAt == 'y') {
                            dateTimeFormatterBuilder.appendYear(length2, i3);
                        }
                    }
                } else {
                    dateTimeFormatterBuilder.appendHourOfDay(length2);
                }
            } else if (length2 < 3) {
                dateTimeFormatterBuilder.appendMonthOfYear(length2);
            } else if (length2 >= 4) {
                dateTimeFormatterBuilder.appendMonthOfYearText();
            } else {
                dateTimeFormatterBuilder.appendMonthOfYearShortText();
            }
            i = i2 + 1;
        }
    }

    private static String parseToken(String str, int[] iArr) {
        StringBuilder sb = new StringBuilder();
        int i = iArr[0];
        int length = str.length();
        char charAt = str.charAt(i);
        if ((charAt >= 'A' && charAt <= 'Z') || (charAt >= 'a' && charAt <= 'z')) {
            sb.append(charAt);
            while (true) {
                int i2 = i + 1;
                if (i2 >= length || str.charAt(i2) != charAt) {
                    break;
                }
                sb.append(charAt);
                i = i2;
            }
        } else {
            sb.append(Chars.QUOTE);
            boolean z = false;
            while (i < length) {
                char charAt2 = str.charAt(i);
                if (charAt2 != '\'') {
                    if (!z && ((charAt2 >= 'A' && charAt2 <= 'Z') || (charAt2 >= 'a' && charAt2 <= 'z'))) {
                        i--;
                        break;
                    }
                    sb.append(charAt2);
                } else {
                    int i3 = i + 1;
                    if (i3 >= length || str.charAt(i3) != '\'') {
                        z = !z;
                    } else {
                        sb.append(charAt2);
                        i = i3;
                    }
                }
                i++;
            }
        }
        iArr[0] = i;
        return sb.toString();
    }

    public static String patternForStyle(String str, Locale locale) {
        DateTimeFormatter createFormatterForStyle = createFormatterForStyle(str);
        if (locale == null) {
            locale = Locale.getDefault();
        }
        return ((StyleFormatter) createFormatterForStyle.getPrinter0()).getPattern(locale);
    }

    private static int selectStyle(char c) {
        if (c != '-') {
            if (c == 'F') {
                return 0;
            }
            if (c == 'S') {
                return 3;
            }
            if (c == 'L') {
                return 1;
            }
            if (c != 'M') {
                throw new IllegalArgumentException(GeneratedOutlineSupport1.outline47("Invalid style character: ", c));
            }
            return 2;
        }
        return 4;
    }

    public static DateTimeFormatter shortDate() {
        return createFormatterForStyleIndex(3, 4);
    }

    public static DateTimeFormatter shortDateTime() {
        return createFormatterForStyleIndex(3, 3);
    }

    public static DateTimeFormatter shortTime() {
        return createFormatterForStyleIndex(4, 3);
    }
}
