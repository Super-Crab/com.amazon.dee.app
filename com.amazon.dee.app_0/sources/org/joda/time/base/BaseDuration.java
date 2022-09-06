package org.joda.time.base;

import java.io.Serializable;
import org.joda.time.Chronology;
import org.joda.time.DateTimeUtils;
import org.joda.time.Interval;
import org.joda.time.Period;
import org.joda.time.PeriodType;
import org.joda.time.ReadableDuration;
import org.joda.time.ReadableInstant;
import org.joda.time.convert.ConverterManager;
import org.joda.time.field.FieldUtils;
/* loaded from: classes5.dex */
public abstract class BaseDuration extends AbstractDuration implements ReadableDuration, Serializable {
    private static final long serialVersionUID = 2581698638990L;
    private volatile long iMillis;

    /* JADX INFO: Access modifiers changed from: protected */
    public BaseDuration(long j) {
        this.iMillis = j;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public BaseDuration(long j, long j2) {
        this.iMillis = FieldUtils.safeSubtract(j2, j);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public BaseDuration(Object obj) {
        this.iMillis = ConverterManager.getInstance().getDurationConverter(obj).getDurationMillis(obj);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public BaseDuration(ReadableInstant readableInstant, ReadableInstant readableInstant2) {
        long safeSubtract;
        if (readableInstant == readableInstant2) {
            safeSubtract = 0;
        } else {
            safeSubtract = FieldUtils.safeSubtract(DateTimeUtils.getInstantMillis(readableInstant2), DateTimeUtils.getInstantMillis(readableInstant));
        }
        this.iMillis = safeSubtract;
    }

    @Override // org.joda.time.ReadableDuration
    public long getMillis() {
        return this.iMillis;
    }

    protected void setMillis(long j) {
        this.iMillis = j;
    }

    public Interval toIntervalFrom(ReadableInstant readableInstant) {
        return new Interval(readableInstant, this);
    }

    public Interval toIntervalTo(ReadableInstant readableInstant) {
        return new Interval(this, readableInstant);
    }

    public Period toPeriod(Chronology chronology) {
        return new Period(getMillis(), chronology);
    }

    public Period toPeriod(PeriodType periodType) {
        return new Period(getMillis(), periodType);
    }

    public Period toPeriod(PeriodType periodType, Chronology chronology) {
        return new Period(getMillis(), periodType, chronology);
    }

    public Period toPeriodFrom(ReadableInstant readableInstant) {
        return new Period(readableInstant, this);
    }

    public Period toPeriodFrom(ReadableInstant readableInstant, PeriodType periodType) {
        return new Period(readableInstant, this, periodType);
    }

    public Period toPeriodTo(ReadableInstant readableInstant) {
        return new Period(this, readableInstant);
    }

    public Period toPeriodTo(ReadableInstant readableInstant, PeriodType periodType) {
        return new Period(this, readableInstant, periodType);
    }
}
