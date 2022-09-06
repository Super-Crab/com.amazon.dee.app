package org.joda.time.convert;

import com.android.tools.r8.GeneratedOutlineSupport1;
import org.joda.time.Chronology;
import org.joda.time.DateTimeUtils;
import org.joda.time.DateTimeZone;
import org.joda.time.PeriodType;
import org.joda.time.ReadablePartial;
import org.joda.time.chrono.ISOChronology;
import org.joda.time.format.DateTimeFormatter;
/* loaded from: classes5.dex */
public abstract class AbstractConverter implements Converter {
    public Chronology getChronology(Object obj, Chronology chronology) {
        return DateTimeUtils.getChronology(chronology);
    }

    public Chronology getChronology(Object obj, DateTimeZone dateTimeZone) {
        return ISOChronology.getInstance(dateTimeZone);
    }

    public long getInstantMillis(Object obj, Chronology chronology) {
        return DateTimeUtils.currentTimeMillis();
    }

    public int[] getPartialValues(ReadablePartial readablePartial, Object obj, Chronology chronology) {
        return chronology.get(readablePartial, getInstantMillis(obj, chronology));
    }

    public int[] getPartialValues(ReadablePartial readablePartial, Object obj, Chronology chronology, DateTimeFormatter dateTimeFormatter) {
        return getPartialValues(readablePartial, obj, chronology);
    }

    public PeriodType getPeriodType(Object obj) {
        return PeriodType.standard();
    }

    public boolean isReadableInterval(Object obj, Chronology chronology) {
        return false;
    }

    public String toString() {
        return GeneratedOutlineSupport1.outline91(GeneratedOutlineSupport1.outline107("Converter["), getSupportedType() == null ? "null" : getSupportedType().getName(), "]");
    }
}
