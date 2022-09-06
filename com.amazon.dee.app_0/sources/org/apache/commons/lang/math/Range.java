package org.apache.commons.lang.math;

import kotlinx.serialization.json.internal.JsonReaderKt;
import org.apache.commons.lang.text.StrBuilder;
/* loaded from: classes4.dex */
public abstract class Range {
    public boolean containsDouble(Number number) {
        if (number == null) {
            return false;
        }
        return containsDouble(number.doubleValue());
    }

    public boolean containsFloat(Number number) {
        if (number == null) {
            return false;
        }
        return containsFloat(number.floatValue());
    }

    public boolean containsInteger(Number number) {
        if (number == null) {
            return false;
        }
        return containsInteger(number.intValue());
    }

    public boolean containsLong(Number number) {
        if (number == null) {
            return false;
        }
        return containsLong(number.longValue());
    }

    public abstract boolean containsNumber(Number number);

    public boolean containsRange(Range range) {
        return range != null && containsNumber(range.getMinimumNumber()) && containsNumber(range.getMaximumNumber());
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }
        Range range = (Range) obj;
        return getMinimumNumber().equals(range.getMinimumNumber()) && getMaximumNumber().equals(range.getMaximumNumber());
    }

    public double getMaximumDouble() {
        return getMaximumNumber().doubleValue();
    }

    public float getMaximumFloat() {
        return getMaximumNumber().floatValue();
    }

    public int getMaximumInteger() {
        return getMaximumNumber().intValue();
    }

    public long getMaximumLong() {
        return getMaximumNumber().longValue();
    }

    public abstract Number getMaximumNumber();

    public double getMinimumDouble() {
        return getMinimumNumber().doubleValue();
    }

    public float getMinimumFloat() {
        return getMinimumNumber().floatValue();
    }

    public int getMinimumInteger() {
        return getMinimumNumber().intValue();
    }

    public long getMinimumLong() {
        return getMinimumNumber().longValue();
    }

    public abstract Number getMinimumNumber();

    public int hashCode() {
        int hashCode = getMinimumNumber().hashCode();
        return getMaximumNumber().hashCode() + ((hashCode + ((getClass().hashCode() + 629) * 37)) * 37);
    }

    public boolean overlapsRange(Range range) {
        if (range == null) {
            return false;
        }
        return range.containsNumber(getMinimumNumber()) || range.containsNumber(getMaximumNumber()) || containsNumber(range.getMinimumNumber());
    }

    public String toString() {
        StrBuilder strBuilder = new StrBuilder(32);
        strBuilder.append("Range[");
        strBuilder.append(getMinimumNumber());
        strBuilder.append(JsonReaderKt.COMMA);
        strBuilder.append(getMaximumNumber());
        strBuilder.append(JsonReaderKt.END_LIST);
        return strBuilder.toString();
    }

    public boolean containsDouble(double d) {
        return NumberUtils.compare(getMinimumDouble(), d) <= 0 && NumberUtils.compare(getMaximumDouble(), d) >= 0;
    }

    public boolean containsFloat(float f) {
        return NumberUtils.compare(getMinimumFloat(), f) <= 0 && NumberUtils.compare(getMaximumFloat(), f) >= 0;
    }

    public boolean containsInteger(int i) {
        return i >= getMinimumInteger() && i <= getMaximumInteger();
    }

    public boolean containsLong(long j) {
        return j >= getMinimumLong() && j <= getMaximumLong();
    }
}
