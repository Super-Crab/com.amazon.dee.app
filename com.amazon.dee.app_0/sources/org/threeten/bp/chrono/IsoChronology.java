package org.threeten.bp.chrono;

import androidx.exifinterface.media.ExifInterface;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.threeten.bp.Clock;
import org.threeten.bp.DateTimeException;
import org.threeten.bp.DayOfWeek;
import org.threeten.bp.Instant;
import org.threeten.bp.LocalDate;
import org.threeten.bp.LocalDateTime;
import org.threeten.bp.Month;
import org.threeten.bp.Year;
import org.threeten.bp.ZoneId;
import org.threeten.bp.ZonedDateTime;
import org.threeten.bp.format.ResolverStyle;
import org.threeten.bp.jdk8.Jdk8Methods;
import org.threeten.bp.temporal.ChronoField;
import org.threeten.bp.temporal.TemporalAccessor;
import org.threeten.bp.temporal.TemporalAdjusters;
import org.threeten.bp.temporal.TemporalField;
import org.threeten.bp.temporal.ValueRange;
/* loaded from: classes5.dex */
public final class IsoChronology extends Chronology implements Serializable {
    public static final IsoChronology INSTANCE = new IsoChronology();
    private static final long serialVersionUID = -1440403870442975015L;

    private IsoChronology() {
    }

    private Object readResolve() {
        return INSTANCE;
    }

    @Override // org.threeten.bp.chrono.Chronology
    public List<Era> eras() {
        return Arrays.asList(IsoEra.values());
    }

    @Override // org.threeten.bp.chrono.Chronology
    public String getCalendarType() {
        return "iso8601";
    }

    @Override // org.threeten.bp.chrono.Chronology
    public String getId() {
        return ExifInterface.TAG_RW2_ISO;
    }

    @Override // org.threeten.bp.chrono.Chronology
    public boolean isLeapYear(long j) {
        return (3 & j) == 0 && (j % 100 != 0 || j % 400 == 0);
    }

    @Override // org.threeten.bp.chrono.Chronology
    public int prolepticYear(Era era, int i) {
        if (era instanceof IsoEra) {
            return era == IsoEra.CE ? i : 1 - i;
        }
        throw new ClassCastException("Era must be IsoEra");
    }

    @Override // org.threeten.bp.chrono.Chronology
    public ValueRange range(ChronoField chronoField) {
        return chronoField.range();
    }

    @Override // org.threeten.bp.chrono.Chronology
    /* renamed from: resolveDate  reason: collision with other method in class */
    public /* bridge */ /* synthetic */ ChronoLocalDate mo13053resolveDate(Map map, ResolverStyle resolverStyle) {
        return mo13053resolveDate((Map<TemporalField, Long>) map, resolverStyle);
    }

    @Override // org.threeten.bp.chrono.Chronology
    /* renamed from: dateEpochDay */
    public LocalDate mo13046dateEpochDay(long j) {
        return LocalDate.ofEpochDay(j);
    }

    @Override // org.threeten.bp.chrono.Chronology
    /* renamed from: eraOf  reason: collision with other method in class */
    public IsoEra mo13052eraOf(int i) {
        return IsoEra.of(i);
    }

    @Override // org.threeten.bp.chrono.Chronology
    /* renamed from: localDateTime */
    public LocalDateTime mo12999localDateTime(TemporalAccessor temporalAccessor) {
        return LocalDateTime.from(temporalAccessor);
    }

    @Override // org.threeten.bp.chrono.Chronology
    /* renamed from: resolveDate */
    public LocalDate mo13053resolveDate(Map<TemporalField, Long> map, ResolverStyle resolverStyle) {
        if (map.containsKey(ChronoField.EPOCH_DAY)) {
            return LocalDate.ofEpochDay(map.remove(ChronoField.EPOCH_DAY).longValue());
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
                int safeToInt = Jdk8Methods.safeToInt(map.remove(ChronoField.MONTH_OF_YEAR).longValue());
                int safeToInt2 = Jdk8Methods.safeToInt(map.remove(ChronoField.DAY_OF_MONTH).longValue());
                if (resolverStyle == ResolverStyle.LENIENT) {
                    return LocalDate.of(checkValidIntValue, 1, 1).plusMonths(Jdk8Methods.safeSubtract(safeToInt, 1)).plusDays(Jdk8Methods.safeSubtract(safeToInt2, 1));
                } else if (resolverStyle == ResolverStyle.SMART) {
                    ChronoField.DAY_OF_MONTH.checkValidValue(safeToInt2);
                    if (safeToInt == 4 || safeToInt == 6 || safeToInt == 9 || safeToInt == 11) {
                        safeToInt2 = Math.min(safeToInt2, 30);
                    } else if (safeToInt == 2) {
                        safeToInt2 = Math.min(safeToInt2, Month.FEBRUARY.length(Year.isLeap(checkValidIntValue)));
                    }
                    return LocalDate.of(checkValidIntValue, safeToInt, safeToInt2);
                } else {
                    return LocalDate.of(checkValidIntValue, safeToInt, safeToInt2);
                }
            } else if (map.containsKey(ChronoField.ALIGNED_WEEK_OF_MONTH)) {
                if (map.containsKey(ChronoField.ALIGNED_DAY_OF_WEEK_IN_MONTH)) {
                    ChronoField chronoField3 = ChronoField.YEAR;
                    int checkValidIntValue2 = chronoField3.checkValidIntValue(map.remove(chronoField3).longValue());
                    if (resolverStyle == ResolverStyle.LENIENT) {
                        long safeSubtract = Jdk8Methods.safeSubtract(map.remove(ChronoField.MONTH_OF_YEAR).longValue(), 1L);
                        return LocalDate.of(checkValidIntValue2, 1, 1).plusMonths(safeSubtract).plusWeeks(Jdk8Methods.safeSubtract(map.remove(ChronoField.ALIGNED_WEEK_OF_MONTH).longValue(), 1L)).plusDays(Jdk8Methods.safeSubtract(map.remove(ChronoField.ALIGNED_DAY_OF_WEEK_IN_MONTH).longValue(), 1L));
                    }
                    ChronoField chronoField4 = ChronoField.MONTH_OF_YEAR;
                    int checkValidIntValue3 = chronoField4.checkValidIntValue(map.remove(chronoField4).longValue());
                    ChronoField chronoField5 = ChronoField.ALIGNED_WEEK_OF_MONTH;
                    int checkValidIntValue4 = chronoField5.checkValidIntValue(map.remove(chronoField5).longValue());
                    ChronoField chronoField6 = ChronoField.ALIGNED_DAY_OF_WEEK_IN_MONTH;
                    LocalDate plusDays = LocalDate.of(checkValidIntValue2, checkValidIntValue3, 1).plusDays((chronoField6.checkValidIntValue(map.remove(chronoField6).longValue()) - 1) + ((checkValidIntValue4 - 1) * 7));
                    if (resolverStyle == ResolverStyle.STRICT && plusDays.get(ChronoField.MONTH_OF_YEAR) != checkValidIntValue3) {
                        throw new DateTimeException("Strict mode rejected date parsed to a different month");
                    }
                    return plusDays;
                } else if (map.containsKey(ChronoField.DAY_OF_WEEK)) {
                    ChronoField chronoField7 = ChronoField.YEAR;
                    int checkValidIntValue5 = chronoField7.checkValidIntValue(map.remove(chronoField7).longValue());
                    if (resolverStyle == ResolverStyle.LENIENT) {
                        long safeSubtract2 = Jdk8Methods.safeSubtract(map.remove(ChronoField.MONTH_OF_YEAR).longValue(), 1L);
                        return LocalDate.of(checkValidIntValue5, 1, 1).plusMonths(safeSubtract2).plusWeeks(Jdk8Methods.safeSubtract(map.remove(ChronoField.ALIGNED_WEEK_OF_MONTH).longValue(), 1L)).plusDays(Jdk8Methods.safeSubtract(map.remove(ChronoField.DAY_OF_WEEK).longValue(), 1L));
                    }
                    ChronoField chronoField8 = ChronoField.MONTH_OF_YEAR;
                    int checkValidIntValue6 = chronoField8.checkValidIntValue(map.remove(chronoField8).longValue());
                    ChronoField chronoField9 = ChronoField.ALIGNED_WEEK_OF_MONTH;
                    int checkValidIntValue7 = chronoField9.checkValidIntValue(map.remove(chronoField9).longValue());
                    ChronoField chronoField10 = ChronoField.DAY_OF_WEEK;
                    LocalDate mo13061with = LocalDate.of(checkValidIntValue5, checkValidIntValue6, 1).plusWeeks(checkValidIntValue7 - 1).mo13061with(TemporalAdjusters.nextOrSame(DayOfWeek.of(chronoField10.checkValidIntValue(map.remove(chronoField10).longValue()))));
                    if (resolverStyle == ResolverStyle.STRICT && mo13061with.get(ChronoField.MONTH_OF_YEAR) != checkValidIntValue6) {
                        throw new DateTimeException("Strict mode rejected date parsed to a different month");
                    }
                    return mo13061with;
                }
            }
        }
        if (map.containsKey(ChronoField.DAY_OF_YEAR)) {
            ChronoField chronoField11 = ChronoField.YEAR;
            int checkValidIntValue8 = chronoField11.checkValidIntValue(map.remove(chronoField11).longValue());
            if (resolverStyle == ResolverStyle.LENIENT) {
                return LocalDate.ofYearDay(checkValidIntValue8, 1).plusDays(Jdk8Methods.safeSubtract(map.remove(ChronoField.DAY_OF_YEAR).longValue(), 1L));
            }
            ChronoField chronoField12 = ChronoField.DAY_OF_YEAR;
            return LocalDate.ofYearDay(checkValidIntValue8, chronoField12.checkValidIntValue(map.remove(chronoField12).longValue()));
        } else if (!map.containsKey(ChronoField.ALIGNED_WEEK_OF_YEAR)) {
            return null;
        } else {
            if (map.containsKey(ChronoField.ALIGNED_DAY_OF_WEEK_IN_YEAR)) {
                ChronoField chronoField13 = ChronoField.YEAR;
                int checkValidIntValue9 = chronoField13.checkValidIntValue(map.remove(chronoField13).longValue());
                if (resolverStyle == ResolverStyle.LENIENT) {
                    return LocalDate.of(checkValidIntValue9, 1, 1).plusWeeks(Jdk8Methods.safeSubtract(map.remove(ChronoField.ALIGNED_WEEK_OF_YEAR).longValue(), 1L)).plusDays(Jdk8Methods.safeSubtract(map.remove(ChronoField.ALIGNED_DAY_OF_WEEK_IN_YEAR).longValue(), 1L));
                }
                ChronoField chronoField14 = ChronoField.ALIGNED_WEEK_OF_YEAR;
                int checkValidIntValue10 = chronoField14.checkValidIntValue(map.remove(chronoField14).longValue());
                ChronoField chronoField15 = ChronoField.ALIGNED_DAY_OF_WEEK_IN_YEAR;
                LocalDate plusDays2 = LocalDate.of(checkValidIntValue9, 1, 1).plusDays((chronoField15.checkValidIntValue(map.remove(chronoField15).longValue()) - 1) + ((checkValidIntValue10 - 1) * 7));
                if (resolverStyle == ResolverStyle.STRICT && plusDays2.get(ChronoField.YEAR) != checkValidIntValue9) {
                    throw new DateTimeException("Strict mode rejected date parsed to a different year");
                }
                return plusDays2;
            } else if (!map.containsKey(ChronoField.DAY_OF_WEEK)) {
                return null;
            } else {
                ChronoField chronoField16 = ChronoField.YEAR;
                int checkValidIntValue11 = chronoField16.checkValidIntValue(map.remove(chronoField16).longValue());
                if (resolverStyle == ResolverStyle.LENIENT) {
                    return LocalDate.of(checkValidIntValue11, 1, 1).plusWeeks(Jdk8Methods.safeSubtract(map.remove(ChronoField.ALIGNED_WEEK_OF_YEAR).longValue(), 1L)).plusDays(Jdk8Methods.safeSubtract(map.remove(ChronoField.DAY_OF_WEEK).longValue(), 1L));
                }
                ChronoField chronoField17 = ChronoField.ALIGNED_WEEK_OF_YEAR;
                int checkValidIntValue12 = chronoField17.checkValidIntValue(map.remove(chronoField17).longValue());
                ChronoField chronoField18 = ChronoField.DAY_OF_WEEK;
                LocalDate mo13061with2 = LocalDate.of(checkValidIntValue11, 1, 1).plusWeeks(checkValidIntValue12 - 1).mo13061with(TemporalAdjusters.nextOrSame(DayOfWeek.of(chronoField18.checkValidIntValue(map.remove(chronoField18).longValue()))));
                if (resolverStyle == ResolverStyle.STRICT && mo13061with2.get(ChronoField.YEAR) != checkValidIntValue11) {
                    throw new DateTimeException("Strict mode rejected date parsed to a different month");
                }
                return mo13061with2;
            }
        }
    }

    @Override // org.threeten.bp.chrono.Chronology
    /* renamed from: dateYearDay */
    public LocalDate mo13051dateYearDay(Era era, int i, int i2) {
        return mo13050dateYearDay(prolepticYear(era, i), i2);
    }

    @Override // org.threeten.bp.chrono.Chronology
    /* renamed from: zonedDateTime */
    public ZonedDateTime mo13002zonedDateTime(TemporalAccessor temporalAccessor) {
        return ZonedDateTime.from(temporalAccessor);
    }

    @Override // org.threeten.bp.chrono.Chronology
    /* renamed from: date */
    public LocalDate mo13044date(Era era, int i, int i2, int i3) {
        return mo13043date(prolepticYear(era, i), i2, i3);
    }

    @Override // org.threeten.bp.chrono.Chronology
    /* renamed from: dateNow */
    public LocalDate mo13047dateNow() {
        return mo13048dateNow(Clock.systemDefaultZone());
    }

    @Override // org.threeten.bp.chrono.Chronology
    /* renamed from: dateYearDay */
    public LocalDate mo13050dateYearDay(int i, int i2) {
        return LocalDate.ofYearDay(i, i2);
    }

    @Override // org.threeten.bp.chrono.Chronology
    /* renamed from: zonedDateTime */
    public ZonedDateTime mo13001zonedDateTime(Instant instant, ZoneId zoneId) {
        return ZonedDateTime.ofInstant(instant, zoneId);
    }

    @Override // org.threeten.bp.chrono.Chronology
    /* renamed from: date */
    public LocalDate mo13043date(int i, int i2, int i3) {
        return LocalDate.of(i, i2, i3);
    }

    @Override // org.threeten.bp.chrono.Chronology
    /* renamed from: dateNow */
    public LocalDate mo13049dateNow(ZoneId zoneId) {
        return mo13048dateNow(Clock.system(zoneId));
    }

    @Override // org.threeten.bp.chrono.Chronology
    /* renamed from: date */
    public LocalDate mo13045date(TemporalAccessor temporalAccessor) {
        return LocalDate.from(temporalAccessor);
    }

    @Override // org.threeten.bp.chrono.Chronology
    /* renamed from: dateNow */
    public LocalDate mo13048dateNow(Clock clock) {
        Jdk8Methods.requireNonNull(clock, "clock");
        return mo13045date((TemporalAccessor) LocalDate.now(clock));
    }
}
