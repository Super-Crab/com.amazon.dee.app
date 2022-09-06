package org.threeten.bp.chrono;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Comparator;
import kotlinx.serialization.json.internal.JsonReaderKt;
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
import org.threeten.bp.temporal.UnsupportedTemporalTypeException;
import org.threeten.bp.temporal.ValueRange;
/* loaded from: classes5.dex */
public abstract class ChronoZonedDateTime<D extends ChronoLocalDate> extends DefaultInterfaceTemporal implements Temporal, Comparable<ChronoZonedDateTime<?>> {
    private static Comparator<ChronoZonedDateTime<?>> INSTANT_COMPARATOR = new Comparator<ChronoZonedDateTime<?>>() { // from class: org.threeten.bp.chrono.ChronoZonedDateTime.1
        @Override // java.util.Comparator
        public int compare(ChronoZonedDateTime<?> chronoZonedDateTime, ChronoZonedDateTime<?> chronoZonedDateTime2) {
            int compareLongs = Jdk8Methods.compareLongs(chronoZonedDateTime.toEpochSecond(), chronoZonedDateTime2.toEpochSecond());
            return compareLongs == 0 ? Jdk8Methods.compareLongs(chronoZonedDateTime.toLocalTime().toNanoOfDay(), chronoZonedDateTime2.toLocalTime().toNanoOfDay()) : compareLongs;
        }
    };

    /* renamed from: org.threeten.bp.chrono.ChronoZonedDateTime$2  reason: invalid class name */
    /* loaded from: classes5.dex */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$org$threeten$bp$temporal$ChronoField = new int[ChronoField.values().length];

        static {
            try {
                $SwitchMap$org$threeten$bp$temporal$ChronoField[ChronoField.INSTANT_SECONDS.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$org$threeten$bp$temporal$ChronoField[ChronoField.OFFSET_SECONDS.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    public static ChronoZonedDateTime<?> from(TemporalAccessor temporalAccessor) {
        Jdk8Methods.requireNonNull(temporalAccessor, "temporal");
        if (temporalAccessor instanceof ChronoZonedDateTime) {
            return (ChronoZonedDateTime) temporalAccessor;
        }
        Chronology chronology = (Chronology) temporalAccessor.query(TemporalQueries.chronology());
        if (chronology != null) {
            return chronology.mo13002zonedDateTime(temporalAccessor);
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("No Chronology found to create ChronoZonedDateTime: ");
        outline107.append(temporalAccessor.getClass());
        throw new DateTimeException(outline107.toString());
    }

    public static Comparator<ChronoZonedDateTime<?>> timeLineOrder() {
        return INSTANT_COMPARATOR;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ChronoZonedDateTime) && compareTo((ChronoZonedDateTime) obj) == 0;
    }

    public String format(DateTimeFormatter dateTimeFormatter) {
        Jdk8Methods.requireNonNull(dateTimeFormatter, "formatter");
        return dateTimeFormatter.format(this);
    }

    @Override // org.threeten.bp.jdk8.DefaultInterfaceTemporalAccessor, org.threeten.bp.temporal.TemporalAccessor
    public int get(TemporalField temporalField) {
        if (temporalField instanceof ChronoField) {
            int ordinal = ((ChronoField) temporalField).ordinal();
            if (ordinal == 28) {
                throw new UnsupportedTemporalTypeException(GeneratedOutlineSupport1.outline82("Field too large for an int: ", temporalField));
            }
            if (ordinal != 29) {
                return toLocalDateTime().get(temporalField);
            }
            return getOffset().getTotalSeconds();
        }
        return super.get(temporalField);
    }

    public Chronology getChronology() {
        return mo12942toLocalDate().mo13054getChronology();
    }

    @Override // org.threeten.bp.temporal.TemporalAccessor
    public long getLong(TemporalField temporalField) {
        if (temporalField instanceof ChronoField) {
            int ordinal = ((ChronoField) temporalField).ordinal();
            if (ordinal == 28) {
                return toEpochSecond();
            }
            if (ordinal != 29) {
                return toLocalDateTime().getLong(temporalField);
            }
            return getOffset().getTotalSeconds();
        }
        return temporalField.getFrom(this);
    }

    public abstract ZoneOffset getOffset();

    public abstract ZoneId getZone();

    public int hashCode() {
        return (toLocalDateTime().hashCode() ^ getOffset().hashCode()) ^ Integer.rotateLeft(getZone().hashCode(), 3);
    }

    public boolean isAfter(ChronoZonedDateTime<?> chronoZonedDateTime) {
        int i = (toEpochSecond() > chronoZonedDateTime.toEpochSecond() ? 1 : (toEpochSecond() == chronoZonedDateTime.toEpochSecond() ? 0 : -1));
        return i > 0 || (i == 0 && toLocalTime().getNano() > chronoZonedDateTime.toLocalTime().getNano());
    }

    public boolean isBefore(ChronoZonedDateTime<?> chronoZonedDateTime) {
        int i = (toEpochSecond() > chronoZonedDateTime.toEpochSecond() ? 1 : (toEpochSecond() == chronoZonedDateTime.toEpochSecond() ? 0 : -1));
        return i < 0 || (i == 0 && toLocalTime().getNano() < chronoZonedDateTime.toLocalTime().getNano());
    }

    public boolean isEqual(ChronoZonedDateTime<?> chronoZonedDateTime) {
        return toEpochSecond() == chronoZonedDateTime.toEpochSecond() && toLocalTime().getNano() == chronoZonedDateTime.toLocalTime().getNano();
    }

    @Override // org.threeten.bp.temporal.Temporal
    /* renamed from: plus */
    public abstract ChronoZonedDateTime<D> mo13060plus(long j, TemporalUnit temporalUnit);

    @Override // org.threeten.bp.jdk8.DefaultInterfaceTemporalAccessor, org.threeten.bp.temporal.TemporalAccessor
    public <R> R query(TemporalQuery<R> temporalQuery) {
        if (temporalQuery != TemporalQueries.zoneId() && temporalQuery != TemporalQueries.zone()) {
            if (temporalQuery == TemporalQueries.chronology()) {
                return (R) mo12942toLocalDate().mo13054getChronology();
            }
            if (temporalQuery == TemporalQueries.precision()) {
                return (R) ChronoUnit.NANOS;
            }
            if (temporalQuery == TemporalQueries.offset()) {
                return (R) getOffset();
            }
            if (temporalQuery == TemporalQueries.localDate()) {
                return (R) LocalDate.ofEpochDay(mo12942toLocalDate().toEpochDay());
            }
            if (temporalQuery == TemporalQueries.localTime()) {
                return (R) toLocalTime();
            }
            return (R) super.query(temporalQuery);
        }
        return (R) getZone();
    }

    @Override // org.threeten.bp.jdk8.DefaultInterfaceTemporalAccessor, org.threeten.bp.temporal.TemporalAccessor
    public ValueRange range(TemporalField temporalField) {
        if (temporalField instanceof ChronoField) {
            if (temporalField != ChronoField.INSTANT_SECONDS && temporalField != ChronoField.OFFSET_SECONDS) {
                return toLocalDateTime().range(temporalField);
            }
            return temporalField.range();
        }
        return temporalField.rangeRefinedBy(this);
    }

    public long toEpochSecond() {
        return ((mo12942toLocalDate().toEpochDay() * 86400) + toLocalTime().toSecondOfDay()) - getOffset().getTotalSeconds();
    }

    public Instant toInstant() {
        return Instant.ofEpochSecond(toEpochSecond(), toLocalTime().getNano());
    }

    /* renamed from: toLocalDate */
    public D mo12942toLocalDate() {
        return toLocalDateTime().mo12900toLocalDate();
    }

    public abstract ChronoLocalDateTime<D> toLocalDateTime();

    public LocalTime toLocalTime() {
        return toLocalDateTime().toLocalTime();
    }

    public String toString() {
        String str = toLocalDateTime().toString() + getOffset().toString();
        if (getOffset() != getZone()) {
            StringBuilder outline108 = GeneratedOutlineSupport1.outline108(str, JsonReaderKt.BEGIN_LIST);
            outline108.append(getZone().toString());
            outline108.append(JsonReaderKt.END_LIST);
            return outline108.toString();
        }
        return str;
    }

    @Override // org.threeten.bp.temporal.Temporal
    /* renamed from: with */
    public abstract ChronoZonedDateTime<D> mo13062with(TemporalField temporalField, long j);

    public abstract ChronoZonedDateTime<D> withEarlierOffsetAtOverlap();

    public abstract ChronoZonedDateTime<D> withLaterOffsetAtOverlap();

    public abstract ChronoZonedDateTime<D> withZoneSameInstant(ZoneId zoneId);

    public abstract ChronoZonedDateTime<D> withZoneSameLocal(ZoneId zoneId);

    /* JADX WARN: Type inference failed for: r5v1, types: [org.threeten.bp.chrono.ChronoLocalDate] */
    @Override // java.lang.Comparable
    public int compareTo(ChronoZonedDateTime<?> chronoZonedDateTime) {
        int compareLongs = Jdk8Methods.compareLongs(toEpochSecond(), chronoZonedDateTime.toEpochSecond());
        if (compareLongs == 0) {
            int nano = toLocalTime().getNano() - chronoZonedDateTime.toLocalTime().getNano();
            if (nano != 0) {
                return nano;
            }
            int compareTo = toLocalDateTime().compareTo(chronoZonedDateTime.toLocalDateTime());
            if (compareTo != 0) {
                return compareTo;
            }
            int compareTo2 = getZone().getId().compareTo(chronoZonedDateTime.getZone().getId());
            return compareTo2 == 0 ? mo12942toLocalDate().mo13054getChronology().compareTo(chronoZonedDateTime.mo12942toLocalDate().mo13054getChronology()) : compareTo2;
        }
        return compareLongs;
    }

    @Override // org.threeten.bp.jdk8.DefaultInterfaceTemporal, org.threeten.bp.temporal.Temporal
    /* renamed from: minus */
    public ChronoZonedDateTime<D> mo13057minus(TemporalAmount temporalAmount) {
        return mo12942toLocalDate().mo13054getChronology().ensureChronoZonedDateTime(super.mo13057minus(temporalAmount));
    }

    @Override // org.threeten.bp.jdk8.DefaultInterfaceTemporal, org.threeten.bp.temporal.Temporal
    /* renamed from: plus */
    public ChronoZonedDateTime<D> mo13059plus(TemporalAmount temporalAmount) {
        return mo12942toLocalDate().mo13054getChronology().ensureChronoZonedDateTime(super.mo13059plus(temporalAmount));
    }

    @Override // org.threeten.bp.jdk8.DefaultInterfaceTemporal, org.threeten.bp.temporal.Temporal
    /* renamed from: with */
    public ChronoZonedDateTime<D> mo13061with(TemporalAdjuster temporalAdjuster) {
        return mo12942toLocalDate().mo13054getChronology().ensureChronoZonedDateTime(super.mo13061with(temporalAdjuster));
    }

    @Override // org.threeten.bp.jdk8.DefaultInterfaceTemporal, org.threeten.bp.temporal.Temporal
    /* renamed from: minus */
    public ChronoZonedDateTime<D> mo13056minus(long j, TemporalUnit temporalUnit) {
        return mo12942toLocalDate().mo13054getChronology().ensureChronoZonedDateTime(super.mo13056minus(j, temporalUnit));
    }
}
