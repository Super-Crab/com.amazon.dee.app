package org.threeten.bp.chrono;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.apache.logging.log4j.util.Chars;
import org.threeten.bp.DateTimeException;
import org.threeten.bp.jdk8.Jdk8Methods;
import org.threeten.bp.temporal.ChronoField;
import org.threeten.bp.temporal.ChronoUnit;
import org.threeten.bp.temporal.Temporal;
import org.threeten.bp.temporal.TemporalAmount;
import org.threeten.bp.temporal.TemporalQueries;
import org.threeten.bp.temporal.TemporalUnit;
import org.threeten.bp.temporal.UnsupportedTemporalTypeException;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes5.dex */
public final class ChronoPeriodImpl extends ChronoPeriod implements Serializable {
    private static final long serialVersionUID = 275618735781L;
    private final Chronology chronology;
    private final int days;
    private final int months;
    private final int years;

    public ChronoPeriodImpl(Chronology chronology, int i, int i2, int i3) {
        this.chronology = chronology;
        this.years = i;
        this.months = i2;
        this.days = i3;
    }

    @Override // org.threeten.bp.chrono.ChronoPeriod, org.threeten.bp.temporal.TemporalAmount
    public Temporal addTo(Temporal temporal) {
        Jdk8Methods.requireNonNull(temporal, "temporal");
        Chronology chronology = (Chronology) temporal.query(TemporalQueries.chronology());
        if (chronology != null && !this.chronology.equals(chronology)) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Invalid chronology, required: ");
            outline107.append(this.chronology.getId());
            outline107.append(", but was: ");
            outline107.append(chronology.getId());
            throw new DateTimeException(outline107.toString());
        }
        int i = this.years;
        if (i != 0) {
            temporal = temporal.mo13060plus(i, ChronoUnit.YEARS);
        }
        int i2 = this.months;
        if (i2 != 0) {
            temporal = temporal.mo13060plus(i2, ChronoUnit.MONTHS);
        }
        int i3 = this.days;
        return i3 != 0 ? temporal.mo13060plus(i3, ChronoUnit.DAYS) : temporal;
    }

    @Override // org.threeten.bp.chrono.ChronoPeriod
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ChronoPeriodImpl)) {
            return false;
        }
        ChronoPeriodImpl chronoPeriodImpl = (ChronoPeriodImpl) obj;
        return this.years == chronoPeriodImpl.years && this.months == chronoPeriodImpl.months && this.days == chronoPeriodImpl.days && this.chronology.equals(chronoPeriodImpl.chronology);
    }

    @Override // org.threeten.bp.chrono.ChronoPeriod, org.threeten.bp.temporal.TemporalAmount
    public long get(TemporalUnit temporalUnit) {
        int i;
        if (temporalUnit == ChronoUnit.YEARS) {
            i = this.years;
        } else if (temporalUnit == ChronoUnit.MONTHS) {
            i = this.months;
        } else if (temporalUnit == ChronoUnit.DAYS) {
            i = this.days;
        } else {
            throw new UnsupportedTemporalTypeException("Unsupported unit: " + temporalUnit);
        }
        return i;
    }

    @Override // org.threeten.bp.chrono.ChronoPeriod
    public Chronology getChronology() {
        return this.chronology;
    }

    @Override // org.threeten.bp.chrono.ChronoPeriod, org.threeten.bp.temporal.TemporalAmount
    public List<TemporalUnit> getUnits() {
        return Collections.unmodifiableList(Arrays.asList(ChronoUnit.YEARS, ChronoUnit.MONTHS, ChronoUnit.DAYS));
    }

    @Override // org.threeten.bp.chrono.ChronoPeriod
    public int hashCode() {
        return Integer.rotateLeft(this.months, 8) + Integer.rotateLeft(this.years, 16) + this.chronology.hashCode() + this.days;
    }

    @Override // org.threeten.bp.chrono.ChronoPeriod
    /* renamed from: minus */
    public ChronoPeriod mo12921minus(TemporalAmount temporalAmount) {
        if (temporalAmount instanceof ChronoPeriodImpl) {
            ChronoPeriodImpl chronoPeriodImpl = (ChronoPeriodImpl) temporalAmount;
            if (chronoPeriodImpl.getChronology().equals(getChronology())) {
                return new ChronoPeriodImpl(this.chronology, Jdk8Methods.safeSubtract(this.years, chronoPeriodImpl.years), Jdk8Methods.safeSubtract(this.months, chronoPeriodImpl.months), Jdk8Methods.safeSubtract(this.days, chronoPeriodImpl.days));
            }
        }
        throw new DateTimeException("Unable to subtract amount: " + temporalAmount);
    }

    @Override // org.threeten.bp.chrono.ChronoPeriod
    /* renamed from: multipliedBy */
    public ChronoPeriod mo12922multipliedBy(int i) {
        return new ChronoPeriodImpl(this.chronology, Jdk8Methods.safeMultiply(this.years, i), Jdk8Methods.safeMultiply(this.months, i), Jdk8Methods.safeMultiply(this.days, i));
    }

    @Override // org.threeten.bp.chrono.ChronoPeriod
    /* renamed from: normalized */
    public ChronoPeriod mo12924normalized() {
        if (this.chronology.range(ChronoField.MONTH_OF_YEAR).isFixed()) {
            long maximum = (this.chronology.range(ChronoField.MONTH_OF_YEAR).getMaximum() - this.chronology.range(ChronoField.MONTH_OF_YEAR).getMinimum()) + 1;
            long j = (this.years * maximum) + this.months;
            return new ChronoPeriodImpl(this.chronology, Jdk8Methods.safeToInt(j / maximum), Jdk8Methods.safeToInt(j % maximum), this.days);
        }
        return this;
    }

    @Override // org.threeten.bp.chrono.ChronoPeriod
    /* renamed from: plus */
    public ChronoPeriod mo12925plus(TemporalAmount temporalAmount) {
        if (temporalAmount instanceof ChronoPeriodImpl) {
            ChronoPeriodImpl chronoPeriodImpl = (ChronoPeriodImpl) temporalAmount;
            if (chronoPeriodImpl.getChronology().equals(getChronology())) {
                return new ChronoPeriodImpl(this.chronology, Jdk8Methods.safeAdd(this.years, chronoPeriodImpl.years), Jdk8Methods.safeAdd(this.months, chronoPeriodImpl.months), Jdk8Methods.safeAdd(this.days, chronoPeriodImpl.days));
            }
        }
        throw new DateTimeException("Unable to add amount: " + temporalAmount);
    }

    @Override // org.threeten.bp.chrono.ChronoPeriod, org.threeten.bp.temporal.TemporalAmount
    public Temporal subtractFrom(Temporal temporal) {
        Jdk8Methods.requireNonNull(temporal, "temporal");
        Chronology chronology = (Chronology) temporal.query(TemporalQueries.chronology());
        if (chronology != null && !this.chronology.equals(chronology)) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Invalid chronology, required: ");
            outline107.append(this.chronology.getId());
            outline107.append(", but was: ");
            outline107.append(chronology.getId());
            throw new DateTimeException(outline107.toString());
        }
        int i = this.years;
        if (i != 0) {
            temporal = temporal.mo13056minus(i, ChronoUnit.YEARS);
        }
        int i2 = this.months;
        if (i2 != 0) {
            temporal = temporal.mo13056minus(i2, ChronoUnit.MONTHS);
        }
        int i3 = this.days;
        return i3 != 0 ? temporal.mo13056minus(i3, ChronoUnit.DAYS) : temporal;
    }

    @Override // org.threeten.bp.chrono.ChronoPeriod
    public String toString() {
        if (isZero()) {
            return this.chronology + " P0D";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(this.chronology);
        sb.append(Chars.SPACE);
        sb.append('P');
        int i = this.years;
        if (i != 0) {
            sb.append(i);
            sb.append('Y');
        }
        int i2 = this.months;
        if (i2 != 0) {
            sb.append(i2);
            sb.append('M');
        }
        int i3 = this.days;
        if (i3 != 0) {
            sb.append(i3);
            sb.append('D');
        }
        return sb.toString();
    }
}
