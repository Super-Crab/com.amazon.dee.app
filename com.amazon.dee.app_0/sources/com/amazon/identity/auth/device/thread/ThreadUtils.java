package com.amazon.identity.auth.device.thread;

import android.os.Looper;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
/* loaded from: classes12.dex */
public final class ThreadUtils {
    public static final Executor THREAD_POOL = Executors.newCachedThreadPool(new ThreadFactory() { // from class: com.amazon.identity.auth.device.thread.ThreadUtils.1
        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable runnable) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("AmazonAuthorzationLibrary#");
            outline107.append(ThreadUtils.access$004());
            return new Thread(runnable, outline107.toString());
        }
    });
    private static int sThreadNum;

    private ThreadUtils() {
    }

    static /* synthetic */ int access$004() {
        int i = sThreadNum + 1;
        sThreadNum = i;
        return i;
    }

    public static boolean isRunningOnMainThread() {
        return Looper.getMainLooper() != null && Looper.getMainLooper() == Looper.myLooper();
    }
}
