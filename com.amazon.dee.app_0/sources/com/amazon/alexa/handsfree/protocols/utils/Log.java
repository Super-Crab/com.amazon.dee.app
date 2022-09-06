package com.amazon.alexa.handsfree.protocols.utils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.IllegalFormatException;
/* loaded from: classes8.dex */
public final class Log {
    private static final String AMPD_PREFIX = "AMPD-";
    private static final String CODE_LINE_FORMAT = "(%s:%d)";
    private static final String ENTER = "Enter-> ";
    private static final String LEAVE = "Leave-> ";
    private static final int MAX_TAG_LENGTH = 23;
    private static final String NOTHING = "";
    private static final String PERIOD = ".";
    private static final int STACK_OFFSET = 1;
    private static final String THERE = "There-> ";
    private static final String LINE_SEPARATOR = System.lineSeparator();
    private static boolean isRelease = false;

    private Log() {
    }

    public static String calledFrom() {
        try {
            StackTraceElement stackTraceElement = new Throwable().getStackTrace()[2];
            return stackTraceElement.getMethodName() + formatCodeReference(stackTraceElement);
        } catch (Exception unused) {
            return "UNKNOWN";
        }
    }

    public static void d(@Nullable String str, @NonNull String str2, Object... objArr) {
        if (!isRelease) {
            android.util.Log.i(prefixTag(str), safeFormat(str2, objArr));
        }
    }

    public static void e(@Nullable String str, @NonNull String str2, Object... objArr) {
        android.util.Log.e(prefixTag(str), safeFormat(str2, objArr));
    }

    public static void enforceReleaseLogging() {
        isRelease = true;
    }

    public static void enter() {
        if (!isRelease) {
            StackTraceElement stackTraceElement = GeneratedOutlineSupport1.outline195()[1];
            String simpleClassName = getSimpleClassName(stackTraceElement);
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107(ENTER);
            outline107.append(stackTraceElement.getMethodName());
            GeneratedOutlineSupport1.outline179(outline107, formatCodeReference(stackTraceElement), simpleClassName);
        }
    }

    private static String formatCodeReference(@NonNull StackTraceElement stackTraceElement) {
        return safeFormat(CODE_LINE_FORMAT, stackTraceElement.getFileName(), Integer.valueOf(stackTraceElement.getLineNumber()));
    }

    @NonNull
    private static String getSimpleClassName(@NonNull StackTraceElement stackTraceElement) {
        String className = stackTraceElement.getClassName();
        return className.substring(className.lastIndexOf(".") + 1);
    }

    @NonNull
    public static String getStackTraceString() {
        if (!isRelease) {
            StackTraceElement[] outline195 = GeneratedOutlineSupport1.outline195();
            StringBuilder sb = new StringBuilder();
            for (int i = 1; i < outline195.length; i++) {
                sb.append(outline195[i].toString());
                sb.append(LINE_SEPARATOR);
            }
            return sb.toString();
        }
        return "";
    }

    public static void i(@Nullable String str, @NonNull String str2, Object... objArr) {
        android.util.Log.i(prefixTag(str), safeFormat(str2, objArr));
    }

    public static void leave() {
        if (!isRelease) {
            StackTraceElement stackTraceElement = GeneratedOutlineSupport1.outline195()[1];
            String simpleClassName = getSimpleClassName(stackTraceElement);
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107(LEAVE);
            outline107.append(stackTraceElement.getMethodName());
            GeneratedOutlineSupport1.outline179(outline107, formatCodeReference(stackTraceElement), simpleClassName);
        }
    }

    public static String line() {
        return !isRelease ? formatCodeReference(GeneratedOutlineSupport1.outline195()[1]) : "";
    }

    private static String prefixTag(@Nullable String str) {
        if (str == null) {
            return null;
        }
        return GeneratedOutlineSupport1.outline72(AMPD_PREFIX, str);
    }

    private static String safeFormat(@Nullable String str, Object... objArr) {
        if (objArr == null || objArr.length == 0) {
            return str;
        }
        try {
            return String.format(str, objArr);
        } catch (IllegalFormatException e) {
            e("Log", "bad format: ", e, new Object[0]);
            return "Formatting error";
        }
    }

    private static String safeTag(@NonNull String str) {
        return str.length() > 23 ? str.substring(0, 23) : str;
    }

    public static String tag(@NonNull String str) {
        return safeTag(str);
    }

    public static void there() {
        if (!isRelease) {
            StackTraceElement stackTraceElement = GeneratedOutlineSupport1.outline195()[1];
            String simpleClassName = getSimpleClassName(stackTraceElement);
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107(THERE);
            outline107.append(stackTraceElement.getMethodName());
            GeneratedOutlineSupport1.outline179(outline107, formatCodeReference(stackTraceElement), simpleClassName);
        }
    }

    public static void v(@Nullable String str, @NonNull String str2, Object... objArr) {
        if (!isRelease) {
            prefixTag(str);
            safeFormat(str2, objArr);
        }
    }

    public static void w(@Nullable String str, @NonNull String str2, Object... objArr) {
        android.util.Log.w(prefixTag(str), safeFormat(str2, objArr));
    }

    public static void wtf(@Nullable String str, @Nullable String str2, Object... objArr) {
        android.util.Log.wtf(prefixTag(str), safeFormat(str2, objArr));
    }

    public static int e(@Nullable String str, @Nullable String str2, @Nullable Throwable th, Object... objArr) {
        return android.util.Log.e(prefixTag(str), safeFormat(str2, objArr), th);
    }

    public static int i(@Nullable String str, @NonNull String str2) {
        return android.util.Log.i(prefixTag(str), str2);
    }

    public static String tag(@NonNull Class cls) {
        return safeTag(cls.getSimpleName());
    }

    public static void w(@Nullable String str, @NonNull String str2) {
        android.util.Log.w(prefixTag(str), str2);
    }

    public static void wtf(@Nullable String str, @Nullable String str2) {
        android.util.Log.wtf(prefixTag(str), str2);
    }

    public static int d(@Nullable String str, @NonNull String str2) {
        if (!isRelease) {
            return android.util.Log.i(prefixTag(str), str2);
        }
        return -1;
    }

    public static int e(@Nullable String str, @NonNull String str2) {
        return android.util.Log.e(str, str2);
    }

    public static void v(@Nullable String str, @NonNull String str2) {
        if (!isRelease) {
            prefixTag(str);
        }
    }

    public static void w(@Nullable String str, @Nullable Throwable th) {
        android.util.Log.w(prefixTag(str), th);
    }

    public static void w(@Nullable String str, @Nullable Throwable th, @Nullable String str2, Object... objArr) {
        android.util.Log.w(prefixTag(str), safeFormat(str2, objArr), th);
    }

    @NonNull
    public static String getStackTraceString(int i) {
        if (!isRelease) {
            StackTraceElement[] outline195 = GeneratedOutlineSupport1.outline195();
            StringBuilder sb = new StringBuilder();
            for (int i2 = 1; i2 < outline195.length && i2 < i + 1; i2++) {
                sb.append(outline195[i2].toString());
                sb.append(LINE_SEPARATOR);
            }
            return sb.toString();
        }
        return "";
    }
}
