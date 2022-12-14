package com.google.android.exoplayer2.util;

import android.os.Looper;
import android.text.TextUtils;
import androidx.annotation.Nullable;
import org.checkerframework.checker.nullness.qual.EnsuresNonNull;
import org.checkerframework.dataflow.qual.Pure;
/* loaded from: classes2.dex */
public final class Assertions {
    private Assertions() {
    }

    @Pure
    public static void checkArgument(boolean z) {
        if (z) {
            return;
        }
        throw new IllegalArgumentException();
    }

    @Pure
    public static int checkIndex(int i, int i2, int i3) {
        if (i < i2 || i >= i3) {
            throw new IndexOutOfBoundsException();
        }
        return i;
    }

    @Pure
    public static void checkMainThread() {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            return;
        }
        throw new IllegalStateException("Not in applications main thread");
    }

    @EnsuresNonNull({"#1"})
    @Pure
    public static String checkNotEmpty(@Nullable String str) {
        if (!TextUtils.isEmpty(str)) {
            return str;
        }
        throw new IllegalArgumentException();
    }

    @EnsuresNonNull({"#1"})
    @Pure
    public static <T> T checkNotNull(@Nullable T t) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException();
    }

    @Pure
    public static void checkState(boolean z) {
        if (z) {
            return;
        }
        throw new IllegalStateException();
    }

    @EnsuresNonNull({"#1"})
    @Pure
    public static <T> T checkStateNotNull(@Nullable T t) {
        if (t != null) {
            return t;
        }
        throw new IllegalStateException();
    }

    @Pure
    public static void checkArgument(boolean z, Object obj) {
        if (z) {
            return;
        }
        throw new IllegalArgumentException(String.valueOf(obj));
    }

    @EnsuresNonNull({"#1"})
    @Pure
    public static <T> T checkNotNull(@Nullable T t, Object obj) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(String.valueOf(obj));
    }

    @Pure
    public static void checkState(boolean z, Object obj) {
        if (z) {
            return;
        }
        throw new IllegalStateException(String.valueOf(obj));
    }

    @EnsuresNonNull({"#1"})
    @Pure
    public static <T> T checkStateNotNull(@Nullable T t, Object obj) {
        if (t != null) {
            return t;
        }
        throw new IllegalStateException(String.valueOf(obj));
    }

    @EnsuresNonNull({"#1"})
    @Pure
    public static String checkNotEmpty(@Nullable String str, Object obj) {
        if (!TextUtils.isEmpty(str)) {
            return str;
        }
        throw new IllegalArgumentException(String.valueOf(obj));
    }
}
