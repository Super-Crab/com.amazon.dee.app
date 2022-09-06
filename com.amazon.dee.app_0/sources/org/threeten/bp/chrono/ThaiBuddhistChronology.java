package org.threeten.bp.chrono;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
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
public final class ThaiBuddhistChronology extends Chronology implements Serializable {
    private static final String FALLBACK_LANGUAGE = "en";
    private static final String TARGET_LANGUAGE = "th";
    static final int YEARS_DIFFERENCE = 543;
    private static final long serialVersionUID = 2775954514031616474L;
    public static final ThaiBuddhistChronology INSTANCE = new ThaiBuddhistChronology();
    private static final HashMap<String, String[]> ERA_NARROW_NAMES = new HashMap<>();
    private static final HashMap<String, String[]> ERA_SHORT_NAMES = new HashMap<>();
    private static final HashMap<String, String[]> ERA_FULL_NAMES = new HashMap<>();

    /* renamed from: org.threeten.bp.chrono.ThaiBuddhistChronology$1  reason: invalid class name */
    /* loaded from: classes5.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$threeten$bp$temporal$ChronoField = new int[ChronoField.values().length];

        static {
            try {
                $SwitchMap$org$threeten$bp$temporal$ChronoField[ChronoField.PROLEPTIC_MONTH.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$org$threeten$bp$temporal$ChronoField[ChronoField.YEAR_OF_ERA.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$org$threeten$bp$temporal$ChronoField[ChronoField.YEAR.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    static {
        ERA_NARROW_NAMES.put(FALLBACK_LANGUAGE, new String[]{"BB", "BE"});
        ERA_NARROW_NAMES.put(TARGET_LANGUAGE, new String[]{"BB", "BE"});
        ERA_SHORT_NAMES.put(FALLBACK_LANGUAGE, new String[]{"B.B.", "B.E."});
        ERA_SHORT_NAMES.put(TARGET_LANGUAGE, new String[]{"พ.ศ.", "ปีก่อนคริสต์กาลที่"});
        ERA_FULL_NAMES.put(FALLBACK_LANGUAGE, new String[]{"Before Buddhist", "Budhhist Era"});
        ERA_FULL_NAMES.put(TARGET_LANGUAGE, new String[]{"พุทธศักราช", "ปีก่อนคริสต์กาลที่"});
    }

    private ThaiBuddhistChronology() {
    }

    private Object readResolve() {
        return INSTANCE;
    }

    @Override // org.threeten.bp.chrono.Chronology
    public List<Era> eras() {
        return Arrays.asList(ThaiBuddhistEra.values());
    }

    @Override // org.threeten.bp.chrono.Chronology
    public String getCalendarType() {
        return "buddhist";
    }

    @Override // org.threeten.bp.chrono.Chronology
    public String getId() {
        return "ThaiBuddhist";
    }

    @Override // org.threeten.bp.chrono.Chronology
    public boolean isLeapYear(long j) {
        return IsoChronology.INSTANCE.isLeapYear(j - 543);
    }

    @Override // org.threeten.bp.chrono.Chronology
    /* renamed from: localDateTime */
    public ChronoLocalDateTime<ThaiBuddhistDate> mo12999localDateTime(TemporalAccessor temporalAccessor) {
        return super.mo12999localDateTime(temporalAccessor);
    }

    @Override // org.threeten.bp.chrono.Chronology
    public int prolepticYear(Era era, int i) {
        if (era instanceof ThaiBuddhistEra) {
            return era == ThaiBuddhistEra.BE ? i : 1 - i;
        }
        throw new ClassCastException("Era must be BuddhistEra");
    }

    @Override // org.threeten.bp.chrono.Chronology
    public ValueRange range(ChronoField chronoField) {
        switch (chronoField.ordinal()) {
            case 24:
                ValueRange range = ChronoField.PROLEPTIC_MONTH.range();
                return ValueRange.of(range.getMinimum() + 6516, range.getMaximum() + 6516);
            case 25:
                ValueRange range2 = ChronoField.YEAR.range();
                return ValueRange.of(1L, (-(range2.getMinimum() + 543)) + 1, range2.getMaximum() + 543);
            case 26:
                ValueRange range3 = ChronoField.YEAR.range();
                return ValueRange.of(range3.getMinimum() + 543, range3.getMaximum() + 543);
            default:
                return chronoField.range();
        }
    }

    @Override // org.threeten.bp.chrono.Chronology
    /* renamed from: resolveDate */
    public /* bridge */ /* synthetic */ ChronoLocalDate mo13053resolveDate(Map map, ResolverStyle resolverStyle) {
        return mo13053resolveDate((Map<TemporalField, Long>) map, resolverStyle);
    }

    @Override // org.threeten.bp.chrono.Chronology
    /* renamed from: zonedDateTime */
    public ChronoZonedDateTime<ThaiBuddhistDate> mo13002zonedDateTime(TemporalAccessor temporalAccessor) {
        return super.mo13002zonedDateTime(temporalAccessor);
    }

    @Override // org.threeten.bp.chrono.Chronology
    /* renamed from: dateEpochDay  reason: collision with other method in class */
    public ThaiBuddhistDate mo13046dateEpochDay(long j) {
        return new ThaiBuddhistDate(LocalDate.ofEpochDay(j));
    }

    @Override // org.threeten.bp.chrono.Chronology
    /* renamed from: eraOf  reason: collision with other method in class */
    public ThaiBuddhistEra mo13052eraOf(int i) {
        return ThaiBuddhistEra.of(i);
    }

    /* JADX WARN: Type inference failed for: r11v21, types: [org.threeten.bp.chrono.ThaiBuddhistDate, org.threeten.bp.jdk8.DefaultInterfaceTemporalAccessor] */
    /* JADX WARN: Type inference failed for: r11v36, types: [org.threeten.bp.chrono.ThaiBuddhistDate] */
    /* JADX WARN: Type inference failed for: r11v73, types: [org.threeten.bp.chrono.ThaiBuddhistDate] */
    @Override // org.threeten.bp.chrono.Chronology
    /* renamed from: resolveDate  reason: collision with other method in class */
    public ThaiBuddhistDate mo13053resolveDate(Map<TemporalField, Long> map, ResolverStyle resolverStyle) {
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
        Long remove2 = map.remove(ChronoField.YEAR_OF_ERA);
        if (remove2 != null) {
            if (resolverStyle != ResolverStyle.LENIENT) {
                ChronoField.YEAR_OF_ERA.checkValidValue(remove2.longValue());
            }
            Long remove3 = map.remove(ChronoField.ERA);
            if (remove3 == null) {
                Long l = map.get(ChronoField.YEAR);
                if (resolverStyle != ResolverStyle.STRICT) {
                    updateResolveMap(map, ChronoField.YEAR, (l == null || l.longValue() > 0) ? remove2.longValue() : Jdk8Methods.safeSubtract(1L, remove2.longValue()));
                } else if (l != null) {
                    updateResolveMap(map, ChronoField.YEAR, l.longValue() > 0 ? remove2.longValue() : Jdk8Methods.safeSubtract(1L, remove2.longValue()));
                } else {
                    map.put(ChronoField.YEAR_OF_ERA, remove2);
                }
            } else if (remove3.longValue() == 1) {
                updateResolveMap(map, ChronoField.YEAR, remove2.longValue());
            } else if (remove3.longValue() == 0) {
                updateResolveMap(map, ChronoField.YEAR, Jdk8Methods.safeSubtract(1L, remove2.longValue()));
            } else {
                throw new DateTimeException("Invalid value for era: " + remove3);
            }
        } else if (map.containsKey(ChronoField.ERA)) {
            ChronoField chronoField = ChronoField.ERA;
            chronoField.checkValidValue(map.get(chronoField).longValue());
        }
        if (!map.containsKey(ChronoField.YEAR)) {
            return null;
        }
        if (map.containsKey(ChronoField.MONTH_OF_YEAR)) {
            if (map.containsKey(ChronoField.DAY_OF_MONTH)) {
                ChronoField chronoField2 = ChronoField.YEAR;
                int checkValidIntValue = chronoField2.checkValidIntValue(map.remove(chronoField2).longValue());
                if (resolverStyle == ResolverStyle.LENIENT) {
                    return mo13043date(checkValidIntValue, 1, 1).plusMonths(Jdk8Methods.safeSubtract(map.remove(ChronoField.MONTH_OF_YEAR).longValue(), 1L)).plusDays(Jdk8Methods.safeSubtract(map.remove(ChronoField.DAY_OF_MONTH).longValue(), 1L));
                }
                int checkValidIntValue2 = range(ChronoField.MONTH_OF_YEAR).checkValidIntValue(map.remove(ChronoField.MONTH_OF_YEAR).longValue(), ChronoField.MONTH_OF_YEAR);
                int checkValidIntValue3 = range(ChronoField.DAY_OF_MONTH).checkValidIntValue(map.remove(ChronoField.DAY_OF_MONTH).longValue(), ChronoField.DAY_OF_MONTH);
                if (resolverStyle == ResolverStyle.SMART && checkValidIntValue3 > 28) {
                    checkValidIntValue3 = Math.min(checkValidIntValue3, mo13043date(checkValidIntValue, checkValidIntValue2, 1).lengthOfMonth());
                }
                return mo13043date(checkValidIntValue, checkValidIntValue2, checkValidIntValue3);
            } else if (map.containsKey(ChronoField.ALIGNED_WEEK_OF_MONTH)) {
                if (map.containsKey(ChronoField.ALIGNED_DAY_OF_WEEK_IN_MONTH)) {
                    ChronoField chronoField3 = ChronoField.YEAR;
                    int checkValidIntValue4 = chronoField3.checkValidIntValue(map.remove(chronoField3).longValue());
                    if (resolverStyle == ResolverStyle.LENIENT) {
                        long safeSubtract = Jdk8Methods.safeSubtract(map.remove(ChronoField.MONTH_OF_YEAR).longValue(), 1L);
                        return mo13043date(checkValidIntValue4, 1, 1).mo13060plus(safeSubtract, (TemporalUnit) ChronoUnit.MONTHS).mo13060plus(Jdk8Methods.safeSubtract(map.remove(ChronoField.ALIGNED_WEEK_OF_MONTH).longValue(), 1L), (TemporalUnit) ChronoUnit.WEEKS).mo13060plus(Jdk8Methods.safeSubtract(map.remove(ChronoField.ALIGNED_DAY_OF_WEEK_IN_MONTH).longValue(), 1L), (TemporalUnit) ChronoUnit.DAYS);
                    }
                    ChronoField chronoField4 = ChronoField.MONTH_OF_YEAR;
                    int checkValidIntValue5 = chronoField4.checkValidIntValue(map.remove(chronoField4).longValue());
                    ChronoField chronoField5 = ChronoField.ALIGNED_WEEK_OF_MONTH;
                    int checkValidIntValue6 = chronoField5.checkValidIntValue(map.remove(chronoField5).longValue());
                    ChronoField chronoField6 = ChronoField.ALIGNED_DAY_OF_WEEK_IN_MONTH;
                    ThaiBuddhistDate mo13060plus = mo13043date(checkValidIntValue4, checkValidIntValue5, 1).mo13060plus((chronoField6.checkValidIntValue(map.remove(chronoField6).longValue()) - 1) + ((checkValidIntValue6 - 1) * 7), (TemporalUnit) ChronoUnit.DAYS);
                    if (resolverStyle == ResolverStyle.STRICT && mo13060plus.get(ChronoField.MONTH_OF_YEAR) != checkValidIntValue5) {
                        throw new DateTimeException("Strict mode rejected date parsed to a different month");
                    }
                    return mo13060plus;
                } else if (map.containsKey(ChronoField.DAY_OF_WEEK)) {
                    ChronoField chronoField7 = ChronoField.YEAR;
                    int checkValidIntValue7 = chronoField7.checkValidIntValue(map.remove(chronoField7).longValue());
                    if (resolverStyle == ResolverStyle.LENIENT) {
                        long safeSubtract2 = Jdk8Methods.safeSubtract(map.remove(ChronoField.MONTH_OF_YEAR).longValue(), 1L);
                        return mo13043date(checkValidIntValue7, 1, 1).mo13060plus(safeSubtract2, (TemporalUnit) ChronoUnit.MONTHS).mo13060plus(Jdk8Methods.safeSubtract(map.remove(ChronoField.ALIGNED_WEEK_OF_MONTH).longValue(), 1L), (TemporalUnit) ChronoUnit.WEEKS).mo13060plus(Jdk8Methods.safeSubtract(map.remove(ChronoField.DAY_OF_WEEK).longValue(), 1L), (TemporalUnit) ChronoUnit.DAYS);
                    }
                    ChronoField chronoField8 = ChronoField.MONTH_OF_YEAR;
                    int checkValidIntValue8 = chronoField8.checkValidIntValue(map.remove(chronoField8).longValue());
                    ChronoField chronoField9 = ChronoField.ALIGNED_WEEK_OF_MONTH;
                    int checkValidIntValue9 = chronoField9.checkValidIntValue(map.remove(chronoField9).longValue());
                    ChronoField chronoField10 = ChronoField.DAY_OF_WEEK;
                    ThaiBuddhistDate mo13061with = mo13043date(checkValidIntValue7, checkValidIntValue8, 1).mo13060plus(checkValidIntValue9 - 1, (TemporalUnit) ChronoUnit.WEEKS).mo13061with(TemporalAdjusters.nextOrSame(DayOfWeek.of(chronoField10.checkValidIntValue(map.remove(chronoField10).longValue()))));
                    if (resolverStyle == ResolverStyle.STRICT && mo13061with.get(ChronoField.MONTH_OF_YEAR) != checkValidIntValue8) {
                        throw new DateTimeException("Strict mode rejected date parsed to a different month");
                    }
                    return mo13061with;
                }
            }
        }
        if (map.containsKey(ChronoField.DAY_OF_YEAR)) {
            ChronoField chronoField11 = ChronoField.YEAR;
            int checkValidIntValue10 = chronoField11.checkValidIntValue(map.remove(chronoField11).longValue());
            if (resolverStyle == ResolverStyle.LENIENT) {
                return mo13050dateYearDay(checkValidIntValue10, 1).plusDays(Jdk8Methods.safeSubtract(map.remove(ChronoField.DAY_OF_YEAR).longValue(), 1L));
            }
            ChronoField chronoField12 = ChronoField.DAY_OF_YEAR;
            return mo13050dateYearDay(checkValidIntValue10, chronoField12.checkValidIntValue(map.remove(chronoField12).longValue()));
        } else if (!map.containsKey(ChronoField.ALIGNED_WEEK_OF_YEAR)) {
            return null;
        } else {
            if (map.containsKey(ChronoField.ALIGNED_DAY_OF_WEEK_IN_YEAR)) {
                ChronoField chronoField13 = ChronoField.YEAR;
                int checkValidIntValue11 = chronoField13.checkValidIntValue(map.remove(chronoField13).longValue());
                if (resolverStyle == ResolverStyle.LENIENT) {
                    return mo13043date(checkValidIntValue11, 1, 1).mo13060plus(Jdk8Methods.safeSubtract(map.remove(ChronoField.ALIGNED_WEEK_OF_YEAR).longValue(), 1L), (TemporalUnit) ChronoUnit.WEEKS).mo13060plus(Jdk8Methods.safeSubtract(map.remove(ChronoField.ALIGNED_DAY_OF_WEEK_IN_YEAR).longValue(), 1L), (TemporalUnit) ChronoUnit.DAYS);
                }
                ChronoField chronoField14 = ChronoField.ALIGNED_WEEK_OF_YEAR;
                int checkValidIntValue12 = chronoField14.checkValidIntValue(map.remove(chronoField14).longValue());
                ChronoField chronoField15 = ChronoField.ALIGNED_DAY_OF_WEEK_IN_YEAR;
                ?? plusDays = mo13043date(checkValidIntValue11, 1, 1).plusDays((chronoField15.checkValidIntValue(map.remove(chronoField15).longValue()) - 1) + ((checkValidIntValue12 - 1) * 7));
                if (resolverStyle == ResolverStyle.STRICT && plusDays.get(ChronoField.YEAR) != checkValidIntValue11) {
                    throw new DateTimeException("Strict mode rejected date parsed to a different year");
                }
                return plusDays;
            } else if (!map.containsKey(ChronoField.DAY_OF_WEEK)) {
                return null;
            } else {
                ChronoField chronoField16 = ChronoField.YEAR;
                int checkValidIntValue13 = chronoField16.checkValidIntValue(map.remove(chronoField16).longValue());
                if (resolverStyle == ResolverStyle.LENIENT) {
                    return mo13043date(checkValidIntValue13, 1, 1).mo13060plus(Jdk8Methods.safeSubtract(map.remove(ChronoField.ALIGNED_WEEK_OF_YEAR).longValue(), 1L), (TemporalUnit) ChronoUnit.WEEKS).mo13060plus(Jdk8Methods.safeSubtract(map.remove(ChronoField.DAY_OF_WEEK).longValue(), 1L), (TemporalUnit) ChronoUnit.DAYS);
                }
                ChronoField chronoField17 = ChronoField.ALIGNED_WEEK_OF_YEAR;
                int checkValidIntValue14 = chronoField17.checkValidIntValue(map.remove(chronoField17).longValue());
                ChronoField chronoField18 = ChronoField.DAY_OF_WEEK;
                ThaiBuddhistDate mo13061with2 = mo13043date(checkValidIntValue13, 1, 1).mo13060plus(checkValidIntValue14 - 1, (TemporalUnit) ChronoUnit.WEEKS).mo13061with(TemporalAdjusters.nextOrSame(DayOfWeek.of(chronoField18.checkValidIntValue(map.remove(chronoField18).longValue()))));
                if (resolverStyle == ResolverStyle.STRICT && mo13061with2.get(ChronoField.YEAR) != checkValidIntValue13) {
                    throw new DateTimeException("Strict mode rejected date parsed to a different month");
                }
                return mo13061with2;
            }
        }
    }

    @Override // org.threeten.bp.chrono.Chronology
    /* renamed from: zonedDateTime */
    public ChronoZonedDateTime<ThaiBuddhistDate> mo13001zonedDateTime(Instant instant, ZoneId zoneId) {
        return super.mo13001zonedDateTime(instant, zoneId);
    }

    @Override // org.threeten.bp.chrono.Chronology
    /* renamed from: dateYearDay  reason: collision with other method in class */
    public ThaiBuddhistDate mo13051dateYearDay(Era era, int i, int i2) {
        return (ThaiBuddhistDate) super.mo13051dateYearDay(era, i, i2);
    }

    @Override // org.threeten.bp.chrono.Chronology
    /* renamed from: date  reason: collision with other method in class */
    public ThaiBuddhistDate mo13044date(Era era, int i, int i2, int i3) {
        return (ThaiBuddhistDate) super.mo13044date(era, i, i2, i3);
    }

    @Override // org.threeten.bp.chrono.Chronology
    /* renamed from: dateNow  reason: collision with other method in class */
    public ThaiBuddhistDate mo13047dateNow() {
        return (ThaiBuddhistDate) super.mo13047dateNow();
    }

    @Override // org.threeten.bp.chrono.Chronology
    /* renamed from: dateYearDay  reason: collision with other method in class */
    public ThaiBuddhistDate mo13050dateYearDay(int i, int i2) {
        return new ThaiBuddhistDate(LocalDate.ofYearDay(i - 543, i2));
    }

    @Override // org.threeten.bp.chrono.Chronology
    /* renamed from: date  reason: collision with other method in class */
    public ThaiBuddhistDate mo13043date(int i, int i2, int i3) {
        return new ThaiBuddhistDate(LocalDate.of(i - 543, i2, i3));
    }

    @Override // org.threeten.bp.chrono.Chronology
    /* renamed from: dateNow  reason: collision with other method in class */
    public ThaiBuddhistDate mo13049dateNow(ZoneId zoneId) {
        return (ThaiBuddhistDate) super.mo13049dateNow(zoneId);
    }

    @Override // org.threeten.bp.chrono.Chronology
    /* renamed from: date  reason: collision with other method in class */
    public ThaiBuddhistDate mo13045date(TemporalAccessor temporalAccessor) {
        if (temporalAccessor instanceof ThaiBuddhistDate) {
            return (ThaiBuddhistDate) temporalAccessor;
        }
        return new ThaiBuddhistDate(LocalDate.from(temporalAccessor));
    }

    @Override // org.threeten.bp.chrono.Chronology
    /* renamed from: dateNow  reason: collision with other method in class */
    public ThaiBuddhistDate mo13048dateNow(Clock clock) {
        Jdk8Methods.requireNonNull(clock, "clock");
        return (ThaiBuddhistDate) super.mo13048dateNow(clock);
    }
}
