package bolts;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
/* loaded from: classes.dex */
final class AndroidExecutors {
    static final int CORE_POOL_SIZE;
    static final long KEEP_ALIVE_TIME = 1;
    static final int MAX_POOL_SIZE;
    private final Executor uiThread = new UIThreadExecutor();
    private static final AndroidExecutors INSTANCE = new AndroidExecutors();
    private static final int CPU_COUNT = Runtime.getRuntime().availableProcessors();

    /* loaded from: classes.dex */
    private static class UIThreadExecutor implements Executor {
        private UIThreadExecutor() {
        }

        @Override // java.util.concurrent.Executor
        public void execute(Runnable runnable) {
            new Handler(Looper.getMainLooper()).post(runnable);
        }
    }

    static {
        int i = CPU_COUNT;
        CORE_POOL_SIZE = i + 1;
        MAX_POOL_SIZE = (i * 2) + 1;
    }

    private AndroidExecutors() {
    }

    @SuppressLint({"NewApi"})
    public static void allowCoreThreadTimeout(ThreadPoolExecutor threadPoolExecutor, boolean z) {
        int i = Build.VERSION.SDK_INT;
        threadPoolExecutor.allowCoreThreadTimeOut(z);
    }

    public static ExecutorService newCachedThreadPool() {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(CORE_POOL_SIZE, MAX_POOL_SIZE, 1L, TimeUnit.SECONDS, new LinkedBlockingQueue());
        allowCoreThreadTimeout(threadPoolExecutor, true);
        return threadPoolExecutor;
    }

    public static Executor uiThread() {
        return INSTANCE.uiThread;
    }

    public static ExecutorService newCachedThreadPool(ThreadFactory threadFactory) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(CORE_POOL_SIZE, MAX_POOL_SIZE, 1L, TimeUnit.SECONDS, new LinkedBlockingQueue(), threadFactory);
        allowCoreThreadTimeout(threadPoolExecutor, true);
        return threadPoolExecutor;
    }
}
