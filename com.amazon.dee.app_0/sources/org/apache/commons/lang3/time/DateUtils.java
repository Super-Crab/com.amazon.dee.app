package org.apache.commons.lang3.time;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
/* loaded from: classes4.dex */
public class DateUtils {
    public static final long MILLIS_PER_DAY = 86400000;
    public static final long MILLIS_PER_HOUR = 3600000;
    public static final long MILLIS_PER_MINUTE = 60000;
    public static final long MILLIS_PER_SECOND = 1000;
    public static final int RANGE_MONTH_MONDAY = 6;
    public static final int RANGE_MONTH_SUNDAY = 5;
    public static final int RANGE_WEEK_CENTER = 4;
    public static final int RANGE_WEEK_MONDAY = 2;
    public static final int RANGE_WEEK_RELATIVE = 3;
    public static final int RANGE_WEEK_SUNDAY = 1;
    public static final int SEMI_MONTH = 1001;
    private static final int[][] fields = {new int[]{14}, new int[]{13}, new int[]{12}, new int[]{11, 10}, new int[]{5, 5, 9}, new int[]{2, 1001}, new int[]{1}, new int[]{0}};

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public static class DateIterator implements Iterator<Calendar> {
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
        public void remove() {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Iterator
        public Calendar next() {
            if (!this.spot.equals(this.endFinal)) {
                this.spot.add(5, 1);
                return (Calendar) this.spot.clone();
            }
            throw new NoSuchElementException();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes4.dex */
    public enum ModifyType {
        TRUNCATE,
        ROUND,
        CEILING
    }

    private static Date add(Date date, int i, int i2) {
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
            modify(calendar, i, ModifyType.CEILING);
            return calendar.getTime();
        }
        throw new IllegalArgumentException("The date must not be null");
    }

    private static long getFragment(Date date, int i, TimeUnit timeUnit) {
        if (date != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            return getFragment(calendar, i, timeUnit);
        }
        throw new IllegalArgumentException("The date must not be null");
    }

    public static long getFragmentInDays(Date date, int i) {
        return getFragment(date, i, TimeUnit.DAYS);
    }

    public static long getFragmentInHours(Date date, int i) {
        return getFragment(date, i, TimeUnit.HOURS);
    }

    public static long getFragmentInMilliseconds(Date date, int i) {
        return getFragment(date, i, TimeUnit.MILLISECONDS);
    }

    public static long getFragmentInMinutes(Date date, int i) {
        return getFragment(date, i, TimeUnit.MINUTES);
    }

    public static long getFragmentInSeconds(Date date, int i) {
        return getFragment(date, i, TimeUnit.SECONDS);
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
        return calendar.get(14) == calendar2.get(14) && calendar.get(13) == calendar2.get(13) && calendar.get(12) == calendar2.get(12) && calendar.get(11) == calendar2.get(11) && calendar.get(6) == calendar2.get(6) && calendar.get(1) == calendar2.get(1) && calendar.get(0) == calendar2.get(0) && calendar.getClass() == calendar2.getClass();
    }

    public static Iterator<Calendar> iterator(Date date, int i) {
        if (date != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            return iterator(calendar, i);
        }
        throw new IllegalArgumentException("The date must not be null");
    }

    /* JADX WARN: Code restructure failed: missing block: B:70:0x00db, code lost:
        if (r7 > 7) goto L90;
     */
    /* JADX WARN: Code restructure failed: missing block: B:78:0x00ef, code lost:
        if (r7 >= 6) goto L90;
     */
    /* JADX WARN: Code restructure failed: missing block: B:79:0x00f1, code lost:
        r9 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:80:0x00f3, code lost:
        r9 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:81:0x00f4, code lost:
        r10 = r9;
        r9 = true;
     */
    /* JADX WARN: Removed duplicated region for block: B:100:0x012e A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:84:0x0100  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x011f  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x0122  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static void modify(java.util.Calendar r17, int r18, org.apache.commons.lang3.time.DateUtils.ModifyType r19) {
        /*
            Method dump skipped, instructions count: 329
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.lang3.time.DateUtils.modify(java.util.Calendar, int, org.apache.commons.lang3.time.DateUtils$ModifyType):void");
    }

    public static Date parseDate(String str, String... strArr) throws ParseException {
        return parseDate(str, null, strArr);
    }

    public static Date parseDateStrictly(String str, String... strArr) throws ParseException {
        return parseDateStrictly(str, null, strArr);
    }

    private static Date parseDateWithLeniency(String str, Locale locale, String[] strArr, boolean z) throws ParseException {
        SimpleDateFormat simpleDateFormat;
        if (str != null && strArr != null) {
            if (locale == null) {
                simpleDateFormat = new SimpleDateFormat();
            } else {
                simpleDateFormat = new SimpleDateFormat("", locale);
            }
            simpleDateFormat.setLenient(z);
            ParsePosition parsePosition = new ParsePosition(0);
            for (String str2 : strArr) {
                simpleDateFormat.applyPattern(str2.endsWith("ZZ") ? GeneratedOutlineSupport1.outline50(str2, -1, 0) : str2);
                parsePosition.setIndex(0);
                String replaceAll = str2.endsWith("ZZ") ? str.replaceAll("([-+][0-9][0-9]):([0-9][0-9])$", "$1$2") : str;
                Date parse = simpleDateFormat.parse(replaceAll, parsePosition);
                if (parse != null && parsePosition.getIndex() == replaceAll.length()) {
                    return parse;
                }
            }
            throw new ParseException(GeneratedOutlineSupport1.outline72("Unable to parse the date: ", str), -1);
        }
        throw new IllegalArgumentException("Date and Patterns must not be null");
    }

    public static Date round(Date date, int i) {
        if (date != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            modify(calendar, i, ModifyType.ROUND);
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
            modify(calendar, i, ModifyType.TRUNCATE);
            return calendar.getTime();
        }
        throw new IllegalArgumentException("The date must not be null");
    }

    public static int truncatedCompareTo(Calendar calendar, Calendar calendar2, int i) {
        return truncate(calendar, i).compareTo(truncate(calendar2, i));
    }

    public static boolean truncatedEquals(Calendar calendar, Calendar calendar2, int i) {
        return truncatedCompareTo(calendar, calendar2, i) == 0;
    }

    public static long getFragmentInDays(Calendar calendar, int i) {
        return getFragment(calendar, i, TimeUnit.DAYS);
    }

    public static long getFragmentInHours(Calendar calendar, int i) {
        return getFragment(calendar, i, TimeUnit.HOURS);
    }

    public static long getFragmentInMilliseconds(Calendar calendar, int i) {
        return getFragment(calendar, i, TimeUnit.MILLISECONDS);
    }

    public static long getFragmentInMinutes(Calendar calendar, int i) {
        return getFragment(calendar, i, TimeUnit.MINUTES);
    }

    public static long getFragmentInSeconds(Calendar calendar, int i) {
        return getFragment(calendar, i, TimeUnit.SECONDS);
    }

    public static Date parseDate(String str, Locale locale, String... strArr) throws ParseException {
        return parseDateWithLeniency(str, locale, strArr, true);
    }

    public static Date parseDateStrictly(String str, Locale locale, String... strArr) throws ParseException {
        return parseDateWithLeniency(str, null, strArr, false);
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

    private static long getFragment(Calendar calendar, int i, TimeUnit timeUnit) {
        long convert;
        if (calendar != null) {
            long j = 0;
            int i2 = timeUnit == TimeUnit.DAYS ? 0 : 1;
            if (i == 1) {
                convert = timeUnit.convert(calendar.get(6) - i2, TimeUnit.DAYS);
            } else {
                if (i == 2) {
                    convert = timeUnit.convert(calendar.get(5) - i2, TimeUnit.DAYS);
                }
                if (i != 1 || i == 2 || i == 5 || i == 6) {
                    j += timeUnit.convert(calendar.get(11), TimeUnit.HOURS);
                } else {
                    switch (i) {
                        case 11:
                            break;
                        case 12:
                            j += timeUnit.convert(calendar.get(13), TimeUnit.SECONDS);
                            break;
                        case 13:
                            break;
                        case 14:
                            return j;
                        default:
                            throw new IllegalArgumentException(GeneratedOutlineSupport1.outline52("The fragment ", i, " is not supported"));
                    }
                    return j + timeUnit.convert(calendar.get(14), TimeUnit.MILLISECONDS);
                }
                j += timeUnit.convert(calendar.get(12), TimeUnit.MINUTES);
                j += timeUnit.convert(calendar.get(13), TimeUnit.SECONDS);
                return j + timeUnit.convert(calendar.get(14), TimeUnit.MILLISECONDS);
            }
            j = 0 + convert;
            if (i != 1) {
            }
            j += timeUnit.convert(calendar.get(11), TimeUnit.HOURS);
            j += timeUnit.convert(calendar.get(12), TimeUnit.MINUTES);
            j += timeUnit.convert(calendar.get(13), TimeUnit.SECONDS);
            return j + timeUnit.convert(calendar.get(14), TimeUnit.MILLISECONDS);
        }
        throw new IllegalArgumentException("The date must not be null");
    }

    public static Iterator<Calendar> iterator(Calendar calendar, int i) {
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
                        truncate2 = calendar2;
                        truncate = truncate3;
                        i3 = 1;
                        i2 = 7;
                        break;
                    } else {
                        truncate2 = calendar2;
                        truncate = truncate3;
                        i2 = 1;
                        break;
                    }
                default:
                    throw new IllegalArgumentException(GeneratedOutlineSupport1.outline52("The range style ", i, " is not valid."));
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
            modify(calendar2, i, ModifyType.CEILING);
            return calendar2;
        }
        throw new IllegalArgumentException("The date must not be null");
    }

    public static Calendar round(Calendar calendar, int i) {
        if (calendar != null) {
            Calendar calendar2 = (Calendar) calendar.clone();
            modify(calendar2, i, ModifyType.ROUND);
            return calendar2;
        }
        throw new IllegalArgumentException("The date must not be null");
    }

    public static Calendar truncate(Calendar calendar, int i) {
        if (calendar != null) {
            Calendar calendar2 = (Calendar) calendar.clone();
            modify(calendar2, i, ModifyType.TRUNCATE);
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
            throw new ClassCastException(GeneratedOutlineSupport1.outline44(obj, GeneratedOutlineSupport1.outline107("Could not find ceiling of for type: ")));
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
            throw new ClassCastException(GeneratedOutlineSupport1.outline70("Could not round ", obj));
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
            throw new ClassCastException(GeneratedOutlineSupport1.outline70("Could not truncate ", obj));
        }
        throw new IllegalArgumentException("The date must not be null");
    }

    public static Iterator<?> iterator(Object obj, int i) {
        if (obj != null) {
            if (obj instanceof Date) {
                return iterator((Date) obj, i);
            }
            if (obj instanceof Calendar) {
                return iterator((Calendar) obj, i);
            }
            throw new ClassCastException(GeneratedOutlineSupport1.outline70("Could not iterate based on ", obj));
        }
        throw new IllegalArgumentException("The date must not be null");
    }
}
