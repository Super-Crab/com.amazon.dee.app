package com.amazon.alexa.utils.concurrent;

import android.util.Log;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
/* loaded from: classes10.dex */
class c implements RejectedExecutionHandler {
    private static final String a = c.class.getSimpleName();

    private String a(ThreadPoolExecutor threadPoolExecutor) {
        ThreadFactory threadFactory = threadPoolExecutor.getThreadFactory();
        String a2 = threadFactory instanceof d ? ((d) threadFactory).a() : "unknown";
        return threadPoolExecutor.toString() + ", named: " + a2;
    }

    @Override // java.util.concurrent.RejectedExecutionHandler
    public void rejectedExecution(Runnable runnable, ThreadPoolExecutor threadPoolExecutor) {
        String str = a;
        Log.e(str, "rejected execution of " + runnable + " on: " + a(threadPoolExecutor));
    }
}
