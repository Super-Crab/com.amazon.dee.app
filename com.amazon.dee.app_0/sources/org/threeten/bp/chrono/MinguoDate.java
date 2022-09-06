package org.threeten.bp.chrono;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.sun.mail.imap.IMAPStore;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.io.Serializable;
import org.threeten.bp.Clock;
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
public final class MinguoDate extends ChronoDateImpl<MinguoDate> implements Serializable {
    private static final long serialVersionUID = 1300372329181994526L;
    private final LocalDate isoDate;

    /* renamed from: org.threeten.bp.chrono.MinguoDate$1  reason: invalid class name */
    /* loaded from: classes5.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$threeten$bp$temporal$ChronoField = new int[ChronoField.values().length];

        static {
            try {
                $SwitchMap$org$threeten$bp$temporal$ChronoField[ChronoField.DAY_OF_MONTH.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$org$threeten$bp$temporal$ChronoField[ChronoField.DAY_OF_YEAR.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$org$threeten$bp$temporal$ChronoField[ChronoField.ALIGNED_WEEK_OF_MONTH.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$org$threeten$bp$temporal$ChronoField[ChronoField.YEAR_OF_ERA.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$org$threeten$bp$temporal$ChronoField[ChronoField.PROLEPTIC_MONTH.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$org$threeten$bp$temporal$ChronoField[ChronoField.YEAR.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$org$threeten$bp$temporal$ChronoField[ChronoField.ERA.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public MinguoDate(LocalDate localDate) {
        Jdk8Methods.requireNonNull(localDate, IMAPStore.ID_DATE);
        this.isoDate = localDate;
    }

    public static MinguoDate from(TemporalAccessor temporalAccessor) {
        return MinguoChronology.INSTANCE.mo13045date(temporalAccessor);
    }

    private long getProlepticMonth() {
        return ((getProlepticYear() * 12) + this.isoDate.getMonthValue()) - 1;
    }

    private int getProlepticYear() {
        return this.isoDate.getYear() - 1911;
    }

    public static MinguoDate now() {
        return now(Clock.systemDefaultZone());
    }

    public static MinguoDate of(int i, int i2, int i3) {
        return MinguoChronology.INSTANCE.mo13043date(i, i2, i3);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ChronoLocalDate readExternal(DataInput dataInput) throws IOException {
        return MinguoChronology.INSTANCE.mo13043date(dataInput.readInt(), (int) dataInput.readByte(), (int) dataInput.readByte());
    }

    private Object writeReplace() {
        return new Ser((byte) 5, this);
    }

    @Override // org.threeten.bp.chrono.ChronoDateImpl, org.threeten.bp.chrono.ChronoLocalDate
    /* renamed from: atTime */
    public final ChronoLocalDateTime<MinguoDate> mo12887atTime(LocalTime localTime) {
        return super.mo12887atTime(localTime);
    }

    @Override // org.threeten.bp.chrono.ChronoLocalDate
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MinguoDate)) {
            return false;
        }
        return this.isoDate.equals(((MinguoDate) obj).isoDate);
    }

    @Override // org.threeten.bp.temporal.TemporalAccessor
    public long getLong(TemporalField temporalField) {
        if (temporalField instanceof ChronoField) {
            int i = 1;
            switch (((ChronoField) temporalField).ordinal()) {
                case 24:
                    return getProlepticMonth();
                case 25:
                    int prolepticYear = getProlepticYear();
                    if (prolepticYear < 1) {
                        prolepticYear = 1 - prolepticYear;
                    }
                    return prolepticYear;
                case 26:
                    return getProlepticYear();
                case 27:
                    if (getProlepticYear() < 1) {
                        i = 0;
                    }
                    return i;
                default:
                    return this.isoDate.getLong(temporalField);
            }
        }
        return temporalField.getFrom(this);
    }

    @Override // org.threeten.bp.chrono.ChronoLocalDate
    public int hashCode() {
        return mo13054getChronology().getId().hashCode() ^ this.isoDate.hashCode();
    }

    @Override // org.threeten.bp.chrono.ChronoLocalDate
    public int lengthOfMonth() {
        return this.isoDate.lengthOfMonth();
    }

    @Override // org.threeten.bp.jdk8.DefaultInterfaceTemporalAccessor, org.threeten.bp.temporal.TemporalAccessor
    public ValueRange range(TemporalField temporalField) {
        if (temporalField instanceof ChronoField) {
            if (isSupported(temporalField)) {
                ChronoField chronoField = (ChronoField) temporalField;
                int ordinal = chronoField.ordinal();
                if (ordinal == 18 || ordinal == 19 || ordinal == 21) {
                    return this.isoDate.range(temporalField);
                }
                if (ordinal != 25) {
                    return mo13054getChronology().range(chronoField);
                }
                ValueRange range = ChronoField.YEAR.range();
                return ValueRange.of(1L, getProlepticYear() <= 0 ? (-range.getMinimum()) + 1 + 1911 : range.getMaximum() - 1911);
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

    public static MinguoDate now(ZoneId zoneId) {
        return now(Clock.system(zoneId));
    }

    @Override // org.threeten.bp.chrono.ChronoLocalDate
    /* renamed from: getChronology  reason: collision with other method in class */
    public MinguoChronology mo13054getChronology() {
        return MinguoChronology.INSTANCE;
    }

    @Override // org.threeten.bp.chrono.ChronoLocalDate
    /* renamed from: getEra  reason: collision with other method in class */
    public MinguoEra mo13055getEra() {
        return (MinguoEra) super.mo13055getEra();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.threeten.bp.chrono.ChronoDateImpl
    public ChronoDateImpl<MinguoDate> plusDays(long j) {
        return with(this.isoDate.plusDays(j));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.threeten.bp.chrono.ChronoDateImpl
    public ChronoDateImpl<MinguoDate> plusMonths(long j) {
        return with(this.isoDate.plusMonths(j));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.threeten.bp.chrono.ChronoDateImpl
    public ChronoDateImpl<MinguoDate> plusYears(long j) {
        return with(this.isoDate.plusYears(j));
    }

    @Override // org.threeten.bp.chrono.ChronoDateImpl, org.threeten.bp.chrono.ChronoLocalDate
    /* renamed from: until */
    public ChronoPeriod mo12893until(ChronoLocalDate chronoLocalDate) {
        Period mo12893until = this.isoDate.mo12893until(chronoLocalDate);
        return mo13054getChronology().period(mo12893until.getYears(), mo12893until.getMonths(), mo12893until.getDays());
    }

    public static MinguoDate now(Clock clock) {
        return new MinguoDate(LocalDate.now(clock));
    }

    @Override // org.threeten.bp.chrono.ChronoLocalDate, org.threeten.bp.jdk8.DefaultInterfaceTemporal, org.threeten.bp.temporal.Temporal
    /* renamed from: minus  reason: collision with other method in class */
    public MinguoDate mo13057minus(TemporalAmount temporalAmount) {
        return (MinguoDate) super.mo13057minus(temporalAmount);
    }

    @Override // org.threeten.bp.chrono.ChronoLocalDate, org.threeten.bp.jdk8.DefaultInterfaceTemporal, org.threeten.bp.temporal.Temporal
    /* renamed from: with  reason: collision with other method in class */
    public MinguoDate mo13061with(TemporalAdjuster temporalAdjuster) {
        return (MinguoDate) super.mo13061with(temporalAdjuster);
    }

    @Override // org.threeten.bp.chrono.ChronoLocalDate, org.threeten.bp.jdk8.DefaultInterfaceTemporal, org.threeten.bp.temporal.Temporal
    /* renamed from: minus  reason: collision with other method in class */
    public MinguoDate mo13056minus(long j, TemporalUnit temporalUnit) {
        return (MinguoDate) super.mo13056minus(j, temporalUnit);
    }

    @Override // org.threeten.bp.chrono.ChronoLocalDate, org.threeten.bp.jdk8.DefaultInterfaceTemporal, org.threeten.bp.temporal.Temporal
    /* renamed from: plus  reason: collision with other method in class */
    public MinguoDate mo13059plus(TemporalAmount temporalAmount) {
        return (MinguoDate) super.mo13059plus(temporalAmount);
    }

    /* JADX WARN: Type inference failed for: r4v16, types: [org.threeten.bp.chrono.MinguoDate] */
    @Override // org.threeten.bp.chrono.ChronoLocalDate, org.threeten.bp.temporal.Temporal
    /* renamed from: with  reason: collision with other method in class */
    public MinguoDate mo13062with(TemporalField temporalField, long j) {
        if (temporalField instanceof ChronoField) {
            ChronoField chronoField = (ChronoField) temporalField;
            if (getLong(chronoField) == j) {
                return this;
            }
            switch (chronoField.ordinal()) {
                case 24:
                    mo13054getChronology().range(chronoField).checkValidValue(j, chronoField);
                    return plusMonths(j - getProlepticMonth());
                case 25:
                case 26:
                case 27:
                    int checkValidIntValue = mo13054getChronology().range(chronoField).checkValidIntValue(j, chronoField);
                    switch (chronoField.ordinal()) {
                        case 25:
                            return with(this.isoDate.withYear(getProlepticYear() >= 1 ? checkValidIntValue + 1911 : (1 - checkValidIntValue) + 1911));
                        case 26:
                            return with(this.isoDate.withYear(checkValidIntValue + 1911));
                        case 27:
                            return with(this.isoDate.withYear((1 - getProlepticYear()) + 1911));
                    }
            }
            return with(this.isoDate.mo13062with(temporalField, j));
        }
        return (MinguoDate) temporalField.adjustInto(this, j);
    }

    @Override // org.threeten.bp.chrono.ChronoDateImpl, org.threeten.bp.chrono.ChronoLocalDate, org.threeten.bp.temporal.Temporal
    /* renamed from: plus */
    public MinguoDate mo13060plus(long j, TemporalUnit temporalUnit) {
        return (MinguoDate) super.mo13060plus(j, temporalUnit);
    }

    private MinguoDate with(LocalDate localDate) {
        return localDate.equals(this.isoDate) ? this : new MinguoDate(localDate);
    }
}
