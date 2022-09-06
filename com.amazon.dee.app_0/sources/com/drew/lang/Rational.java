package com.drew.lang;

import com.amazon.comms.ringservice.webrtc.FrostVideoEffectController;
import com.drew.lang.annotations.NotNull;
import com.drew.lang.annotations.Nullable;
import java.io.Serializable;
/* loaded from: classes2.dex */
public class Rational extends Number implements Comparable<Rational>, Serializable {
    private static final long serialVersionUID = 510688928138848770L;
    private final long _denominator;
    private final long _numerator;

    public Rational(long j, long j2) {
        this._numerator = j;
        this._denominator = j2;
    }

    private static long GCD(long j, long j2) {
        int i;
        if (j < 0) {
            j = -j;
        }
        if (j2 < 0) {
            j2 = -j2;
        }
        while (true) {
            i = (j > 0L ? 1 : (j == 0L ? 0 : -1));
            if (i == 0 || j2 == 0) {
                break;
            } else if (j > j2) {
                j %= j2;
            } else {
                j2 %= j;
            }
        }
        return i == 0 ? j2 : j;
    }

    @Override // java.lang.Number
    public final byte byteValue() {
        return (byte) doubleValue();
    }

    @Override // java.lang.Comparable
    public int compareTo(@NotNull Rational rational) {
        return Double.compare(doubleValue(), rational.doubleValue());
    }

    @Override // java.lang.Number
    public double doubleValue() {
        long j = this._numerator;
        return j == 0 ? FrostVideoEffectController.VIDEO_STRENGTH_CLEAR : j / this._denominator;
    }

    public boolean equals(Rational rational) {
        return rational.doubleValue() == doubleValue();
    }

    public boolean equals(@Nullable Object obj) {
        return obj != null && (obj instanceof Rational) && doubleValue() == ((Rational) obj).doubleValue();
    }

    public boolean equalsExact(Rational rational) {
        return getDenominator() == rational.getDenominator() && getNumerator() == rational.getNumerator();
    }

    @Override // java.lang.Number
    public float floatValue() {
        long j = this._numerator;
        if (j == 0) {
            return 0.0f;
        }
        return ((float) j) / ((float) this._denominator);
    }

    public final long getDenominator() {
        return this._denominator;
    }

    public final long getNumerator() {
        return this._numerator;
    }

    @NotNull
    public Rational getReciprocal() {
        return new Rational(this._denominator, this._numerator);
    }

    @NotNull
    public Rational getSimplifiedInstance() {
        long GCD = GCD(this._numerator, this._denominator);
        return new Rational(this._numerator / GCD, this._denominator / GCD);
    }

    public int hashCode() {
        return (((int) this._denominator) * 23) + ((int) this._numerator);
    }

    @Override // java.lang.Number
    public final int intValue() {
        return (int) doubleValue();
    }

    public boolean isInteger() {
        long j = this._denominator;
        return j == 1 || (j != 0 && this._numerator % j == 0) || (this._denominator == 0 && this._numerator == 0);
    }

    public boolean isZero() {
        return this._numerator == 0 || this._denominator == 0;
    }

    @Override // java.lang.Number
    public final long longValue() {
        return (long) doubleValue();
    }

    @Override // java.lang.Number
    public final short shortValue() {
        return (short) doubleValue();
    }

    @NotNull
    public String toSimpleString(boolean z) {
        if (this._denominator != 0 || this._numerator == 0) {
            if (isInteger()) {
                return Integer.toString(intValue());
            }
            long j = this._numerator;
            if (j != 1) {
                long j2 = this._denominator;
                if (j2 % j == 0) {
                    return new Rational(1L, j2 / j).toSimpleString(z);
                }
            }
            Rational simplifiedInstance = getSimplifiedInstance();
            if (z) {
                String d = Double.toString(simplifiedInstance.doubleValue());
                if (d.length() < 5) {
                    return d;
                }
            }
            return simplifiedInstance.toString();
        }
        return toString();
    }

    @NotNull
    public String toString() {
        return this._numerator + "/" + this._denominator;
    }
}
