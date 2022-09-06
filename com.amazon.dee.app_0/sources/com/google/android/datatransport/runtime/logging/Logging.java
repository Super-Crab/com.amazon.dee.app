package com.google.android.datatransport.runtime.logging;

import android.util.Log;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* compiled from: com.google.android.datatransport:transport-runtime@@2.2.0 */
/* loaded from: classes2.dex */
public final class Logging {
    private Logging() {
    }

    public static void d(String str, String str2) {
        getTag(str);
    }

    public static void e(String str, String str2, Throwable th) {
        Log.e(getTag(str), str2, th);
    }

    private static String getTag(String str) {
        return GeneratedOutlineSupport1.outline72("TransportRuntime.", str);
    }

    public static void i(String str, String str2) {
        Log.i(getTag(str), str2);
    }

    public static void w(String str, String str2, Object obj) {
        Log.w(getTag(str), String.format(str2, obj));
    }

    public static void d(String str, String str2, Object obj) {
        getTag(str);
        String.format(str2, obj);
    }

    public static void d(String str, String str2, Object obj, Object obj2) {
        getTag(str);
        String.format(str2, obj, obj2);
    }

    public static void d(String str, String str2, Object... objArr) {
        getTag(str);
        String.format(str2, objArr);
    }
}
