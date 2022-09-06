package org.threeten.bp.chrono;

import java.io.Serializable;
import org.threeten.bp.DateTimeException;
import org.threeten.bp.LocalDate;
import org.threeten.bp.LocalTime;
import org.threeten.bp.chrono.ChronoLocalDate;
import org.threeten.bp.jdk8.Jdk8Methods;
import org.threeten.bp.temporal.ChronoUnit;
import org.threeten.bp.temporal.Temporal;
import org.threeten.bp.temporal.TemporalAccessor;
import org.threeten.bp.temporal.TemporalAdjuster;
import org.threeten.bp.temporal.TemporalUnit;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes5.dex */
public abstract class ChronoDateImpl<D extends ChronoLocalDate> extends ChronoLocalDate implements Temporal, TemporalAdjuster, Serializable {
    private static final long serialVersionUID = 6282433883239719096L;

    /* renamed from: org.threeten.bp.chrono.ChronoDateImpl$1  reason: invalid class name */
    /* loaded from: classes5.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$threeten$bp$temporal$ChronoUnit = new int[ChronoUnit.values().length];

        static {
            try {
                $SwitchMap$org$threeten$bp$temporal$ChronoUnit[ChronoUnit.DAYS.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$org$threeten$bp$temporal$ChronoUnit[ChronoUnit.WEEKS.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$org$threeten$bp$temporal$ChronoUnit[ChronoUnit.MONTHS.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$org$threeten$bp$temporal$ChronoUnit[ChronoUnit.YEARS.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$org$threeten$bp$temporal$ChronoUnit[ChronoUnit.DECADES.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$org$threeten$bp$temporal$ChronoUnit[ChronoUnit.CENTURIES.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$org$threeten$bp$temporal$ChronoUnit[ChronoUnit.MILLENNIA.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
        }
    }

    @Override // org.threeten.bp.chrono.ChronoLocalDate
    /* renamed from: atTime */
    public ChronoLocalDateTime<?> mo12887atTime(LocalTime localTime) {
        return ChronoLocalDateTimeImpl.of(this, localTime);
    }

    ChronoDateImpl<D> minusDays(long j) {
        return j == Long.MIN_VALUE ? plusDays(Long.MAX_VALUE).plusDays(1L) : plusDays(-j);
    }

    ChronoDateImpl<D> minusMonths(long j) {
        return j == Long.MIN_VALUE ? plusMonths(Long.MAX_VALUE).plusMonths(1L) : plusMonths(-j);
    }

    ChronoDateImpl<D> minusWeeks(long j) {
        return j == Long.MIN_VALUE ? plusWeeks(Long.MAX_VALUE).plusWeeks(1L) : plusWeeks(-j);
    }

    ChronoDateImpl<D> minusYears(long j) {
        return j == Long.MIN_VALUE ? plusYears(Long.MAX_VALUE).plusYears(1L) : plusYears(-j);
    }

    abstract ChronoDateImpl<D> plusDays(long j);

    abstract ChronoDateImpl<D> plusMonths(long j);

    ChronoDateImpl<D> plusWeeks(long j) {
        return plusDays(Jdk8Methods.safeMultiply(j, 7));
    }

    abstract ChronoDateImpl<D> plusYears(long j);

    @Override // org.threeten.bp.temporal.Temporal
    public long until(Temporal temporal, TemporalUnit temporalUnit) {
        ChronoLocalDate mo13045date = mo13054getChronology().mo13045date(temporal);
        if (temporalUnit instanceof ChronoUnit) {
            return LocalDate.from((TemporalAccessor) this).until(mo13045date, temporalUnit);
        }
        return temporalUnit.between(this, mo13045date);
    }

    @Override // org.threeten.bp.chrono.ChronoLocalDate, org.threeten.bp.temporal.Temporal
    /* renamed from: plus */
    public ChronoDateImpl<D> mo13060plus(long j, TemporalUnit temporalUnit) {
        if (temporalUnit instanceof ChronoUnit) {
            switch (((ChronoUnit) temporalUnit).ordinal()) {
                case 7:
                    return plusDays(j);
                case 8:
                    return plusDays(Jdk8Methods.safeMultiply(j, 7));
                case 9:
                    return plusMonths(j);
                case 10:
                    return plusYears(j);
                case 11:
                    return plusYears(Jdk8Methods.safeMultiply(j, 10));
                case 12:
                    return plusYears(Jdk8Methods.safeMultiply(j, 100));
                case 13:
                    return plusYears(Jdk8Methods.safeMultiply(j, 1000));
                default:
                    throw new DateTimeException(temporalUnit + " not valid for chronology " + mo13054getChronology().getId());
            }
        }
        return (ChronoDateImpl) mo13054getChronology().ensureChronoLocalDate(temporalUnit.addTo(this, j));
    }

    @Override // org.threeten.bp.chrono.ChronoLocalDate
    /* renamed from: until */
    public ChronoPeriod mo12893until(ChronoLocalDate chronoLocalDate) {
        throw new UnsupportedOperationException("Not supported in ThreeTen backport");
    }
}
