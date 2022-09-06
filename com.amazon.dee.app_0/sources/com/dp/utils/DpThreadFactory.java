package com.dp.utils;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;
import org.apache.logging.log4j.util.ProcessIdUtil;
/* loaded from: classes2.dex */
public class DpThreadFactory implements ThreadFactory {
    private final AtomicInteger mNextThreadNumber;
    private final String mPrefix;
    protected final ThreadGroup mThreadGroup;

    public DpThreadFactory(String str) {
        if (str != null && str.trim().length() != 0) {
            this.mThreadGroup = DpThreadPoolUtil.buildThreadGroup(str);
            this.mPrefix = str;
            this.mNextThreadNumber = new AtomicInteger(0);
            return;
        }
        throw new IllegalArgumentException("prefix must not be NULL or empty");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String buildNextThreadName() {
        return this.mPrefix + ProcessIdUtil.DEFAULT_PROCESSID + this.mNextThreadNumber.getAndIncrement();
    }

    public ThreadGroup getThreadGroup() {
        return this.mThreadGroup;
    }

    @Override // java.util.concurrent.ThreadFactory
    public Thread newThread(Runnable runnable) {
        return new Thread(this.mThreadGroup, runnable, buildNextThreadName());
    }
}
