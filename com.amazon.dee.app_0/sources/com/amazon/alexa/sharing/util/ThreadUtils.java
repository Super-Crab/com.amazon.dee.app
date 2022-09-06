package com.amazon.alexa.sharing.util;

import android.os.Looper;
/* loaded from: classes10.dex */
public class ThreadUtils {
    public void checkNotMainThread() {
        if (!isRunningOnMainThread()) {
            return;
        }
        throw new IllegalAccessError("UI Thread should not execute this.");
    }

    public boolean isRunningOnMainThread() {
        return Looper.getMainLooper() != null && Looper.getMainLooper() == Looper.myLooper();
    }
}
