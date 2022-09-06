package com.amazon.alexa.mobilytics.util;

import androidx.annotation.NonNull;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes9.dex */
public final class Log {
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

    public static void d(@NonNull String str, @NonNull String str2) {
    }

    public static void d(@NonNull String str, @NonNull String str2, Object... objArr) {
        if (!isRelease) {
            safeFormat(str2, objArr);
        }
    }

    public static void e(@NonNull String str, @NonNull String str2, Object... objArr) {
        android.util.Log.e(str, safeFormat(str2, objArr));
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

    public static void i(@NonNull String str, @NonNull String str2, Object... objArr) {
        android.util.Log.i(str, safeFormat(str2, objArr));
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

    private static String safeFormat(@NonNull String str, Object... objArr) {
        return (objArr == null || objArr.length == 0) ? str : String.format(str, objArr);
    }

    private static String safeTag(@NonNull String str) {
        return str.length() > 23 ? str.substring(0, 23) : str;
    }

    public static String tag(@NonNull Class<?> cls) {
        return "Mobilytics";
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

    public static void v(@NonNull String str, @NonNull String str2) {
    }

    public static void v(@NonNull String str, @NonNull String str2, Object... objArr) {
        if (!isRelease) {
            safeFormat(str2, objArr);
        }
    }

    public static void w(@NonNull String str, @NonNull String str2, Object... objArr) {
        android.util.Log.w(str, safeFormat(str2, objArr));
    }

    public static void wtf(@NonNull String str, @NonNull String str2, Object... objArr) {
        android.util.Log.wtf(str, safeFormat(str2, objArr));
    }

    public static void e(@NonNull String str, @NonNull Throwable th, @NonNull String str2, Object... objArr) {
        android.util.Log.e(str, safeFormat(str2, objArr), th);
    }

    public static void i(@NonNull String str, @NonNull String str2) {
        android.util.Log.i(str, str2);
    }

    public static void w(@NonNull String str, @NonNull String str2) {
        android.util.Log.w(str, str2);
    }

    public static void wtf(@NonNull String str, @NonNull String str2) {
        android.util.Log.wtf(str, str2);
    }

    public static void e(@NonNull String str, @NonNull String str2) {
        android.util.Log.e(str, str2);
    }

    public static void w(@NonNull String str, @NonNull Throwable th) {
        android.util.Log.w(str, th);
    }

    public static void w(@NonNull String str, @NonNull Throwable th, @NonNull String str2, Object... objArr) {
        android.util.Log.w(str, safeFormat(str2, objArr), th);
    }

    @NonNull
    public static String getStackTraceString(int i) {
        if (!isRelease) {
            StackTraceElement[] outline195 = GeneratedOutlineSupport1.outline195();
            StringBuilder sb = new StringBuilder();
            int i2 = i + 1;
            for (int i3 = 1; i3 < outline195.length && i3 < i2; i3++) {
                sb.append(outline195[i3].toString());
                sb.append(LINE_SEPARATOR);
            }
            return sb.toString();
        }
        return "";
    }
}
