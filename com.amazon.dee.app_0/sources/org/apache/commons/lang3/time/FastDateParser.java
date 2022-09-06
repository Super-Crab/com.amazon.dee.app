package org.apache.commons.lang3.time;

import com.amazon.dee.sdk.iotsoftap.Constants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.ParsePosition;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.SortedMap;
import java.util.TimeZone;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.logging.log4j.util.Chars;
/* loaded from: classes4.dex */
public class FastDateParser implements DateParser, Serializable {
    private static final long serialVersionUID = 2;
    private final int century;
    private transient String currentFormatField;
    private final Locale locale;
    private transient Strategy nextStrategy;
    private transient Pattern parsePattern;
    private final String pattern;
    private final int startYear;
    private transient Strategy[] strategies;
    private final TimeZone timeZone;
    static final Locale JAPANESE_IMPERIAL = new Locale("ja", "JP", "JP");
    private static final Pattern formatPattern = Pattern.compile("D+|E+|F+|G+|H+|K+|M+|S+|W+|X+|Z+|a+|d+|h+|k+|m+|s+|w+|y+|z+|''|'[^']++(''[^']*+)*+'|[^'A-Za-z]++");
    private static final ConcurrentMap<Locale, Strategy>[] caches = new ConcurrentMap[17];
    private static final Strategy ABBREVIATED_YEAR_STRATEGY = new NumberStrategy(1) { // from class: org.apache.commons.lang3.time.FastDateParser.1
        @Override // org.apache.commons.lang3.time.FastDateParser.NumberStrategy, org.apache.commons.lang3.time.FastDateParser.Strategy
        void setCalendar(FastDateParser fastDateParser, Calendar calendar, String str) {
            int parseInt = Integer.parseInt(str);
            if (parseInt < 100) {
                parseInt = fastDateParser.adjustYear(parseInt);
            }
            calendar.set(1, parseInt);
        }
    };
    private static final Strategy NUMBER_MONTH_STRATEGY = new NumberStrategy(2) { // from class: org.apache.commons.lang3.time.FastDateParser.2
        @Override // org.apache.commons.lang3.time.FastDateParser.NumberStrategy
        int modify(int i) {
            return i - 1;
        }
    };
    private static final Strategy LITERAL_YEAR_STRATEGY = new NumberStrategy(1);
    private static final Strategy WEEK_OF_YEAR_STRATEGY = new NumberStrategy(3);
    private static final Strategy WEEK_OF_MONTH_STRATEGY = new NumberStrategy(4);
    private static final Strategy DAY_OF_YEAR_STRATEGY = new NumberStrategy(6);
    private static final Strategy DAY_OF_MONTH_STRATEGY = new NumberStrategy(5);
    private static final Strategy DAY_OF_WEEK_IN_MONTH_STRATEGY = new NumberStrategy(8);
    private static final Strategy HOUR_OF_DAY_STRATEGY = new NumberStrategy(11);
    private static final Strategy HOUR24_OF_DAY_STRATEGY = new NumberStrategy(11) { // from class: org.apache.commons.lang3.time.FastDateParser.3
        @Override // org.apache.commons.lang3.time.FastDateParser.NumberStrategy
        int modify(int i) {
            if (i == 24) {
                return 0;
            }
            return i;
        }
    };
    private static final Strategy HOUR12_STRATEGY = new NumberStrategy(10) { // from class: org.apache.commons.lang3.time.FastDateParser.4
        @Override // org.apache.commons.lang3.time.FastDateParser.NumberStrategy
        int modify(int i) {
            if (i == 12) {
                return 0;
            }
            return i;
        }
    };
    private static final Strategy HOUR_STRATEGY = new NumberStrategy(10);
    private static final Strategy MINUTE_STRATEGY = new NumberStrategy(12);
    private static final Strategy SECOND_STRATEGY = new NumberStrategy(13);
    private static final Strategy MILLISECOND_STRATEGY = new NumberStrategy(14);
    private static final Strategy ISO_8601_STRATEGY = new ISO8601TimeZoneStrategy("(Z|(?:[+-]\\d{2}(?::?\\d{2})?))");

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes4.dex */
    public static class CaseInsensitiveTextStrategy extends Strategy {
        private final int field;
        private final Map<String, Integer> lKeyValues;
        private final Locale locale;

        /* JADX WARN: Multi-variable type inference failed */
        CaseInsensitiveTextStrategy(int i, Calendar calendar, Locale locale) {
            super();
            this.field = i;
            this.locale = locale;
            Map displayNames = FastDateParser.getDisplayNames(i, calendar, locale);
            this.lKeyValues = new HashMap();
            for (Map.Entry entry : displayNames.entrySet()) {
                this.lKeyValues.put(((String) entry.getKey()).toLowerCase(locale), entry.getValue());
            }
        }

        @Override // org.apache.commons.lang3.time.FastDateParser.Strategy
        boolean addRegex(FastDateParser fastDateParser, StringBuilder sb) {
            sb.append("((?iu)");
            for (String str : this.lKeyValues.keySet()) {
                FastDateParser.escapeRegex(sb, str, false).append('|');
            }
            sb.setCharAt(sb.length() - 1, ')');
            return true;
        }

        @Override // org.apache.commons.lang3.time.FastDateParser.Strategy
        void setCalendar(FastDateParser fastDateParser, Calendar calendar, String str) {
            Integer num = this.lKeyValues.get(str.toLowerCase(this.locale));
            if (num == null) {
                StringBuilder outline112 = GeneratedOutlineSupport1.outline112(str, " not in (");
                for (String str2 : this.lKeyValues.keySet()) {
                    outline112.append(str2);
                    outline112.append(Chars.SPACE);
                }
                outline112.setCharAt(outline112.length() - 1, ')');
                throw new IllegalArgumentException(outline112.toString());
            }
            calendar.set(this.field, num.intValue());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes4.dex */
    public static class CopyQuotedStrategy extends Strategy {
        private final String formatField;

        CopyQuotedStrategy(String str) {
            super();
            this.formatField = str;
        }

        @Override // org.apache.commons.lang3.time.FastDateParser.Strategy
        boolean addRegex(FastDateParser fastDateParser, StringBuilder sb) {
            FastDateParser.escapeRegex(sb, this.formatField, true);
            return false;
        }

        @Override // org.apache.commons.lang3.time.FastDateParser.Strategy
        boolean isNumber() {
            char charAt = this.formatField.charAt(0);
            if (charAt == '\'') {
                charAt = this.formatField.charAt(1);
            }
            return Character.isDigit(charAt);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes4.dex */
    public static class ISO8601TimeZoneStrategy extends Strategy {
        private static final Strategy ISO_8601_1_STRATEGY = new ISO8601TimeZoneStrategy("(Z|(?:[+-]\\d{2}))");
        private static final Strategy ISO_8601_2_STRATEGY = new ISO8601TimeZoneStrategy("(Z|(?:[+-]\\d{2}\\d{2}))");
        private static final Strategy ISO_8601_3_STRATEGY = new ISO8601TimeZoneStrategy("(Z|(?:[+-]\\d{2}(?::)\\d{2}))");
        private final String pattern;

        ISO8601TimeZoneStrategy(String str) {
            super();
            this.pattern = str;
        }

        static Strategy getStrategy(int i) {
            if (i != 1) {
                if (i == 2) {
                    return ISO_8601_2_STRATEGY;
                }
                if (i == 3) {
                    return ISO_8601_3_STRATEGY;
                }
                throw new IllegalArgumentException("invalid number of X");
            }
            return ISO_8601_1_STRATEGY;
        }

        @Override // org.apache.commons.lang3.time.FastDateParser.Strategy
        boolean addRegex(FastDateParser fastDateParser, StringBuilder sb) {
            sb.append(this.pattern);
            return true;
        }

        @Override // org.apache.commons.lang3.time.FastDateParser.Strategy
        void setCalendar(FastDateParser fastDateParser, Calendar calendar, String str) {
            if (str.equals("Z")) {
                calendar.setTimeZone(TimeZone.getTimeZone(Constants.UTC));
                return;
            }
            calendar.setTimeZone(TimeZone.getTimeZone("GMT" + str));
        }
    }

    /* loaded from: classes4.dex */
    private static class NumberStrategy extends Strategy {
        private final int field;

        NumberStrategy(int i) {
            super();
            this.field = i;
        }

        @Override // org.apache.commons.lang3.time.FastDateParser.Strategy
        boolean addRegex(FastDateParser fastDateParser, StringBuilder sb) {
            if (fastDateParser.isNextNumber()) {
                sb.append("(\\p{Nd}{");
                sb.append(fastDateParser.getFieldWidth());
                sb.append("}+)");
                return true;
            }
            sb.append("(\\p{Nd}++)");
            return true;
        }

        @Override // org.apache.commons.lang3.time.FastDateParser.Strategy
        boolean isNumber() {
            return true;
        }

        int modify(int i) {
            return i;
        }

        @Override // org.apache.commons.lang3.time.FastDateParser.Strategy
        void setCalendar(FastDateParser fastDateParser, Calendar calendar, String str) {
            calendar.set(this.field, modify(Integer.parseInt(str)));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes4.dex */
    public static abstract class Strategy {
        private Strategy() {
        }

        abstract boolean addRegex(FastDateParser fastDateParser, StringBuilder sb);

        boolean isNumber() {
            return false;
        }

        void setCalendar(FastDateParser fastDateParser, Calendar calendar, String str) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes4.dex */
    public static class TimeZoneStrategy extends Strategy {
        private static final int ID = 0;
        private static final int LONG_DST = 3;
        private static final int LONG_STD = 1;
        private static final int SHORT_DST = 4;
        private static final int SHORT_STD = 2;
        private final SortedMap<String, TimeZone> tzNames;
        private final String validTimeZoneChars;

        TimeZoneStrategy(Locale locale) {
            super();
            String[][] zoneStrings;
            this.tzNames = new TreeMap(String.CASE_INSENSITIVE_ORDER);
            for (String[] strArr : DateFormatSymbols.getInstance(locale).getZoneStrings()) {
                if (!strArr[0].startsWith("GMT")) {
                    TimeZone timeZone = TimeZone.getTimeZone(strArr[0]);
                    if (!this.tzNames.containsKey(strArr[1])) {
                        this.tzNames.put(strArr[1], timeZone);
                    }
                    if (!this.tzNames.containsKey(strArr[2])) {
                        this.tzNames.put(strArr[2], timeZone);
                    }
                    if (timeZone.useDaylightTime()) {
                        if (!this.tzNames.containsKey(strArr[3])) {
                            this.tzNames.put(strArr[3], timeZone);
                        }
                        if (!this.tzNames.containsKey(strArr[4])) {
                            this.tzNames.put(strArr[4], timeZone);
                        }
                    }
                }
            }
            StringBuilder sb = new StringBuilder();
            sb.append("(GMT[+-]\\d{1,2}:\\d{2}");
            sb.append('|');
            sb.append("[+-]\\d{4}");
            sb.append('|');
            for (String str : this.tzNames.keySet()) {
                FastDateParser.escapeRegex(sb, str, false).append('|');
            }
            sb.setCharAt(sb.length() - 1, ')');
            this.validTimeZoneChars = sb.toString();
        }

        @Override // org.apache.commons.lang3.time.FastDateParser.Strategy
        boolean addRegex(FastDateParser fastDateParser, StringBuilder sb) {
            sb.append(this.validTimeZoneChars);
            return true;
        }

        @Override // org.apache.commons.lang3.time.FastDateParser.Strategy
        void setCalendar(FastDateParser fastDateParser, Calendar calendar, String str) {
            TimeZone timeZone;
            if (str.charAt(0) != '+' && str.charAt(0) != '-') {
                if (str.startsWith("GMT")) {
                    timeZone = TimeZone.getTimeZone(str);
                } else {
                    timeZone = this.tzNames.get(str);
                    if (timeZone == null) {
                        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline72(str, " is not a supported timezone name"));
                    }
                }
            } else {
                timeZone = TimeZone.getTimeZone("GMT" + str);
            }
            calendar.setTimeZone(timeZone);
        }
    }

    protected FastDateParser(String str, TimeZone timeZone, Locale locale) {
        this(str, timeZone, locale, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int adjustYear(int i) {
        int i2 = this.century + i;
        return i >= this.startYear ? i2 : i2 + 100;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static StringBuilder escapeRegex(StringBuilder sb, String str, boolean z) {
        sb.append("\\Q");
        int i = 0;
        while (i < str.length()) {
            char charAt = str.charAt(i);
            if (charAt != '\'') {
                if (charAt == '\\' && (i = i + 1) != str.length()) {
                    sb.append(charAt);
                    charAt = str.charAt(i);
                    if (charAt == 'E') {
                        sb.append("E\\\\E\\");
                        charAt = 'Q';
                    }
                }
            } else if (!z) {
                continue;
            } else {
                i++;
                if (i == str.length()) {
                    return sb;
                }
                charAt = str.charAt(i);
            }
            sb.append(charAt);
            i++;
        }
        sb.append("\\E");
        return sb;
    }

    private static ConcurrentMap<Locale, Strategy> getCache(int i) {
        ConcurrentMap<Locale, Strategy> concurrentMap;
        synchronized (caches) {
            if (caches[i] == null) {
                caches[i] = new ConcurrentHashMap(3);
            }
            concurrentMap = caches[i];
        }
        return concurrentMap;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Map<String, Integer> getDisplayNames(int i, Calendar calendar, Locale locale) {
        return calendar.getDisplayNames(i, 0, locale);
    }

    private Strategy getLocaleSpecificStrategy(int i, Calendar calendar) {
        ConcurrentMap<Locale, Strategy> cache = getCache(i);
        Strategy strategy = cache.get(this.locale);
        if (strategy == null) {
            strategy = i == 15 ? new TimeZoneStrategy(this.locale) : new CaseInsensitiveTextStrategy(i, calendar, this.locale);
            Strategy putIfAbsent = cache.putIfAbsent(this.locale, strategy);
            if (putIfAbsent != null) {
                return putIfAbsent;
            }
        }
        return strategy;
    }

    private Strategy getStrategy(String str, Calendar calendar) {
        char charAt = str.charAt(0);
        if (charAt != 'W') {
            if (charAt == 'X') {
                return ISO8601TimeZoneStrategy.getStrategy(str.length());
            }
            if (charAt == 'y') {
                return str.length() > 2 ? LITERAL_YEAR_STRATEGY : ABBREVIATED_YEAR_STRATEGY;
            }
            if (charAt != 'z') {
                switch (charAt) {
                    case '\'':
                        if (str.length() > 2) {
                            return new CopyQuotedStrategy(GeneratedOutlineSupport1.outline51(str, 1, 1));
                        }
                        break;
                    case 'K':
                        return HOUR_STRATEGY;
                    case 'M':
                        return str.length() >= 3 ? getLocaleSpecificStrategy(2, calendar) : NUMBER_MONTH_STRATEGY;
                    case 'S':
                        return MILLISECOND_STRATEGY;
                    case 'Z':
                        if (str.equals("ZZ")) {
                            return ISO_8601_STRATEGY;
                        }
                        break;
                    case 'a':
                        return getLocaleSpecificStrategy(9, calendar);
                    case 'd':
                        return DAY_OF_MONTH_STRATEGY;
                    case 'h':
                        return HOUR12_STRATEGY;
                    case 'k':
                        return HOUR24_OF_DAY_STRATEGY;
                    case 'm':
                        return MINUTE_STRATEGY;
                    case 's':
                        return SECOND_STRATEGY;
                    case 'w':
                        return WEEK_OF_YEAR_STRATEGY;
                    default:
                        switch (charAt) {
                            case 'D':
                                return DAY_OF_YEAR_STRATEGY;
                            case 'E':
                                return getLocaleSpecificStrategy(7, calendar);
                            case 'F':
                                return DAY_OF_WEEK_IN_MONTH_STRATEGY;
                            case 'G':
                                return getLocaleSpecificStrategy(0, calendar);
                            case 'H':
                                return HOUR_OF_DAY_STRATEGY;
                        }
                }
                return new CopyQuotedStrategy(str);
            }
            return getLocaleSpecificStrategy(15, calendar);
        }
        return WEEK_OF_MONTH_STRATEGY;
    }

    private void init(Calendar calendar) {
        StringBuilder sb = new StringBuilder();
        ArrayList arrayList = new ArrayList();
        Matcher matcher = formatPattern.matcher(this.pattern);
        if (matcher.lookingAt()) {
            this.currentFormatField = matcher.group();
            Strategy strategy = getStrategy(this.currentFormatField, calendar);
            while (true) {
                matcher.region(matcher.end(), matcher.regionEnd());
                if (!matcher.lookingAt()) {
                    break;
                }
                String group = matcher.group();
                this.nextStrategy = getStrategy(group, calendar);
                if (strategy.addRegex(this, sb)) {
                    arrayList.add(strategy);
                }
                this.currentFormatField = group;
                strategy = this.nextStrategy;
            }
            this.nextStrategy = null;
            if (matcher.regionStart() == matcher.regionEnd()) {
                if (strategy.addRegex(this, sb)) {
                    arrayList.add(strategy);
                }
                this.currentFormatField = null;
                this.strategies = (Strategy[]) arrayList.toArray(new Strategy[arrayList.size()]);
                this.parsePattern = Pattern.compile(sb.toString());
                return;
            }
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Failed to parse \"");
            outline107.append(this.pattern);
            outline107.append("\" ; gave up at index ");
            outline107.append(matcher.regionStart());
            throw new IllegalArgumentException(outline107.toString());
        }
        StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("Illegal pattern character '");
        outline1072.append(this.pattern.charAt(matcher.regionStart()));
        outline1072.append("'");
        throw new IllegalArgumentException(outline1072.toString());
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        init(Calendar.getInstance(this.timeZone, this.locale));
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof FastDateParser)) {
            return false;
        }
        FastDateParser fastDateParser = (FastDateParser) obj;
        return this.pattern.equals(fastDateParser.pattern) && this.timeZone.equals(fastDateParser.timeZone) && this.locale.equals(fastDateParser.locale);
    }

    int getFieldWidth() {
        return this.currentFormatField.length();
    }

    @Override // org.apache.commons.lang3.time.DateParser, org.apache.commons.lang3.time.DatePrinter
    public Locale getLocale() {
        return this.locale;
    }

    Pattern getParsePattern() {
        return this.parsePattern;
    }

    @Override // org.apache.commons.lang3.time.DateParser, org.apache.commons.lang3.time.DatePrinter
    public String getPattern() {
        return this.pattern;
    }

    @Override // org.apache.commons.lang3.time.DateParser, org.apache.commons.lang3.time.DatePrinter
    public TimeZone getTimeZone() {
        return this.timeZone;
    }

    public int hashCode() {
        return (((this.locale.hashCode() * 13) + this.timeZone.hashCode()) * 13) + this.pattern.hashCode();
    }

    boolean isNextNumber() {
        Strategy strategy = this.nextStrategy;
        return strategy != null && strategy.isNumber();
    }

    @Override // org.apache.commons.lang3.time.DateParser
    public Date parse(String str) throws ParseException {
        Date parse = parse(str, new ParsePosition(0));
        if (parse == null) {
            if (this.locale.equals(JAPANESE_IMPERIAL)) {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("(The ");
                outline107.append(this.locale);
                outline107.append(" locale does not support dates before 1868 AD)\nUnparseable date: \"");
                outline107.append(str);
                outline107.append("\" does not match ");
                outline107.append(this.parsePattern.pattern());
                throw new ParseException(outline107.toString(), 0);
            }
            StringBuilder outline115 = GeneratedOutlineSupport1.outline115("Unparseable date: \"", str, "\" does not match ");
            outline115.append(this.parsePattern.pattern());
            throw new ParseException(outline115.toString(), 0);
        }
        return parse;
    }

    @Override // org.apache.commons.lang3.time.DateParser
    public Object parseObject(String str) throws ParseException {
        return parse(str);
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("FastDateParser[");
        outline107.append(this.pattern);
        outline107.append(",");
        outline107.append(this.locale);
        outline107.append(",");
        outline107.append(this.timeZone.getID());
        outline107.append("]");
        return outline107.toString();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public FastDateParser(String str, TimeZone timeZone, Locale locale, Date date) {
        int i;
        this.pattern = str;
        this.timeZone = timeZone;
        this.locale = locale;
        Calendar calendar = Calendar.getInstance(timeZone, locale);
        if (date != null) {
            calendar.setTime(date);
            i = calendar.get(1);
        } else if (locale.equals(JAPANESE_IMPERIAL)) {
            i = 0;
        } else {
            calendar.setTime(new Date());
            i = calendar.get(1) - 80;
        }
        this.century = (i / 100) * 100;
        this.startYear = i - this.century;
        init(calendar);
    }

    @Override // org.apache.commons.lang3.time.DateParser
    public Object parseObject(String str, ParsePosition parsePosition) {
        return parse(str, parsePosition);
    }

    @Override // org.apache.commons.lang3.time.DateParser
    public Date parse(String str, ParsePosition parsePosition) {
        int index = parsePosition.getIndex();
        Matcher matcher = this.parsePattern.matcher(str.substring(index));
        if (!matcher.lookingAt()) {
            return null;
        }
        Calendar calendar = Calendar.getInstance(this.timeZone, this.locale);
        calendar.clear();
        int i = 0;
        while (true) {
            Strategy[] strategyArr = this.strategies;
            if (i < strategyArr.length) {
                int i2 = i + 1;
                strategyArr[i].setCalendar(this, calendar, matcher.group(i2));
                i = i2;
            } else {
                parsePosition.setIndex(matcher.end() + index);
                return calendar.getTime();
            }
        }
    }
}
