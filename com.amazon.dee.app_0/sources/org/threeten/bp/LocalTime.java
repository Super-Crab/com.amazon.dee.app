package org.threeten.bp;

import com.amazon.alexa.comms.mediaInsights.service.configuration.TracePublisherServiceConfigImpl;
import com.android.tools.r8.GeneratedOutlineSupport1;
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
public final class LocalTime extends DefaultInterfaceTemporalAccessor implements Temporal, TemporalAdjuster, Comparable<LocalTime>, Serializable {
    public static final TemporalQuery<LocalTime> FROM = new TemporalQuery<LocalTime>() { // from class: org.threeten.bp.LocalTime.1
        @Override // org.threeten.bp.temporal.TemporalQuery
        /* renamed from: queryFrom  reason: avoid collision after fix types in other method */
        public LocalTime mo13072queryFrom(TemporalAccessor temporalAccessor) {
            return LocalTime.from(temporalAccessor);
        }
    };
    private static final LocalTime[] HOURS = new LocalTime[24];
    static final int HOURS_PER_DAY = 24;
    public static final LocalTime MAX;
    static final long MICROS_PER_DAY = 86400000000L;
    public static final LocalTime MIDNIGHT;
    static final long MILLIS_PER_DAY = 86400000;
    public static final LocalTime MIN;
    static final int MINUTES_PER_DAY = 1440;
    static final int MINUTES_PER_HOUR = 60;
    static final long NANOS_PER_DAY = 86400000000000L;
    static final long NANOS_PER_HOUR = 3600000000000L;
    static final long NANOS_PER_MINUTE = 60000000000L;
    static final long NANOS_PER_SECOND = 1000000000;
    public static final LocalTime NOON;
    static final int SECONDS_PER_DAY = 86400;
    static final int SECONDS_PER_HOUR = 3600;
    static final int SECONDS_PER_MINUTE = 60;
    private static final long serialVersionUID = 6414437269572265201L;
    private final byte hour;
    private final byte minute;
    private final int nano;
    private final byte second;

    /* renamed from: org.threeten.bp.LocalTime$2  reason: invalid class name */
    /* loaded from: classes5.dex */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$org$threeten$bp$temporal$ChronoField;
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
            $SwitchMap$org$threeten$bp$temporal$ChronoField = new int[ChronoField.values().length];
            try {
                $SwitchMap$org$threeten$bp$temporal$ChronoField[ChronoField.NANO_OF_SECOND.ordinal()] = 1;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$org$threeten$bp$temporal$ChronoField[ChronoField.NANO_OF_DAY.ordinal()] = 2;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$org$threeten$bp$temporal$ChronoField[ChronoField.MICRO_OF_SECOND.ordinal()] = 3;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$org$threeten$bp$temporal$ChronoField[ChronoField.MICRO_OF_DAY.ordinal()] = 4;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$org$threeten$bp$temporal$ChronoField[ChronoField.MILLI_OF_SECOND.ordinal()] = 5;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                $SwitchMap$org$threeten$bp$temporal$ChronoField[ChronoField.MILLI_OF_DAY.ordinal()] = 6;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                $SwitchMap$org$threeten$bp$temporal$ChronoField[ChronoField.SECOND_OF_MINUTE.ordinal()] = 7;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                $SwitchMap$org$threeten$bp$temporal$ChronoField[ChronoField.SECOND_OF_DAY.ordinal()] = 8;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                $SwitchMap$org$threeten$bp$temporal$ChronoField[ChronoField.MINUTE_OF_HOUR.ordinal()] = 9;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                $SwitchMap$org$threeten$bp$temporal$ChronoField[ChronoField.MINUTE_OF_DAY.ordinal()] = 10;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                $SwitchMap$org$threeten$bp$temporal$ChronoField[ChronoField.HOUR_OF_AMPM.ordinal()] = 11;
            } catch (NoSuchFieldError unused18) {
            }
            try {
                $SwitchMap$org$threeten$bp$temporal$ChronoField[ChronoField.CLOCK_HOUR_OF_AMPM.ordinal()] = 12;
            } catch (NoSuchFieldError unused19) {
            }
            try {
                $SwitchMap$org$threeten$bp$temporal$ChronoField[ChronoField.HOUR_OF_DAY.ordinal()] = 13;
            } catch (NoSuchFieldError unused20) {
            }
            try {
                $SwitchMap$org$threeten$bp$temporal$ChronoField[ChronoField.CLOCK_HOUR_OF_DAY.ordinal()] = 14;
            } catch (NoSuchFieldError unused21) {
            }
            try {
                $SwitchMap$org$threeten$bp$temporal$ChronoField[ChronoField.AMPM_OF_DAY.ordinal()] = 15;
            } catch (NoSuchFieldError unused22) {
            }
        }
    }

    static {
        int i = 0;
        while (true) {
            LocalTime[] localTimeArr = HOURS;
            if (i < localTimeArr.length) {
                localTimeArr[i] = new LocalTime(i, 0, 0, 0);
                i++;
            } else {
                MIDNIGHT = localTimeArr[0];
                NOON = localTimeArr[12];
                MIN = localTimeArr[0];
                MAX = new LocalTime(23, 59, 59, Year.MAX_VALUE);
                return;
            }
        }
    }

    private LocalTime(int i, int i2, int i3, int i4) {
        this.hour = (byte) i;
        this.minute = (byte) i2;
        this.second = (byte) i3;
        this.nano = i4;
    }

    private static LocalTime create(int i, int i2, int i3, int i4) {
        if ((i2 | i3 | i4) == 0) {
            return HOURS[i];
        }
        return new LocalTime(i, i2, i3, i4);
    }

    public static LocalTime from(TemporalAccessor temporalAccessor) {
        LocalTime localTime = (LocalTime) temporalAccessor.query(TemporalQueries.localTime());
        if (localTime != null) {
            return localTime;
        }
        throw new DateTimeException("Unable to obtain LocalTime from TemporalAccessor: " + temporalAccessor + ", type " + temporalAccessor.getClass().getName());
    }

    private int get0(TemporalField temporalField) {
        switch (((ChronoField) temporalField).ordinal()) {
            case 0:
                return this.nano;
            case 1:
                throw new DateTimeException(GeneratedOutlineSupport1.outline82("Field too large for an int: ", temporalField));
            case 2:
                return this.nano / 1000;
            case 3:
                throw new DateTimeException(GeneratedOutlineSupport1.outline82("Field too large for an int: ", temporalField));
            case 4:
                return this.nano / TracePublisherServiceConfigImpl.TOTAL_TRACES_CAPACITY_IN_BYTES;
            case 5:
                return (int) (toNanoOfDay() / 1000000);
            case 6:
                return this.second;
            case 7:
                return toSecondOfDay();
            case 8:
                return this.minute;
            case 9:
                return (this.hour * 60) + this.minute;
            case 10:
                return this.hour % 12;
            case 11:
                int i = this.hour % 12;
                if (i % 12 != 0) {
                    return i;
                }
                return 12;
            case 12:
                return this.hour;
            case 13:
                byte b = this.hour;
                if (b != 0) {
                    return b;
                }
                return 24;
            case 14:
                return this.hour / 12;
            default:
                throw new UnsupportedTemporalTypeException(GeneratedOutlineSupport1.outline82("Unsupported field: ", temporalField));
        }
    }

    public static LocalTime now() {
        return now(Clock.systemDefaultZone());
    }

    public static LocalTime of(int i, int i2) {
        ChronoField.HOUR_OF_DAY.checkValidValue(i);
        if (i2 == 0) {
            return HOURS[i];
        }
        ChronoField.MINUTE_OF_HOUR.checkValidValue(i2);
        return new LocalTime(i, i2, 0, 0);
    }

    public static LocalTime ofNanoOfDay(long j) {
        ChronoField.NANO_OF_DAY.checkValidValue(j);
        int i = (int) (j / NANOS_PER_HOUR);
        long j2 = j - (i * NANOS_PER_HOUR);
        int i2 = (int) (j2 / NANOS_PER_MINUTE);
        long j3 = j2 - (i2 * NANOS_PER_MINUTE);
        int i3 = (int) (j3 / 1000000000);
        return create(i, i2, i3, (int) (j3 - (i3 * 1000000000)));
    }

    public static LocalTime ofSecondOfDay(long j) {
        ChronoField.SECOND_OF_DAY.checkValidValue(j);
        int i = (int) (j / 3600);
        long j2 = j - (i * 3600);
        int i2 = (int) (j2 / 60);
        return create(i, i2, (int) (j2 - (i2 * 60)), 0);
    }

    public static LocalTime parse(CharSequence charSequence) {
        return parse(charSequence, DateTimeFormatter.ISO_LOCAL_TIME);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v2, types: [int] */
    /* JADX WARN: Type inference failed for: r5v4, types: [int] */
    public static LocalTime readExternal(DataInput dataInput) throws IOException {
        byte readByte;
        int readInt;
        int readByte2 = dataInput.readByte();
        byte b = 0;
        if (readByte2 < 0) {
            readByte2 = ~readByte2;
            readInt = 0;
            readByte = 0;
        } else {
            readByte = dataInput.readByte();
            if (readByte < 0) {
                readByte = ~readByte;
                readInt = 0;
            } else {
                byte readByte3 = dataInput.readByte();
                if (readByte3 < 0) {
                    b = ~readByte3;
                    readInt = 0;
                } else {
                    readInt = dataInput.readInt();
                    b = readByte3;
                }
            }
        }
        return of(readByte2, readByte, b, readInt);
    }

    private Object readResolve() throws ObjectStreamException {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }

    private Object writeReplace() {
        return new Ser((byte) 5, this);
    }

    @Override // org.threeten.bp.temporal.TemporalAdjuster
    public Temporal adjustInto(Temporal temporal) {
        return temporal.mo13062with(ChronoField.NANO_OF_DAY, toNanoOfDay());
    }

    public LocalDateTime atDate(LocalDate localDate) {
        return LocalDateTime.of(localDate, this);
    }

    public OffsetTime atOffset(ZoneOffset zoneOffset) {
        return OffsetTime.of(this, zoneOffset);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof LocalTime)) {
            return false;
        }
        LocalTime localTime = (LocalTime) obj;
        return this.hour == localTime.hour && this.minute == localTime.minute && this.second == localTime.second && this.nano == localTime.nano;
    }

    public String format(DateTimeFormatter dateTimeFormatter) {
        Jdk8Methods.requireNonNull(dateTimeFormatter, "formatter");
        return dateTimeFormatter.format(this);
    }

    @Override // org.threeten.bp.jdk8.DefaultInterfaceTemporalAccessor, org.threeten.bp.temporal.TemporalAccessor
    public int get(TemporalField temporalField) {
        if (temporalField instanceof ChronoField) {
            return get0(temporalField);
        }
        return super.get(temporalField);
    }

    public int getHour() {
        return this.hour;
    }

    @Override // org.threeten.bp.temporal.TemporalAccessor
    public long getLong(TemporalField temporalField) {
        if (temporalField instanceof ChronoField) {
            if (temporalField == ChronoField.NANO_OF_DAY) {
                return toNanoOfDay();
            }
            if (temporalField == ChronoField.MICRO_OF_DAY) {
                return toNanoOfDay() / 1000;
            }
            return get0(temporalField);
        }
        return temporalField.getFrom(this);
    }

    public int getMinute() {
        return this.minute;
    }

    public int getNano() {
        return this.nano;
    }

    public int getSecond() {
        return this.second;
    }

    public int hashCode() {
        long nanoOfDay = toNanoOfDay();
        return (int) (nanoOfDay ^ (nanoOfDay >>> 32));
    }

    public boolean isAfter(LocalTime localTime) {
        return compareTo(localTime) > 0;
    }

    public boolean isBefore(LocalTime localTime) {
        return compareTo(localTime) < 0;
    }

    @Override // org.threeten.bp.temporal.TemporalAccessor
    public boolean isSupported(TemporalField temporalField) {
        if (temporalField instanceof ChronoField) {
            return temporalField.isTimeBased();
        }
        return temporalField != null && temporalField.isSupportedBy(this);
    }

    public LocalTime minusHours(long j) {
        return plusHours(-(j % 24));
    }

    public LocalTime minusMinutes(long j) {
        return plusMinutes(-(j % 1440));
    }

    public LocalTime minusNanos(long j) {
        return plusNanos(-(j % NANOS_PER_DAY));
    }

    public LocalTime minusSeconds(long j) {
        return plusSeconds(-(j % 86400));
    }

    public LocalTime plusHours(long j) {
        return j == 0 ? this : create(((((int) (j % 24)) + this.hour) + 24) % 24, this.minute, this.second, this.nano);
    }

    public LocalTime plusMinutes(long j) {
        if (j == 0) {
            return this;
        }
        int i = (this.hour * 60) + this.minute;
        int i2 = ((((int) (j % 1440)) + i) + 1440) % 1440;
        return i == i2 ? this : create(i2 / 60, i2 % 60, this.second, this.nano);
    }

    public LocalTime plusNanos(long j) {
        if (j == 0) {
            return this;
        }
        long nanoOfDay = toNanoOfDay();
        long j2 = (((j % NANOS_PER_DAY) + nanoOfDay) + NANOS_PER_DAY) % NANOS_PER_DAY;
        return nanoOfDay == j2 ? this : create((int) (j2 / NANOS_PER_HOUR), (int) ((j2 / NANOS_PER_MINUTE) % 60), (int) ((j2 / 1000000000) % 60), (int) (j2 % 1000000000));
    }

    public LocalTime plusSeconds(long j) {
        if (j == 0) {
            return this;
        }
        int i = (this.minute * 60) + (this.hour * 3600) + this.second;
        int i2 = ((((int) (j % 86400)) + i) + 86400) % 86400;
        return i == i2 ? this : create(i2 / 3600, (i2 / 60) % 60, i2 % 60, this.nano);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.threeten.bp.jdk8.DefaultInterfaceTemporalAccessor, org.threeten.bp.temporal.TemporalAccessor
    public <R> R query(TemporalQuery<R> temporalQuery) {
        if (temporalQuery == TemporalQueries.precision()) {
            return (R) ChronoUnit.NANOS;
        }
        if (temporalQuery == TemporalQueries.localTime()) {
            return this;
        }
        if (temporalQuery != TemporalQueries.chronology() && temporalQuery != TemporalQueries.zoneId() && temporalQuery != TemporalQueries.zone() && temporalQuery != TemporalQueries.offset() && temporalQuery != TemporalQueries.localDate()) {
            return temporalQuery.mo13072queryFrom(this);
        }
        return null;
    }

    @Override // org.threeten.bp.jdk8.DefaultInterfaceTemporalAccessor, org.threeten.bp.temporal.TemporalAccessor
    public ValueRange range(TemporalField temporalField) {
        return super.range(temporalField);
    }

    public long toNanoOfDay() {
        return (this.second * 1000000000) + (this.minute * NANOS_PER_MINUTE) + (this.hour * NANOS_PER_HOUR) + this.nano;
    }

    public int toSecondOfDay() {
        return (this.minute * 60) + (this.hour * 3600) + this.second;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(18);
        byte b = this.hour;
        byte b2 = this.minute;
        byte b3 = this.second;
        int i = this.nano;
        sb.append(b < 10 ? "0" : "");
        sb.append((int) b);
        String str = ":0";
        sb.append(b2 < 10 ? str : ":");
        sb.append((int) b2);
        if (b3 > 0 || i > 0) {
            if (b3 >= 10) {
                str = ":";
            }
            sb.append(str);
            sb.append((int) b3);
            if (i > 0) {
                sb.append('.');
                if (i % TracePublisherServiceConfigImpl.TOTAL_TRACES_CAPACITY_IN_BYTES == 0) {
                    sb.append(Integer.toString((i / TracePublisherServiceConfigImpl.TOTAL_TRACES_CAPACITY_IN_BYTES) + 1000).substring(1));
                } else if (i % 1000 == 0) {
                    sb.append(Integer.toString((i / 1000) + TracePublisherServiceConfigImpl.TOTAL_TRACES_CAPACITY_IN_BYTES).substring(1));
                } else {
                    sb.append(Integer.toString(i + 1000000000).substring(1));
                }
            }
        }
        return sb.toString();
    }

    public LocalTime truncatedTo(TemporalUnit temporalUnit) {
        if (temporalUnit == ChronoUnit.NANOS) {
            return this;
        }
        Duration duration = temporalUnit.getDuration();
        if (duration.getSeconds() <= 86400) {
            long nanos = duration.toNanos();
            if (NANOS_PER_DAY % nanos == 0) {
                return ofNanoOfDay((toNanoOfDay() / nanos) * nanos);
            }
            throw new DateTimeException("Unit must divide into a standard day without remainder");
        }
        throw new DateTimeException("Unit is too large to be used for truncation");
    }

    @Override // org.threeten.bp.temporal.Temporal
    public long until(Temporal temporal, TemporalUnit temporalUnit) {
        LocalTime from = from(temporal);
        if (temporalUnit instanceof ChronoUnit) {
            long nanoOfDay = from.toNanoOfDay() - toNanoOfDay();
            switch (((ChronoUnit) temporalUnit).ordinal()) {
                case 0:
                    return nanoOfDay;
                case 1:
                    return nanoOfDay / 1000;
                case 2:
                    return nanoOfDay / 1000000;
                case 3:
                    return nanoOfDay / 1000000000;
                case 4:
                    return nanoOfDay / NANOS_PER_MINUTE;
                case 5:
                    return nanoOfDay / NANOS_PER_HOUR;
                case 6:
                    return nanoOfDay / 43200000000000L;
                default:
                    throw new UnsupportedTemporalTypeException("Unsupported unit: " + temporalUnit);
            }
        }
        return temporalUnit.between(this, from);
    }

    public LocalTime withHour(int i) {
        if (this.hour == i) {
            return this;
        }
        ChronoField.HOUR_OF_DAY.checkValidValue(i);
        return create(i, this.minute, this.second, this.nano);
    }

    public LocalTime withMinute(int i) {
        if (this.minute == i) {
            return this;
        }
        ChronoField.MINUTE_OF_HOUR.checkValidValue(i);
        return create(this.hour, i, this.second, this.nano);
    }

    public LocalTime withNano(int i) {
        if (this.nano == i) {
            return this;
        }
        ChronoField.NANO_OF_SECOND.checkValidValue(i);
        return create(this.hour, this.minute, this.second, i);
    }

    public LocalTime withSecond(int i) {
        if (this.second == i) {
            return this;
        }
        ChronoField.SECOND_OF_MINUTE.checkValidValue(i);
        return create(this.hour, this.minute, i, this.nano);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void writeExternal(DataOutput dataOutput) throws IOException {
        if (this.nano == 0) {
            if (this.second == 0) {
                if (this.minute == 0) {
                    dataOutput.writeByte(~this.hour);
                    return;
                }
                dataOutput.writeByte(this.hour);
                dataOutput.writeByte(~this.minute);
                return;
            }
            dataOutput.writeByte(this.hour);
            dataOutput.writeByte(this.minute);
            dataOutput.writeByte(~this.second);
            return;
        }
        dataOutput.writeByte(this.hour);
        dataOutput.writeByte(this.minute);
        dataOutput.writeByte(this.second);
        dataOutput.writeInt(this.nano);
    }

    public static LocalTime now(ZoneId zoneId) {
        return now(Clock.system(zoneId));
    }

    public static LocalTime parse(CharSequence charSequence, DateTimeFormatter dateTimeFormatter) {
        Jdk8Methods.requireNonNull(dateTimeFormatter, "formatter");
        return (LocalTime) dateTimeFormatter.parse(charSequence, FROM);
    }

    @Override // java.lang.Comparable
    public int compareTo(LocalTime localTime) {
        int compareInts = Jdk8Methods.compareInts(this.hour, localTime.hour);
        if (compareInts == 0) {
            int compareInts2 = Jdk8Methods.compareInts(this.minute, localTime.minute);
            if (compareInts2 != 0) {
                return compareInts2;
            }
            int compareInts3 = Jdk8Methods.compareInts(this.second, localTime.second);
            return compareInts3 == 0 ? Jdk8Methods.compareInts(this.nano, localTime.nano) : compareInts3;
        }
        return compareInts;
    }

    public static LocalTime now(Clock clock) {
        Jdk8Methods.requireNonNull(clock, "clock");
        Instant instant = clock.instant();
        long epochSecond = ((instant.getEpochSecond() % 86400) + clock.getZone().getRules().getOffset(instant).getTotalSeconds()) % 86400;
        if (epochSecond < 0) {
            epochSecond += 86400;
        }
        return ofSecondOfDay(epochSecond, instant.getNano());
    }

    @Override // org.threeten.bp.temporal.Temporal
    /* renamed from: minus */
    public LocalTime mo13057minus(TemporalAmount temporalAmount) {
        return (LocalTime) temporalAmount.subtractFrom(this);
    }

    @Override // org.threeten.bp.temporal.Temporal
    /* renamed from: plus */
    public LocalTime mo13059plus(TemporalAmount temporalAmount) {
        return (LocalTime) temporalAmount.addTo(this);
    }

    @Override // org.threeten.bp.temporal.Temporal
    /* renamed from: with */
    public LocalTime mo13061with(TemporalAdjuster temporalAdjuster) {
        if (temporalAdjuster instanceof LocalTime) {
            return (LocalTime) temporalAdjuster;
        }
        return (LocalTime) temporalAdjuster.adjustInto(this);
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
    public LocalTime mo13056minus(long j, TemporalUnit temporalUnit) {
        return j == Long.MIN_VALUE ? mo13060plus(Long.MAX_VALUE, temporalUnit).mo13060plus(1L, temporalUnit) : mo13060plus(-j, temporalUnit);
    }

    @Override // org.threeten.bp.temporal.Temporal
    /* renamed from: plus */
    public LocalTime mo13060plus(long j, TemporalUnit temporalUnit) {
        if (temporalUnit instanceof ChronoUnit) {
            switch (((ChronoUnit) temporalUnit).ordinal()) {
                case 0:
                    return plusNanos(j);
                case 1:
                    return plusNanos((j % MICROS_PER_DAY) * 1000);
                case 2:
                    return plusNanos((j % 86400000) * 1000000);
                case 3:
                    return plusSeconds(j);
                case 4:
                    return plusMinutes(j);
                case 5:
                    return plusHours(j);
                case 6:
                    return plusHours((j % 2) * 12);
                default:
                    throw new UnsupportedTemporalTypeException("Unsupported unit: " + temporalUnit);
            }
        }
        return (LocalTime) temporalUnit.addTo(this, j);
    }

    public static LocalTime of(int i, int i2, int i3) {
        ChronoField.HOUR_OF_DAY.checkValidValue(i);
        if ((i2 | i3) == 0) {
            return HOURS[i];
        }
        ChronoField.MINUTE_OF_HOUR.checkValidValue(i2);
        ChronoField.SECOND_OF_MINUTE.checkValidValue(i3);
        return new LocalTime(i, i2, i3, 0);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static LocalTime ofSecondOfDay(long j, int i) {
        ChronoField.SECOND_OF_DAY.checkValidValue(j);
        ChronoField.NANO_OF_SECOND.checkValidValue(i);
        int i2 = (int) (j / 3600);
        long j2 = j - (i2 * 3600);
        int i3 = (int) (j2 / 60);
        return create(i2, i3, (int) (j2 - (i3 * 60)), i);
    }

    @Override // org.threeten.bp.temporal.Temporal
    /* renamed from: with */
    public LocalTime mo13062with(TemporalField temporalField, long j) {
        if (temporalField instanceof ChronoField) {
            ChronoField chronoField = (ChronoField) temporalField;
            chronoField.checkValidValue(j);
            switch (chronoField.ordinal()) {
                case 0:
                    return withNano((int) j);
                case 1:
                    return ofNanoOfDay(j);
                case 2:
                    return withNano(((int) j) * 1000);
                case 3:
                    return ofNanoOfDay(j * 1000);
                case 4:
                    return withNano(((int) j) * TracePublisherServiceConfigImpl.TOTAL_TRACES_CAPACITY_IN_BYTES);
                case 5:
                    return ofNanoOfDay(j * 1000000);
                case 6:
                    return withSecond((int) j);
                case 7:
                    return plusSeconds(j - toSecondOfDay());
                case 8:
                    return withMinute((int) j);
                case 9:
                    return plusMinutes(j - ((this.hour * 60) + this.minute));
                case 10:
                    return plusHours(j - (this.hour % 12));
                case 11:
                    if (j == 12) {
                        j = 0;
                    }
                    return plusHours(j - (this.hour % 12));
                case 12:
                    return withHour((int) j);
                case 13:
                    if (j == 24) {
                        j = 0;
                    }
                    return withHour((int) j);
                case 14:
                    return plusHours((j - (this.hour / 12)) * 12);
                default:
                    throw new UnsupportedTemporalTypeException(GeneratedOutlineSupport1.outline82("Unsupported field: ", temporalField));
            }
        }
        return (LocalTime) temporalField.adjustInto(this, j);
    }

    public static LocalTime of(int i, int i2, int i3, int i4) {
        ChronoField.HOUR_OF_DAY.checkValidValue(i);
        ChronoField.MINUTE_OF_HOUR.checkValidValue(i2);
        ChronoField.SECOND_OF_MINUTE.checkValidValue(i3);
        ChronoField.NANO_OF_SECOND.checkValidValue(i4);
        return create(i, i2, i3, i4);
    }
}
