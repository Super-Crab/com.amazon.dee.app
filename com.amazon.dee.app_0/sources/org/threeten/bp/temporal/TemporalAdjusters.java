package org.threeten.bp.temporal;

import org.threeten.bp.DayOfWeek;
import org.threeten.bp.jdk8.Jdk8Methods;
/* loaded from: classes5.dex */
public final class TemporalAdjusters {

    /* loaded from: classes5.dex */
    private static final class DayOfWeekInMonth implements TemporalAdjuster {
        private final int dowValue;
        private final int ordinal;

        @Override // org.threeten.bp.temporal.TemporalAdjuster
        public Temporal adjustInto(Temporal temporal) {
            if (this.ordinal >= 0) {
                Temporal mo13062with = temporal.mo13062with(ChronoField.DAY_OF_MONTH, 1L);
                int i = mo13062with.get(ChronoField.DAY_OF_WEEK);
                return mo13062with.mo13060plus((int) (((this.ordinal - 1) * 7) + (((this.dowValue - i) + 7) % 7)), ChronoUnit.DAYS);
            }
            ChronoField chronoField = ChronoField.DAY_OF_MONTH;
            Temporal mo13062with2 = temporal.mo13062with(chronoField, temporal.range(chronoField).getMaximum());
            int i2 = this.dowValue - mo13062with2.get(ChronoField.DAY_OF_WEEK);
            if (i2 == 0) {
                i2 = 0;
            } else if (i2 > 0) {
                i2 -= 7;
            }
            return mo13062with2.mo13060plus((int) (i2 - (((-this.ordinal) - 1) * 7)), ChronoUnit.DAYS);
        }

        private DayOfWeekInMonth(int i, DayOfWeek dayOfWeek) {
            this.ordinal = i;
            this.dowValue = dayOfWeek.getValue();
        }
    }

    /* loaded from: classes5.dex */
    private static class Impl implements TemporalAdjuster {
        private final int ordinal;
        private static final Impl FIRST_DAY_OF_MONTH = new Impl(0);
        private static final Impl LAST_DAY_OF_MONTH = new Impl(1);
        private static final Impl FIRST_DAY_OF_NEXT_MONTH = new Impl(2);
        private static final Impl FIRST_DAY_OF_YEAR = new Impl(3);
        private static final Impl LAST_DAY_OF_YEAR = new Impl(4);
        private static final Impl FIRST_DAY_OF_NEXT_YEAR = new Impl(5);

        private Impl(int i) {
            this.ordinal = i;
        }

        @Override // org.threeten.bp.temporal.TemporalAdjuster
        public Temporal adjustInto(Temporal temporal) {
            int i = this.ordinal;
            if (i != 0) {
                if (i == 1) {
                    ChronoField chronoField = ChronoField.DAY_OF_MONTH;
                    return temporal.mo13062with(chronoField, temporal.range(chronoField).getMaximum());
                } else if (i == 2) {
                    return temporal.mo13062with(ChronoField.DAY_OF_MONTH, 1L).mo13060plus(1L, ChronoUnit.MONTHS);
                } else {
                    if (i == 3) {
                        return temporal.mo13062with(ChronoField.DAY_OF_YEAR, 1L);
                    }
                    if (i == 4) {
                        ChronoField chronoField2 = ChronoField.DAY_OF_YEAR;
                        return temporal.mo13062with(chronoField2, temporal.range(chronoField2).getMaximum());
                    } else if (i == 5) {
                        return temporal.mo13062with(ChronoField.DAY_OF_YEAR, 1L).mo13060plus(1L, ChronoUnit.YEARS);
                    } else {
                        throw new IllegalStateException("Unreachable");
                    }
                }
            }
            return temporal.mo13062with(ChronoField.DAY_OF_MONTH, 1L);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes5.dex */
    public static final class RelativeDayOfWeek implements TemporalAdjuster {
        private final int dowValue;
        private final int relative;

        @Override // org.threeten.bp.temporal.TemporalAdjuster
        public Temporal adjustInto(Temporal temporal) {
            int i = temporal.get(ChronoField.DAY_OF_WEEK);
            if (this.relative >= 2 || i != this.dowValue) {
                if ((this.relative & 1) == 0) {
                    int i2 = i - this.dowValue;
                    return temporal.mo13060plus(i2 >= 0 ? 7 - i2 : -i2, ChronoUnit.DAYS);
                }
                int i3 = this.dowValue - i;
                return temporal.mo13056minus(i3 >= 0 ? 7 - i3 : -i3, ChronoUnit.DAYS);
            }
            return temporal;
        }

        private RelativeDayOfWeek(int i, DayOfWeek dayOfWeek) {
            Jdk8Methods.requireNonNull(dayOfWeek, "dayOfWeek");
            this.relative = i;
            this.dowValue = dayOfWeek.getValue();
        }
    }

    private TemporalAdjusters() {
    }

    public static TemporalAdjuster dayOfWeekInMonth(int i, DayOfWeek dayOfWeek) {
        Jdk8Methods.requireNonNull(dayOfWeek, "dayOfWeek");
        return new DayOfWeekInMonth(i, dayOfWeek);
    }

    public static TemporalAdjuster firstDayOfMonth() {
        return Impl.FIRST_DAY_OF_MONTH;
    }

    public static TemporalAdjuster firstDayOfNextMonth() {
        return Impl.FIRST_DAY_OF_NEXT_MONTH;
    }

    public static TemporalAdjuster firstDayOfNextYear() {
        return Impl.FIRST_DAY_OF_NEXT_YEAR;
    }

    public static TemporalAdjuster firstDayOfYear() {
        return Impl.FIRST_DAY_OF_YEAR;
    }

    public static TemporalAdjuster firstInMonth(DayOfWeek dayOfWeek) {
        Jdk8Methods.requireNonNull(dayOfWeek, "dayOfWeek");
        return new DayOfWeekInMonth(1, dayOfWeek);
    }

    public static TemporalAdjuster lastDayOfMonth() {
        return Impl.LAST_DAY_OF_MONTH;
    }

    public static TemporalAdjuster lastDayOfYear() {
        return Impl.LAST_DAY_OF_YEAR;
    }

    public static TemporalAdjuster lastInMonth(DayOfWeek dayOfWeek) {
        Jdk8Methods.requireNonNull(dayOfWeek, "dayOfWeek");
        return new DayOfWeekInMonth(-1, dayOfWeek);
    }

    public static TemporalAdjuster next(DayOfWeek dayOfWeek) {
        return new RelativeDayOfWeek(2, dayOfWeek);
    }

    public static TemporalAdjuster nextOrSame(DayOfWeek dayOfWeek) {
        return new RelativeDayOfWeek(0, dayOfWeek);
    }

    public static TemporalAdjuster previous(DayOfWeek dayOfWeek) {
        return new RelativeDayOfWeek(3, dayOfWeek);
    }

    public static TemporalAdjuster previousOrSame(DayOfWeek dayOfWeek) {
        return new RelativeDayOfWeek(1, dayOfWeek);
    }
}
