package org.threeten.bp.chrono;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Calendar;
import org.threeten.bp.Clock;
import org.threeten.bp.DateTimeException;
import org.threeten.bp.LocalDate;
import org.threeten.bp.LocalTime;
import org.threeten.bp.Period;
import org.threeten.bp.ZoneId;
import org.threeten.bp.jdk8.Jdk8Methods;
import org.threeten.bp.temporal.ChronoField;
import org.threeten.bp.temporal.Temporal;
import org.threeten.bp.temporal.TemporalAccessor;
import org.threeten.bp.temporal.TemporalAdjuster;
import org.threeten.bp.temporal.TemporalAmount;
import org.threeten.bp.temporal.TemporalField;
import org.threeten.bp.temporal.TemporalUnit;
import org.threeten.bp.temporal.UnsupportedTemporalTypeException;
import org.threeten.bp.temporal.ValueRange;
/* loaded from: classes5.dex */
public final class JapaneseDate extends ChronoDateImpl<JapaneseDate> implements Serializable {
    static final LocalDate MIN_DATE = LocalDate.of(1873, 1, 1);
    private static final long serialVersionUID = -305327627230580483L;
    private transient JapaneseEra era;
    private final LocalDate isoDate;
    private transient int yearOfEra;

    /* renamed from: org.threeten.bp.chrono.JapaneseDate$1  reason: invalid class name */
    /* loaded from: classes5.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$threeten$bp$temporal$ChronoField = new int[ChronoField.values().length];

        static {
            try {
                $SwitchMap$org$threeten$bp$temporal$ChronoField[ChronoField.DAY_OF_YEAR.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$org$threeten$bp$temporal$ChronoField[ChronoField.YEAR_OF_ERA.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$org$threeten$bp$temporal$ChronoField[ChronoField.ALIGNED_DAY_OF_WEEK_IN_MONTH.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$org$threeten$bp$temporal$ChronoField[ChronoField.ALIGNED_DAY_OF_WEEK_IN_YEAR.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$org$threeten$bp$temporal$ChronoField[ChronoField.ALIGNED_WEEK_OF_MONTH.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$org$threeten$bp$temporal$ChronoField[ChronoField.ALIGNED_WEEK_OF_YEAR.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$org$threeten$bp$temporal$ChronoField[ChronoField.ERA.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public JapaneseDate(LocalDate localDate) {
        if (!localDate.isBefore(MIN_DATE)) {
            this.era = JapaneseEra.from(localDate);
            this.yearOfEra = localDate.getYear() - (this.era.startDate().getYear() - 1);
            this.isoDate = localDate;
            return;
        }
        throw new DateTimeException("Minimum supported date is January 1st Meiji 6");
    }

    private ValueRange actualRange(int i) {
        Calendar calendar = Calendar.getInstance(JapaneseChronology.LOCALE);
        calendar.set(0, this.era.getValue() + 2);
        calendar.set(this.yearOfEra, this.isoDate.getMonthValue() - 1, this.isoDate.getDayOfMonth());
        return ValueRange.of(calendar.getActualMinimum(i), calendar.getActualMaximum(i));
    }

    public static JapaneseDate from(TemporalAccessor temporalAccessor) {
        return JapaneseChronology.INSTANCE.mo13045date(temporalAccessor);
    }

    private long getDayOfYear() {
        int dayOfYear;
        if (this.yearOfEra == 1) {
            dayOfYear = (this.isoDate.getDayOfYear() - this.era.startDate().getDayOfYear()) + 1;
        } else {
            dayOfYear = this.isoDate.getDayOfYear();
        }
        return dayOfYear;
    }

    public static JapaneseDate now() {
        return now(Clock.systemDefaultZone());
    }

    public static JapaneseDate of(JapaneseEra japaneseEra, int i, int i2, int i3) {
        Jdk8Methods.requireNonNull(japaneseEra, "era");
        if (i >= 1) {
            LocalDate startDate = japaneseEra.startDate();
            LocalDate endDate = japaneseEra.endDate();
            LocalDate of = LocalDate.of((startDate.getYear() - 1) + i, i2, i3);
            if (!of.isBefore(startDate) && !of.isAfter(endDate)) {
                return new JapaneseDate(japaneseEra, i, of);
            }
            throw new DateTimeException("Requested date is outside bounds of era " + japaneseEra);
        }
        throw new DateTimeException(GeneratedOutlineSupport1.outline49("Invalid YearOfEra: ", i));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static JapaneseDate ofYearDay(JapaneseEra japaneseEra, int i, int i2) {
        Jdk8Methods.requireNonNull(japaneseEra, "era");
        if (i >= 1) {
            LocalDate startDate = japaneseEra.startDate();
            LocalDate endDate = japaneseEra.endDate();
            if (i == 1 && (i2 = i2 + (startDate.getDayOfYear() - 1)) > startDate.lengthOfYear()) {
                throw new DateTimeException("DayOfYear exceeds maximum allowed in the first year of era " + japaneseEra);
            }
            LocalDate ofYearDay = LocalDate.ofYearDay((startDate.getYear() - 1) + i, i2);
            if (!ofYearDay.isBefore(startDate) && !ofYearDay.isAfter(endDate)) {
                return new JapaneseDate(japaneseEra, i, ofYearDay);
            }
            throw new DateTimeException("Requested date is outside bounds of era " + japaneseEra);
        }
        throw new DateTimeException(GeneratedOutlineSupport1.outline49("Invalid YearOfEra: ", i));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ChronoLocalDate readExternal(DataInput dataInput) throws IOException {
        return JapaneseChronology.INSTANCE.mo13043date(dataInput.readInt(), (int) dataInput.readByte(), (int) dataInput.readByte());
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        this.era = JapaneseEra.from(this.isoDate);
        this.yearOfEra = this.isoDate.getYear() - (this.era.startDate().getYear() - 1);
    }

    private JapaneseDate withYear(JapaneseEra japaneseEra, int i) {
        return with(this.isoDate.withYear(JapaneseChronology.INSTANCE.prolepticYear(japaneseEra, i)));
    }

    private Object writeReplace() {
        return new Ser((byte) 1, this);
    }

    @Override // org.threeten.bp.chrono.ChronoDateImpl, org.threeten.bp.chrono.ChronoLocalDate
    /* renamed from: atTime */
    public final ChronoLocalDateTime<JapaneseDate> mo12887atTime(LocalTime localTime) {
        return super.mo12887atTime(localTime);
    }

    @Override // org.threeten.bp.chrono.ChronoLocalDate
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof JapaneseDate)) {
            return false;
        }
        return this.isoDate.equals(((JapaneseDate) obj).isoDate);
    }

    @Override // org.threeten.bp.temporal.TemporalAccessor
    public long getLong(TemporalField temporalField) {
        if (temporalField instanceof ChronoField) {
            int ordinal = ((ChronoField) temporalField).ordinal();
            if (ordinal != 16 && ordinal != 17) {
                if (ordinal == 19) {
                    return getDayOfYear();
                }
                if (ordinal == 25) {
                    return this.yearOfEra;
                }
                if (ordinal == 27) {
                    return this.era.getValue();
                }
                if (ordinal != 21 && ordinal != 22) {
                    return this.isoDate.getLong(temporalField);
                }
            }
            throw new UnsupportedTemporalTypeException(GeneratedOutlineSupport1.outline82("Unsupported field: ", temporalField));
        }
        return temporalField.getFrom(this);
    }

    @Override // org.threeten.bp.chrono.ChronoLocalDate
    public int hashCode() {
        return mo13054getChronology().getId().hashCode() ^ this.isoDate.hashCode();
    }

    @Override // org.threeten.bp.chrono.ChronoLocalDate, org.threeten.bp.temporal.TemporalAccessor
    public boolean isSupported(TemporalField temporalField) {
        if (temporalField == ChronoField.ALIGNED_DAY_OF_WEEK_IN_MONTH || temporalField == ChronoField.ALIGNED_DAY_OF_WEEK_IN_YEAR || temporalField == ChronoField.ALIGNED_WEEK_OF_MONTH || temporalField == ChronoField.ALIGNED_WEEK_OF_YEAR) {
            return false;
        }
        return super.isSupported(temporalField);
    }

    @Override // org.threeten.bp.chrono.ChronoLocalDate
    public int lengthOfMonth() {
        return this.isoDate.lengthOfMonth();
    }

    @Override // org.threeten.bp.chrono.ChronoLocalDate
    public int lengthOfYear() {
        Calendar calendar = Calendar.getInstance(JapaneseChronology.LOCALE);
        calendar.set(0, this.era.getValue() + 2);
        calendar.set(this.yearOfEra, this.isoDate.getMonthValue() - 1, this.isoDate.getDayOfMonth());
        return calendar.getActualMaximum(6);
    }

    @Override // org.threeten.bp.jdk8.DefaultInterfaceTemporalAccessor, org.threeten.bp.temporal.TemporalAccessor
    public ValueRange range(TemporalField temporalField) {
        if (temporalField instanceof ChronoField) {
            if (isSupported(temporalField)) {
                ChronoField chronoField = (ChronoField) temporalField;
                int ordinal = chronoField.ordinal();
                if (ordinal == 19) {
                    return actualRange(6);
                }
                if (ordinal != 25) {
                    return mo13054getChronology().range(chronoField);
                }
                return actualRange(1);
            }
            throw new UnsupportedTemporalTypeException(GeneratedOutlineSupport1.outline82("Unsupported field: ", temporalField));
        }
        return temporalField.rangeRefinedBy(this);
    }

    @Override // org.threeten.bp.chrono.ChronoLocalDate
    public long toEpochDay() {
        return this.isoDate.toEpochDay();
    }

    @Override // org.threeten.bp.chrono.ChronoDateImpl, org.threeten.bp.temporal.Temporal
    public /* bridge */ /* synthetic */ long until(Temporal temporal, TemporalUnit temporalUnit) {
        return super.until(temporal, temporalUnit);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void writeExternal(DataOutput dataOutput) throws IOException {
        dataOutput.writeInt(get(ChronoField.YEAR));
        dataOutput.writeByte(get(ChronoField.MONTH_OF_YEAR));
        dataOutput.writeByte(get(ChronoField.DAY_OF_MONTH));
    }

    public static JapaneseDate now(ZoneId zoneId) {
        return now(Clock.system(zoneId));
    }

    @Override // org.threeten.bp.chrono.ChronoLocalDate
    /* renamed from: getChronology  reason: collision with other method in class */
    public JapaneseChronology mo13054getChronology() {
        return JapaneseChronology.INSTANCE;
    }

    @Override // org.threeten.bp.chrono.ChronoLocalDate
    /* renamed from: getEra  reason: collision with other method in class */
    public JapaneseEra mo13055getEra() {
        return this.era;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.threeten.bp.chrono.ChronoDateImpl
    public ChronoDateImpl<JapaneseDate> plusDays(long j) {
        return with(this.isoDate.plusDays(j));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.threeten.bp.chrono.ChronoDateImpl
    public ChronoDateImpl<JapaneseDate> plusMonths(long j) {
        return with(this.isoDate.plusMonths(j));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.threeten.bp.chrono.ChronoDateImpl
    public ChronoDateImpl<JapaneseDate> plusYears(long j) {
        return with(this.isoDate.plusYears(j));
    }

    @Override // org.threeten.bp.chrono.ChronoDateImpl, org.threeten.bp.chrono.ChronoLocalDate
    /* renamed from: until */
    public ChronoPeriod mo12893until(ChronoLocalDate chronoLocalDate) {
        Period mo12893until = this.isoDate.mo12893until(chronoLocalDate);
        return mo13054getChronology().period(mo12893until.getYears(), mo12893until.getMonths(), mo12893until.getDays());
    }

    public static JapaneseDate now(Clock clock) {
        return new JapaneseDate(LocalDate.now(clock));
    }

    private JapaneseDate withYear(int i) {
        return withYear(mo13055getEra(), i);
    }

    @Override // org.threeten.bp.chrono.ChronoLocalDate, org.threeten.bp.jdk8.DefaultInterfaceTemporal, org.threeten.bp.temporal.Temporal
    /* renamed from: minus  reason: collision with other method in class */
    public JapaneseDate mo13057minus(TemporalAmount temporalAmount) {
        return (JapaneseDate) super.mo13057minus(temporalAmount);
    }

    @Override // org.threeten.bp.chrono.ChronoLocalDate, org.threeten.bp.jdk8.DefaultInterfaceTemporal, org.threeten.bp.temporal.Temporal
    /* renamed from: with  reason: collision with other method in class */
    public JapaneseDate mo13061with(TemporalAdjuster temporalAdjuster) {
        return (JapaneseDate) super.mo13061with(temporalAdjuster);
    }

    @Override // org.threeten.bp.chrono.ChronoLocalDate, org.threeten.bp.jdk8.DefaultInterfaceTemporal, org.threeten.bp.temporal.Temporal
    /* renamed from: minus  reason: collision with other method in class */
    public JapaneseDate mo13056minus(long j, TemporalUnit temporalUnit) {
        return (JapaneseDate) super.mo13056minus(j, temporalUnit);
    }

    @Override // org.threeten.bp.chrono.ChronoLocalDate, org.threeten.bp.jdk8.DefaultInterfaceTemporal, org.threeten.bp.temporal.Temporal
    /* renamed from: plus  reason: collision with other method in class */
    public JapaneseDate mo13059plus(TemporalAmount temporalAmount) {
        return (JapaneseDate) super.mo13059plus(temporalAmount);
    }

    @Override // org.threeten.bp.chrono.ChronoLocalDate, org.threeten.bp.temporal.Temporal
    /* renamed from: with  reason: collision with other method in class */
    public JapaneseDate mo13062with(TemporalField temporalField, long j) {
        if (temporalField instanceof ChronoField) {
            ChronoField chronoField = (ChronoField) temporalField;
            if (getLong(chronoField) == j) {
                return this;
            }
            int ordinal = chronoField.ordinal();
            if (ordinal == 19 || ordinal == 25 || ordinal == 27) {
                int checkValidIntValue = mo13054getChronology().range(chronoField).checkValidIntValue(j, chronoField);
                int ordinal2 = chronoField.ordinal();
                if (ordinal2 == 19) {
                    return with(this.isoDate.plusDays(checkValidIntValue - getDayOfYear()));
                }
                if (ordinal2 == 25) {
                    return withYear(checkValidIntValue);
                }
                if (ordinal2 == 27) {
                    return withYear(JapaneseEra.of(checkValidIntValue), this.yearOfEra);
                }
            }
            return with(this.isoDate.mo13062with(temporalField, j));
        }
        return (JapaneseDate) temporalField.adjustInto(this, j);
    }

    @Override // org.threeten.bp.chrono.ChronoDateImpl, org.threeten.bp.chrono.ChronoLocalDate, org.threeten.bp.temporal.Temporal
    /* renamed from: plus */
    public JapaneseDate mo13060plus(long j, TemporalUnit temporalUnit) {
        return (JapaneseDate) super.mo13060plus(j, temporalUnit);
    }

    JapaneseDate(JapaneseEra japaneseEra, int i, LocalDate localDate) {
        if (!localDate.isBefore(MIN_DATE)) {
            this.era = japaneseEra;
            this.yearOfEra = i;
            this.isoDate = localDate;
            return;
        }
        throw new DateTimeException("Minimum supported date is January 1st Meiji 6");
    }

    public static JapaneseDate of(int i, int i2, int i3) {
        return new JapaneseDate(LocalDate.of(i, i2, i3));
    }

    private JapaneseDate with(LocalDate localDate) {
        return localDate.equals(this.isoDate) ? this : new JapaneseDate(localDate);
    }
}
