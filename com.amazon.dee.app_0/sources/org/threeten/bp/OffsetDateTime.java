package org.threeten.bp;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectStreamException;
import java.io.Serializable;
import java.util.Comparator;
import org.threeten.bp.chrono.ChronoLocalDateTime;
import org.threeten.bp.chrono.IsoChronology;
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
import org.threeten.bp.temporal.ValueRange;
/* loaded from: classes5.dex */
public final class OffsetDateTime extends DefaultInterfaceTemporal implements Temporal, TemporalAdjuster, Comparable<OffsetDateTime>, Serializable {
    private static final long serialVersionUID = 2287754244819255394L;
    private final LocalDateTime dateTime;
    private final ZoneOffset offset;
    public static final OffsetDateTime MIN = LocalDateTime.MIN.atOffset(ZoneOffset.MAX);
    public static final OffsetDateTime MAX = LocalDateTime.MAX.atOffset(ZoneOffset.MIN);
    public static final TemporalQuery<OffsetDateTime> FROM = new TemporalQuery<OffsetDateTime>() { // from class: org.threeten.bp.OffsetDateTime.1
        @Override // org.threeten.bp.temporal.TemporalQuery
        /* renamed from: queryFrom  reason: avoid collision after fix types in other method */
        public OffsetDateTime mo13072queryFrom(TemporalAccessor temporalAccessor) {
            return OffsetDateTime.from(temporalAccessor);
        }
    };
    private static final Comparator<OffsetDateTime> INSTANT_COMPARATOR = new Comparator<OffsetDateTime>() { // from class: org.threeten.bp.OffsetDateTime.2
        @Override // java.util.Comparator
        public int compare(OffsetDateTime offsetDateTime, OffsetDateTime offsetDateTime2) {
            int compareLongs = Jdk8Methods.compareLongs(offsetDateTime.toEpochSecond(), offsetDateTime2.toEpochSecond());
            return compareLongs == 0 ? Jdk8Methods.compareLongs(offsetDateTime.getNano(), offsetDateTime2.getNano()) : compareLongs;
        }
    };

    /* renamed from: org.threeten.bp.OffsetDateTime$3  reason: invalid class name */
    /* loaded from: classes5.dex */
    static /* synthetic */ class AnonymousClass3 {
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

    private OffsetDateTime(LocalDateTime localDateTime, ZoneOffset zoneOffset) {
        this.dateTime = (LocalDateTime) Jdk8Methods.requireNonNull(localDateTime, "dateTime");
        this.offset = (ZoneOffset) Jdk8Methods.requireNonNull(zoneOffset, "offset");
    }

    public static OffsetDateTime from(TemporalAccessor temporalAccessor) {
        if (temporalAccessor instanceof OffsetDateTime) {
            return (OffsetDateTime) temporalAccessor;
        }
        try {
            ZoneOffset from = ZoneOffset.from(temporalAccessor);
            try {
                return of(LocalDateTime.from(temporalAccessor), from);
            } catch (DateTimeException unused) {
                return ofInstant(Instant.from(temporalAccessor), from);
            }
        } catch (DateTimeException unused2) {
            throw new DateTimeException("Unable to obtain OffsetDateTime from TemporalAccessor: " + temporalAccessor + ", type " + temporalAccessor.getClass().getName());
        }
    }

    public static OffsetDateTime now() {
        return now(Clock.systemDefaultZone());
    }

    public static OffsetDateTime of(LocalDate localDate, LocalTime localTime, ZoneOffset zoneOffset) {
        return new OffsetDateTime(LocalDateTime.of(localDate, localTime), zoneOffset);
    }

    public static OffsetDateTime ofInstant(Instant instant, ZoneId zoneId) {
        Jdk8Methods.requireNonNull(instant, "instant");
        Jdk8Methods.requireNonNull(zoneId, "zone");
        ZoneOffset offset = zoneId.getRules().getOffset(instant);
        return new OffsetDateTime(LocalDateTime.ofEpochSecond(instant.getEpochSecond(), instant.getNano(), offset), offset);
    }

    public static OffsetDateTime parse(CharSequence charSequence) {
        return parse(charSequence, DateTimeFormatter.ISO_OFFSET_DATE_TIME);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static OffsetDateTime readExternal(DataInput dataInput) throws IOException {
        return of(LocalDateTime.readExternal(dataInput), ZoneOffset.readExternal(dataInput));
    }

    private Object readResolve() throws ObjectStreamException {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }

    public static Comparator<OffsetDateTime> timeLineOrder() {
        return INSTANT_COMPARATOR;
    }

    private Object writeReplace() {
        return new Ser((byte) 69, this);
    }

    @Override // org.threeten.bp.temporal.TemporalAdjuster
    public Temporal adjustInto(Temporal temporal) {
        return temporal.mo13062with(ChronoField.EPOCH_DAY, toLocalDate().toEpochDay()).mo13062with(ChronoField.NANO_OF_DAY, toLocalTime().toNanoOfDay()).mo13062with(ChronoField.OFFSET_SECONDS, getOffset().getTotalSeconds());
    }

    public ZonedDateTime atZoneSameInstant(ZoneId zoneId) {
        return ZonedDateTime.ofInstant(this.dateTime, this.offset, zoneId);
    }

    public ZonedDateTime atZoneSimilarLocal(ZoneId zoneId) {
        return ZonedDateTime.ofLocal(this.dateTime, zoneId, this.offset);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof OffsetDateTime)) {
            return false;
        }
        OffsetDateTime offsetDateTime = (OffsetDateTime) obj;
        return this.dateTime.equals(offsetDateTime.dateTime) && this.offset.equals(offsetDateTime.offset);
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
                throw new DateTimeException(GeneratedOutlineSupport1.outline82("Field too large for an int: ", temporalField));
            }
            if (ordinal != 29) {
                return this.dateTime.get(temporalField);
            }
            return getOffset().getTotalSeconds();
        }
        return super.get(temporalField);
    }

    public int getDayOfMonth() {
        return this.dateTime.getDayOfMonth();
    }

    public DayOfWeek getDayOfWeek() {
        return this.dateTime.getDayOfWeek();
    }

    public int getDayOfYear() {
        return this.dateTime.getDayOfYear();
    }

    public int getHour() {
        return this.dateTime.getHour();
    }

    @Override // org.threeten.bp.temporal.TemporalAccessor
    public long getLong(TemporalField temporalField) {
        if (temporalField instanceof ChronoField) {
            int ordinal = ((ChronoField) temporalField).ordinal();
            if (ordinal == 28) {
                return toEpochSecond();
            }
            if (ordinal != 29) {
                return this.dateTime.getLong(temporalField);
            }
            return getOffset().getTotalSeconds();
        }
        return temporalField.getFrom(this);
    }

    public int getMinute() {
        return this.dateTime.getMinute();
    }

    public Month getMonth() {
        return this.dateTime.getMonth();
    }

    public int getMonthValue() {
        return this.dateTime.getMonthValue();
    }

    public int getNano() {
        return this.dateTime.getNano();
    }

    public ZoneOffset getOffset() {
        return this.offset;
    }

    public int getSecond() {
        return this.dateTime.getSecond();
    }

    public int getYear() {
        return this.dateTime.getYear();
    }

    public int hashCode() {
        return this.dateTime.hashCode() ^ this.offset.hashCode();
    }

    public boolean isAfter(OffsetDateTime offsetDateTime) {
        int i = (toEpochSecond() > offsetDateTime.toEpochSecond() ? 1 : (toEpochSecond() == offsetDateTime.toEpochSecond() ? 0 : -1));
        return i > 0 || (i == 0 && toLocalTime().getNano() > offsetDateTime.toLocalTime().getNano());
    }

    public boolean isBefore(OffsetDateTime offsetDateTime) {
        int i = (toEpochSecond() > offsetDateTime.toEpochSecond() ? 1 : (toEpochSecond() == offsetDateTime.toEpochSecond() ? 0 : -1));
        return i < 0 || (i == 0 && toLocalTime().getNano() < offsetDateTime.toLocalTime().getNano());
    }

    public boolean isEqual(OffsetDateTime offsetDateTime) {
        return toEpochSecond() == offsetDateTime.toEpochSecond() && toLocalTime().getNano() == offsetDateTime.toLocalTime().getNano();
    }

    @Override // org.threeten.bp.temporal.TemporalAccessor
    public boolean isSupported(TemporalField temporalField) {
        return (temporalField instanceof ChronoField) || (temporalField != null && temporalField.isSupportedBy(this));
    }

    public OffsetDateTime minusDays(long j) {
        return j == Long.MIN_VALUE ? plusDays(Long.MAX_VALUE).plusDays(1L) : plusDays(-j);
    }

    public OffsetDateTime minusHours(long j) {
        return j == Long.MIN_VALUE ? plusHours(Long.MAX_VALUE).plusHours(1L) : plusHours(-j);
    }

    public OffsetDateTime minusMinutes(long j) {
        return j == Long.MIN_VALUE ? plusMinutes(Long.MAX_VALUE).plusMinutes(1L) : plusMinutes(-j);
    }

    public OffsetDateTime minusMonths(long j) {
        return j == Long.MIN_VALUE ? plusMonths(Long.MAX_VALUE).plusMonths(1L) : plusMonths(-j);
    }

    public OffsetDateTime minusNanos(long j) {
        return j == Long.MIN_VALUE ? plusNanos(Long.MAX_VALUE).plusNanos(1L) : plusNanos(-j);
    }

    public OffsetDateTime minusSeconds(long j) {
        return j == Long.MIN_VALUE ? plusSeconds(Long.MAX_VALUE).plusSeconds(1L) : plusSeconds(-j);
    }

    public OffsetDateTime minusWeeks(long j) {
        return j == Long.MIN_VALUE ? plusWeeks(Long.MAX_VALUE).plusWeeks(1L) : plusWeeks(-j);
    }

    public OffsetDateTime minusYears(long j) {
        return j == Long.MIN_VALUE ? plusYears(Long.MAX_VALUE).plusYears(1L) : plusYears(-j);
    }

    public OffsetDateTime plusDays(long j) {
        return with(this.dateTime.plusDays(j), this.offset);
    }

    public OffsetDateTime plusHours(long j) {
        return with(this.dateTime.plusHours(j), this.offset);
    }

    public OffsetDateTime plusMinutes(long j) {
        return with(this.dateTime.plusMinutes(j), this.offset);
    }

    public OffsetDateTime plusMonths(long j) {
        return with(this.dateTime.plusMonths(j), this.offset);
    }

    public OffsetDateTime plusNanos(long j) {
        return with(this.dateTime.plusNanos(j), this.offset);
    }

    public OffsetDateTime plusSeconds(long j) {
        return with(this.dateTime.plusSeconds(j), this.offset);
    }

    public OffsetDateTime plusWeeks(long j) {
        return with(this.dateTime.plusWeeks(j), this.offset);
    }

    public OffsetDateTime plusYears(long j) {
        return with(this.dateTime.plusYears(j), this.offset);
    }

    @Override // org.threeten.bp.jdk8.DefaultInterfaceTemporalAccessor, org.threeten.bp.temporal.TemporalAccessor
    public <R> R query(TemporalQuery<R> temporalQuery) {
        if (temporalQuery == TemporalQueries.chronology()) {
            return (R) IsoChronology.INSTANCE;
        }
        if (temporalQuery == TemporalQueries.precision()) {
            return (R) ChronoUnit.NANOS;
        }
        if (temporalQuery != TemporalQueries.offset() && temporalQuery != TemporalQueries.zone()) {
            if (temporalQuery == TemporalQueries.localDate()) {
                return (R) toLocalDate();
            }
            if (temporalQuery == TemporalQueries.localTime()) {
                return (R) toLocalTime();
            }
            if (temporalQuery != TemporalQueries.zoneId()) {
                return (R) super.query(temporalQuery);
            }
            return null;
        }
        return (R) getOffset();
    }

    @Override // org.threeten.bp.jdk8.DefaultInterfaceTemporalAccessor, org.threeten.bp.temporal.TemporalAccessor
    public ValueRange range(TemporalField temporalField) {
        if (temporalField instanceof ChronoField) {
            if (temporalField != ChronoField.INSTANT_SECONDS && temporalField != ChronoField.OFFSET_SECONDS) {
                return this.dateTime.range(temporalField);
            }
            return temporalField.range();
        }
        return temporalField.rangeRefinedBy(this);
    }

    public long toEpochSecond() {
        return this.dateTime.toEpochSecond(this.offset);
    }

    public Instant toInstant() {
        return this.dateTime.toInstant(this.offset);
    }

    public LocalDate toLocalDate() {
        return this.dateTime.mo12900toLocalDate();
    }

    public LocalDateTime toLocalDateTime() {
        return this.dateTime;
    }

    public LocalTime toLocalTime() {
        return this.dateTime.toLocalTime();
    }

    public OffsetTime toOffsetTime() {
        return OffsetTime.of(this.dateTime.toLocalTime(), this.offset);
    }

    public String toString() {
        return this.dateTime.toString() + this.offset.toString();
    }

    public ZonedDateTime toZonedDateTime() {
        return ZonedDateTime.of(this.dateTime, this.offset);
    }

    public OffsetDateTime truncatedTo(TemporalUnit temporalUnit) {
        return with(this.dateTime.truncatedTo(temporalUnit), this.offset);
    }

    @Override // org.threeten.bp.temporal.Temporal
    public long until(Temporal temporal, TemporalUnit temporalUnit) {
        OffsetDateTime from = from(temporal);
        if (temporalUnit instanceof ChronoUnit) {
            return this.dateTime.until(from.withOffsetSameInstant(this.offset).dateTime, temporalUnit);
        }
        return temporalUnit.between(this, from);
    }

    public OffsetDateTime withDayOfMonth(int i) {
        return with(this.dateTime.withDayOfMonth(i), this.offset);
    }

    public OffsetDateTime withDayOfYear(int i) {
        return with(this.dateTime.withDayOfYear(i), this.offset);
    }

    public OffsetDateTime withHour(int i) {
        return with(this.dateTime.withHour(i), this.offset);
    }

    public OffsetDateTime withMinute(int i) {
        return with(this.dateTime.withMinute(i), this.offset);
    }

    public OffsetDateTime withMonth(int i) {
        return with(this.dateTime.withMonth(i), this.offset);
    }

    public OffsetDateTime withNano(int i) {
        return with(this.dateTime.withNano(i), this.offset);
    }

    public OffsetDateTime withOffsetSameInstant(ZoneOffset zoneOffset) {
        if (zoneOffset.equals(this.offset)) {
            return this;
        }
        return new OffsetDateTime(this.dateTime.plusSeconds(zoneOffset.getTotalSeconds() - this.offset.getTotalSeconds()), zoneOffset);
    }

    public OffsetDateTime withOffsetSameLocal(ZoneOffset zoneOffset) {
        return with(this.dateTime, zoneOffset);
    }

    public OffsetDateTime withSecond(int i) {
        return with(this.dateTime.withSecond(i), this.offset);
    }

    public OffsetDateTime withYear(int i) {
        return with(this.dateTime.withYear(i), this.offset);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void writeExternal(DataOutput dataOutput) throws IOException {
        this.dateTime.writeExternal(dataOutput);
        this.offset.writeExternal(dataOutput);
    }

    public static OffsetDateTime now(ZoneId zoneId) {
        return now(Clock.system(zoneId));
    }

    public static OffsetDateTime parse(CharSequence charSequence, DateTimeFormatter dateTimeFormatter) {
        Jdk8Methods.requireNonNull(dateTimeFormatter, "formatter");
        return (OffsetDateTime) dateTimeFormatter.parse(charSequence, FROM);
    }

    @Override // java.lang.Comparable
    public int compareTo(OffsetDateTime offsetDateTime) {
        if (getOffset().equals(offsetDateTime.getOffset())) {
            return toLocalDateTime().compareTo((ChronoLocalDateTime<?>) offsetDateTime.toLocalDateTime());
        }
        int compareLongs = Jdk8Methods.compareLongs(toEpochSecond(), offsetDateTime.toEpochSecond());
        if (compareLongs != 0) {
            return compareLongs;
        }
        int nano = toLocalTime().getNano() - offsetDateTime.toLocalTime().getNano();
        return nano == 0 ? toLocalDateTime().compareTo((ChronoLocalDateTime<?>) offsetDateTime.toLocalDateTime()) : nano;
    }

    @Override // org.threeten.bp.temporal.Temporal
    public boolean isSupported(TemporalUnit temporalUnit) {
        return temporalUnit instanceof ChronoUnit ? temporalUnit.isDateBased() || temporalUnit.isTimeBased() : temporalUnit != null && temporalUnit.isSupportedBy(this);
    }

    public static OffsetDateTime now(Clock clock) {
        Jdk8Methods.requireNonNull(clock, "clock");
        Instant instant = clock.instant();
        return ofInstant(instant, clock.getZone().getRules().getOffset(instant));
    }

    public static OffsetDateTime of(LocalDateTime localDateTime, ZoneOffset zoneOffset) {
        return new OffsetDateTime(localDateTime, zoneOffset);
    }

    private OffsetDateTime with(LocalDateTime localDateTime, ZoneOffset zoneOffset) {
        return (this.dateTime != localDateTime || !this.offset.equals(zoneOffset)) ? new OffsetDateTime(localDateTime, zoneOffset) : this;
    }

    @Override // org.threeten.bp.jdk8.DefaultInterfaceTemporal, org.threeten.bp.temporal.Temporal
    /* renamed from: minus */
    public OffsetDateTime mo13057minus(TemporalAmount temporalAmount) {
        return (OffsetDateTime) temporalAmount.subtractFrom(this);
    }

    @Override // org.threeten.bp.jdk8.DefaultInterfaceTemporal, org.threeten.bp.temporal.Temporal
    /* renamed from: plus */
    public OffsetDateTime mo13059plus(TemporalAmount temporalAmount) {
        return (OffsetDateTime) temporalAmount.addTo(this);
    }

    public static OffsetDateTime of(int i, int i2, int i3, int i4, int i5, int i6, int i7, ZoneOffset zoneOffset) {
        return new OffsetDateTime(LocalDateTime.of(i, i2, i3, i4, i5, i6, i7), zoneOffset);
    }

    @Override // org.threeten.bp.jdk8.DefaultInterfaceTemporal, org.threeten.bp.temporal.Temporal
    /* renamed from: minus */
    public OffsetDateTime mo13056minus(long j, TemporalUnit temporalUnit) {
        return j == Long.MIN_VALUE ? mo13060plus(Long.MAX_VALUE, temporalUnit).mo13060plus(1L, temporalUnit) : mo13060plus(-j, temporalUnit);
    }

    @Override // org.threeten.bp.temporal.Temporal
    /* renamed from: plus */
    public OffsetDateTime mo13060plus(long j, TemporalUnit temporalUnit) {
        if (temporalUnit instanceof ChronoUnit) {
            return with(this.dateTime.mo13060plus(j, temporalUnit), this.offset);
        }
        return (OffsetDateTime) temporalUnit.addTo(this, j);
    }

    @Override // org.threeten.bp.jdk8.DefaultInterfaceTemporal, org.threeten.bp.temporal.Temporal
    /* renamed from: with */
    public OffsetDateTime mo13061with(TemporalAdjuster temporalAdjuster) {
        if (!(temporalAdjuster instanceof LocalDate) && !(temporalAdjuster instanceof LocalTime) && !(temporalAdjuster instanceof LocalDateTime)) {
            if (temporalAdjuster instanceof Instant) {
                return ofInstant((Instant) temporalAdjuster, this.offset);
            }
            if (temporalAdjuster instanceof ZoneOffset) {
                return with(this.dateTime, (ZoneOffset) temporalAdjuster);
            }
            if (temporalAdjuster instanceof OffsetDateTime) {
                return (OffsetDateTime) temporalAdjuster;
            }
            return (OffsetDateTime) temporalAdjuster.adjustInto(this);
        }
        return with(this.dateTime.mo13061with(temporalAdjuster), this.offset);
    }

    @Override // org.threeten.bp.temporal.Temporal
    /* renamed from: with */
    public OffsetDateTime mo13062with(TemporalField temporalField, long j) {
        if (temporalField instanceof ChronoField) {
            ChronoField chronoField = (ChronoField) temporalField;
            int ordinal = chronoField.ordinal();
            if (ordinal == 28) {
                return ofInstant(Instant.ofEpochSecond(j, getNano()), this.offset);
            }
            if (ordinal != 29) {
                return with(this.dateTime.mo13062with(temporalField, j), this.offset);
            }
            return with(this.dateTime, ZoneOffset.ofTotalSeconds(chronoField.checkValidIntValue(j)));
        }
        return (OffsetDateTime) temporalField.adjustInto(this, j);
    }
}
