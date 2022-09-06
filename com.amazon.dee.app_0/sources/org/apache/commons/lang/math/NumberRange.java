package org.apache.commons.lang.math;

import java.io.Serializable;
import kotlinx.serialization.json.internal.JsonReaderKt;
import org.apache.commons.lang.text.StrBuilder;
/* loaded from: classes4.dex */
public final class NumberRange extends Range implements Serializable {
    private static final long serialVersionUID = 71849363892710L;
    private final Number max;
    private final Number min;
    private transient int hashCode = 0;
    private transient String toString = null;

    public NumberRange(Number number) {
        if (number != null) {
            if (number instanceof Comparable) {
                if ((number instanceof Double) && ((Double) number).isNaN()) {
                    throw new IllegalArgumentException("The number must not be NaN");
                }
                if ((number instanceof Float) && ((Float) number).isNaN()) {
                    throw new IllegalArgumentException("The number must not be NaN");
                }
                this.min = number;
                this.max = number;
                return;
            }
            throw new IllegalArgumentException("The number must implement Comparable");
        }
        throw new IllegalArgumentException("The number must not be null");
    }

    @Override // org.apache.commons.lang.math.Range
    public boolean containsNumber(Number number) {
        if (number == null) {
            return false;
        }
        if (number.getClass() != this.min.getClass()) {
            throw new IllegalArgumentException("The number must be of the same type as the range numbers");
        }
        return ((Comparable) this.min).compareTo(number) <= 0 && ((Comparable) this.max).compareTo(number) >= 0;
    }

    @Override // org.apache.commons.lang.math.Range
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof NumberRange)) {
            return false;
        }
        NumberRange numberRange = (NumberRange) obj;
        return this.min.equals(numberRange.min) && this.max.equals(numberRange.max);
    }

    @Override // org.apache.commons.lang.math.Range
    public Number getMaximumNumber() {
        return this.max;
    }

    @Override // org.apache.commons.lang.math.Range
    public Number getMinimumNumber() {
        return this.min;
    }

    @Override // org.apache.commons.lang.math.Range
    public int hashCode() {
        if (this.hashCode == 0) {
            this.hashCode = 17;
            this.hashCode = NumberRange.class.hashCode() + (this.hashCode * 37);
            this.hashCode = this.min.hashCode() + (this.hashCode * 37);
            this.hashCode = this.max.hashCode() + (this.hashCode * 37);
        }
        return this.hashCode;
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

    public NumberRange(Number number, Number number2) {
        if (number != null && number2 != null) {
            if (number.getClass() == number2.getClass()) {
                if (number instanceof Comparable) {
                    if (number instanceof Double) {
                        if (((Double) number).isNaN() || ((Double) number2).isNaN()) {
                            throw new IllegalArgumentException("The number must not be NaN");
                        }
                    } else if ((number instanceof Float) && (((Float) number).isNaN() || ((Float) number2).isNaN())) {
                        throw new IllegalArgumentException("The number must not be NaN");
                    }
                    int compareTo = ((Comparable) number).compareTo(number2);
                    if (compareTo == 0) {
                        this.min = number;
                        this.max = number;
                        return;
                    } else if (compareTo > 0) {
                        this.min = number2;
                        this.max = number;
                        return;
                    } else {
                        this.min = number;
                        this.max = number2;
                        return;
                    }
                }
                throw new IllegalArgumentException("The numbers must implement Comparable");
            }
            throw new IllegalArgumentException("The numbers must be of the same type");
        }
        throw new IllegalArgumentException("The numbers must not be null");
    }
}
