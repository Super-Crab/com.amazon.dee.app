package org.joda.time;

import java.io.Serializable;
import org.joda.time.base.BaseInterval;
import org.joda.time.field.FieldUtils;
/* loaded from: classes5.dex */
public class MutableInterval extends BaseInterval implements ReadWritableInterval, Cloneable, Serializable {
    private static final long serialVersionUID = -5982824024992428470L;

    public MutableInterval() {
        super(0L, 0L, null);
    }

    public MutableInterval(long j, long j2) {
        super(j, j2, null);
    }

    public MutableInterval(long j, long j2, Chronology chronology) {
        super(j, j2, chronology);
    }

    public MutableInterval(Object obj) {
        super(obj, (Chronology) null);
    }

    public MutableInterval(Object obj, Chronology chronology) {
        super(obj, chronology);
    }

    public MutableInterval(ReadableDuration readableDuration, ReadableInstant readableInstant) {
        super(readableDuration, readableInstant);
    }

    public MutableInterval(ReadableInstant readableInstant, ReadableDuration readableDuration) {
        super(readableInstant, readableDuration);
    }

    public MutableInterval(ReadableInstant readableInstant, ReadableInstant readableInstant2) {
        super(readableInstant, readableInstant2);
    }

    public MutableInterval(ReadableInstant readableInstant, ReadablePeriod readablePeriod) {
        super(readableInstant, readablePeriod);
    }

    public MutableInterval(ReadablePeriod readablePeriod, ReadableInstant readableInstant) {
        super(readablePeriod, readableInstant);
    }

    public static MutableInterval parse(String str) {
        return new MutableInterval(str);
    }

    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException unused) {
            throw new InternalError("Clone error");
        }
    }

    public MutableInterval copy() {
        return (MutableInterval) clone();
    }

    @Override // org.joda.time.ReadWritableInterval
    public void setChronology(Chronology chronology) {
        super.setInterval(getStartMillis(), getEndMillis(), chronology);
    }

    public void setDurationAfterStart(long j) {
        setEndMillis(FieldUtils.safeAdd(getStartMillis(), j));
    }

    @Override // org.joda.time.ReadWritableInterval
    public void setDurationAfterStart(ReadableDuration readableDuration) {
        setEndMillis(FieldUtils.safeAdd(getStartMillis(), DateTimeUtils.getDurationMillis(readableDuration)));
    }

    public void setDurationBeforeEnd(long j) {
        setStartMillis(FieldUtils.safeAdd(getEndMillis(), -j));
    }

    @Override // org.joda.time.ReadWritableInterval
    public void setDurationBeforeEnd(ReadableDuration readableDuration) {
        setStartMillis(FieldUtils.safeAdd(getEndMillis(), -DateTimeUtils.getDurationMillis(readableDuration)));
    }

    @Override // org.joda.time.ReadWritableInterval
    public void setEnd(ReadableInstant readableInstant) {
        super.setInterval(getStartMillis(), DateTimeUtils.getInstantMillis(readableInstant), getChronology());
    }

    @Override // org.joda.time.ReadWritableInterval
    public void setEndMillis(long j) {
        super.setInterval(getStartMillis(), j, getChronology());
    }

    @Override // org.joda.time.ReadWritableInterval
    public void setInterval(long j, long j2) {
        super.setInterval(j, j2, getChronology());
    }

    @Override // org.joda.time.ReadWritableInterval
    public void setInterval(ReadableInstant readableInstant, ReadableInstant readableInstant2) {
        if (readableInstant != null || readableInstant2 != null) {
            super.setInterval(DateTimeUtils.getInstantMillis(readableInstant), DateTimeUtils.getInstantMillis(readableInstant2), DateTimeUtils.getInstantChronology(readableInstant));
            return;
        }
        long currentTimeMillis = DateTimeUtils.currentTimeMillis();
        setInterval(currentTimeMillis, currentTimeMillis);
    }

    @Override // org.joda.time.ReadWritableInterval
    public void setInterval(ReadableInterval readableInterval) {
        if (readableInterval != null) {
            super.setInterval(readableInterval.getStartMillis(), readableInterval.getEndMillis(), readableInterval.getChronology());
            return;
        }
        throw new IllegalArgumentException("Interval must not be null");
    }

    @Override // org.joda.time.ReadWritableInterval
    public void setPeriodAfterStart(ReadablePeriod readablePeriod) {
        setEndMillis(readablePeriod == null ? getStartMillis() : getChronology().add(readablePeriod, getStartMillis(), 1));
    }

    @Override // org.joda.time.ReadWritableInterval
    public void setPeriodBeforeEnd(ReadablePeriod readablePeriod) {
        setStartMillis(readablePeriod == null ? getEndMillis() : getChronology().add(readablePeriod, getEndMillis(), -1));
    }

    @Override // org.joda.time.ReadWritableInterval
    public void setStart(ReadableInstant readableInstant) {
        super.setInterval(DateTimeUtils.getInstantMillis(readableInstant), getEndMillis(), getChronology());
    }

    @Override // org.joda.time.ReadWritableInterval
    public void setStartMillis(long j) {
        super.setInterval(j, getEndMillis(), getChronology());
    }
}
