package io.reactivex.rxjava3.internal.util;
/* loaded from: classes3.dex */
public final class Pow2 {
    private Pow2() {
        throw new IllegalStateException("No instances!");
    }

    public static boolean isPowerOfTwo(final int value) {
        return (value & (value + (-1))) == 0;
    }

    public static int roundToPowerOfTwo(final int value) {
        return 1 << (32 - Integer.numberOfLeadingZeros(value - 1));
    }
}
