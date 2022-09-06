package org.threeten.bp.temporal;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.InvalidObjectException;
import java.io.Serializable;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import kotlinx.serialization.json.internal.JsonReaderKt;
import org.threeten.bp.DateTimeException;
import org.threeten.bp.DayOfWeek;
import org.threeten.bp.Year;
import org.threeten.bp.chrono.ChronoLocalDate;
import org.threeten.bp.chrono.Chronology;
import org.threeten.bp.format.ResolverStyle;
import org.threeten.bp.jdk8.Jdk8Methods;
/* loaded from: classes5.dex */
public final class WeekFields implements Serializable {
    private static final ConcurrentMap<String, WeekFields> CACHE = new ConcurrentHashMap(4, 0.75f, 2);
    public static final WeekFields ISO = new WeekFields(DayOfWeek.MONDAY, 4);
    public static final WeekFields SUNDAY_START = of(DayOfWeek.SUNDAY, 1);
    private static final long serialVersionUID = -1177360819670808121L;
    private final DayOfWeek firstDayOfWeek;
    private final int minimalDays;
    private final transient TemporalField dayOfWeek = ComputedDayOfField.ofDayOfWeekField(this);
    private final transient TemporalField weekOfMonth = ComputedDayOfField.ofWeekOfMonthField(this);
    private final transient TemporalField weekOfYear = ComputedDayOfField.ofWeekOfYearField(this);
    private final transient TemporalField weekOfWeekBasedYear = ComputedDayOfField.ofWeekOfWeekBasedYearField(this);
    private final transient TemporalField weekBasedYear = ComputedDayOfField.ofWeekBasedYearField(this);

    /* loaded from: classes5.dex */
    static class ComputedDayOfField implements TemporalField {
        private final TemporalUnit baseUnit;
        private final String name;
        private final ValueRange range;
        private final TemporalUnit rangeUnit;
        private final WeekFields weekDef;
        private static final ValueRange DAY_OF_WEEK_RANGE = ValueRange.of(1, 7);
        private static final ValueRange WEEK_OF_MONTH_RANGE = ValueRange.of(0, 1, 4, 6);
        private static final ValueRange WEEK_OF_YEAR_RANGE = ValueRange.of(0, 1, 52, 54);
        private static final ValueRange WEEK_OF_WEEK_BASED_YEAR_RANGE = ValueRange.of(1, 52, 53);
        private static final ValueRange WEEK_BASED_YEAR_RANGE = ChronoField.YEAR.range();

        private ComputedDayOfField(String str, WeekFields weekFields, TemporalUnit temporalUnit, TemporalUnit temporalUnit2, ValueRange valueRange) {
            this.name = str;
            this.weekDef = weekFields;
            this.baseUnit = temporalUnit;
            this.rangeUnit = temporalUnit2;
            this.range = valueRange;
        }

        private int computeWeek(int i, int i2) {
            return ((i2 - 1) + (i + 7)) / 7;
        }

        private int localizedDayOfWeek(TemporalAccessor temporalAccessor, int i) {
            return Jdk8Methods.floorMod(temporalAccessor.get(ChronoField.DAY_OF_WEEK) - i, 7) + 1;
        }

        private int localizedWBY(TemporalAccessor temporalAccessor) {
            int floorMod = Jdk8Methods.floorMod(temporalAccessor.get(ChronoField.DAY_OF_WEEK) - this.weekDef.getFirstDayOfWeek().getValue(), 7) + 1;
            int i = temporalAccessor.get(ChronoField.YEAR);
            long localizedWeekOfYear = localizedWeekOfYear(temporalAccessor, floorMod);
            if (localizedWeekOfYear == 0) {
                return i - 1;
            }
            if (localizedWeekOfYear < 53) {
                return i;
            }
            return localizedWeekOfYear >= ((long) computeWeek(startOfWeekOffset(temporalAccessor.get(ChronoField.DAY_OF_YEAR), floorMod), this.weekDef.getMinimalDaysInFirstWeek() + (Year.isLeap((long) i) ? 366 : 365))) ? i + 1 : i;
        }

        private int localizedWOWBY(TemporalAccessor temporalAccessor) {
            int floorMod = Jdk8Methods.floorMod(temporalAccessor.get(ChronoField.DAY_OF_WEEK) - this.weekDef.getFirstDayOfWeek().getValue(), 7) + 1;
            long localizedWeekOfYear = localizedWeekOfYear(temporalAccessor, floorMod);
            if (localizedWeekOfYear == 0) {
                return ((int) localizedWeekOfYear(Chronology.from(temporalAccessor).mo13045date(temporalAccessor).mo13056minus(1L, (TemporalUnit) ChronoUnit.WEEKS), floorMod)) + 1;
            }
            if (localizedWeekOfYear >= 53) {
                int computeWeek = computeWeek(startOfWeekOffset(temporalAccessor.get(ChronoField.DAY_OF_YEAR), floorMod), this.weekDef.getMinimalDaysInFirstWeek() + (Year.isLeap((long) temporalAccessor.get(ChronoField.YEAR)) ? 366 : 365));
                if (localizedWeekOfYear >= computeWeek) {
                    return (int) (localizedWeekOfYear - (computeWeek - 1));
                }
            }
            return (int) localizedWeekOfYear;
        }

        private long localizedWeekOfMonth(TemporalAccessor temporalAccessor, int i) {
            int i2 = temporalAccessor.get(ChronoField.DAY_OF_MONTH);
            return computeWeek(startOfWeekOffset(i2, i), i2);
        }

        private long localizedWeekOfYear(TemporalAccessor temporalAccessor, int i) {
            int i2 = temporalAccessor.get(ChronoField.DAY_OF_YEAR);
            return computeWeek(startOfWeekOffset(i2, i), i2);
        }

        static ComputedDayOfField ofDayOfWeekField(WeekFields weekFields) {
            return new ComputedDayOfField("DayOfWeek", weekFields, ChronoUnit.DAYS, ChronoUnit.WEEKS, DAY_OF_WEEK_RANGE);
        }

        static ComputedDayOfField ofWeekBasedYearField(WeekFields weekFields) {
            return new ComputedDayOfField("WeekBasedYear", weekFields, IsoFields.WEEK_BASED_YEARS, ChronoUnit.FOREVER, WEEK_BASED_YEAR_RANGE);
        }

        static ComputedDayOfField ofWeekOfMonthField(WeekFields weekFields) {
            return new ComputedDayOfField("WeekOfMonth", weekFields, ChronoUnit.WEEKS, ChronoUnit.MONTHS, WEEK_OF_MONTH_RANGE);
        }

        static ComputedDayOfField ofWeekOfWeekBasedYearField(WeekFields weekFields) {
            return new ComputedDayOfField("WeekOfWeekBasedYear", weekFields, ChronoUnit.WEEKS, IsoFields.WEEK_BASED_YEARS, WEEK_OF_WEEK_BASED_YEAR_RANGE);
        }

        static ComputedDayOfField ofWeekOfYearField(WeekFields weekFields) {
            return new ComputedDayOfField("WeekOfYear", weekFields, ChronoUnit.WEEKS, ChronoUnit.YEARS, WEEK_OF_YEAR_RANGE);
        }

        private ValueRange rangeWOWBY(TemporalAccessor temporalAccessor) {
            int floorMod = Jdk8Methods.floorMod(temporalAccessor.get(ChronoField.DAY_OF_WEEK) - this.weekDef.getFirstDayOfWeek().getValue(), 7) + 1;
            long localizedWeekOfYear = localizedWeekOfYear(temporalAccessor, floorMod);
            if (localizedWeekOfYear == 0) {
                return rangeWOWBY(Chronology.from(temporalAccessor).mo13045date(temporalAccessor).mo13056minus(2L, (TemporalUnit) ChronoUnit.WEEKS));
            }
            int computeWeek = computeWeek(startOfWeekOffset(temporalAccessor.get(ChronoField.DAY_OF_YEAR), floorMod), this.weekDef.getMinimalDaysInFirstWeek() + (Year.isLeap((long) temporalAccessor.get(ChronoField.YEAR)) ? 366 : 365));
            if (localizedWeekOfYear >= computeWeek) {
                return rangeWOWBY(Chronology.from(temporalAccessor).mo13045date(temporalAccessor).mo13060plus(2L, (TemporalUnit) ChronoUnit.WEEKS));
            }
            return ValueRange.of(1L, computeWeek - 1);
        }

        private int startOfWeekOffset(int i, int i2) {
            int floorMod = Jdk8Methods.floorMod(i - i2, 7);
            return floorMod + 1 > this.weekDef.getMinimalDaysInFirstWeek() ? 7 - floorMod : -floorMod;
        }

        @Override // org.threeten.bp.temporal.TemporalField
        public <R extends Temporal> R adjustInto(R r, long j) {
            int checkValidIntValue = this.range.checkValidIntValue(j, this);
            int i = r.get(this);
            if (checkValidIntValue == i) {
                return r;
            }
            if (this.rangeUnit == ChronoUnit.FOREVER) {
                int i2 = r.get(this.weekDef.weekOfWeekBasedYear);
                Temporal mo13060plus = r.mo13060plus((long) ((j - i) * 52.1775d), ChronoUnit.WEEKS);
                if (mo13060plus.get(this) > checkValidIntValue) {
                    return (R) mo13060plus.mo13056minus(mo13060plus.get(this.weekDef.weekOfWeekBasedYear), ChronoUnit.WEEKS);
                }
                if (mo13060plus.get(this) < checkValidIntValue) {
                    mo13060plus = mo13060plus.mo13060plus(2L, ChronoUnit.WEEKS);
                }
                R r2 = (R) mo13060plus.mo13060plus(i2 - mo13060plus.get(this.weekDef.weekOfWeekBasedYear), ChronoUnit.WEEKS);
                return r2.get(this) > checkValidIntValue ? (R) r2.mo13056minus(1L, ChronoUnit.WEEKS) : r2;
            }
            return (R) r.mo13060plus(checkValidIntValue - i, this.baseUnit);
        }

        @Override // org.threeten.bp.temporal.TemporalField
        public TemporalUnit getBaseUnit() {
            return this.baseUnit;
        }

        @Override // org.threeten.bp.temporal.TemporalField
        public String getDisplayName(Locale locale) {
            Jdk8Methods.requireNonNull(locale, "locale");
            return this.rangeUnit == ChronoUnit.YEARS ? "Week" : toString();
        }

        @Override // org.threeten.bp.temporal.TemporalField
        public long getFrom(TemporalAccessor temporalAccessor) {
            int localizedWBY;
            int floorMod = Jdk8Methods.floorMod(temporalAccessor.get(ChronoField.DAY_OF_WEEK) - this.weekDef.getFirstDayOfWeek().getValue(), 7) + 1;
            TemporalUnit temporalUnit = this.rangeUnit;
            if (temporalUnit == ChronoUnit.WEEKS) {
                return floorMod;
            }
            if (temporalUnit == ChronoUnit.MONTHS) {
                int i = temporalAccessor.get(ChronoField.DAY_OF_MONTH);
                localizedWBY = computeWeek(startOfWeekOffset(i, floorMod), i);
            } else if (temporalUnit == ChronoUnit.YEARS) {
                int i2 = temporalAccessor.get(ChronoField.DAY_OF_YEAR);
                localizedWBY = computeWeek(startOfWeekOffset(i2, floorMod), i2);
            } else if (temporalUnit == IsoFields.WEEK_BASED_YEARS) {
                localizedWBY = localizedWOWBY(temporalAccessor);
            } else if (temporalUnit == ChronoUnit.FOREVER) {
                localizedWBY = localizedWBY(temporalAccessor);
            } else {
                throw new IllegalStateException("unreachable");
            }
            return localizedWBY;
        }

        @Override // org.threeten.bp.temporal.TemporalField
        public TemporalUnit getRangeUnit() {
            return this.rangeUnit;
        }

        @Override // org.threeten.bp.temporal.TemporalField
        public boolean isDateBased() {
            return true;
        }

        @Override // org.threeten.bp.temporal.TemporalField
        public boolean isSupportedBy(TemporalAccessor temporalAccessor) {
            if (temporalAccessor.isSupported(ChronoField.DAY_OF_WEEK)) {
                TemporalUnit temporalUnit = this.rangeUnit;
                if (temporalUnit == ChronoUnit.WEEKS) {
                    return true;
                }
                if (temporalUnit == ChronoUnit.MONTHS) {
                    return temporalAccessor.isSupported(ChronoField.DAY_OF_MONTH);
                }
                if (temporalUnit == ChronoUnit.YEARS) {
                    return temporalAccessor.isSupported(ChronoField.DAY_OF_YEAR);
                }
                if (temporalUnit == IsoFields.WEEK_BASED_YEARS) {
                    return temporalAccessor.isSupported(ChronoField.EPOCH_DAY);
                }
                if (temporalUnit != ChronoUnit.FOREVER) {
                    return false;
                }
                return temporalAccessor.isSupported(ChronoField.EPOCH_DAY);
            }
            return false;
        }

        @Override // org.threeten.bp.temporal.TemporalField
        public boolean isTimeBased() {
            return false;
        }

        @Override // org.threeten.bp.temporal.TemporalField
        public ValueRange range() {
            return this.range;
        }

        @Override // org.threeten.bp.temporal.TemporalField
        public ValueRange rangeRefinedBy(TemporalAccessor temporalAccessor) {
            ChronoField chronoField;
            TemporalUnit temporalUnit = this.rangeUnit;
            if (temporalUnit == ChronoUnit.WEEKS) {
                return this.range;
            }
            if (temporalUnit == ChronoUnit.MONTHS) {
                chronoField = ChronoField.DAY_OF_MONTH;
            } else if (temporalUnit == ChronoUnit.YEARS) {
                chronoField = ChronoField.DAY_OF_YEAR;
            } else if (temporalUnit == IsoFields.WEEK_BASED_YEARS) {
                return rangeWOWBY(temporalAccessor);
            } else {
                if (temporalUnit == ChronoUnit.FOREVER) {
                    return temporalAccessor.range(ChronoField.YEAR);
                }
                throw new IllegalStateException("unreachable");
            }
            int value = this.weekDef.getFirstDayOfWeek().getValue();
            int startOfWeekOffset = startOfWeekOffset(temporalAccessor.get(chronoField), Jdk8Methods.floorMod(temporalAccessor.get(ChronoField.DAY_OF_WEEK) - value, 7) + 1);
            ValueRange range = temporalAccessor.range(chronoField);
            return ValueRange.of(computeWeek(startOfWeekOffset, (int) range.getMinimum()), computeWeek(startOfWeekOffset, (int) range.getMaximum()));
        }

        @Override // org.threeten.bp.temporal.TemporalField
        public TemporalAccessor resolve(Map<TemporalField, Long> map, TemporalAccessor temporalAccessor, ResolverStyle resolverStyle) {
            int localizedDayOfWeek;
            long checkValidIntValue;
            int localizedDayOfWeek2;
            ChronoLocalDate mo13043date;
            int localizedDayOfWeek3;
            long checkValidIntValue2;
            int localizedDayOfWeek4;
            ChronoLocalDate mo13043date2;
            long checkValidIntValue3;
            int localizedDayOfWeek5;
            long localizedWeekOfYear;
            int value = this.weekDef.getFirstDayOfWeek().getValue();
            if (this.rangeUnit == ChronoUnit.WEEKS) {
                map.put(ChronoField.DAY_OF_WEEK, Long.valueOf(Jdk8Methods.floorMod((this.range.checkValidIntValue(map.remove(this).longValue(), this) - 1) + (value - 1), 7) + 1));
                return null;
            } else if (!map.containsKey(ChronoField.DAY_OF_WEEK)) {
                return null;
            } else {
                if (this.rangeUnit == ChronoUnit.FOREVER) {
                    if (!map.containsKey(this.weekDef.weekOfWeekBasedYear)) {
                        return null;
                    }
                    Chronology from = Chronology.from(temporalAccessor);
                    ChronoField chronoField = ChronoField.DAY_OF_WEEK;
                    int floorMod = Jdk8Methods.floorMod(chronoField.checkValidIntValue(map.get(chronoField).longValue()) - value, 7) + 1;
                    int checkValidIntValue4 = range().checkValidIntValue(map.get(this).longValue(), this);
                    if (resolverStyle == ResolverStyle.LENIENT) {
                        mo13043date2 = from.mo13043date(checkValidIntValue4, 1, this.weekDef.getMinimalDaysInFirstWeek());
                        checkValidIntValue3 = map.get(this.weekDef.weekOfWeekBasedYear).longValue();
                        localizedDayOfWeek5 = localizedDayOfWeek(mo13043date2, value);
                        localizedWeekOfYear = localizedWeekOfYear(mo13043date2, localizedDayOfWeek5);
                    } else {
                        mo13043date2 = from.mo13043date(checkValidIntValue4, 1, this.weekDef.getMinimalDaysInFirstWeek());
                        checkValidIntValue3 = this.weekDef.weekOfWeekBasedYear.range().checkValidIntValue(map.get(this.weekDef.weekOfWeekBasedYear).longValue(), this.weekDef.weekOfWeekBasedYear);
                        localizedDayOfWeek5 = localizedDayOfWeek(mo13043date2, value);
                        localizedWeekOfYear = localizedWeekOfYear(mo13043date2, localizedDayOfWeek5);
                    }
                    ChronoLocalDate mo13060plus = mo13043date2.mo13060plus(((checkValidIntValue3 - localizedWeekOfYear) * 7) + (floorMod - localizedDayOfWeek5), (TemporalUnit) ChronoUnit.DAYS);
                    if (resolverStyle == ResolverStyle.STRICT && mo13060plus.getLong(this) != map.get(this).longValue()) {
                        throw new DateTimeException("Strict mode rejected date parsed to a different year");
                    }
                    map.remove(this);
                    map.remove(this.weekDef.weekOfWeekBasedYear);
                    map.remove(ChronoField.DAY_OF_WEEK);
                    return mo13060plus;
                } else if (!map.containsKey(ChronoField.YEAR)) {
                    return null;
                } else {
                    ChronoField chronoField2 = ChronoField.DAY_OF_WEEK;
                    int floorMod2 = Jdk8Methods.floorMod(chronoField2.checkValidIntValue(map.get(chronoField2).longValue()) - value, 7) + 1;
                    ChronoField chronoField3 = ChronoField.YEAR;
                    int checkValidIntValue5 = chronoField3.checkValidIntValue(map.get(chronoField3).longValue());
                    Chronology from2 = Chronology.from(temporalAccessor);
                    TemporalUnit temporalUnit = this.rangeUnit;
                    if (temporalUnit == ChronoUnit.MONTHS) {
                        if (!map.containsKey(ChronoField.MONTH_OF_YEAR)) {
                            return null;
                        }
                        long longValue = map.remove(this).longValue();
                        if (resolverStyle == ResolverStyle.LENIENT) {
                            mo13043date = from2.mo13043date(checkValidIntValue5, 1, 1).mo13060plus(map.get(ChronoField.MONTH_OF_YEAR).longValue() - 1, (TemporalUnit) ChronoUnit.MONTHS);
                            checkValidIntValue2 = ((longValue - localizedWeekOfMonth(mo13043date, localizedDayOfWeek(mo13043date, value))) * 7) + (floorMod2 - localizedDayOfWeek4);
                        } else {
                            ChronoField chronoField4 = ChronoField.MONTH_OF_YEAR;
                            mo13043date = from2.mo13043date(checkValidIntValue5, chronoField4.checkValidIntValue(map.get(chronoField4).longValue()), 8);
                            checkValidIntValue2 = ((this.range.checkValidIntValue(longValue, this) - localizedWeekOfMonth(mo13043date, localizedDayOfWeek(mo13043date, value))) * 7) + (floorMod2 - localizedDayOfWeek3);
                        }
                        ChronoLocalDate mo13060plus2 = mo13043date.mo13060plus(checkValidIntValue2, (TemporalUnit) ChronoUnit.DAYS);
                        if (resolverStyle == ResolverStyle.STRICT && mo13060plus2.getLong(ChronoField.MONTH_OF_YEAR) != map.get(ChronoField.MONTH_OF_YEAR).longValue()) {
                            throw new DateTimeException("Strict mode rejected date parsed to a different month");
                        }
                        map.remove(this);
                        map.remove(ChronoField.YEAR);
                        map.remove(ChronoField.MONTH_OF_YEAR);
                        map.remove(ChronoField.DAY_OF_WEEK);
                        return mo13060plus2;
                    } else if (temporalUnit == ChronoUnit.YEARS) {
                        long longValue2 = map.remove(this).longValue();
                        ChronoLocalDate mo13043date3 = from2.mo13043date(checkValidIntValue5, 1, 1);
                        if (resolverStyle == ResolverStyle.LENIENT) {
                            checkValidIntValue = ((longValue2 - localizedWeekOfYear(mo13043date3, localizedDayOfWeek(mo13043date3, value))) * 7) + (floorMod2 - localizedDayOfWeek2);
                        } else {
                            checkValidIntValue = ((this.range.checkValidIntValue(longValue2, this) - localizedWeekOfYear(mo13043date3, localizedDayOfWeek(mo13043date3, value))) * 7) + (floorMod2 - localizedDayOfWeek);
                        }
                        ChronoLocalDate mo13060plus3 = mo13043date3.mo13060plus(checkValidIntValue, (TemporalUnit) ChronoUnit.DAYS);
                        if (resolverStyle == ResolverStyle.STRICT && mo13060plus3.getLong(ChronoField.YEAR) != map.get(ChronoField.YEAR).longValue()) {
                            throw new DateTimeException("Strict mode rejected date parsed to a different year");
                        }
                        map.remove(this);
                        map.remove(ChronoField.YEAR);
                        map.remove(ChronoField.DAY_OF_WEEK);
                        return mo13060plus3;
                    } else {
                        throw new IllegalStateException("unreachable");
                    }
                }
            }
        }

        public String toString() {
            return this.name + "[" + this.weekDef.toString() + "]";
        }
    }

    private WeekFields(DayOfWeek dayOfWeek, int i) {
        Jdk8Methods.requireNonNull(dayOfWeek, "firstDayOfWeek");
        if (i >= 1 && i <= 7) {
            this.firstDayOfWeek = dayOfWeek;
            this.minimalDays = i;
            return;
        }
        throw new IllegalArgumentException("Minimal number of days is invalid");
    }

    public static WeekFields of(Locale locale) {
        Jdk8Methods.requireNonNull(locale, "locale");
        GregorianCalendar gregorianCalendar = new GregorianCalendar(new Locale(locale.getLanguage(), locale.getCountry()));
        return of(DayOfWeek.SUNDAY.plus(gregorianCalendar.getFirstDayOfWeek() - 1), gregorianCalendar.getMinimalDaysInFirstWeek());
    }

    private Object readResolve() throws InvalidObjectException {
        try {
            return of(this.firstDayOfWeek, this.minimalDays);
        } catch (IllegalArgumentException e) {
            throw new InvalidObjectException(GeneratedOutlineSupport1.outline43(e, GeneratedOutlineSupport1.outline107("Invalid WeekFields")));
        }
    }

    public TemporalField dayOfWeek() {
        return this.dayOfWeek;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof WeekFields) && hashCode() == obj.hashCode();
    }

    public DayOfWeek getFirstDayOfWeek() {
        return this.firstDayOfWeek;
    }

    public int getMinimalDaysInFirstWeek() {
        return this.minimalDays;
    }

    public int hashCode() {
        return (this.firstDayOfWeek.ordinal() * 7) + this.minimalDays;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("WeekFields[");
        outline107.append(this.firstDayOfWeek);
        outline107.append(JsonReaderKt.COMMA);
        return GeneratedOutlineSupport1.outline85(outline107, this.minimalDays, JsonReaderKt.END_LIST);
    }

    public TemporalField weekBasedYear() {
        return this.weekBasedYear;
    }

    public TemporalField weekOfMonth() {
        return this.weekOfMonth;
    }

    public TemporalField weekOfWeekBasedYear() {
        return this.weekOfWeekBasedYear;
    }

    public TemporalField weekOfYear() {
        return this.weekOfYear;
    }

    public static WeekFields of(DayOfWeek dayOfWeek, int i) {
        String str = dayOfWeek.toString() + i;
        WeekFields weekFields = CACHE.get(str);
        if (weekFields == null) {
            CACHE.putIfAbsent(str, new WeekFields(dayOfWeek, i));
            return CACHE.get(str);
        }
        return weekFields;
    }
}
