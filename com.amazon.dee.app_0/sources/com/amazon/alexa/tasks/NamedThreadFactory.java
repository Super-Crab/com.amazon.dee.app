package com.amazon.alexa.tasks;

import androidx.annotation.NonNull;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;
/* loaded from: classes10.dex */
final class NamedThreadFactory implements ThreadFactory {
    private final ThreadGroup mGroup;
    private final String mName;
    private final AtomicInteger mThreadNumber;
    private final int mThreadPriority;

    /* JADX INFO: Access modifiers changed from: package-private */
    public NamedThreadFactory(String str) {
        this(str, 4);
    }

    @Override // java.util.concurrent.ThreadFactory
    public Thread newThread(@NonNull Runnable runnable) {
        ThreadGroup threadGroup = this.mGroup;
        Thread thread = new Thread(threadGroup, runnable, this.mName + " #" + this.mThreadNumber.getAndIncrement(), 0L);
        if (thread.isDaemon()) {
            thread.setDaemon(false);
        }
        int priority = thread.getPriority();
        int i = this.mThreadPriority;
        if (priority != i) {
            thread.setPriority(i);
        }
        return thread;
    }

    NamedThreadFactory(String str, int i) {
        this.mThreadNumber = new AtomicInteger(1);
        this.mName = str;
        SecurityManager securityManager = System.getSecurityManager();
        this.mGroup = securityManager != null ? securityManager.getThreadGroup() : Thread.currentThread().getThreadGroup();
        this.mThreadPriority = i;
    }
}
