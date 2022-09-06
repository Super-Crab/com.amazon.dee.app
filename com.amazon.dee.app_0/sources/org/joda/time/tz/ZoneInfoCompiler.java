package org.joda.time.tz;

import com.amazon.communication.CommunicationErrorCodes;
import com.amazon.mobile.heremapsexplore.Constants.ReactProperties;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.common.net.HttpHeaders;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;
import org.apache.logging.log4j.util.ProcessIdUtil;
import org.joda.time.Chronology;
import org.joda.time.DateTimeField;
import org.joda.time.DateTimeZone;
import org.joda.time.LocalDate;
import org.joda.time.MutableDateTime;
import org.joda.time.chrono.ISOChronology;
import org.joda.time.chrono.LenientChronology;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;
/* loaded from: classes5.dex */
public class ZoneInfoCompiler {
    static Chronology cLenientISO;
    static DateTimeOfYear cStartOfYear;
    private Map<String, RuleSet> iRuleSets = new HashMap();
    private List<Zone> iZones = new ArrayList();
    private List<String> iGoodLinks = new ArrayList();
    private List<String> iBackLinks = new ArrayList();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes5.dex */
    public static class DateTimeOfYear {
        public final boolean iAdvanceDayOfWeek;
        public final int iDayOfMonth;
        public final int iDayOfWeek;
        public final int iMillisOfDay;
        public final int iMonthOfYear;
        public final char iZoneChar;

        DateTimeOfYear() {
            this.iMonthOfYear = 1;
            this.iDayOfMonth = 1;
            this.iDayOfWeek = 0;
            this.iAdvanceDayOfWeek = false;
            this.iMillisOfDay = 0;
            this.iZoneChar = 'w';
        }

        DateTimeOfYear(StringTokenizer stringTokenizer) {
            int i;
            int i2;
            int i3;
            int parseInt;
            boolean z;
            boolean z2 = false;
            int i4 = 0;
            z2 = false;
            int i5 = 1;
            char c = 'w';
            if (stringTokenizer.hasMoreTokens()) {
                i3 = ZoneInfoCompiler.parseMonth(stringTokenizer.nextToken());
                if (stringTokenizer.hasMoreTokens()) {
                    String nextToken = stringTokenizer.nextToken();
                    if (nextToken.startsWith("last")) {
                        i2 = ZoneInfoCompiler.parseDayOfWeek(nextToken.substring(4));
                        z = false;
                        parseInt = -1;
                    } else {
                        try {
                            z = false;
                            parseInt = Integer.parseInt(nextToken);
                            i2 = 0;
                        } catch (NumberFormatException unused) {
                            int indexOf = nextToken.indexOf(">=");
                            if (indexOf > 0) {
                                parseInt = Integer.parseInt(nextToken.substring(indexOf + 2));
                                i2 = ZoneInfoCompiler.parseDayOfWeek(nextToken.substring(0, indexOf));
                                z = true;
                            } else {
                                int indexOf2 = nextToken.indexOf("<=");
                                if (indexOf2 <= 0) {
                                    throw new IllegalArgumentException(nextToken);
                                }
                                parseInt = Integer.parseInt(nextToken.substring(indexOf2 + 2));
                                i2 = ZoneInfoCompiler.parseDayOfWeek(nextToken.substring(0, indexOf2));
                                z = false;
                            }
                        }
                    }
                    if (stringTokenizer.hasMoreTokens()) {
                        String nextToken2 = stringTokenizer.nextToken();
                        c = ZoneInfoCompiler.parseZoneChar(nextToken2.charAt(nextToken2.length() - 1));
                        if (nextToken2.equals("24:00")) {
                            if (i3 == 12 && parseInt == 31) {
                                nextToken2 = "23:59:59.999";
                            } else {
                                LocalDate plusMonths = parseInt == -1 ? new LocalDate(CommunicationErrorCodes.ERR_HANDLER_NOTHING_TO_DEREGISTER, i3, 1).plusMonths(1) : new LocalDate(CommunicationErrorCodes.ERR_HANDLER_NOTHING_TO_DEREGISTER, i3, parseInt).plusDays(1);
                                boolean z3 = (parseInt == -1 || i2 == 0) ? false : true;
                                int monthOfYear = plusMonths.getMonthOfYear();
                                int dayOfMonth = plusMonths.getDayOfMonth();
                                i2 = i2 != 0 ? (((i2 - 1) + 1) % 7) + 1 : i2;
                                i5 = dayOfMonth;
                                i = 0;
                                z2 = z3;
                                i3 = monthOfYear;
                            }
                        }
                        i4 = ZoneInfoCompiler.parseTime(nextToken2);
                    }
                    i = i4;
                    z2 = z;
                    i5 = parseInt;
                } else {
                    i = 0;
                    i2 = 0;
                }
            } else {
                i = 0;
                i2 = 0;
                i3 = 1;
            }
            this.iMonthOfYear = i3;
            this.iDayOfMonth = i5;
            this.iDayOfWeek = i2;
            this.iAdvanceDayOfWeek = z2;
            this.iMillisOfDay = i;
            this.iZoneChar = c;
        }

        public void addCutover(DateTimeZoneBuilder dateTimeZoneBuilder, int i) {
            dateTimeZoneBuilder.addCutover(i, this.iZoneChar, this.iMonthOfYear, this.iDayOfMonth, this.iDayOfWeek, this.iAdvanceDayOfWeek, this.iMillisOfDay);
        }

        public void addRecurring(DateTimeZoneBuilder dateTimeZoneBuilder, String str, int i, int i2, int i3) {
            dateTimeZoneBuilder.addRecurringSavings(str, i, i2, i3, this.iZoneChar, this.iMonthOfYear, this.iDayOfMonth, this.iDayOfWeek, this.iAdvanceDayOfWeek, this.iMillisOfDay);
        }

        public String toString() {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("MonthOfYear: ");
            outline107.append(this.iMonthOfYear);
            outline107.append("\nDayOfMonth: ");
            outline107.append(this.iDayOfMonth);
            outline107.append("\nDayOfWeek: ");
            outline107.append(this.iDayOfWeek);
            outline107.append("\nAdvanceDayOfWeek: ");
            outline107.append(this.iAdvanceDayOfWeek);
            outline107.append("\nMillisOfDay: ");
            outline107.append(this.iMillisOfDay);
            outline107.append("\nZoneChar: ");
            outline107.append(this.iZoneChar);
            outline107.append("\n");
            return outline107.toString();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes5.dex */
    public static class Rule {
        public final DateTimeOfYear iDateTimeOfYear;
        public final int iFromYear;
        public final String iLetterS;
        public final String iName;
        public final int iSaveMillis;
        public final int iToYear;
        public final String iType;

        Rule(StringTokenizer stringTokenizer) {
            this.iName = stringTokenizer.nextToken().intern();
            this.iFromYear = ZoneInfoCompiler.parseYear(stringTokenizer.nextToken(), 0);
            this.iToYear = ZoneInfoCompiler.parseYear(stringTokenizer.nextToken(), this.iFromYear);
            if (this.iToYear >= this.iFromYear) {
                this.iType = ZoneInfoCompiler.parseOptional(stringTokenizer.nextToken());
                this.iDateTimeOfYear = new DateTimeOfYear(stringTokenizer);
                this.iSaveMillis = ZoneInfoCompiler.parseTime(stringTokenizer.nextToken());
                this.iLetterS = ZoneInfoCompiler.parseOptional(stringTokenizer.nextToken());
                return;
            }
            throw new IllegalArgumentException();
        }

        private String formatName(String str) {
            int indexOf = str.indexOf(47);
            if (indexOf > 0) {
                return (this.iSaveMillis == 0 ? str.substring(0, indexOf) : str.substring(indexOf + 1)).intern();
            }
            int indexOf2 = str.indexOf("%s");
            if (indexOf2 < 0) {
                return str;
            }
            String substring = str.substring(0, indexOf2);
            String substring2 = str.substring(indexOf2 + 2);
            return (this.iLetterS == null ? substring.concat(substring2) : GeneratedOutlineSupport1.outline91(GeneratedOutlineSupport1.outline107(substring), this.iLetterS, substring2)).intern();
        }

        public void addRecurring(DateTimeZoneBuilder dateTimeZoneBuilder, String str) {
            this.iDateTimeOfYear.addRecurring(dateTimeZoneBuilder, formatName(str), this.iSaveMillis, this.iFromYear, this.iToYear);
        }

        public String toString() {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("[Rule]\nName: ");
            outline107.append(this.iName);
            outline107.append("\nFromYear: ");
            outline107.append(this.iFromYear);
            outline107.append("\nToYear: ");
            outline107.append(this.iToYear);
            outline107.append("\nType: ");
            outline107.append(this.iType);
            outline107.append("\n");
            outline107.append(this.iDateTimeOfYear);
            outline107.append("SaveMillis: ");
            outline107.append(this.iSaveMillis);
            outline107.append("\nLetterS: ");
            return GeneratedOutlineSupport1.outline91(outline107, this.iLetterS, "\n");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes5.dex */
    public static class RuleSet {
        private List<Rule> iRules = new ArrayList();

        RuleSet(Rule rule) {
            this.iRules.add(rule);
        }

        public void addRecurring(DateTimeZoneBuilder dateTimeZoneBuilder, String str) {
            for (int i = 0; i < this.iRules.size(); i++) {
                this.iRules.get(i).addRecurring(dateTimeZoneBuilder, str);
            }
        }

        void addRule(Rule rule) {
            if (rule.iName.equals(this.iRules.get(0).iName)) {
                this.iRules.add(rule);
                return;
            }
            throw new IllegalArgumentException("Rule name mismatch");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes5.dex */
    public static class Zone {
        public final String iFormat;
        public final String iName;
        private Zone iNext;
        public final int iOffsetMillis;
        public final String iRules;
        public final DateTimeOfYear iUntilDateTimeOfYear;
        public final int iUntilYear;

        private Zone(String str, StringTokenizer stringTokenizer) {
            int i;
            this.iName = str.intern();
            this.iOffsetMillis = ZoneInfoCompiler.parseTime(stringTokenizer.nextToken());
            this.iRules = ZoneInfoCompiler.parseOptional(stringTokenizer.nextToken());
            this.iFormat = stringTokenizer.nextToken().intern();
            DateTimeOfYear startOfYear = ZoneInfoCompiler.getStartOfYear();
            if (stringTokenizer.hasMoreTokens()) {
                i = Integer.parseInt(stringTokenizer.nextToken());
                if (stringTokenizer.hasMoreTokens()) {
                    startOfYear = new DateTimeOfYear(stringTokenizer);
                }
            } else {
                i = Integer.MAX_VALUE;
            }
            this.iUntilYear = i;
            this.iUntilDateTimeOfYear = startOfYear;
        }

        Zone(StringTokenizer stringTokenizer) {
            this(stringTokenizer.nextToken(), stringTokenizer);
        }

        private static void addToBuilder(Zone zone, DateTimeZoneBuilder dateTimeZoneBuilder, Map<String, RuleSet> map) {
            while (zone != null) {
                dateTimeZoneBuilder.setStandardOffset(zone.iOffsetMillis);
                String str = zone.iRules;
                if (str == null) {
                    dateTimeZoneBuilder.setFixedSavings(zone.iFormat, 0);
                } else {
                    try {
                        dateTimeZoneBuilder.setFixedSavings(zone.iFormat, ZoneInfoCompiler.parseTime(str));
                    } catch (Exception unused) {
                        RuleSet ruleSet = map.get(zone.iRules);
                        if (ruleSet == null) {
                            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Rules not found: ");
                            outline107.append(zone.iRules);
                            throw new IllegalArgumentException(outline107.toString());
                        }
                        ruleSet.addRecurring(dateTimeZoneBuilder, zone.iFormat);
                    }
                }
                int i = zone.iUntilYear;
                if (i == Integer.MAX_VALUE) {
                    return;
                }
                zone.iUntilDateTimeOfYear.addCutover(dateTimeZoneBuilder, i);
                zone = zone.iNext;
            }
        }

        public void addToBuilder(DateTimeZoneBuilder dateTimeZoneBuilder, Map<String, RuleSet> map) {
            addToBuilder(this, dateTimeZoneBuilder, map);
        }

        void chain(StringTokenizer stringTokenizer) {
            Zone zone = this.iNext;
            if (zone != null) {
                zone.chain(stringTokenizer);
            } else {
                this.iNext = new Zone(this.iName, stringTokenizer);
            }
        }

        public String toString() {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("[Zone]\nName: ");
            outline107.append(this.iName);
            outline107.append("\nOffsetMillis: ");
            outline107.append(this.iOffsetMillis);
            outline107.append("\nRules: ");
            outline107.append(this.iRules);
            outline107.append("\nFormat: ");
            outline107.append(this.iFormat);
            outline107.append("\nUntilYear: ");
            outline107.append(this.iUntilYear);
            outline107.append("\n");
            outline107.append(this.iUntilDateTimeOfYear);
            String sb = outline107.toString();
            if (this.iNext == null) {
                return sb;
            }
            StringBuilder outline113 = GeneratedOutlineSupport1.outline113(sb, "...\n");
            outline113.append(this.iNext.toString());
            return outline113.toString();
        }
    }

    static Chronology getLenientISOChronology() {
        if (cLenientISO == null) {
            cLenientISO = LenientChronology.getInstance(ISOChronology.getInstanceUTC());
        }
        return cLenientISO;
    }

    static DateTimeOfYear getStartOfYear() {
        if (cStartOfYear == null) {
            cStartOfYear = new DateTimeOfYear();
        }
        return cStartOfYear;
    }

    /* JADX WARN: Code restructure failed: missing block: B:20:0x004e, code lost:
        if ("-?".equals(r9[r0]) == false) goto L29;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0050, code lost:
        printUsage();
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x0053, code lost:
        return;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void main(java.lang.String[] r9) throws java.lang.Exception {
        /*
            int r0 = r9.length
            if (r0 != 0) goto L7
            printUsage()
            return
        L7:
            r0 = 0
            r1 = 0
            r2 = r0
            r3 = r2
            r0 = r1
            r4 = r0
        Ld:
            int r5 = r9.length
            r6 = 1
            if (r0 >= r5) goto L58
            java.lang.String r5 = "-src"
            r7 = r9[r0]     // Catch: java.lang.IndexOutOfBoundsException -> L54
            boolean r5 = r5.equals(r7)     // Catch: java.lang.IndexOutOfBoundsException -> L54
            if (r5 == 0) goto L25
            java.io.File r2 = new java.io.File     // Catch: java.lang.IndexOutOfBoundsException -> L54
            int r0 = r0 + 1
            r5 = r9[r0]     // Catch: java.lang.IndexOutOfBoundsException -> L54
            r2.<init>(r5)     // Catch: java.lang.IndexOutOfBoundsException -> L54
            goto L44
        L25:
            java.lang.String r5 = "-dst"
            r7 = r9[r0]     // Catch: java.lang.IndexOutOfBoundsException -> L54
            boolean r5 = r5.equals(r7)     // Catch: java.lang.IndexOutOfBoundsException -> L54
            if (r5 == 0) goto L39
            java.io.File r3 = new java.io.File     // Catch: java.lang.IndexOutOfBoundsException -> L54
            int r0 = r0 + 1
            r5 = r9[r0]     // Catch: java.lang.IndexOutOfBoundsException -> L54
            r3.<init>(r5)     // Catch: java.lang.IndexOutOfBoundsException -> L54
            goto L44
        L39:
            java.lang.String r5 = "-verbose"
            r7 = r9[r0]     // Catch: java.lang.IndexOutOfBoundsException -> L54
            boolean r5 = r5.equals(r7)     // Catch: java.lang.IndexOutOfBoundsException -> L54
            if (r5 == 0) goto L46
            r4 = r6
        L44:
            int r0 = r0 + r6
            goto Ld
        L46:
            java.lang.String r5 = "-?"
            r7 = r9[r0]     // Catch: java.lang.IndexOutOfBoundsException -> L54
            boolean r5 = r5.equals(r7)     // Catch: java.lang.IndexOutOfBoundsException -> L54
            if (r5 == 0) goto L58
            printUsage()     // Catch: java.lang.IndexOutOfBoundsException -> L54
            return
        L54:
            printUsage()
            return
        L58:
            int r5 = r9.length
            if (r0 < r5) goto L5f
            printUsage()
            return
        L5f:
            int r5 = r9.length
            int r5 = r5 - r0
            java.io.File[] r5 = new java.io.File[r5]
        L63:
            int r7 = r9.length
            if (r0 >= r7) goto L7b
            java.io.File r7 = new java.io.File
            if (r2 != 0) goto L70
            r8 = r9[r0]
            r7.<init>(r8)
            goto L75
        L70:
            r8 = r9[r0]
            r7.<init>(r2, r8)
        L75:
            r5[r1] = r7
            int r0 = r0 + 1
            int r1 = r1 + r6
            goto L63
        L7b:
            org.joda.time.tz.ZoneInfoLogger.set(r4)
            org.joda.time.tz.ZoneInfoCompiler r9 = new org.joda.time.tz.ZoneInfoCompiler
            r9.<init>()
            r9.compile(r3, r5)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.joda.time.tz.ZoneInfoCompiler.main(java.lang.String[]):void");
    }

    static int parseDayOfWeek(String str) {
        DateTimeField dayOfWeek = ISOChronology.getInstanceUTC().dayOfWeek();
        return dayOfWeek.get(dayOfWeek.set(0L, str, Locale.ENGLISH));
    }

    static int parseMonth(String str) {
        DateTimeField monthOfYear = ISOChronology.getInstanceUTC().monthOfYear();
        return monthOfYear.get(monthOfYear.set(0L, str, Locale.ENGLISH));
    }

    static String parseOptional(String str) {
        if (str.equals(ProcessIdUtil.DEFAULT_PROCESSID)) {
            return null;
        }
        return str;
    }

    /* JADX WARN: Type inference failed for: r2v2, types: [boolean, int] */
    static int parseTime(String str) {
        DateTimeFormatter hourMinuteSecondFraction = ISODateTimeFormat.hourMinuteSecondFraction();
        MutableDateTime mutableDateTime = new MutableDateTime(0L, getLenientISOChronology());
        ?? startsWith = str.startsWith(ProcessIdUtil.DEFAULT_PROCESSID);
        if (hourMinuteSecondFraction.parseInto(mutableDateTime, str, startsWith == true ? 1 : 0) != (~startsWith)) {
            int millis = (int) mutableDateTime.getMillis();
            return startsWith == 1 ? -millis : millis;
        }
        throw new IllegalArgumentException(str);
    }

    static int parseYear(String str, int i) {
        String lowerCase = str.toLowerCase();
        if (lowerCase.equals("minimum") || lowerCase.equals(ReactProperties.GEOFENCE_MINIMUM_RANGE)) {
            return Integer.MIN_VALUE;
        }
        if (lowerCase.equals("maximum") || lowerCase.equals(ReactProperties.GEOFENCE_MAXIMUM_RANGE)) {
            return Integer.MAX_VALUE;
        }
        return lowerCase.equals("only") ? i : Integer.parseInt(lowerCase);
    }

    static char parseZoneChar(char c) {
        if (c != 'G') {
            if (c != 'S') {
                if (c != 'U' && c != 'Z' && c != 'g') {
                    if (c != 's') {
                        if (c != 'u' && c != 'z') {
                            return 'w';
                        }
                    }
                }
            }
            return 's';
        }
        return 'u';
    }

    private static void printUsage() {
        System.out.println("Usage: java org.joda.time.tz.ZoneInfoCompiler <options> <source files>");
        System.out.println("where possible options include:");
        System.out.println("  -src <directory>    Specify where to read source files");
        System.out.println("  -dst <directory>    Specify where to write generated files");
        System.out.println("  -verbose            Output verbosely (default false)");
    }

    /* JADX WARN: Code restructure failed: missing block: B:16:0x0073, code lost:
        r0.append(r15);
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x00c2, code lost:
        r5 = org.joda.time.chrono.ISOChronology.getInstanceUTC().year().set(0, 2050);
        r0 = org.joda.time.chrono.ISOChronology.getInstanceUTC().year().set(0, 1850);
        r14 = r9.size();
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x00de, code lost:
        r14 = r14 - 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x00e0, code lost:
        if (r14 < 0) goto L47;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x00e2, code lost:
        r2 = r15.previousTransition(r5);
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x00e8, code lost:
        if (r2 == r5) goto L45;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x00ec, code lost:
        if (r2 >= r0) goto L38;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x00ef, code lost:
        r5 = ((java.lang.Long) r9.get(r14)).longValue() - 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x00fe, code lost:
        if (r5 == r2) goto L40;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x0100, code lost:
        r14 = java.lang.System.out;
        r0 = com.android.tools.r8.GeneratedOutlineSupport1.outline107("*r* Error in ");
        r0.append(r15.getID());
        r0.append(" ");
        r0.append(new org.joda.time.DateTime(r2, org.joda.time.chrono.ISOChronology.getInstanceUTC()));
        r0.append(" != ");
        r15 = new org.joda.time.DateTime(r5, org.joda.time.chrono.ISOChronology.getInstanceUTC());
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x012e, code lost:
        r5 = r2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x0130, code lost:
        return true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:?, code lost:
        return true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:?, code lost:
        return true;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static boolean test(java.lang.String r14, org.joda.time.DateTimeZone r15) {
        /*
            Method dump skipped, instructions count: 306
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.joda.time.tz.ZoneInfoCompiler.test(java.lang.String, org.joda.time.DateTimeZone):boolean");
    }

    private void writeZone(File file, DateTimeZoneBuilder dateTimeZoneBuilder, DateTimeZone dateTimeZone) throws IOException {
        if (ZoneInfoLogger.verbose()) {
            PrintStream printStream = System.out;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Writing ");
            outline107.append(dateTimeZone.getID());
            printStream.println(outline107.toString());
        }
        File file2 = new File(file, dateTimeZone.getID());
        if (!file2.getParentFile().exists()) {
            file2.getParentFile().mkdirs();
        }
        FileOutputStream fileOutputStream = new FileOutputStream(file2);
        try {
            dateTimeZoneBuilder.writeTo(dateTimeZone.getID(), fileOutputStream);
            fileOutputStream.close();
            FileInputStream fileInputStream = new FileInputStream(file2);
            DateTimeZone readFrom = DateTimeZoneBuilder.readFrom(fileInputStream, dateTimeZone.getID());
            fileInputStream.close();
            if (dateTimeZone.equals(readFrom)) {
                return;
            }
            PrintStream printStream2 = System.out;
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("*e* Error in ");
            outline1072.append(dateTimeZone.getID());
            outline1072.append(": Didn't read properly from file");
            printStream2.println(outline1072.toString());
        } catch (Throwable th) {
            fileOutputStream.close();
            throw th;
        }
    }

    static void writeZoneInfoMap(DataOutputStream dataOutputStream, Map<String, DateTimeZone> map) throws IOException {
        HashMap hashMap = new HashMap(map.size());
        TreeMap treeMap = new TreeMap();
        short s = 0;
        for (Map.Entry<String, DateTimeZone> entry : map.entrySet()) {
            String key = entry.getKey();
            if (!hashMap.containsKey(key)) {
                Short valueOf = Short.valueOf(s);
                hashMap.put(key, valueOf);
                treeMap.put(valueOf, key);
                s = (short) (s + 1);
                if (s == 0) {
                    throw new InternalError("Too many time zone ids");
                }
            }
            String id = entry.getValue().getID();
            if (!hashMap.containsKey(id)) {
                Short valueOf2 = Short.valueOf(s);
                hashMap.put(id, valueOf2);
                treeMap.put(valueOf2, id);
                s = (short) (s + 1);
                if (s == 0) {
                    throw new InternalError("Too many time zone ids");
                }
            }
        }
        dataOutputStream.writeShort(treeMap.size());
        for (String str : treeMap.values()) {
            dataOutputStream.writeUTF(str);
        }
        dataOutputStream.writeShort(map.size());
        for (Map.Entry<String, DateTimeZone> entry2 : map.entrySet()) {
            dataOutputStream.writeShort(((Short) hashMap.get(entry2.getKey())).shortValue());
            dataOutputStream.writeShort(((Short) hashMap.get(entry2.getValue().getID())).shortValue());
        }
    }

    public Map<String, DateTimeZone> compile(File file, File[] fileArr) throws IOException {
        if (fileArr != null) {
            for (int i = 0; i < fileArr.length; i++) {
                BufferedReader bufferedReader = new BufferedReader(new FileReader(fileArr[i]));
                parseDataFile(bufferedReader, "backward".equals(fileArr[i].getName()));
                bufferedReader.close();
            }
        }
        if (file != null) {
            if (!file.exists() && !file.mkdirs()) {
                throw new IOException(GeneratedOutlineSupport1.outline63("Destination directory doesn't exist and cannot be created: ", file));
            }
            if (!file.isDirectory()) {
                throw new IOException(GeneratedOutlineSupport1.outline63("Destination is not a directory: ", file));
            }
        }
        TreeMap treeMap = new TreeMap();
        TreeMap treeMap2 = new TreeMap();
        System.out.println("Writing zoneinfo files");
        for (int i2 = 0; i2 < this.iZones.size(); i2++) {
            Zone zone = this.iZones.get(i2);
            DateTimeZoneBuilder dateTimeZoneBuilder = new DateTimeZoneBuilder();
            zone.addToBuilder(dateTimeZoneBuilder, this.iRuleSets);
            DateTimeZone dateTimeZone = dateTimeZoneBuilder.toDateTimeZone(zone.iName, true);
            if (test(dateTimeZone.getID(), dateTimeZone)) {
                treeMap.put(dateTimeZone.getID(), dateTimeZone);
                treeMap2.put(dateTimeZone.getID(), zone);
                if (file != null) {
                    writeZone(file, dateTimeZoneBuilder, dateTimeZone);
                }
            }
        }
        for (int i3 = 0; i3 < this.iGoodLinks.size(); i3 += 2) {
            String str = this.iGoodLinks.get(i3);
            String str2 = this.iGoodLinks.get(i3 + 1);
            Zone zone2 = (Zone) treeMap2.get(str);
            if (zone2 == null) {
                System.out.println(GeneratedOutlineSupport1.outline77("Cannot find source zone '", str, "' to link alias '", str2, "' to"));
            } else {
                DateTimeZoneBuilder dateTimeZoneBuilder2 = new DateTimeZoneBuilder();
                zone2.addToBuilder(dateTimeZoneBuilder2, this.iRuleSets);
                DateTimeZone dateTimeZone2 = dateTimeZoneBuilder2.toDateTimeZone(str2, true);
                if (test(dateTimeZone2.getID(), dateTimeZone2)) {
                    treeMap.put(dateTimeZone2.getID(), dateTimeZone2);
                    if (file != null) {
                        writeZone(file, dateTimeZoneBuilder2, dateTimeZone2);
                    }
                }
                treeMap.put(dateTimeZone2.getID(), dateTimeZone2);
                if (ZoneInfoLogger.verbose()) {
                    System.out.println(GeneratedOutlineSupport1.outline77("Good link: ", str2, " -> ", str, " revived"));
                }
            }
        }
        for (int i4 = 0; i4 < 2; i4++) {
            for (int i5 = 0; i5 < this.iBackLinks.size(); i5 += 2) {
                String str3 = this.iBackLinks.get(i5);
                String str4 = this.iBackLinks.get(i5 + 1);
                DateTimeZone dateTimeZone3 = (DateTimeZone) treeMap.get(str3);
                if (dateTimeZone3 != null) {
                    treeMap.put(str4, dateTimeZone3);
                    if (ZoneInfoLogger.verbose()) {
                        PrintStream printStream = System.out;
                        StringBuilder outline115 = GeneratedOutlineSupport1.outline115("Back link: ", str4, " -> ");
                        outline115.append(dateTimeZone3.getID());
                        printStream.println(outline115.toString());
                    }
                } else if (i4 > 0) {
                    System.out.println(GeneratedOutlineSupport1.outline77("Cannot find time zone '", str3, "' to link alias '", str4, "' to"));
                }
            }
        }
        if (file != null) {
            System.out.println("Writing ZoneInfoMap");
            File file2 = new File(file, "ZoneInfoMap");
            if (!file2.getParentFile().exists()) {
                file2.getParentFile().mkdirs();
            }
            DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream(file2));
            try {
                TreeMap treeMap3 = new TreeMap(String.CASE_INSENSITIVE_ORDER);
                treeMap3.putAll(treeMap);
                writeZoneInfoMap(dataOutputStream, treeMap3);
            } finally {
                dataOutputStream.close();
            }
        }
        return treeMap;
    }

    public void parseDataFile(BufferedReader bufferedReader, boolean z) throws IOException {
        Zone zone;
        List<String> list;
        loop0: while (true) {
            zone = null;
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    break loop0;
                }
                String trim = readLine.trim();
                if (trim.length() != 0 && trim.charAt(0) != '#') {
                    int indexOf = readLine.indexOf(35);
                    if (indexOf >= 0) {
                        readLine = readLine.substring(0, indexOf);
                    }
                    StringTokenizer stringTokenizer = new StringTokenizer(readLine, " \t");
                    if (!Character.isWhitespace(readLine.charAt(0)) || !stringTokenizer.hasMoreTokens()) {
                        if (zone != null) {
                            this.iZones.add(zone);
                        }
                        if (stringTokenizer.hasMoreTokens()) {
                            String nextToken = stringTokenizer.nextToken();
                            if (nextToken.equalsIgnoreCase("Rule")) {
                                Rule rule = new Rule(stringTokenizer);
                                RuleSet ruleSet = this.iRuleSets.get(rule.iName);
                                if (ruleSet == null) {
                                    this.iRuleSets.put(rule.iName, new RuleSet(rule));
                                } else {
                                    ruleSet.addRule(rule);
                                }
                            } else if (nextToken.equalsIgnoreCase("Zone")) {
                                zone = new Zone(stringTokenizer);
                            } else if (nextToken.equalsIgnoreCase(HttpHeaders.LINK)) {
                                String nextToken2 = stringTokenizer.nextToken();
                                String nextToken3 = stringTokenizer.nextToken();
                                if (z || nextToken3.equals("US/Pacific-New") || nextToken3.startsWith("Etc/") || nextToken3.equals("GMT")) {
                                    this.iBackLinks.add(nextToken2);
                                    list = this.iBackLinks;
                                } else {
                                    this.iGoodLinks.add(nextToken2);
                                    list = this.iGoodLinks;
                                }
                                list.add(nextToken3);
                            } else {
                                System.out.println("Unknown line: " + readLine);
                            }
                        }
                    } else if (zone != null) {
                        zone.chain(stringTokenizer);
                    }
                }
            }
        }
        if (zone != null) {
            this.iZones.add(zone);
        }
    }
}
