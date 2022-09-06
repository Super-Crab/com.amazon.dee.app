package org.threeten.bp.temporal;

import java.util.Locale;
import java.util.Map;
import org.threeten.bp.DayOfWeek;
import org.threeten.bp.Duration;
import org.threeten.bp.LocalDate;
import org.threeten.bp.chrono.Chronology;
import org.threeten.bp.chrono.IsoChronology;
import org.threeten.bp.format.ResolverStyle;
import org.threeten.bp.jdk8.Jdk8Methods;
/* loaded from: classes5.dex */
public final class IsoFields {
    public static final TemporalField DAY_OF_QUARTER = Field.DAY_OF_QUARTER;
    public static final TemporalField QUARTER_OF_YEAR = Field.QUARTER_OF_YEAR;
    public static final TemporalField WEEK_OF_WEEK_BASED_YEAR = Field.WEEK_OF_WEEK_BASED_YEAR;
    public static final TemporalField WEEK_BASED_YEAR = Field.WEEK_BASED_YEAR;
    public static final TemporalUnit WEEK_BASED_YEARS = Unit.WEEK_BASED_YEARS;
    public static final TemporalUnit QUARTER_YEARS = Unit.QUARTER_YEARS;

    /* renamed from: org.threeten.bp.temporal.IsoFields$1  reason: invalid class name */
    /* loaded from: classes5.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$threeten$bp$temporal$IsoFields$Unit = new int[Unit.values().length];

        static {
            try {
                $SwitchMap$org$threeten$bp$temporal$IsoFields$Unit[Unit.WEEK_BASED_YEARS.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$org$threeten$bp$temporal$IsoFields$Unit[Unit.QUARTER_YEARS.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes5.dex */
    public enum Field implements TemporalField {
        DAY_OF_QUARTER { // from class: org.threeten.bp.temporal.IsoFields.Field.1
            @Override // org.threeten.bp.temporal.TemporalField
            public <R extends Temporal> R adjustInto(R r, long j) {
                long from = getFrom(r);
                range().checkValidValue(j, this);
                ChronoField chronoField = ChronoField.DAY_OF_YEAR;
                return (R) r.mo13062with(chronoField, (j - from) + r.getLong(chronoField));
            }

            @Override // org.threeten.bp.temporal.TemporalField
            public TemporalUnit getBaseUnit() {
                return ChronoUnit.DAYS;
            }

            @Override // org.threeten.bp.temporal.TemporalField
            public long getFrom(TemporalAccessor temporalAccessor) {
                if (temporalAccessor.isSupported(this)) {
                    return temporalAccessor.get(ChronoField.DAY_OF_YEAR) - Field.QUARTER_DAYS[((temporalAccessor.get(ChronoField.MONTH_OF_YEAR) - 1) / 3) + (IsoChronology.INSTANCE.isLeapYear(temporalAccessor.getLong(ChronoField.YEAR)) ? 4 : 0)];
                }
                throw new UnsupportedTemporalTypeException("Unsupported field: DayOfQuarter");
            }

            @Override // org.threeten.bp.temporal.TemporalField
            public TemporalUnit getRangeUnit() {
                return IsoFields.QUARTER_YEARS;
            }

            @Override // org.threeten.bp.temporal.TemporalField
            public boolean isSupportedBy(TemporalAccessor temporalAccessor) {
                return temporalAccessor.isSupported(ChronoField.DAY_OF_YEAR) && temporalAccessor.isSupported(ChronoField.MONTH_OF_YEAR) && temporalAccessor.isSupported(ChronoField.YEAR) && Field.isIso(temporalAccessor);
            }

            @Override // org.threeten.bp.temporal.TemporalField
            public ValueRange range() {
                return ValueRange.of(1L, 90L, 92L);
            }

            @Override // org.threeten.bp.temporal.TemporalField
            public ValueRange rangeRefinedBy(TemporalAccessor temporalAccessor) {
                if (temporalAccessor.isSupported(this)) {
                    long j = temporalAccessor.getLong(Field.QUARTER_OF_YEAR);
                    if (j == 1) {
                        return IsoChronology.INSTANCE.isLeapYear(temporalAccessor.getLong(ChronoField.YEAR)) ? ValueRange.of(1L, 91L) : ValueRange.of(1L, 90L);
                    } else if (j == 2) {
                        return ValueRange.of(1L, 91L);
                    } else {
                        if (j != 3 && j != 4) {
                            return range();
                        }
                        return ValueRange.of(1L, 92L);
                    }
                }
                throw new UnsupportedTemporalTypeException("Unsupported field: DayOfQuarter");
            }

            /* JADX WARN: Code restructure failed: missing block: B:18:0x0079, code lost:
                if (r0 == 2) goto L19;
             */
            @Override // org.threeten.bp.temporal.IsoFields.Field, org.threeten.bp.temporal.TemporalField
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public org.threeten.bp.temporal.TemporalAccessor resolve(java.util.Map<org.threeten.bp.temporal.TemporalField, java.lang.Long> r11, org.threeten.bp.temporal.TemporalAccessor r12, org.threeten.bp.format.ResolverStyle r13) {
                /*
                    r10 = this;
                    org.threeten.bp.temporal.ChronoField r12 = org.threeten.bp.temporal.ChronoField.YEAR
                    java.lang.Object r12 = r11.get(r12)
                    java.lang.Long r12 = (java.lang.Long) r12
                    org.threeten.bp.temporal.IsoFields$Field r0 = org.threeten.bp.temporal.IsoFields.Field.QUARTER_OF_YEAR
                    java.lang.Object r0 = r11.get(r0)
                    java.lang.Long r0 = (java.lang.Long) r0
                    if (r12 == 0) goto La6
                    if (r0 != 0) goto L16
                    goto La6
                L16:
                    org.threeten.bp.temporal.ChronoField r1 = org.threeten.bp.temporal.ChronoField.YEAR
                    long r2 = r12.longValue()
                    int r12 = r1.checkValidIntValue(r2)
                    org.threeten.bp.temporal.IsoFields$Field r1 = org.threeten.bp.temporal.IsoFields.Field.DAY_OF_QUARTER
                    java.lang.Object r1 = r11.get(r1)
                    java.lang.Long r1 = (java.lang.Long) r1
                    long r1 = r1.longValue()
                    org.threeten.bp.format.ResolverStyle r3 = org.threeten.bp.format.ResolverStyle.LENIENT
                    r4 = 3
                    r5 = 1
                    r7 = 1
                    if (r13 != r3) goto L51
                    long r8 = r0.longValue()
                    org.threeten.bp.LocalDate r12 = org.threeten.bp.LocalDate.of(r12, r7, r7)
                    long r7 = org.threeten.bp.jdk8.Jdk8Methods.safeSubtract(r8, r5)
                    long r3 = org.threeten.bp.jdk8.Jdk8Methods.safeMultiply(r7, r4)
                    org.threeten.bp.LocalDate r12 = r12.plusMonths(r3)
                    long r0 = org.threeten.bp.jdk8.Jdk8Methods.safeSubtract(r1, r5)
                    org.threeten.bp.LocalDate r12 = r12.plusDays(r0)
                    goto L98
                L51:
                    org.threeten.bp.temporal.IsoFields$Field r3 = org.threeten.bp.temporal.IsoFields.Field.QUARTER_OF_YEAR
                    org.threeten.bp.temporal.ValueRange r3 = r3.range()
                    long r8 = r0.longValue()
                    org.threeten.bp.temporal.IsoFields$Field r0 = org.threeten.bp.temporal.IsoFields.Field.QUARTER_OF_YEAR
                    int r0 = r3.checkValidIntValue(r8, r0)
                    org.threeten.bp.format.ResolverStyle r3 = org.threeten.bp.format.ResolverStyle.STRICT
                    if (r13 != r3) goto L85
                    r13 = 92
                    r3 = 91
                    if (r0 != r7) goto L78
                    org.threeten.bp.chrono.IsoChronology r13 = org.threeten.bp.chrono.IsoChronology.INSTANCE
                    long r8 = (long) r12
                    boolean r13 = r13.isLeapYear(r8)
                    if (r13 == 0) goto L75
                    goto L7b
                L75:
                    r13 = 90
                    goto L7c
                L78:
                    r8 = 2
                    if (r0 != r8) goto L7c
                L7b:
                    r13 = r3
                L7c:
                    long r8 = (long) r13
                    org.threeten.bp.temporal.ValueRange r13 = org.threeten.bp.temporal.ValueRange.of(r5, r8)
                    r13.checkValidValue(r1, r10)
                    goto L8c
                L85:
                    org.threeten.bp.temporal.ValueRange r13 = r10.range()
                    r13.checkValidValue(r1, r10)
                L8c:
                    int r0 = r0 - r7
                    int r0 = r0 * r4
                    int r0 = r0 + r7
                    org.threeten.bp.LocalDate r12 = org.threeten.bp.LocalDate.of(r12, r0, r7)
                    long r1 = r1 - r5
                    org.threeten.bp.LocalDate r12 = r12.plusDays(r1)
                L98:
                    r11.remove(r10)
                    org.threeten.bp.temporal.ChronoField r13 = org.threeten.bp.temporal.ChronoField.YEAR
                    r11.remove(r13)
                    org.threeten.bp.temporal.IsoFields$Field r13 = org.threeten.bp.temporal.IsoFields.Field.QUARTER_OF_YEAR
                    r11.remove(r13)
                    return r12
                La6:
                    r11 = 0
                    return r11
                */
                throw new UnsupportedOperationException("Method not decompiled: org.threeten.bp.temporal.IsoFields.Field.AnonymousClass1.resolve(java.util.Map, org.threeten.bp.temporal.TemporalAccessor, org.threeten.bp.format.ResolverStyle):org.threeten.bp.temporal.TemporalAccessor");
            }

            @Override // java.lang.Enum
            public String toString() {
                return "DayOfQuarter";
            }
        },
        QUARTER_OF_YEAR { // from class: org.threeten.bp.temporal.IsoFields.Field.2
            @Override // org.threeten.bp.temporal.TemporalField
            public <R extends Temporal> R adjustInto(R r, long j) {
                long from = getFrom(r);
                range().checkValidValue(j, this);
                ChronoField chronoField = ChronoField.MONTH_OF_YEAR;
                return (R) r.mo13062with(chronoField, ((j - from) * 3) + r.getLong(chronoField));
            }

            @Override // org.threeten.bp.temporal.TemporalField
            public TemporalUnit getBaseUnit() {
                return IsoFields.QUARTER_YEARS;
            }

            @Override // org.threeten.bp.temporal.TemporalField
            public long getFrom(TemporalAccessor temporalAccessor) {
                if (temporalAccessor.isSupported(this)) {
                    return (temporalAccessor.getLong(ChronoField.MONTH_OF_YEAR) + 2) / 3;
                }
                throw new UnsupportedTemporalTypeException("Unsupported field: QuarterOfYear");
            }

            @Override // org.threeten.bp.temporal.TemporalField
            public TemporalUnit getRangeUnit() {
                return ChronoUnit.YEARS;
            }

            @Override // org.threeten.bp.temporal.TemporalField
            public boolean isSupportedBy(TemporalAccessor temporalAccessor) {
                return temporalAccessor.isSupported(ChronoField.MONTH_OF_YEAR) && Field.isIso(temporalAccessor);
            }

            @Override // org.threeten.bp.temporal.TemporalField
            public ValueRange range() {
                return ValueRange.of(1L, 4L);
            }

            @Override // org.threeten.bp.temporal.TemporalField
            public ValueRange rangeRefinedBy(TemporalAccessor temporalAccessor) {
                return range();
            }

            @Override // java.lang.Enum
            public String toString() {
                return "QuarterOfYear";
            }
        },
        WEEK_OF_WEEK_BASED_YEAR { // from class: org.threeten.bp.temporal.IsoFields.Field.3
            @Override // org.threeten.bp.temporal.TemporalField
            public <R extends Temporal> R adjustInto(R r, long j) {
                range().checkValidValue(j, this);
                return (R) r.mo13060plus(Jdk8Methods.safeSubtract(j, getFrom(r)), ChronoUnit.WEEKS);
            }

            @Override // org.threeten.bp.temporal.TemporalField
            public TemporalUnit getBaseUnit() {
                return ChronoUnit.WEEKS;
            }

            @Override // org.threeten.bp.temporal.IsoFields.Field, org.threeten.bp.temporal.TemporalField
            public String getDisplayName(Locale locale) {
                Jdk8Methods.requireNonNull(locale, "locale");
                return "Week";
            }

            @Override // org.threeten.bp.temporal.TemporalField
            public long getFrom(TemporalAccessor temporalAccessor) {
                if (temporalAccessor.isSupported(this)) {
                    return Field.getWeek(LocalDate.from(temporalAccessor));
                }
                throw new UnsupportedTemporalTypeException("Unsupported field: WeekOfWeekBasedYear");
            }

            @Override // org.threeten.bp.temporal.TemporalField
            public TemporalUnit getRangeUnit() {
                return IsoFields.WEEK_BASED_YEARS;
            }

            @Override // org.threeten.bp.temporal.TemporalField
            public boolean isSupportedBy(TemporalAccessor temporalAccessor) {
                return temporalAccessor.isSupported(ChronoField.EPOCH_DAY) && Field.isIso(temporalAccessor);
            }

            @Override // org.threeten.bp.temporal.TemporalField
            public ValueRange range() {
                return ValueRange.of(1L, 52L, 53L);
            }

            @Override // org.threeten.bp.temporal.TemporalField
            public ValueRange rangeRefinedBy(TemporalAccessor temporalAccessor) {
                if (temporalAccessor.isSupported(this)) {
                    return Field.getWeekRange(LocalDate.from(temporalAccessor));
                }
                throw new UnsupportedTemporalTypeException("Unsupported field: WeekOfWeekBasedYear");
            }

            @Override // org.threeten.bp.temporal.IsoFields.Field, org.threeten.bp.temporal.TemporalField
            public TemporalAccessor resolve(Map<TemporalField, Long> map, TemporalAccessor temporalAccessor, ResolverStyle resolverStyle) {
                LocalDate mo13062with;
                Long l = map.get(Field.WEEK_BASED_YEAR);
                Long l2 = map.get(ChronoField.DAY_OF_WEEK);
                if (l == null || l2 == null) {
                    return null;
                }
                int checkValidIntValue = Field.WEEK_BASED_YEAR.range().checkValidIntValue(l.longValue(), Field.WEEK_BASED_YEAR);
                long longValue = map.get(Field.WEEK_OF_WEEK_BASED_YEAR).longValue();
                if (resolverStyle == ResolverStyle.LENIENT) {
                    long longValue2 = l2.longValue();
                    long j = 0;
                    if (longValue2 > 7) {
                        long j2 = longValue2 - 1;
                        j = j2 / 7;
                        longValue2 = (j2 % 7) + 1;
                    } else if (longValue2 < 1) {
                        j = (longValue2 / 7) - 1;
                        longValue2 = (longValue2 % 7) + 7;
                    }
                    mo13062with = LocalDate.of(checkValidIntValue, 1, 4).plusWeeks(longValue - 1).plusWeeks(j).mo13062with((TemporalField) ChronoField.DAY_OF_WEEK, longValue2);
                } else {
                    int checkValidIntValue2 = ChronoField.DAY_OF_WEEK.checkValidIntValue(l2.longValue());
                    if (resolverStyle == ResolverStyle.STRICT) {
                        Field.getWeekRange(LocalDate.of(checkValidIntValue, 1, 4)).checkValidValue(longValue, this);
                    } else {
                        range().checkValidValue(longValue, this);
                    }
                    mo13062with = LocalDate.of(checkValidIntValue, 1, 4).plusWeeks(longValue - 1).mo13062with((TemporalField) ChronoField.DAY_OF_WEEK, checkValidIntValue2);
                }
                map.remove(this);
                map.remove(Field.WEEK_BASED_YEAR);
                map.remove(ChronoField.DAY_OF_WEEK);
                return mo13062with;
            }

            @Override // java.lang.Enum
            public String toString() {
                return "WeekOfWeekBasedYear";
            }
        },
        WEEK_BASED_YEAR { // from class: org.threeten.bp.temporal.IsoFields.Field.4
            @Override // org.threeten.bp.temporal.TemporalField
            public <R extends Temporal> R adjustInto(R r, long j) {
                LocalDate of;
                if (isSupportedBy(r)) {
                    int checkValidIntValue = range().checkValidIntValue(j, Field.WEEK_BASED_YEAR);
                    LocalDate from = LocalDate.from((TemporalAccessor) r);
                    int i = from.get(ChronoField.DAY_OF_WEEK);
                    int week = Field.getWeek(from);
                    if (week == 53 && Field.getWeekRange(checkValidIntValue) == 52) {
                        week = 52;
                    }
                    return (R) r.mo13061with(LocalDate.of(checkValidIntValue, 1, 4).plusDays(((week - 1) * 7) + (i - of.get(ChronoField.DAY_OF_WEEK))));
                }
                throw new UnsupportedTemporalTypeException("Unsupported field: WeekBasedYear");
            }

            @Override // org.threeten.bp.temporal.TemporalField
            public TemporalUnit getBaseUnit() {
                return IsoFields.WEEK_BASED_YEARS;
            }

            @Override // org.threeten.bp.temporal.TemporalField
            public long getFrom(TemporalAccessor temporalAccessor) {
                if (temporalAccessor.isSupported(this)) {
                    return Field.getWeekBasedYear(LocalDate.from(temporalAccessor));
                }
                throw new UnsupportedTemporalTypeException("Unsupported field: WeekBasedYear");
            }

            @Override // org.threeten.bp.temporal.TemporalField
            public TemporalUnit getRangeUnit() {
                return ChronoUnit.FOREVER;
            }

            @Override // org.threeten.bp.temporal.TemporalField
            public boolean isSupportedBy(TemporalAccessor temporalAccessor) {
                return temporalAccessor.isSupported(ChronoField.EPOCH_DAY) && Field.isIso(temporalAccessor);
            }

            @Override // org.threeten.bp.temporal.TemporalField
            public ValueRange range() {
                return ChronoField.YEAR.range();
            }

            @Override // org.threeten.bp.temporal.TemporalField
            public ValueRange rangeRefinedBy(TemporalAccessor temporalAccessor) {
                return ChronoField.YEAR.range();
            }

            @Override // java.lang.Enum
            public String toString() {
                return "WeekBasedYear";
            }
        };
        
        private static final int[] QUARTER_DAYS = {0, 90, 181, 273, 0, 91, 182, 274};

        /* JADX INFO: Access modifiers changed from: private */
        public static int getWeek(LocalDate localDate) {
            int ordinal = localDate.getDayOfWeek().ordinal();
            int dayOfYear = localDate.getDayOfYear() - 1;
            int i = (3 - ordinal) + dayOfYear;
            int i2 = (i - ((i / 7) * 7)) - 3;
            if (i2 < -3) {
                i2 += 7;
            }
            if (dayOfYear < i2) {
                return (int) getWeekRange(localDate.withDayOfYear(180).minusYears(1L)).getMaximum();
            }
            int i3 = ((dayOfYear - i2) / 7) + 1;
            if (i3 != 53) {
                return i3;
            }
            if (i2 == -3 || (i2 == -2 && localDate.isLeapYear())) {
                return i3;
            }
            return 1;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static int getWeekBasedYear(LocalDate localDate) {
            int year = localDate.getYear();
            int dayOfYear = localDate.getDayOfYear();
            if (dayOfYear <= 3) {
                return dayOfYear - localDate.getDayOfWeek().ordinal() < -2 ? year - 1 : year;
            } else if (dayOfYear < 363) {
                return year;
            } else {
                return ((dayOfYear - 363) - (localDate.isLeapYear() ? 1 : 0)) - localDate.getDayOfWeek().ordinal() >= 0 ? year + 1 : year;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static ValueRange getWeekRange(LocalDate localDate) {
            return ValueRange.of(1L, getWeekRange(getWeekBasedYear(localDate)));
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static boolean isIso(TemporalAccessor temporalAccessor) {
            return Chronology.from(temporalAccessor).equals(IsoChronology.INSTANCE);
        }

        @Override // org.threeten.bp.temporal.TemporalField
        public String getDisplayName(Locale locale) {
            Jdk8Methods.requireNonNull(locale, "locale");
            return toString();
        }

        @Override // org.threeten.bp.temporal.TemporalField
        public boolean isDateBased() {
            return true;
        }

        @Override // org.threeten.bp.temporal.TemporalField
        public boolean isTimeBased() {
            return false;
        }

        @Override // org.threeten.bp.temporal.TemporalField
        public TemporalAccessor resolve(Map<TemporalField, Long> map, TemporalAccessor temporalAccessor, ResolverStyle resolverStyle) {
            return null;
        }

        /* synthetic */ Field(AnonymousClass1 anonymousClass1) {
            this();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static int getWeekRange(int i) {
            LocalDate of = LocalDate.of(i, 1, 1);
            if (of.getDayOfWeek() != DayOfWeek.THURSDAY) {
                return (of.getDayOfWeek() != DayOfWeek.WEDNESDAY || !of.isLeapYear()) ? 52 : 53;
            }
            return 53;
        }
    }

    /* loaded from: classes5.dex */
    private enum Unit implements TemporalUnit {
        WEEK_BASED_YEARS("WeekBasedYears", Duration.ofSeconds(31556952)),
        QUARTER_YEARS("QuarterYears", Duration.ofSeconds(7889238));
        
        private final Duration duration;
        private final String name;

        Unit(String str, Duration duration) {
            this.name = str;
            this.duration = duration;
        }

        @Override // org.threeten.bp.temporal.TemporalUnit
        public <R extends Temporal> R addTo(R r, long j) {
            int ordinal = ordinal();
            if (ordinal == 0) {
                return (R) r.mo13062with(IsoFields.WEEK_BASED_YEAR, Jdk8Methods.safeAdd(r.get(IsoFields.WEEK_BASED_YEAR), j));
            } else if (ordinal == 1) {
                return (R) r.mo13060plus(j / 256, ChronoUnit.YEARS).mo13060plus((j % 256) * 3, ChronoUnit.MONTHS);
            } else {
                throw new IllegalStateException("Unreachable");
            }
        }

        @Override // org.threeten.bp.temporal.TemporalUnit
        public long between(Temporal temporal, Temporal temporal2) {
            int ordinal = ordinal();
            if (ordinal != 0) {
                if (ordinal == 1) {
                    return temporal.until(temporal2, ChronoUnit.MONTHS) / 3;
                }
                throw new IllegalStateException("Unreachable");
            }
            return Jdk8Methods.safeSubtract(temporal2.getLong(IsoFields.WEEK_BASED_YEAR), temporal.getLong(IsoFields.WEEK_BASED_YEAR));
        }

        @Override // org.threeten.bp.temporal.TemporalUnit
        public Duration getDuration() {
            return this.duration;
        }

        @Override // org.threeten.bp.temporal.TemporalUnit
        public boolean isDateBased() {
            return true;
        }

        @Override // org.threeten.bp.temporal.TemporalUnit
        public boolean isDurationEstimated() {
            return true;
        }

        @Override // org.threeten.bp.temporal.TemporalUnit
        public boolean isSupportedBy(Temporal temporal) {
            return temporal.isSupported(ChronoField.EPOCH_DAY);
        }

        @Override // org.threeten.bp.temporal.TemporalUnit
        public boolean isTimeBased() {
            return false;
        }

        @Override // java.lang.Enum, org.threeten.bp.temporal.TemporalUnit
        public String toString() {
            return this.name;
        }
    }

    private IsoFields() {
        throw new AssertionError("Not instantiable");
    }
}
