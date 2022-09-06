package com.dp.utils;

import com.amazon.dp.logger.DPLogger;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes2.dex */
public final class FailFast {
    private static final DPLogger log = new DPLogger();

    private FailFast() {
    }

    private static String buildFailFastMessage(String str, String str2, Object[] objArr) {
        StringBuilder sb = new StringBuilder("FailFast: ");
        sb.append("expected<");
        sb.append(str);
        sb.append("> actual<");
        sb.append(str2);
        sb.append("> ");
        if (objArr != null) {
            for (Object obj : objArr) {
                sb.append(obj);
            }
        }
        return sb.toString();
    }

    public static void expectEquals(String str, String str2) {
        failFastInternal(str, str2, new Object[0]);
    }

    public static void expectFalse(boolean z) {
        failFastInternal(!z, new Object[0]);
    }

    public static void expectNotNull(Object obj) {
        failFastInternal(obj != null, new Object[0]);
    }

    public static void expectNull(Object obj) {
        failFastInternal(obj == null, new Object[0]);
    }

    public static void expectTrue(boolean z) {
        failFastInternal(z, new Object[0]);
    }

    private static void failFastInternal(String str, String str2, Object... objArr) {
        if (str == null && str2 == null) {
            return;
        }
        if (str != null && str.equals(str2)) {
            return;
        }
        logFailFastMessage(buildFailFastMessage(str, str2, objArr));
        logStackTrace();
        throw new FailFastException();
    }

    private static void logFailFastMessage(String str) {
        log.error("", str, new Object[0]);
    }

    private static void logStackTrace() {
        StackTraceElement[] outline195 = GeneratedOutlineSupport1.outline195();
        for (int i = 0; i < outline195.length; i++) {
            log.error("", "\t> " + outline195[i].toString(), new Object[0]);
        }
    }

    public static void expectEquals(String str, String str2, Object... objArr) {
        failFastInternal(str, str2, objArr);
    }

    public static void expectFalse(boolean z, Object... objArr) {
        failFastInternal(!z, objArr);
    }

    public static void expectNotNull(Object obj, Object... objArr) {
        failFastInternal(obj != null, objArr);
    }

    public static void expectNull(Object obj, Object... objArr) {
        failFastInternal(obj == null, objArr);
    }

    public static void expectTrue(boolean z, Object... objArr) {
        failFastInternal(z, objArr);
    }

    public static void expectEquals(int i, int i2) {
        failFastInternal(i, i2, new Object[0]);
    }

    public static void expectEquals(int i, int i2, Object... objArr) {
        failFastInternal(i, i2, objArr);
    }

    private static void failFastInternal(int i, int i2, Object... objArr) {
        if (i == i2) {
            return;
        }
        logFailFastMessage(buildFailFastMessage(String.valueOf(i), String.valueOf(i2), objArr));
        logStackTrace();
        throw new FailFastException();
    }

    private static String buildFailFastMessage(Object[] objArr) {
        StringBuilder sb = new StringBuilder("FailFast: ");
        if (objArr != null) {
            for (Object obj : objArr) {
                sb.append(obj);
            }
        }
        return sb.toString();
    }

    private static void failFastInternal(boolean z, Object... objArr) {
        if (z) {
            return;
        }
        logFailFastMessage(buildFailFastMessage(objArr));
        logStackTrace();
        throw new FailFastException();
    }
}
