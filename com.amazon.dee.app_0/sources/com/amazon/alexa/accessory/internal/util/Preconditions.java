package com.amazon.alexa.accessory.internal.util;

import android.os.Looper;
import android.text.TextUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Locale;
/* loaded from: classes.dex */
public final class Preconditions {
    public static final String VALID_FILE_REGEX_PATTERN = "^[A-za-z0-9._-]{1,255}$";

    private Preconditions() {
        throw new IllegalStateException("No instances!");
    }

    public static void elementIndex(int i, int i2, String str) {
        if (i < 0 || i >= i2) {
            IndexOutOfBoundsException indexOutOfBoundsException = new IndexOutOfBoundsException(formatElementIndex(i, i2, str));
            Logger.e("Throwing ", indexOutOfBoundsException);
            throw indexOutOfBoundsException;
        }
    }

    public static void equals(int i, int i2, String str) {
        if (i == i2) {
            return;
        }
        IllegalArgumentException illegalArgumentException = new IllegalArgumentException(String.format(Locale.US, "%s (%d) must equal (%d)", str, Integer.valueOf(i), Integer.valueOf(i2)));
        Logger.e("Throwing ", illegalArgumentException);
        throw illegalArgumentException;
    }

    private static String formatElementIndex(int i, int i2, String str) {
        if (i < 0) {
            return String.format(Locale.US, "%s (%d) must not be negative", str, Integer.valueOf(i));
        }
        if (i2 < 0) {
            throw new IllegalArgumentException(GeneratedOutlineSupport1.outline49("negative size: ", i2));
        }
        return String.format(Locale.US, "%s (%d) must be less than size (%d)", str, Integer.valueOf(i), Integer.valueOf(i2));
    }

    public static void in(int i, String str, int... iArr) {
        for (int i2 : iArr) {
            if (i == i2) {
                return;
            }
        }
        IllegalArgumentException illegalArgumentException = new IllegalArgumentException(String.format(Locale.US, "%s must be in the set of values", str));
        Logger.e("Throwing ", illegalArgumentException);
        throw illegalArgumentException;
    }

    public static void mainThread() {
        if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
            return;
        }
        IllegalStateException illegalStateException = new IllegalStateException("Current thread must be a main thread");
        Logger.e("Throwing ", illegalStateException);
        throw illegalStateException;
    }

    public static void notEmpty(String str, String str2) {
        if (!TextUtils.isEmpty(str)) {
            return;
        }
        IllegalArgumentException illegalArgumentException = new IllegalArgumentException(String.format(Locale.US, "%s must not be empty", str2));
        Logger.e("Throwing ", illegalArgumentException);
        throw illegalArgumentException;
    }

    public static void notNegative(int i, String str) {
        if (i >= 0) {
            return;
        }
        IllegalArgumentException illegalArgumentException = new IllegalArgumentException(String.format(Locale.US, "%s (%d) must not be negative", str, Integer.valueOf(i)));
        Logger.e("Throwing ", illegalArgumentException);
        throw illegalArgumentException;
    }

    public static void notNull(Object obj, String str) {
        if (obj != null) {
            return;
        }
        NullPointerException nullPointerException = new NullPointerException(String.format(Locale.US, "%s must not be null", str));
        Logger.e("Throwing ", nullPointerException);
        throw nullPointerException;
    }

    public static void precondition(boolean z, String str) {
        if (z) {
            return;
        }
        IllegalArgumentException illegalArgumentException = new IllegalArgumentException(str);
        Logger.e("Throwing ", illegalArgumentException);
        throw illegalArgumentException;
    }

    public static void validFileName(String str, String str2) {
        if (TextUtils.isEmpty(str) || !str.matches(VALID_FILE_REGEX_PATTERN)) {
            throw new IllegalArgumentException(String.format(Locale.US, "%s must be valid file name, provided fileName: %s", str2, str));
        }
    }
}
