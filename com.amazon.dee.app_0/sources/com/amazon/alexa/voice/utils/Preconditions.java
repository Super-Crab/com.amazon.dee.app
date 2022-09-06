package com.amazon.alexa.voice.utils;

import android.os.Looper;
import android.text.TextUtils;
import java.util.List;
import java.util.Map;
/* loaded from: classes11.dex */
public final class Preconditions {
    private Preconditions() {
    }

    public static void assertMainThread() {
        if (Looper.getMainLooper() == Looper.myLooper()) {
            return;
        }
        throw new IllegalStateException("It's not running on UI thread.");
    }

    public static void notBlank(String str, String... strArr) {
        if (strArr == null) {
            return;
        }
        for (String str2 : strArr) {
            if (TextUtils.isEmpty(str2)) {
                throw new IllegalArgumentException(str);
            }
        }
    }

    public static <S, T> void notEmpty(String str, Map<S, T> map) {
        if (map != null && map.isEmpty()) {
            throw new IllegalArgumentException(str);
        }
    }

    public static void notNull(String str, Object... objArr) {
        if (objArr == null) {
            return;
        }
        for (Object obj : objArr) {
            if (obj == null) {
                throw new IllegalArgumentException(str);
            }
        }
    }

    public static <T> void notNullOrEmpty(String str, List<T> list) {
        if (list != null) {
            if (list.isEmpty()) {
                throw new IllegalArgumentException(String.format("%s is empty", str));
            }
            return;
        }
        throw new NullPointerException(String.format("%s is null", str));
    }
}
