package com.amazon.alexa.presence.bleconn.helpers;
/* loaded from: classes9.dex */
public final class MathHelper {
    private MathHelper() {
    }

    public static int clamp(int i, int i2, int i3) {
        if (i2 <= i3) {
            return Math.max(i2, Math.min(i3, i));
        }
        throw new IllegalArgumentException(String.format("min (%s) should not be greater than max (%s)", Integer.valueOf(i2), Integer.valueOf(i3)));
    }
}
