package com.amazon.alexa.utils;

import android.os.Handler;
import android.os.Looper;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
/* loaded from: classes10.dex */
public class ApiThreadHelper {
    private static Handler handler = new Handler(Looper.getMainLooper());

    private ApiThreadHelper() {
        throw new UnsupportedOperationException();
    }

    public static boolean runOnDefaultHandler(Handler handler2, Runnable runnable) {
        if (handler2.getLooper() == Looper.myLooper()) {
            runnable.run();
            return true;
        }
        return handler2.post(runnable);
    }

    public static <T> T runOnUiThread(Callable<T> callable, long j) throws Exception {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            return callable.call();
        }
        FutureTask futureTask = new FutureTask(callable);
        handler.post(futureTask);
        return (T) futureTask.get(j, TimeUnit.MILLISECONDS);
    }

    public static boolean runOnUiThread(Runnable runnable) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            runnable.run();
            return true;
        }
        return handler.post(runnable);
    }
}
