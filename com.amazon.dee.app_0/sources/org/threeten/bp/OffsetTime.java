package org.threeten.bp;

import com.google.android.exoplayer2.C;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectStreamException;
import java.io.Serializable;
import org.threeten.bp.format.DateTimeFormatter;
import org.threeten.bp.jdk8.DefaultInterfaceTemporalAccessor;
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
public final class OffsetTime extends DefaultInterfaceTemporalAccessor implements Temporal, TemporalAdjuster, Comparable<OffsetTime>, Serializable {
    private static final long serialVersionUID = 7264499704384272492L;
    private final ZoneOffset offset;
    private final LocalTime time;
    public static final OffsetTime MIN = LocalTime.MIN.atOffset(ZoneOffset.MAX);
    public static final OffsetTime MAX = LocalTime.MAX.atOffset(ZoneOffset.MIN);
    public static final TemporalQuery<OffsetTime> FROM = new TemporalQuery<OffsetTime>() { // from class: org.threeten.bp.OffsetTime.1
        @Override // org.threeten.bp.temporal.TemporalQuery
        /* renamed from: queryFrom  reason: avoid collision after fix types in other method */
        public OffsetTime mo13072queryFrom(TemporalAccessor temporalAccessor) {
            return OffsetTime.from(temporalAccessor);
        }
    };

    /* renamed from: org.threeten.bp.OffsetTime$2  reason: invalid class name */
    /* loaded from: classes5.dex */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$org$threeten$bp$temporal$ChronoUnit = new int[ChronoUnit.values().length];

        static {
            try {
                $SwitchMap$org$threeten$bp$temporal$ChronoUnit[ChronoUnit.NANOS.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$org$threeten$bp$temporal$ChronoUnit[ChronoUnit.MICROS.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$org$threeten$bp$temporal$ChronoUnit[ChronoUnit.MILLIS.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$org$threeten$bp$temporal$ChronoUnit[ChronoUnit.SECONDS.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$org$threeten$bp$temporal$ChronoUnit[ChronoUnit.MINUTES.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$org$threeten$bp$temporal$ChronoUnit[ChronoUnit.HOURS.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$org$threeten$bp$temporal$ChronoUnit[ChronoUnit.HALF_DAYS.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
        }
    }

    private OffsetTime(LocalTime localTime, ZoneOffset zoneOffset) {
        this.time = (LocalTime) Jdk8Methods.requireNonNull(localTime, "time");
        this.offset = (ZoneOffset) Jdk8Methods.requireNonNull(zoneOffset, "offset");
    }

    public static OffsetTime from(TemporalAccessor temporalAccessor) {
        if (temporalAccessor instanceof OffsetTime) {
            return (OffsetTime) temporalAccessor;
        }
        try {
            return new OffsetTime(LocalTime.from(temporalAccessor), ZoneOffset.from(temporalAccessor));
        } catch (DateTimeException unused) {
            throw new DateTimeException("Unable to obtain OffsetTime from TemporalAccessor: " + temporalAccessor + ", type " + temporalAccessor.getClass().getName());
        }
    }

    public static OffsetTime now() {
        return now(Clock.systemDefaultZone());
    }

    public static OffsetTime of(LocalTime localTime, ZoneOffset zoneOffset) {
        return new OffsetTime(localTime, zoneOffset);
    }

    public static OffsetTime ofInstant(Instant instant, ZoneId zoneId) {
        Jdk8Methods.requireNonNull(instant, "instant");
        Jdk8Methods.requireNonNull(zoneId, "zone");
        ZoneOffset offset = zoneId.getRules().getOffset(instant);
        long epochSecond = ((instant.getEpochSecond() % 86400) + offset.getTotalSeconds()) % 86400;
        if (epochSecond < 0) {
            epochSecond += 86400;
        }
        return new OffsetTime(LocalTime.ofSecondOfDay(epochSecond, instant.getNano()), offset);
    }

    public static OffsetTime parse(CharSequence charSequence) {
        return parse(charSequence, DateTimeFormatter.ISO_OFFSET_TIME);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static OffsetTime readExternal(DataInput dataInput) throws IOException {
        return of(LocalTime.readExternal(dataInput), ZoneOffset.readExternal(dataInput));
    }

    private Object readResolve() throws ObjectStreamException {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }

    private long toEpochNano() {
        return this.time.toNanoOfDay() - (this.offset.getTotalSeconds() * C.NANOS_PER_SECOND);
    }

    private Object writeReplace() {
        return new Ser((byte) 66, this);
    }

    @Override // org.threeten.bp.temporal.TemporalAdjuster
    public Temporal adjustInto(Temporal temporal) {
        return temporal.mo13062with(ChronoField.NANO_OF_DAY, this.time.toNanoOfDay()).mo13062with(ChronoField.OFFSET_SECONDS, getOffset().getTotalSeconds());
    }

    public OffsetDateTime atDate(LocalDate localDate) {
        return OffsetDateTime.of(localDate, this.time, this.offset);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof OffsetTime)) {
            return false;
        }
        OffsetTime offsetTime = (OffsetTime) obj;
        return this.time.equals(offsetTime.time) && this.offset.equals(offsetTime.offset);
    }

    public String format(DateTimeFormatter dateTimeFormatter) {
        Jdk8Methods.requireNonNull(dateTimeFormatter, "formatter");
        return dateTimeFormatter.format(this);
    }

    @Override // org.threeten.bp.jdk8.DefaultInterfaceTemporalAccessor, org.threeten.bp.temporal.TemporalAccessor
    public int get(TemporalField temporalField) {
        return super.get(temporalField);
    }

    public int getHour() {
        return this.time.getHour();
    }

    @Override // org.threeten.bp.temporal.TemporalAccessor
    public long getLong(TemporalField temporalField) {
        if (temporalField instanceof ChronoField) {
            if (temporalField == ChronoField.OFFSET_SECONDS) {
                return getOffset().getTotalSeconds();
            }
            return this.time.getLong(temporalField);
        }
        return temporalField.getFrom(this);
    }

    public int getMinute() {
        return this.time.getMinute();
    }

    public int getNano() {
        return this.time.getNano();
    }

    public ZoneOffset getOffset() {
        return this.offset;
    }

    public int getSecond() {
        return this.time.getSecond();
    }

    public int hashCode() {
        return this.time.hashCode() ^ this.offset.hashCode();
    }

    public boolean isAfter(OffsetTime offsetTime) {
        return toEpochNano() > offsetTime.toEpochNano();
    }

    public boolean isBefore(OffsetTime offsetTime) {
        return toEpochNano() < offsetTime.toEpochNano();
    }

    public boolean isEqual(OffsetTime offsetTime) {
        return toEpochNano() == offsetTime.toEpochNano();
    }

    @Override // org.threeten.bp.temporal.TemporalAccessor
    public boolean isSupported(TemporalField temporalField) {
        return temporalField instanceof ChronoField ? temporalField.isTimeBased() || temporalField == ChronoField.OFFSET_SECONDS : temporalField != null && temporalField.isSupportedBy(this);
    }

    public OffsetTime minusHours(long j) {
        return with(this.time.minusHours(j), this.offset);
    }

    public OffsetTime minusMinutes(long j) {
        return with(this.time.minusMinutes(j), this.offset);
    }

    public OffsetTime minusNanos(long j) {
        return with(this.time.minusNanos(j), this.offset);
    }

    public OffsetTime minusSeconds(long j) {
        return with(this.time.minusSeconds(j), this.offset);
    }

    public OffsetTime plusHours(long j) {
        return with(this.time.plusHours(j), this.offset);
    }

    public OffsetTime plusMinutes(long j) {
        return with(this.time.plusMinutes(j), this.offset);
    }

    public OffsetTime plusNanos(long j) {
        return with(this.time.plusNanos(j), this.offset);
    }

    public OffsetTime plusSeconds(long j) {
        return with(this.time.plusSeconds(j), this.offset);
    }

    @Override // org.threeten.bp.jdk8.DefaultInterfaceTemporalAccessor, org.threeten.bp.temporal.TemporalAccessor
    public <R> R query(TemporalQuery<R> temporalQuery) {
        if (temporalQuery == TemporalQueries.precision()) {
            return (R) ChronoUnit.NANOS;
        }
        if (temporalQuery != TemporalQueries.offset() && temporalQuery != TemporalQueries.zone()) {
            if (temporalQuery == TemporalQueries.localTime()) {
                return (R) this.time;
            }
            if (temporalQuery != TemporalQueries.chronology() && temporalQuery != TemporalQueries.localDate() && temporalQuery != TemporalQueries.zoneId()) {
                return (R) super.query(temporalQuery);
            }
            return null;
        }
        return (R) getOffset();
    }

    @Override // org.threeten.bp.jdk8.DefaultInterfaceTemporalAccessor, org.threeten.bp.temporal.TemporalAccessor
    public ValueRange range(TemporalField temporalField) {
        if (temporalField instanceof ChronoField) {
            if (temporalField == ChronoField.OFFSET_SECONDS) {
                return temporalField.range();
            }
            return this.time.range(temporalField);
        }
        return temporalField.rangeRefinedBy(this);
    }

    public LocalTime toLocalTime() {
        return this.time;
    }

    public String toString() {
        return this.time.toString() + this.offset.toString();
    }

    public OffsetTime truncatedTo(TemporalUnit temporalUnit) {
        return with(this.time.truncatedTo(temporalUnit), this.offset);
    }

    @Override // org.threeten.bp.temporal.Temporal
    public long until(Temporal temporal, TemporalUnit temporalUnit) {
        OffsetTime from = from(temporal);
        if (temporalUnit instanceof ChronoUnit) {
            long epochNano = from.toEpochNano() - toEpochNano();
            switch (((ChronoUnit) temporalUnit).ordinal()) {
                case 0:
                    return epochNano;
                case 1:
                    return epochNano / 1000;
                case 2:
                    return epochNano / 1000000;
                case 3:
                    return epochNano / C.NANOS_PER_SECOND;
                case 4:
                    return epochNano / 60000000000L;
                case 5:
                    return epochNano / 3600000000000L;
                case 6:
                    return epochNano / 43200000000000L;
                default:
                    throw new UnsupportedTemporalTypeException("Unsupported unit: " + temporalUnit);
            }
        }
        return temporalUnit.between(this, from);
    }

    public OffsetTime withHour(int i) {
        return with(this.time.withHour(i), this.offset);
    }

    public OffsetTime withMinute(int i) {
        return with(this.time.withMinute(i), this.offset);
    }

    public OffsetTime withNano(int i) {
        return with(this.time.withNano(i), this.offset);
    }

    public OffsetTime withOffsetSameInstant(ZoneOffset zoneOffset) {
        if (zoneOffset.equals(this.offset)) {
            return this;
        }
        return new OffsetTime(this.time.plusSeconds(zoneOffset.getTotalSeconds() - this.offset.getTotalSeconds()), zoneOffset);
    }

    public OffsetTime withOffsetSameLocal(ZoneOffset zoneOffset) {
        return (zoneOffset == null || !zoneOffset.equals(this.offset)) ? new OffsetTime(this.time, zoneOffset) : this;
    }

    public OffsetTime withSecond(int i) {
        return with(this.time.withSecond(i), this.offset);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void writeExternal(DataOutput dataOutput) throws IOException {
        this.time.writeExternal(dataOutput);
        this.offset.writeExternal(dataOutput);
    }

    public static OffsetTime now(ZoneId zoneId) {
        return now(Clock.system(zoneId));
    }

    public static OffsetTime of(int i, int i2, int i3, int i4, ZoneOffset zoneOffset) {
        return new OffsetTime(LocalTime.of(i, i2, i3, i4), zoneOffset);
    }

    public static OffsetTime parse(CharSequence charSequence, DateTimeFormatter dateTimeFormatter) {
        Jdk8Methods.requireNonNull(dateTimeFormatter, "formatter");
        return (OffsetTime) dateTimeFormatter.parse(charSequence, FROM);
    }

    @Override // java.lang.Comparable
    public int compareTo(OffsetTime offsetTime) {
        if (this.offset.equals(offsetTime.offset)) {
            return this.time.compareTo(offsetTime.time);
        }
        int compareLongs = Jdk8Methods.compareLongs(toEpochNano(), offsetTime.toEpochNano());
        return compareLongs == 0 ? this.time.compareTo(offsetTime.time) : compareLongs;
    }

    public static OffsetTime now(Clock clock) {
        Jdk8Methods.requireNonNull(clock, "clock");
        Instant instant = clock.instant();
        return ofInstant(instant, clock.getZone().getRules().getOffset(instant));
    }

    private OffsetTime with(LocalTime localTime, ZoneOffset zoneOffset) {
        return (this.time != localTime || !this.offset.equals(zoneOffset)) ? new OffsetTime(localTime, zoneOffset) : this;
    }

    @Override // org.threeten.bp.temporal.Temporal
    /* renamed from: minus */
    public OffsetTime mo13057minus(TemporalAmount temporalAmount) {
        return (OffsetTime) temporalAmount.subtractFrom(this);
    }

    @Override // org.threeten.bp.temporal.Temporal
    /* renamed from: plus */
    public OffsetTime mo13059plus(TemporalAmount temporalAmount) {
        return (OffsetTime) temporalAmount.addTo(this);
    }

    @Override // org.threeten.bp.temporal.Temporal
    public boolean isSupported(TemporalUnit temporalUnit) {
        if (temporalUnit instanceof ChronoUnit) {
            return temporalUnit.isTimeBased();
        }
        return temporalUnit != null && temporalUnit.isSupportedBy(this);
    }

    @Override // org.threeten.bp.temporal.Temporal
    /* renamed from: minus */
    public OffsetTime mo13056minus(long j, TemporalUnit temporalUnit) {
        return j == Long.MIN_VALUE ? mo13060plus(Long.MAX_VALUE, temporalUnit).mo13060plus(1L, temporalUnit) : mo13060plus(-j, temporalUnit);
    }

    @Override // org.threeten.bp.temporal.Temporal
    /* renamed from: plus */
    public OffsetTime mo13060plus(long j, TemporalUnit temporalUnit) {
        if (temporalUnit instanceof ChronoUnit) {
            return with(this.time.mo13060plus(j, temporalUnit), this.offset);
        }
        return (OffsetTime) temporalUnit.addTo(this, j);
    }

    @Override // org.threeten.bp.temporal.Temporal
    /* renamed from: with */
    public OffsetTime mo13061with(TemporalAdjuster temporalAdjuster) {
        if (temporalAdjuster instanceof LocalTime) {
            return with((LocalTime) temporalAdjuster, this.offset);
        }
        if (temporalAdjuster instanceof ZoneOffset) {
            return with(this.time, (ZoneOffset) temporalAdjuster);
        }
        if (temporalAdjuster instanceof OffsetTime) {
            return (OffsetTime) temporalAdjuster;
        }
        return (OffsetTime) temporalAdjuster.adjustInto(this);
    }

    @Override // org.threeten.bp.temporal.Temporal
    /* renamed from: with */
    public OffsetTime mo13062with(TemporalField temporalField, long j) {
        if (temporalField instanceof ChronoField) {
            if (temporalField == ChronoField.OFFSET_SECONDS) {
                return with(this.time, ZoneOffset.ofTotalSeconds(((ChronoField) temporalField).checkValidIntValue(j)));
            }
            return with(this.time.mo13062with(temporalField, j), this.offset);
        }
        return (OffsetTime) temporalField.adjustInto(this, j);
    }
}
