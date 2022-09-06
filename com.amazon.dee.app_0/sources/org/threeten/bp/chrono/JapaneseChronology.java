package org.threeten.bp.chrono;

import androidx.exifinterface.media.ExifInterface;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.threeten.bp.Clock;
import org.threeten.bp.DateTimeException;
import org.threeten.bp.DayOfWeek;
import org.threeten.bp.Instant;
import org.threeten.bp.LocalDate;
import org.threeten.bp.ZoneId;
import org.threeten.bp.format.ResolverStyle;
import org.threeten.bp.jdk8.Jdk8Methods;
import org.threeten.bp.temporal.ChronoField;
import org.threeten.bp.temporal.ChronoUnit;
import org.threeten.bp.temporal.TemporalAccessor;
import org.threeten.bp.temporal.TemporalAdjusters;
import org.threeten.bp.temporal.TemporalField;
import org.threeten.bp.temporal.TemporalUnit;
import org.threeten.bp.temporal.ValueRange;
/* loaded from: classes5.dex */
public final class JapaneseChronology extends Chronology implements Serializable {
    private static final String FALLBACK_LANGUAGE = "en";
    private static final long serialVersionUID = 459996390165777884L;
    private static final String TARGET_LANGUAGE = "ja";
    static final Locale LOCALE = new Locale(TARGET_LANGUAGE, "JP", "JP");
    public static final JapaneseChronology INSTANCE = new JapaneseChronology();
    private static final Map<String, String[]> ERA_NARROW_NAMES = new HashMap();
    private static final Map<String, String[]> ERA_SHORT_NAMES = new HashMap();
    private static final Map<String, String[]> ERA_FULL_NAMES = new HashMap();

    /* renamed from: org.threeten.bp.chrono.JapaneseChronology$1  reason: invalid class name */
    /* loaded from: classes5.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$threeten$bp$temporal$ChronoField = new int[ChronoField.values().length];

        static {
            try {
                $SwitchMap$org$threeten$bp$temporal$ChronoField[ChronoField.DAY_OF_MONTH.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$org$threeten$bp$temporal$ChronoField[ChronoField.DAY_OF_WEEK.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$org$threeten$bp$temporal$ChronoField[ChronoField.MICRO_OF_DAY.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$org$threeten$bp$temporal$ChronoField[ChronoField.MICRO_OF_SECOND.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$org$threeten$bp$temporal$ChronoField[ChronoField.HOUR_OF_DAY.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$org$threeten$bp$temporal$ChronoField[ChronoField.HOUR_OF_AMPM.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$org$threeten$bp$temporal$ChronoField[ChronoField.MINUTE_OF_DAY.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$org$threeten$bp$temporal$ChronoField[ChronoField.MINUTE_OF_HOUR.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$org$threeten$bp$temporal$ChronoField[ChronoField.SECOND_OF_DAY.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$org$threeten$bp$temporal$ChronoField[ChronoField.SECOND_OF_MINUTE.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$org$threeten$bp$temporal$ChronoField[ChronoField.MILLI_OF_DAY.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$org$threeten$bp$temporal$ChronoField[ChronoField.MILLI_OF_SECOND.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                $SwitchMap$org$threeten$bp$temporal$ChronoField[ChronoField.NANO_OF_DAY.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                $SwitchMap$org$threeten$bp$temporal$ChronoField[ChronoField.NANO_OF_SECOND.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                $SwitchMap$org$threeten$bp$temporal$ChronoField[ChronoField.CLOCK_HOUR_OF_DAY.ordinal()] = 15;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                $SwitchMap$org$threeten$bp$temporal$ChronoField[ChronoField.CLOCK_HOUR_OF_AMPM.ordinal()] = 16;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                $SwitchMap$org$threeten$bp$temporal$ChronoField[ChronoField.EPOCH_DAY.ordinal()] = 17;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                $SwitchMap$org$threeten$bp$temporal$ChronoField[ChronoField.PROLEPTIC_MONTH.ordinal()] = 18;
            } catch (NoSuchFieldError unused18) {
            }
            try {
                $SwitchMap$org$threeten$bp$temporal$ChronoField[ChronoField.ERA.ordinal()] = 19;
            } catch (NoSuchFieldError unused19) {
            }
            try {
                $SwitchMap$org$threeten$bp$temporal$ChronoField[ChronoField.YEAR.ordinal()] = 20;
            } catch (NoSuchFieldError unused20) {
            }
            try {
                $SwitchMap$org$threeten$bp$temporal$ChronoField[ChronoField.YEAR_OF_ERA.ordinal()] = 21;
            } catch (NoSuchFieldError unused21) {
            }
            try {
                $SwitchMap$org$threeten$bp$temporal$ChronoField[ChronoField.MONTH_OF_YEAR.ordinal()] = 22;
            } catch (NoSuchFieldError unused22) {
            }
            try {
                $SwitchMap$org$threeten$bp$temporal$ChronoField[ChronoField.DAY_OF_YEAR.ordinal()] = 23;
            } catch (NoSuchFieldError unused23) {
            }
        }
    }

    static {
        ERA_NARROW_NAMES.put(FALLBACK_LANGUAGE, new String[]{"Unknown", "K", "M", ExifInterface.GPS_DIRECTION_TRUE, ExifInterface.LATITUDE_SOUTH, "H"});
        ERA_NARROW_NAMES.put(TARGET_LANGUAGE, new String[]{"Unknown", "K", "M", ExifInterface.GPS_DIRECTION_TRUE, ExifInterface.LATITUDE_SOUTH, "H"});
        ERA_SHORT_NAMES.put(FALLBACK_LANGUAGE, new String[]{"Unknown", "K", "M", ExifInterface.GPS_DIRECTION_TRUE, ExifInterface.LATITUDE_SOUTH, "H"});
        ERA_SHORT_NAMES.put(TARGET_LANGUAGE, new String[]{"Unknown", "慶", "明", "大", "昭", "平"});
        ERA_FULL_NAMES.put(FALLBACK_LANGUAGE, new String[]{"Unknown", "Keio", "Meiji", "Taisho", "Showa", "Heisei"});
        ERA_FULL_NAMES.put(TARGET_LANGUAGE, new String[]{"Unknown", "慶応", "明治", "大正", "昭和", "平成"});
    }

    private JapaneseChronology() {
    }

    private Object readResolve() {
        return INSTANCE;
    }

    private JapaneseDate resolveEYD(Map<TemporalField, Long> map, ResolverStyle resolverStyle, JapaneseEra japaneseEra, int i) {
        if (resolverStyle == ResolverStyle.LENIENT) {
            return mo13050dateYearDay((japaneseEra.startDate().getYear() + i) - 1, 1).mo13060plus(Jdk8Methods.safeSubtract(map.remove(ChronoField.DAY_OF_YEAR).longValue(), 1L), (TemporalUnit) ChronoUnit.DAYS);
        }
        return mo13051dateYearDay((Era) japaneseEra, i, range(ChronoField.DAY_OF_YEAR).checkValidIntValue(map.remove(ChronoField.DAY_OF_YEAR).longValue(), ChronoField.DAY_OF_YEAR));
    }

    private JapaneseDate resolveEYMD(Map<TemporalField, Long> map, ResolverStyle resolverStyle, JapaneseEra japaneseEra, int i) {
        if (resolverStyle == ResolverStyle.LENIENT) {
            long safeSubtract = Jdk8Methods.safeSubtract(map.remove(ChronoField.MONTH_OF_YEAR).longValue(), 1L);
            return mo13043date((japaneseEra.startDate().getYear() + i) - 1, 1, 1).mo13060plus(safeSubtract, (TemporalUnit) ChronoUnit.MONTHS).mo13060plus(Jdk8Methods.safeSubtract(map.remove(ChronoField.DAY_OF_MONTH).longValue(), 1L), (TemporalUnit) ChronoUnit.DAYS);
        }
        int checkValidIntValue = range(ChronoField.MONTH_OF_YEAR).checkValidIntValue(map.remove(ChronoField.MONTH_OF_YEAR).longValue(), ChronoField.MONTH_OF_YEAR);
        int checkValidIntValue2 = range(ChronoField.DAY_OF_MONTH).checkValidIntValue(map.remove(ChronoField.DAY_OF_MONTH).longValue(), ChronoField.DAY_OF_MONTH);
        if (resolverStyle != ResolverStyle.SMART) {
            return mo13044date((Era) japaneseEra, i, checkValidIntValue, checkValidIntValue2);
        }
        if (i >= 1) {
            int year = (japaneseEra.startDate().getYear() + i) - 1;
            if (checkValidIntValue2 > 28) {
                checkValidIntValue2 = Math.min(checkValidIntValue2, mo13043date(year, checkValidIntValue, 1).lengthOfMonth());
            }
            JapaneseDate mo13043date = mo13043date(year, checkValidIntValue, checkValidIntValue2);
            if (mo13043date.mo13055getEra() != japaneseEra) {
                if (Math.abs(mo13043date.mo13055getEra().getValue() - japaneseEra.getValue()) <= 1) {
                    if (mo13043date.get(ChronoField.YEAR_OF_ERA) != 1 && i != 1) {
                        throw new DateTimeException("Invalid Era/YearOfEra: " + japaneseEra + " " + i);
                    }
                } else {
                    throw new DateTimeException("Invalid Era/YearOfEra: " + japaneseEra + " " + i);
                }
            }
            return mo13043date;
        }
        throw new DateTimeException(GeneratedOutlineSupport1.outline49("Invalid YearOfEra: ", i));
    }

    @Override // org.threeten.bp.chrono.Chronology
    public List<Era> eras() {
        return Arrays.asList(JapaneseEra.values());
    }

    @Override // org.threeten.bp.chrono.Chronology
    public String getCalendarType() {
        return "japanese";
    }

    @Override // org.threeten.bp.chrono.Chronology
    public String getId() {
        return "Japanese";
    }

    @Override // org.threeten.bp.chrono.Chronology
    public boolean isLeapYear(long j) {
        return IsoChronology.INSTANCE.isLeapYear(j);
    }

    @Override // org.threeten.bp.chrono.Chronology
    /* renamed from: localDateTime */
    public ChronoLocalDateTime<JapaneseDate> mo12999localDateTime(TemporalAccessor temporalAccessor) {
        return super.mo12999localDateTime(temporalAccessor);
    }

    @Override // org.threeten.bp.chrono.Chronology
    public int prolepticYear(Era era, int i) {
        JapaneseEra japaneseEra;
        if (era instanceof JapaneseEra) {
            int year = (((JapaneseEra) era).startDate().getYear() + i) - 1;
            ValueRange.of(1L, (japaneseEra.endDate().getYear() - japaneseEra.startDate().getYear()) + 1).checkValidValue(i, ChronoField.YEAR_OF_ERA);
            return year;
        }
        throw new ClassCastException("Era must be JapaneseEra");
    }

    @Override // org.threeten.bp.chrono.Chronology
    public ValueRange range(ChronoField chronoField) {
        int ordinal = chronoField.ordinal();
        if (ordinal != 15 && ordinal != 18 && ordinal != 20 && ordinal != 24) {
            switch (ordinal) {
                case 0:
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                case 7:
                case 8:
                case 9:
                case 10:
                case 11:
                case 12:
                case 13:
                    break;
                default:
                    Calendar calendar = Calendar.getInstance(LOCALE);
                    int ordinal2 = chronoField.ordinal();
                    int i = 0;
                    if (ordinal2 == 19) {
                        JapaneseEra[] values = JapaneseEra.values();
                        int i2 = 366;
                        while (i < values.length) {
                            i2 = Math.min(i2, (values[i].startDate().lengthOfYear() - values[i].startDate().getDayOfYear()) + 1);
                            i++;
                        }
                        return ValueRange.of(1L, i2, 366L);
                    } else if (ordinal2 != 23) {
                        switch (ordinal2) {
                            case 25:
                                JapaneseEra[] values2 = JapaneseEra.values();
                                int year = (values2[values2.length - 1].endDate().getYear() - values2[values2.length - 1].startDate().getYear()) + 1;
                                int i3 = Integer.MAX_VALUE;
                                while (i < values2.length) {
                                    i3 = Math.min(i3, (values2[i].endDate().getYear() - values2[i].startDate().getYear()) + 1);
                                    i++;
                                }
                                return ValueRange.of(1L, 6L, i3, year);
                            case 26:
                                JapaneseEra[] values3 = JapaneseEra.values();
                                return ValueRange.of(JapaneseDate.MIN_DATE.getYear(), values3[values3.length - 1].endDate().getYear());
                            case 27:
                                JapaneseEra[] values4 = JapaneseEra.values();
                                return ValueRange.of(values4[0].getValue(), values4[values4.length - 1].getValue());
                            default:
                                throw new UnsupportedOperationException("Unimplementable field: " + chronoField);
                        }
                    } else {
                        return ValueRange.of(calendar.getMinimum(2) + 1, calendar.getGreatestMinimum(2) + 1, calendar.getLeastMaximum(2) + 1, calendar.getMaximum(2) + 1);
                    }
            }
        }
        return chronoField.range();
    }

    @Override // org.threeten.bp.chrono.Chronology
    /* renamed from: resolveDate */
    public /* bridge */ /* synthetic */ ChronoLocalDate mo13053resolveDate(Map map, ResolverStyle resolverStyle) {
        return mo13053resolveDate((Map<TemporalField, Long>) map, resolverStyle);
    }

    @Override // org.threeten.bp.chrono.Chronology
    /* renamed from: zonedDateTime */
    public ChronoZonedDateTime<JapaneseDate> mo13002zonedDateTime(TemporalAccessor temporalAccessor) {
        return super.mo13002zonedDateTime(temporalAccessor);
    }

    @Override // org.threeten.bp.chrono.Chronology
    /* renamed from: dateEpochDay  reason: collision with other method in class */
    public JapaneseDate mo13046dateEpochDay(long j) {
        return new JapaneseDate(LocalDate.ofEpochDay(j));
    }

    @Override // org.threeten.bp.chrono.Chronology
    /* renamed from: eraOf  reason: collision with other method in class */
    public JapaneseEra mo13052eraOf(int i) {
        return JapaneseEra.of(i);
    }

    /* JADX WARN: Type inference failed for: r9v18, types: [org.threeten.bp.chrono.JapaneseDate, org.threeten.bp.jdk8.DefaultInterfaceTemporalAccessor] */
    /* JADX WARN: Type inference failed for: r9v33, types: [org.threeten.bp.chrono.JapaneseDate] */
    /* JADX WARN: Type inference failed for: r9v70, types: [org.threeten.bp.chrono.JapaneseDate] */
    @Override // org.threeten.bp.chrono.Chronology
    /* renamed from: resolveDate  reason: collision with other method in class */
    public JapaneseDate mo13053resolveDate(Map<TemporalField, Long> map, ResolverStyle resolverStyle) {
        if (map.containsKey(ChronoField.EPOCH_DAY)) {
            return mo13046dateEpochDay(map.remove(ChronoField.EPOCH_DAY).longValue());
        }
        Long remove = map.remove(ChronoField.PROLEPTIC_MONTH);
        if (remove != null) {
            if (resolverStyle != ResolverStyle.LENIENT) {
                ChronoField.PROLEPTIC_MONTH.checkValidValue(remove.longValue());
            }
            updateResolveMap(map, ChronoField.MONTH_OF_YEAR, Jdk8Methods.floorMod(remove.longValue(), 12) + 1);
            updateResolveMap(map, ChronoField.YEAR, Jdk8Methods.floorDiv(remove.longValue(), 12L));
        }
        Long l = map.get(ChronoField.ERA);
        JapaneseEra mo13052eraOf = l != null ? mo13052eraOf(range(ChronoField.ERA).checkValidIntValue(l.longValue(), ChronoField.ERA)) : null;
        Long l2 = map.get(ChronoField.YEAR_OF_ERA);
        if (l2 != null) {
            int checkValidIntValue = range(ChronoField.YEAR_OF_ERA).checkValidIntValue(l2.longValue(), ChronoField.YEAR_OF_ERA);
            if (mo13052eraOf == null && resolverStyle != ResolverStyle.STRICT && !map.containsKey(ChronoField.YEAR)) {
                mo13052eraOf = (JapaneseEra) GeneratedOutlineSupport1.outline25(eras(), 1);
            }
            if (mo13052eraOf != null && map.containsKey(ChronoField.MONTH_OF_YEAR) && map.containsKey(ChronoField.DAY_OF_MONTH)) {
                map.remove(ChronoField.ERA);
                map.remove(ChronoField.YEAR_OF_ERA);
                return resolveEYMD(map, resolverStyle, mo13052eraOf, checkValidIntValue);
            } else if (mo13052eraOf != null && map.containsKey(ChronoField.DAY_OF_YEAR)) {
                map.remove(ChronoField.ERA);
                map.remove(ChronoField.YEAR_OF_ERA);
                return resolveEYD(map, resolverStyle, mo13052eraOf, checkValidIntValue);
            }
        }
        if (map.containsKey(ChronoField.YEAR)) {
            if (map.containsKey(ChronoField.MONTH_OF_YEAR)) {
                if (map.containsKey(ChronoField.DAY_OF_MONTH)) {
                    ChronoField chronoField = ChronoField.YEAR;
                    int checkValidIntValue2 = chronoField.checkValidIntValue(map.remove(chronoField).longValue());
                    if (resolverStyle == ResolverStyle.LENIENT) {
                        return mo13043date(checkValidIntValue2, 1, 1).plusMonths(Jdk8Methods.safeSubtract(map.remove(ChronoField.MONTH_OF_YEAR).longValue(), 1L)).plusDays(Jdk8Methods.safeSubtract(map.remove(ChronoField.DAY_OF_MONTH).longValue(), 1L));
                    }
                    int checkValidIntValue3 = range(ChronoField.MONTH_OF_YEAR).checkValidIntValue(map.remove(ChronoField.MONTH_OF_YEAR).longValue(), ChronoField.MONTH_OF_YEAR);
                    int checkValidIntValue4 = range(ChronoField.DAY_OF_MONTH).checkValidIntValue(map.remove(ChronoField.DAY_OF_MONTH).longValue(), ChronoField.DAY_OF_MONTH);
                    if (resolverStyle == ResolverStyle.SMART && checkValidIntValue4 > 28) {
                        checkValidIntValue4 = Math.min(checkValidIntValue4, mo13043date(checkValidIntValue2, checkValidIntValue3, 1).lengthOfMonth());
                    }
                    return mo13043date(checkValidIntValue2, checkValidIntValue3, checkValidIntValue4);
                } else if (map.containsKey(ChronoField.ALIGNED_WEEK_OF_MONTH)) {
                    if (map.containsKey(ChronoField.ALIGNED_DAY_OF_WEEK_IN_MONTH)) {
                        ChronoField chronoField2 = ChronoField.YEAR;
                        int checkValidIntValue5 = chronoField2.checkValidIntValue(map.remove(chronoField2).longValue());
                        if (resolverStyle == ResolverStyle.LENIENT) {
                            long safeSubtract = Jdk8Methods.safeSubtract(map.remove(ChronoField.MONTH_OF_YEAR).longValue(), 1L);
                            return mo13043date(checkValidIntValue5, 1, 1).mo13060plus(safeSubtract, (TemporalUnit) ChronoUnit.MONTHS).mo13060plus(Jdk8Methods.safeSubtract(map.remove(ChronoField.ALIGNED_WEEK_OF_MONTH).longValue(), 1L), (TemporalUnit) ChronoUnit.WEEKS).mo13060plus(Jdk8Methods.safeSubtract(map.remove(ChronoField.ALIGNED_DAY_OF_WEEK_IN_MONTH).longValue(), 1L), (TemporalUnit) ChronoUnit.DAYS);
                        }
                        ChronoField chronoField3 = ChronoField.MONTH_OF_YEAR;
                        int checkValidIntValue6 = chronoField3.checkValidIntValue(map.remove(chronoField3).longValue());
                        ChronoField chronoField4 = ChronoField.ALIGNED_WEEK_OF_MONTH;
                        int checkValidIntValue7 = chronoField4.checkValidIntValue(map.remove(chronoField4).longValue());
                        ChronoField chronoField5 = ChronoField.ALIGNED_DAY_OF_WEEK_IN_MONTH;
                        JapaneseDate mo13060plus = mo13043date(checkValidIntValue5, checkValidIntValue6, 1).mo13060plus((chronoField5.checkValidIntValue(map.remove(chronoField5).longValue()) - 1) + ((checkValidIntValue7 - 1) * 7), (TemporalUnit) ChronoUnit.DAYS);
                        if (resolverStyle == ResolverStyle.STRICT && mo13060plus.get(ChronoField.MONTH_OF_YEAR) != checkValidIntValue6) {
                            throw new DateTimeException("Strict mode rejected date parsed to a different month");
                        }
                        return mo13060plus;
                    } else if (map.containsKey(ChronoField.DAY_OF_WEEK)) {
                        ChronoField chronoField6 = ChronoField.YEAR;
                        int checkValidIntValue8 = chronoField6.checkValidIntValue(map.remove(chronoField6).longValue());
                        if (resolverStyle == ResolverStyle.LENIENT) {
                            long safeSubtract2 = Jdk8Methods.safeSubtract(map.remove(ChronoField.MONTH_OF_YEAR).longValue(), 1L);
                            return mo13043date(checkValidIntValue8, 1, 1).mo13060plus(safeSubtract2, (TemporalUnit) ChronoUnit.MONTHS).mo13060plus(Jdk8Methods.safeSubtract(map.remove(ChronoField.ALIGNED_WEEK_OF_MONTH).longValue(), 1L), (TemporalUnit) ChronoUnit.WEEKS).mo13060plus(Jdk8Methods.safeSubtract(map.remove(ChronoField.DAY_OF_WEEK).longValue(), 1L), (TemporalUnit) ChronoUnit.DAYS);
                        }
                        ChronoField chronoField7 = ChronoField.MONTH_OF_YEAR;
                        int checkValidIntValue9 = chronoField7.checkValidIntValue(map.remove(chronoField7).longValue());
                        ChronoField chronoField8 = ChronoField.ALIGNED_WEEK_OF_MONTH;
                        int checkValidIntValue10 = chronoField8.checkValidIntValue(map.remove(chronoField8).longValue());
                        ChronoField chronoField9 = ChronoField.DAY_OF_WEEK;
                        JapaneseDate mo13061with = mo13043date(checkValidIntValue8, checkValidIntValue9, 1).mo13060plus(checkValidIntValue10 - 1, (TemporalUnit) ChronoUnit.WEEKS).mo13061with(TemporalAdjusters.nextOrSame(DayOfWeek.of(chronoField9.checkValidIntValue(map.remove(chronoField9).longValue()))));
                        if (resolverStyle == ResolverStyle.STRICT && mo13061with.get(ChronoField.MONTH_OF_YEAR) != checkValidIntValue9) {
                            throw new DateTimeException("Strict mode rejected date parsed to a different month");
                        }
                        return mo13061with;
                    }
                }
            }
            if (map.containsKey(ChronoField.DAY_OF_YEAR)) {
                ChronoField chronoField10 = ChronoField.YEAR;
                int checkValidIntValue11 = chronoField10.checkValidIntValue(map.remove(chronoField10).longValue());
                if (resolverStyle == ResolverStyle.LENIENT) {
                    return mo13050dateYearDay(checkValidIntValue11, 1).plusDays(Jdk8Methods.safeSubtract(map.remove(ChronoField.DAY_OF_YEAR).longValue(), 1L));
                }
                ChronoField chronoField11 = ChronoField.DAY_OF_YEAR;
                return mo13050dateYearDay(checkValidIntValue11, chronoField11.checkValidIntValue(map.remove(chronoField11).longValue()));
            } else if (map.containsKey(ChronoField.ALIGNED_WEEK_OF_YEAR)) {
                if (map.containsKey(ChronoField.ALIGNED_DAY_OF_WEEK_IN_YEAR)) {
                    ChronoField chronoField12 = ChronoField.YEAR;
                    int checkValidIntValue12 = chronoField12.checkValidIntValue(map.remove(chronoField12).longValue());
                    if (resolverStyle == ResolverStyle.LENIENT) {
                        return mo13043date(checkValidIntValue12, 1, 1).mo13060plus(Jdk8Methods.safeSubtract(map.remove(ChronoField.ALIGNED_WEEK_OF_YEAR).longValue(), 1L), (TemporalUnit) ChronoUnit.WEEKS).mo13060plus(Jdk8Methods.safeSubtract(map.remove(ChronoField.ALIGNED_DAY_OF_WEEK_IN_YEAR).longValue(), 1L), (TemporalUnit) ChronoUnit.DAYS);
                    }
                    ChronoField chronoField13 = ChronoField.ALIGNED_WEEK_OF_YEAR;
                    int checkValidIntValue13 = chronoField13.checkValidIntValue(map.remove(chronoField13).longValue());
                    ChronoField chronoField14 = ChronoField.ALIGNED_DAY_OF_WEEK_IN_YEAR;
                    ?? plusDays = mo13043date(checkValidIntValue12, 1, 1).plusDays((chronoField14.checkValidIntValue(map.remove(chronoField14).longValue()) - 1) + ((checkValidIntValue13 - 1) * 7));
                    if (resolverStyle == ResolverStyle.STRICT && plusDays.get(ChronoField.YEAR) != checkValidIntValue12) {
                        throw new DateTimeException("Strict mode rejected date parsed to a different year");
                    }
                    return plusDays;
                } else if (map.containsKey(ChronoField.DAY_OF_WEEK)) {
                    ChronoField chronoField15 = ChronoField.YEAR;
                    int checkValidIntValue14 = chronoField15.checkValidIntValue(map.remove(chronoField15).longValue());
                    if (resolverStyle == ResolverStyle.LENIENT) {
                        return mo13043date(checkValidIntValue14, 1, 1).mo13060plus(Jdk8Methods.safeSubtract(map.remove(ChronoField.ALIGNED_WEEK_OF_YEAR).longValue(), 1L), (TemporalUnit) ChronoUnit.WEEKS).mo13060plus(Jdk8Methods.safeSubtract(map.remove(ChronoField.DAY_OF_WEEK).longValue(), 1L), (TemporalUnit) ChronoUnit.DAYS);
                    }
                    ChronoField chronoField16 = ChronoField.ALIGNED_WEEK_OF_YEAR;
                    int checkValidIntValue15 = chronoField16.checkValidIntValue(map.remove(chronoField16).longValue());
                    ChronoField chronoField17 = ChronoField.DAY_OF_WEEK;
                    JapaneseDate mo13061with2 = mo13043date(checkValidIntValue14, 1, 1).mo13060plus(checkValidIntValue15 - 1, (TemporalUnit) ChronoUnit.WEEKS).mo13061with(TemporalAdjusters.nextOrSame(DayOfWeek.of(chronoField17.checkValidIntValue(map.remove(chronoField17).longValue()))));
                    if (resolverStyle == ResolverStyle.STRICT && mo13061with2.get(ChronoField.YEAR) != checkValidIntValue14) {
                        throw new DateTimeException("Strict mode rejected date parsed to a different month");
                    }
                    return mo13061with2;
                }
            }
        }
        return null;
    }

    @Override // org.threeten.bp.chrono.Chronology
    /* renamed from: zonedDateTime */
    public ChronoZonedDateTime<JapaneseDate> mo13001zonedDateTime(Instant instant, ZoneId zoneId) {
        return super.mo13001zonedDateTime(instant, zoneId);
    }

    @Override // org.threeten.bp.chrono.Chronology
    /* renamed from: dateYearDay  reason: collision with other method in class */
    public JapaneseDate mo13051dateYearDay(Era era, int i, int i2) {
        if (era instanceof JapaneseEra) {
            return JapaneseDate.ofYearDay((JapaneseEra) era, i, i2);
        }
        throw new ClassCastException("Era must be JapaneseEra");
    }

    @Override // org.threeten.bp.chrono.Chronology
    /* renamed from: date  reason: collision with other method in class */
    public JapaneseDate mo13044date(Era era, int i, int i2, int i3) {
        if (era instanceof JapaneseEra) {
            return JapaneseDate.of((JapaneseEra) era, i, i2, i3);
        }
        throw new ClassCastException("Era must be JapaneseEra");
    }

    @Override // org.threeten.bp.chrono.Chronology
    /* renamed from: dateNow  reason: collision with other method in class */
    public JapaneseDate mo13047dateNow() {
        return (JapaneseDate) super.mo13047dateNow();
    }

    @Override // org.threeten.bp.chrono.Chronology
    /* renamed from: dateNow  reason: collision with other method in class */
    public JapaneseDate mo13049dateNow(ZoneId zoneId) {
        return (JapaneseDate) super.mo13049dateNow(zoneId);
    }

    @Override // org.threeten.bp.chrono.Chronology
    /* renamed from: dateNow  reason: collision with other method in class */
    public JapaneseDate mo13048dateNow(Clock clock) {
        Jdk8Methods.requireNonNull(clock, "clock");
        return (JapaneseDate) super.mo13048dateNow(clock);
    }

    @Override // org.threeten.bp.chrono.Chronology
    /* renamed from: dateYearDay  reason: collision with other method in class */
    public JapaneseDate mo13050dateYearDay(int i, int i2) {
        LocalDate ofYearDay = LocalDate.ofYearDay(i, i2);
        return mo13043date(i, ofYearDay.getMonthValue(), ofYearDay.getDayOfMonth());
    }

    @Override // org.threeten.bp.chrono.Chronology
    /* renamed from: date  reason: collision with other method in class */
    public JapaneseDate mo13043date(int i, int i2, int i3) {
        return new JapaneseDate(LocalDate.of(i, i2, i3));
    }

    @Override // org.threeten.bp.chrono.Chronology
    /* renamed from: date  reason: collision with other method in class */
    public JapaneseDate mo13045date(TemporalAccessor temporalAccessor) {
        if (temporalAccessor instanceof JapaneseDate) {
            return (JapaneseDate) temporalAccessor;
        }
        return new JapaneseDate(LocalDate.from(temporalAccessor));
    }
}
