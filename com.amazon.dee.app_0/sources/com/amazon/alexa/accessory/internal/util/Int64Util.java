package com.amazon.alexa.accessory.internal.util;
/* loaded from: classes.dex */
public final class Int64Util {
    private static final long MASK = 4294967295L;

    private Int64Util() {
    }

    public static int getHigh32(long j) {
        return (int) (j >>> 32);
    }

    public static long getLong(int i, int i2) {
        return (i2 & 4294967295L) | ((i & 4294967295L) << 32);
    }

    public static int getLow32(long j) {
        return (int) (j & 4294967295L);
    }
}
