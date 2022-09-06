package com.amazon.alexa.utils.validation;
/* loaded from: classes10.dex */
public class Preconditions {
    private Preconditions() {
        throw new UnsupportedOperationException();
    }

    public static void isFalse(boolean z, String str) {
        if (!z) {
            return;
        }
        throw new IllegalArgumentException(str);
    }

    public static void notNull(Object obj, String str) {
        isFalse(obj == null, str);
    }
}
