package org.apache.commons.lang.math;

import java.io.Serializable;
import kotlinx.serialization.json.internal.JsonReaderKt;
import org.apache.commons.lang.text.StrBuilder;
/* loaded from: classes4.dex */
public final class DoubleRange extends Range implements Serializable {
    private static final long serialVersionUID = 71849363892740L;
    private transient int hashCode;
    private final double max;
    private transient Double maxObject;
    private final double min;
    private transient Double minObject;
    private transient String toString;

    public DoubleRange(double d) {
        this.minObject = null;
        this.maxObject = null;
        this.hashCode = 0;
        this.toString = null;
        if (!Double.isNaN(d)) {
            this.min = d;
            this.max = d;
            return;
        }
        throw new IllegalArgumentException("The number must not be NaN");
    }

    @Override // org.apache.commons.lang.math.Range
    public boolean containsDouble(double d) {
        return d >= this.min && d <= this.max;
    }

    @Override // org.apache.commons.lang.math.Range
    public boolean containsNumber(Number number) {
        if (number == null) {
            return false;
        }
        return containsDouble(number.doubleValue());
    }

    @Override // org.apache.commons.lang.math.Range
    public boolean containsRange(Range range) {
        return range != null && containsDouble(range.getMinimumDouble()) && containsDouble(range.getMaximumDouble());
    }

    @Override // org.apache.commons.lang.math.Range
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof DoubleRange)) {
            return false;
        }
        DoubleRange doubleRange = (DoubleRange) obj;
        return Double.doubleToLongBits(this.min) == Double.doubleToLongBits(doubleRange.min) && Double.doubleToLongBits(this.max) == Double.doubleToLongBits(doubleRange.max);
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
        return (long) this.max;
    }

    @Override // org.apache.commons.lang.math.Range
    public Number getMaximumNumber() {
        if (this.maxObject == null) {
            this.maxObject = new Double(this.max);
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
        return (long) this.min;
    }

    @Override // org.apache.commons.lang.math.Range
    public Number getMinimumNumber() {
        if (this.minObject == null) {
            this.minObject = new Double(this.min);
        }
        return this.minObject;
    }

    @Override // org.apache.commons.lang.math.Range
    public int hashCode() {
        if (this.hashCode == 0) {
            this.hashCode = 17;
            this.hashCode = DoubleRange.class.hashCode() + (this.hashCode * 37);
            long doubleToLongBits = Double.doubleToLongBits(this.min);
            this.hashCode = (this.hashCode * 37) + ((int) (doubleToLongBits ^ (doubleToLongBits >> 32)));
            long doubleToLongBits2 = Double.doubleToLongBits(this.max);
            this.hashCode = (this.hashCode * 37) + ((int) (doubleToLongBits2 ^ (doubleToLongBits2 >> 32)));
        }
        return this.hashCode;
    }

    @Override // org.apache.commons.lang.math.Range
    public boolean overlapsRange(Range range) {
        if (range == null) {
            return false;
        }
        return range.containsDouble(this.min) || range.containsDouble(this.max) || containsDouble(range.getMinimumDouble());
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

    public DoubleRange(Number number) {
        this.minObject = null;
        this.maxObject = null;
        this.hashCode = 0;
        this.toString = null;
        if (number != null) {
            this.min = number.doubleValue();
            this.max = number.doubleValue();
            if (!Double.isNaN(this.min) && !Double.isNaN(this.max)) {
                if (!(number instanceof Double)) {
                    return;
                }
                Double d = (Double) number;
                this.minObject = d;
                this.maxObject = d;
                return;
            }
            throw new IllegalArgumentException("The number must not be NaN");
        }
        throw new IllegalArgumentException("The number must not be null");
    }

    public DoubleRange(double d, double d2) {
        this.minObject = null;
        this.maxObject = null;
        this.hashCode = 0;
        this.toString = null;
        if (Double.isNaN(d) || Double.isNaN(d2)) {
            throw new IllegalArgumentException("The numbers must not be NaN");
        }
        if (d2 < d) {
            this.min = d2;
            this.max = d;
            return;
        }
        this.min = d;
        this.max = d2;
    }

    public DoubleRange(Number number, Number number2) {
        this.minObject = null;
        this.maxObject = null;
        this.hashCode = 0;
        this.toString = null;
        if (number != null && number2 != null) {
            double doubleValue = number.doubleValue();
            double doubleValue2 = number2.doubleValue();
            if (Double.isNaN(doubleValue) || Double.isNaN(doubleValue2)) {
                throw new IllegalArgumentException("The numbers must not be NaN");
            }
            if (doubleValue2 < doubleValue) {
                this.min = doubleValue2;
                this.max = doubleValue;
                if (number2 instanceof Double) {
                    this.minObject = (Double) number2;
                }
                if (!(number instanceof Double)) {
                    return;
                }
                this.maxObject = (Double) number;
                return;
            }
            this.min = doubleValue;
            this.max = doubleValue2;
            if (number instanceof Double) {
                this.minObject = (Double) number;
            }
            if (!(number2 instanceof Double)) {
                return;
            }
            this.maxObject = (Double) number2;
            return;
        }
        throw new IllegalArgumentException("The numbers must not be null");
    }
}
