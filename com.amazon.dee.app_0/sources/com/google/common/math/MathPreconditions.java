package com.google.common.math;

import com.amazon.comms.ringservice.webrtc.FrostVideoEffectController;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.common.annotations.GwtCompatible;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.math.BigInteger;
import java.math.RoundingMode;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
/* JADX INFO: Access modifiers changed from: package-private */
@CanIgnoreReturnValue
@GwtCompatible
/* loaded from: classes3.dex */
public final class MathPreconditions {
    private MathPreconditions() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void checkInRangeForRoundingInputs(boolean z, double d, RoundingMode roundingMode) {
        if (z) {
            return;
        }
        String valueOf = String.valueOf(roundingMode);
        StringBuilder sb = new StringBuilder(valueOf.length() + 83);
        sb.append("rounded value is out of range for input ");
        sb.append(d);
        sb.append(" and rounding mode ");
        sb.append(valueOf);
        throw new ArithmeticException(sb.toString());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void checkNoOverflow(boolean z, String str, int i, int i2) {
        if (z) {
            return;
        }
        StringBuilder sb = new StringBuilder(GeneratedOutlineSupport1.outline6(str, 36));
        sb.append("overflow: ");
        sb.append(str);
        sb.append("(");
        sb.append(i);
        sb.append(", ");
        sb.append(i2);
        sb.append(")");
        throw new ArithmeticException(sb.toString());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int checkNonNegative(@NullableDecl String str, int i) {
        if (i >= 0) {
            return i;
        }
        StringBuilder sb = new StringBuilder(GeneratedOutlineSupport1.outline6(str, 27));
        sb.append(str);
        sb.append(" (");
        sb.append(i);
        sb.append(") must be >= 0");
        throw new IllegalArgumentException(sb.toString());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int checkPositive(@NullableDecl String str, int i) {
        if (i > 0) {
            return i;
        }
        StringBuilder sb = new StringBuilder(GeneratedOutlineSupport1.outline6(str, 26));
        sb.append(str);
        sb.append(" (");
        sb.append(i);
        sb.append(") must be > 0");
        throw new IllegalArgumentException(sb.toString());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void checkRoundingUnnecessary(boolean z) {
        if (z) {
            return;
        }
        throw new ArithmeticException("mode was UNNECESSARY, but rounding was necessary");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void checkNoOverflow(boolean z, String str, long j, long j2) {
        if (z) {
            return;
        }
        StringBuilder sb = new StringBuilder(GeneratedOutlineSupport1.outline6(str, 54));
        sb.append("overflow: ");
        sb.append(str);
        sb.append("(");
        sb.append(j);
        sb.append(", ");
        sb.append(j2);
        sb.append(")");
        throw new ArithmeticException(sb.toString());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static long checkNonNegative(@NullableDecl String str, long j) {
        if (j >= 0) {
            return j;
        }
        StringBuilder sb = new StringBuilder(GeneratedOutlineSupport1.outline6(str, 36));
        sb.append(str);
        sb.append(" (");
        sb.append(j);
        sb.append(") must be >= 0");
        throw new IllegalArgumentException(sb.toString());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static long checkPositive(@NullableDecl String str, long j) {
        if (j > 0) {
            return j;
        }
        StringBuilder sb = new StringBuilder(GeneratedOutlineSupport1.outline6(str, 35));
        sb.append(str);
        sb.append(" (");
        sb.append(j);
        sb.append(") must be > 0");
        throw new IllegalArgumentException(sb.toString());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static BigInteger checkNonNegative(@NullableDecl String str, BigInteger bigInteger) {
        if (bigInteger.signum() >= 0) {
            return bigInteger;
        }
        String valueOf = String.valueOf(bigInteger);
        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline31(valueOf.length() + GeneratedOutlineSupport1.outline6(str, 16), str, " (", valueOf, ") must be >= 0"));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static BigInteger checkPositive(@NullableDecl String str, BigInteger bigInteger) {
        if (bigInteger.signum() > 0) {
            return bigInteger;
        }
        String valueOf = String.valueOf(bigInteger);
        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline31(valueOf.length() + GeneratedOutlineSupport1.outline6(str, 15), str, " (", valueOf, ") must be > 0"));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static double checkNonNegative(@NullableDecl String str, double d) {
        if (d >= FrostVideoEffectController.VIDEO_STRENGTH_CLEAR) {
            return d;
        }
        StringBuilder sb = new StringBuilder(GeneratedOutlineSupport1.outline6(str, 40));
        sb.append(str);
        sb.append(" (");
        sb.append(d);
        sb.append(") must be >= 0");
        throw new IllegalArgumentException(sb.toString());
    }
}
