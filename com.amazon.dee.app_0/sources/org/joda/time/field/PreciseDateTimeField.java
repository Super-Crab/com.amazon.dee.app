package org.joda.time.field;

import org.joda.time.DateTimeFieldType;
import org.joda.time.DurationField;
/* loaded from: classes5.dex */
public class PreciseDateTimeField extends PreciseDurationDateTimeField {
    private static final long serialVersionUID = -5586801265774496376L;
    private final int iRange;
    private final DurationField iRangeField;

    public PreciseDateTimeField(DateTimeFieldType dateTimeFieldType, DurationField durationField, DurationField durationField2) {
        super(dateTimeFieldType, durationField);
        if (durationField2.isPrecise()) {
            this.iRange = (int) (durationField2.getUnitMillis() / getUnitMillis());
            if (this.iRange < 2) {
                throw new IllegalArgumentException("The effective range must be at least 2");
            }
            this.iRangeField = durationField2;
            return;
        }
        throw new IllegalArgumentException("Range duration field must be precise");
    }

    @Override // org.joda.time.field.BaseDateTimeField, org.joda.time.DateTimeField
    public long addWrapField(long j, int i) {
        int i2 = get(j);
        return (getUnitMillis() * (FieldUtils.getWrappedValue(i2, i, getMinimumValue(), getMaximumValue()) - i2)) + j;
    }

    @Override // org.joda.time.field.BaseDateTimeField, org.joda.time.DateTimeField
    public int get(long j) {
        return j >= 0 ? (int) ((j / getUnitMillis()) % this.iRange) : (this.iRange - 1) + ((int) (((j + 1) / getUnitMillis()) % this.iRange));
    }

    @Override // org.joda.time.field.BaseDateTimeField, org.joda.time.DateTimeField
    public int getMaximumValue() {
        return this.iRange - 1;
    }

    public int getRange() {
        return this.iRange;
    }

    @Override // org.joda.time.field.BaseDateTimeField, org.joda.time.DateTimeField
    public DurationField getRangeDurationField() {
        return this.iRangeField;
    }

    @Override // org.joda.time.field.PreciseDurationDateTimeField, org.joda.time.field.BaseDateTimeField, org.joda.time.DateTimeField
    public long set(long j, int i) {
        FieldUtils.verifyValueBounds(this, i, getMinimumValue(), getMaximumValue());
        return ((i - get(j)) * this.iUnitMillis) + j;
    }
}
