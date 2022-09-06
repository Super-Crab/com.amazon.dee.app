package com.amazon.alexa.presence.library;

import android.annotation.TargetApi;
import android.os.Build;
import java.util.concurrent.Callable;
/* loaded from: classes9.dex */
public final class Compatibility {
    private Compatibility() {
    }

    private static <T> T call(Callable<T> callable) {
        try {
            return callable.call();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T ifAndroidMOrLater(Callable<T> callable) {
        if (isAndroidMOrLater()) {
            return (T) whenIsAndroidMOrLater(callable);
        }
        return null;
    }

    public static <T> T ifAndroidNOrLater(Callable<T> callable) {
        if (isAndroidNOrLater()) {
            return (T) whenIsAndroidNOrLater(callable);
        }
        return null;
    }

    public static <T> T ifAndroidOOrLater(Callable<T> callable) {
        if (isAndroidOOrLater()) {
            return (T) whenIsAndroidOOrLater(callable);
        }
        return null;
    }

    public static boolean isAndroidMOrLater() {
        int i = Build.VERSION.SDK_INT;
        return true;
    }

    public static boolean isAndroidNOrLater() {
        int i = Build.VERSION.SDK_INT;
        return true;
    }

    public static boolean isAndroidOOrLater() {
        int i = Build.VERSION.SDK_INT;
        return true;
    }

    @TargetApi(23)
    private static <T> T whenIsAndroidMOrLater(Callable<T> callable) {
        try {
            return callable.call();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @TargetApi(24)
    private static <T> T whenIsAndroidNOrLater(Callable<T> callable) {
        try {
            return callable.call();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @TargetApi(26)
    private static <T> T whenIsAndroidOOrLater(Callable<T> callable) {
        try {
            return callable.call();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T ifAndroidOOrLater(Callable<T> callable, Callable<T> callable2) {
        return isAndroidOOrLater() ? (T) whenIsAndroidOOrLater(callable) : (T) call(callable2);
    }
}
