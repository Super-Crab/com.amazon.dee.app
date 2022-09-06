package org.threeten.bp.zone;

import androidx.exifinterface.media.ExifInterface;
import com.amazon.dee.sdk.iotsoftap.Constants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.common.net.HttpHeaders;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.text.ParsePosition;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.jar.JarOutputStream;
import java.util.zip.ZipEntry;
import kotlinx.serialization.json.internal.JsonReaderKt;
import org.apache.logging.log4j.util.ProcessIdUtil;
import org.joda.time.DateTimeConstants;
import org.threeten.bp.DayOfWeek;
import org.threeten.bp.LocalDate;
import org.threeten.bp.LocalDateTime;
import org.threeten.bp.LocalTime;
import org.threeten.bp.Month;
import org.threeten.bp.Year;
import org.threeten.bp.ZoneOffset;
import org.threeten.bp.chrono.ChronoLocalDate;
import org.threeten.bp.format.DateTimeFormatter;
import org.threeten.bp.format.DateTimeFormatterBuilder;
import org.threeten.bp.jdk8.Jdk8Methods;
import org.threeten.bp.temporal.ChronoField;
import org.threeten.bp.temporal.TemporalAccessor;
import org.threeten.bp.temporal.TemporalAdjusters;
import org.threeten.bp.zone.ZoneOffsetTransitionRule;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes5.dex */
public final class TzdbZoneRulesCompiler {
    private static final DateTimeFormatter TIME_PARSER = new DateTimeFormatterBuilder().appendValue(ChronoField.HOUR_OF_DAY).optionalStart().appendLiteral(JsonReaderKt.COLON).appendValue(ChronoField.MINUTE_OF_HOUR, 2).optionalStart().appendLiteral(JsonReaderKt.COLON).appendValue(ChronoField.SECOND_OF_MINUTE, 2).toFormatter();
    private final File leapSecondsFile;
    private final List<File> sourceFiles;
    private final boolean verbose;
    private final String version;
    private final Map<String, List<TZDBRule>> rules = new HashMap();
    private final Map<String, List<TZDBZone>> zones = new HashMap();
    private final Map<String, String> links = new HashMap();
    private final SortedMap<String, ZoneRules> builtZones = new TreeMap();
    private Map<Object, Object> deduplicateMap = new HashMap();
    private final SortedMap<LocalDate, Byte> leapSeconds = new TreeMap();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes5.dex */
    public static final class LeapSecondRule {
        final LocalDate leapDate;
        byte secondAdjustment;

        public LeapSecondRule(LocalDate localDate, byte b) {
            this.leapDate = localDate;
            this.secondAdjustment = b;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes5.dex */
    public abstract class TZDBMonthDayTime {
        int adjustDays;
        DayOfWeek dayOfWeek;
        Month month = Month.JANUARY;
        int dayOfMonth = 1;
        boolean adjustForwards = true;
        LocalTime time = LocalTime.MIDNIGHT;
        ZoneOffsetTransitionRule.TimeDefinition timeDefinition = ZoneOffsetTransitionRule.TimeDefinition.WALL;

        TZDBMonthDayTime() {
        }

        void adjustToFowards(int i) {
            int i2;
            if (this.adjustForwards || (i2 = this.dayOfMonth) <= 0) {
                return;
            }
            LocalDate minusDays = LocalDate.of(i, this.month, i2).minusDays(6L);
            this.dayOfMonth = minusDays.getDayOfMonth();
            this.month = minusDays.getMonth();
            this.adjustForwards = true;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes5.dex */
    public final class TZDBRule extends TZDBMonthDayTime {
        int endYear;
        int savingsAmount;
        int startYear;
        String text;

        TZDBRule() {
            super();
        }

        void addToBuilder(ZoneRulesBuilder zoneRulesBuilder) {
            adjustToFowards(2004);
            zoneRulesBuilder.addRuleToWindow(this.startYear, this.endYear, this.month, this.dayOfMonth, this.dayOfWeek, this.time, this.adjustDays, this.timeDefinition, this.savingsAmount);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes5.dex */
    public final class TZDBZone extends TZDBMonthDayTime {
        Integer fixedSavingsSecs;
        String savingsRule;
        ZoneOffset standardOffset;
        String text;
        Year year;

        TZDBZone() {
            super();
        }

        private LocalDateTime toDateTime(int i) {
            LocalDate of;
            adjustToFowards(i);
            int i2 = this.dayOfMonth;
            if (i2 == -1) {
                this.dayOfMonth = this.month.length(Year.isLeap(i));
                of = LocalDate.of(i, this.month, this.dayOfMonth);
                DayOfWeek dayOfWeek = this.dayOfWeek;
                if (dayOfWeek != null) {
                    of = of.mo13061with(TemporalAdjusters.previousOrSame(dayOfWeek));
                }
            } else {
                of = LocalDate.of(i, this.month, i2);
                DayOfWeek dayOfWeek2 = this.dayOfWeek;
                if (dayOfWeek2 != null) {
                    of = of.mo13061with(TemporalAdjusters.nextOrSame(dayOfWeek2));
                }
            }
            return LocalDateTime.of((LocalDate) TzdbZoneRulesCompiler.this.deduplicate(of.plusDays(this.adjustDays)), this.time);
        }

        ZoneRulesBuilder addToBuilder(ZoneRulesBuilder zoneRulesBuilder, Map<String, List<TZDBRule>> map) {
            Year year = this.year;
            if (year != null) {
                zoneRulesBuilder.addWindow(this.standardOffset, toDateTime(year.getValue()), this.timeDefinition);
            } else {
                zoneRulesBuilder.addWindowForever(this.standardOffset);
            }
            Integer num = this.fixedSavingsSecs;
            if (num != null) {
                zoneRulesBuilder.setFixedSavingsToWindow(num.intValue());
            } else {
                List<TZDBRule> list = map.get(this.savingsRule);
                if (list != null) {
                    for (TZDBRule tZDBRule : list) {
                        tZDBRule.addToBuilder(zoneRulesBuilder);
                    }
                } else {
                    StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Rule not found: ");
                    outline107.append(this.savingsRule);
                    throw new IllegalArgumentException(outline107.toString());
                }
            }
            return zoneRulesBuilder;
        }
    }

    public TzdbZoneRulesCompiler(String str, List<File> list, File file, boolean z) {
        this.version = str;
        this.sourceFiles = list;
        this.leapSecondsFile = file;
        this.verbose = z;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void buildZoneRules() throws Exception {
        for (String str : this.zones.keySet()) {
            printVerbose(GeneratedOutlineSupport1.outline72("Building zone ", str));
            String str2 = (String) deduplicate(str);
            ZoneRulesBuilder zoneRulesBuilder = new ZoneRulesBuilder();
            for (TZDBZone tZDBZone : this.zones.get(str2)) {
                zoneRulesBuilder = tZDBZone.addToBuilder(zoneRulesBuilder, this.rules);
            }
            this.builtZones.put(str2, deduplicate(zoneRulesBuilder.toRules(str2, this.deduplicateMap)));
        }
        for (String str3 : this.links.keySet()) {
            String str4 = (String) deduplicate(str3);
            String str5 = this.links.get(str4);
            printVerbose(GeneratedOutlineSupport1.outline76("Linking alias ", str4, " to ", str5));
            ZoneRules zoneRules = this.builtZones.get(str5);
            if (zoneRules == null) {
                String str6 = this.links.get(str5);
                printVerbose(GeneratedOutlineSupport1.outline76("Relinking alias ", str4, " to ", str6));
                zoneRules = this.builtZones.get(str6);
                if (zoneRules == null) {
                    throw new IllegalArgumentException(GeneratedOutlineSupport1.outline91(GeneratedOutlineSupport1.outline116("Alias '", str4, "' links to invalid zone '", str6, "' for '"), this.version, "'"));
                }
            }
            this.builtZones.put(str4, zoneRules);
        }
        this.builtZones.remove(Constants.UTC);
        this.builtZones.remove("GMT");
        this.builtZones.remove("GMT0");
        this.builtZones.remove("GMT+0");
        this.builtZones.remove("GMT-0");
    }

    private LocalDate getMostRecentLeapSecond() {
        if (this.leapSeconds.isEmpty()) {
            return null;
        }
        return this.leapSeconds.lastKey();
    }

    public static void main(String[] strArr) {
        File[] listFiles;
        if (strArr.length < 2) {
            outputHelp();
            return;
        }
        File file = null;
        File file2 = null;
        String str = null;
        int i = 0;
        boolean z = false;
        boolean z2 = false;
        while (i < strArr.length) {
            String str2 = strArr[i];
            if (!str2.startsWith(ProcessIdUtil.DEFAULT_PROCESSID)) {
                break;
            } else if ("-srcdir".equals(str2)) {
                if (file == null && (i = i + 1) < strArr.length) {
                    file = new File(strArr[i]);
                    i++;
                }
                outputHelp();
                return;
            } else if ("-dstdir".equals(str2)) {
                if (file2 == null && (i = i + 1) < strArr.length) {
                    file2 = new File(strArr[i]);
                    i++;
                }
                outputHelp();
                return;
            } else if ("-version".equals(str2)) {
                if (str == null && (i = i + 1) < strArr.length) {
                    str = strArr[i];
                    i++;
                }
                outputHelp();
                return;
            } else if (!"-unpacked".equals(str2)) {
                if ("-verbose".equals(str2)) {
                    if (!z2) {
                        z2 = true;
                        i++;
                    }
                } else if (!"-help".equals(str2)) {
                    System.out.println("Unrecognised option: " + str2);
                }
                outputHelp();
                return;
            } else if (z) {
                outputHelp();
                return;
            } else {
                z = true;
                i++;
            }
        }
        if (file == null) {
            System.out.println("Source directory must be specified using -srcdir: " + file);
        } else if (!file.isDirectory()) {
            System.out.println("Source does not exist or is not a directory: " + file);
        } else {
            if (file2 == null) {
                file2 = file;
            }
            List asList = Arrays.asList(Arrays.copyOfRange(strArr, i, strArr.length));
            if (asList.isEmpty()) {
                System.out.println("Source filenames not specified, using default set");
                System.out.println("(africa antarctica asia australasia backward etcetera europe northamerica southamerica)");
                asList = Arrays.asList("africa", "antarctica", "asia", "australasia", "backward", "etcetera", "europe", "northamerica", "southamerica");
            }
            ArrayList arrayList = new ArrayList();
            if (str != null) {
                File file3 = new File(file, str);
                if (!file3.isDirectory()) {
                    System.out.println("Version does not represent a valid source directory : " + file3);
                    return;
                }
                arrayList.add(file3);
            } else {
                for (File file4 : file.listFiles()) {
                    if (file4.isDirectory() && file4.getName().matches("[12][0-9][0-9][0-9][A-Za-z0-9._-]+")) {
                        arrayList.add(file4);
                    }
                }
            }
            if (arrayList.isEmpty()) {
                System.out.println("Source directory contains no valid source folders: " + file);
            } else if (!file2.exists() && !file2.mkdirs()) {
                System.out.println("Destination directory could not be created: " + file2);
            } else if (!file2.isDirectory()) {
                System.out.println("Destination is not a directory: " + file2);
            } else {
                process(arrayList, asList, file2, z, z2);
            }
        }
    }

    private boolean matches(String str, String str2) {
        return str.startsWith(str2.substring(0, 3)) && str2.startsWith(str) && str.length() <= str2.length();
    }

    private static void outputFile(File file, String str, SortedMap<String, ZoneRules> sortedMap, SortedMap<LocalDate, Byte> sortedMap2) {
        TreeMap treeMap = new TreeMap();
        treeMap.put(str, sortedMap);
        outputFile(file, treeMap, new TreeSet(sortedMap.keySet()), new HashSet(sortedMap.values()), sortedMap2);
    }

    private static void outputFilesDat(File file, Map<String, SortedMap<String, ZoneRules>> map, Set<String> set, Set<ZoneRules> set2, SortedMap<LocalDate, Byte> sortedMap) {
        File file2 = new File(file, "TZDB.dat");
        file2.delete();
        FileOutputStream fileOutputStream = null;
        try {
            try {
                FileOutputStream fileOutputStream2 = new FileOutputStream(file2);
                try {
                    outputTzdbDat(fileOutputStream2, map, set, set2);
                    fileOutputStream2.close();
                } catch (Throwable th) {
                    th = th;
                    fileOutputStream = fileOutputStream2;
                    if (fileOutputStream != null) {
                        fileOutputStream.close();
                    }
                    throw th;
                }
            } catch (Exception e) {
                PrintStream printStream = System.out;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Failed: ");
                outline107.append(e.toString());
                printStream.println(outline107.toString());
                e.printStackTrace();
                System.exit(1);
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    private static void outputHelp() {
        System.out.println("Usage: TzdbZoneRulesCompiler <options> <tzdb source filenames>");
        System.out.println("where options include:");
        System.out.println("   -srcdir <directory>   Where to find source directories (required)");
        System.out.println("   -dstdir <directory>   Where to output generated files (default srcdir)");
        System.out.println("   -version <version>    Specify the version, such as 2009a (optional)");
        System.out.println("   -unpacked             Generate dat files without jar files");
        System.out.println("   -help                 Print this usage message");
        System.out.println("   -verbose              Output verbose information during compilation");
        System.out.println(" There must be one directory for each version in srcdir");
        System.out.println(" Each directory must have the name of the version, such as 2009a");
        System.out.println(" Each directory must contain the unpacked tzdb files, such as asia or europe");
        System.out.println(" Directories must match the regex [12][0-9][0-9][0-9][A-Za-z0-9._-]+");
        System.out.println(" There will be one jar file for each version and one combined jar in dstdir");
        System.out.println(" If the version is specified, only that version is processed");
    }

    private static void outputTzdbDat(OutputStream outputStream, Map<String, SortedMap<String, ZoneRules>> map, Set<String> set, Set<ZoneRules> set2) throws IOException {
        DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
        dataOutputStream.writeByte(1);
        dataOutputStream.writeUTF("TZDB");
        String[] strArr = (String[]) map.keySet().toArray(new String[map.size()]);
        dataOutputStream.writeShort(strArr.length);
        for (String str : strArr) {
            dataOutputStream.writeUTF(str);
        }
        String[] strArr2 = (String[]) set.toArray(new String[set.size()]);
        dataOutputStream.writeShort(strArr2.length);
        for (String str2 : strArr2) {
            dataOutputStream.writeUTF(str2);
        }
        ArrayList<ZoneRules> arrayList = new ArrayList(set2);
        dataOutputStream.writeShort(arrayList.size());
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(1024);
        for (ZoneRules zoneRules : arrayList) {
            byteArrayOutputStream.reset();
            DataOutputStream dataOutputStream2 = new DataOutputStream(byteArrayOutputStream);
            Ser.write(zoneRules, dataOutputStream2);
            dataOutputStream2.close();
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            dataOutputStream.writeShort(byteArray.length);
            dataOutputStream.write(byteArray);
        }
        for (String str3 : map.keySet()) {
            dataOutputStream.writeShort(map.get(str3).size());
            for (Map.Entry<String, ZoneRules> entry : map.get(str3).entrySet()) {
                int binarySearch = Arrays.binarySearch(strArr2, entry.getKey());
                int indexOf = arrayList.indexOf(entry.getValue());
                dataOutputStream.writeShort(binarySearch);
                dataOutputStream.writeShort(indexOf);
            }
        }
        dataOutputStream.flush();
    }

    private static void outputTzdbEntry(JarOutputStream jarOutputStream, Map<String, SortedMap<String, ZoneRules>> map, Set<String> set, Set<ZoneRules> set2) {
        try {
            jarOutputStream.putNextEntry(new ZipEntry("org/threeten/bp/TZDB.dat"));
            outputTzdbDat(jarOutputStream, map, set, set2);
            jarOutputStream.closeEntry();
        } catch (Exception e) {
            PrintStream printStream = System.out;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Failed: ");
            outline107.append(e.toString());
            printStream.println(outline107.toString());
            e.printStackTrace();
            System.exit(1);
        }
    }

    private DayOfWeek parseDayOfWeek(String str) {
        DayOfWeek[] values;
        String lowerCase = str.toLowerCase();
        for (DayOfWeek dayOfWeek : DayOfWeek.values()) {
            if (matches(lowerCase, dayOfWeek.name().toLowerCase())) {
                return dayOfWeek;
            }
        }
        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline72("Unknown day-of-week: ", lowerCase));
    }

    private void parseFile(File file) throws Exception {
        BufferedReader bufferedReader;
        int i;
        String str;
        Exception e;
        BufferedReader bufferedReader2 = null;
        try {
            try {
                bufferedReader = new BufferedReader(new FileReader(file));
                i = 1;
                ArrayList arrayList = null;
                str = null;
                while (true) {
                    try {
                        str = bufferedReader.readLine();
                        if (str != null) {
                            int indexOf = str.indexOf(35);
                            if (indexOf >= 0) {
                                str = str.substring(0, indexOf);
                            }
                            if (str.trim().length() != 0) {
                                StringTokenizer stringTokenizer = new StringTokenizer(str, " \t");
                                if (arrayList != null && Character.isWhitespace(str.charAt(0)) && stringTokenizer.hasMoreTokens()) {
                                    if (!parseZoneLine(stringTokenizer, arrayList)) {
                                    }
                                    arrayList = null;
                                } else if (stringTokenizer.hasMoreTokens()) {
                                    String nextToken = stringTokenizer.nextToken();
                                    if (nextToken.equals("Zone")) {
                                        if (stringTokenizer.countTokens() >= 3) {
                                            arrayList = new ArrayList();
                                            this.zones.put(stringTokenizer.nextToken(), arrayList);
                                            if (parseZoneLine(stringTokenizer, arrayList)) {
                                            }
                                        } else {
                                            printVerbose("Invalid Zone line in file: " + file + ", line: " + str);
                                            throw new IllegalArgumentException("Invalid Zone line");
                                        }
                                    } else if (nextToken.equals("Rule")) {
                                        if (stringTokenizer.countTokens() >= 9) {
                                            parseRuleLine(stringTokenizer);
                                        } else {
                                            printVerbose("Invalid Rule line in file: " + file + ", line: " + str);
                                            throw new IllegalArgumentException("Invalid Rule line");
                                        }
                                    } else if (nextToken.equals(HttpHeaders.LINK)) {
                                        if (stringTokenizer.countTokens() >= 2) {
                                            this.links.put(stringTokenizer.nextToken(), stringTokenizer.nextToken());
                                        } else {
                                            printVerbose("Invalid Link line in file: " + file + ", line: " + str);
                                            throw new IllegalArgumentException("Invalid Link line");
                                        }
                                    } else {
                                        throw new IllegalArgumentException("Unknown line");
                                    }
                                    arrayList = null;
                                } else {
                                    continue;
                                }
                            }
                            i++;
                        } else {
                            bufferedReader.close();
                            return;
                        }
                    } catch (Exception e2) {
                        e = e2;
                        bufferedReader2 = bufferedReader;
                        throw new Exception("Failed while processing file '" + file + "' on line " + i + " '" + str + "'", e);
                    } catch (Throwable th) {
                        th = th;
                        if (bufferedReader != null) {
                            bufferedReader.close();
                        }
                        throw th;
                    }
                }
            } catch (Exception e3) {
                i = 1;
                str = null;
                e = e3;
            }
        } catch (Throwable th2) {
            th = th2;
            bufferedReader = bufferedReader2;
        }
    }

    private void parseFiles() throws Exception {
        for (File file : this.sourceFiles) {
            printVerbose(GeneratedOutlineSupport1.outline63("Parsing file: ", file));
            parseFile(file);
        }
    }

    private LeapSecondRule parseLeapSecondRule(String str) {
        byte b;
        StringTokenizer stringTokenizer = new StringTokenizer(str, " \t");
        if (stringTokenizer.nextToken().equals("Leap")) {
            if (stringTokenizer.countTokens() >= 6) {
                LocalDate of = LocalDate.of(Integer.parseInt(stringTokenizer.nextToken()), parseMonth(stringTokenizer.nextToken()), Integer.parseInt(stringTokenizer.nextToken()));
                String nextToken = stringTokenizer.nextToken();
                String nextToken2 = stringTokenizer.nextToken();
                if (nextToken2.equals("+")) {
                    if (!"23:59:60".equals(nextToken)) {
                        throw new IllegalArgumentException("Leap seconds can only be inserted at 23:59:60 - Date:" + of);
                    }
                    b = 1;
                } else if (nextToken2.equals(ProcessIdUtil.DEFAULT_PROCESSID)) {
                    if (!"23:59:59".equals(nextToken)) {
                        throw new IllegalArgumentException("Leap seconds can only be removed at 23:59:59 - Date:" + of);
                    }
                    b = -1;
                } else {
                    throw new IllegalArgumentException("Invalid adjustment '" + nextToken2 + "' in leap second rule for " + of);
                }
                String nextToken3 = stringTokenizer.nextToken();
                if (ExifInterface.LATITUDE_SOUTH.equalsIgnoreCase(nextToken3)) {
                    return new LeapSecondRule(of, b);
                }
                throw new IllegalArgumentException(GeneratedOutlineSupport1.outline75("Only stationary ('S') leap seconds are supported, not '", nextToken3, "'"));
            }
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Invalid leap second line in file: ");
            outline107.append(this.leapSecondsFile);
            outline107.append(", line: ");
            outline107.append(str);
            printVerbose(outline107.toString());
            throw new IllegalArgumentException("Invalid leap second line");
        }
        throw new IllegalArgumentException("Unknown line");
    }

    /* JADX WARN: Removed duplicated region for block: B:36:0x0090 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void parseLeapSecondsFile() throws java.lang.Exception {
        /*
            r7 = this;
            java.lang.String r0 = "Parsing leap second file: "
            java.lang.StringBuilder r0 = com.android.tools.r8.GeneratedOutlineSupport1.outline107(r0)
            java.io.File r1 = r7.leapSecondsFile
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            r7.printVerbose(r0)
            r0 = 0
            r1 = 1
            java.io.BufferedReader r2 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> L58 java.lang.Exception -> L5a
            java.io.FileReader r3 = new java.io.FileReader     // Catch: java.lang.Throwable -> L58 java.lang.Exception -> L5a
            java.io.File r4 = r7.leapSecondsFile     // Catch: java.lang.Throwable -> L58 java.lang.Exception -> L5a
            r3.<init>(r4)     // Catch: java.lang.Throwable -> L58 java.lang.Exception -> L5a
            r2.<init>(r3)     // Catch: java.lang.Throwable -> L58 java.lang.Exception -> L5a
        L20:
            java.lang.String r0 = r2.readLine()     // Catch: java.lang.Exception -> L56 java.lang.Throwable -> L8b
            if (r0 == 0) goto L52
            r3 = 35
            int r3 = r0.indexOf(r3)     // Catch: java.lang.Exception -> L56 java.lang.Throwable -> L8b
            if (r3 < 0) goto L33
            r4 = 0
            java.lang.String r0 = r0.substring(r4, r3)     // Catch: java.lang.Exception -> L56 java.lang.Throwable -> L8b
        L33:
            java.lang.String r3 = r0.trim()     // Catch: java.lang.Exception -> L56 java.lang.Throwable -> L8b
            int r3 = r3.length()     // Catch: java.lang.Exception -> L56 java.lang.Throwable -> L8b
            if (r3 != 0) goto L3e
            goto L4f
        L3e:
            org.threeten.bp.zone.TzdbZoneRulesCompiler$LeapSecondRule r3 = r7.parseLeapSecondRule(r0)     // Catch: java.lang.Exception -> L56 java.lang.Throwable -> L8b
            java.util.SortedMap<org.threeten.bp.LocalDate, java.lang.Byte> r4 = r7.leapSeconds     // Catch: java.lang.Exception -> L56 java.lang.Throwable -> L8b
            org.threeten.bp.LocalDate r5 = r3.leapDate     // Catch: java.lang.Exception -> L56 java.lang.Throwable -> L8b
            byte r3 = r3.secondAdjustment     // Catch: java.lang.Exception -> L56 java.lang.Throwable -> L8b
            java.lang.Byte r3 = java.lang.Byte.valueOf(r3)     // Catch: java.lang.Exception -> L56 java.lang.Throwable -> L8b
            r4.put(r5, r3)     // Catch: java.lang.Exception -> L56 java.lang.Throwable -> L8b
        L4f:
            int r1 = r1 + 1
            goto L20
        L52:
            r2.close()     // Catch: java.lang.Exception -> L55
        L55:
            return
        L56:
            r3 = move-exception
            goto L5d
        L58:
            r1 = move-exception
            goto L8e
        L5a:
            r2 = move-exception
            r3 = r2
            r2 = r0
        L5d:
            java.lang.Exception r4 = new java.lang.Exception     // Catch: java.lang.Throwable -> L8b
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L8b
            r5.<init>()     // Catch: java.lang.Throwable -> L8b
            java.lang.String r6 = "Failed while processing file '"
            r5.append(r6)     // Catch: java.lang.Throwable -> L8b
            java.io.File r6 = r7.leapSecondsFile     // Catch: java.lang.Throwable -> L8b
            r5.append(r6)     // Catch: java.lang.Throwable -> L8b
            java.lang.String r6 = "' on line "
            r5.append(r6)     // Catch: java.lang.Throwable -> L8b
            r5.append(r1)     // Catch: java.lang.Throwable -> L8b
            java.lang.String r1 = " '"
            r5.append(r1)     // Catch: java.lang.Throwable -> L8b
            r5.append(r0)     // Catch: java.lang.Throwable -> L8b
            java.lang.String r0 = "'"
            r5.append(r0)     // Catch: java.lang.Throwable -> L8b
            java.lang.String r0 = r5.toString()     // Catch: java.lang.Throwable -> L8b
            r4.<init>(r0, r3)     // Catch: java.lang.Throwable -> L8b
            throw r4     // Catch: java.lang.Throwable -> L8b
        L8b:
            r0 = move-exception
            r1 = r0
            r0 = r2
        L8e:
            if (r0 == 0) goto L93
            r0.close()     // Catch: java.lang.Exception -> L93
        L93:
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.threeten.bp.zone.TzdbZoneRulesCompiler.parseLeapSecondsFile():void");
    }

    private Month parseMonth(String str) {
        Month[] values;
        String lowerCase = str.toLowerCase();
        for (Month month : Month.values()) {
            if (matches(lowerCase, month.name().toLowerCase())) {
                return month;
            }
        }
        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline72("Unknown month: ", lowerCase));
    }

    private void parseMonthDayTime(StringTokenizer stringTokenizer, TZDBMonthDayTime tZDBMonthDayTime) {
        tZDBMonthDayTime.month = parseMonth(stringTokenizer.nextToken());
        if (stringTokenizer.hasMoreTokens()) {
            String nextToken = stringTokenizer.nextToken();
            if (nextToken.startsWith("last")) {
                tZDBMonthDayTime.dayOfMonth = -1;
                tZDBMonthDayTime.dayOfWeek = parseDayOfWeek(nextToken.substring(4));
                tZDBMonthDayTime.adjustForwards = false;
            } else {
                int indexOf = nextToken.indexOf(">=");
                if (indexOf > 0) {
                    tZDBMonthDayTime.dayOfWeek = parseDayOfWeek(nextToken.substring(0, indexOf));
                    nextToken = nextToken.substring(indexOf + 2);
                } else {
                    int indexOf2 = nextToken.indexOf("<=");
                    if (indexOf2 > 0) {
                        tZDBMonthDayTime.dayOfWeek = parseDayOfWeek(nextToken.substring(0, indexOf2));
                        tZDBMonthDayTime.adjustForwards = false;
                        nextToken = nextToken.substring(indexOf2 + 2);
                    }
                }
                tZDBMonthDayTime.dayOfMonth = Integer.parseInt(nextToken);
            }
            if (!stringTokenizer.hasMoreTokens()) {
                return;
            }
            String nextToken2 = stringTokenizer.nextToken();
            int parseSecs = parseSecs(nextToken2);
            tZDBMonthDayTime.time = (LocalTime) deduplicate(LocalTime.ofSecondOfDay(Jdk8Methods.floorMod(parseSecs, (int) DateTimeConstants.SECONDS_PER_DAY)));
            tZDBMonthDayTime.adjustDays = Jdk8Methods.floorDiv(parseSecs, (int) DateTimeConstants.SECONDS_PER_DAY);
            tZDBMonthDayTime.timeDefinition = parseTimeDefinition(nextToken2.charAt(nextToken2.length() - 1));
        }
    }

    private ZoneOffset parseOffset(String str) {
        return ZoneOffset.ofTotalSeconds(parseSecs(str));
    }

    private String parseOptional(String str) {
        if (str.equals(ProcessIdUtil.DEFAULT_PROCESSID)) {
            return null;
        }
        return str;
    }

    private int parsePeriod(String str) {
        return parseSecs(str);
    }

    private void parseRuleLine(StringTokenizer stringTokenizer) {
        TZDBRule tZDBRule = new TZDBRule();
        String nextToken = stringTokenizer.nextToken();
        if (!this.rules.containsKey(nextToken)) {
            this.rules.put(nextToken, new ArrayList());
        }
        this.rules.get(nextToken).add(tZDBRule);
        tZDBRule.startYear = parseYear(stringTokenizer.nextToken(), 0);
        tZDBRule.endYear = parseYear(stringTokenizer.nextToken(), tZDBRule.startYear);
        if (tZDBRule.startYear <= tZDBRule.endYear) {
            parseOptional(stringTokenizer.nextToken());
            parseMonthDayTime(stringTokenizer, tZDBRule);
            tZDBRule.savingsAmount = parsePeriod(stringTokenizer.nextToken());
            tZDBRule.text = parseOptional(stringTokenizer.nextToken());
            return;
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Year order invalid: ");
        outline107.append(tZDBRule.startYear);
        outline107.append(" > ");
        outline107.append(tZDBRule.endYear);
        throw new IllegalArgumentException(outline107.toString());
    }

    private int parseSecs(String str) {
        if (str.equals(ProcessIdUtil.DEFAULT_PROCESSID)) {
            return 0;
        }
        boolean startsWith = str.startsWith(ProcessIdUtil.DEFAULT_PROCESSID);
        ParsePosition parsePosition = new ParsePosition(startsWith ? 1 : 0);
        TemporalAccessor parseUnresolved = TIME_PARSER.parseUnresolved(str, parsePosition);
        if (parseUnresolved != null && parsePosition.getErrorIndex() < 0) {
            long j = parseUnresolved.getLong(ChronoField.HOUR_OF_DAY);
            Long l = null;
            Long valueOf = parseUnresolved.isSupported(ChronoField.MINUTE_OF_HOUR) ? Long.valueOf(parseUnresolved.getLong(ChronoField.MINUTE_OF_HOUR)) : null;
            if (parseUnresolved.isSupported(ChronoField.SECOND_OF_MINUTE)) {
                l = Long.valueOf(parseUnresolved.getLong(ChronoField.SECOND_OF_MINUTE));
            }
            long j2 = 0;
            long longValue = ((valueOf != null ? valueOf.longValue() : 0L) * 60) + (j * 60 * 60);
            if (l != null) {
                j2 = l.longValue();
            }
            int i = (int) (longValue + j2);
            return startsWith ? -i : i;
        }
        throw new IllegalArgumentException(str);
    }

    private ZoneOffsetTransitionRule.TimeDefinition parseTimeDefinition(char c) {
        if (c != 'G') {
            if (c != 'S') {
                if (c != 'U' && c != 'Z' && c != 'g') {
                    if (c != 's') {
                        if (c != 'u' && c != 'z') {
                            return ZoneOffsetTransitionRule.TimeDefinition.WALL;
                        }
                    }
                }
            }
            return ZoneOffsetTransitionRule.TimeDefinition.STANDARD;
        }
        return ZoneOffsetTransitionRule.TimeDefinition.UTC;
    }

    private int parseYear(String str, int i) {
        String lowerCase = str.toLowerCase();
        return matches(lowerCase, "minimum") ? Year.MIN_VALUE : matches(lowerCase, "maximum") ? Year.MAX_VALUE : lowerCase.equals("only") ? i : Integer.parseInt(lowerCase);
    }

    private boolean parseZoneLine(StringTokenizer stringTokenizer, List<TZDBZone> list) {
        TZDBZone tZDBZone = new TZDBZone();
        list.add(tZDBZone);
        tZDBZone.standardOffset = parseOffset(stringTokenizer.nextToken());
        String parseOptional = parseOptional(stringTokenizer.nextToken());
        if (parseOptional == null) {
            tZDBZone.fixedSavingsSecs = 0;
            tZDBZone.savingsRule = null;
        } else {
            try {
                tZDBZone.fixedSavingsSecs = Integer.valueOf(parsePeriod(parseOptional));
                tZDBZone.savingsRule = null;
            } catch (Exception unused) {
                tZDBZone.fixedSavingsSecs = null;
                tZDBZone.savingsRule = parseOptional;
            }
        }
        tZDBZone.text = stringTokenizer.nextToken();
        if (stringTokenizer.hasMoreTokens()) {
            tZDBZone.year = Year.of(Integer.parseInt(stringTokenizer.nextToken()));
            if (stringTokenizer.hasMoreTokens()) {
                parseMonthDayTime(stringTokenizer, tZDBZone);
            }
            return false;
        }
        return true;
    }

    private void printVerbose(String str) {
        if (this.verbose) {
            System.out.println(str);
        }
    }

    private static void process(List<File> list, List<String> list2, File file, boolean z, boolean z2) {
        HashMap hashMap = new HashMap();
        TreeMap treeMap = new TreeMap();
        TreeSet treeSet = new TreeSet();
        HashSet hashSet = new HashSet();
        SortedMap<LocalDate, Byte> sortedMap = null;
        for (File file2 : list) {
            ArrayList arrayList = new ArrayList();
            for (String str : list2) {
                File file3 = new File(file2, str);
                if (file3.exists()) {
                    arrayList.add(file3);
                }
            }
            if (!arrayList.isEmpty()) {
                File file4 = new File(file2, "leapseconds");
                if (!file4.exists()) {
                    PrintStream printStream = System.out;
                    StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Version ");
                    outline107.append(file2.getName());
                    outline107.append(" does not include leap seconds information.");
                    printStream.println(outline107.toString());
                    file4 = null;
                }
                String name = file2.getName();
                TzdbZoneRulesCompiler tzdbZoneRulesCompiler = new TzdbZoneRulesCompiler(name, arrayList, file4, z2);
                tzdbZoneRulesCompiler.setDeduplicateMap(hashMap);
                try {
                    tzdbZoneRulesCompiler.compile();
                    SortedMap<String, ZoneRules> zones = tzdbZoneRulesCompiler.getZones();
                    SortedMap<LocalDate, Byte> leapSeconds = tzdbZoneRulesCompiler.getLeapSeconds();
                    if (!z) {
                        File file5 = new File(file, "threeten-TZDB-" + name + ".jar");
                        if (z2) {
                            System.out.println("Outputting file: " + file5);
                        }
                        outputFile(file5, name, zones, leapSeconds);
                    }
                    treeMap.put(name, zones);
                    treeSet.addAll(zones.keySet());
                    hashSet.addAll(zones.values());
                    if (tzdbZoneRulesCompiler.getMostRecentLeapSecond() != null && (sortedMap == null || tzdbZoneRulesCompiler.getMostRecentLeapSecond().compareTo((ChronoLocalDate) sortedMap.lastKey()) > 0)) {
                        sortedMap = leapSeconds;
                    }
                } catch (Exception e) {
                    PrintStream printStream2 = System.out;
                    StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("Failed: ");
                    outline1072.append(e.toString());
                    printStream2.println(outline1072.toString());
                    e.printStackTrace();
                    System.exit(1);
                }
            }
        }
        if (z) {
            if (z2) {
                System.out.println("Outputting combined files: " + file);
            }
            outputFilesDat(file, treeMap, treeSet, hashSet, sortedMap);
            return;
        }
        File file6 = new File(file, "threeten-TZDB-all.jar");
        if (z2) {
            System.out.println("Outputting combined file: " + file6);
        }
        outputFile(file6, treeMap, treeSet, hashSet, sortedMap);
    }

    public void compile() throws Exception {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Compiling TZDB version ");
        outline107.append(this.version);
        printVerbose(outline107.toString());
        parseFiles();
        parseLeapSecondsFile();
        buildZoneRules();
        StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("Compiled TZDB version ");
        outline1072.append(this.version);
        printVerbose(outline1072.toString());
    }

    <T> T deduplicate(T t) {
        if (!this.deduplicateMap.containsKey(t)) {
            this.deduplicateMap.put(t, t);
        }
        return (T) this.deduplicateMap.get(t);
    }

    public SortedMap<LocalDate, Byte> getLeapSeconds() {
        return this.leapSeconds;
    }

    public SortedMap<String, ZoneRules> getZones() {
        return this.builtZones;
    }

    void setDeduplicateMap(Map<Object, Object> map) {
        this.deduplicateMap = map;
    }

    private static void outputFile(File file, Map<String, SortedMap<String, ZoneRules>> map, Set<String> set, Set<ZoneRules> set2, SortedMap<LocalDate, Byte> sortedMap) {
        JarOutputStream jarOutputStream;
        JarOutputStream jarOutputStream2 = null;
        try {
            try {
                try {
                    jarOutputStream = new JarOutputStream(new FileOutputStream(file));
                } catch (Exception e) {
                    e = e;
                }
            } catch (Throwable th) {
                th = th;
            }
            try {
                outputTzdbEntry(jarOutputStream, map, set, set2);
                jarOutputStream.close();
            } catch (Exception e2) {
                e = e2;
                jarOutputStream2 = jarOutputStream;
                PrintStream printStream = System.out;
                printStream.println("Failed: " + e.toString());
                e.printStackTrace();
                System.exit(1);
                if (jarOutputStream2 == null) {
                    return;
                }
                jarOutputStream2.close();
            } catch (Throwable th2) {
                th = th2;
                jarOutputStream2 = jarOutputStream;
                if (jarOutputStream2 != null) {
                    try {
                        jarOutputStream2.close();
                    } catch (IOException unused) {
                    }
                }
                throw th;
            }
        } catch (IOException unused2) {
        }
    }
}
