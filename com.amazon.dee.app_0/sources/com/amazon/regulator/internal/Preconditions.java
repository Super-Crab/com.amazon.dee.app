package com.amazon.regulator.internal;
/* loaded from: classes13.dex */
public final class Preconditions {
    private Preconditions() {
        throw new UnsupportedOperationException("No instances!");
    }

    public static void nonNull(Object obj, String str) {
        if (obj != null) {
            return;
        }
        throw new NullPointerException(str);
    }
}
