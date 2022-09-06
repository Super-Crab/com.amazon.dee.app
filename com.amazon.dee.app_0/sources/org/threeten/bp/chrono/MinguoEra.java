package org.threeten.bp.chrono;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.Locale;
import org.threeten.bp.DateTimeException;
import org.threeten.bp.format.DateTimeFormatterBuilder;
import org.threeten.bp.format.TextStyle;
import org.threeten.bp.temporal.ChronoField;
import org.threeten.bp.temporal.ChronoUnit;
import org.threeten.bp.temporal.Temporal;
import org.threeten.bp.temporal.TemporalField;
import org.threeten.bp.temporal.TemporalQueries;
import org.threeten.bp.temporal.TemporalQuery;
import org.threeten.bp.temporal.UnsupportedTemporalTypeException;
import org.threeten.bp.temporal.ValueRange;
/* loaded from: classes5.dex */
public enum MinguoEra implements Era {
    BEFORE_ROC,
    ROC;

    public static MinguoEra of(int i) {
        if (i != 0) {
            if (i == 1) {
                return ROC;
            }
            throw new DateTimeException(GeneratedOutlineSupport1.outline49("Invalid era: ", i));
        }
        return BEFORE_ROC;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static MinguoEra readExternal(DataInput dataInput) throws IOException {
        return of(dataInput.readByte());
    }

    private Object writeReplace() {
        return new Ser((byte) 6, this);
    }

    @Override // org.threeten.bp.temporal.TemporalAdjuster
    public Temporal adjustInto(Temporal temporal) {
        return temporal.mo13062with(ChronoField.ERA, getValue());
    }

    @Override // org.threeten.bp.temporal.TemporalAccessor
    public int get(TemporalField temporalField) {
        if (temporalField == ChronoField.ERA) {
            return getValue();
        }
        return range(temporalField).checkValidIntValue(getLong(temporalField), temporalField);
    }

    @Override // org.threeten.bp.chrono.Era
    public String getDisplayName(TextStyle textStyle, Locale locale) {
        return new DateTimeFormatterBuilder().appendText(ChronoField.ERA, textStyle).toFormatter(locale).format(this);
    }

    @Override // org.threeten.bp.temporal.TemporalAccessor
    public long getLong(TemporalField temporalField) {
        if (temporalField == ChronoField.ERA) {
            return getValue();
        }
        if (!(temporalField instanceof ChronoField)) {
            return temporalField.getFrom(this);
        }
        throw new UnsupportedTemporalTypeException(GeneratedOutlineSupport1.outline82("Unsupported field: ", temporalField));
    }

    @Override // org.threeten.bp.chrono.Era
    public int getValue() {
        return ordinal();
    }

    @Override // org.threeten.bp.temporal.TemporalAccessor
    public boolean isSupported(TemporalField temporalField) {
        return temporalField instanceof ChronoField ? temporalField == ChronoField.ERA : temporalField != null && temporalField.isSupportedBy(this);
    }

    @Override // org.threeten.bp.temporal.TemporalAccessor
    public <R> R query(TemporalQuery<R> temporalQuery) {
        if (temporalQuery == TemporalQueries.precision()) {
            return (R) ChronoUnit.ERAS;
        }
        if (temporalQuery != TemporalQueries.chronology() && temporalQuery != TemporalQueries.zone() && temporalQuery != TemporalQueries.zoneId() && temporalQuery != TemporalQueries.offset() && temporalQuery != TemporalQueries.localDate() && temporalQuery != TemporalQueries.localTime()) {
            return temporalQuery.mo13072queryFrom(this);
        }
        return null;
    }

    @Override // org.threeten.bp.temporal.TemporalAccessor
    public ValueRange range(TemporalField temporalField) {
        if (temporalField == ChronoField.ERA) {
            return temporalField.range();
        }
        if (!(temporalField instanceof ChronoField)) {
            return temporalField.rangeRefinedBy(this);
        }
        throw new UnsupportedTemporalTypeException(GeneratedOutlineSupport1.outline82("Unsupported field: ", temporalField));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void writeExternal(DataOutput dataOutput) throws IOException {
        dataOutput.writeByte(getValue());
    }
}
