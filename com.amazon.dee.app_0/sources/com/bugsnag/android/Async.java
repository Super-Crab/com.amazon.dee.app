package com.bugsnag.android;

import androidx.annotation.NonNull;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class Async {
    private static final int KEEP_ALIVE_SECONDS = 30;
    private static final int CPU_COUNT = Runtime.getRuntime().availableProcessors();
    private static final int CORE_POOL_SIZE = Math.max(1, Math.min(CPU_COUNT - 1, 4));
    private static final int MAXIMUM_POOL_SIZE = (CPU_COUNT * 2) + 1;
    static final BlockingQueue<Runnable> POOL_WORK_QUEUE = new LinkedBlockingQueue(128);
    private static final ThreadFactory THREAD_FACTORY = new ThreadFactory() { // from class: com.bugsnag.android.Async.1
        private final AtomicInteger count = new AtomicInteger(1);

        @Override // java.util.concurrent.ThreadFactory
        @NonNull
        public Thread newThread(@NonNull Runnable runnable) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Bugsnag Thread #");
            outline107.append(this.count.getAndIncrement());
            return new Thread(runnable, outline107.toString());
        }
    };
    private static final ThreadPoolExecutor EXECUTOR = new ThreadPoolExecutor(CORE_POOL_SIZE, MAXIMUM_POOL_SIZE, 30, TimeUnit.SECONDS, POOL_WORK_QUEUE, THREAD_FACTORY);

    Async() {
    }

    static void cancelTasks() throws InterruptedException {
        Logger.info("Cancelling tasks");
        EXECUTOR.shutdown();
        EXECUTOR.awaitTermination(2000L, TimeUnit.MILLISECONDS);
        Logger.info("Finishing cancelling tasks");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void run(@NonNull Runnable runnable) throws RejectedExecutionException {
        EXECUTOR.execute(runnable);
    }
}
