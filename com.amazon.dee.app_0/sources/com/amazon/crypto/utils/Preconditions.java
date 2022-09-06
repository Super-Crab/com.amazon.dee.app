package com.amazon.crypto.utils;

import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
/* loaded from: classes12.dex */
public final class Preconditions {
    private Preconditions() {
        UtilityInstanceAttempt.in(this);
    }

    public static void isTrue(@NonNull boolean... zArr) {
        for (boolean z : zArr) {
            if (!z) {
                throw new IllegalArgumentException("Condition(s) not met.");
            }
        }
    }

    public static void notBlank(@Nullable String... strArr) {
        if (strArr != null) {
            for (String str : strArr) {
                if (TextUtils.isEmpty(str)) {
                    throw new IllegalArgumentException("Null or blank argument(s) provided.");
                }
            }
            return;
        }
        throw new IllegalArgumentException("Null or blank argument(s) provided.");
    }

    public static void notNull(@Nullable Object... objArr) {
        if (objArr != null) {
            for (Object obj : objArr) {
                if (obj == null) {
                    throw new IllegalArgumentException("Null arguments provided. Precondition failed.");
                }
            }
            return;
        }
        throw new IllegalArgumentException("Null arguments provided. Precondition failed.");
    }
}
