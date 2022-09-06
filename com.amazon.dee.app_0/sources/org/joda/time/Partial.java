package org.joda.time;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import kotlinx.serialization.json.internal.JsonReaderKt;
import org.apache.logging.log4j.util.Chars;
import org.joda.time.base.AbstractPartial;
import org.joda.time.field.AbstractPartialFieldProperty;
import org.joda.time.field.FieldUtils;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;
/* loaded from: classes5.dex */
public final class Partial extends AbstractPartial implements ReadablePartial, Serializable {
    private static final long serialVersionUID = 12324121189002L;
    private final Chronology iChronology;
    private transient DateTimeFormatter[] iFormatter;
    private final DateTimeFieldType[] iTypes;
    private final int[] iValues;

    /* loaded from: classes5.dex */
    public static class Property extends AbstractPartialFieldProperty implements Serializable {
        private static final long serialVersionUID = 53278362873888L;
        private final int iFieldIndex;
        private final Partial iPartial;

        Property(Partial partial, int i) {
            this.iPartial = partial;
            this.iFieldIndex = i;
        }

        public Partial addToCopy(int i) {
            return new Partial(this.iPartial, getField().add(this.iPartial, this.iFieldIndex, this.iPartial.getValues(), i));
        }

        public Partial addWrapFieldToCopy(int i) {
            return new Partial(this.iPartial, getField().addWrapField(this.iPartial, this.iFieldIndex, this.iPartial.getValues(), i));
        }

        @Override // org.joda.time.field.AbstractPartialFieldProperty
        public int get() {
            return this.iPartial.getValue(this.iFieldIndex);
        }

        @Override // org.joda.time.field.AbstractPartialFieldProperty
        public DateTimeField getField() {
            return this.iPartial.getField(this.iFieldIndex);
        }

        public Partial getPartial() {
            return this.iPartial;
        }

        @Override // org.joda.time.field.AbstractPartialFieldProperty
        protected ReadablePartial getReadablePartial() {
            return this.iPartial;
        }

        public Partial setCopy(int i) {
            return new Partial(this.iPartial, getField().set(this.iPartial, this.iFieldIndex, this.iPartial.getValues(), i));
        }

        public Partial setCopy(String str) {
            return setCopy(str, null);
        }

        public Partial setCopy(String str, Locale locale) {
            return new Partial(this.iPartial, getField().set(this.iPartial, this.iFieldIndex, this.iPartial.getValues(), str, locale));
        }

        public Partial withMaximumValue() {
            return setCopy(getMaximumValue());
        }

        public Partial withMinimumValue() {
            return setCopy(getMinimumValue());
        }
    }

    public Partial() {
        this((Chronology) null);
    }

    public Partial(Chronology chronology) {
        this.iChronology = DateTimeUtils.getChronology(chronology).withUTC();
        this.iTypes = new DateTimeFieldType[0];
        this.iValues = new int[0];
    }

    Partial(Chronology chronology, DateTimeFieldType[] dateTimeFieldTypeArr, int[] iArr) {
        this.iChronology = chronology;
        this.iTypes = dateTimeFieldTypeArr;
        this.iValues = iArr;
    }

    public Partial(DateTimeFieldType dateTimeFieldType, int i) {
        this(dateTimeFieldType, i, (Chronology) null);
    }

    public Partial(DateTimeFieldType dateTimeFieldType, int i, Chronology chronology) {
        Chronology withUTC = DateTimeUtils.getChronology(chronology).withUTC();
        this.iChronology = withUTC;
        if (dateTimeFieldType != null) {
            this.iTypes = new DateTimeFieldType[]{dateTimeFieldType};
            this.iValues = new int[]{i};
            withUTC.validate(this, this.iValues);
            return;
        }
        throw new IllegalArgumentException("The field type must not be null");
    }

    Partial(Partial partial, int[] iArr) {
        this.iChronology = partial.iChronology;
        this.iTypes = partial.iTypes;
        this.iValues = iArr;
    }

    public Partial(ReadablePartial readablePartial) {
        if (readablePartial != null) {
            this.iChronology = DateTimeUtils.getChronology(readablePartial.getChronology()).withUTC();
            this.iTypes = new DateTimeFieldType[readablePartial.size()];
            this.iValues = new int[readablePartial.size()];
            for (int i = 0; i < readablePartial.size(); i++) {
                this.iTypes[i] = readablePartial.getFieldType(i);
                this.iValues[i] = readablePartial.getValue(i);
            }
            return;
        }
        throw new IllegalArgumentException("The partial must not be null");
    }

    public Partial(DateTimeFieldType[] dateTimeFieldTypeArr, int[] iArr) {
        this(dateTimeFieldTypeArr, iArr, (Chronology) null);
    }

    public Partial(DateTimeFieldType[] dateTimeFieldTypeArr, int[] iArr, Chronology chronology) {
        Chronology withUTC = DateTimeUtils.getChronology(chronology).withUTC();
        this.iChronology = withUTC;
        if (dateTimeFieldTypeArr != null) {
            if (iArr == null) {
                throw new IllegalArgumentException("Values array must not be null");
            }
            if (iArr.length != dateTimeFieldTypeArr.length) {
                throw new IllegalArgumentException("Values array must be the same length as the types array");
            }
            if (dateTimeFieldTypeArr.length == 0) {
                this.iTypes = dateTimeFieldTypeArr;
                this.iValues = iArr;
                return;
            }
            int i = 0;
            for (int i2 = 0; i2 < dateTimeFieldTypeArr.length; i2++) {
                if (dateTimeFieldTypeArr[i2] == null) {
                    throw new IllegalArgumentException(GeneratedOutlineSupport1.outline49("Types array must not contain null: index ", i2));
                }
            }
            DurationField durationField = null;
            while (i < dateTimeFieldTypeArr.length) {
                DateTimeFieldType dateTimeFieldType = dateTimeFieldTypeArr[i];
                DurationField field = dateTimeFieldType.getDurationType().getField(this.iChronology);
                if (i > 0) {
                    if (!field.isSupported()) {
                        if (durationField.isSupported()) {
                            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Types array must be in order largest-smallest: ");
                            outline107.append(dateTimeFieldTypeArr[i - 1].getName());
                            outline107.append(" < ");
                            outline107.append(dateTimeFieldType.getName());
                            throw new IllegalArgumentException(outline107.toString());
                        }
                        StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("Types array must not contain duplicate unsupported: ");
                        outline1072.append(dateTimeFieldTypeArr[i - 1].getName());
                        outline1072.append(" and ");
                        outline1072.append(dateTimeFieldType.getName());
                        throw new IllegalArgumentException(outline1072.toString());
                    }
                    int compareTo = durationField.compareTo(field);
                    if (compareTo < 0) {
                        StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("Types array must be in order largest-smallest: ");
                        outline1073.append(dateTimeFieldTypeArr[i - 1].getName());
                        outline1073.append(" < ");
                        outline1073.append(dateTimeFieldType.getName());
                        throw new IllegalArgumentException(outline1073.toString());
                    } else if (compareTo != 0) {
                        continue;
                    } else if (durationField.equals(field)) {
                        int i3 = i - 1;
                        DurationFieldType rangeDurationType = dateTimeFieldTypeArr[i3].getRangeDurationType();
                        DurationFieldType rangeDurationType2 = dateTimeFieldType.getRangeDurationType();
                        if (rangeDurationType == null) {
                            if (rangeDurationType2 == null) {
                                StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("Types array must not contain duplicate: ");
                                outline1074.append(dateTimeFieldTypeArr[i3].getName());
                                outline1074.append(" and ");
                                outline1074.append(dateTimeFieldType.getName());
                                throw new IllegalArgumentException(outline1074.toString());
                            }
                        } else if (rangeDurationType2 == null) {
                            StringBuilder outline1075 = GeneratedOutlineSupport1.outline107("Types array must be in order largest-smallest: ");
                            outline1075.append(dateTimeFieldTypeArr[i3].getName());
                            outline1075.append(" < ");
                            outline1075.append(dateTimeFieldType.getName());
                            throw new IllegalArgumentException(outline1075.toString());
                        } else {
                            DurationField field2 = rangeDurationType.getField(this.iChronology);
                            DurationField field3 = rangeDurationType2.getField(this.iChronology);
                            if (field2.compareTo(field3) < 0) {
                                StringBuilder outline1076 = GeneratedOutlineSupport1.outline107("Types array must be in order largest-smallest: ");
                                outline1076.append(dateTimeFieldTypeArr[i3].getName());
                                outline1076.append(" < ");
                                outline1076.append(dateTimeFieldType.getName());
                                throw new IllegalArgumentException(outline1076.toString());
                            } else if (field2.compareTo(field3) == 0) {
                                StringBuilder outline1077 = GeneratedOutlineSupport1.outline107("Types array must not contain duplicate: ");
                                outline1077.append(dateTimeFieldTypeArr[i3].getName());
                                outline1077.append(" and ");
                                outline1077.append(dateTimeFieldType.getName());
                                throw new IllegalArgumentException(outline1077.toString());
                            }
                        }
                    } else if (durationField.isSupported() && durationField.getType() != DurationFieldType.YEARS_TYPE) {
                        StringBuilder outline1078 = GeneratedOutlineSupport1.outline107("Types array must be in order largest-smallest, for year-based fields, years is defined as being largest: ");
                        outline1078.append(dateTimeFieldTypeArr[i - 1].getName());
                        outline1078.append(" < ");
                        outline1078.append(dateTimeFieldType.getName());
                        throw new IllegalArgumentException(outline1078.toString());
                    }
                }
                i++;
                durationField = field;
            }
            this.iTypes = (DateTimeFieldType[]) dateTimeFieldTypeArr.clone();
            withUTC.validate(this, iArr);
            this.iValues = (int[]) iArr.clone();
            return;
        }
        throw new IllegalArgumentException("Types array must not be null");
    }

    @Override // org.joda.time.ReadablePartial
    public Chronology getChronology() {
        return this.iChronology;
    }

    @Override // org.joda.time.base.AbstractPartial
    protected DateTimeField getField(int i, Chronology chronology) {
        return this.iTypes[i].getField(chronology);
    }

    @Override // org.joda.time.base.AbstractPartial, org.joda.time.ReadablePartial
    public DateTimeFieldType getFieldType(int i) {
        return this.iTypes[i];
    }

    @Override // org.joda.time.base.AbstractPartial
    public DateTimeFieldType[] getFieldTypes() {
        return (DateTimeFieldType[]) this.iTypes.clone();
    }

    public DateTimeFormatter getFormatter() {
        DateTimeFormatter[] dateTimeFormatterArr = this.iFormatter;
        if (dateTimeFormatterArr == null) {
            if (size() == 0) {
                return null;
            }
            dateTimeFormatterArr = new DateTimeFormatter[2];
            try {
                ArrayList arrayList = new ArrayList(Arrays.asList(this.iTypes));
                dateTimeFormatterArr[0] = ISODateTimeFormat.forFields(arrayList, true, false);
                if (arrayList.size() == 0) {
                    dateTimeFormatterArr[1] = dateTimeFormatterArr[0];
                }
            } catch (IllegalArgumentException unused) {
            }
            this.iFormatter = dateTimeFormatterArr;
        }
        return dateTimeFormatterArr[0];
    }

    @Override // org.joda.time.ReadablePartial
    public int getValue(int i) {
        return this.iValues[i];
    }

    @Override // org.joda.time.base.AbstractPartial
    public int[] getValues() {
        return (int[]) this.iValues.clone();
    }

    public boolean isMatch(ReadableInstant readableInstant) {
        long instantMillis = DateTimeUtils.getInstantMillis(readableInstant);
        Chronology instantChronology = DateTimeUtils.getInstantChronology(readableInstant);
        int i = 0;
        while (true) {
            DateTimeFieldType[] dateTimeFieldTypeArr = this.iTypes;
            if (i < dateTimeFieldTypeArr.length) {
                if (dateTimeFieldTypeArr[i].getField(instantChronology).get(instantMillis) != this.iValues[i]) {
                    return false;
                }
                i++;
            } else {
                return true;
            }
        }
    }

    public boolean isMatch(ReadablePartial readablePartial) {
        if (readablePartial != null) {
            int i = 0;
            while (true) {
                DateTimeFieldType[] dateTimeFieldTypeArr = this.iTypes;
                if (i >= dateTimeFieldTypeArr.length) {
                    return true;
                }
                if (readablePartial.get(dateTimeFieldTypeArr[i]) != this.iValues[i]) {
                    return false;
                }
                i++;
            }
        } else {
            throw new IllegalArgumentException("The partial must not be null");
        }
    }

    public Partial minus(ReadablePeriod readablePeriod) {
        return withPeriodAdded(readablePeriod, -1);
    }

    public Partial plus(ReadablePeriod readablePeriod) {
        return withPeriodAdded(readablePeriod, 1);
    }

    public Property property(DateTimeFieldType dateTimeFieldType) {
        return new Property(this, indexOfSupported(dateTimeFieldType));
    }

    @Override // org.joda.time.ReadablePartial
    public int size() {
        return this.iTypes.length;
    }

    @Override // org.joda.time.ReadablePartial
    public String toString() {
        DateTimeFormatter[] dateTimeFormatterArr = this.iFormatter;
        if (dateTimeFormatterArr == null) {
            getFormatter();
            dateTimeFormatterArr = this.iFormatter;
            if (dateTimeFormatterArr == null) {
                return toStringList();
            }
        }
        DateTimeFormatter dateTimeFormatter = dateTimeFormatterArr[1];
        return dateTimeFormatter == null ? toStringList() : dateTimeFormatter.print(this);
    }

    public String toString(String str) {
        return str == null ? toString() : DateTimeFormat.forPattern(str).print(this);
    }

    public String toString(String str, Locale locale) {
        return str == null ? toString() : DateTimeFormat.forPattern(str).withLocale(locale).print(this);
    }

    public String toStringList() {
        int size = size();
        StringBuilder sb = new StringBuilder(size * 20);
        sb.append(JsonReaderKt.BEGIN_LIST);
        for (int i = 0; i < size; i++) {
            if (i > 0) {
                sb.append(JsonReaderKt.COMMA);
                sb.append(Chars.SPACE);
            }
            sb.append(this.iTypes[i].getName());
            sb.append(Chars.EQ);
            sb.append(this.iValues[i]);
        }
        sb.append(JsonReaderKt.END_LIST);
        return sb.toString();
    }

    public Partial with(DateTimeFieldType dateTimeFieldType, int i) {
        int i2;
        int compareTo;
        if (dateTimeFieldType != null) {
            int indexOf = indexOf(dateTimeFieldType);
            if (indexOf != -1) {
                return i == getValue(indexOf) ? this : new Partial(this, getField(indexOf).set(this, indexOf, getValues(), i));
            }
            DateTimeFieldType[] dateTimeFieldTypeArr = new DateTimeFieldType[this.iTypes.length + 1];
            int[] iArr = new int[dateTimeFieldTypeArr.length];
            DurationField field = dateTimeFieldType.getDurationType().getField(this.iChronology);
            if (field.isSupported()) {
                i2 = 0;
                while (true) {
                    DateTimeFieldType[] dateTimeFieldTypeArr2 = this.iTypes;
                    if (i2 >= dateTimeFieldTypeArr2.length) {
                        break;
                    }
                    DateTimeFieldType dateTimeFieldType2 = dateTimeFieldTypeArr2[i2];
                    DurationField field2 = dateTimeFieldType2.getDurationType().getField(this.iChronology);
                    if (field2.isSupported() && ((compareTo = field.compareTo(field2)) > 0 || (compareTo == 0 && (dateTimeFieldType.getRangeDurationType() == null || (dateTimeFieldType2.getRangeDurationType() != null && dateTimeFieldType.getRangeDurationType().getField(this.iChronology).compareTo(dateTimeFieldType2.getRangeDurationType().getField(this.iChronology)) > 0))))) {
                        break;
                    }
                    i2++;
                }
            } else {
                i2 = 0;
            }
            System.arraycopy(this.iTypes, 0, dateTimeFieldTypeArr, 0, i2);
            System.arraycopy(this.iValues, 0, iArr, 0, i2);
            dateTimeFieldTypeArr[i2] = dateTimeFieldType;
            iArr[i2] = i;
            int i3 = i2 + 1;
            System.arraycopy(this.iTypes, i2, dateTimeFieldTypeArr, i3, (dateTimeFieldTypeArr.length - i2) - 1);
            System.arraycopy(this.iValues, i2, iArr, i3, (iArr.length - i2) - 1);
            Partial partial = new Partial(dateTimeFieldTypeArr, iArr, this.iChronology);
            this.iChronology.validate(partial, iArr);
            return partial;
        }
        throw new IllegalArgumentException("The field type must not be null");
    }

    public Partial withChronologyRetainFields(Chronology chronology) {
        Chronology withUTC = DateTimeUtils.getChronology(chronology).withUTC();
        if (withUTC == getChronology()) {
            return this;
        }
        Partial partial = new Partial(withUTC, this.iTypes, this.iValues);
        withUTC.validate(partial, this.iValues);
        return partial;
    }

    public Partial withField(DateTimeFieldType dateTimeFieldType, int i) {
        int indexOfSupported = indexOfSupported(dateTimeFieldType);
        if (i == getValue(indexOfSupported)) {
            return this;
        }
        return new Partial(this, getField(indexOfSupported).set(this, indexOfSupported, getValues(), i));
    }

    public Partial withFieldAddWrapped(DurationFieldType durationFieldType, int i) {
        int indexOfSupported = indexOfSupported(durationFieldType);
        if (i == 0) {
            return this;
        }
        return new Partial(this, getField(indexOfSupported).addWrapPartial(this, indexOfSupported, getValues(), i));
    }

    public Partial withFieldAdded(DurationFieldType durationFieldType, int i) {
        int indexOfSupported = indexOfSupported(durationFieldType);
        if (i == 0) {
            return this;
        }
        return new Partial(this, getField(indexOfSupported).add(this, indexOfSupported, getValues(), i));
    }

    public Partial withPeriodAdded(ReadablePeriod readablePeriod, int i) {
        if (readablePeriod == null || i == 0) {
            return this;
        }
        int[] values = getValues();
        for (int i2 = 0; i2 < readablePeriod.size(); i2++) {
            int indexOf = indexOf(readablePeriod.getFieldType(i2));
            if (indexOf >= 0) {
                values = getField(indexOf).add(this, indexOf, values, FieldUtils.safeMultiply(readablePeriod.getValue(i2), i));
            }
        }
        return new Partial(this, values);
    }

    public Partial without(DateTimeFieldType dateTimeFieldType) {
        int indexOf = indexOf(dateTimeFieldType);
        if (indexOf != -1) {
            DateTimeFieldType[] dateTimeFieldTypeArr = new DateTimeFieldType[size() - 1];
            int[] iArr = new int[size() - 1];
            System.arraycopy(this.iTypes, 0, dateTimeFieldTypeArr, 0, indexOf);
            int i = indexOf + 1;
            System.arraycopy(this.iTypes, i, dateTimeFieldTypeArr, indexOf, dateTimeFieldTypeArr.length - indexOf);
            System.arraycopy(this.iValues, 0, iArr, 0, indexOf);
            System.arraycopy(this.iValues, i, iArr, indexOf, iArr.length - indexOf);
            Partial partial = new Partial(this.iChronology, dateTimeFieldTypeArr, iArr);
            this.iChronology.validate(partial, iArr);
            return partial;
        }
        return this;
    }
}
