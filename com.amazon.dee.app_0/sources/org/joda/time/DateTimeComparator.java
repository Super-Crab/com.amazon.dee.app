package org.joda.time;

import java.io.Serializable;
import java.util.Comparator;
import org.joda.time.convert.ConverterManager;
import org.joda.time.convert.InstantConverter;
/* loaded from: classes5.dex */
public class DateTimeComparator implements Comparator<Object>, Serializable {
    private static final DateTimeComparator ALL_INSTANCE = new DateTimeComparator(null, null);
    private static final DateTimeComparator DATE_INSTANCE = new DateTimeComparator(DateTimeFieldType.dayOfYear(), null);
    private static final DateTimeComparator TIME_INSTANCE = new DateTimeComparator(null, DateTimeFieldType.dayOfYear());
    private static final long serialVersionUID = -6097339773320178364L;
    private final DateTimeFieldType iLowerLimit;
    private final DateTimeFieldType iUpperLimit;

    protected DateTimeComparator(DateTimeFieldType dateTimeFieldType, DateTimeFieldType dateTimeFieldType2) {
        this.iLowerLimit = dateTimeFieldType;
        this.iUpperLimit = dateTimeFieldType2;
    }

    public static DateTimeComparator getDateOnlyInstance() {
        return DATE_INSTANCE;
    }

    public static DateTimeComparator getInstance() {
        return ALL_INSTANCE;
    }

    public static DateTimeComparator getInstance(DateTimeFieldType dateTimeFieldType) {
        return getInstance(dateTimeFieldType, null);
    }

    public static DateTimeComparator getInstance(DateTimeFieldType dateTimeFieldType, DateTimeFieldType dateTimeFieldType2) {
        return (dateTimeFieldType == null && dateTimeFieldType2 == null) ? ALL_INSTANCE : (dateTimeFieldType == DateTimeFieldType.dayOfYear() && dateTimeFieldType2 == null) ? DATE_INSTANCE : (dateTimeFieldType == null && dateTimeFieldType2 == DateTimeFieldType.dayOfYear()) ? TIME_INSTANCE : new DateTimeComparator(dateTimeFieldType, dateTimeFieldType2);
    }

    public static DateTimeComparator getTimeOnlyInstance() {
        return TIME_INSTANCE;
    }

    private Object readResolve() {
        return getInstance(this.iLowerLimit, this.iUpperLimit);
    }

    @Override // java.util.Comparator
    public int compare(Object obj, Object obj2) {
        InstantConverter instantConverter = ConverterManager.getInstance().getInstantConverter(obj);
        Chronology chronology = instantConverter.getChronology(obj, (Chronology) null);
        long instantMillis = instantConverter.getInstantMillis(obj, chronology);
        InstantConverter instantConverter2 = ConverterManager.getInstance().getInstantConverter(obj2);
        Chronology chronology2 = instantConverter2.getChronology(obj2, (Chronology) null);
        long instantMillis2 = instantConverter2.getInstantMillis(obj2, chronology2);
        DateTimeFieldType dateTimeFieldType = this.iLowerLimit;
        if (dateTimeFieldType != null) {
            instantMillis = dateTimeFieldType.getField(chronology).roundFloor(instantMillis);
            instantMillis2 = this.iLowerLimit.getField(chronology2).roundFloor(instantMillis2);
        }
        DateTimeFieldType dateTimeFieldType2 = this.iUpperLimit;
        if (dateTimeFieldType2 != null) {
            instantMillis = dateTimeFieldType2.getField(chronology).remainder(instantMillis);
            instantMillis2 = this.iUpperLimit.getField(chronology2).remainder(instantMillis2);
        }
        int i = (instantMillis > instantMillis2 ? 1 : (instantMillis == instantMillis2 ? 0 : -1));
        if (i < 0) {
            return -1;
        }
        return i > 0 ? 1 : 0;
    }

    @Override // java.util.Comparator
    public boolean equals(Object obj) {
        DateTimeFieldType dateTimeFieldType;
        DateTimeFieldType dateTimeFieldType2;
        if (obj instanceof DateTimeComparator) {
            DateTimeComparator dateTimeComparator = (DateTimeComparator) obj;
            if (this.iLowerLimit != dateTimeComparator.getLowerLimit() && ((dateTimeFieldType2 = this.iLowerLimit) == null || !dateTimeFieldType2.equals(dateTimeComparator.getLowerLimit()))) {
                return false;
            }
            return this.iUpperLimit == dateTimeComparator.getUpperLimit() || ((dateTimeFieldType = this.iUpperLimit) != null && dateTimeFieldType.equals(dateTimeComparator.getUpperLimit()));
        }
        return false;
    }

    public DateTimeFieldType getLowerLimit() {
        return this.iLowerLimit;
    }

    public DateTimeFieldType getUpperLimit() {
        return this.iUpperLimit;
    }

    public int hashCode() {
        DateTimeFieldType dateTimeFieldType = this.iLowerLimit;
        int i = 0;
        int hashCode = dateTimeFieldType == null ? 0 : dateTimeFieldType.hashCode();
        DateTimeFieldType dateTimeFieldType2 = this.iUpperLimit;
        if (dateTimeFieldType2 != null) {
            i = dateTimeFieldType2.hashCode();
        }
        return (i * 123) + hashCode;
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x002d, code lost:
        if (r1 == null) goto L6;
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x0030, code lost:
        r4 = r1.getName();
     */
    /* JADX WARN: Code restructure failed: missing block: B:5:0x0012, code lost:
        if (r1 == null) goto L6;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.String toString() {
        /*
            r5 = this;
            org.joda.time.DateTimeFieldType r0 = r5.iLowerLimit
            org.joda.time.DateTimeFieldType r1 = r5.iUpperLimit
            java.lang.String r2 = "]"
            java.lang.String r3 = "DateTimeComparator["
            java.lang.String r4 = ""
            if (r0 != r1) goto L15
            java.lang.StringBuilder r0 = com.android.tools.r8.GeneratedOutlineSupport1.outline107(r3)
            org.joda.time.DateTimeFieldType r1 = r5.iLowerLimit
            if (r1 != 0) goto L30
            goto L34
        L15:
            java.lang.StringBuilder r0 = com.android.tools.r8.GeneratedOutlineSupport1.outline107(r3)
            org.joda.time.DateTimeFieldType r1 = r5.iLowerLimit
            if (r1 != 0) goto L1f
            r1 = r4
            goto L23
        L1f:
            java.lang.String r1 = r1.getName()
        L23:
            r0.append(r1)
            java.lang.String r1 = "-"
            r0.append(r1)
            org.joda.time.DateTimeFieldType r1 = r5.iUpperLimit
            if (r1 != 0) goto L30
            goto L34
        L30:
            java.lang.String r4 = r1.getName()
        L34:
            java.lang.String r0 = com.android.tools.r8.GeneratedOutlineSupport1.outline91(r0, r4, r2)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.joda.time.DateTimeComparator.toString():java.lang.String");
    }
}
