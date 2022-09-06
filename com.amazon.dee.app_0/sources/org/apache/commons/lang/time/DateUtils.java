package org.apache.commons.lang.time;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.TimeZone;
import org.apache.commons.lang.StringUtils;
/* loaded from: classes4.dex */
public class DateUtils {
    public static final int MILLIS_IN_DAY = 86400000;
    public static final int MILLIS_IN_HOUR = 3600000;
    public static final int MILLIS_IN_MINUTE = 60000;
    public static final int MILLIS_IN_SECOND = 1000;
    public static final long MILLIS_PER_DAY = 86400000;
    public static final long MILLIS_PER_HOUR = 3600000;
    public static final long MILLIS_PER_MINUTE = 60000;
    public static final long MILLIS_PER_SECOND = 1000;
    private static final int MODIFY_CEILING = 2;
    private static final int MODIFY_ROUND = 1;
    private static final int MODIFY_TRUNCATE = 0;
    public static final int RANGE_MONTH_MONDAY = 6;
    public static final int RANGE_MONTH_SUNDAY = 5;
    public static final int RANGE_WEEK_CENTER = 4;
    public static final int RANGE_WEEK_MONDAY = 2;
    public static final int RANGE_WEEK_RELATIVE = 3;
    public static final int RANGE_WEEK_SUNDAY = 1;
    public static final int SEMI_MONTH = 1001;
    public static final TimeZone UTC_TIME_ZONE = TimeZone.getTimeZone("GMT");
    private static final int[][] fields = {new int[]{14}, new int[]{13}, new int[]{12}, new int[]{11, 10}, new int[]{5, 5, 9}, new int[]{2, 1001}, new int[]{1}, new int[]{0}};

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public static class DateIterator implements Iterator {
        private final Calendar endFinal;
        private final Calendar spot;

        DateIterator(Calendar calendar, Calendar calendar2) {
            this.endFinal = calendar2;
            this.spot = calendar;
            this.spot.add(5, -1);
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.spot.before(this.endFinal);
        }

        @Override // java.util.Iterator
        public Object next() {
            if (!this.spot.equals(this.endFinal)) {
                this.spot.add(5, 1);
                return this.spot.clone();
            }
            throw new NoSuchElementException();
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public static Date add(Date date, int i, int i2) {
        if (date != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(i, i2);
            return calendar.getTime();
        }
        throw new IllegalArgumentException("The date must not be null");
    }

    public static Date addDays(Date date, int i) {
        return add(date, 5, i);
    }

    public static Date addHours(Date date, int i) {
        return add(date, 11, i);
    }

    public static Date addMilliseconds(Date date, int i) {
        return add(date, 14, i);
    }

    public static Date addMinutes(Date date, int i) {
        return add(date, 12, i);
    }

    public static Date addMonths(Date date, int i) {
        return add(date, 2, i);
    }

    public static Date addSeconds(Date date, int i) {
        return add(date, 13, i);
    }

    public static Date addWeeks(Date date, int i) {
        return add(date, 3, i);
    }

    public static Date addYears(Date date, int i) {
        return add(date, 1, i);
    }

    public static Date ceiling(Date date, int i) {
        if (date != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            modify(calendar, i, 2);
            return calendar.getTime();
        }
        throw new IllegalArgumentException("The date must not be null");
    }

    private static long getFragment(Date date, int i, int i2) {
        if (date != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            return getFragment(calendar, i, i2);
        }
        throw new IllegalArgumentException("The date must not be null");
    }

    public static long getFragmentInDays(Date date, int i) {
        return getFragment(date, i, 6);
    }

    public static long getFragmentInHours(Date date, int i) {
        return getFragment(date, i, 11);
    }

    public static long getFragmentInMilliseconds(Date date, int i) {
        return getFragment(date, i, 14);
    }

    public static long getFragmentInMinutes(Date date, int i) {
        return getFragment(date, i, 12);
    }

    public static long getFragmentInSeconds(Date date, int i) {
        return getFragment(date, i, 13);
    }

    private static long getMillisPerUnit(int i) {
        if (i == 5 || i == 6) {
            return 86400000L;
        }
        switch (i) {
            case 11:
                return 3600000L;
            case 12:
                return 60000L;
            case 13:
                return 1000L;
            case 14:
                return 1L;
            default:
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("The unit ");
                stringBuffer.append(i);
                stringBuffer.append(" cannot be represented is milleseconds");
                throw new IllegalArgumentException(stringBuffer.toString());
        }
    }

    private static int indexOfSignChars(String str, int i) {
        int indexOf = StringUtils.indexOf(str, '+', i);
        return indexOf < 0 ? StringUtils.indexOf(str, '-', i) : indexOf;
    }

    public static boolean isSameDay(Date date, Date date2) {
        if (date != null && date2 != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            Calendar calendar2 = Calendar.getInstance();
            calendar2.setTime(date2);
            return isSameDay(calendar, calendar2);
        }
        throw new IllegalArgumentException("The date must not be null");
    }

    public static boolean isSameInstant(Date date, Date date2) {
        if (date == null || date2 == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        return date.getTime() == date2.getTime();
    }

    public static boolean isSameLocalTime(Calendar calendar, Calendar calendar2) {
        if (calendar == null || calendar2 == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        return calendar.get(14) == calendar2.get(14) && calendar.get(13) == calendar2.get(13) && calendar.get(12) == calendar2.get(12) && calendar.get(10) == calendar2.get(10) && calendar.get(6) == calendar2.get(6) && calendar.get(1) == calendar2.get(1) && calendar.get(0) == calendar2.get(0) && calendar.getClass() == calendar2.getClass();
    }

    public static Iterator iterator(Date date, int i) {
        if (date != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            return iterator(calendar, i);
        }
        throw new IllegalArgumentException("The date must not be null");
    }

    /* JADX WARN: Code restructure failed: missing block: B:56:0x00c1, code lost:
        if (r17 == 9) goto L87;
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x00c3, code lost:
        if (r17 == 1001) goto L64;
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x00cb, code lost:
        if (r9[r5][0] != 5) goto L86;
     */
    /* JADX WARN: Code restructure failed: missing block: B:61:0x00cd, code lost:
        r6 = r16.get(5) - 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:62:0x00d2, code lost:
        if (r6 < 15) goto L69;
     */
    /* JADX WARN: Code restructure failed: missing block: B:63:0x00d4, code lost:
        r6 = r6 - 15;
     */
    /* JADX WARN: Code restructure failed: missing block: B:64:0x00d6, code lost:
        r8 = r6;
     */
    /* JADX WARN: Code restructure failed: missing block: B:65:0x00d8, code lost:
        if (r8 <= 7) goto L71;
     */
    /* JADX WARN: Code restructure failed: missing block: B:68:0x00e0, code lost:
        if (r9[r5][0] != 11) goto L86;
     */
    /* JADX WARN: Code restructure failed: missing block: B:69:0x00e2, code lost:
        r6 = r16.get(11);
     */
    /* JADX WARN: Code restructure failed: missing block: B:70:0x00e6, code lost:
        if (r6 < 12) goto L92;
     */
    /* JADX WARN: Code restructure failed: missing block: B:71:0x00e8, code lost:
        r6 = r6 - 12;
     */
    /* JADX WARN: Code restructure failed: missing block: B:72:0x00ea, code lost:
        r8 = r6;
     */
    /* JADX WARN: Code restructure failed: missing block: B:73:0x00ec, code lost:
        if (r8 < 6) goto L71;
     */
    /* JADX WARN: Code restructure failed: missing block: B:74:0x00ee, code lost:
        r6 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:75:0x00f0, code lost:
        r6 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:76:0x00f1, code lost:
        r7 = r6;
        r6 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:77:0x00f4, code lost:
        r7 = r6;
        r6 = false;
        r8 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:78:0x00f7, code lost:
        if (r6 != false) goto L84;
     */
    /* JADX WARN: Code restructure failed: missing block: B:79:0x00f9, code lost:
        r6 = r16.getActualMinimum(org.apache.commons.lang.time.DateUtils.fields[r5][0]);
        r8 = r16.getActualMaximum(org.apache.commons.lang.time.DateUtils.fields[r5][0]);
        r7 = r16.get(org.apache.commons.lang.time.DateUtils.fields[r5][0]) - r6;
     */
    /* JADX WARN: Code restructure failed: missing block: B:80:0x011b, code lost:
        if (r7 <= ((r8 - r6) / 2)) goto L83;
     */
    /* JADX WARN: Code restructure failed: missing block: B:81:0x011d, code lost:
        r6 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:82:0x011f, code lost:
        r6 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:83:0x0121, code lost:
        r6 = r7;
        r7 = r8;
     */
    /* JADX WARN: Code restructure failed: missing block: B:84:0x0123, code lost:
        if (r7 == 0) goto L81;
     */
    /* JADX WARN: Code restructure failed: missing block: B:85:0x0125, code lost:
        r8 = org.apache.commons.lang.time.DateUtils.fields;
        r16.set(r8[r5][0], r16.get(r8[r5][0]) - r7);
     */
    /* JADX WARN: Code restructure failed: missing block: B:87:0x013a, code lost:
        r5 = r5 + 1;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static void modify(java.util.Calendar r16, int r17, int r18) {
        /*
            Method dump skipped, instructions count: 354
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.lang.time.DateUtils.modify(java.util.Calendar, int, int):void");
    }

    public static Date parseDate(String str, String[] strArr) throws ParseException {
        return parseDateWithLeniency(str, strArr, true);
    }

    public static Date parseDateStrictly(String str, String[] strArr) throws ParseException {
        return parseDateWithLeniency(str, strArr, false);
    }

    private static Date parseDateWithLeniency(String str, String[] strArr, boolean z) throws ParseException {
        String str2;
        if (str != null && strArr != null) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
            simpleDateFormat.setLenient(z);
            ParsePosition parsePosition = new ParsePosition(0);
            for (int i = 0; i < strArr.length; i++) {
                String str3 = strArr[i];
                if (strArr[i].endsWith("ZZ")) {
                    str3 = GeneratedOutlineSupport1.outline50(str3, -1, 0);
                }
                simpleDateFormat.applyPattern(str3);
                parsePosition.setIndex(0);
                if (strArr[i].endsWith("ZZ")) {
                    int indexOfSignChars = indexOfSignChars(str, 0);
                    str2 = str;
                    while (indexOfSignChars >= 0) {
                        str2 = reformatTimezone(str2, indexOfSignChars);
                        indexOfSignChars = indexOfSignChars(str2, indexOfSignChars + 1);
                    }
                } else {
                    str2 = str;
                }
                Date parse = simpleDateFormat.parse(str2, parsePosition);
                if (parse != null && parsePosition.getIndex() == str2.length()) {
                    return parse;
                }
            }
            throw new ParseException(GeneratedOutlineSupport1.outline71("Unable to parse the date: ", str), -1);
        }
        throw new IllegalArgumentException("Date and Patterns must not be null");
    }

    private static String reformatTimezone(String str, int i) {
        int i2;
        if (i < 0 || (i2 = i + 5) >= str.length() || !Character.isDigit(str.charAt(i + 1)) || !Character.isDigit(str.charAt(i + 2))) {
            return str;
        }
        int i3 = i + 3;
        if (str.charAt(i3) != ':') {
            return str;
        }
        int i4 = i + 4;
        if (!Character.isDigit(str.charAt(i4)) || !Character.isDigit(str.charAt(i2))) {
            return str;
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(str.substring(0, i3));
        stringBuffer.append(str.substring(i4));
        return stringBuffer.toString();
    }

    public static Date round(Date date, int i) {
        if (date != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            modify(calendar, i, 1);
            return calendar.getTime();
        }
        throw new IllegalArgumentException("The date must not be null");
    }

    private static Date set(Date date, int i, int i2) {
        if (date != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.setLenient(false);
            calendar.setTime(date);
            calendar.set(i, i2);
            return calendar.getTime();
        }
        throw new IllegalArgumentException("The date must not be null");
    }

    public static Date setDays(Date date, int i) {
        return set(date, 5, i);
    }

    public static Date setHours(Date date, int i) {
        return set(date, 11, i);
    }

    public static Date setMilliseconds(Date date, int i) {
        return set(date, 14, i);
    }

    public static Date setMinutes(Date date, int i) {
        return set(date, 12, i);
    }

    public static Date setMonths(Date date, int i) {
        return set(date, 2, i);
    }

    public static Date setSeconds(Date date, int i) {
        return set(date, 13, i);
    }

    public static Date setYears(Date date, int i) {
        return set(date, 1, i);
    }

    public static Calendar toCalendar(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }

    public static Date truncate(Date date, int i) {
        if (date != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            modify(calendar, i, 0);
            return calendar.getTime();
        }
        throw new IllegalArgumentException("The date must not be null");
    }

    public static int truncatedCompareTo(Calendar calendar, Calendar calendar2, int i) {
        return truncate(calendar, i).getTime().compareTo(truncate(calendar2, i).getTime());
    }

    public static boolean truncatedEquals(Calendar calendar, Calendar calendar2, int i) {
        return truncatedCompareTo(calendar, calendar2, i) == 0;
    }

    public static long getFragmentInDays(Calendar calendar, int i) {
        return getFragment(calendar, i, 6);
    }

    public static long getFragmentInHours(Calendar calendar, int i) {
        return getFragment(calendar, i, 11);
    }

    public static long getFragmentInMilliseconds(Calendar calendar, int i) {
        return getFragment(calendar, i, 14);
    }

    public static long getFragmentInMinutes(Calendar calendar, int i) {
        return getFragment(calendar, i, 12);
    }

    public static long getFragmentInSeconds(Calendar calendar, int i) {
        return getFragment(calendar, i, 13);
    }

    public static boolean truncatedEquals(Date date, Date date2, int i) {
        return truncatedCompareTo(date, date2, i) == 0;
    }

    public static boolean isSameInstant(Calendar calendar, Calendar calendar2) {
        if (calendar == null || calendar2 == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        return calendar.getTime().getTime() == calendar2.getTime().getTime();
    }

    public static int truncatedCompareTo(Date date, Date date2, int i) {
        return truncate(date, i).compareTo(truncate(date2, i));
    }

    private static long getFragment(Calendar calendar, int i, int i2) {
        long j;
        if (calendar != null) {
            long millisPerUnit = getMillisPerUnit(i2);
            long j2 = 0;
            if (i == 1) {
                j = (calendar.get(6) * 86400000) / millisPerUnit;
            } else {
                if (i == 2) {
                    j = (calendar.get(5) * 86400000) / millisPerUnit;
                }
                if (i != 1 || i == 2 || i == 5 || i == 6) {
                    j2 += (calendar.get(11) * 3600000) / millisPerUnit;
                } else {
                    switch (i) {
                        case 11:
                            break;
                        case 12:
                            j2 += (calendar.get(13) * 1000) / millisPerUnit;
                            break;
                        case 13:
                            break;
                        case 14:
                            return j2;
                        default:
                            StringBuffer stringBuffer = new StringBuffer();
                            stringBuffer.append("The fragment ");
                            stringBuffer.append(i);
                            stringBuffer.append(" is not supported");
                            throw new IllegalArgumentException(stringBuffer.toString());
                    }
                    return j2 + ((calendar.get(14) * 1) / millisPerUnit);
                }
                j2 += (calendar.get(12) * 60000) / millisPerUnit;
                j2 += (calendar.get(13) * 1000) / millisPerUnit;
                return j2 + ((calendar.get(14) * 1) / millisPerUnit);
            }
            j2 = 0 + j;
            if (i != 1) {
            }
            j2 += (calendar.get(11) * 3600000) / millisPerUnit;
            j2 += (calendar.get(12) * 60000) / millisPerUnit;
            j2 += (calendar.get(13) * 1000) / millisPerUnit;
            return j2 + ((calendar.get(14) * 1) / millisPerUnit);
        }
        throw new IllegalArgumentException("The date must not be null");
    }

    public static Iterator iterator(Calendar calendar, int i) {
        Calendar truncate;
        Calendar truncate2;
        int i2;
        if (calendar != null) {
            int i3 = 2;
            switch (i) {
                case 1:
                case 2:
                case 3:
                case 4:
                    truncate = truncate(calendar, 5);
                    truncate2 = truncate(calendar, 5);
                    if (i != 1) {
                        if (i != 2) {
                            if (i == 3) {
                                i3 = calendar.get(7);
                                i2 = i3 - 1;
                                break;
                            } else if (i == 4) {
                                i2 = calendar.get(7) + 3;
                                i3 = calendar.get(7) - 3;
                                break;
                            }
                        }
                        i2 = 1;
                        break;
                    }
                    i3 = 1;
                    i2 = 7;
                    break;
                case 5:
                case 6:
                    Calendar truncate3 = truncate(calendar, 2);
                    Calendar calendar2 = (Calendar) truncate3.clone();
                    calendar2.add(2, 1);
                    calendar2.add(5, -1);
                    if (i != 6) {
                        i3 = 1;
                        truncate2 = calendar2;
                        truncate = truncate3;
                        i2 = 7;
                        break;
                    } else {
                        truncate2 = calendar2;
                        truncate = truncate3;
                        i2 = 1;
                        break;
                    }
                default:
                    StringBuffer stringBuffer = new StringBuffer();
                    stringBuffer.append("The range style ");
                    stringBuffer.append(i);
                    stringBuffer.append(" is not valid.");
                    throw new IllegalArgumentException(stringBuffer.toString());
            }
            if (i3 < 1) {
                i3 += 7;
            }
            if (i3 > 7) {
                i3 -= 7;
            }
            if (i2 < 1) {
                i2 += 7;
            }
            if (i2 > 7) {
                i2 -= 7;
            }
            while (truncate.get(7) != i3) {
                truncate.add(5, -1);
            }
            while (truncate2.get(7) != i2) {
                truncate2.add(5, 1);
            }
            return new DateIterator(truncate, truncate2);
        }
        throw new IllegalArgumentException("The date must not be null");
    }

    public static Calendar ceiling(Calendar calendar, int i) {
        if (calendar != null) {
            Calendar calendar2 = (Calendar) calendar.clone();
            modify(calendar2, i, 2);
            return calendar2;
        }
        throw new IllegalArgumentException("The date must not be null");
    }

    public static Calendar round(Calendar calendar, int i) {
        if (calendar != null) {
            Calendar calendar2 = (Calendar) calendar.clone();
            modify(calendar2, i, 1);
            return calendar2;
        }
        throw new IllegalArgumentException("The date must not be null");
    }

    public static Calendar truncate(Calendar calendar, int i) {
        if (calendar != null) {
            Calendar calendar2 = (Calendar) calendar.clone();
            modify(calendar2, i, 0);
            return calendar2;
        }
        throw new IllegalArgumentException("The date must not be null");
    }

    public static boolean isSameDay(Calendar calendar, Calendar calendar2) {
        if (calendar == null || calendar2 == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        return calendar.get(0) == calendar2.get(0) && calendar.get(1) == calendar2.get(1) && calendar.get(6) == calendar2.get(6);
    }

    public static Date ceiling(Object obj, int i) {
        if (obj != null) {
            if (obj instanceof Date) {
                return ceiling((Date) obj, i);
            }
            if (obj instanceof Calendar) {
                return ceiling((Calendar) obj, i).getTime();
            }
            StringBuffer outline103 = GeneratedOutlineSupport1.outline103("Could not find ceiling of for type: ");
            outline103.append(obj.getClass());
            throw new ClassCastException(outline103.toString());
        }
        throw new IllegalArgumentException("The date must not be null");
    }

    public static Date round(Object obj, int i) {
        if (obj != null) {
            if (obj instanceof Date) {
                return round((Date) obj, i);
            }
            if (obj instanceof Calendar) {
                return round((Calendar) obj, i).getTime();
            }
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Could not round ");
            stringBuffer.append(obj);
            throw new ClassCastException(stringBuffer.toString());
        }
        throw new IllegalArgumentException("The date must not be null");
    }

    public static Date truncate(Object obj, int i) {
        if (obj != null) {
            if (obj instanceof Date) {
                return truncate((Date) obj, i);
            }
            if (obj instanceof Calendar) {
                return truncate((Calendar) obj, i).getTime();
            }
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Could not truncate ");
            stringBuffer.append(obj);
            throw new ClassCastException(stringBuffer.toString());
        }
        throw new IllegalArgumentException("The date must not be null");
    }

    public static Iterator iterator(Object obj, int i) {
        if (obj != null) {
            if (obj instanceof Date) {
                return iterator((Date) obj, i);
            }
            if (obj instanceof Calendar) {
                return iterator((Calendar) obj, i);
            }
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Could not iterate based on ");
            stringBuffer.append(obj);
            throw new ClassCastException(stringBuffer.toString());
        }
        throw new IllegalArgumentException("The date must not be null");
    }
}
