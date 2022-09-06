package com.google.common.math;

import com.amazon.android.codahalemetricreporter.JsonReportFormat;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import com.google.common.primitives.Booleans;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.Iterator;
@GwtCompatible(emulated = true)
/* loaded from: classes3.dex */
public final class DoubleMath {
    @VisibleForTesting
    static final int MAX_FACTORIAL = 170;
    private static final double MAX_INT_AS_DOUBLE = 2.147483647E9d;
    private static final double MAX_LONG_AS_DOUBLE_PLUS_ONE = 9.223372036854776E18d;
    private static final double MIN_INT_AS_DOUBLE = -2.147483648E9d;
    private static final double MIN_LONG_AS_DOUBLE = -9.223372036854776E18d;
    private static final double LN_2 = Math.log(2.0d);
    @VisibleForTesting
    static final double[] everySixteenthFactorial = {1.0d, 2.0922789888E13d, 2.631308369336935E35d, 1.2413915592536073E61d, 1.2688693218588417E89d, 7.156945704626381E118d, 9.916779348709496E149d, 1.974506857221074E182d, 3.856204823625804E215d, 5.5502938327393044E249d, 4.7147236359920616E284d};

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.google.common.math.DoubleMath$1  reason: invalid class name */
    /* loaded from: classes3.dex */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$java$math$RoundingMode = new int[RoundingMode.values().length];

        static {
            try {
                $SwitchMap$java$math$RoundingMode[RoundingMode.UNNECESSARY.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$java$math$RoundingMode[RoundingMode.FLOOR.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$java$math$RoundingMode[RoundingMode.CEILING.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$java$math$RoundingMode[RoundingMode.DOWN.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$java$math$RoundingMode[RoundingMode.UP.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$java$math$RoundingMode[RoundingMode.HALF_EVEN.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$java$math$RoundingMode[RoundingMode.HALF_UP.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$java$math$RoundingMode[RoundingMode.HALF_DOWN.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
        }
    }

    private DoubleMath() {
    }

    @CanIgnoreReturnValue
    @GwtIncompatible
    private static double checkFinite(double d) {
        Preconditions.checkArgument(DoubleUtils.isFinite(d));
        return d;
    }

    public static double factorial(int i) {
        MathPreconditions.checkNonNegative(JsonReportFormat.COUNT, i);
        if (i > 170) {
            return Double.POSITIVE_INFINITY;
        }
        double d = 1.0d;
        int i2 = i & (-16);
        while (true) {
            i2++;
            if (i2 > i) {
                return d * everySixteenthFactorial[i >> 4];
            }
            d *= i2;
        }
    }

    public static int fuzzyCompare(double d, double d2, double d3) {
        if (fuzzyEquals(d, d2, d3)) {
            return 0;
        }
        if (d < d2) {
            return -1;
        }
        if (d <= d2) {
            return Booleans.compare(Double.isNaN(d), Double.isNaN(d2));
        }
        return 1;
    }

    public static boolean fuzzyEquals(double d, double d2, double d3) {
        MathPreconditions.checkNonNegative("tolerance", d3);
        return Math.copySign(d - d2, 1.0d) <= d3 || d == d2 || (Double.isNaN(d) && Double.isNaN(d2));
    }

    @GwtIncompatible
    public static boolean isMathematicalInteger(double d) {
        return DoubleUtils.isFinite(d) && (d == 0.0d || 52 - Long.numberOfTrailingZeros(DoubleUtils.getSignificand(d)) <= Math.getExponent(d));
    }

    @GwtIncompatible
    public static boolean isPowerOfTwo(double d) {
        if (d <= 0.0d || !DoubleUtils.isFinite(d)) {
            return false;
        }
        long significand = DoubleUtils.getSignificand(d);
        return (significand & (significand - 1)) == 0;
    }

    public static double log2(double d) {
        return Math.log(d) / LN_2;
    }

    @GwtIncompatible
    @Deprecated
    public static double mean(double... dArr) {
        Preconditions.checkArgument(dArr.length > 0, "Cannot take mean of 0 values");
        double checkFinite = checkFinite(dArr[0]);
        long j = 1;
        for (int i = 1; i < dArr.length; i++) {
            checkFinite(dArr[i]);
            j++;
            checkFinite += (dArr[i] - checkFinite) / j;
        }
        return checkFinite;
    }

    @GwtIncompatible
    static double roundIntermediate(double d, RoundingMode roundingMode) {
        if (DoubleUtils.isFinite(d)) {
            switch (AnonymousClass1.$SwitchMap$java$math$RoundingMode[roundingMode.ordinal()]) {
                case 1:
                    MathPreconditions.checkRoundingUnnecessary(isMathematicalInteger(d));
                    return d;
                case 2:
                    return (d >= 0.0d || isMathematicalInteger(d)) ? d : ((long) d) - 1;
                case 3:
                    return (d <= 0.0d || isMathematicalInteger(d)) ? d : ((long) d) + 1;
                case 4:
                    return d;
                case 5:
                    if (isMathematicalInteger(d)) {
                        return d;
                    }
                    return ((long) d) + (d > 0.0d ? 1 : -1);
                case 6:
                    return Math.rint(d);
                case 7:
                    double rint = Math.rint(d);
                    return Math.abs(d - rint) == 0.5d ? Math.copySign(0.5d, d) + d : rint;
                case 8:
                    double rint2 = Math.rint(d);
                    return Math.abs(d - rint2) == 0.5d ? d : rint2;
                default:
                    throw new AssertionError();
            }
        }
        throw new ArithmeticException("input is infinite or NaN");
    }

    @GwtIncompatible
    public static BigInteger roundToBigInteger(double d, RoundingMode roundingMode) {
        double roundIntermediate = roundIntermediate(d, roundingMode);
        boolean z = true;
        boolean z2 = MIN_LONG_AS_DOUBLE - roundIntermediate < 1.0d;
        if (roundIntermediate >= MAX_LONG_AS_DOUBLE_PLUS_ONE) {
            z = false;
        }
        if (z2 & z) {
            return BigInteger.valueOf((long) roundIntermediate);
        }
        BigInteger shiftLeft = BigInteger.valueOf(DoubleUtils.getSignificand(roundIntermediate)).shiftLeft(Math.getExponent(roundIntermediate) - 52);
        return roundIntermediate < 0.0d ? shiftLeft.negate() : shiftLeft;
    }

    @GwtIncompatible
    public static int roundToInt(double d, RoundingMode roundingMode) {
        double roundIntermediate = roundIntermediate(d, roundingMode);
        boolean z = true;
        boolean z2 = roundIntermediate > -2.147483649E9d;
        if (roundIntermediate >= 2.147483648E9d) {
            z = false;
        }
        MathPreconditions.checkInRangeForRoundingInputs(z2 & z, d, roundingMode);
        return (int) roundIntermediate;
    }

    @GwtIncompatible
    public static long roundToLong(double d, RoundingMode roundingMode) {
        double roundIntermediate = roundIntermediate(d, roundingMode);
        boolean z = true;
        boolean z2 = MIN_LONG_AS_DOUBLE - roundIntermediate < 1.0d;
        if (roundIntermediate >= MAX_LONG_AS_DOUBLE_PLUS_ONE) {
            z = false;
        }
        MathPreconditions.checkInRangeForRoundingInputs(z2 & z, d, roundingMode);
        return (long) roundIntermediate;
    }

    @GwtIncompatible
    public static int log2(double d, RoundingMode roundingMode) {
        boolean isPowerOfTwo;
        boolean z = false;
        Preconditions.checkArgument(d > 0.0d && DoubleUtils.isFinite(d), "x must be positive and finite");
        int exponent = Math.getExponent(d);
        if (!DoubleUtils.isNormal(d)) {
            return log2(d * 4.503599627370496E15d, roundingMode) - 52;
        }
        switch (AnonymousClass1.$SwitchMap$java$math$RoundingMode[roundingMode.ordinal()]) {
            case 1:
                MathPreconditions.checkRoundingUnnecessary(isPowerOfTwo(d));
                break;
            case 2:
                break;
            case 3:
                z = !isPowerOfTwo(d);
                break;
            case 4:
                if (exponent < 0) {
                    z = true;
                }
                isPowerOfTwo = isPowerOfTwo(d);
                z &= !isPowerOfTwo;
                break;
            case 5:
                if (exponent >= 0) {
                    z = true;
                }
                isPowerOfTwo = isPowerOfTwo(d);
                z &= !isPowerOfTwo;
                break;
            case 6:
            case 7:
            case 8:
                double scaleNormalize = DoubleUtils.scaleNormalize(d);
                if (scaleNormalize * scaleNormalize > 2.0d) {
                    z = true;
                    break;
                }
                break;
            default:
                throw new AssertionError();
        }
        return z ? exponent + 1 : exponent;
    }

    @Deprecated
    public static double mean(int... iArr) {
        Preconditions.checkArgument(iArr.length > 0, "Cannot take mean of 0 values");
        long j = 0;
        for (int i : iArr) {
            j += i;
        }
        return j / iArr.length;
    }

    @Deprecated
    public static double mean(long... jArr) {
        Preconditions.checkArgument(jArr.length > 0, "Cannot take mean of 0 values");
        double d = jArr[0];
        long j = 1;
        for (int i = 1; i < jArr.length; i++) {
            j++;
            d += (jArr[i] - d) / j;
        }
        return d;
    }

    @GwtIncompatible
    @Deprecated
    public static double mean(Iterable<? extends Number> iterable) {
        return mean(iterable.iterator());
    }

    @GwtIncompatible
    @Deprecated
    public static double mean(Iterator<? extends Number> it2) {
        Preconditions.checkArgument(it2.hasNext(), "Cannot take mean of 0 values");
        double checkFinite = checkFinite(it2.next().doubleValue());
        long j = 1;
        while (it2.hasNext()) {
            j++;
            checkFinite += (checkFinite(it2.next().doubleValue()) - checkFinite) / j;
        }
        return checkFinite;
    }
}
