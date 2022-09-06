package com.amazon.alexa.utils.validation;

import android.text.TextUtils;
/* loaded from: classes10.dex */
public final class Assertions {
    private Assertions() {
        throw new UnsupportedOperationException();
    }

    public static void isFalse(boolean z, String str) {
        if (!z) {
            return;
        }
        throw new IllegalStateException(str);
    }

    public static void notEmpty(String str, String str2) {
        isFalse(TextUtils.isEmpty(str), str2);
    }

    public static void notNull(Object obj, String str) {
        isFalse(obj == null, str);
    }
}
