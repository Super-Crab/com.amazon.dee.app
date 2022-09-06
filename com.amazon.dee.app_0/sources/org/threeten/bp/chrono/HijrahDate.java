package org.threeten.bp.chrono;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.drew.metadata.exif.ExifDirectoryBase;
import java.io.BufferedReader;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.text.ParseException;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import org.apache.commons.net.telnet.TelnetCommand;
import org.threeten.bp.Clock;
import org.threeten.bp.DateTimeException;
import org.threeten.bp.DayOfWeek;
import org.threeten.bp.LocalDate;
import org.threeten.bp.LocalTime;
import org.threeten.bp.ZoneId;
import org.threeten.bp.jdk8.Jdk8Methods;
import org.threeten.bp.temporal.ChronoField;
import org.threeten.bp.temporal.Temporal;
import org.threeten.bp.temporal.TemporalAccessor;
import org.threeten.bp.temporal.TemporalAdjuster;
import org.threeten.bp.temporal.TemporalAmount;
import org.threeten.bp.temporal.TemporalField;
import org.threeten.bp.temporal.TemporalUnit;
import org.threeten.bp.temporal.UnsupportedTemporalTypeException;
import org.threeten.bp.temporal.ValueRange;
/* loaded from: classes5.dex */
public final class HijrahDate extends ChronoDateImpl<HijrahDate> implements Serializable {
    private static final Long[] ADJUSTED_CYCLES;
    private static final HashMap<Integer, Integer[]> ADJUSTED_CYCLE_YEARS;
    private static final Integer[] ADJUSTED_LEAST_MAX_VALUES;
    private static final Integer[] ADJUSTED_MAX_VALUES;
    private static final Integer[] ADJUSTED_MIN_VALUES;
    private static final HashMap<Integer, Integer[]> ADJUSTED_MONTH_DAYS;
    private static final HashMap<Integer, Integer[]> ADJUSTED_MONTH_LENGTHS;
    private static final String DEFAULT_CONFIG_FILENAME = "hijrah_deviation.cfg";
    private static final String DEFAULT_CONFIG_PATH;
    private static final Integer[] DEFAULT_CYCLE_YEARS;
    private static final Integer[] DEFAULT_LEAP_MONTH_DAYS;
    private static final Integer[] DEFAULT_LEAP_MONTH_LENGTHS;
    private static final Integer[] DEFAULT_MONTH_DAYS;
    private static final Integer[] DEFAULT_MONTH_LENGTHS;
    private static final int HIJRAH_JAN_1_1_GREGORIAN_DAY = -492148;
    private static final int MAX_ADJUSTED_CYCLE = 334;
    public static final int MIN_VALUE_OF_ERA = 1;
    private static final int POSITION_DAY_OF_MONTH = 5;
    private static final int POSITION_DAY_OF_YEAR = 6;
    private static final long serialVersionUID = -5207853542612002020L;
    private final transient int dayOfMonth;
    private final transient DayOfWeek dayOfWeek;
    private final transient int dayOfYear;
    private final transient HijrahEra era;
    private final long gregorianEpochDay;
    private final transient boolean isLeapYear;
    private final transient int monthOfYear;
    private final transient int yearOfEra;
    private static final int[] NUM_DAYS = {0, 30, 59, 89, 118, 148, 177, 207, TelnetCommand.EOF, 266, 295, ExifDirectoryBase.TAG_TILE_BYTE_COUNTS};
    private static final int[] LEAP_NUM_DAYS = {0, 30, 59, 89, 118, 148, 177, 207, TelnetCommand.EOF, 266, 295, ExifDirectoryBase.TAG_TILE_BYTE_COUNTS};
    private static final int[] MONTH_LENGTH = {30, 29, 30, 29, 30, 29, 30, 29, 30, 29, 30, 29};
    private static final int[] LEAP_MONTH_LENGTH = {30, 29, 30, 29, 30, 29, 30, 29, 30, 29, 30, 30};
    private static final int[] MIN_VALUES = {0, 1, 0, 1, 0, 1, 1};
    public static final int MAX_VALUE_OF_ERA = 9999;
    private static final int[] LEAST_MAX_VALUES = {1, MAX_VALUE_OF_ERA, 11, 51, 5, 29, 354};
    private static final int[] MAX_VALUES = {1, MAX_VALUE_OF_ERA, 11, 52, 6, 30, 355};
    private static final int[] CYCLEYEAR_START_DATE = {0, 354, 709, 1063, 1417, 1772, 2126, 2481, 2835, 3189, 3544, 3898, 4252, 4607, 4961, 5315, 5670, 6024, 6379, 6733, 7087, 7442, 7796, 8150, 8505, 8859, 9214, 9568, 9922, 10277};
    private static final char FILE_SEP = File.separatorChar;
    private static final String PATH_SEP = File.pathSeparator;

    /* renamed from: org.threeten.bp.chrono.HijrahDate$1  reason: invalid class name */
    /* loaded from: classes5.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$threeten$bp$temporal$ChronoField = new int[ChronoField.values().length];

        static {
            try {
                $SwitchMap$org$threeten$bp$temporal$ChronoField[ChronoField.DAY_OF_MONTH.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$org$threeten$bp$temporal$ChronoField[ChronoField.DAY_OF_YEAR.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$org$threeten$bp$temporal$ChronoField[ChronoField.ALIGNED_WEEK_OF_MONTH.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$org$threeten$bp$temporal$ChronoField[ChronoField.YEAR_OF_ERA.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$org$threeten$bp$temporal$ChronoField[ChronoField.DAY_OF_WEEK.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$org$threeten$bp$temporal$ChronoField[ChronoField.ALIGNED_DAY_OF_WEEK_IN_MONTH.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$org$threeten$bp$temporal$ChronoField[ChronoField.ALIGNED_DAY_OF_WEEK_IN_YEAR.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$org$threeten$bp$temporal$ChronoField[ChronoField.EPOCH_DAY.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$org$threeten$bp$temporal$ChronoField[ChronoField.ALIGNED_WEEK_OF_YEAR.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$org$threeten$bp$temporal$ChronoField[ChronoField.MONTH_OF_YEAR.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$org$threeten$bp$temporal$ChronoField[ChronoField.YEAR.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$org$threeten$bp$temporal$ChronoField[ChronoField.ERA.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
        }
    }

    static {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("org");
        outline107.append(FILE_SEP);
        outline107.append("threeten");
        outline107.append(FILE_SEP);
        outline107.append("bp");
        outline107.append(FILE_SEP);
        outline107.append("chrono");
        DEFAULT_CONFIG_PATH = outline107.toString();
        ADJUSTED_MONTH_DAYS = new HashMap<>();
        ADJUSTED_MONTH_LENGTHS = new HashMap<>();
        ADJUSTED_CYCLE_YEARS = new HashMap<>();
        DEFAULT_MONTH_DAYS = new Integer[NUM_DAYS.length];
        int i = 0;
        int i2 = 0;
        while (true) {
            int[] iArr = NUM_DAYS;
            if (i2 >= iArr.length) {
                break;
            }
            DEFAULT_MONTH_DAYS[i2] = Integer.valueOf(iArr[i2]);
            i2++;
        }
        DEFAULT_LEAP_MONTH_DAYS = new Integer[LEAP_NUM_DAYS.length];
        int i3 = 0;
        while (true) {
            int[] iArr2 = LEAP_NUM_DAYS;
            if (i3 >= iArr2.length) {
                break;
            }
            DEFAULT_LEAP_MONTH_DAYS[i3] = Integer.valueOf(iArr2[i3]);
            i3++;
        }
        DEFAULT_MONTH_LENGTHS = new Integer[MONTH_LENGTH.length];
        int i4 = 0;
        while (true) {
            int[] iArr3 = MONTH_LENGTH;
            if (i4 >= iArr3.length) {
                break;
            }
            DEFAULT_MONTH_LENGTHS[i4] = Integer.valueOf(iArr3[i4]);
            i4++;
        }
        DEFAULT_LEAP_MONTH_LENGTHS = new Integer[LEAP_MONTH_LENGTH.length];
        int i5 = 0;
        while (true) {
            int[] iArr4 = LEAP_MONTH_LENGTH;
            if (i5 >= iArr4.length) {
                break;
            }
            DEFAULT_LEAP_MONTH_LENGTHS[i5] = Integer.valueOf(iArr4[i5]);
            i5++;
        }
        DEFAULT_CYCLE_YEARS = new Integer[CYCLEYEAR_START_DATE.length];
        int i6 = 0;
        while (true) {
            int[] iArr5 = CYCLEYEAR_START_DATE;
            if (i6 >= iArr5.length) {
                break;
            }
            DEFAULT_CYCLE_YEARS[i6] = Integer.valueOf(iArr5[i6]);
            i6++;
        }
        ADJUSTED_CYCLES = new Long[334];
        int i7 = 0;
        while (true) {
            Long[] lArr = ADJUSTED_CYCLES;
            if (i7 >= lArr.length) {
                break;
            }
            lArr[i7] = Long.valueOf(i7 * 10631);
            i7++;
        }
        ADJUSTED_MIN_VALUES = new Integer[MIN_VALUES.length];
        int i8 = 0;
        while (true) {
            int[] iArr6 = MIN_VALUES;
            if (i8 >= iArr6.length) {
                break;
            }
            ADJUSTED_MIN_VALUES[i8] = Integer.valueOf(iArr6[i8]);
            i8++;
        }
        ADJUSTED_LEAST_MAX_VALUES = new Integer[LEAST_MAX_VALUES.length];
        int i9 = 0;
        while (true) {
            int[] iArr7 = LEAST_MAX_VALUES;
            if (i9 >= iArr7.length) {
                break;
            }
            ADJUSTED_LEAST_MAX_VALUES[i9] = Integer.valueOf(iArr7[i9]);
            i9++;
        }
        ADJUSTED_MAX_VALUES = new Integer[MAX_VALUES.length];
        while (true) {
            int[] iArr8 = MAX_VALUES;
            if (i < iArr8.length) {
                ADJUSTED_MAX_VALUES[i] = Integer.valueOf(iArr8[i]);
                i++;
            } else {
                try {
                    readDeviationConfig();
                    return;
                } catch (IOException | ParseException unused) {
                    return;
                }
            }
        }
    }

    private HijrahDate(long j) {
        int[] hijrahDateInfo = getHijrahDateInfo(j);
        checkValidYearOfEra(hijrahDateInfo[1]);
        checkValidMonth(hijrahDateInfo[2]);
        checkValidDayOfMonth(hijrahDateInfo[3]);
        checkValidDayOfYear(hijrahDateInfo[4]);
        this.era = HijrahEra.of(hijrahDateInfo[0]);
        this.yearOfEra = hijrahDateInfo[1];
        this.monthOfYear = hijrahDateInfo[2];
        this.dayOfMonth = hijrahDateInfo[3];
        this.dayOfYear = hijrahDateInfo[4];
        this.dayOfWeek = DayOfWeek.of(hijrahDateInfo[5]);
        this.gregorianEpochDay = j;
        this.isLeapYear = isLeapYear(this.yearOfEra);
    }

    private static void addDeviationAsHijrah(int i, int i2, int i3, int i4, int i5) {
        if (i >= 1) {
            if (i3 < 1) {
                throw new IllegalArgumentException("endYear < 1");
            }
            if (i2 < 0 || i2 > 11) {
                throw new IllegalArgumentException("startMonth < 0 || startMonth > 11");
            }
            if (i4 < 0 || i4 > 11) {
                throw new IllegalArgumentException("endMonth < 0 || endMonth > 11");
            }
            if (i3 > 9999) {
                throw new IllegalArgumentException("endYear > 9999");
            }
            if (i3 < i) {
                throw new IllegalArgumentException("startYear > endYear");
            }
            if (i3 == i && i4 < i2) {
                throw new IllegalArgumentException("startYear == endYear && endMonth < startMonth");
            }
            boolean isLeapYear = isLeapYear(i);
            Integer[] numArr = ADJUSTED_MONTH_DAYS.get(Integer.valueOf(i));
            if (numArr == null) {
                if (isLeapYear) {
                    numArr = new Integer[LEAP_NUM_DAYS.length];
                    int i6 = 0;
                    while (true) {
                        int[] iArr = LEAP_NUM_DAYS;
                        if (i6 >= iArr.length) {
                            break;
                        }
                        numArr[i6] = Integer.valueOf(iArr[i6]);
                        i6++;
                    }
                } else {
                    numArr = new Integer[NUM_DAYS.length];
                    int i7 = 0;
                    while (true) {
                        int[] iArr2 = NUM_DAYS;
                        if (i7 >= iArr2.length) {
                            break;
                        }
                        numArr[i7] = Integer.valueOf(iArr2[i7]);
                        i7++;
                    }
                }
            }
            Integer[] numArr2 = new Integer[numArr.length];
            for (int i8 = 0; i8 < 12; i8++) {
                if (i8 > i2) {
                    numArr2[i8] = Integer.valueOf(numArr[i8].intValue() - i5);
                } else {
                    numArr2[i8] = Integer.valueOf(numArr[i8].intValue());
                }
            }
            ADJUSTED_MONTH_DAYS.put(Integer.valueOf(i), numArr2);
            Integer[] numArr3 = ADJUSTED_MONTH_LENGTHS.get(Integer.valueOf(i));
            if (numArr3 == null) {
                if (isLeapYear) {
                    numArr3 = new Integer[LEAP_MONTH_LENGTH.length];
                    int i9 = 0;
                    while (true) {
                        int[] iArr3 = LEAP_MONTH_LENGTH;
                        if (i9 >= iArr3.length) {
                            break;
                        }
                        numArr3[i9] = Integer.valueOf(iArr3[i9]);
                        i9++;
                    }
                } else {
                    numArr3 = new Integer[MONTH_LENGTH.length];
                    int i10 = 0;
                    while (true) {
                        int[] iArr4 = MONTH_LENGTH;
                        if (i10 >= iArr4.length) {
                            break;
                        }
                        numArr3[i10] = Integer.valueOf(iArr4[i10]);
                        i10++;
                    }
                }
            }
            Integer[] numArr4 = new Integer[numArr3.length];
            for (int i11 = 0; i11 < 12; i11++) {
                if (i11 == i2) {
                    numArr4[i11] = Integer.valueOf(numArr3[i11].intValue() - i5);
                } else {
                    numArr4[i11] = Integer.valueOf(numArr3[i11].intValue());
                }
            }
            ADJUSTED_MONTH_LENGTHS.put(Integer.valueOf(i), numArr4);
            if (i != i3) {
                int i12 = i - 1;
                int i13 = i12 / 30;
                int i14 = i12 % 30;
                Integer[] numArr5 = ADJUSTED_CYCLE_YEARS.get(Integer.valueOf(i13));
                if (numArr5 == null) {
                    numArr5 = new Integer[CYCLEYEAR_START_DATE.length];
                    for (int i15 = 0; i15 < numArr5.length; i15++) {
                        numArr5[i15] = Integer.valueOf(CYCLEYEAR_START_DATE[i15]);
                    }
                }
                for (int i16 = i14 + 1; i16 < CYCLEYEAR_START_DATE.length; i16++) {
                    numArr5[i16] = Integer.valueOf(numArr5[i16].intValue() - i5);
                }
                ADJUSTED_CYCLE_YEARS.put(Integer.valueOf(i13), numArr5);
                int i17 = i3 - 1;
                int i18 = i17 / 30;
                if (i13 != i18) {
                    int i19 = i13 + 1;
                    while (true) {
                        Long[] lArr = ADJUSTED_CYCLES;
                        if (i19 >= lArr.length) {
                            break;
                        }
                        lArr[i19] = Long.valueOf(lArr[i19].longValue() - i5);
                        i19++;
                    }
                    int i20 = i18 + 1;
                    while (true) {
                        Long[] lArr2 = ADJUSTED_CYCLES;
                        if (i20 >= lArr2.length) {
                            break;
                        }
                        lArr2[i20] = Long.valueOf(lArr2[i20].longValue() + i5);
                        i20++;
                        i18 = i18;
                    }
                }
                int i21 = i18;
                int i22 = i17 % 30;
                Integer[] numArr6 = ADJUSTED_CYCLE_YEARS.get(Integer.valueOf(i21));
                if (numArr6 == null) {
                    numArr6 = new Integer[CYCLEYEAR_START_DATE.length];
                    for (int i23 = 0; i23 < numArr6.length; i23++) {
                        numArr6[i23] = Integer.valueOf(CYCLEYEAR_START_DATE[i23]);
                    }
                }
                for (int i24 = i22 + 1; i24 < CYCLEYEAR_START_DATE.length; i24++) {
                    numArr6[i24] = Integer.valueOf(numArr6[i24].intValue() + i5);
                }
                ADJUSTED_CYCLE_YEARS.put(Integer.valueOf(i21), numArr6);
            }
            boolean isLeapYear2 = isLeapYear(i3);
            Integer[] numArr7 = ADJUSTED_MONTH_DAYS.get(Integer.valueOf(i3));
            if (numArr7 == null) {
                if (isLeapYear2) {
                    numArr7 = new Integer[LEAP_NUM_DAYS.length];
                    int i25 = 0;
                    while (true) {
                        int[] iArr5 = LEAP_NUM_DAYS;
                        if (i25 >= iArr5.length) {
                            break;
                        }
                        numArr7[i25] = Integer.valueOf(iArr5[i25]);
                        i25++;
                    }
                } else {
                    numArr7 = new Integer[NUM_DAYS.length];
                    int i26 = 0;
                    while (true) {
                        int[] iArr6 = NUM_DAYS;
                        if (i26 >= iArr6.length) {
                            break;
                        }
                        numArr7[i26] = Integer.valueOf(iArr6[i26]);
                        i26++;
                    }
                }
            }
            Integer[] numArr8 = new Integer[numArr7.length];
            for (int i27 = 0; i27 < 12; i27++) {
                if (i27 > i4) {
                    numArr8[i27] = Integer.valueOf(numArr7[i27].intValue() + i5);
                } else {
                    numArr8[i27] = Integer.valueOf(numArr7[i27].intValue());
                }
            }
            ADJUSTED_MONTH_DAYS.put(Integer.valueOf(i3), numArr8);
            Integer[] numArr9 = ADJUSTED_MONTH_LENGTHS.get(Integer.valueOf(i3));
            if (numArr9 == null) {
                if (isLeapYear2) {
                    numArr9 = new Integer[LEAP_MONTH_LENGTH.length];
                    int i28 = 0;
                    while (true) {
                        int[] iArr7 = LEAP_MONTH_LENGTH;
                        if (i28 >= iArr7.length) {
                            break;
                        }
                        numArr9[i28] = Integer.valueOf(iArr7[i28]);
                        i28++;
                    }
                } else {
                    numArr9 = new Integer[MONTH_LENGTH.length];
                    int i29 = 0;
                    while (true) {
                        int[] iArr8 = MONTH_LENGTH;
                        if (i29 >= iArr8.length) {
                            break;
                        }
                        numArr9[i29] = Integer.valueOf(iArr8[i29]);
                        i29++;
                    }
                }
            }
            Integer[] numArr10 = new Integer[numArr9.length];
            for (int i30 = 0; i30 < 12; i30++) {
                if (i30 == i4) {
                    numArr10[i30] = Integer.valueOf(numArr9[i30].intValue() + i5);
                } else {
                    numArr10[i30] = Integer.valueOf(numArr9[i30].intValue());
                }
            }
            ADJUSTED_MONTH_LENGTHS.put(Integer.valueOf(i3), numArr10);
            Integer[] numArr11 = ADJUSTED_MONTH_LENGTHS.get(Integer.valueOf(i));
            Integer[] numArr12 = ADJUSTED_MONTH_LENGTHS.get(Integer.valueOf(i3));
            int intValue = numArr11[i2].intValue();
            int intValue2 = numArr12[i4].intValue();
            int intValue3 = numArr11[11].intValue() + ADJUSTED_MONTH_DAYS.get(Integer.valueOf(i))[11].intValue();
            int intValue4 = numArr12[11].intValue() + ADJUSTED_MONTH_DAYS.get(Integer.valueOf(i3))[11].intValue();
            int intValue5 = ADJUSTED_MAX_VALUES[5].intValue();
            int intValue6 = ADJUSTED_LEAST_MAX_VALUES[5].intValue();
            if (intValue5 < intValue) {
                intValue5 = intValue;
            }
            if (intValue5 < intValue2) {
                intValue5 = intValue2;
            }
            ADJUSTED_MAX_VALUES[5] = Integer.valueOf(intValue5);
            if (intValue6 <= intValue) {
                intValue = intValue6;
            }
            if (intValue > intValue2) {
                intValue = intValue2;
            }
            ADJUSTED_LEAST_MAX_VALUES[5] = Integer.valueOf(intValue);
            int intValue7 = ADJUSTED_MAX_VALUES[6].intValue();
            int intValue8 = ADJUSTED_LEAST_MAX_VALUES[6].intValue();
            if (intValue7 < intValue3) {
                intValue7 = intValue3;
            }
            if (intValue7 < intValue4) {
                intValue7 = intValue4;
            }
            ADJUSTED_MAX_VALUES[6] = Integer.valueOf(intValue7);
            if (intValue8 > intValue3) {
                intValue8 = intValue3;
            }
            if (intValue8 <= intValue4) {
                intValue4 = intValue8;
            }
            ADJUSTED_LEAST_MAX_VALUES[6] = Integer.valueOf(intValue4);
            return;
        }
        throw new IllegalArgumentException("startYear < 1");
    }

    private static void checkValidDayOfMonth(int i) {
        if (i < 1 || i > getMaximumDayOfMonth()) {
            throw new DateTimeException(GeneratedOutlineSupport1.outline86(GeneratedOutlineSupport1.outline109("Invalid day of month of Hijrah date, day ", i, " greater than "), getMaximumDayOfMonth(), " or less than 1"));
        }
    }

    private static void checkValidDayOfYear(int i) {
        if (i < 1 || i > getMaximumDayOfYear()) {
            throw new DateTimeException("Invalid day of year of Hijrah date");
        }
    }

    private static void checkValidMonth(int i) {
        if (i < 1 || i > 12) {
            throw new DateTimeException("Invalid month of Hijrah date");
        }
    }

    private static void checkValidYearOfEra(int i) {
        if (i < 1 || i > 9999) {
            throw new DateTimeException("Invalid year of Hijrah Era");
        }
    }

    public static HijrahDate from(TemporalAccessor temporalAccessor) {
        return HijrahChronology.INSTANCE.mo13045date(temporalAccessor);
    }

    private static Integer[] getAdjustedCycle(int i) {
        Integer[] numArr;
        try {
            numArr = ADJUSTED_CYCLE_YEARS.get(Integer.valueOf(i));
        } catch (ArrayIndexOutOfBoundsException unused) {
            numArr = null;
        }
        return numArr == null ? DEFAULT_CYCLE_YEARS : numArr;
    }

    private static Integer[] getAdjustedMonthDays(int i) {
        Integer[] numArr;
        try {
            numArr = ADJUSTED_MONTH_DAYS.get(Integer.valueOf(i));
        } catch (ArrayIndexOutOfBoundsException unused) {
            numArr = null;
        }
        if (numArr == null) {
            if (isLeapYear(i)) {
                return DEFAULT_LEAP_MONTH_DAYS;
            }
            return DEFAULT_MONTH_DAYS;
        }
        return numArr;
    }

    private static Integer[] getAdjustedMonthLength(int i) {
        Integer[] numArr;
        try {
            numArr = ADJUSTED_MONTH_LENGTHS.get(Integer.valueOf(i));
        } catch (ArrayIndexOutOfBoundsException unused) {
            numArr = null;
        }
        if (numArr == null) {
            if (isLeapYear(i)) {
                return DEFAULT_LEAP_MONTH_LENGTHS;
            }
            return DEFAULT_MONTH_LENGTHS;
        }
        return numArr;
    }

    private static InputStream getConfigFileInputStream() throws IOException {
        ZipFile zipFile;
        String property = System.getProperty("org.threeten.bp.i18n.HijrahDate.deviationConfigFile");
        if (property == null) {
            property = DEFAULT_CONFIG_FILENAME;
        }
        String property2 = System.getProperty("org.threeten.bp.i18n.HijrahDate.deviationConfigDir");
        if (property2 != null) {
            if (property2.length() != 0 || !property2.endsWith(System.getProperty("file.separator"))) {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107(property2);
                outline107.append(System.getProperty("file.separator"));
                property2 = outline107.toString();
            }
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107(property2);
            outline1072.append(FILE_SEP);
            outline1072.append(property);
            File file = new File(outline1072.toString());
            if (!file.exists()) {
                return null;
            }
            try {
                return new FileInputStream(file);
            } catch (IOException e) {
                throw e;
            }
        }
        StringTokenizer stringTokenizer = new StringTokenizer(System.getProperty("java.class.path"), PATH_SEP);
        while (stringTokenizer.hasMoreTokens()) {
            String nextToken = stringTokenizer.nextToken();
            File file2 = new File(nextToken);
            if (file2.exists()) {
                if (file2.isDirectory()) {
                    StringBuilder outline1073 = GeneratedOutlineSupport1.outline107(nextToken);
                    outline1073.append(FILE_SEP);
                    outline1073.append(DEFAULT_CONFIG_PATH);
                    if (new File(outline1073.toString(), property).exists()) {
                        try {
                            return new FileInputStream(nextToken + FILE_SEP + DEFAULT_CONFIG_PATH + FILE_SEP + property);
                        } catch (IOException e2) {
                            throw e2;
                        }
                    }
                } else {
                    try {
                        zipFile = new ZipFile(file2);
                    } catch (IOException unused) {
                        zipFile = null;
                    }
                    if (zipFile != null) {
                        String str = DEFAULT_CONFIG_PATH + FILE_SEP + property;
                        ZipEntry entry = zipFile.getEntry(str);
                        if (entry == null) {
                            char c = FILE_SEP;
                            if (c == '/') {
                                str = str.replace('/', '\\');
                            } else if (c == '\\') {
                                str = str.replace('\\', '/');
                            }
                            entry = zipFile.getEntry(str);
                        }
                        if (entry != null) {
                            try {
                                return zipFile.getInputStream(entry);
                            } catch (IOException e3) {
                                throw e3;
                            }
                        }
                    } else {
                        continue;
                    }
                }
            }
        }
        return null;
    }

    private static int getCycleNumber(long j) {
        Long[] lArr = ADJUSTED_CYCLES;
        for (int i = 0; i < lArr.length; i++) {
            try {
                if (j < lArr[i].longValue()) {
                    return i - 1;
                }
            } catch (ArrayIndexOutOfBoundsException unused) {
                return ((int) j) / 10631;
            }
        }
        return ((int) j) / 10631;
    }

    private static int getDayOfCycle(long j, int i) {
        Long l;
        try {
            l = ADJUSTED_CYCLES[i];
        } catch (ArrayIndexOutOfBoundsException unused) {
            l = null;
        }
        if (l == null) {
            l = Long.valueOf(i * 10631);
        }
        return (int) (j - l.longValue());
    }

    private static int getDayOfMonth(int i, int i2, int i3) {
        int intValue;
        Integer[] adjustedMonthDays = getAdjustedMonthDays(i3);
        if (i < 0) {
            i = isLeapYear((long) i3) ? i + 355 : i + 354;
            if (i2 <= 0) {
                return i;
            }
            intValue = adjustedMonthDays[i2].intValue();
        } else if (i2 <= 0) {
            return i;
        } else {
            intValue = adjustedMonthDays[i2].intValue();
        }
        return i - intValue;
    }

    private static int getDayOfYear(int i, int i2, int i3) {
        Integer[] adjustedCycle = getAdjustedCycle(i);
        if (i2 > 0) {
            return i2 - adjustedCycle[i3].intValue();
        }
        return adjustedCycle[i3].intValue() + i2;
    }

    private static long getGregorianEpochDay(int i, int i2, int i3) {
        return yearToGregorianEpochDay(i) + getMonthDays(i2 - 1, i) + i3;
    }

    private static int[] getHijrahDateInfo(long j) {
        int monthOfYear;
        int dayOfMonth;
        int value;
        int i;
        int i2;
        long j2 = j - (-492148);
        if (j2 >= 0) {
            int cycleNumber = getCycleNumber(j2);
            int dayOfCycle = getDayOfCycle(j2, cycleNumber);
            int yearInCycle = getYearInCycle(cycleNumber, dayOfCycle);
            i = getDayOfYear(cycleNumber, dayOfCycle, yearInCycle);
            i2 = GeneratedOutlineSupport1.outline3(cycleNumber, 30, yearInCycle, 1);
            monthOfYear = getMonthOfYear(i, i2);
            dayOfMonth = getDayOfMonth(i, monthOfYear, i2) + 1;
            value = HijrahEra.AH.getValue();
        } else {
            int i3 = (int) j2;
            int i4 = i3 / 10631;
            int i5 = i3 % 10631;
            if (i5 == 0) {
                i5 = -10631;
                i4++;
            }
            int yearInCycle2 = getYearInCycle(i4, i5);
            int dayOfYear = getDayOfYear(i4, i5, yearInCycle2);
            int i6 = 1 - ((i4 * 30) - yearInCycle2);
            int i7 = isLeapYear((long) i6) ? dayOfYear + 355 : dayOfYear + 354;
            monthOfYear = getMonthOfYear(i7, i6);
            dayOfMonth = getDayOfMonth(i7, monthOfYear, i6) + 1;
            value = HijrahEra.BEFORE_AH.getValue();
            i = i7;
            i2 = i6;
        }
        int i8 = (int) ((j2 + 5) % 7);
        return new int[]{value, i2, monthOfYear + 1, dayOfMonth, i + 1, i8 + (i8 <= 0 ? 7 : 0)};
    }

    static int getMaximumDayOfMonth() {
        return ADJUSTED_MAX_VALUES[5].intValue();
    }

    static int getMaximumDayOfYear() {
        return ADJUSTED_MAX_VALUES[6].intValue();
    }

    private static int getMonthDays(int i, int i2) {
        return getAdjustedMonthDays(i2)[i].intValue();
    }

    static int getMonthLength(int i, int i2) {
        return getAdjustedMonthLength(i2)[i].intValue();
    }

    private static int getMonthOfYear(int i, int i2) {
        Integer[] adjustedMonthDays = getAdjustedMonthDays(i2);
        int i3 = 0;
        if (i >= 0) {
            while (i3 < adjustedMonthDays.length) {
                if (i < adjustedMonthDays[i3].intValue()) {
                    return i3 - 1;
                }
                i3++;
            }
            return 11;
        }
        int i4 = isLeapYear((long) i2) ? i + 355 : i + 354;
        while (i3 < adjustedMonthDays.length) {
            if (i4 < adjustedMonthDays[i3].intValue()) {
                return i3 - 1;
            }
            i3++;
        }
        return 11;
    }

    static int getSmallestMaximumDayOfMonth() {
        return ADJUSTED_LEAST_MAX_VALUES[5].intValue();
    }

    static int getSmallestMaximumDayOfYear() {
        return ADJUSTED_LEAST_MAX_VALUES[6].intValue();
    }

    private static int getYearInCycle(int i, long j) {
        Integer[] adjustedCycle = getAdjustedCycle(i);
        int i2 = (j > 0L ? 1 : (j == 0L ? 0 : -1));
        int i3 = 0;
        if (i2 == 0) {
            return 0;
        }
        if (i2 > 0) {
            while (i3 < adjustedCycle.length) {
                if (j < adjustedCycle[i3].intValue()) {
                    return i3 - 1;
                }
                i3++;
            }
            return 29;
        }
        long j2 = -j;
        while (i3 < adjustedCycle.length) {
            if (j2 <= adjustedCycle[i3].intValue()) {
                return i3 - 1;
            }
            i3++;
        }
        return 29;
    }

    static int getYearLength(int i) {
        Integer[] numArr;
        int intValue;
        int intValue2;
        int i2 = i - 1;
        int i3 = i2 / 30;
        try {
            numArr = ADJUSTED_CYCLE_YEARS.get(Integer.valueOf(i3));
        } catch (ArrayIndexOutOfBoundsException unused) {
            numArr = null;
        }
        if (numArr == null) {
            return isLeapYear((long) i) ? 355 : 354;
        }
        int i4 = i2 % 30;
        if (i4 == 29) {
            intValue = ADJUSTED_CYCLES[i3 + 1].intValue() - ADJUSTED_CYCLES[i3].intValue();
            intValue2 = numArr[i4].intValue();
        } else {
            intValue = numArr[i4 + 1].intValue();
            intValue2 = numArr[i4].intValue();
        }
        return intValue - intValue2;
    }

    public static HijrahDate now() {
        return now(Clock.systemDefaultZone());
    }

    public static HijrahDate of(int i, int i2, int i3) {
        if (i >= 1) {
            return of(HijrahEra.AH, i, i2, i3);
        }
        return of(HijrahEra.BEFORE_AH, 1 - i, i2, i3);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static HijrahDate ofEpochDay(long j) {
        return new HijrahDate(j);
    }

    private static void parseLine(String str, int i) throws ParseException {
        StringTokenizer stringTokenizer = new StringTokenizer(str, ";");
        while (stringTokenizer.hasMoreTokens()) {
            String nextToken = stringTokenizer.nextToken();
            int indexOf = nextToken.indexOf(58);
            if (indexOf != -1) {
                try {
                    int parseInt = Integer.parseInt(nextToken.substring(indexOf + 1, nextToken.length()));
                    int indexOf2 = nextToken.indexOf(45);
                    if (indexOf2 != -1) {
                        String substring = nextToken.substring(0, indexOf2);
                        String substring2 = nextToken.substring(indexOf2 + 1, indexOf);
                        int indexOf3 = substring.indexOf(47);
                        int indexOf4 = substring2.indexOf(47);
                        if (indexOf3 != -1) {
                            String substring3 = substring.substring(0, indexOf3);
                            String substring4 = substring.substring(indexOf3 + 1, substring.length());
                            try {
                                int parseInt2 = Integer.parseInt(substring3);
                                try {
                                    int parseInt3 = Integer.parseInt(substring4);
                                    if (indexOf4 != -1) {
                                        String substring5 = substring2.substring(0, indexOf4);
                                        String substring6 = substring2.substring(indexOf4 + 1, substring2.length());
                                        try {
                                            int parseInt4 = Integer.parseInt(substring5);
                                            try {
                                                int parseInt5 = Integer.parseInt(substring6);
                                                if (parseInt2 != -1 && parseInt3 != -1 && parseInt4 != -1 && parseInt5 != -1) {
                                                    addDeviationAsHijrah(parseInt2, parseInt3, parseInt4, parseInt5, parseInt);
                                                } else {
                                                    throw new ParseException(GeneratedOutlineSupport1.outline52("Unknown error at line ", i, "."), i);
                                                }
                                            } catch (NumberFormatException unused) {
                                                throw new ParseException(GeneratedOutlineSupport1.outline52("End month is not properly set at line ", i, "."), i);
                                            }
                                        } catch (NumberFormatException unused2) {
                                            throw new ParseException(GeneratedOutlineSupport1.outline52("End year is not properly set at line ", i, "."), i);
                                        }
                                    } else {
                                        throw new ParseException(GeneratedOutlineSupport1.outline52("End year/month has incorrect format at line ", i, "."), i);
                                    }
                                } catch (NumberFormatException unused3) {
                                    throw new ParseException(GeneratedOutlineSupport1.outline52("Start month is not properly set at line ", i, "."), i);
                                }
                            } catch (NumberFormatException unused4) {
                                throw new ParseException(GeneratedOutlineSupport1.outline52("Start year is not properly set at line ", i, "."), i);
                            }
                        } else {
                            throw new ParseException(GeneratedOutlineSupport1.outline52("Start year/month has incorrect format at line ", i, "."), i);
                        }
                    } else {
                        throw new ParseException(GeneratedOutlineSupport1.outline52("Start and end year/month has incorrect format at line ", i, "."), i);
                    }
                } catch (NumberFormatException unused5) {
                    throw new ParseException(GeneratedOutlineSupport1.outline52("Offset is not properly set at line ", i, "."), i);
                }
            } else {
                throw new ParseException(GeneratedOutlineSupport1.outline52("Offset has incorrect format at line ", i, "."), i);
            }
        }
    }

    private static void readDeviationConfig() throws IOException, ParseException {
        InputStream configFileInputStream = getConfigFileInputStream();
        if (configFileInputStream != null) {
            BufferedReader bufferedReader = null;
            try {
                BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(configFileInputStream));
                int i = 0;
                while (true) {
                    try {
                        String readLine = bufferedReader2.readLine();
                        if (readLine != null) {
                            i++;
                            parseLine(readLine.trim(), i);
                        } else {
                            bufferedReader2.close();
                            return;
                        }
                    } catch (Throwable th) {
                        th = th;
                        bufferedReader = bufferedReader2;
                        if (bufferedReader != null) {
                            bufferedReader.close();
                        }
                        throw th;
                    }
                }
            } catch (Throwable th2) {
                th = th2;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ChronoLocalDate readExternal(DataInput dataInput) throws IOException {
        return HijrahChronology.INSTANCE.mo13043date(dataInput.readInt(), (int) dataInput.readByte(), (int) dataInput.readByte());
    }

    private Object readResolve() {
        return new HijrahDate(this.gregorianEpochDay);
    }

    private static HijrahDate resolvePreviousValid(int i, int i2, int i3) {
        int monthDays = getMonthDays(i2 - 1, i);
        if (i3 > monthDays) {
            i3 = monthDays;
        }
        return of(i, i2, i3);
    }

    private Object writeReplace() {
        return new Ser((byte) 3, this);
    }

    private static long yearToGregorianEpochDay(int i) {
        Long l;
        int i2 = i - 1;
        int i3 = i2 / 30;
        int i4 = i2 % 30;
        int intValue = getAdjustedCycle(i3)[Math.abs(i4)].intValue();
        if (i4 < 0) {
            intValue = -intValue;
        }
        try {
            l = ADJUSTED_CYCLES[i3];
        } catch (ArrayIndexOutOfBoundsException unused) {
            l = null;
        }
        if (l == null) {
            l = Long.valueOf(i3 * 10631);
        }
        return ((l.longValue() + intValue) - 492148) - 1;
    }

    @Override // org.threeten.bp.chrono.ChronoDateImpl, org.threeten.bp.chrono.ChronoLocalDate
    /* renamed from: atTime */
    public final ChronoLocalDateTime<HijrahDate> mo12887atTime(LocalTime localTime) {
        return super.mo12887atTime(localTime);
    }

    @Override // org.threeten.bp.temporal.TemporalAccessor
    public long getLong(TemporalField temporalField) {
        int value;
        int i;
        if (temporalField instanceof ChronoField) {
            switch (((ChronoField) temporalField).ordinal()) {
                case 15:
                    value = this.dayOfWeek.getValue();
                    break;
                case 16:
                    i = (this.dayOfMonth - 1) % 7;
                    value = i + 1;
                    break;
                case 17:
                    i = (this.dayOfYear - 1) % 7;
                    value = i + 1;
                    break;
                case 18:
                    value = this.dayOfMonth;
                    break;
                case 19:
                    value = this.dayOfYear;
                    break;
                case 20:
                    return toEpochDay();
                case 21:
                    i = (this.dayOfMonth - 1) / 7;
                    value = i + 1;
                    break;
                case 22:
                    i = (this.dayOfYear - 1) / 7;
                    value = i + 1;
                    break;
                case 23:
                    value = this.monthOfYear;
                    break;
                case 24:
                default:
                    throw new UnsupportedTemporalTypeException(GeneratedOutlineSupport1.outline82("Unsupported field: ", temporalField));
                case 25:
                    value = this.yearOfEra;
                    break;
                case 26:
                    value = this.yearOfEra;
                    break;
                case 27:
                    value = this.era.getValue();
                    break;
            }
            return value;
        }
        return temporalField.getFrom(this);
    }

    @Override // org.threeten.bp.chrono.ChronoLocalDate
    public boolean isLeapYear() {
        return this.isLeapYear;
    }

    @Override // org.threeten.bp.chrono.ChronoLocalDate
    public int lengthOfMonth() {
        return getMonthLength(this.monthOfYear - 1, this.yearOfEra);
    }

    @Override // org.threeten.bp.chrono.ChronoLocalDate
    public int lengthOfYear() {
        return getYearLength(this.yearOfEra);
    }

    @Override // org.threeten.bp.jdk8.DefaultInterfaceTemporalAccessor, org.threeten.bp.temporal.TemporalAccessor
    public ValueRange range(TemporalField temporalField) {
        if (temporalField instanceof ChronoField) {
            if (isSupported(temporalField)) {
                ChronoField chronoField = (ChronoField) temporalField;
                int ordinal = chronoField.ordinal();
                if (ordinal == 18) {
                    return ValueRange.of(1L, lengthOfMonth());
                }
                if (ordinal == 19) {
                    return ValueRange.of(1L, lengthOfYear());
                }
                if (ordinal == 21) {
                    return ValueRange.of(1L, 5L);
                }
                if (ordinal != 25) {
                    return mo13054getChronology().range(chronoField);
                }
                return ValueRange.of(1L, 1000L);
            }
            throw new UnsupportedTemporalTypeException(GeneratedOutlineSupport1.outline82("Unsupported field: ", temporalField));
        }
        return temporalField.rangeRefinedBy(this);
    }

    @Override // org.threeten.bp.chrono.ChronoLocalDate
    public long toEpochDay() {
        return getGregorianEpochDay(this.yearOfEra, this.monthOfYear, this.dayOfMonth);
    }

    @Override // org.threeten.bp.chrono.ChronoDateImpl, org.threeten.bp.temporal.Temporal
    public /* bridge */ /* synthetic */ long until(Temporal temporal, TemporalUnit temporalUnit) {
        return super.until(temporal, temporalUnit);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void writeExternal(DataOutput dataOutput) throws IOException {
        dataOutput.writeInt(get(ChronoField.YEAR));
        dataOutput.writeByte(get(ChronoField.MONTH_OF_YEAR));
        dataOutput.writeByte(get(ChronoField.DAY_OF_MONTH));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isLeapYear(long j) {
        if (j <= 0) {
            j = -j;
        }
        return ((j * 11) + 14) % 30 < 11;
    }

    public static HijrahDate now(ZoneId zoneId) {
        return now(Clock.system(zoneId));
    }

    @Override // org.threeten.bp.chrono.ChronoLocalDate
    /* renamed from: getChronology  reason: collision with other method in class */
    public HijrahChronology mo13054getChronology() {
        return HijrahChronology.INSTANCE;
    }

    @Override // org.threeten.bp.chrono.ChronoLocalDate
    /* renamed from: getEra  reason: collision with other method in class */
    public HijrahEra mo13055getEra() {
        return this.era;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.threeten.bp.chrono.ChronoDateImpl
    public ChronoDateImpl<HijrahDate> plusDays(long j) {
        return new HijrahDate(this.gregorianEpochDay + j);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.threeten.bp.chrono.ChronoDateImpl
    public ChronoDateImpl<HijrahDate> plusMonths(long j) {
        if (j == 0) {
            return this;
        }
        int i = (this.monthOfYear - 1) + ((int) j);
        int i2 = i / 12;
        int i3 = i % 12;
        while (i3 < 0) {
            i3 += 12;
            i2 = Jdk8Methods.safeSubtract(i2, 1);
        }
        return of(this.era, Jdk8Methods.safeAdd(this.yearOfEra, i2), i3 + 1, this.dayOfMonth);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.threeten.bp.chrono.ChronoDateImpl
    public ChronoDateImpl<HijrahDate> plusYears(long j) {
        if (j == 0) {
            return this;
        }
        return of(this.era, Jdk8Methods.safeAdd(this.yearOfEra, (int) j), this.monthOfYear, this.dayOfMonth);
    }

    @Override // org.threeten.bp.chrono.ChronoDateImpl, org.threeten.bp.chrono.ChronoLocalDate
    /* renamed from: until */
    public /* bridge */ /* synthetic */ ChronoPeriod mo12893until(ChronoLocalDate chronoLocalDate) {
        return super.mo12893until(chronoLocalDate);
    }

    public static HijrahDate now(Clock clock) {
        return HijrahChronology.INSTANCE.mo13048dateNow(clock);
    }

    static HijrahDate of(HijrahEra hijrahEra, int i, int i2, int i3) {
        Jdk8Methods.requireNonNull(hijrahEra, "era");
        checkValidYearOfEra(i);
        checkValidMonth(i2);
        checkValidDayOfMonth(i3);
        return new HijrahDate(getGregorianEpochDay(hijrahEra.prolepticYear(i), i2, i3));
    }

    @Override // org.threeten.bp.chrono.ChronoLocalDate, org.threeten.bp.jdk8.DefaultInterfaceTemporal, org.threeten.bp.temporal.Temporal
    /* renamed from: minus  reason: collision with other method in class */
    public HijrahDate mo13057minus(TemporalAmount temporalAmount) {
        return (HijrahDate) super.mo13057minus(temporalAmount);
    }

    @Override // org.threeten.bp.chrono.ChronoLocalDate, org.threeten.bp.jdk8.DefaultInterfaceTemporal, org.threeten.bp.temporal.Temporal
    /* renamed from: with  reason: collision with other method in class */
    public HijrahDate mo13061with(TemporalAdjuster temporalAdjuster) {
        return (HijrahDate) super.mo13061with(temporalAdjuster);
    }

    @Override // org.threeten.bp.chrono.ChronoLocalDate, org.threeten.bp.jdk8.DefaultInterfaceTemporal, org.threeten.bp.temporal.Temporal
    /* renamed from: minus  reason: collision with other method in class */
    public HijrahDate mo13056minus(long j, TemporalUnit temporalUnit) {
        return (HijrahDate) super.mo13056minus(j, temporalUnit);
    }

    @Override // org.threeten.bp.chrono.ChronoLocalDate, org.threeten.bp.jdk8.DefaultInterfaceTemporal, org.threeten.bp.temporal.Temporal
    /* renamed from: plus  reason: collision with other method in class */
    public HijrahDate mo13059plus(TemporalAmount temporalAmount) {
        return (HijrahDate) super.mo13059plus(temporalAmount);
    }

    /* JADX WARN: Type inference failed for: r6v16, types: [org.threeten.bp.chrono.HijrahDate] */
    /* JADX WARN: Type inference failed for: r6v18, types: [org.threeten.bp.chrono.HijrahDate] */
    /* JADX WARN: Type inference failed for: r6v5, types: [org.threeten.bp.chrono.HijrahDate] */
    /* JADX WARN: Type inference failed for: r6v7, types: [org.threeten.bp.chrono.HijrahDate] */
    /* JADX WARN: Type inference failed for: r6v9, types: [org.threeten.bp.chrono.HijrahDate] */
    @Override // org.threeten.bp.chrono.ChronoLocalDate, org.threeten.bp.temporal.Temporal
    /* renamed from: with  reason: collision with other method in class */
    public HijrahDate mo13062with(TemporalField temporalField, long j) {
        if (temporalField instanceof ChronoField) {
            ChronoField chronoField = (ChronoField) temporalField;
            chronoField.checkValidValue(j);
            int i = (int) j;
            switch (chronoField.ordinal()) {
                case 15:
                    return plusDays(j - this.dayOfWeek.getValue());
                case 16:
                    return plusDays(j - getLong(ChronoField.ALIGNED_DAY_OF_WEEK_IN_MONTH));
                case 17:
                    return plusDays(j - getLong(ChronoField.ALIGNED_DAY_OF_WEEK_IN_YEAR));
                case 18:
                    return resolvePreviousValid(this.yearOfEra, this.monthOfYear, i);
                case 19:
                    int i2 = i - 1;
                    return resolvePreviousValid(this.yearOfEra, (i2 / 30) + 1, (i2 % 30) + 1);
                case 20:
                    return new HijrahDate(i);
                case 21:
                    return plusDays((j - getLong(ChronoField.ALIGNED_WEEK_OF_MONTH)) * 7);
                case 22:
                    return plusDays((j - getLong(ChronoField.ALIGNED_WEEK_OF_YEAR)) * 7);
                case 23:
                    return resolvePreviousValid(this.yearOfEra, i, this.dayOfMonth);
                case 24:
                default:
                    throw new UnsupportedTemporalTypeException(GeneratedOutlineSupport1.outline82("Unsupported field: ", temporalField));
                case 25:
                    if (this.yearOfEra < 1) {
                        i = 1 - i;
                    }
                    return resolvePreviousValid(i, this.monthOfYear, this.dayOfMonth);
                case 26:
                    return resolvePreviousValid(i, this.monthOfYear, this.dayOfMonth);
                case 27:
                    return resolvePreviousValid(1 - this.yearOfEra, this.monthOfYear, this.dayOfMonth);
            }
        }
        return (HijrahDate) temporalField.adjustInto(this, j);
    }

    @Override // org.threeten.bp.chrono.ChronoDateImpl, org.threeten.bp.chrono.ChronoLocalDate, org.threeten.bp.temporal.Temporal
    /* renamed from: plus */
    public HijrahDate mo13060plus(long j, TemporalUnit temporalUnit) {
        return (HijrahDate) super.mo13060plus(j, temporalUnit);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static HijrahDate of(LocalDate localDate) {
        return new HijrahDate(localDate.toEpochDay());
    }
}
