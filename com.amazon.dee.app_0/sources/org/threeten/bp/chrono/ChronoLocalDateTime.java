package org.threeten.bp.chrono;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Comparator;
import org.threeten.bp.DateTimeException;
import org.threeten.bp.Instant;
import org.threeten.bp.LocalDate;
import org.threeten.bp.LocalTime;
import org.threeten.bp.ZoneId;
import org.threeten.bp.ZoneOffset;
import org.threeten.bp.chrono.ChronoLocalDate;
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
public abstract class ChronoLocalDateTime<D extends ChronoLocalDate> extends DefaultInterfaceTemporal implements Temporal, TemporalAdjuster, Comparable<ChronoLocalDateTime<?>> {
    private static final Comparator<ChronoLocalDateTime<?>> DATE_TIME_COMPARATOR = new Comparator<ChronoLocalDateTime<?>>() { // from class: org.threeten.bp.chrono.ChronoLocalDateTime.1
        /* JADX WARN: Type inference failed for: r0v0, types: [org.threeten.bp.chrono.ChronoLocalDate] */
        /* JADX WARN: Type inference failed for: r2v0, types: [org.threeten.bp.chrono.ChronoLocalDate] */
        @Override // java.util.Comparator
        public int compare(ChronoLocalDateTime<?> chronoLocalDateTime, ChronoLocalDateTime<?> chronoLocalDateTime2) {
            int compareLongs = Jdk8Methods.compareLongs(chronoLocalDateTime.mo12900toLocalDate().toEpochDay(), chronoLocalDateTime2.mo12900toLocalDate().toEpochDay());
            return compareLongs == 0 ? Jdk8Methods.compareLongs(chronoLocalDateTime.toLocalTime().toNanoOfDay(), chronoLocalDateTime2.toLocalTime().toNanoOfDay()) : compareLongs;
        }
    };

    public static ChronoLocalDateTime<?> from(TemporalAccessor temporalAccessor) {
        Jdk8Methods.requireNonNull(temporalAccessor, "temporal");
        if (temporalAccessor instanceof ChronoLocalDateTime) {
            return (ChronoLocalDateTime) temporalAccessor;
        }
        Chronology chronology = (Chronology) temporalAccessor.query(TemporalQueries.chronology());
        if (chronology != null) {
            return chronology.mo12999localDateTime(temporalAccessor);
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("No Chronology found to create ChronoLocalDateTime: ");
        outline107.append(temporalAccessor.getClass());
        throw new DateTimeException(outline107.toString());
    }

    public static Comparator<ChronoLocalDateTime<?>> timeLineOrder() {
        return DATE_TIME_COMPARATOR;
    }

    public Temporal adjustInto(Temporal temporal) {
        return temporal.mo13062with(ChronoField.EPOCH_DAY, mo12900toLocalDate().toEpochDay()).mo13062with(ChronoField.NANO_OF_DAY, toLocalTime().toNanoOfDay());
    }

    public abstract ChronoZonedDateTime<D> atZone(ZoneId zoneId);

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ChronoLocalDateTime) && compareTo((ChronoLocalDateTime) obj) == 0;
    }

    public String format(DateTimeFormatter dateTimeFormatter) {
        Jdk8Methods.requireNonNull(dateTimeFormatter, "formatter");
        return dateTimeFormatter.format(this);
    }

    public Chronology getChronology() {
        return mo12900toLocalDate().mo13054getChronology();
    }

    public int hashCode() {
        return mo12900toLocalDate().hashCode() ^ toLocalTime().hashCode();
    }

    /* JADX WARN: Type inference failed for: r2v0, types: [org.threeten.bp.chrono.ChronoLocalDate] */
    public boolean isAfter(ChronoLocalDateTime<?> chronoLocalDateTime) {
        int i = (mo12900toLocalDate().toEpochDay() > chronoLocalDateTime.mo12900toLocalDate().toEpochDay() ? 1 : (mo12900toLocalDate().toEpochDay() == chronoLocalDateTime.mo12900toLocalDate().toEpochDay() ? 0 : -1));
        return i > 0 || (i == 0 && toLocalTime().toNanoOfDay() > chronoLocalDateTime.toLocalTime().toNanoOfDay());
    }

    /* JADX WARN: Type inference failed for: r2v0, types: [org.threeten.bp.chrono.ChronoLocalDate] */
    public boolean isBefore(ChronoLocalDateTime<?> chronoLocalDateTime) {
        int i = (mo12900toLocalDate().toEpochDay() > chronoLocalDateTime.mo12900toLocalDate().toEpochDay() ? 1 : (mo12900toLocalDate().toEpochDay() == chronoLocalDateTime.mo12900toLocalDate().toEpochDay() ? 0 : -1));
        return i < 0 || (i == 0 && toLocalTime().toNanoOfDay() < chronoLocalDateTime.toLocalTime().toNanoOfDay());
    }

    /* JADX WARN: Type inference failed for: r5v2, types: [org.threeten.bp.chrono.ChronoLocalDate] */
    public boolean isEqual(ChronoLocalDateTime<?> chronoLocalDateTime) {
        return toLocalTime().toNanoOfDay() == chronoLocalDateTime.toLocalTime().toNanoOfDay() && mo12900toLocalDate().toEpochDay() == chronoLocalDateTime.mo12900toLocalDate().toEpochDay();
    }

    @Override // org.threeten.bp.temporal.Temporal
    /* renamed from: plus */
    public abstract ChronoLocalDateTime<D> mo13060plus(long j, TemporalUnit temporalUnit);

    @Override // org.threeten.bp.jdk8.DefaultInterfaceTemporalAccessor, org.threeten.bp.temporal.TemporalAccessor
    public <R> R query(TemporalQuery<R> temporalQuery) {
        if (temporalQuery == TemporalQueries.chronology()) {
            return (R) getChronology();
        }
        if (temporalQuery == TemporalQueries.precision()) {
            return (R) ChronoUnit.NANOS;
        }
        if (temporalQuery == TemporalQueries.localDate()) {
            return (R) LocalDate.ofEpochDay(mo12900toLocalDate().toEpochDay());
        }
        if (temporalQuery == TemporalQueries.localTime()) {
            return (R) toLocalTime();
        }
        if (temporalQuery != TemporalQueries.zone() && temporalQuery != TemporalQueries.zoneId() && temporalQuery != TemporalQueries.offset()) {
            return (R) super.query(temporalQuery);
        }
        return null;
    }

    public long toEpochSecond(ZoneOffset zoneOffset) {
        Jdk8Methods.requireNonNull(zoneOffset, "offset");
        return ((mo12900toLocalDate().toEpochDay() * 86400) + toLocalTime().toSecondOfDay()) - zoneOffset.getTotalSeconds();
    }

    public Instant toInstant(ZoneOffset zoneOffset) {
        return Instant.ofEpochSecond(toEpochSecond(zoneOffset), toLocalTime().getNano());
    }

    /* renamed from: toLocalDate */
    public abstract D mo12900toLocalDate();

    public abstract LocalTime toLocalTime();

    public String toString() {
        return mo12900toLocalDate().toString() + 'T' + toLocalTime().toString();
    }

    @Override // org.threeten.bp.temporal.Temporal
    /* renamed from: with */
    public abstract ChronoLocalDateTime<D> mo13062with(TemporalField temporalField, long j);

    @Override // java.lang.Comparable
    public int compareTo(ChronoLocalDateTime<?> chronoLocalDateTime) {
        int compareTo = mo12900toLocalDate().compareTo(chronoLocalDateTime.mo12900toLocalDate());
        if (compareTo == 0) {
            int compareTo2 = toLocalTime().compareTo(chronoLocalDateTime.toLocalTime());
            return compareTo2 == 0 ? getChronology().compareTo(chronoLocalDateTime.getChronology()) : compareTo2;
        }
        return compareTo;
    }

    @Override // org.threeten.bp.jdk8.DefaultInterfaceTemporal, org.threeten.bp.temporal.Temporal
    /* renamed from: minus */
    public ChronoLocalDateTime<D> mo13057minus(TemporalAmount temporalAmount) {
        return mo12900toLocalDate().mo13054getChronology().ensureChronoLocalDateTime(super.mo13057minus(temporalAmount));
    }

    @Override // org.threeten.bp.jdk8.DefaultInterfaceTemporal, org.threeten.bp.temporal.Temporal
    /* renamed from: plus */
    public ChronoLocalDateTime<D> mo13059plus(TemporalAmount temporalAmount) {
        return mo12900toLocalDate().mo13054getChronology().ensureChronoLocalDateTime(super.mo13059plus(temporalAmount));
    }

    @Override // org.threeten.bp.jdk8.DefaultInterfaceTemporal, org.threeten.bp.temporal.Temporal
    /* renamed from: with */
    public ChronoLocalDateTime<D> mo13061with(TemporalAdjuster temporalAdjuster) {
        return mo12900toLocalDate().mo13054getChronology().ensureChronoLocalDateTime(super.mo13061with(temporalAdjuster));
    }

    @Override // org.threeten.bp.jdk8.DefaultInterfaceTemporal, org.threeten.bp.temporal.Temporal
    /* renamed from: minus */
    public ChronoLocalDateTime<D> mo13056minus(long j, TemporalUnit temporalUnit) {
        return mo12900toLocalDate().mo13054getChronology().ensureChronoLocalDateTime(super.mo13056minus(j, temporalUnit));
    }
}
