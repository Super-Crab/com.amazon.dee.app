package org.threeten.bp.chrono;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Comparator;
import org.apache.logging.log4j.util.ProcessIdUtil;
import org.threeten.bp.DateTimeException;
import org.threeten.bp.LocalDate;
import org.threeten.bp.LocalTime;
import org.threeten.bp.format.DateTimeFormatter;
import org.threeten.bp.jdk8.DefaultInterfaceTemporal;
import org.threeten.bp.jdk8.Jdk8Methods;
import org.threeten.bp.temporal.ChronoField;
import org.threeten.bp.temporal.ChronoUnit;
import org.threeten.bp.temporal.Temporal;
import org.threeten.bp.temporal.TemporalAccessor;
import org.threeten.bp.temporal.TemporalAdjuster;
import org.threeten.bp.temporal.TemporalAmount;
import org.threeten.bp.temporal.TemporalField;
import org.threeten.bp.temporal.TemporalQueries;
import org.threeten.bp.temporal.TemporalQuery;
import org.threeten.bp.temporal.TemporalUnit;
/* loaded from: classes5.dex */
public abstract class ChronoLocalDate extends DefaultInterfaceTemporal implements Temporal, TemporalAdjuster, Comparable<ChronoLocalDate> {
    private static final Comparator<ChronoLocalDate> DATE_COMPARATOR = new Comparator<ChronoLocalDate>() { // from class: org.threeten.bp.chrono.ChronoLocalDate.1
        @Override // java.util.Comparator
        public int compare(ChronoLocalDate chronoLocalDate, ChronoLocalDate chronoLocalDate2) {
            return Jdk8Methods.compareLongs(chronoLocalDate.toEpochDay(), chronoLocalDate2.toEpochDay());
        }
    };

    public static ChronoLocalDate from(TemporalAccessor temporalAccessor) {
        Jdk8Methods.requireNonNull(temporalAccessor, "temporal");
        if (temporalAccessor instanceof ChronoLocalDate) {
            return (ChronoLocalDate) temporalAccessor;
        }
        Chronology chronology = (Chronology) temporalAccessor.query(TemporalQueries.chronology());
        if (chronology != null) {
            return chronology.mo13045date(temporalAccessor);
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("No Chronology found to create ChronoLocalDate: ");
        outline107.append(temporalAccessor.getClass());
        throw new DateTimeException(outline107.toString());
    }

    public static Comparator<ChronoLocalDate> timeLineOrder() {
        return DATE_COMPARATOR;
    }

    public Temporal adjustInto(Temporal temporal) {
        return temporal.mo13062with(ChronoField.EPOCH_DAY, toEpochDay());
    }

    /* renamed from: atTime */
    public ChronoLocalDateTime<?> mo12887atTime(LocalTime localTime) {
        return ChronoLocalDateTimeImpl.of(this, localTime);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ChronoLocalDate) && compareTo((ChronoLocalDate) obj) == 0;
    }

    public String format(DateTimeFormatter dateTimeFormatter) {
        Jdk8Methods.requireNonNull(dateTimeFormatter, "formatter");
        return dateTimeFormatter.format(this);
    }

    /* renamed from: getChronology */
    public abstract Chronology mo13054getChronology();

    /* renamed from: getEra */
    public Era mo13055getEra() {
        return mo13054getChronology().mo13052eraOf(get(ChronoField.ERA));
    }

    public int hashCode() {
        long epochDay = toEpochDay();
        return ((int) (epochDay ^ (epochDay >>> 32))) ^ mo13054getChronology().hashCode();
    }

    public boolean isAfter(ChronoLocalDate chronoLocalDate) {
        return toEpochDay() > chronoLocalDate.toEpochDay();
    }

    public boolean isBefore(ChronoLocalDate chronoLocalDate) {
        return toEpochDay() < chronoLocalDate.toEpochDay();
    }

    public boolean isEqual(ChronoLocalDate chronoLocalDate) {
        return toEpochDay() == chronoLocalDate.toEpochDay();
    }

    public boolean isLeapYear() {
        return mo13054getChronology().isLeapYear(getLong(ChronoField.YEAR));
    }

    @Override // org.threeten.bp.temporal.TemporalAccessor
    public boolean isSupported(TemporalField temporalField) {
        if (temporalField instanceof ChronoField) {
            return temporalField.isDateBased();
        }
        return temporalField != null && temporalField.isSupportedBy(this);
    }

    public abstract int lengthOfMonth();

    public int lengthOfYear() {
        return isLeapYear() ? 366 : 365;
    }

    @Override // org.threeten.bp.temporal.Temporal
    /* renamed from: plus */
    public abstract ChronoLocalDate mo13060plus(long j, TemporalUnit temporalUnit);

    @Override // org.threeten.bp.jdk8.DefaultInterfaceTemporalAccessor, org.threeten.bp.temporal.TemporalAccessor
    public <R> R query(TemporalQuery<R> temporalQuery) {
        if (temporalQuery == TemporalQueries.chronology()) {
            return (R) mo13054getChronology();
        }
        if (temporalQuery == TemporalQueries.precision()) {
            return (R) ChronoUnit.DAYS;
        }
        if (temporalQuery == TemporalQueries.localDate()) {
            return (R) LocalDate.ofEpochDay(toEpochDay());
        }
        if (temporalQuery != TemporalQueries.localTime() && temporalQuery != TemporalQueries.zone() && temporalQuery != TemporalQueries.zoneId() && temporalQuery != TemporalQueries.offset()) {
            return (R) super.query(temporalQuery);
        }
        return null;
    }

    public long toEpochDay() {
        return getLong(ChronoField.EPOCH_DAY);
    }

    public String toString() {
        long j = getLong(ChronoField.YEAR_OF_ERA);
        long j2 = getLong(ChronoField.MONTH_OF_YEAR);
        long j3 = getLong(ChronoField.DAY_OF_MONTH);
        StringBuilder sb = new StringBuilder(30);
        sb.append(mo13054getChronology().toString());
        sb.append(" ");
        sb.append(mo13055getEra());
        sb.append(" ");
        sb.append(j);
        String str = "-0";
        sb.append(j2 < 10 ? str : ProcessIdUtil.DEFAULT_PROCESSID);
        sb.append(j2);
        if (j3 >= 10) {
            str = ProcessIdUtil.DEFAULT_PROCESSID;
        }
        sb.append(str);
        sb.append(j3);
        return sb.toString();
    }

    /* renamed from: until */
    public abstract ChronoPeriod mo12893until(ChronoLocalDate chronoLocalDate);

    @Override // org.threeten.bp.temporal.Temporal
    /* renamed from: with */
    public abstract ChronoLocalDate mo13062with(TemporalField temporalField, long j);

    @Override // java.lang.Comparable
    public int compareTo(ChronoLocalDate chronoLocalDate) {
        int compareLongs = Jdk8Methods.compareLongs(toEpochDay(), chronoLocalDate.toEpochDay());
        return compareLongs == 0 ? mo13054getChronology().compareTo(chronoLocalDate.mo13054getChronology()) : compareLongs;
    }

    @Override // org.threeten.bp.jdk8.DefaultInterfaceTemporal, org.threeten.bp.temporal.Temporal
    /* renamed from: minus */
    public ChronoLocalDate mo13057minus(TemporalAmount temporalAmount) {
        return mo13054getChronology().ensureChronoLocalDate(super.mo13057minus(temporalAmount));
    }

    @Override // org.threeten.bp.jdk8.DefaultInterfaceTemporal, org.threeten.bp.temporal.Temporal
    /* renamed from: plus */
    public ChronoLocalDate mo13059plus(TemporalAmount temporalAmount) {
        return mo13054getChronology().ensureChronoLocalDate(super.mo13059plus(temporalAmount));
    }

    @Override // org.threeten.bp.jdk8.DefaultInterfaceTemporal, org.threeten.bp.temporal.Temporal
    /* renamed from: with */
    public ChronoLocalDate mo13061with(TemporalAdjuster temporalAdjuster) {
        return mo13054getChronology().ensureChronoLocalDate(super.mo13061with(temporalAdjuster));
    }

    @Override // org.threeten.bp.temporal.Temporal
    public boolean isSupported(TemporalUnit temporalUnit) {
        if (temporalUnit instanceof ChronoUnit) {
            return temporalUnit.isDateBased();
        }
        return temporalUnit != null && temporalUnit.isSupportedBy(this);
    }

    @Override // org.threeten.bp.jdk8.DefaultInterfaceTemporal, org.threeten.bp.temporal.Temporal
    /* renamed from: minus */
    public ChronoLocalDate mo13056minus(long j, TemporalUnit temporalUnit) {
        return mo13054getChronology().ensureChronoLocalDate(super.mo13056minus(j, temporalUnit));
    }
}
