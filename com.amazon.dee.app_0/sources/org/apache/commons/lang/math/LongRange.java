package org.apache.commons.lang.math;

import java.io.Serializable;
import kotlinx.serialization.json.internal.JsonReaderKt;
import org.apache.commons.lang.text.StrBuilder;
/* loaded from: classes4.dex */
public final class LongRange extends Range implements Serializable {
    private static final long serialVersionUID = 71849363892720L;
    private transient int hashCode;
    private final long max;
    private transient Long maxObject;
    private final long min;
    private transient Long minObject;
    private transient String toString;

    public LongRange(long j) {
        this.minObject = null;
        this.maxObject = null;
        this.hashCode = 0;
        this.toString = null;
        this.min = j;
        this.max = j;
    }

    @Override // org.apache.commons.lang.math.Range
    public boolean containsLong(long j) {
        return j >= this.min && j <= this.max;
    }

    @Override // org.apache.commons.lang.math.Range
    public boolean containsNumber(Number number) {
        if (number == null) {
            return false;
        }
        return containsLong(number.longValue());
    }

    @Override // org.apache.commons.lang.math.Range
    public boolean containsRange(Range range) {
        return range != null && containsLong(range.getMinimumLong()) && containsLong(range.getMaximumLong());
    }

    @Override // org.apache.commons.lang.math.Range
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof LongRange)) {
            return false;
        }
        LongRange longRange = (LongRange) obj;
        return this.min == longRange.min && this.max == longRange.max;
    }

    @Override // org.apache.commons.lang.math.Range
    public double getMaximumDouble() {
        return this.max;
    }

    @Override // org.apache.commons.lang.math.Range
    public float getMaximumFloat() {
        return (float) this.max;
    }

    @Override // org.apache.commons.lang.math.Range
    public int getMaximumInteger() {
        return (int) this.max;
    }

    @Override // org.apache.commons.lang.math.Range
    public long getMaximumLong() {
        return this.max;
    }

    @Override // org.apache.commons.lang.math.Range
    public Number getMaximumNumber() {
        if (this.maxObject == null) {
            this.maxObject = new Long(this.max);
        }
        return this.maxObject;
    }

    @Override // org.apache.commons.lang.math.Range
    public double getMinimumDouble() {
        return this.min;
    }

    @Override // org.apache.commons.lang.math.Range
    public float getMinimumFloat() {
        return (float) this.min;
    }

    @Override // org.apache.commons.lang.math.Range
    public int getMinimumInteger() {
        return (int) this.min;
    }

    @Override // org.apache.commons.lang.math.Range
    public long getMinimumLong() {
        return this.min;
    }

    @Override // org.apache.commons.lang.math.Range
    public Number getMinimumNumber() {
        if (this.minObject == null) {
            this.minObject = new Long(this.min);
        }
        return this.minObject;
    }

    @Override // org.apache.commons.lang.math.Range
    public int hashCode() {
        if (this.hashCode == 0) {
            this.hashCode = 17;
            this.hashCode = LongRange.class.hashCode() + (this.hashCode * 37);
            long j = this.min;
            this.hashCode = (this.hashCode * 37) + ((int) (j ^ (j >> 32)));
            long j2 = this.max;
            this.hashCode = (this.hashCode * 37) + ((int) (j2 ^ (j2 >> 32)));
        }
        return this.hashCode;
    }

    @Override // org.apache.commons.lang.math.Range
    public boolean overlapsRange(Range range) {
        if (range == null) {
            return false;
        }
        return range.containsLong(this.min) || range.containsLong(this.max) || containsLong(range.getMinimumLong());
    }

    public long[] toArray() {
        long[] jArr = new long[(int) ((this.max - this.min) + 1)];
        for (int i = 0; i < jArr.length; i++) {
            jArr[i] = this.min + i;
        }
        return jArr;
    }

    @Override // org.apache.commons.lang.math.Range
    public String toString() {
        if (this.toString == null) {
            StrBuilder strBuilder = new StrBuilder(32);
            strBuilder.append("Range[");
            strBuilder.append(this.min);
            strBuilder.append(JsonReaderKt.COMMA);
            strBuilder.append(this.max);
            strBuilder.append(JsonReaderKt.END_LIST);
            this.toString = strBuilder.toString();
        }
        return this.toString;
    }

    public LongRange(Number number) {
        this.minObject = null;
        this.maxObject = null;
        this.hashCode = 0;
        this.toString = null;
        if (number != null) {
            this.min = number.longValue();
            this.max = number.longValue();
            if (!(number instanceof Long)) {
                return;
            }
            Long l = (Long) number;
            this.minObject = l;
            this.maxObject = l;
            return;
        }
        throw new IllegalArgumentException("The number must not be null");
    }

    public LongRange(long j, long j2) {
        this.minObject = null;
        this.maxObject = null;
        this.hashCode = 0;
        this.toString = null;
        if (j2 < j) {
            this.min = j2;
            this.max = j;
            return;
        }
        this.min = j;
        this.max = j2;
    }

    public LongRange(Number number, Number number2) {
        this.minObject = null;
        this.maxObject = null;
        this.hashCode = 0;
        this.toString = null;
        if (number != null && number2 != null) {
            long longValue = number.longValue();
            long longValue2 = number2.longValue();
            if (longValue2 < longValue) {
                this.min = longValue2;
                this.max = longValue;
                if (number2 instanceof Long) {
                    this.minObject = (Long) number2;
                }
                if (!(number instanceof Long)) {
                    return;
                }
                this.maxObject = (Long) number;
                return;
            }
            this.min = longValue;
            this.max = longValue2;
            if (number instanceof Long) {
                this.minObject = (Long) number;
            }
            if (!(number2 instanceof Long)) {
                return;
            }
            this.maxObject = (Long) number2;
            return;
        }
        throw new IllegalArgumentException("The numbers must not be null");
    }
}
