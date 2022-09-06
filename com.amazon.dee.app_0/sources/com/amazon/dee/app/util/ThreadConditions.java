package com.amazon.dee.app.util;

import android.os.Looper;
/* loaded from: classes12.dex */
public final class ThreadConditions {
    private ThreadConditions() {
    }

    public static boolean isOnMainThread() {
        return Thread.currentThread() == Looper.getMainLooper().getThread();
    }
}
