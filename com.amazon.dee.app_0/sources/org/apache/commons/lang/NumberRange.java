package org.apache.commons.lang;

import com.amazon.comms.ringservice.webrtc.FrostVideoEffectController;
import org.apache.commons.lang.text.StrBuilder;
/* loaded from: classes4.dex */
public final class NumberRange {
    private final Number max;
    private final Number min;

    public NumberRange(Number number) {
        if (number != null) {
            this.min = number;
            this.max = number;
            return;
        }
        throw new NullPointerException("The number must not be null");
    }

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

    public Number getMaximum() {
        return this.max;
    }

    public Number getMinimum() {
        return this.min;
    }

    public int hashCode() {
        return this.max.hashCode() + ((this.min.hashCode() + 629) * 37);
    }

    public boolean includesNumber(Number number) {
        return number != null && this.min.doubleValue() <= number.doubleValue() && this.max.doubleValue() >= number.doubleValue();
    }

    public boolean includesRange(NumberRange numberRange) {
        return numberRange != null && includesNumber(numberRange.min) && includesNumber(numberRange.max);
    }

    public boolean overlaps(NumberRange numberRange) {
        if (numberRange == null) {
            return false;
        }
        return numberRange.includesNumber(this.min) || numberRange.includesNumber(this.max) || includesRange(numberRange);
    }

    public String toString() {
        StrBuilder strBuilder = new StrBuilder();
        if (this.min.doubleValue() < FrostVideoEffectController.VIDEO_STRENGTH_CLEAR) {
            strBuilder.append('(').append(this.min).append(')');
        } else {
            strBuilder.append(this.min);
        }
        strBuilder.append('-');
        if (this.max.doubleValue() < FrostVideoEffectController.VIDEO_STRENGTH_CLEAR) {
            strBuilder.append('(').append(this.max).append(')');
        } else {
            strBuilder.append(this.max);
        }
        return strBuilder.toString();
    }

    public NumberRange(Number number, Number number2) {
        if (number != null) {
            if (number2 != null) {
                if (number2.doubleValue() < number.doubleValue()) {
                    this.max = number;
                    this.min = number;
                    return;
                }
                this.min = number;
                this.max = number2;
                return;
            }
            throw new NullPointerException("The maximum value must not be null");
        }
        throw new NullPointerException("The minimum value must not be null");
    }
}
