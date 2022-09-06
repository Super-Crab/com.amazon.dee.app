package com.dp.utils;
/* loaded from: classes2.dex */
public class ThreadGuard {
    private static final ThreadGuard INSTANCE = new ThreadGuard();

    public static void ensureThread(String str) {
        INSTANCE.doEnsureThread(str);
    }

    public static void ensureThreadPrefix(String str) {
        INSTANCE.doEnsureThreadPrefix(str);
    }

    public void doEnsureThread(String str) {
        if (str != null && str.trim().length() != 0) {
            String name = Thread.currentThread().getName();
            boolean z = true;
            FailFast.expectFalse(name == null);
            if (name.length() != 0) {
                z = false;
            }
            FailFast.expectFalse(z);
            FailFast.expectEquals(str, name);
            return;
        }
        throw new IllegalArgumentException("Must expect a thread name.");
    }

    public void doEnsureThreadPrefix(String str) {
        if (str != null && str.trim().length() != 0) {
            String name = Thread.currentThread().getName();
            FailFast.expectFalse(name == null);
            FailFast.expectFalse(name.length() == 0);
            FailFast.expectTrue(name.startsWith(str), "Prefix: ", str, " Actual: ", name);
            return;
        }
        throw new IllegalArgumentException("Must expect a thread prefix.");
    }
}
