package com.amazon.crypto.utils;
/* loaded from: classes12.dex */
public final class UtilityInstanceAttempt {
    private UtilityInstanceAttempt() {
        in(this);
    }

    public static void in(Object obj) throws UnsupportedOperationException {
        if (obj == null) {
            throw new UnsupportedOperationException("No instances of utility classes.");
        }
        throw new UnsupportedOperationException(String.format("%s is a utility class; instantiation is not allowed.", obj.getClass().getSimpleName()));
    }
}
