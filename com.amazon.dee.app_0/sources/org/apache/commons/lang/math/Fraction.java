package org.apache.commons.lang.math;

import java.math.BigInteger;
import org.apache.commons.lang.text.StrBuilder;
import org.apache.logging.log4j.util.Chars;
/* loaded from: classes4.dex */
public final class Fraction extends Number implements Comparable {
    private static final long serialVersionUID = 65382027393090L;
    private final int denominator;
    private final int numerator;
    public static final Fraction ZERO = new Fraction(0, 1);
    public static final Fraction ONE = new Fraction(1, 1);
    public static final Fraction ONE_HALF = new Fraction(1, 2);
    public static final Fraction ONE_THIRD = new Fraction(1, 3);
    public static final Fraction TWO_THIRDS = new Fraction(2, 3);
    public static final Fraction ONE_QUARTER = new Fraction(1, 4);
    public static final Fraction TWO_QUARTERS = new Fraction(2, 4);
    public static final Fraction THREE_QUARTERS = new Fraction(3, 4);
    public static final Fraction ONE_FIFTH = new Fraction(1, 5);
    public static final Fraction TWO_FIFTHS = new Fraction(2, 5);
    public static final Fraction THREE_FIFTHS = new Fraction(3, 5);
    public static final Fraction FOUR_FIFTHS = new Fraction(4, 5);
    private transient int hashCode = 0;
    private transient String toString = null;
    private transient String toProperString = null;

    private Fraction(int i, int i2) {
        this.numerator = i;
        this.denominator = i2;
    }

    private static int addAndCheck(int i, int i2) {
        long j = i + i2;
        if (j < -2147483648L || j > 2147483647L) {
            throw new ArithmeticException("overflow: add");
        }
        return (int) j;
    }

    private Fraction addSub(Fraction fraction, boolean z) {
        if (fraction != null) {
            if (this.numerator == 0) {
                return z ? fraction : fraction.negate();
            } else if (fraction.numerator == 0) {
                return this;
            } else {
                int greatestCommonDivisor = greatestCommonDivisor(this.denominator, fraction.denominator);
                if (greatestCommonDivisor == 1) {
                    int mulAndCheck = mulAndCheck(this.numerator, fraction.denominator);
                    int mulAndCheck2 = mulAndCheck(fraction.numerator, this.denominator);
                    return new Fraction(z ? addAndCheck(mulAndCheck, mulAndCheck2) : subAndCheck(mulAndCheck, mulAndCheck2), mulPosAndCheck(this.denominator, fraction.denominator));
                }
                BigInteger multiply = BigInteger.valueOf(this.numerator).multiply(BigInteger.valueOf(fraction.denominator / greatestCommonDivisor));
                BigInteger multiply2 = BigInteger.valueOf(fraction.numerator).multiply(BigInteger.valueOf(this.denominator / greatestCommonDivisor));
                BigInteger add = z ? multiply.add(multiply2) : multiply.subtract(multiply2);
                int intValue = add.mod(BigInteger.valueOf(greatestCommonDivisor)).intValue();
                int greatestCommonDivisor2 = intValue == 0 ? greatestCommonDivisor : greatestCommonDivisor(intValue, greatestCommonDivisor);
                BigInteger divide = add.divide(BigInteger.valueOf(greatestCommonDivisor2));
                if (divide.bitLength() <= 31) {
                    return new Fraction(divide.intValue(), mulPosAndCheck(this.denominator / greatestCommonDivisor, fraction.denominator / greatestCommonDivisor2));
                }
                throw new ArithmeticException("overflow: numerator too large after multiply");
            }
        }
        throw new IllegalArgumentException("The fraction must not be null");
    }

    public static Fraction getFraction(int i, int i2) {
        if (i2 != 0) {
            if (i2 < 0) {
                if (i == Integer.MIN_VALUE || i2 == Integer.MIN_VALUE) {
                    throw new ArithmeticException("overflow: can't negate");
                }
                i = -i;
                i2 = -i2;
            }
            return new Fraction(i, i2);
        }
        throw new ArithmeticException("The denominator must not be zero");
    }

    public static Fraction getReducedFraction(int i, int i2) {
        if (i2 != 0) {
            if (i == 0) {
                return ZERO;
            }
            if (i2 == Integer.MIN_VALUE && (i & 1) == 0) {
                i /= 2;
                i2 /= 2;
            }
            if (i2 < 0) {
                if (i == Integer.MIN_VALUE || i2 == Integer.MIN_VALUE) {
                    throw new ArithmeticException("overflow: can't negate");
                }
                i = -i;
                i2 = -i2;
            }
            int greatestCommonDivisor = greatestCommonDivisor(i, i2);
            return new Fraction(i / greatestCommonDivisor, i2 / greatestCommonDivisor);
        }
        throw new ArithmeticException("The denominator must not be zero");
    }

    /* JADX WARN: Code restructure failed: missing block: B:19:0x002a, code lost:
        if (r2 != 1) goto L38;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x002c, code lost:
        r2 = r6;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x002e, code lost:
        r2 = -(r5 / 2);
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0033, code lost:
        if ((r2 & 1) != 0) goto L23;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0035, code lost:
        r2 = r2 / 2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0038, code lost:
        if (r2 <= 0) goto L34;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x003a, code lost:
        r5 = -r2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x003c, code lost:
        r6 = r2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x003d, code lost:
        r2 = (r6 - r5) / 2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x0041, code lost:
        if (r2 != 0) goto L32;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x0047, code lost:
        return (-r5) * (1 << r0);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static int greatestCommonDivisor(int r5, int r6) {
        /*
            int r0 = java.lang.Math.abs(r5)
            r1 = 1
            if (r0 <= r1) goto L50
            int r0 = java.lang.Math.abs(r6)
            if (r0 > r1) goto Le
            goto L50
        Le:
            if (r5 <= 0) goto L11
            int r5 = -r5
        L11:
            if (r6 <= 0) goto L14
            int r6 = -r6
        L14:
            r0 = 0
        L15:
            r2 = r5 & 1
            r3 = 31
            if (r2 != 0) goto L28
            r4 = r6 & 1
            if (r4 != 0) goto L28
            if (r0 >= r3) goto L28
            int r5 = r5 / 2
            int r6 = r6 / 2
            int r0 = r0 + 1
            goto L15
        L28:
            if (r0 == r3) goto L48
            if (r2 != r1) goto L2e
            r2 = r6
            goto L31
        L2e:
            int r2 = r5 / 2
            int r2 = -r2
        L31:
            r3 = r2 & 1
            if (r3 != 0) goto L38
            int r2 = r2 / 2
            goto L31
        L38:
            if (r2 <= 0) goto L3c
            int r5 = -r2
            goto L3d
        L3c:
            r6 = r2
        L3d:
            int r2 = r6 - r5
            int r2 = r2 / 2
            if (r2 != 0) goto L31
            int r5 = -r5
            int r6 = r1 << r0
            int r5 = r5 * r6
            return r5
        L48:
            java.lang.ArithmeticException r5 = new java.lang.ArithmeticException
            java.lang.String r6 = "overflow: gcd is 2^31"
            r5.<init>(r6)
            throw r5
        L50:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.lang.math.Fraction.greatestCommonDivisor(int, int):int");
    }

    private static int mulAndCheck(int i, int i2) {
        long j = i * i2;
        if (j < -2147483648L || j > 2147483647L) {
            throw new ArithmeticException("overflow: mul");
        }
        return (int) j;
    }

    private static int mulPosAndCheck(int i, int i2) {
        long j = i * i2;
        if (j <= 2147483647L) {
            return (int) j;
        }
        throw new ArithmeticException("overflow: mulPos");
    }

    private static int subAndCheck(int i, int i2) {
        long j = i - i2;
        if (j < -2147483648L || j > 2147483647L) {
            throw new ArithmeticException("overflow: add");
        }
        return (int) j;
    }

    public Fraction abs() {
        return this.numerator >= 0 ? this : negate();
    }

    public Fraction add(Fraction fraction) {
        return addSub(fraction, true);
    }

    @Override // java.lang.Comparable
    public int compareTo(Object obj) {
        int i;
        Fraction fraction = (Fraction) obj;
        if (this == fraction) {
            return 0;
        }
        if ((this.numerator == fraction.numerator && this.denominator == fraction.denominator) || this.numerator * fraction.denominator == fraction.numerator * this.denominator) {
            return 0;
        }
        return i < 0 ? -1 : 1;
    }

    public Fraction divideBy(Fraction fraction) {
        if (fraction != null) {
            if (fraction.numerator != 0) {
                return multiplyBy(fraction.invert());
            }
            throw new ArithmeticException("The fraction to divide by must not be zero");
        }
        throw new IllegalArgumentException("The fraction must not be null");
    }

    @Override // java.lang.Number
    public double doubleValue() {
        return this.numerator / this.denominator;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Fraction)) {
            return false;
        }
        Fraction fraction = (Fraction) obj;
        return getNumerator() == fraction.getNumerator() && getDenominator() == fraction.getDenominator();
    }

    @Override // java.lang.Number
    public float floatValue() {
        return this.numerator / this.denominator;
    }

    public int getDenominator() {
        return this.denominator;
    }

    public int getNumerator() {
        return this.numerator;
    }

    public int getProperNumerator() {
        return Math.abs(this.numerator % this.denominator);
    }

    public int getProperWhole() {
        return this.numerator / this.denominator;
    }

    public int hashCode() {
        if (this.hashCode == 0) {
            this.hashCode = getDenominator() + ((getNumerator() + 629) * 37);
        }
        return this.hashCode;
    }

    @Override // java.lang.Number
    public int intValue() {
        return this.numerator / this.denominator;
    }

    public Fraction invert() {
        int i = this.numerator;
        if (i != 0) {
            if (i == Integer.MIN_VALUE) {
                throw new ArithmeticException("overflow: can't negate numerator");
            }
            if (i < 0) {
                return new Fraction(-this.denominator, -i);
            }
            return new Fraction(this.denominator, i);
        }
        throw new ArithmeticException("Unable to invert zero.");
    }

    @Override // java.lang.Number
    public long longValue() {
        return this.numerator / this.denominator;
    }

    public Fraction multiplyBy(Fraction fraction) {
        if (fraction != null) {
            int i = this.numerator;
            if (i != 0 && fraction.numerator != 0) {
                int greatestCommonDivisor = greatestCommonDivisor(i, fraction.denominator);
                int greatestCommonDivisor2 = greatestCommonDivisor(fraction.numerator, this.denominator);
                return getReducedFraction(mulAndCheck(this.numerator / greatestCommonDivisor, fraction.numerator / greatestCommonDivisor2), mulPosAndCheck(this.denominator / greatestCommonDivisor2, fraction.denominator / greatestCommonDivisor));
            }
            return ZERO;
        }
        throw new IllegalArgumentException("The fraction must not be null");
    }

    public Fraction negate() {
        int i = this.numerator;
        if (i != Integer.MIN_VALUE) {
            return new Fraction(-i, this.denominator);
        }
        throw new ArithmeticException("overflow: too large to negate");
    }

    public Fraction pow(int i) {
        if (i == 1) {
            return this;
        }
        if (i == 0) {
            return ONE;
        }
        if (i < 0) {
            if (i == Integer.MIN_VALUE) {
                return invert().pow(2).pow(-(i / 2));
            }
            return invert().pow(-i);
        }
        Fraction multiplyBy = multiplyBy(this);
        if (i % 2 == 0) {
            return multiplyBy.pow(i / 2);
        }
        return multiplyBy.pow(i / 2).multiplyBy(this);
    }

    public Fraction reduce() {
        int i = this.numerator;
        if (i == 0) {
            return equals(ZERO) ? this : ZERO;
        }
        int greatestCommonDivisor = greatestCommonDivisor(Math.abs(i), this.denominator);
        return greatestCommonDivisor == 1 ? this : getFraction(this.numerator / greatestCommonDivisor, this.denominator / greatestCommonDivisor);
    }

    public Fraction subtract(Fraction fraction) {
        return addSub(fraction, false);
    }

    public String toProperString() {
        if (this.toProperString == null) {
            int i = this.numerator;
            if (i == 0) {
                this.toProperString = "0";
            } else {
                int i2 = this.denominator;
                if (i == i2) {
                    this.toProperString = "1";
                } else if (i == i2 * (-1)) {
                    this.toProperString = "-1";
                } else {
                    if (i > 0) {
                        i = -i;
                    }
                    if (i < (-this.denominator)) {
                        int properNumerator = getProperNumerator();
                        if (properNumerator == 0) {
                            this.toProperString = Integer.toString(getProperWhole());
                        } else {
                            this.toProperString = new StrBuilder(32).append(getProperWhole()).append(Chars.SPACE).append(properNumerator).append('/').append(getDenominator()).toString();
                        }
                    } else {
                        this.toProperString = new StrBuilder(32).append(getNumerator()).append('/').append(getDenominator()).toString();
                    }
                }
            }
        }
        return this.toProperString;
    }

    public String toString() {
        if (this.toString == null) {
            this.toString = new StrBuilder(32).append(getNumerator()).append('/').append(getDenominator()).toString();
        }
        return this.toString;
    }

    public static Fraction getFraction(int i, int i2, int i3) {
        if (i3 != 0) {
            if (i3 < 0) {
                throw new ArithmeticException("The denominator must not be negative");
            }
            if (i2 < 0) {
                throw new ArithmeticException("The numerator must not be negative");
            }
            long j = i < 0 ? (i * i3) - i2 : (i * i3) + i2;
            if (j >= -2147483648L && j <= 2147483647L) {
                return new Fraction((int) j, i3);
            }
            throw new ArithmeticException("Numerator too large to represent as an Integer.");
        }
        throw new ArithmeticException("The denominator must not be zero");
    }

    /* JADX WARN: Code restructure failed: missing block: B:21:0x0074, code lost:
        return getReducedFraction(((r4 * r14) + r12) * r0, r14);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static org.apache.commons.lang.math.Fraction getFraction(double r19) {
        /*
            r0 = 0
            int r0 = (r19 > r0 ? 1 : (r19 == r0 ? 0 : -1))
            if (r0 >= 0) goto L8
            r0 = -1
            goto L9
        L8:
            r0 = 1
        L9:
            double r2 = java.lang.Math.abs(r19)
            r4 = 4746794007244308480(0x41dfffffffc00000, double:2.147483647E9)
            int r4 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r4 > 0) goto L7d
            boolean r4 = java.lang.Double.isNaN(r2)
            if (r4 != 0) goto L7d
            int r4 = (int) r2
            double r5 = (double) r4
            double r2 = r2 - r5
            int r5 = (int) r2
            r6 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            double r8 = (double) r5
            double r8 = r2 - r8
            r10 = 9218868437227405311(0x7fefffffffffffff, double:1.7976931348623157E308)
            r12 = 0
            r19 = r2
            r13 = r12
            r14 = r13
            r12 = 1
            r15 = 1
            r16 = 1
        L33:
            double r1 = r6 / r8
            int r1 = (int) r1
            double r2 = (double) r1
            double r2 = r2 * r8
            double r2 = r6 - r2
            int r6 = r5 * r12
            int r6 = r6 + r13
            int r5 = r5 * r14
            int r5 = r5 + r15
            r7 = r1
            r17 = r2
            double r1 = (double) r6
            r3 = r6
            r13 = r7
            double r6 = (double) r5
            double r1 = r1 / r6
            double r1 = r19 - r1
            double r1 = java.lang.Math.abs(r1)
            r6 = 1
            int r7 = r16 + 1
            int r10 = (r10 > r1 ? 1 : (r10 == r1 ? 0 : -1))
            r11 = 25
            if (r10 <= 0) goto L6b
            r10 = 10000(0x2710, float:1.4013E-41)
            if (r5 > r10) goto L6b
            if (r5 <= 0) goto L6b
            if (r7 < r11) goto L5f
            goto L6b
        L5f:
            r10 = r1
            r16 = r7
            r6 = r8
            r15 = r14
            r8 = r17
            r14 = r5
            r5 = r13
            r13 = r12
            r12 = r3
            goto L33
        L6b:
            if (r7 == r11) goto L75
            int r4 = r4 * r14
            int r4 = r4 + r12
            int r4 = r4 * r0
            org.apache.commons.lang.math.Fraction r0 = getReducedFraction(r4, r14)
            return r0
        L75:
            java.lang.ArithmeticException r0 = new java.lang.ArithmeticException
            java.lang.String r1 = "Unable to convert double to fraction"
            r0.<init>(r1)
            throw r0
        L7d:
            java.lang.ArithmeticException r0 = new java.lang.ArithmeticException
            java.lang.String r1 = "The value must not be greater than Integer.MAX_VALUE or NaN"
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.lang.math.Fraction.getFraction(double):org.apache.commons.lang.math.Fraction");
    }

    public static Fraction getFraction(String str) {
        if (str != null) {
            if (str.indexOf(46) >= 0) {
                return getFraction(Double.parseDouble(str));
            }
            int indexOf = str.indexOf(32);
            if (indexOf > 0) {
                int parseInt = Integer.parseInt(str.substring(0, indexOf));
                String substring = str.substring(indexOf + 1);
                int indexOf2 = substring.indexOf(47);
                if (indexOf2 >= 0) {
                    return getFraction(parseInt, Integer.parseInt(substring.substring(0, indexOf2)), Integer.parseInt(substring.substring(indexOf2 + 1)));
                }
                throw new NumberFormatException("The fraction could not be parsed as the format X Y/Z");
            }
            int indexOf3 = str.indexOf(47);
            if (indexOf3 < 0) {
                return getFraction(Integer.parseInt(str), 1);
            }
            return getFraction(Integer.parseInt(str.substring(0, indexOf3)), Integer.parseInt(str.substring(indexOf3 + 1)));
        }
        throw new IllegalArgumentException("The string must not be null");
    }
}
