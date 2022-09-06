package com.amazon.deecomms.util;

import android.os.Handler;
import android.os.Looper;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
/* loaded from: classes12.dex */
public final class ThreadUtils {
    private static final Executor THREAD_POOL = Executors.newCachedThreadPool(new NamedThreadFactory("DCALThreadPool"));

    private ThreadUtils() {
    }

    public static void checkMainThread() {
        if (isRunningOnMainThread()) {
            return;
        }
        throw new IllegalAccessError("UI Thread should execute this.");
    }

    public static void checkNotMainThread() {
        if (!isRunningOnMainThread()) {
            return;
        }
        throw new IllegalAccessError("UI Thread should not execute this.");
    }

    public static boolean isRunningOnMainThread() {
        return Looper.getMainLooper() != null && Looper.getMainLooper() == Looper.myLooper();
    }

    public static void runOffMainThread(Runnable runnable) {
        if (isRunningOnMainThread()) {
            THREAD_POOL.execute(runnable);
        } else {
            runnable.run();
        }
    }

    public static void runOnMainThread(Runnable runnable) {
        new Handler(Looper.getMainLooper()).post(runnable);
    }
}
