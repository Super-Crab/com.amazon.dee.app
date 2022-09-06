package com.amazon.alexa.handsfree.protocols.utils;

import androidx.annotation.Nullable;
import java.util.Locale;
/* loaded from: classes8.dex */
public final class Preconditions {
    private Preconditions() {
    }

    public static void checkArgument(boolean z) {
        checkArgument(z, null);
    }

    public static void checkInRange(int i, int i2, int i3) {
        if (i < i2 || i > i3) {
            throw new IllegalArgumentException(String.format(Locale.getDefault(), "Expected a value between %d and %d", Integer.valueOf(i2), Integer.valueOf(i3)));
        }
    }

    public static void checkValidRange(int i, int i2) {
        if (i < i2) {
            return;
        }
        throw new IllegalArgumentException(String.format(Locale.getDefault(), "%d should be < %d", Integer.valueOf(i), Integer.valueOf(i2)));
    }

    public static void checkArgument(boolean z, @Nullable String str) {
        if (z) {
            return;
        }
        throw new IllegalArgumentException(str);
    }
}
