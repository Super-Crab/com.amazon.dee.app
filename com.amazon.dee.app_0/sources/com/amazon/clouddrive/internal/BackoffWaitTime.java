package com.amazon.clouddrive.internal;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
/* loaded from: classes11.dex */
public final class BackoffWaitTime {
    private static final long BASE_WAIT_TIME = 1000;
    private static final int FAILURE_CEILING = 8;
    private static final RequestRetryCount mRequestRetryCount = new RequestRetryCount();

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes11.dex */
    public static class RequestRetryCount {
        private final Map<Class<?>, AtomicInteger> mRequestRetryCounts;

        private RequestRetryCount() {
            this.mRequestRetryCounts = new HashMap();
        }

        synchronized int getRetiresForRequestAndIncrement(Class<?> cls) {
            if (!this.mRequestRetryCounts.containsKey(cls)) {
                this.mRequestRetryCounts.put(cls, new AtomicInteger(1));
                return 0;
            }
            return this.mRequestRetryCounts.get(cls).getAndIncrement();
        }

        synchronized void removeRequestRetryCount(Class<?> cls) {
            this.mRequestRetryCounts.remove(cls);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void addSuccess(Class<?> cls) {
        mRequestRetryCount.removeRequestRetryCount(cls);
    }

    static long getMaxWaitTime(int i, Class<?> cls) {
        return 1000 << Math.min(Math.max(mRequestRetryCount.getRetiresForRequestAndIncrement(cls), i), 8);
    }

    public static long getNextWaitTime(int i, Class<?> cls) {
        return Math.round(Math.random() * getMaxWaitTime(i, cls));
    }
}
