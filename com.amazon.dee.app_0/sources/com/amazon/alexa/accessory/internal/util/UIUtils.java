package com.amazon.alexa.accessory.internal.util;

import android.os.Handler;
import android.os.Looper;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
/* loaded from: classes.dex */
public final class UIUtils {
    private static final Handler handler = new Handler(Looper.getMainLooper());

    private UIUtils() {
        throw new IllegalStateException("No instances!");
    }

    public static <T> T execute(Callable<T> callable) throws Exception {
        Preconditions.notNull(callable, "callable");
        if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
            return callable.call();
        }
        FutureTask futureTask = new FutureTask(callable);
        handler.post(futureTask);
        return (T) futureTask.get();
    }

    public static void schedule(Runnable runnable) {
        Preconditions.notNull(runnable, "runnable");
        handler.post(runnable);
    }
}
