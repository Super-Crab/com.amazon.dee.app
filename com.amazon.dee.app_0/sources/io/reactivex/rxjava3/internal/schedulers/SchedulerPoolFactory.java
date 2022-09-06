package io.reactivex.rxjava3.internal.schedulers;

import io.reactivex.rxjava3.exceptions.Exceptions;
import io.reactivex.rxjava3.functions.Function;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
/* loaded from: classes3.dex */
public final class SchedulerPoolFactory {
    public static final boolean PURGE_ENABLED;
    static final String PURGE_ENABLED_KEY = "rx3.purge-enabled";
    public static final int PURGE_PERIOD_SECONDS;
    static final String PURGE_PERIOD_SECONDS_KEY = "rx3.purge-period-seconds";
    static final AtomicReference<ScheduledExecutorService> PURGE_THREAD = new AtomicReference<>();
    static final Map<ScheduledThreadPoolExecutor, Object> POOLS = new ConcurrentHashMap();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static final class ScheduledTask implements Runnable {
        ScheduledTask() {
        }

        @Override // java.lang.Runnable
        public void run() {
            Iterator it2 = new ArrayList(SchedulerPoolFactory.POOLS.keySet()).iterator();
            while (it2.hasNext()) {
                ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = (ScheduledThreadPoolExecutor) it2.next();
                if (scheduledThreadPoolExecutor.isShutdown()) {
                    SchedulerPoolFactory.POOLS.remove(scheduledThreadPoolExecutor);
                } else {
                    scheduledThreadPoolExecutor.purge();
                }
            }
        }
    }

    /* loaded from: classes3.dex */
    static final class SystemPropertyAccessor implements Function<String, String> {
        SystemPropertyAccessor() {
        }

        @Override // io.reactivex.rxjava3.functions.Function
        /* renamed from: apply  reason: avoid collision after fix types in other method */
        public String mo10358apply(String t) {
            return System.getProperty(t);
        }
    }

    static {
        SystemPropertyAccessor systemPropertyAccessor = new SystemPropertyAccessor();
        PURGE_ENABLED = getBooleanProperty(true, PURGE_ENABLED_KEY, true, true, systemPropertyAccessor);
        PURGE_PERIOD_SECONDS = getIntProperty(PURGE_ENABLED, PURGE_PERIOD_SECONDS_KEY, 1, 1, systemPropertyAccessor);
        start();
    }

    private SchedulerPoolFactory() {
        throw new IllegalStateException("No instances!");
    }

    public static ScheduledExecutorService create(ThreadFactory factory) {
        ScheduledExecutorService newScheduledThreadPool = Executors.newScheduledThreadPool(1, factory);
        tryPutIntoPool(PURGE_ENABLED, newScheduledThreadPool);
        return newScheduledThreadPool;
    }

    static boolean getBooleanProperty(boolean enabled, String key, boolean defaultNotFound, boolean defaultNotEnabled, Function<String, String> propertyAccessor) {
        if (enabled) {
            try {
                String mo10358apply = propertyAccessor.mo10358apply(key);
                return mo10358apply == null ? defaultNotFound : "true".equals(mo10358apply);
            } catch (Throwable th) {
                Exceptions.throwIfFatal(th);
                return defaultNotFound;
            }
        }
        return defaultNotEnabled;
    }

    static int getIntProperty(boolean enabled, String key, int defaultNotFound, int defaultNotEnabled, Function<String, String> propertyAccessor) {
        if (enabled) {
            try {
                String mo10358apply = propertyAccessor.mo10358apply(key);
                return mo10358apply == null ? defaultNotFound : Integer.parseInt(mo10358apply);
            } catch (Throwable th) {
                Exceptions.throwIfFatal(th);
                return defaultNotFound;
            }
        }
        return defaultNotEnabled;
    }

    public static void shutdown() {
        ScheduledExecutorService andSet = PURGE_THREAD.getAndSet(null);
        if (andSet != null) {
            andSet.shutdownNow();
        }
        POOLS.clear();
    }

    public static void start() {
        tryStart(PURGE_ENABLED);
    }

    static void tryPutIntoPool(boolean purgeEnabled, ScheduledExecutorService exec) {
        if (!purgeEnabled || !(exec instanceof ScheduledThreadPoolExecutor)) {
            return;
        }
        POOLS.put((ScheduledThreadPoolExecutor) exec, exec);
    }

    static void tryStart(boolean purgeEnabled) {
        if (purgeEnabled) {
            while (true) {
                ScheduledExecutorService scheduledExecutorService = PURGE_THREAD.get();
                if (scheduledExecutorService != null) {
                    return;
                }
                ScheduledExecutorService newScheduledThreadPool = Executors.newScheduledThreadPool(1, new RxThreadFactory("RxSchedulerPurge"));
                if (PURGE_THREAD.compareAndSet(scheduledExecutorService, newScheduledThreadPool)) {
                    ScheduledTask scheduledTask = new ScheduledTask();
                    int i = PURGE_PERIOD_SECONDS;
                    newScheduledThreadPool.scheduleAtFixedRate(scheduledTask, i, i, TimeUnit.SECONDS);
                    return;
                }
                newScheduledThreadPool.shutdownNow();
            }
        }
    }
}
