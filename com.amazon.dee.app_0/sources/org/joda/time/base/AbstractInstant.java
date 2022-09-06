package org.joda.time.base;

import java.util.Date;
import org.joda.time.Chronology;
import org.joda.time.DateTime;
import org.joda.time.DateTimeField;
import org.joda.time.DateTimeFieldType;
import org.joda.time.DateTimeUtils;
import org.joda.time.DateTimeZone;
import org.joda.time.Instant;
import org.joda.time.MutableDateTime;
import org.joda.time.ReadableInstant;
import org.joda.time.chrono.ISOChronology;
import org.joda.time.field.FieldUtils;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;
/* loaded from: classes5.dex */
public abstract class AbstractInstant implements ReadableInstant {
    @Override // java.lang.Comparable
    public int compareTo(ReadableInstant readableInstant) {
        if (this == readableInstant) {
            return 0;
        }
        int i = (getMillis() > readableInstant.getMillis() ? 1 : (getMillis() == readableInstant.getMillis() ? 0 : -1));
        if (i == 0) {
            return 0;
        }
        return i < 0 ? -1 : 1;
    }

    @Override // org.joda.time.ReadableInstant
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ReadableInstant)) {
            return false;
        }
        ReadableInstant readableInstant = (ReadableInstant) obj;
        return getMillis() == readableInstant.getMillis() && FieldUtils.equals(getChronology(), readableInstant.getChronology());
    }

    public int get(DateTimeField dateTimeField) {
        if (dateTimeField != null) {
            return dateTimeField.get(getMillis());
        }
        throw new IllegalArgumentException("The DateTimeField must not be null");
    }

    @Override // org.joda.time.ReadableInstant
    public int get(DateTimeFieldType dateTimeFieldType) {
        if (dateTimeFieldType != null) {
            return dateTimeFieldType.getField(getChronology()).get(getMillis());
        }
        throw new IllegalArgumentException("The DateTimeFieldType must not be null");
    }

    @Override // org.joda.time.ReadableInstant
    public DateTimeZone getZone() {
        return getChronology().getZone();
    }

    @Override // org.joda.time.ReadableInstant
    public int hashCode() {
        return getChronology().hashCode() + ((int) (getMillis() ^ (getMillis() >>> 32)));
    }

    public boolean isAfter(long j) {
        return getMillis() > j;
    }

    @Override // org.joda.time.ReadableInstant
    public boolean isAfter(ReadableInstant readableInstant) {
        return isAfter(DateTimeUtils.getInstantMillis(readableInstant));
    }

    public boolean isAfterNow() {
        return isAfter(DateTimeUtils.currentTimeMillis());
    }

    public boolean isBefore(long j) {
        return getMillis() < j;
    }

    @Override // org.joda.time.ReadableInstant
    public boolean isBefore(ReadableInstant readableInstant) {
        return isBefore(DateTimeUtils.getInstantMillis(readableInstant));
    }

    public boolean isBeforeNow() {
        return isBefore(DateTimeUtils.currentTimeMillis());
    }

    public boolean isEqual(long j) {
        return getMillis() == j;
    }

    @Override // org.joda.time.ReadableInstant
    public boolean isEqual(ReadableInstant readableInstant) {
        return isEqual(DateTimeUtils.getInstantMillis(readableInstant));
    }

    public boolean isEqualNow() {
        return isEqual(DateTimeUtils.currentTimeMillis());
    }

    @Override // org.joda.time.ReadableInstant
    public boolean isSupported(DateTimeFieldType dateTimeFieldType) {
        if (dateTimeFieldType == null) {
            return false;
        }
        return dateTimeFieldType.getField(getChronology()).isSupported();
    }

    public Date toDate() {
        return new Date(getMillis());
    }

    public DateTime toDateTime() {
        return new DateTime(getMillis(), getZone());
    }

    public DateTime toDateTime(Chronology chronology) {
        return new DateTime(getMillis(), chronology);
    }

    public DateTime toDateTime(DateTimeZone dateTimeZone) {
        return new DateTime(getMillis(), DateTimeUtils.getChronology(getChronology()).withZone(dateTimeZone));
    }

    public DateTime toDateTimeISO() {
        return new DateTime(getMillis(), ISOChronology.getInstance(getZone()));
    }

    @Override // org.joda.time.ReadableInstant
    public Instant toInstant() {
        return new Instant(getMillis());
    }

    public MutableDateTime toMutableDateTime() {
        return new MutableDateTime(getMillis(), getZone());
    }

    public MutableDateTime toMutableDateTime(Chronology chronology) {
        return new MutableDateTime(getMillis(), chronology);
    }

    public MutableDateTime toMutableDateTime(DateTimeZone dateTimeZone) {
        return new MutableDateTime(getMillis(), DateTimeUtils.getChronology(getChronology()).withZone(dateTimeZone));
    }

    public MutableDateTime toMutableDateTimeISO() {
        return new MutableDateTime(getMillis(), ISOChronology.getInstance(getZone()));
    }

    @Override // org.joda.time.ReadableInstant
    public String toString() {
        return ISODateTimeFormat.dateTime().print(this);
    }

    public String toString(DateTimeFormatter dateTimeFormatter) {
        return dateTimeFormatter == null ? toString() : dateTimeFormatter.print(this);
    }
}
