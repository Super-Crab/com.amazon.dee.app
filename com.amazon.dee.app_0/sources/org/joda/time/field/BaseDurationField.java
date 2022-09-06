package org.joda.time.field;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
import kotlinx.serialization.json.internal.JsonReaderKt;
import org.joda.time.DurationField;
import org.joda.time.DurationFieldType;
/* loaded from: classes5.dex */
public abstract class BaseDurationField extends DurationField implements Serializable {
    private static final long serialVersionUID = -2554245107589433218L;
    private final DurationFieldType iType;

    /* JADX INFO: Access modifiers changed from: protected */
    public BaseDurationField(DurationFieldType durationFieldType) {
        if (durationFieldType != null) {
            this.iType = durationFieldType;
            return;
        }
        throw new IllegalArgumentException("The type must not be null");
    }

    @Override // java.lang.Comparable
    public int compareTo(DurationField durationField) {
        int i = (getUnitMillis() > durationField.getUnitMillis() ? 1 : (getUnitMillis() == durationField.getUnitMillis() ? 0 : -1));
        if (i == 0) {
            return 0;
        }
        return i < 0 ? -1 : 1;
    }

    @Override // org.joda.time.DurationField
    public int getDifference(long j, long j2) {
        return FieldUtils.safeToInt(getDifferenceAsLong(j, j2));
    }

    @Override // org.joda.time.DurationField
    public long getMillis(int i) {
        return getUnitMillis() * i;
    }

    @Override // org.joda.time.DurationField
    public long getMillis(long j) {
        return FieldUtils.safeMultiply(j, getUnitMillis());
    }

    @Override // org.joda.time.DurationField
    public final String getName() {
        return this.iType.getName();
    }

    @Override // org.joda.time.DurationField
    public final DurationFieldType getType() {
        return this.iType;
    }

    @Override // org.joda.time.DurationField
    public int getValue(long j) {
        return FieldUtils.safeToInt(getValueAsLong(j));
    }

    @Override // org.joda.time.DurationField
    public int getValue(long j, long j2) {
        return FieldUtils.safeToInt(getValueAsLong(j, j2));
    }

    @Override // org.joda.time.DurationField
    public long getValueAsLong(long j) {
        return j / getUnitMillis();
    }

    @Override // org.joda.time.DurationField
    public final boolean isSupported() {
        return true;
    }

    @Override // org.joda.time.DurationField
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("DurationField[");
        outline107.append(getName());
        outline107.append(JsonReaderKt.END_LIST);
        return outline107.toString();
    }
}
